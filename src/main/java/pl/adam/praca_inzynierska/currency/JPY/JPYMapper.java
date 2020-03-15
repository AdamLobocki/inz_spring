package pl.adam.praca_inzynierska.currency.JPY;

import org.springframework.stereotype.Component;


@Component
public class JPYMapper {
    public JPYTO jpyTOMapper(JPY jpy) {

        if (jpy != null) {
            JPYTO jpyTO = new JPYTO();
            jpyTO.setActualizationDate(jpy.getActualizationDate());
            jpyTO.setRate(jpy.getRate());

            return jpyTO;
        }

        return null;
    }

    public JPY jpyMapper(JPYTO jpyTO) {

        if (jpyTO != null) {
            JPY jpy = new JPY();

            jpy.setActualizationDate(jpy.getActualizationDate());
            jpy.setRate(jpy.getRate());

            return jpy;
        }

        return null;
    }
}
