# Remake: a Three Kingdoms grid-tactics game, built from scratch

> Living document. Decisions are recorded in [`docs/adr/`](../adr/README.md);
> this folder describes the **current** design. Last updated 2026-10-14
> (decisions [0001](../adr/0001-record-architecture-decisions.md)–[0014](../adr/0014-scoped-progression-fair-arena.md)).

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

## Open questions (next design sessions)

| # | Question | Current leaning |
|---|---|---|
| 1 | Delivery order of modes (roadmap) | Rules engine + battle sandbox → Story chapter 1 → Arena → private campaign → public season → Raids, Duels |
| 2 | Where the new code lives | Separate repository; this one stays the reference archive |
| 3 | Menu/UI framework | DOM overlay (a light framework) over the Pixi canvas |
| 4 | Network protocol | Plain WebSocket + typed JSON (no Socket.IO) with DO WebSocket hibernation |
| 5 | Accounts and sign-in | Friends: magic link or passkeys; stores later |
| 6 | Story scope | Whose story (조조 only, several lords' routes, or the player's officer) |
| 7 | Stat and class system | 5 stats like 조조전 (STR, CMD, INT, AGI, LUCK), class promotions, equipment |
| 8 | Rules reference from the APK | Decompile about 50–100 formula, ability and AI functions into an engine-neutral spec |
| 9 | Art pipeline | 96 px tiles (2×), palette and atlas tooling |
| 10 | Languages | Korean first; English next |
| 11 | Monetization (if public) | Out of scope until a public launch |

## Reference material in this repository

| What | Where |
|---|---|
| Original 22 battle modes, server patterns, online flow | [`docs/server/GAME_SERVER_PRACTICES.md`](../server/GAME_SERVER_PRACTICES.md) |
| Scaling memo (seasons with many players) | [`docs/server/HYPERSCALE_ROADMAP.md`](../server/HYPERSCALE_ROADMAP.md) |
| 239 data-table schemas + localization | [`data/tables/`](../../data/tables/README.md) |
| 1998 officers, classes, items, terrain, maps | [`data/reference/ekd5/`](../../data/reference/ekd5/README.md) |
| 1998 file formats and event scripts (EEX) | [`docs/re/EKD5_FORMATS.md`](../re/EKD5_FORMATS.md) |
| Formulas, abilities, AI, event vocabulary (names, signatures, code) | `extracted/il2cpp/dump.cs`, `analysis/decompiled/` |
