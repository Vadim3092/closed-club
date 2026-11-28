package com.club.closed_club.service;

import com.club.closed_club.dto.QrCodeRequestDto;
import com.club.closed_club.dto.QrCodeResponseDto;
import com.club.closed_club.entity.Participant;
import com.club.closed_club.entity.QrCode;
import com.club.closed_club.exception.ParticipantNotFoundException;
import com.club.closed_club.exception.QrCodeNotFoundException;
import com.club.closed_club.mapper.QrCodeMapper;
import com.club.closed_club.repository.ParticipantRepository;
import com.club.closed_club.repository.QrCodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final ParticipantRepository participantRepository;
    private final QrCodeMapper qrCodeMapper;

    public QrCodeService(QrCodeRepository qrCodeRepository,
                         ParticipantRepository participantRepository,
                         QrCodeMapper qrCodeMapper) {
        this.qrCodeRepository = qrCodeRepository;
        this.participantRepository = participantRepository;
        this.qrCodeMapper = qrCodeMapper;
    }

    public QrCodeResponseDto createQrCode(QrCodeRequestDto request) {
        Optional<Participant> optionalParticipant = participantRepository.findById(request.getParticipantId());
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantNotFoundException("Участник с id " + request.getParticipantId() + " не найден.");
        }

        Participant participant = optionalParticipant.get();

        QrCode qrCode = qrCodeMapper.toEntity(request, participant);
        QrCode saved = qrCodeRepository.save(qrCode);

        return qrCodeMapper.toDto(saved);
    }

    public List<QrCodeResponseDto> getAllQrCodes() {
        List<QrCode> qrCodes = qrCodeRepository.findAll();
        List<QrCodeResponseDto> responseList = new ArrayList<>();

        for (QrCode qrCode : qrCodes) {
            responseList.add(qrCodeMapper.toDto(qrCode));
        }

        return responseList;
    }

    public QrCodeResponseDto getQrCodeById(Long id) {
        Optional<QrCode> optionalQrCode = qrCodeRepository.findById(id);
        if (optionalQrCode.isEmpty()) {
            throw new QrCodeNotFoundException("Код с id " + id + " не найден.");
        }
        return qrCodeMapper.toDto(optionalQrCode.get());
    }

    public QrCodeResponseDto updateQrCode(Long id, QrCodeRequestDto request) {
        Optional<QrCode> optionalQrCode = qrCodeRepository.findById(id);
        if (optionalQrCode.isEmpty()) {
            throw new QrCodeNotFoundException("Код с id " + id + " не найден.");
        }

        Optional<Participant> optionalParticipant = participantRepository.findById(request.getParticipantId());
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantNotFoundException("Участник с id " + request.getParticipantId() + " не найден.");
        }

        QrCode qrCode = optionalQrCode.get();
        qrCode.setParticipant(optionalParticipant.get());

        QrCode saved = qrCodeRepository.save(qrCode);
        return qrCodeMapper.toDto(saved);
    }

    public void deleteQrCode(Long id) {
        Optional<QrCode> optionalQrCode = qrCodeRepository.findById(id);
        if (optionalQrCode.isEmpty()) {
            throw new QrCodeNotFoundException("Код с id " + id + " не найден.");
        }
        qrCodeRepository.deleteById(id);
    }
}
