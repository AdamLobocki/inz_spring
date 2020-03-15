package pl.adam.praca_inzynierska.currency.usd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;

@Component
public class USDMapper {
    private TransactionMapper transactionMapper;

    @Autowired
    public USDMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }


    public USDTO usdTOMapper(USD usd) {

        if (usd != null) {
            USDTO usdTO = new USDTO();
            usdTO.setActualizationDate(usd.getActualizationDate());
            usdTO.setRate(usd.getRate());

            Transaction transaction = new Transaction();
            usdTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return usdTO;
        }

        return null;
    }

    public USD usdMapper(USDTO usdTO) {

        if (usdTO != null) {
            USD usd = new USD();

            usd.setActualizationDate(usd.getActualizationDate());
            usd.setRate(usd.getRate());

            Transaction transaction = new Transaction();
            usdTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return usd;
        }

        return null;
    }
}
