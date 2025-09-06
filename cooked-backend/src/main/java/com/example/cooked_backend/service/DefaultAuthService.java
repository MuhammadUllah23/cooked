package com.example.cooked_backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final DefaultUserService defaultUserService;

    public DefaultAuthService(AuthenticationManager authenticationManager, DefaultUserService defaultUserService) {
        this.authenticationManager = authenticationManager;
        this.defaultUserService = defaultUserService;
    }

    
    
}
