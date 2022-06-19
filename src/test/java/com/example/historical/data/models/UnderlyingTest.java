package com.example.historical.data.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

public class UnderlyingTest {

    public static Underlying.UnderlyingBuilder dummy() {
        return Underlying.builder()
                .ticker("BOA")
                .exchange("KINGDOM")
                .fullname("BOALA");
    }
}
