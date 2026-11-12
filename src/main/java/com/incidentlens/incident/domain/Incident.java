package com.incidentlens.incident.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "incidents")
public class Incident {
    protected Incident() {
        // Required by JPA
    }

    public Incident(Severity severity) {
        this.status = IncidentStatus.OPEN;
        this.severity = Objects.requireNonNull(severity, "severity must not be null");
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IncidentStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private Severity severity;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public UUID getId() {
        return id;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
