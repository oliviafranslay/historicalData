package com.example.historical.data.controllers;
import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.MarketDataDTO;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import com.example.historical.data.services.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public List<MarketData> getAllMarketData(){
        return marketDataRepository.findAll();
    }

    @GetMapping("/marketdata/{underlying}")
    public List<MarketData> getMarketDataByTicker(
            @PathVariable(value="underlying") String ticker){
        // 1. get udly
        Underlying underlying = underlyingRepository.findByTicker(ticker);
        // 2. get mkt data
        return marketDataRepository.findAllByUnderlying(underlying);
    }

    @PostMapping("/marketdata")
    public MarketData addMarketData(@RequestBody MarketDataDTO marketDataDTO){
        return marketDataService.toEntity(marketDataDTO);
    }

    @PostMapping("/multipleMarketData")
    public List<MarketData> addMultipleMarketData(@RequestBody List<MarketDataDTO> marketDataDTO){
        return marketDataService.addMultipleData(marketDataDTO);
    }

    @PutMapping("/marketdata/{id}")
    public MarketData updateMarketData(@PathVariable(value="id") int id, @RequestBody MarketDataDTO marketDataDTO){
        return marketDataService.editMarketData(id, marketDataDTO);
    }


    @DeleteMapping("/marketdata/{id}")
    public void deleteMarketData(
            @PathVariable(value="id") int id){
        marketDataRepository.deleteById(id);
    }

}
