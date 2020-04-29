package pl.adam.praca_inzynierska.currency.jpy;

import pl.adam.praca_inzynierska.account.TransactionTO;

public class JPYTO {
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



    public JPYTO() {
    }

    public JPYTO(String actualizationDate, double rate) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;

    }
}
