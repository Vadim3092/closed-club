package com.club.closed_club.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "qr_codes")
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;
    @Column(name = "is_used", nullable = false)
    private Boolean used = false;
    @Column(name = "used_at")
    private LocalDateTime usedAt;
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    public QrCode(Long id, Participant participant, LocalDateTime usedAt, Boolean used, UUID uuid) {
        this.id = id;
        this.participant = participant;
        this.usedAt = usedAt;
        this.used = used;
        this.uuid = uuid;
    }

    public QrCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
