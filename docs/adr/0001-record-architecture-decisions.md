# 0001. Record architecture decisions as ADRs

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [ADR index](README.md), [`docs/remake/`](../remake/README.md)

## Context

The project is moving from recovering the 2018 mobile game to designing a new
game. Decisions are being made quickly in interactive design sessions, and they
depend on each other: the turn structure decides the server model, which
decides the language, which decides the renderer. Without a written trail,
later sessions (human or AI) would re-argue settled points or miss why a choice
was made.

## Options considered

1. **One design document, edited in place:** easy to read, but loses *why* and *when*.
2. **ADRs plus living technical documents:** the ADRs keep the history, and the documents describe the current design.

## Decision

Keep one Architecture Decision Record per decision in `docs/adr/`
(`NNNN-title.md`, from [`template.md`](template.md)), and keep the technical
documents in `docs/remake/` current, with diagrams where they help. Every
decision gets its ADR in the same commit, and the documents are updated alongside.

## Consequences

- **Positive:** reviewers and future sessions see what was decided, the
  alternatives and the revisit triggers. Documents stay short because the
  rationale lives in ADRs.
- **Negative:** some overhead per decision; superseding needs discipline.

## Revisit when

The ADR count makes the index hard to scan (more than about 100); then group by area.
