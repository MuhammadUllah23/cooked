package com.example.cooked_backend.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.Collection;

public enum UserRole {
    USER;

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + this.name())
        );
    }

}
