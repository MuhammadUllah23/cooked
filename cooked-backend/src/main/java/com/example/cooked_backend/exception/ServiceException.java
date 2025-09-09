package com.example.cooked_backend.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Map<String, Object> details = new HashMap<>();

    private ServiceException(ErrorCode errorCode, String customMessage) {
        // If customMessage is null, fall back to the default from ErrorCode
        super(customMessage != null ? customMessage : errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // Factory method without custom message
    public static ServiceException of(ErrorCode errorCode) {
        return new ServiceException(errorCode, null);
    }

    // Factory method with custom message
    public static ServiceException of(ErrorCode errorCode, String customMessage) {
        return new ServiceException(errorCode, customMessage);
    }

    // Add key-value pair detail
    public ServiceException addDetail(String key, Object value) {
        details.put(key, value);
        return this; // allows chaining
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
