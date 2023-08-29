package com.db.trade.validate.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TradeValidateEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeValidateEngineApplication.class, args);
	}
	
}
