package pl.adam.praca_inzynierska.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adam.praca_inzynierska.currency.chf.CHFService;
import pl.adam.praca_inzynierska.currency.eur.EURService;
import pl.adam.praca_inzynierska.currency.gbp.GBPService;
import pl.adam.praca_inzynierska.currency.jpy.JPYService;
import pl.adam.praca_inzynierska.currency.usd.USDService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllCurrenciesServices {

    private CHFService chfService;
    private EURService eurService;
    private GBPService gbpService;
    private JPYService jpyService;
    private USDService usdService;

    @Autowired
    public AllCurrenciesServices(CHFService chfService, EURService eurService, GBPService gbpService, JPYService jpyService, USDService usdService) {
        this.chfService = chfService;
        this.eurService = eurService;
        this.gbpService = gbpService;
        this.jpyService = jpyService;
        this.usdService = usdService;
    }

    public Map<String, Object> allCurrencies() {
        Map<String, Object> mapOfCurrencies = new HashMap<>();
        mapOfCurrencies.put("CHF", chfService.chfTOObjectCreate());
        mapOfCurrencies.put("EUR", eurService.eurTOObjectCreate());
        mapOfCurrencies.put("GBP", gbpService.gbpTOObjectCreate());
        mapOfCurrencies.put("JPY", jpyService.jpyTOObjectCreate());
        mapOfCurrencies.put("USD", usdService.usdTOObjectCreate());

        return mapOfCurrencies;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public List<Double> getAllRates () {
        List<Double> allRatesList = new ArrayList<>();
        allRatesList.add(round(eurService.findLastRecord().getRate(), 4));
        allRatesList.add(round(usdService.findLastRecord().getRate(), 4));
        allRatesList.add(round(chfService.findLastCHFRecord().getRate(), 4));
        allRatesList.add(round(gbpService.findLastRecord().getRate(), 4));
        allRatesList.add(round(jpyService.findLastRecord().getRate() * 100, 5));

        return allRatesList;
    }

    public List<String> currencyNames() {
        List<String> names = new ArrayList<>();
        names.add("EUR");
        names.add("USD");
        names.add("CHF");
        names.add("GBP");
        names.add("JPY");

        return names;
    }
}