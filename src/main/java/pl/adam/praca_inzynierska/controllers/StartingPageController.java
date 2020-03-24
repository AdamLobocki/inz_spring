package pl.adam.praca_inzynierska.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adam.praca_inzynierska.account.AccountTO;

@Controller
public class StartingPageController {


    @GetMapping("/")
    public String starting() {
        return "startingPage";
    }

    @GetMapping("/createAccountPage")
    public String addAccount(Model model) {
        model.addAttribute("account", new AccountTO());

        return "createAccountPage";
    }



}
