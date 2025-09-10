package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.cooked_backend.service.DefaultAuthService;
import com.example.cooked_backend.service.DefaultUserService;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final DefaultUserService defaultUserService;
    private final DefaultAuthService defaultAuthService;

    public AuthController(DefaultUserService defaultUserService, DefaultAuthService defaultAuthService) {
        this.defaultUserService = defaultUserService;
        this.defaultAuthService = defaultAuthService;
    }

    // CREATE user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerAndLoginUser(@Valid @RequestBody UserRequest userRequest) {

        defaultUserService.createUser(userRequest);
        

        System.out.println("after user in controller");
        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());

        System.out.println("after user creation and login request " + loginRequest.toString());
        

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest);

        return ResponseEntity.ok(authResponse);
    }
    
}
