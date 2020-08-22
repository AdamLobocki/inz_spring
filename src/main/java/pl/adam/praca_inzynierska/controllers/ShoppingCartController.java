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
    public String aa(Model model, @PathVariable int id){
        List<Double> allRates = allCurrenciesServices.getAllRates();
        List<String> names = allCurrenciesServices.currencyNames();
//        Map<String, Object> allCurrencies = allCurrenciesServices.allCurrencies();
        currencyRate = allRates.get(id);
        name = names.get(id);
        model.addAttribute("value", allRates.get(id));
        model.addAttribute("name", names.get(id));
        model.addAttribute("shoppingCart", new ShoppingCart());

        return "shoppingCart" ;
    }

    @PostMapping("/shoppingCart")
    public String currencyBuying(ShoppingCart shoppingCart) {
        shoppingCart.setAmountBought(shoppingCart.getTransactionValue());
        shoppingCart.setCurrencyRate(currencyRate);
        shoppingCart.setTransactionValue(shoppingCart.getTransactionValue() * currencyRate);
        shoppingCart.setName(name);
        shoppingCartRepository.save(shoppingCart);

        return "redirect:/mainPage";
    }

}

