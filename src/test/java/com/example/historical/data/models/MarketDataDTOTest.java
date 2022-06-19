package com.example.historical.data.models;

import java.time.LocalDate;

public class MarketDataDTOTest {
    public static MarketDataDTO.MarketDataDTOBuilder dummy() {
        return MarketDataDTO.builder()
                .id(1)
                .date(LocalDate.now())
                .open(123d)
                .high(125d)
                .low(120d)
                .close(122d)
                .volume(1000d)
                .underlyingTicker("BOA");
    }
}
