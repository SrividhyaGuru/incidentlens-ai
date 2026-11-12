CREATE TABLE incidents (
                           id UUID PRIMARY KEY,
                           status VARCHAR(30) NOT NULL,
                           severity VARCHAR(30) NOT NULL,
                           created_at TIMESTAMPTZ NOT NULL,
                           updated_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE alerts (
                        id UUID PRIMARY KEY,
                        incident_id UUID NOT NULL,
                        source VARCHAR(100) NOT NULL,
                        service VARCHAR(100) NOT NULL,
                        alert_type VARCHAR(100) NOT NULL,
                        severity VARCHAR(30) NOT NULL,
                        message TEXT NOT NULL,
                        occurred_at TIMESTAMPTZ NOT NULL,
                        received_at TIMESTAMPTZ NOT NULL,

                        CONSTRAINT fk_alert_incident
                            FOREIGN KEY (incident_id)
                                REFERENCES incidents(id)
);

CREATE INDEX idx_alerts_incident_id
    ON alerts(incident_id);