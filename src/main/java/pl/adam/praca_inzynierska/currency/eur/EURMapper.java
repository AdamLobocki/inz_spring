package pl.adam.praca_inzynierska.currency.eur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class EURMapper {

    private TransactionMapper transactionMapper;

    @Autowired
    public EURMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }
    public EURTO eurTOMapper(EUR eur) {

        if (eur != null) {
            EURTO eurTO = new EURTO();
            eurTO.setActualizationDate(eur.getActualizationDate());
            eurTO.setRate(eur.getRate());

            Transaction transaction = new Transaction();
            eurTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return eurTO;
        }

        return null;
    }

    public EUR eurMapper(EURTO eurTO) {

        if (eurTO != null) {
            EUR eur = new EUR();

            eur.setActualizationDate(eurTO.getActualizationDate());
            eur.setRate(eurTO.getRate());

            Transaction transaction = new Transaction();
            eurTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return eur;
        }


        return null;
    }
}
