package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cooked_backend.service.UserService;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        return ResponseEntity.ok(createdUser);
    }
}
