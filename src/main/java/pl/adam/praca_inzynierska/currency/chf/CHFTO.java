package pl.adam.praca_inzynierska.currency.chf;

import pl.adam.praca_inzynierska.account.TransactionTO;

import java.util.List;

public class CHFTO {

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



    public CHFTO() {
    }

    public CHFTO(String actualizationDate, double rate) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;

    }
}
