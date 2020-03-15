package pl.adam.praca_inzynierska.currency.JPY;

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

            Transaction transaction = new Transaction();
            jpyTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return jpyTO;
        }

        return null;
    }

    public JPY jpyMapper(JPYTO jpyTO) {

        if (jpyTO != null) {
            JPY jpy = new JPY();

            jpy.setActualizationDate(jpy.getActualizationDate());
            jpy.setRate(jpy.getRate());

            Transaction transaction = new Transaction();
            jpyTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return jpy;
        }

        return null;
    }
}
