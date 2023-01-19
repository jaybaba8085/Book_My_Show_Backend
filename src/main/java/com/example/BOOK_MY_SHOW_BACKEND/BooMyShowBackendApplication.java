package com.example.BOOK_MY_SHOW_BACKEND;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BooMyShowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooMyShowBackendApplication.class, args);
	}

}
