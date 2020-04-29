package pl.adam.praca_inzynierska.currency.eur;

import pl.adam.praca_inzynierska.account.Transaction;

import javax.persistence.*;

@Entity
public class EUR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String actualizationDate;
    private double rate;


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



    public EUR() {
    }

    public EUR(String actualizationDate, double rate) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;

    }
}
