package pl.adam.praca_inzynierska.controllers;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.adam.praca_inzynierska.ShoppingCart;
import pl.adam.praca_inzynierska.ShoppingCartRepository;
import pl.adam.praca_inzynierska.account.*;
import pl.adam.praca_inzynierska.currency.AllCurrenciesServices;
import pl.adam.praca_inzynierska.currency.CurrencyFilters;
import pl.adam.praca_inzynierska.currency.CurrencyNames;
import pl.adam.praca_inzynierska.currency.chf.CHFService;
import pl.adam.praca_inzynierska.currency.chf.CHFTO;
import pl.adam.praca_inzynierska.currency.eur.EURService;
import pl.adam.praca_inzynierska.currency.eur.EURTO;
import pl.adam.praca_inzynierska.currency.gbp.GBPService;
import pl.adam.praca_inzynierska.currency.gbp.GBPTO;
import pl.adam.praca_inzynierska.currency.jpy.JPYService;
import pl.adam.praca_inzynierska.currency.jpy.JPYTO;
import pl.adam.praca_inzynierska.currency.usd.USDService;
import pl.adam.praca_inzynierska.currency.usd.USDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainPageController {

    private AllCurrenciesServices currenciesServices;
    private CHFService chfService;
    private EURService eurService;
    private GBPService gbpService;
    private USDService usdService;
    private JPYService jpyService;
    private ShoppingCartRepository shoppingCartRepository;
    private TransactionService transactionService;
    private AccountService accountService;
    private boolean flag;

    @Autowired
    public MainPageController(AllCurrenciesServices currenciesServices, CHFService chfService, EURService eurService,
                              GBPService gbpService, USDService usdService, JPYService jpyService, ShoppingCartRepository shoppingCartRepository,
                              TransactionService transactionService, AccountService accountService) {
        this.currenciesServices = currenciesServices;
        this.chfService = chfService;
        this.eurService = eurService;
        this.gbpService = gbpService;
        this.usdService = usdService;
        this.jpyService = jpyService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.flag = false;
    }

    @GetMapping("/mainPage")
    public String starting(Model model) {
        List<Double> allRates = new ArrayList<>();
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        try {
            allRates = currenciesServices.getAllRates();
        } catch (Exception ignored) {
        }

        model.addAttribute("flag", flag);
        model.addAttribute("rates", allRates);
        try {
            for (Double allRate : allRates) {
                model.addAttribute("currency", allRate);
            }
        } catch(Exception ignored) {
        }
        if (!shoppingCarts.isEmpty()) {
            model.addAttribute("emptyCart", true);
        }

        model.addAttribute("shoppingCarts", shoppingCarts);

        return "mainPage";
    }

    @GetMapping("/getCurrencies")
    private String getCurrencies() {
        Map<String, Object> allCurrencies = currenciesServices.allCurrencies();
        chfService.saveCHF((CHFTO) allCurrencies.get("CHF"));
        eurService.saveEUR((EURTO) allCurrencies.get("EUR"));
        usdService.saveUSD((USDTO) allCurrencies.get("USD"));
        gbpService.saveGBP((GBPTO) allCurrencies.get("GBP"));
        jpyService.saveJPY((JPYTO) allCurrencies.get("JPY"));

        flag = true;

        return "redirect:/mainPage";
    }

    @GetMapping("/createTransactions")
    public String createTransactions() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        for (ShoppingCart cart : shoppingCarts) {
            transactionCreation(cart);
        }

        // TODO walidacja zeby nie kupic za wincej hajsu niz jest
        // TODO dodanie daty
        shoppingCartRepository.deleteAll();

        return "redirect:/mainPage";
    }

    private TransactionTO transactionCreation (ShoppingCart shoppingCart) {
        TransactionTO transaction = new TransactionTO();
        transaction.setAmountBought(shoppingCart.getAmountBought());
        transaction.setCurrencyName(shoppingCart.getName());
        transaction.setBuyRate(shoppingCart.getCurrencyRate());

        AccountTO account = accountService.findAccountById(accountService.getCurrentId());
        transaction.setAccount(account);
        transactionService.saveTransaction(transaction);
        String name = transaction.getCurrencyName();

        return transaction;
    }




}
