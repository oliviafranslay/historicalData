package com.example.historical.data.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.ExcludeDefaultListeners;
import java.time.LocalDate;

public class MarketDataTest {

    public static MarketData.MarketDataBuilder dummy() {
        return MarketData.builder()
                .date(LocalDate.now())
                .open(123d)
                .high(125d)
                .low(120d)
                .close(121d)
                .volume(1000d)
                .underlying(UnderlyingTest.dummy()
                        .ticker("BOA")
                        .fullname("BOALA")
                        .build());
    }
}
