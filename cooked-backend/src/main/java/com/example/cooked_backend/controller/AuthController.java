package com.example.cooked_backend.controller;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.cooked_backend.service.DefaultAuthService;
import com.example.cooked_backend.service.DefaultUserService;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.UserResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final DefaultUserService defaultUserService;
    private final AuthenticationManager authenticationManager;
    private final DefaultAuthService defaultAuthService;

    public AuthController(DefaultUserService defaultUserService, AuthenticationManager authenticationManager, DefaultAuthService defaultAuthService) {
        this.defaultUserService = defaultUserService;
        this.authenticationManager = authenticationManager;
        this.defaultAuthService = defaultAuthService;
    }

    // CREATE user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerAndLoginUser(@Valid @RequestBody UserRequest userRequest) {
        defaultUserService.createUser(userRequest);

        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest);

        return ResponseEntity.ok(authResponse);
    }
    
}
