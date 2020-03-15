package pl.adam.praca_inzynierska.currency.JPY;

import pl.adam.praca_inzynierska.account.TransactionTO;

import java.util.List;

public class JPYTO {
    private long id;
    private String actualizationDate;
    private double rate;
    private List<TransactionTO> transactionTO;

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

    public List<TransactionTO> getTransactionTO() {

        return transactionTO;
    }

    public void setTransactionTO(List<TransactionTO> transactionTO) {

        this.transactionTO = transactionTO;
    }

    public JPYTO() {
    }

    public JPYTO(String actualizationDate, double rate, List<TransactionTO> transactionTO) {
        this.actualizationDate = actualizationDate;
        this.rate = rate;
        this.transactionTO = transactionTO;
    }
}
