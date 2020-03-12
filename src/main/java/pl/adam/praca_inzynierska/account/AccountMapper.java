package pl.adam.praca_inzynierska.account;


import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountTO accountTOMapper(Account account) {

        if (account != null) {
            AccountTO accountTO = new AccountTO();
            accountTO.setUsername(account.getUsername());
            accountTO.setPassword(account.getPassword());
            accountTO.setEmail(account.getEmail());
            accountTO.setAccountCreateDate(account.getAccountCreateDate());
            accountTO.setBalance(account.getBalance());

            return accountTO;
        }

        return null;
    }

    public Account accountMapper(AccountTO accountTO) {

        if (accountTO != null) {
            Account account = new Account();

            account.setUsername(account.getUsername());
            account.setPassword(account.getPassword());
            account.setEmail(account.getEmail());
            account.setAccountCreateDate(account.getAccountCreateDate());
            account.setBalance(account.getBalance());

            return account;
        }

        return null;
    }
}