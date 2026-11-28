package com.club.closed_club.exception;

public class QrCodeAlreadyUsedException extends RuntimeException {
    public QrCodeAlreadyUsedException(String message) {
        super(message);
    }
}
