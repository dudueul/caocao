# @tk/content

Game data for `@tk/rules`, written as typed TypeScript modules and checked by
validators in the test suite ([ADR 0032](../../docs/adr/0032-content-as-typed-modules.md)).
Our own data only: nothing copied from Koei or Nexon sources ([ADR 0002](../../docs/adr/0002-remake-from-scratch.md)).

| Module | Holds |
|---|---|
| `classes.ts` | The six P0 tier-1 classes ([ADR 0021](../../docs/adr/0021-class-families-and-tiers.md)) |
| `terrain.ts` | Twelve terrains, map codes, and the family × terrain affinity table ([ADR 0023](../../docs/adr/0023-terrain-family-affinity-and-cover.md)) |
| `stratagems.ts` | 화계, 풍계, 혼란, 치료 ([ADR 0024](../../docs/adr/0024-stratagems-elements-and-fortune-twists.md), [ADR 0030](../../docs/adr/0030-modern-techniques-adopted.md)) |
| `ruleset.ts` | `P0_RULES` (with the counter-triangle relation matrix) and its content hash `P0_RULES_HASH` |
| `officers.ts` | Officers: historical names, our own ratings |
| `maps.ts`, `scenarios.ts` | Maps as text rows; scenarios and `scenarioSetup()` → `BattleSetup` |
| `validate.ts` | `validateRuleset()`, `validateScenario()` |

The numbers are tabled in [RULES.md](../../docs/remake/RULES.md) §3–§5.

## Officer rating rubric

Ratings are 1–100 per stat ([ADR 0020](../../docs/adr/0020-five-stats-with-distinct-roles.md)),
set in **steps of 5** from our own reading of the historical record (mainly
the *Records of the Three Kingdoms*), never from another game's tables. Finer
values come with the full officer pass.

| Band | Meaning |
|---|---|
| 95–100 | the best of the era at this (a handful of officers) |
| 85–90 | outstanding; famous for it |
| 70–80 | strong; a reliable general or adviser |
| 50–65 | capable |
| 30–45 | weak at it |
| 5–25 | no talent recorded for it |

운 (fortune) reads the officer's recorded luck: escapes, survivals and deaths in battle.

## Maps

One character per tile, row 0 at the top:

| Code | Terrain | Code | Terrain | Code | Terrain |
|---|---|---|---|---|---|
| `.` | 평지 plain | `m` | 산 mountain | `v` | 마을 village |
| `g` | 초원 grass | `d` | 여울 ford | `F` | 성채 fort |
| `f` | 숲 forest | `w` | 강 river | `#` | 성벽 wall |
| `h` | 언덕 hills | `b` | 다리 bridge | `c` | 진영 camp |

## Scenarios

| Id | Name | Map | Notes |
|---|---|---|---|
| `wuchao-raid` | 오소 습격 (200) | 16 × 12 | P0 sandbox: 조조군 (6) cross the river to the 원소군 grain depot (6), whose defenders hold the camp. The roster draws freely on the Guandu campaign. |

Scenario units may carry **standing orders** for the AI, written as gambits
(`ORDERS.hold`, `ORDERS.healThenHold`); `scenarioOrders()` collects them for
`chooseCommand` ([ADR 0034](../../docs/adr/0034-scenario-ai-orders.md)).
