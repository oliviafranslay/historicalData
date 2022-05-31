package com.example.historical.data.models;

import java.time.LocalDate;

public class MarketDataStatistic {

    private Double averagePrice;
    private Double max;
    private Double min;
    private LocalDate startDate;
    private LocalDate endDate;
    private Underlying underlying;

    public MarketDataStatistic() {
    }

    public MarketDataStatistic(Double averagePrice, Double max, Double min, LocalDate startDate, LocalDate endDate, Underlying underlying) {
        this.averagePrice = averagePrice;
        this.max = max;
        this.min = min;
        this.startDate = startDate;
        this.endDate = endDate;
        this.underlying = underlying;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public Underlying getUnderlying(){
        return underlying;
    }

    @Override
    public String toString() {
        return getAveragePrice() + "|" + getMax() + "|" + getMin() + "|" + getStartDate() + "|" + getEndDate() + "|" + getUnderlying();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MarketDataStatistic)) {
            return false;
        }
        MarketDataStatistic statistic = (MarketDataStatistic) obj;
        return statistic.averagePrice.equals(averagePrice) &&
                statistic.max.equals(max) &&
                statistic.min.equals(min) &&
                statistic.startDate.equals(startDate) &&
                statistic.endDate.equals(endDate) &&
                statistic.underlying.equals(underlying);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + averagePrice.hashCode();
        result = 31 * result + max.hashCode();
        result = 31 * result + min.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + underlying.hashCode();
        return result;
    }

}
