# Remake: a Three Kingdoms grid-tactics game, built from scratch

> Living document. Decisions are recorded in [`docs/adr/`](../adr/README.md);
> this folder describes the **current** design. Last updated 2026-10-15
> (decisions [0001](../adr/0001-record-architecture-decisions.md)–[0041](../adr/0041-p1-arena-defaults.md)).

## Vision

A lighter, cleaner successor to 조조전 and its 2018 mobile remake. Grid
tactics battles sit at the heart of every mode; around them is a **living
Three Kingdoms history** where friends create officers, found households, serve
or betray lords, and watch 120 years unfold in 120 days. It ships on the web
first, so a friend joins with a link, and the same build later wraps for PC
and the mobile stores.

| Document | Content |
|---|---|
| [ARCHITECTURE.md](ARCHITECTURE.md) | Stack, code layout, rules engine, online battles, server topology, data, testing |
| [RULES.md](RULES.md) | Battle and character rules: stats, attack resolution, formulas (starting tuning) |
| [BENCHMARK.md](BENCHMARK.md) | How the 2018 mobile game resolved battles (decompiled, in our words) and the lessons for ours |
| [MODERN_TECHNIQUES.md](MODERN_TECHNIQUES.md) | Techniques from recent tactics games, rated for fit (danger zones, gambits, break, hazards, AI) |
| [WORLD_ENGINE.md](WORLD_ENGINE.md) | Living History and Warlord Campaign: calendar, clock, anchor events, households, pacing, seasons |
| [../adr/](../adr/README.md) | Every decision with its context, alternatives and revisit triggers |

## Main menu

```mermaid
flowchart TB
  M(("Main menu"))
  M --> S["Story<br/>조조전-style chapters"]
  M --> LH["Living History<br/>public seasons, 3 a year"]
  M --> WC["Warlord Campaign<br/>private worlds"]
  M --> A["Arena<br/>live PvP rooms + async ladder"]
  M --> R["Raids<br/>co-op bosses"]
  M --> D["Duels / Board<br/>quick mini-modes"]
  subgraph BE["One battle engine (rules package)"]
    direction LR
    SP["SidePhases<br/>turn policy"]
    AA["AlternatingActivations<br/>turn policy"]
  end
  subgraph WE["One World engine"]
    P1["preset: public season"]
    P2["preset: private campaign"]
  end
  S --> SP
  R --> SP
  A --> AA
  D --> AA
  LH --> P1
  WC --> P2
  WE -->|"armies meet"| AA
```

## Decisions so far

