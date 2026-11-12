package com.incidentlens.incident.api;

import com.incidentlens.incident.domain.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AlertRequest(
        @NotBlank String source,
        @NotBlank String service,
        @NotBlank String alertType,
        @NotNull Severity severity,
        @NotBlank String message,
        Instant occurredAt

) {
}
