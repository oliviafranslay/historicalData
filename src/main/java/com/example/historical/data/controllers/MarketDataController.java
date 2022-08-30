package com.example.historical.data.controllers;

import com.example.historical.data.models.*;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import com.example.historical.data.services.MarketDataCorrelationService;
import com.example.historical.data.services.MarketDataMovingAverageService;
import com.example.historical.data.services.MarketDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
public class MarketDataController {

    @Autowired
    private MarketDataService marketDataService;

    @Autowired
    private MarketDataCorrelationService marketDataCorrelationService;

    @Autowired
    private MarketDataMovingAverageService marketDataMovingAverageService;

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;


    @GetMapping("/marketdata")

    public List<MarketData> getAllMarketData() {
        log.info("Get all marketdata");
        return marketDataRepository.findAll();
    }

    @GetMapping("/marketdata/{underlying}")
    public List<MarketData> getMarketDataByTicker(
            @PathVariable(value = "underlying") String ticker) {
        Underlying underlying = underlyingRepository.findByTicker(ticker);
        log.info("Get all marketdata for {}", ticker);
        return marketDataRepository.findAllByUnderlying(underlying);
    }

    @GetMapping("/marketdata/{underlying}/byDate")
    public List<MarketData> getMarketDataByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable(value = "underlying") String ticker) {
        log.info("Get marketdata for {} from {} to {}", ticker, startDate, endDate);
        return marketDataService.findByDate(startDate, endDate, ticker);
    }

    @GetMapping("/statistic/{underlying}")
    public MarketDataStatistic getStatistic(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable(value = "underlying") String ticker) {
        log.info("Get marketdata statistic for {} from {} to {}", ticker, startDate, endDate);
        return marketDataService.companyStatistic(startDate, endDate, ticker);
    }

    @GetMapping("/covariance/{underlying1}/{underlying2}")
    public MarketDataCorrelation getCorrelation(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable(value = "underlying1") String ticker1,
            @PathVariable(value = "underlying2") String ticker2) {
        log.info("Get covariance data for {} and {} from {} to {}", ticker1, ticker2, startDate, endDate);
        return marketDataCorrelationService.correlation(startDate, endDate, ticker1, ticker2);
    }

    @GetMapping("/movingaverage/{underlying}")
    public List<Double> getMovingAverage(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("days") int days,
            @PathVariable(value = "underlying") String ticker) {
        return marketDataMovingAverageService.fiveDaysAverage(startDate, endDate, ticker, days);
    }

    @PostMapping("/marketdata")
    public List<MarketData> addMultipleMarketData(@Valid @RequestBody List<MarketDataDTO> marketDataDTO) {
        return marketDataService.addMultipleData(marketDataDTO);
    }

    @PutMapping("/marketdata/{id}")
    public MarketData updateMarketData(@PathVariable(value = "id") int id, @Valid @RequestBody MarketDataDTO marketDataDTO) {
        return marketDataService.editMarketData(id, marketDataDTO);
    }

    @DeleteMapping("/marketdata/{id}")
    public void deleteMarketData(
            @PathVariable(value = "id") int id) {
        marketDataRepository.deleteById(id);
    }
}
