package pl.adam.praca_inzynierska.account;


public class TransactionTO {
    private long id;
    private String currencyName;
    private String buyDate;
    private String sellDate;
    private double buyRate;
    private double sellRate;
    private double amountBought;
    private AccountTO accountTO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public double getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(double amountBought) {
        this.amountBought = amountBought;
    }

    public AccountTO getAccount() {
        return accountTO;
    }

    public void setAccount(AccountTO accountTO) {
        this.accountTO = accountTO;
    }

    public TransactionTO() {
    }

    public TransactionTO(long id, String currencyName, String buyDate, String sellDate, double buyRate, double sellRate, double amountBought, AccountTO accountTO) {
        this.id = id;
        this.currencyName = currencyName;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
        this.amountBought = amountBought;
        this.accountTO = accountTO;
    }
}
