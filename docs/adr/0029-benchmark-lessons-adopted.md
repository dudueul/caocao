# 0029. Adopt four benchmark lessons: relation matrix, per-mode tuning, named damage pipeline, level-scaled floor

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Amends:** [0021](0021-class-families-and-tiers.md) (the triangle becomes default
  data), [0022](0022-medium-number-scale.md) (the damage floor scales with level)
- **Related:** [`docs/remake/BENCHMARK.md`](../remake/BENCHMARK.md), [`docs/remake/RULES.md` §2](../remake/RULES.md#2-attack-resolution-draft)

## Context

The decompiled original ([BENCHMARK.md](../remake/BENCHMARK.md)) had a class ×
class relation matrix, per-mode coefficients, a level-based damage floor, and an
undocumented pipeline of about 120 ability hooks. The owner adopted four lessons
(and asked for more modern techniques, researched separately).

## Decision

- **A. Class-relation matrix.** `Ruleset.relations[attackerFamily][defenderFamily]`
  gives a damage‰; missing pairs are 1000. `triangleRelations()` builds the
  ADR 0021 triangle (±200‰) as default content; specific pairs (e.g. spearmen
  against cavalry) can be added as data. `ClassDef.counterGroup` is removed.
- **B. Per-mode tuning.** `Ruleset.modes[mode]` overrides any tuning value;
  `forMode()` merges it. A battle stores its `mode` (sandbox, story, arena,
  world, raid), and `apply`, `legalCommands`, the AI and previews all use that
  mode's tuning.
- **C. Named damage pipeline.** Strike damage is computed as named stages:
  **base → relation → role → critical → mode**, floored at each step. ATK and
  DEF already include terrain effect‰. `damageSteps()` returns the stage values,
  and `previewAttack().breakdown` shows them in the forecast. Future ability
  modifiers attach to a named stage, never as unordered hooks.
- **D. Level-scaled floor.** Base damage = `max(floor, ATK − ⌊DEF/2⌋)` with
  `floor = max(10, ⌊1.5 × attacker level⌋)` (`damageFloorPerLevel` = 1500‰): 10
  until level 6, 30 at level 20, 60 at level 40.

## Consequences

- **Positive:** more expressive matchups as data; balance per mode without
  forks; damage that can be explained step by step in the UI; late-game chip
  damage stays meaningful.
- **Negative:** the relation matrix is a 12 × 12 table to keep coherent; mode
  overrides must be shown in the UI where they change numbers.
- Covered by tests in `packages/rules/test/combat.test.ts`.

## Revisit when

Mode overrides make the same unit read differently in ways players find confusing.
