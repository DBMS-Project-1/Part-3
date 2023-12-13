import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;



public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private TreesDAO TreesDAO = new TreesDAO();
	    private QuotesDAO QuotesDAO = new QuotesDAO();
	    private BillsDAO BillsDAO = new BillsDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/listTrees":
        		System.out.println("The action is: listTrees");
        		listTree(request,response);
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
 	    	case "/submitQuote":
    			System.out.println("The action is: submitQuote");
    			activityPage(request,response, "");
    			break;
 	    	case "/insertQuote":
    			System.out.println("The action is: insertQuote");
    			insertQuote(request,response);
    			break;
 	    	case "/payBill":
 	    		System.out.println("The action is: payBill");
 	    		payBill(request, response);
 	    		break;



 	    	case "/listUserQuotes":
    			System.out.println("The action is: listUserQuote");
    			listUserQuote(request,response);
    			break;
 	    	case "/listDavidQuote":
    			System.out.println("The action is: listDavidQuote");
    			listDavidQuote(request,response);
    			break;
    			
    			
 	    	case "/insertUserReply":
    			System.out.println("The action is: insertUserReply");
    			insertUserReply(request,response);
    			break;
    			
 	    	case "/insertDavidReply":
    			System.out.println("The action is: insertDavidReply");
    			insertDavidReply(request,response);
    			break;

    			
        		
 	    	case "/Big clients":
        		System.out.println("Big clients clicked!");
        		BigClients(request,response,"");
        		break;
        		
 	    	case "/Easy clients":
        		System.out.println("Easy clients clicked!");
        		EasyClients(request,response,"");
        		break;
        		
 	    	case "/Prospective clients":
        		System.out.println("Prospective clients clicked!");
        		ProspectiveClients(request,response,"");
        		break;
        		
 	    	case "/One tree quotes":
        		System.out.println("One tree quotes clicked!");
        		OneTreeQuotes(request,response,"");
        		break;
        		
 	    	case "/Highest tree":
        		System.out.println("Highest tree clicked!");
        		HighestTree(request,response,"");
        		break;
        		
 	    	case "/Overdue bills":
        		System.out.println("Overdue bills clicked!");
        		OverdueBills(request,response,"");
        		break;
        		
 	    	case "/Bad clients":
        		System.out.println("Bad clients clicked!");
        		BadClients(request,response,"");
        		break;
        		
 	    	case "/Good clients":
        		System.out.println("Good clients clicked!");
        		GoodClients(request,response,"");
        		break;
        		
 	    	case "/Statistics":
        		System.out.println("Statistics clicked!");
        		Statistics(request,response,"");
        		break;
        		
	    	}
        	
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
	    
	    
	    //part 3 control servlet methods 
	    private void BigClients(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	        System.out.println("BigClients method");
	        QuotesDAO quotesDAO = new QuotesDAO();
	        // Retrieve the list of top clients
	        List<Integer> topClients = quotesDAO.getTopClients();
	        System.out.println("List of Top Clients:");
	        for (Integer clientId : topClients) {
	            System.out.println("Client ID: " + clientId);
	        }
	        request.setAttribute("topClients", topClients);
	        request.getRequestDispatcher("BigClients.jsp").forward(request, response);
	    }
	    
	    private void EasyClients(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	        System.out.println("EasyClients Method");
	        QuotesDAO quotesDAO = new QuotesDAO();
	        List<Integer> easyClients = quotesDAO.getEasyClients();
	        request.setAttribute("easyClients", easyClients);
	        request.getRequestDispatcher("EasyClients.jsp").forward(request, response);
	    }

	    private void ProspectiveClients(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	        System.out.println("ProspectiveClients method");
	        QuotesDAO quotesDAO = new QuotesDAO();
	        List<Integer> prospectiveClients = quotesDAO.getProspectiveClients();
	        request.setAttribute("prospectiveClients", prospectiveClients);
	        request.getRequestDispatcher("ProspectiveClients.jsp").forward(request, response);
	    }

	    private void OneTreeQuotes(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	        System.out.println("OneTreeQuotes method");
	        QuotesDAO quotesDAO = new QuotesDAO();
	        List<Integer> oneTreeQuotes = quotesDAO.getOneTreeQuotes();
	        request.setAttribute("oneTreeQuotes", oneTreeQuotes);
	        request.getRequestDispatcher("OneTreeQuotes.jsp").forward(request, response);
	    }

	    private void HighestTree(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	        System.out.println("HighestTree method");
	        QuotesDAO quotesDAO = new QuotesDAO();
	        List<Integer> highestTrees = quotesDAO.getHighestTrees();
	        request.setAttribute("highestTrees", highestTrees);
	        request.getRequestDispatcher("HighestTree.jsp").forward(request, response);
	    }
	    
	    private void OverdueBills(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("OverdueBills method");
	    	List<Integer> overdueBills = QuotesDAO.getOverdue();
	    	request.setAttribute("overdueBills", overdueBills);
	    	request.getRequestDispatcher("OverdueBills.jsp").forward(request, response);
	    }
	    
	    private void BadClients(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("BadClients method");
	    	List<Integer> badClients = QuotesDAO.getBadClients();
	    	request.setAttribute("badClients", badClients);
	    	request.getRequestDispatcher("BadClients.jsp").forward(request, response);
	    }
	    
	    private void GoodClients(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("GoodClients method");
	    	List<Integer> goodClients = QuotesDAO.getGoodClients();
	    	request.setAttribute("goodClients", goodClients);
	    	request.getRequestDispatcher("GoodClients.jsp").forward(request, response);
	    }
	    
	    private void Statistics(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("Statistics method");
	    	request.getRequestDispatcher("Statistics.jsp").forward(request, response);
	    }
	    
	    
	    
	    

	    
	    
	    
	    
	    
	    private void insertUserReply(HttpServletRequest request, HttpServletResponse response) 
	    		throws SQLException, IOException, ServletException, ParseException {
	    	
	    	int id = Integer.parseInt(request.getParameter("id"));
	        String acceptOrDeny = request.getParameter("acceptOrDeny");

	        boolean isAccepted;
	       
	        if (acceptOrDeny.equals("accept")) {
	        	isAccepted = true;
	        }
	        else {
	        	isAccepted = false;
	        }
	        
	        String Reply = request.getParameter("userReply");
	        System.out.println("Accept or deny quote: " + acceptOrDeny + ". Reply: " + Reply);

	        QuotesDAO.updateDavidReply(id, isAccepted, Reply);
	        response.sendRedirect("davidSmith.jsp");

	    }
	    
	    private void insertDavidReply(HttpServletRequest request, HttpServletResponse response) 
	    		throws SQLException, IOException, ServletException, ParseException {
	    	
	    	int id = Integer.parseInt(request.getParameter("id"));
	        String acceptOrDeny = request.getParameter("acceptOrDeny");

	        boolean isAccepted;
	       
	        if (acceptOrDeny.equals("accept")) {
	        	isAccepted = true;
	        }
	        else {
	        	isAccepted = false;
	        }
	        
	        String Reply = request.getParameter("davidReply");
	        System.out.println("Accept or deny quote: " + acceptOrDeny + ". Reply: " + Reply);

	        QuotesDAO.updateUserReply(id, isAccepted, Reply);
	        response.sendRedirect("activitypage.jsp");


	    }
	    
	    
	    private void listUserQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUserQuote started: 00000000000000000000000000000000000");
	        
	     
	        List<Quotes> listQuote = QuotesDAO.listUserQuotes();
	        request.setAttribute("listQuote", listQuote);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("listUserQuote.jsp");       
	        dispatcher.forward(request, response);

	     
	        System.out.println("listUserQuote finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listDavidQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listDavidQuote started: 00000000000000000000000000000000000");
	        int id = userDAO.getUserByEmail(currentUser);
	        System.out.println(id);
	     
	        List<Quotes> listQuote = QuotesDAO.listDavidQuotes(id);
	        request.setAttribute("listQuote", listQuote);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("listDavidQuote.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listDavidQuote finished: 111111111111111111111111111111111111");
	    }
	    	
	    
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listTree(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listTree started: 00000000000000000000000000000000000");

	     
	        List<Trees> listTree = TreesDAO.listAllTrees();
	        request.setAttribute("listTree", listTree);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("TreeList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listTree finished: 111111111111111111111111111111111111");
	    }

	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidSmith(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("David Smith");
	    	request.getRequestDispatcher("davidSmith.jsp").forward(request, response);
	    }
	    
	    
	    private void activityPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
	    	System.out.println("Acitivity page");
	        int userId = userDAO.getUserByEmail(currentUser);
	        Quotes quote = new Quotes(2, userId);
	        int quoteID = QuotesDAO.insertQuoteEmpty(quote);

	        System.out.println("insertQuoteEmpty");
	        System.out.println("I am hereee activityPage");
	        
	    	double size = Double.parseDouble(request.getParameter("size"));
	        double height = Double.parseDouble(request.getParameter("height"));
	        double distanceFromHouse = Double.parseDouble(request.getParameter("distanceFromHouse"));


	        // Create a new Tree instance
	        Trees tree = new Trees(quoteID, size, height, distanceFromHouse);

	        TreesDAO.insertTree(tree); // Set the Quote ID for the Tree

	        
	        response.sendRedirect("activitypage.jsp");
	    }
	    
	    private void insertQuote(HttpServletRequest request, HttpServletResponse response) 
	    		throws SQLException, IOException, ServletException, ParseException {
	    	
	    	int clientId = Integer.parseInt(request.getParameter("clientId"));
	    	double price = Double.parseDouble(request.getParameter("price"));
	    	
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	Date scheduleStart = format.parse(request.getParameter("scheduleStart"));
	    	Date scheduleEnd = format.parse(request.getParameter("scheduleEnd"));

	        
	        QuotesDAO.updateQuotes(clientId, price, scheduleStart, scheduleEnd);
	        response.sendRedirect("davidSmith.jsp");
	        


	    }
	    
	    private void payBill(HttpServletRequest request, HttpServletResponse response) 
	    		throws SQLException, IOException, ServletException, ParseException {
	    	int billId = Integer.parseInt(request.getParameter("id"));
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	Date paymentDate = format.parse(request.getParameter("date"));
	    	
	    	//send to sql here
	    	BillsDAO.updateBills(billId, paymentDate);
	    	
	    	System.out.println("Bill with id: " + billId + " was payed on: " + paymentDate);
	    	response.sendRedirect("activitypage.jsp");
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 if (email.equals("David Smith") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to David Smith");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 davidSmith(request, response, "");
	    	 }
	    	 
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");  	 
	   	 	String creditCardNum = request.getParameter("creditCardNum");  
	   	 	String confirm = request.getParameter("confirmation");
	   	 	String role = request.getParameter("role");
	        
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email, firstName, lastName, creditCardNum, password, role);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}

	    
}
	        
	        
	    
	        
	        
	        
	    


