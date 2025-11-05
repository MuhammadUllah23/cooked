package com.example.cooked_backend.service;

import org.springframework.security.core.Authentication;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cooked_backend.Security.util.JwtUtil;
import com.example.cooked_backend.dto.request.LoginRequest;
import com.example.cooked_backend.dto.response.AuthResponse;
import com.example.cooked_backend.dto.response.RefreshResponse;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.exception.ErrorCode;
import com.example.cooked_backend.exception.ServiceException;
import com.example.cooked_backend.model.CustomUserDetails;
import com.example.cooked_backend.model.RefreshToken;
import com.example.cooked_backend.model.User;
import com.example.cooked_backend.repository.RefreshTokenRepository;
import com.example.cooked_backend.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DefaultAuthService {

    private final AuthenticationManager authenticationManager;
    private final DefaultUserService defaultUserService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public DefaultAuthService(AuthenticationManager authenticationManager, 
                              DefaultUserService defaultUserService, 
                              JwtUtil jwtUtil,
                              RefreshTokenRepository refreshTokenRepository,
                              UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.defaultUserService = defaultUserService;
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AuthResponse loginUser(LoginRequest loginRequest, HttpServletResponse servletResponse) {
        try {
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
        } catch (BadCredentialsException ex) {
            throw ServiceException.of(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }
    }

    @Transactional
    public void logout(UUID userId, UUID deviceId, boolean global) {
        if(global) {
            refreshTokenRepository.deleteAllByUserId(userId);
        } else {
            refreshTokenRepository.deleteByUserIdAndDeviceId(userId, deviceId);
        }
    }

    public RefreshResponse refresh(String refreshToken, UUID deviceId) {
        validateRefreshToken(refreshToken);

        RefreshToken refreshTokenEntity = retrieveRefreshTokenEntity(refreshToken, deviceId);

        User user = userRepository.findById(refreshTokenEntity.getUserId())
                                    .orElseThrow(() -> ServiceException.of(ErrorCode.USER_NOT_FOUND)
                                    .addDetail("refresh token", refreshToken));

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        String accessToken = jwtUtil.generateAccessToken(customUserDetails);

        return new RefreshResponse(accessToken);
    }

    private void validateRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw ServiceException.of(ErrorCode.MISSING_REFRESH_TOKEN);
        }
    }

    private RefreshToken retrieveRefreshTokenEntity(String refreshToken, UUID deviceId){
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByTokenAndDeviceId(refreshToken, deviceId)
                                        .orElseThrow(() -> ServiceException.of(ErrorCode.INVALID_REFRESH_TOKEN)
                                                                .addDetail("refresh token", refreshToken));

        if(refreshTokenEntity.getExpiresAt().isBefore(OffsetDateTime.now(ZoneOffset.UTC))) {
            throw ServiceException.of(ErrorCode.EXPIRED_REFRESH_TOKEN).addDetail("refresh token", refreshToken);
        }

        return refreshTokenEntity;
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
