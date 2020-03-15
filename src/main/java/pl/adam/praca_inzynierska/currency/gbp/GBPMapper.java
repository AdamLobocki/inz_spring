package pl.adam.praca_inzynierska.currency.gbp;


import org.springframework.stereotype.Component;


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

            gbp.setActualizationDate(gbp.getActualizationDate());
            gbp.setRate(gbp.getRate());

            return gbp;
        }

        return null;
    }
}
