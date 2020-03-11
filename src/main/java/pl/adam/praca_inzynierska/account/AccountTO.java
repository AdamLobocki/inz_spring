package pl.adam.praca_inzynierska.account;


import java.util.List;

public class AccountTO {

    private long id;
    private String username;
    private String password;
    private String email;
    private String accountCreateDate;
    private double balance;
    private List<TransactionTO> transactionTO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountCreateDate() {
        return accountCreateDate;
    }

    public void setAccountCreateDate(String accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionTO> getTransactionTO() {
        return transactionTO;
    }

    public void setTransactionTO(List<TransactionTO> transactionTO) {
        this.transactionTO = transactionTO;
    }
}
