package com.example.cooked_backend.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserSubscription {
    FREE,
    PREMIUM;

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority("SUBSCRIPTION_" + this.name())
        );
    }
}
