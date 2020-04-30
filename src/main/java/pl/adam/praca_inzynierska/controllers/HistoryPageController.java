package pl.adam.praca_inzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adam.praca_inzynierska.account.TransactionService;
import pl.adam.praca_inzynierska.account.TransactionTO;

import java.util.List;

@Controller
public class HistoryPageController {


    private TransactionService transactionService;

    @Autowired
    public HistoryPageController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/historyPage")
    public String starting(Model model) {

        List<TransactionTO> allTransactionByUser = transactionService.getAllIdTransactions();

        model.addAttribute("allTransaction", allTransactionByUser);


        return "historyPage";
    }




}
