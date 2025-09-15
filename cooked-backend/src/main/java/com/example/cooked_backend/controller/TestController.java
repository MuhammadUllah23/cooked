package com.example.cooked_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

@RestController
public class TestController {
        // Endpoint accessible to any authenticated user
    @GetMapping("/role")
    public String userEndpoint(Authentication authentication) {
        return "Hello " + authentication.getName() + ", you are authenticated!";
    }

    // Endpoint accessible only to USER role + PREMIUM subscription
    @GetMapping("/premium")
    public String premiumEndpoint(Authentication authentication) {
        return "Hello " + authentication.getName() + ", you have PREMIUM access!";
    }
}
