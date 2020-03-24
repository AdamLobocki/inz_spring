package pl.adam.praca_inzynierska.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    private AccountService accountService;
    private static final String ACCOUNT_HAS_BEEN_ADDED = "Konto zostało utworzone";

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccountPage")
    public String createAccount (AccountTO accountTO, Model model) {
        accountService.saveAccount(accountTO);
        model.addAttribute("errorMessage", ACCOUNT_HAS_BEEN_ADDED);
        return "startingPage";
    }
}
