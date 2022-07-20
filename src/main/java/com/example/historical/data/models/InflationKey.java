package com.example.historical.data.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class InflationKey {
    private Integer year;
    private Integer month;

    public InflationKey() {
    }
}