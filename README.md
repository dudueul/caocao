# tk: a Three Kingdoms grid-tactics game (working name)

A from-scratch successor to 조조전 and its 2018 mobile remake. Grid-tactics
battles drive every mode: Story chapters, Arena PvP, co-op Raids, and **Living
History**, where friends found households in a 120-year history that plays out
in 120 days. Web-first (PWA), with the same build wrapped for PC and mobile later.

**Status:** design settled for the first phases (18 ADRs); **P0** (rules
engine) has started: `packages/rules` has a seeded PRNG and a canonical state hash.

| Start here | |
|---|---|
| [`docs/remake/README.md`](docs/remake/README.md) | Vision, modes, decision map, roadmap, open questions |
| [`docs/remake/ARCHITECTURE.md`](docs/remake/ARCHITECTURE.md) | Stack, code layout, rules engine, online battles, servers, data |
| [`docs/remake/WORLD_ENGINE.md`](docs/remake/WORLD_ENGINE.md) | Living History / Warlord Campaign design |
| [`docs/adr/`](docs/adr/README.md) | One record per decision |

```
packages/
  rules/      pure, deterministic battle rules (shared by client and server)  ← code
  content/    game data + schemas                                            (P0)
  protocol/   client-server messages                                         (P1)
apps/
  client/     Vite + PixiJS battle view and UI, PWA                          (P0)
  server/     Cloudflare Worker + Durable Objects                            (P1)
  tools/      map / event / anchor editors                                   (P2)
docs/
  remake/     current design documents (with diagrams)
  adr/        architecture decision records
scripts/      check-determinism.mjs
```

## Develop

Requires Node ≥ 22.12.

```bash
npm ci
npm run check      # typecheck → lint (Biome) → determinism guard → tests (Vitest)
npm run format     # Biome format + safe fixes
```

## Archive

The recovery of the 2018 mobile game (APK dumps, recovered Unity project, old
Workers server, 239 data-table schemas, 1998 reference data, protocol and server
memos) lives in this repository's history at commit
[`dabaf0a2`](https://github.com/dudueul/caocao/tree/dabaf0a2). It is a design
reference only. Nothing derived from Koei or Nexon is copied into this tree
([ADR 0002](docs/adr/0002-remake-from-scratch.md), [ADR 0017](docs/adr/0017-clear-this-repository.md)).
