package com.example.historical.data.controllers;
import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.MarketDataDTO;
import com.example.historical.data.models.MarketDataStatistic;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import com.example.historical.data.services.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class MarketDataController {

    @Autowired
    private MarketDataService marketDataService;

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;


    @GetMapping("/marketdata")
    public List<MarketData> getAllMarketData() {
        return marketDataRepository.findAll();
    }

    @GetMapping("/marketdata/{underlying}")
    public List<MarketData> getMarketDataByTicker(
            @PathVariable(value = "underlying") String ticker) {
        Underlying underlying = underlyingRepository.findByTicker(ticker);
        return marketDataRepository.findAllByUnderlying(underlying);
    }

    @GetMapping("/marketdata/{underlying}/byDate")
    public List<MarketData> getMarketDataByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable(value = "underlying") String ticker) {
        return marketDataService.findByDate(startDate, endDate, ticker);
    }

    @GetMapping("/statistic/{underlying}")
    public MarketDataStatistic getStatistic(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable(value = "underlying") String ticker) {
        return marketDataService.companyStatistic(startDate, endDate, ticker);
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
