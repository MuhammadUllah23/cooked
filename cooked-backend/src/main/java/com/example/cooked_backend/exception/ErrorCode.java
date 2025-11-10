package com.example.cooked_backend.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_ALREADY_EXISTS("User already exists with the following email", HttpStatus.CONFLICT),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    INVALID_REFRESH_TOKEN("Invalid refresh token", HttpStatus.UNAUTHORIZED),
    EXPIRED_REFRESH_TOKEN("Refresh Token is expired", HttpStatus.UNAUTHORIZED),
    MISSING_REFRESH_TOKEN("Refresh Token is missing", HttpStatus.UNAUTHORIZED),
    INVALID_EMAIL_OR_PASSWORD("Invalid email or password", HttpStatus.UNAUTHORIZED),
    STORE_ALREADY_EXISTS("You already have a store with that name", HttpStatus.CONFLICT),
    STORE_NOT_FOUND("Store does not exist", HttpStatus.NOT_FOUND),
    STORE_LIMIT_REACHED("You have reached the store limit creation", HttpStatus.FORBIDDEN),
    
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }
    public HttpStatus getStatus() { return status; }
}
