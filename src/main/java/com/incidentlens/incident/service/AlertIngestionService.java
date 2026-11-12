package com.incidentlens.incident.service;

import com.incidentlens.incident.api.AlertRequest;
import com.incidentlens.incident.domain.Alert;
import com.incidentlens.incident.domain.Incident;
import com.incidentlens.incident.repository.AlertRepository;
import com.incidentlens.incident.repository.IncidentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlertIngestionService {

    private final IncidentRepository incidentRepository;
    private final AlertRepository alertRepository;

    public AlertIngestionService(IncidentRepository incidentRepository, AlertRepository alertRepository) {
        this.incidentRepository = incidentRepository;
        this.alertRepository = alertRepository;
    }

    @Transactional
    public UUID ingest(AlertRequest request) {
        Incident incident = incidentRepository.save(mapRequestToIncident(request));
        alertRepository.save(mapRequestToAlert(request, incident));
        return incident.getId();
    }

    private Incident mapRequestToIncident(AlertRequest request) {
        return new Incident(request.severity());
    }

    private Alert mapRequestToAlert(AlertRequest request, Incident incident) {
        return new Alert(incident, request.source(), request.service(),
                request.alertType(), request.severity(), request.message(), request.occurredAt());
    }
}
