package com.example.cooked_backend.repository;

import com.example.cooked_backend.model.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);

    void deleteAllByUserId(UUID userId);
}
