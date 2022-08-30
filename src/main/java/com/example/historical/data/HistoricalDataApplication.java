package com.example.historical.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Log4j2
public class HistoricalDataApplication {
    public static void main(String[] args) {
        log.info("HistoricalData initializing ...");
        ConfigurableApplicationContext context = SpringApplication.run(HistoricalDataApplication.class, args);
        log.info("HistoricalData finished initializing ...");
    }
}

