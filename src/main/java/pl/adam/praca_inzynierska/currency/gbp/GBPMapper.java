package pl.adam.praca_inzynierska.currency.gbp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionMapper;


@Component
public class GBPMapper {

    private TransactionMapper transactionMapper;

    @Autowired
    public GBPMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }


    public GBPTO gbpTOMapper(GBP gbp) {
       if (gbp != null) {
        GBPTO gbpTO = new GBPTO();
        gbpTO.setActualizationDate(gbp.getActualizationDate());
        gbpTO.setRate(gbp.getRate());

           Transaction transaction = new Transaction();
           gbpTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

        return gbpTO;
    }

        return null;
}

    public GBP gbpMapper(GBPTO gbpTO) {

        if (gbpTO != null) {
            GBP gbp = new GBP();

            gbp.setActualizationDate(gbpTO.getActualizationDate());
            gbp.setRate(gbpTO.getRate());

            Transaction transaction = new Transaction();
            gbpTO.setTransaction(transactionMapper.transactionTOMapper(transaction));

            return gbp;
        }

        return null;
    }
}
