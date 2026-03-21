package com.siddharthmulupuru.passwordmanager.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<Map<String, Object>> handleUsernameAlreadyTaken(UsernameAlreadyTakenException e) {
        return ResponseEntity.status(409).body(Map.of(
            "error", e.getMessage(),
            "status", 409
        ));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsException e) {
        return ResponseEntity.status(401).body(Map.of(
            "error", e.getMessage(),
            "status", 401
        ));
    }

    @ExceptionHandler(VaultEntryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleVaultEntryNotFound(VaultEntryNotFoundException e) {
        return ResponseEntity.status(404).body(Map.of(
            "error", e.getMessage(),
            "status", 404
        ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(UnauthorizedException e) {
        return ResponseEntity.status(403).body(Map.of(
            "error", e.getMessage(),
            "status", 403
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception e) {
        return ResponseEntity.status(500).body(Map.of(
            "error", "An unexpected error occurred",
            "status", 500
        ));
    }
}
