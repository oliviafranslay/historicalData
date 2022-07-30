package com.example.historical.data.services;

import com.example.historical.data.models.Inflation;
import com.example.historical.data.models.InflationKey;
import com.example.historical.data.repository.InflationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InflationService {

    @Autowired
    private InflationRepository inflationRepository;

    public List<Inflation> addInflationData(List<Inflation> inflations) {
        List<Inflation> list = new ArrayList<>();

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
    }

//    public List<Inflation> getInflationByPeriod(Integer year1, Integer month1, Integer year2, Integer month2, String country) {
//        List<Inflation> byPeriod = inflationRepository.findByCountry(country)
//                .stream()
//                .filter(i -> i.getYear().compareTo(year1) >= 0 && i.getMonth().compareTo(month1) >=0 && i.getYear().compareTo(year2) <=0 && i.getMonth().compareTo(month2) <= 0)
//                .sorted(Comparator.comparing(Inflation::getMonth))
//                .collect(Collectors.toList());
//    }
}
