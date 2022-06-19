package com.example.historical.data.services;

import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.MarketDataCorrelation;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketDataCorrelationService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public MarketDataCorrelation correlation(LocalDate startDate, LocalDate endDate, String ticker1, String ticker2) {
        Underlying underlying1 = underlyingRepository.findByTicker(ticker1);
        Underlying underlying2 = underlyingRepository.findByTicker(ticker2);

        List<MarketData> byDate1 = marketDataRepository.findAllByUnderlying(underlying1)
                .stream()
                .filter(i -> i.getDate().compareTo(startDate) >= 0 && i.getDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(MarketData::getDate))
                .collect(Collectors.toList());

        List<MarketData> byDate2 = marketDataRepository.findAllByUnderlying(underlying2)
                .stream()
                .filter(i -> i.getDate().compareTo(startDate) >= 0 && i.getDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(MarketData::getDate))
                .collect(Collectors.toList());

        double dataSum1 = 0;
        double dataSum2 = 0;
        double sum = 0;
        double covariance = 0;
        List<Double> mean1 = new ArrayList<>();
        List<Double> mean2 = new ArrayList<>();

        //Calculating Mean
        for (int j = 0; j < byDate1.size(); j++) {
            if (j < byDate1.size() - 1) {
                double closingData1 = (byDate1.get(j + 1).getClose() - byDate1.get(j).getClose()) / byDate1.get(j).getClose() * 100;
                double closingData2 = (byDate2.get(j + 1).getClose() - byDate2.get(j).getClose()) / byDate2.get(j).getClose() * 100;
                dataSum1 += closingData1;
                mean1.add(closingData1);
                dataSum2 += closingData2;
                mean2.add(closingData2);
            } else {
                break;
            }
        }

        //Calculating Covariance
        for (int k = 0; k < mean1.size(); k++) {
            sum = sum + ((mean1.get(k) - (dataSum1 / mean1.size())) * (mean2.get(k) - (dataSum2 / mean2.size())));
        }
        covariance = sum / (mean1.size() - 1);

        double sumVariance1 = 0;
        double variance1 = 0;
        //Calculating Variance
        for (int i = 0; i < mean1.size(); i++) {
            sumVariance1 = sumVariance1 + (mean1.get(i) - (dataSum1 / mean1.size())) * (mean1.get(i) - (dataSum1 / mean1.size()));
        }
        variance1 = sumVariance1 / (mean1.size() - 1);

        double sumVariance2 = 0;
        double variance2 = 0;
        //Calculating Variance
        for (int i = 0; i < mean2.size(); i++) {
            sumVariance2 = sumVariance2 + (mean2.get(i) - (dataSum2 / mean2.size())) * (mean2.get(i) - (dataSum2 / mean2.size()));
        }
        variance2 = sumVariance2 / (mean2.size() - 1);

        //Calculating standard deviation
        double standardDeviation1 = Math.sqrt(variance1);
        double standardDeviation2 = Math.sqrt(variance2);

        double correlation = covariance / (standardDeviation1 * standardDeviation2);
        MarketDataCorrelation correlationData = new MarketDataCorrelation(underlying1, underlying2, covariance, variance1, variance2, standardDeviation1, standardDeviation2, correlation);
        return correlationData;
    }
}
