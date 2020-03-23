package pl.adam.praca_inzynierska.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartingPageController {


    @GetMapping("/")
    public String starting() {
        return "startingPage";
    }

    @GetMapping("/createAccoutPage")
    public String addAccount() {

        return "createAccountPage";
    }



}
