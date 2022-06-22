package com.example.historical.data.services;

import com.example.historical.data.models.*;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MarketDataCorrelationServiceTest {

    @Mock
    private UnderlyingRepository underlyingRepository;

    @Mock
    private MarketDataRepository marketDataRepository;

    @InjectMocks
    private MarketDataCorrelationService marketDataCorrelationService;

    @Test
    public void correlationTest() {
        Underlying underlying1 = UnderlyingTest.dummy().build();
        Underlying underlying2 = UnderlyingTest.dummy().ticker("KOA").fullname("Koala").build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying1);
        Mockito.when(underlyingRepository.findByTicker("KOA")).thenReturn(underlying2);
        MarketData underlying1Dummy1 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 10)).close(1692d).build();
        MarketData underlying1Dummy2 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 11)).close(1978d).build();
        MarketData underlying1Dummy3 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 12)).close(1884d).build();
        MarketData underlying1Dummy4 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 13)).close(2151d).build();
        MarketData underlying1Dummy5 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 14)).close(2519d).build();
        MarketData underlying2Dummy1 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 10)).close(68d).build();
        MarketData underlying2Dummy2 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 11)).close(102d).build();
        MarketData underlying2Dummy3 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 12)).close(110d).build();
        MarketData underlying2Dummy4 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 13)).close(112d).build();
        MarketData underlying2Dummy5 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 14)).close(184d).build();
        List<MarketData> listOfDummy1 = List.of(underlying1Dummy1, underlying1Dummy2, underlying1Dummy3, underlying1Dummy4, underlying1Dummy5);
        List<MarketData> listOfDummy2 = List.of(underlying2Dummy1, underlying2Dummy2, underlying2Dummy3, underlying2Dummy4, underlying2Dummy5);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying1)).thenReturn(listOfDummy1);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying2)).thenReturn(listOfDummy2);
        MarketDataCorrelation actual = marketDataCorrelationService.correlation(LocalDate.of(2022, 6, 10), LocalDate.of(2022, 6, 14), "BOA", "KOA");
        assertThat(actual).isEqualTo(new MarketDataCorrelation(underlying1, underlying2, 146.92, 110.09, 952.25, 10.49, 30.86,0.45));
    }
}
