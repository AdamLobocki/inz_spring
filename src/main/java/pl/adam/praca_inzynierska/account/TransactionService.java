package pl.adam.praca_inzynierska.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
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
    public TransactionTO saveTransaction(TransactionTO transactionTO){
        Transaction entity = transactionMapper.transactionMapper(transactionTO);
        entity = transactionRepository.save(entity);
        return transactionMapper.transactionTOMapper(entity);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
