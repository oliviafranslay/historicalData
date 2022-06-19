package com.example.historical.data.services;

import com.example.historical.data.models.*;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MarketDataServiceTest {

    @Mock
    private MarketDataRepository marketDataRepository;

    @Mock
    private UnderlyingRepository underlyingRepository;

    @InjectMocks
    private MarketDataService marketDataService;

    @Captor
    ArgumentCaptor<MarketData> marketDataCaptor;

    @Test
    public void editMarketDataTest() {
        Underlying underlying1 = UnderlyingTest.dummy().build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying1);
        MarketData dummy = MarketDataTest.dummy().build();
        Mockito.when(marketDataRepository.findById(1)).thenReturn(dummy);
        MarketDataDTO dummyDTO = MarketDataDTOTest.dummy().build();
        marketDataService.editMarketData(1, dummyDTO);
        verify(marketDataRepository).save(marketDataCaptor.capture());
        MarketData actual = marketDataCaptor.getValue();
        assertThat(actual).isEqualTo(MarketDataTest.dummy().close(122d).build());
    }

    @Test
    public void findByDateTest() {
        Underlying underlying1 = UnderlyingTest.dummy().build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying1);
        MarketData dummy1 = MarketDataTest.dummy().build();
        MarketData dummy2 = MarketDataTest.dummy().date(LocalDate.of(2022,5,12)).build();
        MarketData dummy3 = MarketDataTest.dummy().date(LocalDate.of(2022,6,12)).build();
        List<MarketData> listOfDummy = List.of(dummy1, dummy2, dummy3);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying1)).thenReturn(listOfDummy);
        List<MarketData> actual = marketDataService.findByDate(LocalDate.of(2022,6,12), LocalDate.now(), "BOA");
        assertThat(actual).isEqualTo(List.of(dummy3, dummy1));
    }

    @Test
    public void companyStatisticTest() {
        Underlying underlying1 = UnderlyingTest.dummy().build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying1);
        MarketData dummy1 = MarketDataTest.dummy().build();
        MarketData dummy2 = MarketDataTest.dummy().close(122d).build();
        MarketData dummy3 = MarketDataTest.dummy().close(123d).build();
        List<MarketData> listOfDummy = List.of(dummy1, dummy2, dummy3);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying1)).thenReturn(listOfDummy);
        MarketDataStatistic actual = marketDataService.companyStatistic(LocalDate.now(), LocalDate.now(), "BOA");
        assertThat(actual).isEqualTo(new MarketDataStatistic(122d,123d,121d, LocalDate.now(), LocalDate.now(), underlying1));
    }
}
