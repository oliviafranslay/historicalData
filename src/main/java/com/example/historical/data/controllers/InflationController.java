package com.example.historical.data.controllers;

import com.example.historical.data.models.Inflation;
import com.example.historical.data.repository.InflationRepository;
import com.example.historical.data.services.InflationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
public class InflationController {

    @Autowired
    private InflationService inflationService;

    @Autowired
    private InflationRepository inflationRepository;


    @GetMapping("/inflation")
    public List<Inflation> getAllInflationData() {
        log.info("Get all inflation data");
        return inflationRepository.findAll();
    }


    @PostMapping("/inflation")
    public List<Inflation> addInflationData(@Valid @RequestBody List<Inflation> inflations) {
        log.info("Add inflation data");
        return inflationService.addInflationData(inflations);
    }

    @DeleteMapping("/inflation/{id}")
    public void deleteInflation(
            @PathVariable(value = "id") int id) {
        log.info("Delete inflation data");
        inflationRepository.deleteById(id);
    }
}
