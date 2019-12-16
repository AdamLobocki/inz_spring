package pl.adam.praca_inzynierska;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoryPageController {
    @GetMapping("/historyPage")
    public String starting() {
        return "historyPage";
    }
/*
    @RequestMapping("/mainPage")
    public String toMainPage() {
        return "mainPage";
    }
*/

}
