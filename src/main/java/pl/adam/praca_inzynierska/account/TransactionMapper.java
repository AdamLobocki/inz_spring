package pl.adam.praca_inzynierska.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private AccountMapper accountMapper;

    @Autowired
    public TransactionMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public TransactionTO transactionTOMapper(Transaction transaction) {
        if (transaction != null) {
            TransactionTO transactionTO = new TransactionTO();
            transactionTO.setId(transaction.getId());
            transactionTO.setCurrencyName(transaction.getCurrencyName());
            transactionTO.setBuyDate(transaction.getBuyDate());
            transactionTO.setSellDate(transaction.getSellDate());
            transactionTO.setBuyRate(transaction.getBuyRate());
            transactionTO.setAmountBought(transaction.getAmountBought());

            Account account = new Account();
            transactionTO.setAccount(accountMapper.accountTOMapper(account));

            return transactionTO;
        }

        return null;
    }

    public Transaction transactionMapper(TransactionTO transactionTO) {

        if (transactionTO != null) {
            Transaction transaction = new Transaction();
            transaction.setCurrencyName(transactionTO.getCurrencyName());
            transaction.setBuyDate(transactionTO.getBuyDate());
            transaction.setSellDate(transactionTO.getSellDate());
            transaction.setBuyRate(transactionTO.getBuyRate());
            transaction.setAmountBought(transactionTO.getAmountBought());

            AccountTO account = new AccountTO();
            transaction.setAccount(accountMapper.accountMapper(account));

            return transaction;
        }

        return null;
    }

}
