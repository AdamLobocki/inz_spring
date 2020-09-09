package pl.adam.praca_inzynierska.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.AllCurrenciesServices;
import pl.adam.praca_inzynierska.currency.chf.CHFTO;
import pl.adam.praca_inzynierska.currency.eur.EURService;
import pl.adam.praca_inzynierska.currency.eur.EURTO;
import pl.adam.praca_inzynierska.currency.gbp.GBPTO;
import pl.adam.praca_inzynierska.currency.jpy.JPYTO;
import pl.adam.praca_inzynierska.currency.usd.USDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private AccountService accountService;
    private AccountMapper accountMapper;
    private AllCurrenciesServices allCurrenciesServices;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService, AccountMapper accountMapper,
                              AllCurrenciesServices allCurrenciesServices) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.allCurrenciesServices = allCurrenciesServices;
    }

    public List<TransactionTO> findAllTransaction() {
        return transactionRepository.findAll().stream()
                .map(transaction -> transactionMapper.transactionTOMapper(transaction))
                .collect(Collectors.toList());
    }

    public TransactionTO findTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            throw new IllegalArgumentException();
        }

        return transactionMapper.transactionTOMapper(transaction.get());
    }

    @Transactional
    public TransactionTO saveTransaction(TransactionTO transactionTO) {
        Transaction entity = transactionMapper.transactionMapper(transactionTO);
        entity = transactionRepository.save(entity);

        return transactionMapper.transactionTOMapper(entity);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<TransactionTO> getAllIdTransactions() {
        List<TransactionTO> allTransactions = findAllTransaction();
        List<TransactionTO> transactionsById = new ArrayList<>();
        for (TransactionTO transaction : allTransactions) {
            if (transaction.getAccount().getId() == accountService.getCurrentId()) {
                transactionsById.add(transaction);
            }
        }

        return transactionsById;
    }

//    public double getTransactionProfit(){
//        return
//    }

    @Transactional
    @Modifying
    List<TransactionTO> profitCalculation(List<TransactionTO> transactionToCalculate) {

        for (int i = 0; i < transactionToCalculate.size(); i++) {
            TransactionTO transactionTO = transactionToCalculate.get(i);

            String name = transactionTO.getCurrencyName();

            switch (name){
                case "EUR":
                    EURTO eurto = (EURTO) allCurrenciesServices.allCurrencies().get(name);
                    transactionTO.setSellRate(eurto.getRate());
                    break;
                case "USD":
                    USDTO usdto = (USDTO) allCurrenciesServices.allCurrencies().get(name);
                    transactionTO.setSellRate(usdto.getRate());
                    break;
                case "CHF":
                    CHFTO chfto = (CHFTO) allCurrenciesServices.allCurrencies().get(name);
                    transactionTO.setSellRate(chfto.getRate());
                    break;
                case "GBP":
                    GBPTO gbpto = (GBPTO) allCurrenciesServices.allCurrencies().get(name);
                    transactionTO.setSellRate(gbpto.getRate());
                    break;
                case "JPY":
                    JPYTO jpyto = (JPYTO) allCurrenciesServices.allCurrencies().get(name);
                    transactionTO.setSellRate(jpyto.getRate());
                    break;
            }
            Transaction transaction = transactionMapper.transactionMapper(transactionTO);
            transactionRepository.save(transaction);
            transactionToCalculate.set(i, transactionTO);
        }

        return transactionToCalculate;
    }

    private List<TransactionTO> findTransactionsToCalculate(String actualDate) {
        List<TransactionTO> transactionToCalculate = new ArrayList<>();

        for (TransactionTO transactionTO : findAllTransaction()) {
            if (transactionTO.getSellRate() == 0) {
                if (!actualDate.equals(transactionTO.getBuyDate()) && transactionTO.getSellDate() == null) {
                    transactionTO.setSellDate(actualDate);
                    transactionToCalculate.add(transactionTO);
                }
            }
        }

        return transactionToCalculate;
    }

    @Transactional
    public double balanceAfterSession() {

        List<TransactionTO> transactionsToCalculate = findTransactionsToCalculate(allCurrenciesServices.currencyDates().get(0));
        List<TransactionTO> profitCalculation = profitCalculation(transactionsToCalculate);

        for (TransactionTO transaction : profitCalculation) {
            accountService.updateAccountBalance(transaction);
        }

        return accountService.findAccountById(accountService.getCurrentId()).getBalance();
    }

}