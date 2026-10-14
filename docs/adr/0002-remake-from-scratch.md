# 0002. Build the remake from scratch; the recovered project is a reference archive

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0006](0006-typescript-everywhere.md), [0007](0007-top-down-2d-pixijs.md),
  [`docs/re/CLASSIC_ART.md`](../re/CLASSIC_ART.md), [`data/tables/`](../../data/tables/README.md)

## Context

The owner will redesign and redraw everything, including the online combat. They
asked whether to keep the recovered Unity 6 project or start again (Rust/WASM
was suggested for web support). Facts from the repository:

- `client/` has 5,956 C# scripts, about **95% of them empty stubs** from
  AssetRipper. NGUI, PlayMaker and tk2d are stubbed too. Its 409 textures, 40
  scenes and 62 prefabs are the old art and UI, which will be replaced.
- `server/` is about 1,250 lines of TypeScript (Engine.IO/Socket.IO on Workers).
- The **useful knowledge** is in `extracted/` (IL2CPP names, signatures and
  ARM64 code) and the 1998 data. It is a design reference, not code to port:

| Reference | What's there |
|---|---|
| Combat formulas | `UnitCommonFunc`: 96 methods, 150 KB of code (`GetRawAttackDamage`, `GetAttackSuccessRate`, `GetRawSkillDamage` …) |
| Abilities | 475 `SpAction###`, 234 `Skill*` scripts, `Conditions` table |
| AI | `nAI.*` utility scoring + `AITendencies` weight schema |
| Events | 216 `EventCmd` types with typed fields; serialized to EEX, the 1998 script format |
| Online flow | `BattlePlayManager` / `MultiplayManager` (decompiled, documented) |
| Data | 239 table schemas, localization, 1998 officers/items/classes/maps |

## Options considered

1. **Keep the recovered Unity project:** none of its logic survives, the old
   art is replaced anyway, and the stubs would be carried as dead weight.
2. **Fresh Unity 6 project:** good store tooling, but the C# rules can't run in
   the Durable Object referee ([0004](0004-server-authoritative-referee.md)).
3. **Fresh codebase on a web-native stack:** chosen; the stack is in [0006](0006-typescript-everywhere.md).

## Decision

Start a new codebase. This repository stays as a **read-only reference
archive**: formulas, the ability and AI catalogue, the event vocabulary and
schemas are mined from it as design input. No code or Koei assets are copied
into the remake. Historical names are public; Koei's art, text and specific
expression are not.

## Consequences

- **Positive:** no dead weight; the stack can be chosen for the new design.
- **Negative:** everything from input handling to UI is new work.
- **Follow-ups:** decide where the new code lives (separate repository or a
  folder here; open question in [`docs/remake/README.md`](../remake/README.md)).
  Optional: decompile the key formula functions into an engine-neutral rules
  reference (about 50–100 functions).

## Revisit when

Never for the Unity project itself. Reopen the stack only through [0006](0006-typescript-everywhere.md).
