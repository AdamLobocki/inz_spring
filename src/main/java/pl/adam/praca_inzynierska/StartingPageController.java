package pl.adam.praca_inzynierska;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartingPageController {


    @GetMapping("/")
    public String starting() {
        return "startingPage";
    }



}
