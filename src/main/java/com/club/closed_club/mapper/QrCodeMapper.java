package com.club.closed_club.mapper;

import com.club.closed_club.dto.QrCodeRequestDto;
import com.club.closed_club.dto.QrCodeResponseDto;
import com.club.closed_club.entity.Participant;
import com.club.closed_club.entity.QrCode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QrCodeMapper {

    public QrCode toEntity(QrCodeRequestDto request, Participant participant) {
        QrCode qrCode = new QrCode();
        qrCode.setUuid(UUID.randomUUID());
        qrCode.setUsed(false);
        qrCode.setParticipant(participant);

        return qrCode;
    }

    public QrCodeResponseDto toDto(QrCode qrCode) {
        QrCodeResponseDto response = new QrCodeResponseDto();
        response.setId(qrCode.getId());
        response.setUuid(qrCode.getUuid());
        response.setUsed(qrCode.getUsed());
        response.setUsedAt(qrCode.getUsedAt());
        response.setParticipantId(qrCode.getParticipant().getId());

        return response;
    }


}
