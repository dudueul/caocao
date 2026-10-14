# 0032. Content starts as typed TypeScript modules with our own officer ratings

- **Status:** Proposed (default chosen while building P0; the owner is asked to confirm)
- **Date:** 2026-10-14
- **Deciders:** Claude (proposal)
- **Related:** [0002](0002-remake-from-scratch.md), [0004](0004-server-authoritative-referee.md),
  [0006](0006-typescript-everywhere.md), [0015](0015-delivery-order-engine-then-arena.md),
  [0020](0020-five-stats-with-distinct-roles.md), [0023](0023-terrain-family-affinity-and-cover.md),
  [`packages/content`](../../packages/content/README.md), [`docs/remake/RULES.md`](../remake/RULES.md)

## Context

P0 needs real content: the six tier-1 classes, twelve terrains, the
6 × 12 affinity table, four stratagems, officers and a sandbox map. The
architecture sketch said "JSON + schemas, validated at build time". In P0 no
editor exists yet ([`apps/tools`](../../apps/tools/README.md) is P2), and every
number must be an integer the rules can hash ([0006](0006-typescript-everywhere.md)).
Officer ratings must be ours: nothing may come from Koei or Nexon data ([0002](0002-remake-from-scratch.md)).

## Options considered

1. **Typed TS modules plus validation tests:** chosen. The compiler checks every
   field against `@tk/rules` types; `validateRuleset()` and `validateScenario()`
   check cross-references, table completeness and integer-only values.
2. **JSON files with JSON Schema:** editor-friendly and language-neutral, but
   needs a schema generator and loader now, and errors show up later.
3. **A spreadsheet export (CSV):** easy bulk tuning; weak typing and hard to diff.

## Decision

- `packages/content` holds data as TypeScript modules typed by `@tk/rules`
  (`P0_RULES`, `OFFICERS`, maps as text rows, `SCENARIOS`), plus
  `validateRuleset()` / `validateScenario()`, run by `bun test`.
- The determinism guard and Biome's banned globals cover `packages/content/src`
  as well as `packages/rules/src`.
- `P0_RULES_HASH = hashState(P0_RULES)` identifies the exact numbers a battle was
  played with; replays and the referee record it ([0004](0004-server-authoritative-referee.md)).
- **Officer ratings are our own,** set in steps of 5 from the rubric in the
  content README (our reading of the historical record). Fine-grained values
  come with the full officer pass. Names are historical and public domain.
- When the editors arrive (P2), the same validators run on JSON exported from
  these types, or the data moves to JSON. That is a new ADR.

## Consequences

- **Positive:** type errors at edit time; no loader or schema tooling in P0;
  content and rules versions move together in one commit.
- **Negative / costs:** non-programmers can't edit content until the tools
  exist; client bundles include the data as code (small in P0).
- **Follow-ups:** the P0 tables are in [RULES.md](../remake/RULES.md) §3–§5; the
  balance numbers are starting tuning, measured by the sandbox soak.

## Revisit when

The map, event or anchor editors (P2) need to write content, or content grows past what reads well as code.
