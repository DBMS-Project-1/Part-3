import java.util.Date;

public class Bills {
    private int id;
    private int clientId;
    private String firstName; 
    private String lastName; 
    private int totalTreesCut;
    private int quoteId;
    private double amountDue;
    private double amountPaid;
    private Date paymentDate;
    private Date billGeneratedDate;

    // Constructors
    public Bills() {
    }

    public Bills(int id) {
        this.id = id;
    }

    public Bills(int id, int quoteId, double amountDue, double amountPaid, Date paymentDate, Date billGeneratedDate) {
    	this.id = id;
    	this.quoteId = quoteId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.billGeneratedDate = billGeneratedDate;
    }
    
    public Bills(int quoteId, double amountDue, double amountPaid, Date paymentDate, Date billGeneratedDate) {
        this.quoteId = quoteId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.billGeneratedDate = billGeneratedDate;
    }
    
    public Bills(int clientId, String firstName, String lastName, int totalTreesCut, double amountDue, double amountPaid, Date workDoneDate) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalTreesCut = totalTreesCut;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.billGeneratedDate = workDoneDate;
    }


    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalTreesCut() {
        return totalTreesCut;
    }

    public void setTotalTreesCut(int totalTreesCut) {
        this.totalTreesCut = totalTreesCut;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getBillGeneratedDate() {
        return billGeneratedDate;
    }

    public void setBillGeneratedDate(Date billGeneratedDate) {
        this.billGeneratedDate = billGeneratedDate;
    }
}
