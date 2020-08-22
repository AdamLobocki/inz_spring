package pl.adam.praca_inzynierska.currency.chf;

import pl.adam.praca_inzynierska.account.Transaction;

import javax.persistence.*;

@Entity
public class CHF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String actualizationDate;
    private double rate;
    private final String name = "CHF";


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(String actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public CHF() {
    }

    public CHF(String actualizationDate, double rate) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;
    }

    // 1. dodać pole final String name w encji
    // 2. dodac pole String name w TO(nie final)
    // 3. dodać w maperze - tylko tym TOmapper - mapowanie name z CHF na CHFTO
}
