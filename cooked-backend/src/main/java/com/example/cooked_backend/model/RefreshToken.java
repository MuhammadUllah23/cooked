package com.example.cooked_backend.model;


import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(nullable = false, updatable = false, unique = true)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now(ZoneOffset.UTC);

    // Constructors
    public RefreshToken() {}

    public RefreshToken(UUID userId, String token, OffsetDateTime expiresAt) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    // Getters & Setters
    public UUID getId() { 
        return id; 
    }

    public void setId(UUID id) { 
        this.id = id; 
    }

    public UUID getUserId() { 
        return userId; 
    }

    public void setUserId(UUID userId) { 
        this.userId = userId; 
    }

    public String getToken() { 
        return token; 
    }

    public void setToken(String token) { 
        this.token = token; 
    }

    public OffsetDateTime getExpiresAt() { 
        return expiresAt; 
    }

    public void setExpiresAt(OffsetDateTime expiresAt) { 
        this.expiresAt = expiresAt; 
    }

    public OffsetDateTime getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(OffsetDateTime createdAt) { 
        this.createdAt = createdAt; 
    }
    
}
