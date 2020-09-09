package pl.adam.praca_inzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adam.praca_inzynierska.ShoppingCart;
import pl.adam.praca_inzynierska.ShoppingCartRepository;
import pl.adam.praca_inzynierska.currency.AllCurrenciesServices;
import pl.adam.praca_inzynierska.currency.eur.EURService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {

    private EURService eurService;
    private AllCurrenciesServices allCurrenciesServices;
    private ShoppingCartRepository shoppingCartRepository;
    private Double currencyRate;
    private String name;
    private String date;
    private int id;

    @Autowired
    public ShoppingCartController(EURService eurService, AllCurrenciesServices allCurrenciesServices,
                                  ShoppingCartRepository shoppingCartRepository) {
        this.eurService = eurService;
        this.allCurrenciesServices = allCurrenciesServices;
        this.shoppingCartRepository = shoppingCartRepository;

    }

    @GetMapping("/shoppingCart/{id}")
    public String shoppingCart(Model model, @PathVariable int id){
        currencyRate = allCurrenciesServices.getAllRates().get(id);
        name = allCurrenciesServices.currencyNames().get(id);
        date = allCurrenciesServices.currencyDates().get(id);
        model.addAttribute("value", currencyRate);
        model.addAttribute("name", name);
        model.addAttribute("shoppingCart", new ShoppingCart());

        return "shoppingCart" ;
    }

    @PostMapping("/shoppingCart")
    public String currencyBuying(ShoppingCart shoppingCart) {
        shoppingCart.setAmountBought(shoppingCart.getTransactionValue());
        shoppingCart.setCurrencyRate(currencyRate);
        shoppingCart.setTransactionValue(shoppingCart.getTransactionValue() * currencyRate);
        shoppingCart.setName(name);
        shoppingCart.setCurrencyDate(date);

        shoppingCartRepository.save(shoppingCart);

        return "redirect:/mainPage";
    }

}