package pl.adam.praca_inzynierska.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private Date date;
    private DateFormat dateFormat;

    public AccountService() {
    }

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    public List<AccountTO> findAllAccount() {
        return accountRepository.findAll().stream()
                .map(account -> accountMapper.accountTOMapper(account))
                .collect(Collectors.toList());
    }

    public AccountTO findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new IllegalArgumentException();
        }

        return accountMapper.accountTOMapper(account.get());
    }


//     public List<Account> accountRates = new ArrayList<>();
//        try {
//        accountRates = findAccountByUsername(userNamesSortedByRank(findAllAccount()))
//    } catch (Exception ignored) {
//    }


    public AccountTO findAccountByUsername(String username){
        Optional<Account> account = Optional.ofNullable(accountRepository.findAccountByUsername(username));
        if (!account.isPresent()) {
            throw new IllegalArgumentException();
        }

        return accountMapper.accountTOMapper(account.get());
    }


    private List<String> userNamesSortedByRank(List<AccountTO> allAccount) {
        Map<String, Double> result = new HashMap<>();
        allAccount.forEach(n -> result.put(n.getUsername(), n.getBalance()));
        return  result.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(n -> n.getKey())
                .collect(Collectors.toList());
    }

    public List<AccountTO> getRanks () {
        List<AccountTO> allRanksList = new ArrayList<>();
        List<String> userNameList = userNamesSortedByRank(findAllAccount());
        for (String userName : userNameList) {
            allRanksList.add(findAccountByUsername(userName));
        }

        return allRanksList;
    }

    public AccountTO saveAccount(AccountTO accountTO) {

        Account entity = accountMapper.accountMapper(accountTO);
        entity = accountRepository.save(entity);
        return accountMapper.accountTOMapper(entity);
    }


    @Transactional
    public AccountTO setAccountDetails(AccountTO accountTO){
        this.date = Calendar.getInstance().getTime();

        accountTO.setBalance(100);
        accountTO.setRole("USER");
        accountTO.setAccountCreateDate(dateFormat.format(date));

        return saveAccount(accountTO);
    }

    @Transactional
    @Modifying
    public AccountTO updateAccountBalance(TransactionTO transactionTO) {
        double newBalance = (transactionTO.getSellRate() - transactionTO.getBuyRate()) * transactionTO.getAmountBought();

        AccountTO accountById = findAccountById(transactionTO.getAccount().getId());
        accountById.setBalance(accountById.getBalance() + newBalance);
        Account account = accountMapper.accountMapper(accountById);
        accountRepository.save(account);

        return accountMapper.accountTOMapper(account);
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();   // pobieranie name zalogowanego użytkownika
    }

    public Long getCurrentId(){
        return findAccountByUsername(getUserName()).getId();
    }

}