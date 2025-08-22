package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cooked_backend.service.DefaultUserService;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class AuthController {
	
    private final DefaultUserService defaultUserService;

    public AuthController(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    // CREATE user
    @PostMapping
    public ResponseEntity<UserResponse> registerAndLoginUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse createdUser = defaultUserService.createUser(userRequest);
        return ResponseEntity.ok(createdUser);
    }
}
