package pl.adam.praca_inzynierska;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class SetOfCurrencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currencyDate;
    @OneToMany(mappedBy = "setOfCurrencies")
    private List<Currency> currency;
}
