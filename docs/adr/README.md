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