| Area | Decision | ADR |
|---|---|---|
| Codebase | New codebase; this repo is a reference archive | [0002](../adr/0002-remake-from-scratch.md) |
| Turn structure | Side phases in PvE, alternating single-unit activations in PvP | [0003](../adr/0003-turn-structure.md) |
| Online model | Durable Object referee runs the shared rules; clients animate events | [0004](../adr/0004-server-authoritative-referee.md) |
| Distribution | Web/PWA + PC (Tauri) for friends; store wrappers later | [0005](../adr/0005-web-first-distribution.md) |
| Language | TypeScript everywhere; one pure rules package | [0006](../adr/0006-typescript-everywhere.md) |
| Art | Top-down square-grid 2D, 3 drawn facings, PixiJS | [0007](../adr/0007-top-down-2d-pixijs.md) |
| Modes | Story, Living History, Warlord Campaign, Arena, Raids, Duels/Board | [0008](../adr/0008-modes-as-main-menu-entries.md) |
| History | Anchored history with what-ifs; 161–280 in 120 days; 3 seasons a year | [0009](../adr/0009-living-history-anchored-history.md) |
| Death | Households and lineage; heirs inherit | [0010](../adr/0010-lineage-households.md) |
| Pacing | Check-ins, prime-time anchor battles, auto-join on connect | [0011](../adr/0011-world-pacing-and-auto-join.md) |
| Battle control | One seat per household corps; ≤ 6 seats a side; linked fronts | [0012](../adr/0012-household-corps-seats.md) |
| World modes | One World engine, two presets | [0013](../adr/0013-one-world-engine.md) |
| Progression | Scoped per mode; Arena normalized | [0014](../adr/0014-scoped-progression-fair-arena.md) |
| Build order | Engine → Arena → Story → private World → public seasons → Raids, Duels | [0015](../adr/0015-delivery-order-engine-then-arena.md) |
| Repository | This repository, cleared; the recovery archive is commit `dabaf0a2` | [0017](../adr/0017-clear-this-repository.md) (supersedes [0016](../adr/0016-new-repository.md)) |
| Stats | 무력, 통솔, 지력, 민첩, 운, each with one battle role and one World role (운 = critical hits + fate on defeat) | [0020](../adr/0020-five-stats-with-distinct-roles.md) |
| Classes | 12 families × 3 tiers + unique lines; counter triangle infantry ▶ cavalry ▶ archers | [0021](../adr/0021-class-families-and-tiers.md) |
| Number scale | HP 100–600, damage `max(10, ATK − DEF/2)`, triangle ±200‰, critical ×1.5 | [0022](../adr/0022-medium-number-scale.md) |
| Terrain | ~12 types; per-family movement cost and effect‰ on ATK/DEF; cover evasion for anyone; healing tiles | [0023](../adr/0023-terrain-family-affinity-and-cover.md) |
| Stratagems | MP; fire, water, earth, wind, support, disruption; terrain‰ × weather‰; rare 운 fortune twists (천운/불운, ~1 in 25) | [0024](../adr/0024-stratagems-elements-and-fortune-twists.md) |
| Counters and double attacks | Double attack at a 민첩 gap ≥ 20 (무도가 ≥ 10); one counter per enemy action at 800‰; shown exactly in previews | [0025](../adr/0025-deterministic-double-attacks-and-counters.md) |
| Movement | Soft ZOC: entering an enemy-adjacent tile ends movement; cavalry and bandits pass one ZOC tile | [0026](../adr/0026-soft-zone-of-control.md) |
| Engine defaults | One command per unit action, alternating round starter, confusion, round-limit draw, baseline AI | [0027](../adr/0027-p0-engine-defaults.md) |
| Undo | Free move undo until an action is confirmed; then final, in every mode | [0028](../adr/0028-undo-moves-until-action.md) |
| Benchmark lessons | Relation matrix as data, per-mode tuning, named damage pipeline, damage floor `max(10, 1.5 × level)` | [0029](../adr/0029-benchmark-lessons-adopted.md) |
| Modern techniques | Danger zones (+ boss intent), gambits for absent seats, break on advantage, push and fire hazards; all free for everyone | [0030](../adr/0030-modern-techniques-adopted.md) |
| Client UI | Svelte 5 components as a DOM overlay on the PixiJS canvas; one battle store | [0031](../adr/0031-svelte-dom-overlay.md) |
| Toolchain | Bun (install, tests, scripts), TypeScript 7, Biome, determinism guard | [0019](../adr/0019-bun-toolchain.md) (supersedes [0018](../adr/0018-toolchain.md)) |
| Content format | Typed TS modules + validators; our own officer ratings in steps of 5; ruleset content hash | [0032](../adr/0032-content-as-typed-modules.md) |
| Sandbox client | Fixed layout, tap-to-move with pending undo, forecast then confirm, placeholder shapes, Korean UI text, speed and auto-battle; svelte-check (TS 6) + Biome for `.svelte` | [0033](../adr/0033-p0-sandbox-client-defaults.md) |
| Enemy behaviour | Scenario units carry standing AI orders written as gambits (hold, heal then hold) | [0034](../adr/0034-scenario-ai-orders.md) |
| Conditions | Buffs and debuffs stack, each further stack worth half; every stack's duration is rolled on landing and shown | [0035](../adr/0035-stacking-conditions-rolled-durations.md) |
| P0 skills | 13 skills (10 for 책사, one class skill each for 보병, 기병, 무도가), 8 conditions; poison never below 1 HP | [0036](../adr/0036-p0-skills-and-conditions.md) |
| Protocol | Plain WebSocket + typed JSON; numbered batches; reconnect by `hello` with the last batch seen; no `Prng` sent to clients | [0037](../adr/0037-websocket-json-protocol.md) |
| P1 identity | Guests: display name + secret device key; invite links; accounts in P2 | [0038](../adr/0038-guest-identity-device-key.md) |
| Arena armies | Snake draft of six officers each from a shared pool, normalized level | [0039](../adr/0039-arena-draft.md) |
| Arena clock | 45 s per activation + 3 min reserve; then the AI plays that activation | [0040](../adr/0040-arena-turn-clock.md) |
| P1 defaults | One `MatchDO` per room, room codes, first pick vs first move, 16-officer pool at Lv 20, one mirrored map | [0041](../adr/0041-p1-arena-defaults.md) |
| Classic skin | The owner's own 1998 game exported into a git-ignored folder; dev server only; animated sprites, portraits, terrain and 1998 maps; never built, deployed or published | [0042](../adr/0042-local-classic-skin.md) |
| Command menu | Command menu, skill list and forecast float beside the unit; keyboard and touch | [0043](../adr/0043-floating-command-menu.md) |
| Characters | Rigged 2D chibi figures at tile scale in the map's bird's-eye view; three facings; class looks and side colours; parts painted in code until an artist draws them | [0045](../adr/0045-rigged-chibi-characters.md) |
| Presentation (proposed) | Soft terrain edges, water shimmer, weather and colour grade, cinematic camera, particles per skill school, skill cut-ins, glass-and-gold UI, smoothed classic sprites; our own procedural terrain and tokens without the skin | [0044](../adr/0044-modern-battlefield-presentation.md) |

