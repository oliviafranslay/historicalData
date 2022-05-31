package com.example.historical.data.repository;

import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.Underlying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Integer> {
    List<MarketData> findAllByUnderlying(Underlying underlying);
    MarketData findById(int id);
}
