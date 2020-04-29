package pl.adam.praca_inzynierska.currency.chf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


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

            chf.setActualizationDate(chfTO.getActualizationDate());
            chf.setRate(chfTO.getRate());


            return chf;
        }

        return null;
    }

}
