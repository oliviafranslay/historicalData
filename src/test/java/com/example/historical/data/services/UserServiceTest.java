package com.example.historical.data.services;

import com.example.historical.data.models.User;
import com.example.historical.data.models.UserTest;
import com.example.historical.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void updateUser() {
        User dummy = UserTest.dummy().build();
        Mockito.when(userRepository.findById(1)).thenReturn(dummy);
        userService.updateUser(1, dummy);
        verify(userRepository).save(userCaptor.capture());
        User actual = userCaptor.getValue();
        assertThat(actual).isEqualTo(UserTest.dummy().build());
    }
}
