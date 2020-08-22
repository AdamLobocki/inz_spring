package pl.adam.praca_inzynierska.currency.chf;

import pl.adam.praca_inzynierska.account.TransactionTO;

import java.util.List;

public class CHFTO {

    private long id;
    private String actualizationDate;
    private double rate;
    private String name;

    public CHFTO() {
    }

    public CHFTO(String actualizationDate, double rate, String name) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;
        this.name = name;
    }


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

    public void setName(String name) {
        this.name = name;
    }
}
