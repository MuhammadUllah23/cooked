package com.example.cooked_backend.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class LogoutRequest {

    @NotNull(message = "Device Id is required for logout")
    private UUID deviceId;

    private boolean global = false;

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public boolean getGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }
    
}
