# Progress and handoff

Last updated: 2026-11-24

## Completed / observed
- Database migrations, domain entities, repositories and ingestion service previously committed.
- Repository inspection confirmed Java 21 and Spring Boot 4.0.9 in `pom.xml`, and transactional incident/alert creation in `AlertIngestionService`.
- Owner previously reported passing ingestion Mockito unit test and successful local build. Tests have not been rerun during this documentation update.
- Documentation baseline prepared; verify its commit and current working tree before beginning the next task.

## Current task — next implementation
Deliver the first HTTP-to-database vertical slice:
- `POST /api/v1/alerts`: validate existing `AlertRequest`, delegate to ingestion service, return HTTP 201 with `{"incidentId":"<uuid>"}`.
- `GET /api/v1/incidents/{id}`: return incident and associated alerts via DTOs; return 404 for a missing incident.
- Add focused MVC tests for successful requests, validation and missing incident. Verify persistence against PostgreSQL, including the alert foreign key.
- Preserve current one-alert/one-incident behavior for this slice. Do not add correlation, idempotency, AI or remediation here.

## Pending / limitations
- API completion and database integration verification are not yet confirmed.
- Correlation and idempotency not implemented in inspected ingestion service.
- Synthetic cross-service operational evidence, Bedrock, KB retrieval, reports and approval are planned. Verify the existing `opspilot-runbooks-kb` in AWS before reuse; its current availability has not been confirmed.
- No known current blocker; local uncommitted changes and environment readiness must be checked by owner/agent.

## Next action
Read current source, tests and migrations; check `git status`; implement the API slice above. Run relevant tests and record actual outcomes. Suggest a small commit after review.

## Handoff convention
Replace stale current-task notes after each meaningful increment. Record changed files, actual test commands/results, unresolved issues and exact next action. Update roadmap or architecture only when decisions change. Do not store secrets or personal information here.
