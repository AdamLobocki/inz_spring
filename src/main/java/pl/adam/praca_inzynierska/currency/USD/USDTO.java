package pl.adam.praca_inzynierska.currency.USD;

import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionTO;


public class USDTO {
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

    public USDTO() {
    }

    public USDTO(String actualizationDate, double rate, TransactionTO transactionTO) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;
        this.transactionTO = transactionTO;
    }
}
