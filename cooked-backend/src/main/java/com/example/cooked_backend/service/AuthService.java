package com.example.cooked_backend.service;

import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse loginUser(LoginRequest loginRequest);    
}
