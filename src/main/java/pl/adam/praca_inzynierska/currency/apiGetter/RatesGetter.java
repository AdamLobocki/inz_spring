package pl.adam.praca_inzynierska.currency.apiGetter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RatesGetter {

    private final String chf = "http://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json";
    private final String eur = "http://api.nbp.pl/api/exchangerates/rates/a/eur/?format=json";
    private final String gbp = "http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json";
    private final String jpy = "http://api.nbp.pl/api/exchangerates/rates/a/jpy/?format=json";
    private final String usd = "http://api.nbp.pl/api/exchangerates/rates/a/usd/?format=json";

    public void run(String currency) {

        RestTemplate restTemplate = new RestTemplate();
        MainRates mainRates = restTemplate.getForObject(currency, MainRates.class);

        System.out.println("Waluta: " + ". Kurs: " + mainRates.getRates().get(0).getMid());
    }

    public double rate (String currencyLink) {
        RestTemplate restTemplate = new RestTemplate();
        MainRates mainRates = restTemplate.getForObject(currencyLink, MainRates.class);

        return mainRates.getRates().get(0).getMid();
    }

    public String date(String currencyLink) {
        RestTemplate restTemplate = new RestTemplate();
        MainRates mainRates = restTemplate.getForObject(currencyLink, MainRates.class);

        return mainRates.getRates().get(0).getEffectiveDate();
    }
}
