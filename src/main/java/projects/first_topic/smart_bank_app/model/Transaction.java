package projects.first_topic.smart_bank_app.model;

public class Transaction {
    private Integer transaction_id;
    private Integer account_id;
    private String transaction_type;
    private double transaction_amount;
    private String transaction_date;

    public Transaction() {
    }

    public Transaction(Integer account_id, String transaction_type, double transaction_amount, String transaction_date) {
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_date = transaction_date;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getAccount_id() { return account_id; }

    public void setAccount_id(Integer account_id) { this.account_id = account_id; }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }


}