## Roadmap

```mermaid
flowchart LR
  P0["P0 rules engine<br/>AI + battle sandbox"] --> P1["P1 Arena<br/>MatchDO referee, PWA<br/>friends play PvP"]
  P1 --> P2["P2 Story ch.1<br/>event VM, dialogue,<br/>solo re-simulation"]
  P2 --> P3["P3 Warlord Campaign<br/>WorldDO, clock, anchors,<br/>households (private)"]
  P3 --> P4["P4 Living History<br/>season 1 (public)"]
  P4 --> P5["P5 Raids, Duels/Board"]
```

Exit criteria per phase are in [ADR 0015](../adr/0015-delivery-order-engine-then-arena.md).
**Today:** P0 is done (rules engine with conditions; 6 classes, 12 terrains, 13
skills, 8 conditions; a playable sandbox). P1 runs locally end to end: rooms by
invite link, the six-officer draft, deployment, live alternating battles refereed
by a `MatchDO` with a 45-second clock, reconnects, and a PWA. Next for P1: deploy
to Cloudflare so friends can play, then their feedback.

## Open questions (next design sessions)

| # | Question | Current leaning |
|---|---|---|
| 2 | Name of the game | Working name `tk`; the remake is built in this repository ([0017](../adr/0017-clear-this-repository.md)) |
| 5 | Accounts and sign-in | P1 uses guest device keys ([0038](../adr/0038-guest-identity-device-key.md)); passkeys with `PlayerDO` in P2 |
| 6 | Story scope | Whose story (조조 only, several lords' routes, or the player's officer) |
| 7 | Equipment ([RULES.md §11](RULES.md#11-still-to-decide)) | Stats and classes are decided ([0020](../adr/0020-five-stats-with-distinct-roles.md), [0021](../adr/0021-class-families-and-tiers.md)); the P1 Arena uses no equipment, officers at Lv 20 ([0039](../adr/0039-arena-draft.md), [0041](../adr/0041-p1-arena-defaults.md)) |
| 8 | Remaining techniques ([MODERN_TECHNIQUES.md](MODERN_TECHNIQUES.md)) | AI profiles and lookahead next; speed/skip in the client; party level cap and TP later |
| 9 | Art pipeline | Direction chosen: rigged 2D chibi at tile scale ([0045](../adr/0045-rigged-chibi-characters.md)); still open: the rig format (Spine, which needs editor licences, or our own parts-and-bones JSON) and who draws |
| 10 | Languages | Korean first; English next |
| 11 | Monetization (if public) | Out of scope until a public launch |

## Reference material in the archive (commit `dabaf0a2`)

| What | Where |
|---|---|
| Original 22 battle modes, server patterns, online flow | [`docs/server/GAME_SERVER_PRACTICES.md`](https://github.com/dudueul/caocao/blob/dabaf0a2/docs/server/GAME_SERVER_PRACTICES.md) |
| Scaling memo (seasons with many players) | [`docs/server/HYPERSCALE_ROADMAP.md`](https://github.com/dudueul/caocao/blob/dabaf0a2/docs/server/HYPERSCALE_ROADMAP.md) |
| 239 data-table schemas + localization | [`data/tables/`](https://github.com/dudueul/caocao/blob/dabaf0a2/data/tables/README.md) |
| 1998 officers, classes, items, terrain, maps | [`data/reference/ekd5/`](https://github.com/dudueul/caocao/blob/dabaf0a2/data/reference/ekd5/README.md) |
| 1998 file formats and event scripts (EEX) | [`docs/re/EKD5_FORMATS.md`](https://github.com/dudueul/caocao/blob/dabaf0a2/docs/re/EKD5_FORMATS.md) |
| Formulas, abilities, AI, event vocabulary (names, signatures, code) | [`extracted/il2cpp/dump.cs`](https://github.com/dudueul/caocao/blob/dabaf0a2/extracted/il2cpp/dump.cs), [`analysis/decompiled/`](https://github.com/dudueul/caocao/tree/dabaf0a2/analysis/decompiled) |
