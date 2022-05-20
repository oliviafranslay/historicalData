package com.example.historical.data.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="marketdata")
@Cacheable
public class MarketData {
    @Id
    @GeneratedValue(generator = "optimized-sequence")
    private int id;
    @NotNull(message = "Date cannot be empty")
    @Column(name = "date", unique = true)
    private LocalDate date;
    @Column(name="open", nullable = false)
    @NotNull(message = "Open cannot be empty")
    private Double open;
    private Double high;
    private Double low;
    @NotNull(message = "Close cannot be empty")
    private Double close;
    private Double volume;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "underlying_id", nullable = false)
    @NotNull
    private Underlying underlying;

    public MarketData(){

    }

    public MarketData(int id, LocalDate date, Double open, Double high, Double low, Double close, Double volume, Underlying underlying) {
        this.id = id;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.underlying = underlying;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getOpen() {
        return open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setUnderlying(Underlying underlying) {
        this.underlying = underlying;
    }

    public Underlying getUnderlying() {
        return underlying;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}
