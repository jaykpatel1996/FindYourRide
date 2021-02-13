package com.findyourride.AddUser;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class AddUserApplication {

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "FindYourRide");
	}

	@Bean
	public MongoOperations getMongoOperations() throws Exception {
		return new MongoTemplate(MongoClients.create(), "FindYourRide");
	}

	public static void main(String[] args) {
		SpringApplication.run(AddUserApplication.class, args);
	}

}
