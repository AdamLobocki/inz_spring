package pl.adam.praca_inzynierska.currency.chf;


import org.springframework.stereotype.Component;


@Component
public class CHFMapper {
    public CHFTO chfTOMapper(CHF chf) {

        if (chf != null) {
            CHFTO chfTO = new CHFTO();
            chfTO.setActualizationDate(chf.getActualizationDate());
            chfTO.setRate(chf.getRate());

            return chfTO;
        }

        return null;
    }

    public CHF chfMapper(CHFTO chfTO) {

        if (chfTO != null) {
            CHF chf = new CHF();

            chf.setActualizationDate(chf.getActualizationDate());
            chf.setRate(chf.getRate());

            return chf;
        }

        return null;
    }

}
