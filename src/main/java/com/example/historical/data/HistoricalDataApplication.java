package com.example.historical.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HistoricalDataApplication {

    private static Logger logger = LogManager.getLogger(HistoricalDataApplication.class);

    public static void main(String[] args) {

        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

        ConfigurableApplicationContext context = SpringApplication.run(HistoricalDataApplication.class, args);

    }
}

