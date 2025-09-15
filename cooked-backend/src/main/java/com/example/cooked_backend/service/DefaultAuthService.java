package com.example.cooked_backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.model.CustomUserDetails;
import com.example.cooked_backend.util.JwtUtil;

@Service
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final DefaultUserService defaultUserService;
    private final JwtUtil jwtUtil;
    

    public DefaultAuthService(AuthenticationManager authenticationManager, DefaultUserService defaultUserService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.defaultUserService = defaultUserService;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse loginUser(LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        UserResponse userResponse = defaultUserService.getUserByEmail(customUserDetails.getUsername());

        // generate JWT token
        String jwtToken = jwtUtil.generateToken(customUserDetails);

        AuthResponse authResponse = new AuthResponse(jwtToken, userResponse);

        return authResponse;
    }
    
}
