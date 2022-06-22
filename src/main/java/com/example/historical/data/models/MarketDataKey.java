package com.example.historical.data.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class MarketDataKey {

    private final LocalDate date;
    private final String underlyingTicker;

    public MarketDataKey(LocalDate date, String underlyingTicker) {
        this.date = date;
        this.underlyingTicker = underlyingTicker;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUnderlyingTicker() {
        return underlyingTicker;
    }

    @Override
    public String toString() {
        return getDate() + " | " + getUnderlyingTicker();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MarketDataKey)) {
            return false;
        }
        MarketDataKey mktdatakey = (MarketDataKey) obj;
        return mktdatakey.date.equals(date) &&
                mktdatakey.underlyingTicker.equals(underlyingTicker);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + date.hashCode();
        result = 31 * result + underlyingTicker.hashCode();
        return result;
    }
}
