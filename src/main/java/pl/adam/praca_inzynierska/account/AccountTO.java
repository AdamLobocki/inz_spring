package pl.adam.praca_inzynierska.account;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AccountTO {

    private long id;
    private String username;
    private String password;
    private String email;
    private String accountCreateDate;
    private double balance;
    private String role;
    private List<TransactionTO> transactionTO;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

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
        this.password = passwordEncoder().encode(password);
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

    public AccountTO() {
    }

    public AccountTO(long id, String username, String password, String email, String accountCreateDate, double balance, List<TransactionTO> transactionTO,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountCreateDate = accountCreateDate;
        this.balance = balance;
        this.transactionTO = transactionTO;
        this.role = role;
    }
}
