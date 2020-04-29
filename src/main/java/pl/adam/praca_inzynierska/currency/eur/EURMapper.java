package pl.adam.praca_inzynierska.currency.eur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class EURMapper {

    public EURTO eurTOMapper(EUR eur) {

        if (eur != null) {
            EURTO eurTO = new EURTO();
            eurTO.setActualizationDate(eur.getActualizationDate());
            eurTO.setRate(eur.getRate());


            return eurTO;
        }

        return null;
    }

    public EUR eurMapper(EURTO eurTO) {

        if (eurTO != null) {
            EUR eur = new EUR();

            eur.setActualizationDate(eurTO.getActualizationDate());
            eur.setRate(eurTO.getRate());


            return eur;
        }


        return null;
    }
}
