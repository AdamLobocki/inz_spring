package pl.adam.praca_inzynierska.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService, AccountMapper accountMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    public List<TransactionTO> findAllTransaction() {
        return transactionRepository.findAll().stream()
                .map(transaction -> transactionMapper.transactionTOMapper(transaction))
                .collect(Collectors.toList());
    }

    public TransactionTO findTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
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
            if (transaction.getId() == accountService.getCurrentId()) {
                transactionsById.add(transaction);
            }
        }

        return transactionsById;
    }

}
