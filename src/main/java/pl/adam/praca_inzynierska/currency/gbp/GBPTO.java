package pl.adam.praca_inzynierska.currency.gbp;

import pl.adam.praca_inzynierska.account.TransactionTO;

import java.util.List;

public class GBPTO {
    private long id;
    private String actualizationDate;
    private double rate;
    private TransactionTO transactionTO;

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

    public TransactionTO getTransaction() {
        return transactionTO;
    }

    public void setTransaction(TransactionTO transactionTO) {
        this.transactionTO = transactionTO;
    }

    public GBPTO() {
    }

    public GBPTO(String actualizationDate, double rate, TransactionTO transactionTO) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;
        this.transactionTO = transactionTO;
    }
}
