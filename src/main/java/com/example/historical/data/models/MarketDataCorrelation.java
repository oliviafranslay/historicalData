package com.example.historical.data.models;

public class MarketDataCorrelation {

    private Underlying underlying1;
    private Underlying underlying2;
    private Double covariance;
    private Double underlyingVariance1;
    private Double underlyingVariance2;
    private Double standardDeviation1;
    private Double standardDeviation2;
    private Double correlationData;

    public MarketDataCorrelation() {
    }

    public MarketDataCorrelation(Underlying underlying1, Underlying underlying2, Double covariance, Double underlyingVariance1, Double underlyingVariance2, Double standardDeviation1, Double standardDeviation2, Double correlationData) {
        this.underlying1 = underlying1;
        this.underlying2 = underlying2;
        this.covariance = covariance;
        this.underlyingVariance1 = underlyingVariance1;
        this.underlyingVariance2 = underlyingVariance2;
        this.standardDeviation1 = standardDeviation1;
        this.standardDeviation2 = standardDeviation2;
        this.correlationData = correlationData;
    }


    public Underlying getUnderlying1() {
        return underlying1;
    }

    public Underlying getUnderlying2() {
        return underlying2;
    }

    public Double getCovariance() {
        return covariance;
    }

    public Double getUnderlyingVariance1() {
        return underlyingVariance1;
    }

    public Double getUnderlyingVariance2() {
        return underlyingVariance2;
    }

    public Double getStandardDeviation1() {
        return standardDeviation1;
    }

    public Double getStandardDeviation2() {
        return standardDeviation2;
    }

    public Double getCorrelationData() {
        return correlationData;
    }

    @Override
    public String toString() {
        return getUnderlying1() + "|" + getUnderlyingVariance1() + "|" + getStandardDeviation1() + "|" + getUnderlying2() + "|" + getUnderlyingVariance2() + "|" + getStandardDeviation2() + "|" + getCovariance() + "|" + getCorrelationData();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MarketDataCorrelation)) {
            return false;
        }
        MarketDataCorrelation correlation = (MarketDataCorrelation) obj;
        return correlation.underlying1.equals(underlying1) &&
                correlation.underlying2.equals(underlying2) &&
                correlation.underlyingVariance1.equals(underlyingVariance1) &&
                correlation.underlyingVariance2.equals(underlyingVariance2) &&
                correlation.standardDeviation1.equals(standardDeviation1) &&
                correlation.standardDeviation2.equals(standardDeviation2) &&
                correlation.covariance.equals(covariance) &&
                correlation.correlationData.equals(correlationData);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + underlying1.hashCode();
        result = 31 * result + underlying2.hashCode();
        result = 31 * result + underlyingVariance2.hashCode();
        result = 31 * result + underlyingVariance2.hashCode();
        result = 31 * result + standardDeviation1.hashCode();
        result = 31 * result + standardDeviation2.hashCode();
        result = 31 * result + covariance.hashCode();
        result = 31 * result + correlationData.hashCode();
        return result;
    }
}
