package com.example.historical.data.controllers;

import com.example.historical.data.models.Inflation;
import com.example.historical.data.repository.InflationRepository;
import com.example.historical.data.services.InflationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InflationController {

    @Autowired
    private InflationService inflationService;

    @Autowired
    private InflationRepository inflationRepository;

    @GetMapping("/inflation")
    public List<Inflation> getAllInflationData() {
        return inflationRepository.findAll();
    }

    @PostMapping("/inflation")
    public List<Inflation> addInflationData(@Valid @RequestBody List<Inflation> inflations) {
        return inflationService.addInflationData(inflations);
    }

    @DeleteMapping("/inflation/{id}")
    public void deleteInflation(
            @PathVariable(value = "id") int id) {
        inflationRepository.deleteById(id);
    }
}
