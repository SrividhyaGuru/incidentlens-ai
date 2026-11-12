package com.incidentlens.incident.service;

import com.incidentlens.incident.api.AlertRequest;
import com.incidentlens.incident.domain.Alert;
import com.incidentlens.incident.domain.Incident;
import com.incidentlens.incident.domain.IncidentStatus;
import com.incidentlens.incident.domain.Severity;
import com.incidentlens.incident.repository.AlertRepository;
import com.incidentlens.incident.repository.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlertIngestionServiceTest {

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private IncidentRepository incidentRepository;


    private AlertIngestionService alertIngestionService;

    @BeforeEach
    public void setUp() {
        alertIngestionService = new AlertIngestionService(incidentRepository, alertRepository);
    }

    @Test
    public void shouldCreateIncidentAndAlertGivenValidAlertRequest() {
        AlertRequest alertRequest = new AlertRequest(
                "prometheus",
                "payment_service",
                "HIGH_ERROR_RATE",
                Severity.CRITICAL,
                "High error rate in last 5 mins",
                null
        );

        ArgumentCaptor<Incident> incidentArgumentCaptor = ArgumentCaptor.forClass(Incident.class);
        ArgumentCaptor<Alert> alertArgumentCaptor = ArgumentCaptor.forClass(Alert.class);

        when(incidentRepository.save(any(Incident.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UUID incidentId = alertIngestionService.ingest(alertRequest);

        verify(incidentRepository).save(incidentArgumentCaptor.capture());
        verify(alertRepository).save(alertArgumentCaptor.capture());

        Incident persistedIncident = incidentArgumentCaptor.getValue();
        Alert persistedAlert = alertArgumentCaptor.getValue();

        assertSame(persistedIncident, persistedAlert.getIncident());

        assertEquals(alertRequest.service(), persistedAlert.getService());
        assertEquals(alertRequest.source(), persistedAlert.getSource());
        assertEquals(alertRequest.alertType(), persistedAlert.getType());
        assertEquals(alertRequest.message(), persistedAlert.getMessage());
        assertEquals(alertRequest.severity(), persistedAlert.getSeverity());
        assertEquals(alertRequest.occurredAt(), persistedAlert.getOccurredAt());

        assertEquals(alertRequest.severity(), persistedIncident.getSeverity());
        assertEquals(IncidentStatus.OPEN, persistedIncident.getStatus());

        assertNotNull(persistedAlert.getReceivedAt());
        assertNotNull(persistedIncident.getCreatedAt());

    }
}
