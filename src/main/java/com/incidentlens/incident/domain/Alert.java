package com.incidentlens.incident.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "alerts")
public class Alert {

    protected Alert() {
        // Required by JPA
    }

    public Alert(
            Incident incident,
            String source,
            String service,
            String type,
            Severity severity,
            String message,
            Instant occurredAt
    ) {
        this.incident = Objects.requireNonNull(incident);
        this.source = Objects.requireNonNull(source);
        this.service = Objects.requireNonNull(service);
        this.type = Objects.requireNonNull(type);
        this.severity = Objects.requireNonNull(severity);
        this.message = Objects.requireNonNull(message);
        this.occurredAt = occurredAt;
        this.receivedAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident incident;
    @Column(name = "source", nullable = false)
    private String source;
    @Column(name = "service", nullable = false)
    private String service;
    @Column(name = "alert_type", nullable = false)
    private String type;
    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private Severity severity;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "occurred_at", nullable = true)
    private Instant occurredAt;
    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    public UUID getId() {
        return id;
    }

    public Incident getIncident() {
        return incident;
    }

    public String getSource() {
        return source;
    }

    public String getService() {
        return service;
    }

    public String getType() {
        return type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

}
