package com.example.cooked_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RefreshRequest(
    @NotNull(message = "Device ID must not be null")    
    UUID deviceId
) {}
