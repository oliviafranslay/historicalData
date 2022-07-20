package com.example.historical.data.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inflation")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Inflation {
    @Id
    @GeneratedValue(generator = "optimized-sequence")
    private int id;
    private Integer year;
    private Integer month;
    private Double consumerPriceIndex;
    private String country;

    public Inflation() {
    }
}
