# tk: a Three Kingdoms grid-tactics game (working name)

A from-scratch successor to 조조전 and its 2018 mobile remake. Grid-tactics
battles drive every mode: Story chapters, Arena PvP, co-op Raids, and **Living
History**, where friends found households in a 120-year history that plays out
in 120 days. Web-first (PWA), with the same build wrapped for PC and mobile later.

**Status:** 32 ADRs. **P0** rules engine in `packages/rules`: movement with
zones of control, combat, stratagems with fortune twists, both turn policies, a
baseline AI with danger zones and gambits, break and push/fire hazards. P0
content in `packages/content`: six classes, twelve terrains, four stratagems,
and the 오소 습격 sandbox. 73 tests, including AI-vs-AI soaks and golden replays.

| Start here | |
|---|---|
| [`docs/remake/README.md`](docs/remake/README.md) | Vision, modes, decision map, roadmap, open questions |
| [`docs/remake/ARCHITECTURE.md`](docs/remake/ARCHITECTURE.md) | Stack, code layout, rules engine, online battles, servers, data |
| [`docs/remake/RULES.md`](docs/remake/RULES.md) | Battle and character rules (stats, attack resolution, classes, terrain, stratagems) |
| [`docs/remake/BENCHMARK.md`](docs/remake/BENCHMARK.md) | The 2018 game's battle formulas (decompiled, in our words) and lessons |
| [`docs/remake/WORLD_ENGINE.md`](docs/remake/WORLD_ENGINE.md) | Living History / Warlord Campaign design |
| [`docs/adr/`](docs/adr/README.md) | One record per decision |

```
packages/
  rules/      pure, deterministic battle rules (shared by client and server)  ← code
  content/    game data as typed modules + validators                       ← code
  protocol/   client-server messages                                         (P1)
apps/
  client/     Vite + PixiJS battlefield + Svelte 5 UI overlay, PWA             (P0)
  server/     Cloudflare Worker + Durable Objects                            (P1)
  tools/      map / event / anchor editors                                   (P2)
docs/
  remake/     current design documents (with diagrams)
  adr/        architecture decision records
scripts/      check-determinism.mjs (run by bun run check)
```

## Develop

Requires [Bun](https://bun.sh) 1.4 (version pinned in `.bun-version`).

```bash
bun install
bun run check      # typecheck (tsc) → lint (Biome) → determinism guard → bun test
bun run format     # Biome format + safe fixes
```

## Archive

The recovery of the 2018 mobile game (APK dumps, recovered Unity project, old
Workers server, 239 data-table schemas, 1998 reference data, protocol and server
memos) lives in this repository's history at commit
[`dabaf0a2`](https://github.com/dudueul/caocao/tree/dabaf0a2). It is a design
reference only. Nothing derived from Koei or Nexon is copied into this tree
([ADR 0002](docs/adr/0002-remake-from-scratch.md), [ADR 0017](docs/adr/0017-clear-this-repository.md)).
