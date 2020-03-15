package pl.adam.praca_inzynierska.currency.chf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class CHFMapper {

    private TransactionMapper transactionMapper;

    @Autowired
    public CHFMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }


    public CHFTO chfTOMapper(CHF chf) {

        if (chf != null) {
            CHFTO chfTO = new CHFTO();
            chfTO.setActualizationDate(chf.getActualizationDate());
            chfTO.setRate(chf.getRate());

            Transaction transaction = new Transaction();
            chfTO.setTransaction(transactionMapper.transactionTOMapper(transaction));


            return chfTO;
        }

        return null;
    }

    public CHF chfMapper(CHFTO chfTO) {

        if (chfTO != null) {
            CHF chf = new CHF();

            chf.setActualizationDate(chf.getActualizationDate());
            chf.setRate(chf.getRate());

            Transaction transaction = new Transaction();
            chfTO.setTransaction(transactionMapper.transactionTOMapper(transaction));


            return chf;
        }

        return null;
    }

}
