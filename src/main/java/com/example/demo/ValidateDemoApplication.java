package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.repo.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ValidateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidateDemoApplication.class, args);
	}

	@Component
	static class DatabaseInitializer implements ApplicationRunner {
		private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

		@Autowired
		private AuthorRepository authorRepository;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			LOGGER.info("initializing database");
			authorRepository.save(new Author("author@example.com", "Author Demo"));
		}
	}
}
