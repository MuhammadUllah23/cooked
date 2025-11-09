package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.example.cooked_backend.service.DefaultAuthService;
import com.example.cooked_backend.service.DefaultStoreService;
import com.example.cooked_backend.service.DefaultUserService;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.model.CustomUserDetails;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	
    private final DefaultUserService defaultUserService;
    private final DefaultStoreService defaultStoreService;

    public StoreController(DefaultUserService defaultUserService, DefaultStoreService defaultStoreService) {
        this.defaultUserService = defaultUserService;
        this.defaultStoreService = defaultStoreService;
    }

    // CREATE user
    @PostMapping("/create")
    public ResponseEntity<StoreResponse> createStore(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    @Valid @RequestBody StoreRequest storeRequest,
                                                    HttpServletResponse response) {


        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/create")
    public ResponseEntity<StoreResponse> getAllStoresByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    HttpServletResponse response) {


        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/create")
    public ResponseEntity<StoreResponse> getStoreByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    @Valid @RequestBody StoreRequest storeRequest,
                                                    HttpServletResponse response) {


        return ResponseEntity.ok(authResponse);
    }

}
