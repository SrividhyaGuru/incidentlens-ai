# IncidentLens AI — Agent instructions

## Mission and context

Build a minimum demonstrable AI-assisted incident investigation platform for distributed backend systems. IncidentLens
itself is a modular monolith. Read `docs/ROADMAP.md`, `docs/ARCHITECTURE.md` and `docs/PROGRESS.md` before starting.
Inspect the current code and Git status; ask about local changes you cannot see. Code and tests describe actual
behavior; documentation distinguishes decisions from plans. Flag discrepancies rather than guessing.

## Engineering rules

- Java 21, Spring Boot 4.0.9, Maven Wrapper, PostgreSQL, Flyway. Use feature-based packages and a modular monolith.
- Keep changes focused on the assigned task. Avoid speculative abstractions, extra infrastructure and dependencies. Ask
  before changing scope or architecture.
- Use constructor injection, explicit transaction boundaries, validated API DTOs and deliberate error handling. Do not
  serialize JPA entities directly. Never rewrite applied Flyway migrations.
- Use focused tests for business rules, HTTP contracts and persistence. Unit tests should not require a database. Verify
  actual PostgreSQL behavior with integration tests where relevant.
- Never expose credentials, real production data or sensitive logs. Use synthetic evidence and environment-based AWS
  configuration. No autonomous remediation.

## Workflow

1. Inspect relevant files and propose a small implementation plan with acceptance criteria.
2. Implement the smallest coherent increment; explain important tradeoffs.
3. Run relevant tests with `./mvnw` when the environment permits. Report commands, results and anything not run. Never
   claim unexecuted tests passed.
4. Update progress after meaningful increments and architecture/roadmap only when decisions change. Record unfinished
   work and the exact next action before stopping.
5. Show the diff and suggest a commit message. Do not commit, push, open a PR or modify remote branches without explicit
   approval. Preserve user changes.

The project owner makes engineering decisions. Agents are interchangeable; durable context belongs in the repository.
Keep documentation factual and concise.
