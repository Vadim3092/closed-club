package com.club.closed_club.dto;

import java.util.UUID;

public class LoginResponseDto {

    private String fullName;
    private UUID newQrCode;

    public LoginResponseDto(String fullName, UUID newQrCode) {
        this.fullName = fullName;
        this.newQrCode = newQrCode;
    }
    public LoginResponseDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UUID getNewQrCode() {
        return newQrCode;
    }

    public void setNewQrCode(UUID newQrCode) {
        this.newQrCode = newQrCode;
    }
}
