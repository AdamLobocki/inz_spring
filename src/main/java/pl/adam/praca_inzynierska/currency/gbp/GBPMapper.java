package pl.adam.praca_inzynierska.currency.gbp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class GBPMapper {



    public GBPTO gbpTOMapper(GBP gbp) {
       if (gbp != null) {
        GBPTO gbpTO = new GBPTO();
        gbpTO.setActualizationDate(gbp.getActualizationDate());
        gbpTO.setRate(gbp.getRate());


        return gbpTO;
    }

        return null;
}

    public GBP gbpMapper(GBPTO gbpTO) {

        if (gbpTO != null) {
            GBP gbp = new GBP();

            gbp.setActualizationDate(gbpTO.getActualizationDate());
            gbp.setRate(gbpTO.getRate());


            return gbp;
        }

        return null;
    }
}
