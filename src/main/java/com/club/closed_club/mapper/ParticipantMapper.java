package com.club.closed_club.mapper;

import com.club.closed_club.dto.ParticipantRequestDto;
import com.club.closed_club.dto.ParticipantResponseDto;
import com.club.closed_club.entity.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public Participant toEntity(ParticipantRequestDto request) {
        Participant participant = new Participant();
        participant.setFirstName(request.getFirstName());
        participant.setMiddleName(request.getMiddleName());
        participant.setLastName(request.getLastName());
        participant.setAge(request.getAge());

        return participant;
    }

    public ParticipantResponseDto toDto(Participant participant) {
        ParticipantResponseDto response = new ParticipantResponseDto();
        response.setId(participant.getId());
        response.setFirstName(participant.getFirstName());
        response.setMiddleName(participant.getMiddleName());
        response.setLastName(participant.getLastName());
        response.setAge(participant.getAge());

        return response;
    }
}
