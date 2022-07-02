package com.example.historical.data.services;

import com.example.historical.data.models.*;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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

    @Captor
    ArgumentCaptor<List<MarketData>> listMarketDataCaptor;

    @Test
    public void addMultipleDataTest() {
        MarketData dummyMarket1 = MarketDataTest.dummy().close(122d).build();
        MarketData dummyMarket2 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 10)).close(123d).build();
        MarketData dummyMarket3 = MarketDataTest.dummy().date(LocalDate.of(2022, 5, 10)).close(124d).build();
        Underlying underlying1 = UnderlyingTest.dummy().build();
        Mockito.when(underlyingRepository.findByTicker("BOA")).thenReturn(underlying1);
        MarketDataDTO dummyDTO1 = MarketDataDTOTest.dummy().build();
        MarketDataDTO dummyDTO2 = MarketDataDTOTest.dummy().date(LocalDate.of(2022, 6, 10)).close(123d).build();
        MarketDataDTO dummyDTO3 = MarketDataDTOTest.dummy().date(LocalDate.of(2022, 5, 10)).close(124d).build();
        MarketDataDTO dummyDTO4 = MarketDataDTOTest.dummy().date(LocalDate.of(2022, 5, 10)).close(125d).build();
        marketDataService.addMultipleData(List.of(dummyDTO1, dummyDTO2, dummyDTO3, dummyDTO4));
        verify(marketDataRepository).saveAll(listMarketDataCaptor.capture());
        List<MarketData> actual = listMarketDataCaptor.getValue();
        assertThat(actual).isEqualTo(List.of(dummyMarket1, dummyMarket2, dummyMarket3));
    }

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
        MarketData dummy2 = MarketDataTest.dummy().date(LocalDate.of(2022, 5, 12)).build();
        MarketData dummy3 = MarketDataTest.dummy().date(LocalDate.of(2022, 6, 12)).build();
        List<MarketData> listOfDummy = List.of(dummy1, dummy2, dummy3);
        Mockito.when(marketDataRepository.findAllByUnderlying(underlying1)).thenReturn(listOfDummy);
        List<MarketData> actual = marketDataService.findByDate(LocalDate.of(2022, 6, 12), LocalDate.now(), "BOA");
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
        assertThat(actual).isEqualTo(new MarketDataStatistic(122d, 123d, 121d, LocalDate.now(), LocalDate.now(), underlying1));
    }
}
