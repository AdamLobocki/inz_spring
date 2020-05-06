package pl.adam.praca_inzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class MainPageController {

    private AllCurrenciesServices currenciesServices;
    private CHFService chfService;
    private EURService eurService;
    private GBPService gbpService;
    private USDService usdService;
    private JPYService jpyService;
    private boolean flag;

    @Autowired
    public MainPageController(AllCurrenciesServices currenciesServices, CHFService chfService, EURService eurService, GBPService gbpService, USDService usdService, JPYService jpyService) {
        this.currenciesServices = currenciesServices;
        this.chfService = chfService;
        this.eurService = eurService;
        this.gbpService = gbpService;
        this.usdService = usdService;
        this.jpyService = jpyService;
        this.flag = false;
    }

    @GetMapping("/mainPage")
    public String starting(Model model, @RequestParam(required = false) CurrencyNames currencyNames) {
        List<Double> allRates = new ArrayList<>();
        try {
            allRates = currenciesServices.getAllRates();
        } catch (Exception ignored) {
        }

        CurrencyFilters currencyFilters = new CurrencyFilters();
        currencyFilters.setCurrencyNames(currencyNames);
        Integer integer = null;

        model.addAttribute("flag", flag);
        model.addAttribute("rates", allRates);
        model.addAttribute("filters", currencyFilters);
        model.addAttribute("ilsoc", integer);

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

    @PostMapping("/mainPage")
    public String currencyBuying(Model model) {

        CurrencyFilters currencyFilters = new CurrencyFilters();
//        currencyFilters.setCurrencyNames(currencyNames);
        Integer integer = null;

        model.addAttribute("filters", currencyFilters);
        model.addAttribute("ilsoc", integer);

        return "redirect:/mainPage";
    }





}
