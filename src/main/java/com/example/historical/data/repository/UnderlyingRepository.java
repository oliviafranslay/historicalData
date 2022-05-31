package com.example.historical.data.repository;

import com.example.historical.data.models.Underlying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderlyingRepository extends JpaRepository<Underlying, Integer> {
    Underlying findByTicker(String ticker);

    Underlying findById(int id);
}
