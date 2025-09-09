package com.example.cooked_backend.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_ALREADY_EXISTS("User already exists with the following email", HttpStatus.CONFLICT),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }
    public HttpStatus getStatus() { return status; }
}
