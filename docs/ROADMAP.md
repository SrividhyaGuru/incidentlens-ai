# MVP roadmap

## Outcome
An interview-ready end-to-end demo: ingest an alert, retrieve the incident, gather simulated cross-service operational evidence, use AWS Bedrock to investigate a distributed-system incident, produce a structured report and record human approval of a proposed remediation.

Target: approximately three weeks at 15–20 hours/week; prioritize a working vertical slice and the first Bedrock investigation over polish. This is a target, not a deadline guarantee.

## Delivery sequence
| Sessions | Deliverable |
|---|---|
| 1–2 | POST alert and GET incident APIs, validation, focused MVC tests and PostgreSQL persistence verification |
| 3 | Synthetic cross-service logs, metrics, distributed traces and deployment fixtures; retrieval interface |
| 4–5 | Bedrock Converse integration and first evidence-grounded investigation |
| 6 | Verify availability and relevance of `opspilot-runbooks-kb`; integrate real Bedrock runbook retrieval if feasible |
| 7 | Structured report with evidence references and uncertainty |
| 8 | Proposed remediation and human approval state; no automatic execution |
| 9 | Idempotency, basic correlation, important failure paths and essential tests |
| 10 | End-to-end demo, README, architecture review and interview walkthrough |

If time is tight, simplify evidence adapters and KB integration before delaying the first investigation. Keep core ingestion correctness, duplicate handling, evidence grounding and human approval. Reassess the plan after each milestone.

## Out of scope
Production monitoring integrations, Kubernetes, Kafka, Redis, microservice decomposition, full authentication/authorization, elaborate frontend, multi-tenancy and autonomous remediation. Do not add these without an explicit scope decision. The MVP is a local/sandbox demonstration, not a production-ready service.

## Definition of done
Reproducible setup; demonstrable alert-to-report workflow; essential tests with recorded results; safe approval boundary; clear README and known limitations. Keep job-search and interview preparation time protected.
