package com.club.closed_club.controller;


import com.club.closed_club.dto.ParticipantRequestDto;
import com.club.closed_club.dto.ParticipantResponseDto;
import com.club.closed_club.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;


    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;

    }

    @PostMapping
    public ParticipantResponseDto createParticipant (@RequestBody ParticipantRequestDto request) {
        return participantService.createParticipant(request);
    }

    @GetMapping
    public List<ParticipantResponseDto> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantResponseDto getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }

    @PutMapping("/{id}")
    public ParticipantResponseDto updateParticipant(@PathVariable Long id, @RequestBody ParticipantRequestDto request) {
        return participantService.updateParticipant(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
    }
}
