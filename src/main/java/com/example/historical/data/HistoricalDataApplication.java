package com.example.historical.data;

import com.example.historical.data.models.Underlying;
import com.example.historical.data.repository.MarketDataRepository;
import com.example.historical.data.repository.UnderlyingRepository;
import com.example.historical.data.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HistoricalDataApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(HistoricalDataApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner mapping(MarketDataRepository marketDataRepository,
//									 UnderlyingRepository underlyingRepository,
//									 UserRepository userRepository) {
//		return args -> {
//
//			Underlying underlying = new Underlying();
//			underlyingRepository.save(underlying);
//		};
//	}
}
