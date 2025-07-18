package com.example.cooked_backend.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvTestConfig {
     @PostConstruct
    public void loadDotenv() {
        Dotenv dotenv = Dotenv.configure()
                              .ignoreIfMalformed()
                              .ignoreIfMissing()
                              .load();
        // Optionally, print to confirm
        System.out.println("Loaded DB_URL: " + dotenv.get("DB_URL"));
    }
    
}
