package com.example.cooked_backend.service;

import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.UserResponse;

public interface AuthService {
    AuthResponse loginUser(UserRequest userRequest, UserResponse createdUser);
    AuthResponse loginUser(LoginRequest loginRequest, UserResponse createdUser);    
}
