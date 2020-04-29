package pl.adam.praca_inzynierska.currency.jpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class JPYMapper {

    private TransactionMapper transactionMapper;

    @Autowired
    public JPYMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

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

            jpy.setActualizationDate(jpyTO.getActualizationDate());
            jpy.setRate(jpyTO.getRate());



            return jpy;
        }

        return null;
    }
}
