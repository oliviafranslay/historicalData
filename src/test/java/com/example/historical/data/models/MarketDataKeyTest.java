package com.example.historical.data.models;

import java.time.LocalDate;

public class MarketDataKeyTest {

    public static MarketDataKey.MarketDataKeyBuilder dummy() {
        return MarketDataKey.builder()
                .date(LocalDate.now())
                .underlyingTicker("BOA");
    }
}
