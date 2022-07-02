package com.example.historical.data.services;

import com.example.historical.data.models.Underlying;
import com.example.historical.data.models.UnderlyingTest;
import com.example.historical.data.repository.UnderlyingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UnderlyingServiceTest {

    @Mock
    private UnderlyingRepository underlyingRepository;

    @InjectMocks
    private UnderlyingService underlyingService;

    @Captor
    ArgumentCaptor<Underlying> underlyingCaptor;

    @Test
    public void updateUnderlyingTest() {
        Underlying dummy = UnderlyingTest.dummy().build();
        Mockito.when(underlyingRepository.findById(1)).thenReturn(dummy);
        underlyingService.updateUnderlying(1, dummy);
        verify(underlyingRepository).save(underlyingCaptor.capture());
        Underlying actual = underlyingCaptor.getValue();
        assertThat(actual).isEqualTo(UnderlyingTest.dummy().build());
    }

}
