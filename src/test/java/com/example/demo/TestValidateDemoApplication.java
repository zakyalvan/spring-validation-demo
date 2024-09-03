package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestValidateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(ValidateDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
