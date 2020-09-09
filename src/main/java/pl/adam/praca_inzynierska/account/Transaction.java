package pl.adam.praca_inzynierska.account;

import pl.adam.praca_inzynierska.account.Account;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currencyName;
    private String buyDate;
    private String sellDate;
    private double buyRate;
    private double sellRate;
    private double amountBought;

    @ManyToOne
    private Account account;

    public Transaction(String currencyName, String buyDate, double buyRate, double amountBought, Account account) {
        this.currencyName = currencyName;
        this.buyDate = buyDate;
        this.buyRate = buyRate;
        this.amountBought = amountBought;
        this.account = account;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public double getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(double amountBought) {
        this.amountBought = amountBought;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction() {
    }

    public Transaction(String currencyName, String buyDate, String sellDate, double buyRate, double sellRate, double amountBought, Account account) {
        this.currencyName = currencyName;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
        this.amountBought = amountBought;
        this.account = account;
    }
}