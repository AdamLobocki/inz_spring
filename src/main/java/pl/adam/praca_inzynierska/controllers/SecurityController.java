package pl.adam.praca_inzynierska.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/login")
    public String letsLog(){
        return "/mainPage";
    }


}
