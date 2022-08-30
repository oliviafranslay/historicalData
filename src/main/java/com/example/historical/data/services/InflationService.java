package com.example.historical.data.services;

import com.example.historical.data.models.Inflation;
import com.example.historical.data.models.InflationKey;
import com.example.historical.data.repository.InflationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InflationService {

    @Autowired
    private InflationRepository inflationRepository;

    public List<Inflation> addInflationData(List<Inflation> inflations) {
        List<Inflation> list = new ArrayList<>();

        try{
            Map<InflationKey, Inflation> existingInflationMap = inflationRepository.findAll()
                    .stream()
                    .collect(Collectors.toMap(i -> new InflationKey(i.getYear(), i.getMonth()), i -> i));

            for (int i = 0; i < inflations.size(); i++) {
                Inflation inflation = new Inflation(inflations.get(i).getId(), inflations.get(i).getYear(), inflations.get(i).getMonth(), inflations.get(i).getConsumerPriceIndex(), inflations.get(i).getCountry());
                InflationKey inflationKey = new InflationKey(inflation.getYear(), inflation.getMonth());
                if (!existingInflationMap.containsKey(inflationKey)) {
                    list.add(inflation);
                    existingInflationMap.put(inflationKey, inflation);
                }
            }
            inflationRepository.saveAll(list);
            return list;
        } catch (Exception exception) {
            log.error("Error: {} when adding multiple market data", exception.getMessage());
            return Collections.emptyList();
        }
    }
}
