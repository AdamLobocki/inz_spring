package pl.adam.praca_inzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adam.praca_inzynierska.account.AccountService;
import pl.adam.praca_inzynierska.account.AccountTO;

import java.util.List;

@Controller
public class RankPageController {


    private AccountService accountService;

    @Autowired
    public RankPageController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/rankPage")
    public String rank(Model model) {


        List<AccountTO> allAccountsInOrder = accountService.getRanks();

        model.addAttribute("allAccounts", allAccountsInOrder);

        return "rankPage";
    }
}
