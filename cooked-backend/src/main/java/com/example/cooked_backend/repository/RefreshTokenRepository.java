package com.example.cooked_backend.repository;

import com.example.cooked_backend.model.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByTokenAndDeviceId(String token, UUID deviceId);

    void deleteAllByUserId(UUID userId);

    void deleteByUserIdAndDeviceId(UUID userId, UUID deviceId);

    @Modifying
    @Query(value = """
        INSERT INTO refresh_tokens (user_id, device_id, token, expires_at, created_at)
        VALUES (:#{#rt.userId}, :#{#rt.deviceId}, :#{#rt.token}, :#{#rt.expiresAt}, :#{#rt.createdAt})
        ON CONFLICT (user_id, device_id)
        DO UPDATE
        SET token = EXCLUDED.token,
            expires_at = EXCLUDED.expires_at,
            created_at = EXCLUDED.created_at
    """, nativeQuery = true)
    void upsert(@Param("rt") RefreshToken refreshToken);
}
