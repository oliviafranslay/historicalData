package com.example.historical.data.services;

import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.UnderlyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnderlyingService {

    @Autowired
    private UnderlyingRepository underlyingRepository;

    public Underlying updateUnderlying(int id, Underlying underlying){
        Underlying udly = underlyingRepository.findById(id);
        udly.setTicker(underlying.getTicker());
        udly.setFullname(underlying.getFullname());
        udly.setExchange(underlying.getExchange());
        return underlyingRepository.save(udly);
    }
}
