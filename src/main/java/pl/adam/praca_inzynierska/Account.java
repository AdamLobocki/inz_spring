package pl.adam.praca_inzynierska;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private String accountCreateDate;
    private double balance;

    @OneToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private History history;



}
