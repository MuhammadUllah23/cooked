package com.example.cooked_backend.Security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.cooked_backend.model.CustomUserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.accessExpiration}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshExpiration}")
    private long refreshTokenExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate JWT access token for authentication
     * @param userId
     * @param email
     * 
     * @return 
     */
    public String generateAccessToken(CustomUserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getId().toString())
                .claim("email", userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

        /**
     * Generate JWT refresh token for authentication
     * @param userId
     * @param email
     * 
     * @return 
     */
    public String generateRefreshToken(CustomUserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getId().toString())
                .claim("email", userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts userId from token
     * 
     * @param token
     * @return
     */
    public UUID extractUserId(String token) {
        return UUID.fromString(extractClaims(token).getSubject());
    }

    /**
     * Extracts email from token
     * 
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    /**
     * Validates token
     * 
     * @param token
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Helper method to parse claims
     * @param token
     * @return
     */
    @SuppressWarnings("deprecation")
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
