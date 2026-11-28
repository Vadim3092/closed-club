package com.club.closed_club.handler;


import com.club.closed_club.exception.AgeRestrictionException;
import com.club.closed_club.exception.ParticipantNotFoundException;
import com.club.closed_club.exception.QrCodeAlreadyUsedException;
import com.club.closed_club.exception.QrCodeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AgeRestrictionException.class)
    public ResponseEntity<Map<String, String>> handleAgeRestriction(AgeRestrictionException exception) {
        return ResponseEntity.status(403).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(QrCodeAlreadyUsedException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyUsed(QrCodeAlreadyUsedException exception) {
        return ResponseEntity.status(409).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(QrCodeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleQrCodeNotFound(QrCodeNotFoundException exception) {
        return ResponseEntity.status(404).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleParticipantNotFound(ParticipantNotFoundException exception) {
        return ResponseEntity.status(404).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.status(400).body(Map.of("error", exception.getMessage()));
    }
}
