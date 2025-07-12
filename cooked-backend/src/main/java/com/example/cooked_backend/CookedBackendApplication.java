package com.example.cooked_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CookedBackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		SpringApplication.run(CookedBackendApplication.class, args);
	}

}
