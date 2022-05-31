package com.example.historical.data.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "marketdata")
@Cacheable
public class MarketData {
    @Id
    @GeneratedValue(generator = "optimized-sequence")
    private int id;
    @NotNull(message = "Date cannot be empty")
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @NotNull(message = "Open cannot be empty")
    @Column(name = "open", nullable = false)
    private Double open;
    private Double high;
    private Double low;
    @NotNull(message = "Close cannot be empty")
    @Column(name = "close", nullable = false)
    private Double close;
    private Double volume;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "underlying_id", nullable = false)
    @NotNull
    private Underlying underlying;

    public MarketData() {
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

    @Override
    public String toString() {
        return getDate() + " | " + getOpen() + " | " + getHigh() + " | " + getLow() + " | " + getClose() + " | " + getVolume() + " | " + getUnderlying();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MarketData)) {
            return false;
        }
        MarketData mktdata = (MarketData) obj;
        return mktdata.date.equals(date) &&
                mktdata.open.equals(open) &&
                mktdata.high.equals(high) &&
                mktdata.low.equals(low) &&
                mktdata.close.equals(close) &&
                mktdata.volume.equals(volume) &&
                mktdata.underlying.equals(underlying);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + date.hashCode();
        result = 31 * result + open.hashCode();
        result = 31 * result + high.hashCode();
        result = 31 * result + low.hashCode();
        result = 31 * result + close.hashCode();
        result = 31 * result + volume.hashCode();
        result = 31 * result + underlying.hashCode();
        return result;
    }

}
