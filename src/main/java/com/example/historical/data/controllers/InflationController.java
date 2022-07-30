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
        log.debug("get the data");
        return inflationRepository.findAll();
    }

    @GetMapping("/inflation/{country}")
    public List<Inflation> getInflationByPeriod(
            @RequestParam("year1") Integer year1,
            @RequestParam("month1") Integer month1,
            @RequestParam("year2") Integer year2,
            @RequestParam("month2") Integer month2,
            @PathVariable(value="country") String country) {
        return null;
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
