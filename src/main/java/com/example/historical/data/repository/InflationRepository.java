package com.example.historical.data.repository;

import com.example.historical.data.models.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InflationRepository extends JpaRepository<Inflation, Integer> {
}
