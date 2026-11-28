package com.club.closed_club.controller;

import com.club.closed_club.dto.QrCodeRequestDto;
import com.club.closed_club.dto.QrCodeResponseDto;
import com.club.closed_club.service.QrCodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qr-codes")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    public QrCodeResponseDto createQrCode(@RequestBody QrCodeRequestDto request) {
        return qrCodeService.createQrCode(request);
    }

    @GetMapping
    public List<QrCodeResponseDto> getAllQrCodes() {
        return qrCodeService.getAllQrCodes();
    }

    @GetMapping("/{id}")
    public QrCodeResponseDto getQrCodeById(@PathVariable Long id) {
        return qrCodeService.getQrCodeById(id);
    }

    @PutMapping("/{id}")
    public QrCodeResponseDto updateQrCode(@PathVariable Long id, @RequestBody QrCodeRequestDto request) {
        return qrCodeService.updateQrCode(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteQrCode(@PathVariable Long id) {
        qrCodeService.deleteQrCode(id);
    }
}
