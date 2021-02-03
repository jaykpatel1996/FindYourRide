package com.FindYourRide.Routing;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class RoutingApplication {

	@Bean
	public MongoOperations getMongoOperations() {
		return new MongoTemplate(MongoClients.create(), "FindYourRide");
	}

	public static void main(String[] args) {
		SpringApplication.run(RoutingApplication.class, args);
	}

}
