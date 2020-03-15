package pl.adam.praca_inzynierska.currency.eur;

import org.springframework.stereotype.Component;


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

            eur.setActualizationDate(eur.getActualizationDate());
            eur.setRate(eur.getRate());

            return eur;
        }

        return null;
    }
}
