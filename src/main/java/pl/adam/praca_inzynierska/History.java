package pl.adam.praca_inzynierska;

import javax.persistence.*;

// @Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    @OneToOne
    private Currency currency;
    private int currencyRate;
    private int amountBought;
    private int profit;

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(int currencyRate) {
        this.currencyRate = currencyRate;
    }

    public int getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(int amountBought) {
        this.amountBought = amountBought;
    }


    public History(String date, Currency currency, int currencyRate, int amountBought, int profit) {
        this.date = date;
        this.currency = currency;
        this.currencyRate = currencyRate;
        this.amountBought = amountBought;
        this.profit = profit;
    }
}
