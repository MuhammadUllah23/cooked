package com.example.cooked_backend.dto.response;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UserResponse user;
    private UUID deviceId;
    
    public AuthResponse(String token, UserResponse user, UUID deviceId) {
        this.token = token;
        this.user = user;
        this.deviceId = deviceId;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public UUID getDeviceId() { 
        return deviceId; 
    }

    public void setDeviceId(UUID deviceId) { 
        this.deviceId = deviceId; 
    }
}
