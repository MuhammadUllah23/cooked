package com.example.cooked_backend.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;

    private UUID deviceId;

    public LoginRequest(String email, String password, UUID deviceId) {
        this.email = email;
        this.password = password;
        this.deviceId = deviceId;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }
}
