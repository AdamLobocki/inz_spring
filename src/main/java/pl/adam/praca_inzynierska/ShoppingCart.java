package pl.adam.praca_inzynierska;

import javax.persistence.*;

// @Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Currency currencyName;
    private String boughtDate;
    private int rate;
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(Currency currencyName) {
        this.currencyName = currencyName;
    }

    public String getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(String boughtDate) {
        this.boughtDate = boughtDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShoppingCart(Currency currencyName, String boughtDate, int rate, int amount) {
        this.currencyName = currencyName;
        this.boughtDate = boughtDate;
        this.rate = rate;
        this.amount = amount;
    }
}
