package pl.adam.praca_inzynierska;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(String actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public int getRecentRate() {
        return recentRate;
    }

    public void setRecentRate(int recentRate) {
        this.recentRate = recentRate;
    }

    public int getDay1Rate() {
        return day1Rate;
    }

    public void setDay1Rate(int day1Rate) {
        this.day1Rate = day1Rate;
    }

    public int getDay2Rate() {
        return day2Rate;
    }

    public void setDay2Rate(int day2Rate) {
        this.day2Rate = day2Rate;
    }

    public int getDay3Rate() {
        return day3Rate;
    }

    public void setDay3Rate(int day3Rate) {
        this.day3Rate = day3Rate;
    }

    public int getDay4Rate() {
        return day4Rate;
    }

    public void setDay4Rate(int day4Rate) {
        this.day4Rate = day4Rate;
    }

    public int getDay5Rate() {
        return day5Rate;
    }

    public void setDay5Rate(int day5Rate) {
        this.day5Rate = day5Rate;
    }

    public Currency(String name, String actualizationDate, int recentRate, int day1Rate, int day2Rate, int day3Rate, int day4Rate, int day5Rate) {
        this.name = name;
        this.actualizationDate = actualizationDate;
        this.recentRate = recentRate;
        this.day1Rate = day1Rate;
        this.day2Rate = day2Rate;
        this.day3Rate = day3Rate;
        this.day4Rate = day4Rate;
        this.day5Rate = day5Rate;
    }
}
