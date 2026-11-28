package com.club.closed_club.service;


import com.club.closed_club.dto.LoginResponseDto;
import com.club.closed_club.entity.Participant;
import com.club.closed_club.entity.QrCode;
import com.club.closed_club.exception.AgeRestrictionException;
import com.club.closed_club.exception.QrCodeAlreadyUsedException;
import com.club.closed_club.exception.QrCodeNotFoundException;
import com.club.closed_club.repository.QrCodeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class QrLoginService {

    private final QrCodeRepository qrCodeRepository;

    public QrLoginService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    @Transactional
    public LoginResponseDto login(UUID uuid) {
        Optional<QrCode> found = qrCodeRepository.findByUuid(uuid);
        if (found.isEmpty()) {
            throw new QrCodeNotFoundException("QR-CODE не найден.");
        }

        QrCode code = found.get();
        if (code.getUsed()) {
            throw new QrCodeAlreadyUsedException("QR-CODE уже использован.");
        }

        Integer age = code.getParticipant().getAge();
        if (age == null) {
            throw new IllegalArgumentException("Возраст должен быть указан.");
        }

        if (age < 18) {
            throw new AgeRestrictionException("Вход только с 18 лет.");
        }

        code.setUsed(true);
        code.setUsedAt(LocalDateTime.now());
        qrCodeRepository.save(code);

        QrCode newCode = new QrCode();
        newCode.setUuid(UUID.randomUUID());
        newCode.setParticipant(code.getParticipant());
        newCode.setUsed(false);
        qrCodeRepository.save(newCode);

        Participant participant = code.getParticipant();
        String fullName = participant.getLastName() + " " + participant.getFirstName() + " " + participant.getMiddleName();

        LoginResponseDto response = new LoginResponseDto();
        response.setFullName(fullName);
        response.setNewQrCode(newCode.getUuid());
        return response;
    }
}
