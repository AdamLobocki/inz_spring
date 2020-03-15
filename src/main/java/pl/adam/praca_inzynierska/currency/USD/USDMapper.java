package pl.adam.praca_inzynierska.currency.USD;

import org.springframework.stereotype.Component;

@Component
public class USDMapper {
    public USDTO usdTOMapper(USD usd) {

        if (usd != null) {
            USDTO usdTO = new USDTO();
            usdTO.setActualizationDate(usd.getActualizationDate());
            usdTO.setRate(usd.getRate());

            return usdTO;
        }

        return null;
    }

    public USD usdMapper(USDTO usdTO) {

        if (usdTO != null) {
            USD usd = new USD();

            usd.setActualizationDate(usd.getActualizationDate());
            usd.setRate(usd.getRate());

            return usd;
        }

        return null;
    }
}
