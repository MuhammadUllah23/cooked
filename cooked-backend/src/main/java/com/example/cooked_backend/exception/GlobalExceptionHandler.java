package com.example.cooked_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(ServiceException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ex.getErrorCode().name()); // e.g. USER_ALREADY_EXISTS
        response.put("message", ex.getMessage());
        response.put("status", ex.getErrorCode().getStatus().value());

        if (ex.getDetails() != null && !ex.getDetails().isEmpty()) {
            response.put("details", ex.getDetails());
        }

        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(response);
    }
}
