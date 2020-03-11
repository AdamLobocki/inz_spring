package pl.adam.praca_inzynierska.account;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy="account")
    private List<Transaction> transaction;


}
