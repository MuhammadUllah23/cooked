package com.example.cooked_backend.service;

import org.springframework.security.core.Authentication;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cooked_backend.Security.util.JwtUtil;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.model.CustomUserDetails;
import com.example.cooked_backend.model.RefreshToken;
import com.example.cooked_backend.repository.RefreshTokenRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DefaultAuthService {

    private final AuthenticationManager authenticationManager;
    private final DefaultUserService defaultUserService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public DefaultAuthService(AuthenticationManager authenticationManager, 
                              DefaultUserService defaultUserService, 
                              JwtUtil jwtUtil,
                              RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.defaultUserService = defaultUserService;
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public AuthResponse loginUser(LoginRequest loginRequest, HttpServletResponse servletResponse) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        UserResponse userResponse = defaultUserService.getUserByEmail(customUserDetails.getUsername());

        UUID deviceId = loginRequest.getDeviceId() != null ? loginRequest.getDeviceId() : UUID.randomUUID();

        ResponseCookie cookie = createRefreshTokenCookie(customUserDetails, deviceId);
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // generate access token
        String accessToken = jwtUtil.generateAccessToken(customUserDetails);

        AuthResponse authResponse = new AuthResponse(accessToken, userResponse, deviceId);

        return authResponse;
    }

    public void logout(UUID userId, UUID deviceId, boolean global) {
        if(global) {
            refreshTokenRepository.deleteAllByUserId(userId);
        } else {
            refreshTokenRepository.deleteByUserIdAndDeviceId(userId, deviceId);
        }
    }

    private ResponseCookie createRefreshTokenCookie (CustomUserDetails customUserDetails, UUID deviceId) {
        // generate refresh token
        String refreshToken = jwtUtil.generateRefreshToken(customUserDetails);

        RefreshToken tokenEnity = new RefreshToken(customUserDetails.getId(), deviceId, refreshToken, OffsetDateTime.now(ZoneOffset.UTC).plusDays(7));
        refreshTokenRepository.upsert(tokenEnity);

        return ResponseCookie.from("refreshToken", refreshToken)
                                              .httpOnly(true)
                                              .secure(true)
                                              .path("/api/auth/refresh")
                                              .maxAge(Duration.ofDays(7))
                                              .sameSite("Strict")
                                              .build();

    }
    
}
