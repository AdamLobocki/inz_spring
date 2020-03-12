package pl.adam.praca_inzynierska.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountTO> findAllAccount() {
        return accountRepository.findAll().stream()
                .map(account -> accountMapper.accountTOMapper(account))
                .collect(Collectors.toList());
    }

    public AccountTO findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return accountMapper.accountTOMapper(account.get());
    }

    @Transactional
    public AccountTO saveAccount(AccountTO accountTO){
        Account entity = accountMapper.accountMapper(accountTO);
        entity = accountRepository.save(entity);
        return accountMapper.accountTOMapper(entity);
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
