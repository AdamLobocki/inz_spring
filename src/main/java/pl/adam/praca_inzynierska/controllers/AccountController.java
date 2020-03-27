package pl.adam.praca_inzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adam.praca_inzynierska.account.AccountService;
import pl.adam.praca_inzynierska.account.AccountTO;

import static pl.adam.praca_inzynierska.validators.AccountValidator.*;

@Controller
public class AccountController {

    private AccountService accountService;
    private static final String ACCOUNT_HAS_BEEN_ADDED = "Konto zostało utworzone";
    private static final String NO_USERNAME = "Username nie może być puste";
    private static final String NO_EMAIL = "email nie może być puste";
    private static final String NO_PASSWORD = "password nie może być puste";
    private static final String WRONG_EMAIL = "nie poprawny email";

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccountPage")
    public String createAccount (@ModelAttribute("account") AccountTO accountTO, Model model) {

        if (!checkEmptyUserName(accountTO)) {
            model.addAttribute("errorMessage", NO_USERNAME);
            return "createAccountPage";
        } else if (!checkEmptyEmail(accountTO)) {
            model.addAttribute("errorMessage,", NO_EMAIL);
            return "createAccountPage";
        } else if (!matchEmail(accountTO)) {
            model.addAttribute("errorMessage", WRONG_EMAIL);
            return "createAccountPage";
        } else if (!checkEmptyPassword(accountTO)) {
            model.addAttribute("errorMessage", NO_PASSWORD);
            return "createAccountPage";
        }
        accountService.saveAccount(accountTO);
        model.addAttribute("errorMessage", ACCOUNT_HAS_BEEN_ADDED);
        return "startingPage";
    }
}