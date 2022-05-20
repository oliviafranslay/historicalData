package com.example.historical.data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HistoricalDataApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(HistoricalDataApplication.class, args);
	}

}
