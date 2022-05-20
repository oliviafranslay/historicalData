package com.example.historical.data.models;

import java.time.LocalDate;


public class MarketDataDTO {
    private int id;
    private LocalDate date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private String underlyingTicker;

    public MarketDataDTO() {
    }

        public MarketDataDTO(int id, LocalDate date, Double open, Double high, Double low, Double close, Double volume, String underlyingTicker) {
        this.id = id;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.underlyingTicker = underlyingTicker;
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

    public String getUnderlyingTicker() {
        return underlyingTicker;
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

    public void setUnderlyingTicker(String underlyingTicker) {
        this.underlyingTicker = underlyingTicker;
    }
}