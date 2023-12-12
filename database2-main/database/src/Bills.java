import java.util.Date;

public class Bills {
    private int id;
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

    public Bills(int quoteId, double amountDue, double amountPaid, Date paymentDate, Date billGeneratedDate) {
        this.quoteId = quoteId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.billGeneratedDate = billGeneratedDate;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
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
