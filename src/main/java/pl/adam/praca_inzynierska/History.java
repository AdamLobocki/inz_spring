package pl.adam.praca_inzynierska;

import javax.persistence.*;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String actualDate;
    private int currencyRate;
    private int amountBought;

    @ManyToOne
    private AccountBalance accountBalance;

}
