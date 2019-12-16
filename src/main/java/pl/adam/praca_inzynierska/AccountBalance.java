package pl.adam.praca_inzynierska;

import javax.persistence.*;

// @Entity
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int recentAccountBalance;
    private int profit;
    @ManyToOne
    private History history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getRecentAccountBalance() {
        return recentAccountBalance;
    }

    public void setRecentAccountBalance(int recentAccountBalance) {
        this.recentAccountBalance = recentAccountBalance;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public AccountBalance(int recentAccountBalance, int profit, History history) {
        this.recentAccountBalance = recentAccountBalance;
        this.profit = profit;
        this.history = history;
    }
}
