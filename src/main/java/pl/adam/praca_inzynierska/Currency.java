package pl.adam.praca_inzynierska;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String actualizationDate;
    private int recentRate;
    private int day1Rate;
    private int day2Rate;
    private int day3Rate;
    private int day4Rate;
    private int day5Rate;

}
