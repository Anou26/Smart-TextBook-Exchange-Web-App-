package edu.syr.textbooks;

/*
* Team members:
* 1. Anoushka Mergoju
* 2. Pavan Pandya
* 3. Parag Kaldate
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OldBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(OldBooksApplication.class, args);
	}

}
