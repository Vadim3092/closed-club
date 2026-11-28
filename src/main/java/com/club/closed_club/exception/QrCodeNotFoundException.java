package com.club.closed_club.exception;

public class QrCodeNotFoundException extends RuntimeException {
    public QrCodeNotFoundException(String message){
        super(message);
    }
}
