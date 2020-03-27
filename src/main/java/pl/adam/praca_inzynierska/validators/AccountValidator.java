package pl.adam.praca_inzynierska.validators;

import org.springframework.stereotype.Component;
import pl.adam.praca_inzynierska.account.AccountTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountValidator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
            .compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

    public static boolean checkEmptyUserName(AccountTO accountTO) {
        return accountTO.getUsername() != null && !accountTO.getUsername().isEmpty();
    }

    public static boolean checkEmptyEmail(AccountTO accountTO) {
        return accountTO.getEmail() != null && !accountTO.getEmail().isEmpty();
    }

    public static boolean matchEmail(AccountTO accountTO) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(accountTO.getEmail());
        return matcher.matches();
    }

    public static boolean checkEmptyPassword(AccountTO accountTO) {
        return accountTO.getPassword() != null && !accountTO.getPassword().isEmpty();

    }

}