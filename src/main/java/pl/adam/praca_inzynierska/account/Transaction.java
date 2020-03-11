package pl.adam.praca_inzynierska.account;

import pl.adam.praca_inzynierska.account.Account;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currencyName;
    private String buyDate;
    private String sellDate;
    private double buyRate;
    private double sellRate;
    private double amountBought;

    @ManyToOne
    private Account account;
}
