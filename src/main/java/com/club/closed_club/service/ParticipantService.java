package com.club.closed_club.service;

import com.club.closed_club.dto.ParticipantRequestDto;
import com.club.closed_club.dto.ParticipantResponseDto;
import com.club.closed_club.entity.Participant;
import com.club.closed_club.entity.QrCode;
import com.club.closed_club.exception.AgeRestrictionException;
import com.club.closed_club.exception.ParticipantNotFoundException;
import com.club.closed_club.mapper.ParticipantMapper;
import com.club.closed_club.repository.ParticipantRepository;
import com.club.closed_club.repository.QrCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;
    private final ParticipantMapper participantMapper;


    public ParticipantService(ParticipantRepository participantRepository,
                              QrCodeRepository qrCodeRepository,
                              ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.participantMapper = participantMapper;
    }

    public ParticipantResponseDto createParticipant(ParticipantRequestDto request) {
        if (request.getAge() == null) {
            throw new IllegalArgumentException("Возраст должен быть указан.");
        }

        if (request.getAge() < 18) {
            throw new AgeRestrictionException("Регистрация только с 18 лет");
        }

        Participant participant = participantMapper.toEntity(request);

        Participant saved = participantRepository.save(participant);

        QrCode qrCode = new QrCode();
        qrCode.setUuid(UUID.randomUUID());
        qrCode.setParticipant(saved);
        qrCode.setUsed(false);
        qrCodeRepository.save(qrCode);

        return participantMapper.toDto(saved);
    }


    public List<ParticipantResponseDto> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(participantMapper::toDto)
                .toList();
    }

    public ParticipantResponseDto getParticipantById(Long id) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantNotFoundException("Участник с id " + id + " не найден.");
        }
        return participantMapper.toDto(optionalParticipant.get());
    }

    public ParticipantResponseDto updateParticipant(Long id, ParticipantRequestDto updated) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantNotFoundException("Участник с id " + id + " не найден.");
        }

        if (updated.getAge() == null) {
            throw new IllegalArgumentException("Возраст должен быть указан.");
        }

        if (updated.getAge() < 18) {
            throw new AgeRestrictionException("Регистрация только с 18 лет.");
        }

        Participant existing = optionalParticipant.get();

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setMiddleName(updated.getMiddleName());
        existing.setAge(updated.getAge());

        Participant saved = participantRepository.save(existing);
        return participantMapper.toDto(saved);
    }

    public void deleteParticipant(Long id) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantNotFoundException("Участник с id " + id + " не найден.");
        }
        participantRepository.deleteById(id);
    }
}
