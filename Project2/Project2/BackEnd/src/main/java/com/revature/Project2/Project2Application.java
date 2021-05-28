package com.revature.Project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
public class Project2Application {

	@Autowired
	private MongoTemplate mongo;

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) throws Exception {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			//Tells me that the Mongo has mad a connection to the test db
			System.out.println("---------->" + mongo.getCollectionNames());//returns test collection
		};
	}

}
