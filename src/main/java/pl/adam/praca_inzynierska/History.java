package pl.adam.praca_inzynierska;

import javax.persistence.*;
import java.util.List;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String actualDate;

    @OneToOne(mappedBy = "history")
    private Account account;

    @OneToMany(mappedBy = "history")
    private List<Transaction> transaction;





}
