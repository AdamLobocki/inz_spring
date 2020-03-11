package pl.adam.praca_inzynierska.currency;

import pl.adam.praca_inzynierska.account.Transaction;

import javax.persistence.*;

@Entity
public class CHF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String actualizationDate;
    private double rate;
    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;
}
