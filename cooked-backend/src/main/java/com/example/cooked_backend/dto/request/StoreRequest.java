package com.example.cooked_backend.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StoreRequest {

    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Password must be less than 255 characters")
    private String name;

    // Getters and setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String name) {
        this.name = name;
    }
    
}
