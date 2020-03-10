package pl.adam.praca_inzynierska;


import javax.persistence.*;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String actualizationDate;
    private double rate;
    @ManyToOne
    private SetOfCurrencies setOfCurrencies;


}
