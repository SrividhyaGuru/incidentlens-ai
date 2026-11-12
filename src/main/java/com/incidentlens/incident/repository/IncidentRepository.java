package com.incidentlens.incident.repository;

import com.incidentlens.incident.domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {
}
