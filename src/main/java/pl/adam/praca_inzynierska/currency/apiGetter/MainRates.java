package pl.adam.praca_inzynierska.currency.apiGetter;

import java.util.ArrayList;

public class MainRates {
    ArrayList< CurrencyRates > rates = new ArrayList< CurrencyRates >();

    public ArrayList<CurrencyRates> getRates() {
        return rates;
    }

    public void setRates(ArrayList<CurrencyRates> rates) {
        this.rates = rates;
    }
}
