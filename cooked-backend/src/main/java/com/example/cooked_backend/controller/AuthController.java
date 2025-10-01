package com.example.cooked_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.example.cooked_backend.service.DefaultAuthService;
import com.example.cooked_backend.service.DefaultUserService;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.request.LogoutRequest;
import com.example.cooked_backend.dto.request.RefreshRequest;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.RefreshResponse;
import com.example.cooked_backend.model.CustomUserDetails;

@RestController
@RequestMapping("api/auth")
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
                                                             HttpServletResponse response) {

        defaultUserService.createUser(userRequest);

        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword(), null);        

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest, response);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest,
                                                  HttpServletResponse response) {

        AuthResponse authResponse = defaultAuthService.loginUser(loginRequest, response);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid LogoutRequest logoutRequest
    ) {

        defaultAuthService.logout(customUserDetails.getId(), logoutRequest.getDeviceId(), logoutRequest.getGlobal());

        return ResponseEntity.noContent().build(); 
    }


    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken,
                                                    @RequestBody @Valid RefreshRequest refreshRequest) {
        RefreshResponse refreshResponse = defaultAuthService.refresh(refreshToken, refreshRequest.deviceId());

        return ResponseEntity.ok(refreshResponse);
    }
    
}
