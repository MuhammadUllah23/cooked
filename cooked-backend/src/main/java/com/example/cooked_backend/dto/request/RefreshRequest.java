package com.example.cooked_backend.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefreshRequest {

    @NotNull(message = "Device Id is required to generate acess token")
    private UUID deviceId;

    @NotNull(message = "Refresh Token is required to generate acess token")
    @NotBlank(message = "Refresh Token is required to generate acess token")
    private String refreshToken;

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
