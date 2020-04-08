package pl.adam.praca_inzynierska.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.currency.apiGetter.RatesGetter;

@Component
public abstract class abstractService {

    private RatesGetter ratesGetter;

    @Autowired
    public abstractService() {
        this.ratesGetter = new RatesGetter();
    }

    public RatesGetter getRatesGetter() {
        return ratesGetter;
    }
}