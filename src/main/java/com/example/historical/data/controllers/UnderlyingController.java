package com.example.historical.data.controllers;

import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.UnderlyingRepository;
import com.example.historical.data.services.UnderlyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UnderlyingController {
    @Autowired
    private UnderlyingRepository underlyingRepository;

    @Autowired
    private UnderlyingService underlyingService;

    @GetMapping("/underlying")
    public List<Underlying> getAllUnderlying()
    { return underlyingRepository.findAll(); }

    @GetMapping("/underlying/{ticker}")
    public Underlying getUnderlyingByTicker(
            @PathVariable(value="ticker") String ticker)
    {
        return underlyingRepository.findByTicker(ticker);
    }

    @PostMapping("/underlying")
    @ResponseStatus(HttpStatus.CREATED)
    public Underlying addUnderlying(
            @RequestBody Underlying underlying)
    {
        return underlyingRepository.save(underlying);
    }

    @PutMapping("/underlying/{id}")
    public Underlying updateUnderlying(@PathVariable(value = "id") int id, @RequestBody Underlying underlying){
        return underlyingService.updateUnderlying(id, underlying);
    }

    @DeleteMapping("/underlying/{id}")
    public void deleteUnderlying(
            @PathVariable(value="id") int id)
    {
        underlyingRepository.deleteById(id);
    }

}
