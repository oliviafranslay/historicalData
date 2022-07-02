package com.example.historical.data.services;

import com.example.historical.data.models.MarketData;
import com.example.historical.data.models.MarketDataTest;
import com.example.historical.data.models.Underlying;
import com.example.historical.data.models.UnderlyingTest;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MarketDataMovingAverageServiceTest {

    @Mock
    private MarketDataRepository marketDataRepository;

    @Mock
    private UnderlyingRepository underlyingRepository;

    @InjectMocks
    private MarketDataMovingAverageService marketDataMovingAverageService;

    @Test
    public void fiveDaysAverageTest() {
        Underlying underlying = UnderlyingTest.dummy().build();
        MarketData dummy = MarketDataTest.dummy().build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying);
        MarketData dummy1 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 10)).build();
        MarketData dummy2 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 11)).build();
        MarketData dummy3 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 12)).build();
        MarketData dummy4 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 13)).build();
        MarketData dummy5 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 14)).build();
        MarketData dummy6 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 15)).build();
        MarketData dummy7 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 16)).build();
        MarketData dummy8 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 17)).build();
        MarketData dummy9 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 18)).build();
        MarketData dummy10 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 19)).build();
        List<MarketData> listOfDummy = List.of(dummy1, dummy2, dummy3, dummy4, dummy5, dummy6, dummy7, dummy8, dummy9, dummy10);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying)).thenReturn(listOfDummy);
        List<Double> actual = marketDataMovingAverageService.fiveDaysAverage(LocalDate.of(2022,6,10), LocalDate.of(2022,6,19), "BOA", 5);
        assertThat(actual).isEqualTo(List.of(121d,121d,121d,121d,121d,121d));
    }
}

