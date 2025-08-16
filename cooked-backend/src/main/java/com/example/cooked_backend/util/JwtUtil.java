package com.example.cooked_backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate JWT token for authentication
     * @param userId
     * @param email
     * 
     * @return 
     */
    public String generateToken(UUID userId, String email) {

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
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
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

}
