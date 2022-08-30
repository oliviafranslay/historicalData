package com.example.historical.data.services;

import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MarketDataMovingAverageService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public List<Double> fiveDaysAverage(LocalDate startDate, LocalDate endDate, String ticker, int days) {
        log.info("Get market data for ticker {} from {} to {}", ticker, startDate, endDate);
        Underlying underlying = underlyingRepository.findByTicker(ticker);

        if (underlying == null) {
            throw new IllegalArgumentException(String.format("Cannot find ticker : %s", ticker));
        }

        List<MarketData> byDate = marketDataRepository.findAllByUnderlying(underlying)
                .stream()
                .filter(i -> i.getDate().compareTo(startDate) >= 0 && i.getDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(MarketData::getDate))
                .collect(Collectors.toList());

        List<Double> fiveDaysAverage = new ArrayList<>();
        double sum = 0;
        List<Double> result = new ArrayList<>();

        for (int j = 0; j < byDate.size(); j++) {
            double closingPrice = byDate.get(j).getClose();
            if (fiveDaysAverage.size() < days) {
                fiveDaysAverage.add(closingPrice);
                sum += closingPrice;
                if (fiveDaysAverage.size() == days) {
                    result.add(sum / days);
                }
            } else {
                sum = sum - fiveDaysAverage.remove(0);
                fiveDaysAverage.add(closingPrice);
                sum += closingPrice;
                result.add(sum / days);
            }
        }
        return result;
    }
}
