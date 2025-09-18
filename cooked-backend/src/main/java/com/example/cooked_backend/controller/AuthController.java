package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.UUID;

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
    public ResponseEntity<AuthResponse> registerAndLoginUser(@Valid @RequestBody UserRequest userRequest,
                                                             @RequestParam(required = false) UUID deviceId, 
                                                             HttpServletResponse response) {

        defaultUserService.createUser(userRequest);

        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());        

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest, deviceId, response);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest,
                                                  @RequestParam(required = false) UUID deviceId,
                                                  HttpServletResponse response) {

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest, deviceId, response);

        return ResponseEntity.ok(authResponse);
    }
    
}
