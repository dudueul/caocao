# Architecture Decision Records

One file per decision for the **remake** (a new Three Kingdoms grid-tactics game
built from scratch; see [`docs/remake/`](../remake/README.md)). Each record says
what was decided, why, what else was considered, and when to revisit it.

- **Numbering:** `NNNN-short-title.md`, never reused or renumbered.
- **Status:** `Proposed` → `Accepted` → (`Superseded by NNNN` | `Deprecated`).
  Records are not edited after acceptance except for status and links. A change of mind is a new record.
- **Template:** [`template.md`](template.md).
- **Process:** every decision made in a design session gets a record in the same
  commit, and the technical documents in `docs/remake/` are updated to match.

| # | Decision | Status | Date |
|---|---|---|---|
| [0001](0001-record-architecture-decisions.md) | Record architecture decisions as ADRs | Accepted | 2026-10-14 |
| [0002](0002-remake-from-scratch.md) | Build the remake from scratch; the recovered project is a reference archive | Accepted | 2026-10-14 |
| [0003](0003-turn-structure.md) | Side phases in PvE, alternating single-unit activations in PvP | Accepted | 2026-10-14 |
| [0004](0004-server-authoritative-referee.md) | Server-authoritative referee on Durable Objects with one shared rules engine | Accepted | 2026-10-14 |
| [0005](0005-web-first-distribution.md) | Web-first (PWA) plus a PC build for friends; store wrappers later | Accepted | 2026-10-14 |
| [0006](0006-typescript-everywhere.md) | TypeScript everywhere; the rules engine is one pure TS package | Accepted | 2026-10-14 |
| [0007](0007-top-down-2d-pixijs.md) | Top-down square-grid 2D art, 3 drawn facings, PixiJS renderer | Accepted | 2026-10-14 |
| [0008](0008-modes-as-main-menu-entries.md) | Every mode is its own main-menu entry on one battle engine | Accepted | 2026-10-14 |
| [0009](0009-living-history-anchored-history.md) | Living History: anchored history with what-ifs, 120 years in 120 days, 3 seasons a year | Accepted | 2026-10-14 |
| [0010](0010-lineage-households.md) | Death passes control to an heir (households and lineage) | Accepted | 2026-10-14 |
| [0011](0011-world-pacing-and-auto-join.md) | World pacing: check-ins, prime-time anchor battles, auto-join on connect | Accepted | 2026-10-14 |
| [0012](0012-household-corps-seats.md) | Players control a household corps (one seat per corps) in shared battles | Accepted | 2026-10-14 |
| [0013](0013-one-world-engine.md) | Living History and Warlord Campaign share one World engine | Accepted | 2026-10-14 |
| [0014](0014-scoped-progression-fair-arena.md) | Scoped progression per mode, with a level-normalized Arena | Accepted | 2026-10-14 |
| [0015](0015-delivery-order-engine-then-arena.md) | Build order: rules engine, then Arena, Story, private World, public seasons | Accepted | 2026-10-14 |
| [0016](0016-new-repository.md) | The remake lives in a new repository; this one stays the reference archive | Superseded by 0017 | 2026-10-14 |
| [0017](0017-clear-this-repository.md) | Clear this repository and build the remake in it; the archive is commit `dabaf0a2` | Accepted | 2026-10-14 |
| [0018](0018-toolchain.md) | Toolchain: npm workspaces, TypeScript 7, Vitest, Biome, determinism guard | Superseded by 0019 | 2026-10-14 |
| [0019](0019-bun-toolchain.md) | Bun for install, tests and scripts; TypeScript 7 and Biome stay | Accepted | 2026-10-14 |
| [0020](0020-five-stats-with-distinct-roles.md) | Officers have five stats, each with one distinct role | Accepted | 2026-10-14 |
| [0021](0021-class-families-and-tiers.md) | Twelve class families with three tiers each, plus unique lines | Accepted | 2026-10-14 |
| [0022](0022-medium-number-scale.md) | Medium number scale: HP 100–600, hits of about 20–120 | Accepted | 2026-10-14 |
| [0023](0023-terrain-family-affinity-and-cover.md) | Terrain: per-family movement and affinity, plus cover for everyone | Accepted | 2026-10-14 |
| [0024](0024-stratagems-elements-and-fortune-twists.md) | Stratagems: MP and elements shaped by terrain and weather, with rare fortune twists | Accepted | 2026-10-14 |
| [0025](0025-deterministic-double-attacks-and-counters.md) | Double attacks by a fixed 민첩 threshold; one counter at 800‰ | Accepted | 2026-10-14 |
| [0026](0026-soft-zone-of-control.md) | Soft zones of control, with skirmisher exceptions | Accepted | 2026-10-14 |
| [0027](0027-p0-engine-defaults.md) | Rules-engine defaults chosen while building P0 | Accepted | 2026-10-14 |
| [0028](0028-undo-moves-until-action.md) | Moves can be undone freely until an action is confirmed; nothing after that | Accepted | 2026-10-14 |
| [0029](0029-benchmark-lessons-adopted.md) | Adopt four benchmark lessons: relation matrix, per-mode tuning, named damage pipeline, level-scaled floor | Accepted | 2026-10-14 |
| [0030](0030-modern-techniques-adopted.md) | Adopt danger zones, gambits, break on advantage, and forced movement with hazards | Accepted | 2026-10-14 |
| [0031](0031-svelte-dom-overlay.md) | The client UI is a Svelte 5 DOM overlay on the PixiJS battlefield | Accepted | 2026-10-14 |
| [0032](0032-content-as-typed-modules.md) | Content starts as typed TypeScript modules with our own officer ratings | Accepted | 2026-10-14 |
| [0033](0033-p0-sandbox-client-defaults.md) | Battle sandbox client defaults chosen while building P0 | Accepted | 2026-10-14 |
| [0034](0034-scenario-ai-orders.md) | Scenario units carry standing AI orders, written as gambits | Accepted | 2026-10-14 |
| [0035](0035-stacking-conditions-rolled-durations.md) | Conditions stack with diminishing weight; each stack's duration is rolled when it lands | Accepted | 2026-10-15 |
| [0036](0036-p0-skills-and-conditions.md) | P0 skills and conditions: thirteen skills, eight conditions | Accepted | 2026-10-15 |
| [0037](0037-websocket-json-protocol.md) | Clients talk to the referee over plain WebSocket with typed JSON messages | Accepted | 2026-10-15 |
| [0038](0038-guest-identity-device-key.md) | P1 players are guests: a display name and a secret device key | Accepted | 2026-10-15 |
| [0039](0039-arena-draft.md) | Arena armies are drafted: six officers each from a shared pool | Accepted | 2026-10-15 |
| [0040](0040-arena-turn-clock.md) | Live Arena clock: 45 seconds per activation plus a 3-minute reserve | Accepted | 2026-10-15 |
| [0041](0041-p1-arena-defaults.md) | P1 Arena defaults chosen while building | Accepted | 2026-10-15 |
| [0042](0042-local-classic-skin.md) | A local-only classic skin from the owner's own copy of the 1998 game | Accepted | 2026-10-15 |
| [0043](0043-floating-command-menu.md) | Float the command menu and the forecast beside the unit | Accepted | 2026-10-15 |
