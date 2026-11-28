package com.club.closed_club.controller;


import com.club.closed_club.dto.LoginResponseDto;
import com.club.closed_club.service.QrLoginService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/qr")
public class QrLoginController {

    private final QrLoginService qrLoginService;

    public QrLoginController(QrLoginService qrLoginService) {
        this.qrLoginService = qrLoginService;
    }

    @PostMapping("/login/{uuid}")
    public LoginResponseDto login(@PathVariable UUID uuid) {
        return qrLoginService.login(uuid);
    }
}
