package com.example.historical.data.models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "underlying")
@Cacheable
public class Underlying {
    @Id
    @GeneratedValue(generator = "optimized-sequence")

    private int id;
    @NotEmpty(message = "Ticker cannot be empty")
    @Column(name="ticker", unique = true)
    private String ticker;
    @NotEmpty(message = "Fullname cannot be empty")
    @Column(name="fullname", unique = true)
    private String fullname;
    @NotEmpty(message = "Exchange cannot be empty")
    private String exchange;


    public Underlying() {
    }

    public Underlying(String ticker, String fullname, String exchange) {
        this.ticker = ticker;
        this.fullname = fullname;
        this.exchange = exchange;
    }


    public int getId(){ return id;}

    public String getTicker() {
        return ticker;
    }

    public String getFullname() {
        return fullname;
    }

    public String getExchange() {
        return exchange;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
