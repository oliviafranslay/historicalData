package com.example.historical.data.services;
import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.MarketDataDTO;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class MarketDataService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public MarketData toEntity(MarketDataDTO marketDataDTO) {
        Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.getUnderlyingTicker());
        MarketData marketData = new MarketData(marketDataDTO.getId(), marketDataDTO.getDate(), marketDataDTO.getOpen(), marketDataDTO.getHigh(), marketDataDTO.getLow(), marketDataDTO.getClose(), marketDataDTO.getVolume(), underlying);
        marketDataRepository.save(marketData);
        return marketData;
    }

    public List<MarketData> addMultipleData(List<MarketDataDTO> marketDataDTO) {
        List<MarketData> list = new ArrayList<>();


        for (int i = 0; i < marketDataDTO.size(); i++) {

            Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.get(i).getUnderlyingTicker());


            MarketData marketData = new MarketData(marketDataDTO.get(i).getId(), marketDataDTO.get(i).getDate(), marketDataDTO.get(i).getOpen(), marketDataDTO.get(i).getHigh(), marketDataDTO.get(i).getLow(), marketDataDTO.get(i).getClose(), marketDataDTO.get(i).getVolume(), underlying);
            marketDataRepository.save(marketData);
            list.add(marketData);
        }
        return list;
    }

    public MarketData editMarketData(int id, MarketDataDTO marketDataDTO){
        Underlying underlying = underlyingRepository.findByTicker(marketDataDTO.getUnderlyingTicker());
        MarketData marketData = marketDataRepository.findById(id);
        marketData.setDate(marketDataDTO.getDate());
        marketData.setOpen(marketDataDTO.getOpen());
        marketData.setHigh(marketData.getHigh());
        marketData.setLow(marketDataDTO.getLow());
        marketData.setClose(marketDataDTO.getClose());
        marketData.setVolume(marketDataDTO.getVolume());
        marketData.setUnderlying(underlying);
        return marketDataRepository.save(marketData);
    }
}
