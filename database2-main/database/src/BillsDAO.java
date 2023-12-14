import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

public class BillsDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

    
    public BillsDAO() {
    }

    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }
    
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    
    public List<Bills> listUserBills(int userId) throws SQLException {
        List<Bills> listBill = new ArrayList<>();
        String sql = "SELECT * FROM Bills WHERE quoteid = ?";
        connect_func();
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int quoteId = resultSet.getInt("quoteid");
            double amountDue = resultSet.getDouble("amountDue");
            double amountPaid = resultSet.getDouble("amountPaid");

            Timestamp paymentDateTimestamp = resultSet.getTimestamp("paymentDate");
            Timestamp billGeneratedDateTimestamp = resultSet.getTimestamp("billGeneratedDate");

            java.util.Date paymentDate = null;
            java.util.Date billGeneratedDate = null;

            if (paymentDateTimestamp != null) {
                paymentDate = new java.util.Date(paymentDateTimestamp.getTime());
            }

            if (billGeneratedDateTimestamp != null) {
                billGeneratedDate = new java.util.Date(billGeneratedDateTimestamp.getTime());
            }

            Bills bill = new Bills(id, quoteId, amountDue, amountPaid, paymentDate, billGeneratedDate);
            listBill.add(bill);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return listBill;
    }


    public int insertBillIfAccepted(int quoteId, double amountDue) throws SQLException {
    	System.out.println("insertBillIfAccepted method");
        connect_func("root", "pass1234"); 
        String query = "INSERT INTO Bills (quoteid, amountDue, amountPaid, paymentDate, billGeneratedDate) VALUES (?, ?, 0, NULL, NOW())";
        int id = -1;


        String checkAcceptanceQuery = "SELECT * FROM Quotes WHERE id = ? AND davidAccept = TRUE AND userAccept = TRUE";
        
        try (PreparedStatement pstmtCheck = connect.prepareStatement(checkAcceptanceQuery)) {
            pstmtCheck.setInt(1, quoteId);
            ResultSet rs = pstmtCheck.executeQuery();


            if (rs.next()) {
                try (PreparedStatement pstmtInsert = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    pstmtInsert.setInt(1, quoteId);
                    pstmtInsert.setDouble(2, amountDue);

                    int affectedRows = pstmtInsert.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating bill failed, no rows affected.");
                    }

                    try (ResultSet generatedKeys = pstmtInsert.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating bill failed, no ID obtained.");
                        }
                    }
                }
            }
        }
        System.out.println("The Bills generated id is: " + id);
        return id;
    }

    public List<Bills> getStatistics() throws SQLException {
        connect_func("root", "pass1234"); // Connect to the database
        List<Bills> billsList = new ArrayList<>();
        String sql = "SELECT " +
                     "u.id AS client_id, " +
                     "u.firstname, " +
                     "u.lastname, " +
                     "COUNT(t.id) AS total_trees_cut, " +
                     "SUM(b.amountDue) AS total_due_amount, " +
                     "SUM(b.amountPaid) AS total_paid_amount, " +
                     "q.scheduleend AS work_done_date " +
                     "FROM Users u " +
                     "JOIN Quotes q ON u.id = q.clientid " +
                     "JOIN Trees t ON q.id = t.quoteid " +
                     "JOIN Bills b ON q.id = b.quoteid " +
                     "WHERE q.userAccept = TRUE " +
                     "AND q.davidAccept = TRUE " +
                     "AND q.scheduleend < CURRENT_DATE() " +
                     "GROUP BY u.id, q.scheduleend " +
                     "ORDER BY u.id, q.scheduleend;";

        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bills bill = new Bills(
                    rs.getInt("client_id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getInt("total_trees_cut"),
                    rs.getDouble("total_due_amount"),
                    rs.getDouble("total_paid_amount"),
                    rs.getDate("work_done_date")
                );
                billsList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return billsList;
    }




    
    
    public List<Integer> getBadClients() throws SQLException {
        connect_func("root", "pass1234");
        List<Integer> badClients = new ArrayList<>();
        String sql = "SELECT DISTINCT q.clientid " +
                     "FROM Quotes q " +
                     "JOIN Bills b ON q.id = b.quoteid " +
                     "WHERE b.amountPaid = 0 " +
                     "AND b.paymentDate > DATE_ADD(b.billGeneratedDate, INTERVAL 7 DAY);";

        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                badClients.add(rs.getInt("clientid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return badClients;
    }

    
    
    
    
    
    
    
    
    
    public void updateBills(int id, double amountPaid, Date paymentDate) throws SQLException {
        connect_func("root", "pass1234");
        System.out.println("I am hereee updateBills");
        String query = "UPDATE Bills SET paymentDate = ?, amountPaid = ? WHERE id = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setTimestamp(1, new java.sql.Timestamp(paymentDate.getTime()));
            pstmt.setDouble(2, amountPaid);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }

    
    
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    
}