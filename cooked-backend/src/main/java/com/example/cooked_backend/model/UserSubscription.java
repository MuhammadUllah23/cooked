package com.example.cooked_backend.model;

public enum UserSubscription {
    FREE,
    PREMIUM;

    public String asAuthority() {
        return "SUBSCRIPTION_" + this.name();
    }
}
