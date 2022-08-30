package com.example.historical.data.services;

import com.example.historical.data.models.*;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MarketDataService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public MarketData toEntity(MarketDataDTO marketDataDTO) {
        Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.getUnderlyingTicker());
        log.info("Get {} data", underlying);
        MarketData marketData = new MarketData(marketDataDTO.getId(), marketDataDTO.getDate(), marketDataDTO.getOpen(), marketDataDTO.getHigh(), marketDataDTO.getLow(), marketDataDTO.getClose(), marketDataDTO.getVolume(), underlying);
        marketDataRepository.save(marketData);
        return marketData;
    }

    public List<MarketData> addMultipleData(List<MarketDataDTO> marketDataDTO) {
        List<MarketData> list = new ArrayList<>();

        try {
            Map<MarketDataKey, MarketData> existingMarketDataMap = marketDataRepository.findAll()
                    .stream()
                    .collect(Collectors.toMap(i -> new MarketDataKey(i.getDate(), i.getUnderlying().getTicker()), i -> i));

            for (int i = 0; i < marketDataDTO.size(); i++) {
                Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.get(i).getUnderlyingTicker());
                MarketData marketData = new MarketData(marketDataDTO.get(i).getId(), marketDataDTO.get(i).getDate(), marketDataDTO.get(i).getOpen(), marketDataDTO.get(i).getHigh(), marketDataDTO.get(i).getLow(), marketDataDTO.get(i).getClose(), marketDataDTO.get(i).getVolume(), underlying);
                MarketDataKey marketDataKey = new MarketDataKey(marketData.getDate(), marketData.getUnderlying().getTicker());
                if (!existingMarketDataMap.containsKey(marketDataKey)) {
                    list.add(marketData);
                    existingMarketDataMap.put(marketDataKey, marketData);
                }
            }
            marketDataRepository.saveAll(list);
            return list;
        } catch (Exception exception) {
            Collection<String> tickers = marketDataDTO.stream().map(i -> i.getUnderlyingTicker()).collect(Collectors.toSet());
            log.error("Error: {} when adding multiple market data for tickers: {}", exception.getMessage(), tickers);
            return Collections.emptyList();
//            throw exception;
        }
    }

    public MarketData editMarketData(int id, MarketDataDTO marketDataDTO) {
        Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.getUnderlyingTicker());
        MarketData marketData = marketDataRepository.findById(id);
        marketData.setDate(marketDataDTO.getDate());
        marketData.setOpen(marketDataDTO.getOpen());
        marketData.setHigh(marketData.getHigh());
        marketData.setLow(marketDataDTO.getLow());
        marketData.setClose(marketDataDTO.getClose());
        marketData.setVolume(marketDataDTO.getVolume());
        marketData.setUnderlying(underlying);
        log.info("Edit {} , id: {}", underlying, marketData);
        return marketDataRepository.save(marketData);
    }

    public List<MarketData> findByDate(LocalDate startDate, LocalDate endDate, String ticker) {
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

        return byDate;
    }

    public MarketDataStatistic companyStatistic(LocalDate startDate, LocalDate endDate, String ticker) {
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

        Double min = byDate
                .stream()
                .mapToDouble(MarketData::getClose)
                .min()
                .orElse(0);

        Double max = byDate
                .stream()
                .mapToDouble(MarketData::getClose)
                .max()
                .orElse(0);

        Double averagePrice = byDate
                .stream()
                .collect(Collectors.averagingDouble(MarketData::getClose));

        MarketDataStatistic statistic = new MarketDataStatistic(averagePrice, max, min, startDate, endDate, underlying);
        return statistic;
    }
}
