package pl.adam.praca_inzynierska.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/mainPage")
    public String starting() {
        return "mainPage";
    }




}
