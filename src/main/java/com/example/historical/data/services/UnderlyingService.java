package com.example.historical.data.services;

import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.UnderlyingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UnderlyingService {

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public Underlying updateUnderlying(int id, Underlying underlying) {
        Underlying udly = underlyingRepository.findById(id);
        log.info("Get {}", udly);
        udly.setTicker(underlying.getTicker());
        udly.setFullname(underlying.getFullname());
        udly.setExchange(underlying.getExchange());
        log.info("Edit underlying {} data", underlying);
        return underlyingRepository.save(udly);
    }
}
