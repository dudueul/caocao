# 0006. TypeScript everywhere; the rules engine is one pure TS package

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0005](0005-web-first-distribution.md),
  [`docs/remake/ARCHITECTURE.md` §2–3](../remake/ARCHITECTURE.md#2-code-layout)

## Context

The rules engine (formulas, about 200 abilities, conditions, AI, mission events)
must run in the browser and inside Durable Objects ([0004](0004-server-authoritative-referee.md)).
That leaves TypeScript or Rust compiled to WASM; C# isn't practical in a DO.
The team is one developer (plus possibly an artist) who is strong in
TypeScript, wrote the existing Workers server, and is new to Rust. Performance
isn't a constraint for turn-based battles.

## Options considered

1. **TypeScript everywhere:** chosen. The client and server import the same package; fastest iteration.
2. **Rust rules engine compiled to WASM, TS shells:** stricter determinism and
   a safer compiler, but a learning curve, glue code at the boundary and slower iteration.
3. **Godot / Unity:** good editors, but the rules engine can't run in the DO.

## Decision

A TypeScript monorepo: `rules` (pure, deterministic), `content` (data and
schemas), `protocol` (messages), `client` (PixiJS + UI), `server` (Workers and
DOs), `tools` (editors). The rules package has **no** I/O, DOM, clock, global
RNG or floating-point maths in outcomes. It uses integer or fixed-point maths and a
seeded PRNG passed in explicitly. A lint rule and golden-replay tests enforce this.

## Consequences

- **Positive:** one language, one rules package, no client/server mismatch;
  the existing server code and knowledge carry over.
- **Negative:** determinism is by discipline rather than compiler-enforced. GC
  pauses don't matter for turn-based play.
- **Follow-ups:** pick the UI framework for menus (open question); set up lint
  rules (`no-restricted-globals` for `Math.random`, `Date`).

## Revisit when

The rules or AI CPU in DOs exceeds the budget in [0004](0004-server-authoritative-referee.md),
or native mobile performance requires it. The rules package could then be
ported to Rust behind the same command/event interface.
