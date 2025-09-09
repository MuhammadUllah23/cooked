package com.example.cooked_backend.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Map<String, Object> details;

    public ServiceException(ErrorCode errorCode, Object... keyValuePairs) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.details = new HashMap<>();

        if (keyValuePairs != null && keyValuePairs.length % 2 == 0) {
            for (int i = 0; i < keyValuePairs.length; i += 2) {
                String key = String.valueOf(keyValuePairs[i]);
                Object value = keyValuePairs[i + 1];
                this.details.put(key, value);
            }
        }
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
