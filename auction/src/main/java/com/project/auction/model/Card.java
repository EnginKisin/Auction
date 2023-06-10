package com.project.auction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String card_owner;

    @Column(nullable = false, unique = true, length = 16)
    private String number;

    @Column(nullable = false, length = 3)
    private String cvc;

    @Column(nullable = false, length = 2)
    private String month;

    @Column(nullable = false, length = 4)
    private String year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_owner() {
        return card_owner;
    }

    public void setCard_owner(String card_owner) {
        this.card_owner = card_owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
