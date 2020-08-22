package pl.adam.praca_inzynierska;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double transactionValue;
    private double amountBought;
    private double currencyRate;
    private String name;
    private String currencyDate;

    public ShoppingCart(double transactionValue, double currencyRate, String name, String currencyDate, double amountBought) {
        this.currencyRate = currencyRate;
        this.transactionValue = transactionValue;
        this.name = name;
        this.currencyDate = currencyDate;
        this.amountBought = amountBought;
    }

    public ShoppingCart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double amount) {
        this.transactionValue = amount;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(String currencyDate) {
        this.currencyDate = currencyDate;
    }

    public double getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(double amountBought) {
        this.amountBought = amountBought;
    }
}
