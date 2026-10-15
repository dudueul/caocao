# Remake architecture

> Living document; reflects ADRs [0003](../adr/0003-turn-structure.md)–[0041](../adr/0041-p1-arena-defaults.md).
> Items marked **(proposed)** are leanings, not decisions yet.

## 1. System context

Everything is TypeScript ([0006](../adr/0006-typescript-everywhere.md)). The
browser build is the only client build; PC and mobile builds wrap it
([0005](../adr/0005-web-first-distribution.md)). Durable Objects (DOs) own
every piece of live state and run the same rules package as the client
([0004](../adr/0004-server-authoritative-referee.md)).

```mermaid
flowchart LR
  subgraph Clients
    W["Browser / PWA<br/>desktop + phones"]
    PC["PC build<br/>Tauri shell"]
    MOB["iOS / Android<br/>store wrapper, later"]
  end
  subgraph CF["Cloudflare"]
    ST["Static assets<br/>app + atlases"]
    WK["Worker<br/>HTTP API + WebSocket upgrade"]
    subgraph DO["Durable Objects"]
      PL["PlayerDO<br/>account, Story roster"]
      LB["LobbyDO<br/>presence, rooms, chat"]
      MA["MatchDO<br/>battle referee"]
      WO["WorldDO<br/>season / campaign"]
      BO["BossDO<br/>raid boss HP"]
    end
    D1[("D1<br/>ladders, season archive,<br/>account index")]
    R2[("R2<br/>replays, uploads")]
  end
  W --> ST
  W --> WK
  PC --> WK
  MOB --> WK
  WK --> PL
  WK --> LB
  WK --> MA
  WK --> WO
  WK --> BO
  WO -->|"armies meet"| MA
  MA -->|"result"| WO
  PL --> D1
  WO --> D1
  MA --> R2
```

## 2. Code layout

A TypeScript monorepo in this repository ([0017](../adr/0017-clear-this-repository.md)),
with the toolchain of [0019](../adr/0019-bun-toolchain.md) (Bun for install, tests and
scripts; TypeScript 7; Biome). **Today `packages/rules`** (seeded PRNG, canonical
state hash, and the P0 battle engine below), **`packages/content`** (the P0
ruleset, sandbox scenario and Arena pool), **`packages/protocol`**,
**`apps/server`** (the Arena referee, §4) and **`apps/client`** (sandbox and
Arena, §7) have code; `apps/tools` holds a README describing its phase.

```mermaid
flowchart BT
  rules["packages/rules<br/>pure, deterministic:<br/>state, commands, events,<br/>formulas, AI, event VM"]
  content["packages/content<br/>officers, classes, items, maps,<br/>scenarios, anchors (typed TS + validators)"]
  protocol["packages/protocol<br/>client-server messages"]
  client["apps/client<br/>PixiJS battle view + UI"]
  server["apps/server<br/>Worker + Durable Objects"]
  tools["apps/tools<br/>map / event / anchor editors"]
  content --> rules
  rules --> client
  rules --> server
  protocol --> client
  protocol --> server
  content --> tools
  rules --> tools
```

| Package | Allowed to use | Never uses |
|---|---|---|
| `rules` | `content` types, its own PRNG | DOM, network, `Date`, `Math.random`, floats in outcomes, `Map`/`Set` iteration order in outcomes |
| `content` | `rules` types and helpers; typed data and validators ([0032](../adr/0032-content-as-typed-modules.md)) | the same bans as `rules`: clock, I/O, `Math.random`, floats |
| `client` | `rules`, `content`, `protocol`, PixiJS, Svelte 5 | deciding outcomes of online battles; building commands that `legalCommands()` didn't list |
| `protocol` | `rules` types | anything but message shapes, validators and redaction |
| `server` | `rules`, `protocol`, Workers/DO APIs, D1, R2 | rendering |

## 3. Rules engine

The rules package is a pure function from state and command to new state and
events. Online, only the DO calls `apply`, and clients only receive events
([0004](../adr/0004-server-authoritative-referee.md)).

```mermaid
flowchart LR
  C["Command<br/>move / attack / skill /<br/>item / wait / endPhase"] --> V{"validate<br/>legal for seat + cursor?"}
  V -->|no| E["reject<br/>with reason"]
  V -->|yes| AP["apply<br/>formulas + PRNG"]
  AP --> T["triggers<br/>event scripts, conditions"]
  T --> TP["turn policy<br/>next activation"]
  TP --> OUT["new state + events[]<br/>+ state hash"]
```

```ts
// packages/rules: public surface (sketch)
type Command =
  | { kind: "move"; unit: UnitId; to: Cell }
  | { kind: "attack"; unit: UnitId; target: UnitId }
  | { kind: "skill"; unit: UnitId; skill: SkillId; target: Cell }
  | { kind: "item"; unit: UnitId; item: ItemId; target: UnitId }
  | { kind: "wait"; unit: UnitId }
  | { kind: "endPhase" };

interface TurnPolicy {                    // ADR 0003
  current(s: BattleState): Cursor;        // whose activation, which units may act
  advance(s: BattleState): BattleState;   // after an activation or phase end
}
// SidePhases (Story, Raids, vs AI) | AlternatingActivations (Arena, World battles)

function legalCommands(s: BattleState, seat: SeatId): Command[];
function apply(s: BattleState, c: Command, rng: Prng):
  { ok: true; state: BattleState; events: BattleEvent[] } | { ok: false; reason: string };
function hash(s: BattleState): string;   // replay and parity checks
```

**Determinism rules:** integer or fixed-point (×1000) maths; one seeded PRNG
(for example sfc32) carried in the state; stable ordering (units sorted by id);
no wall-clock time. Golden-replay tests run in CI.

**Implemented (P0)** in `packages/rules/src/`:

| Module | Responsibility |
|---|---|
| `types.ts` | Ruleset (classes, terrains, affinity, stratagems, tuning) and battle state, commands, events |
| `board.ts` | Tile, unit and range queries; zones of control |
| `move.ts` | `reachable()`: Dijkstra over family terrain costs with soft ZOC ([0026](../adr/0026-soft-zone-of-control.md)) |
| `combat.ts` | Hit, critical, relation matrix, named damage pipeline, strike → counter → second strike, officer fate; `previewAttack()` with breakdown |
| `stratagem.ts` | MP, element × terrain × weather, condition riders and cleansing, fortune twists, push collisions, ignition; `previewStratagem()` |
| `conditions.ts` | Condition stacks ([0035](../adr/0035-stacking-conditions-rolled-durations.md)): discounted stacking, rolled durations, blocks, ATK/DEF/hit/move modifiers, damage over time, end-of-turn countdown |
| `battle.ts` | `createBattle()`, `legalCommands()`, `apply()`, both turn policies, round effects (healing, burning), victory |
| `ai.ts` | Baseline utility AI with a threat map ([0027](../adr/0027-p0-engine-defaults.md)); the approach pull follows a movement-cost field to the nearest attack position, so units route around rivers and walls; casts that achieve nothing are never chosen, and MP has a cost; `dangerTiles()` and gambits ([0030](../adr/0030-modern-techniques-adopted.md)) |
| `sim.ts` | `runBattle()` (AI vs AI), `replay()` (command-log verification) |
| `prng.ts`, `hash.ts`, `math.ts`, `tuning.ts` | Seeded sfc32, canonical hash, integer helpers, default tuning, `forMode()`, `triangleRelations()` |

Tests (`packages/rules/test/`, 77 cases) cover the RULES.md worked
examples, movement and ZOC, turn policies and validation, stratagem
formulas, previews and twist rates, conditions (stacking, rolled durations, blocks,
poison, cleansing), golden replays, and an AI-vs-AI soak that checks every
invariant after every step.

**Content (P0)** in `packages/content/src/` ([0032](../adr/0032-content-as-typed-modules.md)):

```mermaid
flowchart LR
  CL["classes.ts<br/>6 tier-1 classes"] --> RS["ruleset.ts<br/>P0_RULES + P0_RULES_HASH"]
  TE["terrain.ts<br/>12 terrains, 6 × 12 affinity"] --> RS
  SG["stratagems.ts<br/>13 skills"] --> RS
  CO["conditions.ts<br/>8 buffs and debuffs"] --> RS
  OF["officers.ts<br/>our own ratings"] --> SC["scenarios.ts<br/>scenarioSetup()"]
  MP["maps.ts<br/>text rows"] --> SC
  RS --> VA["validate.ts<br/>validateRuleset / validateScenario"]
  SC --> VA
  SC -->|"BattleSetup"| CB["rules.createBattle()"]
  RS --> CB
```

Its tests (17 cases) validate the ruleset and every scenario, show that broken
content is reported, check the P0 skill and condition sets, the medium number
scale and the defenders' hold orders, and run an AI-vs-AI soak of the 오소 sandbox
under both turn policies. In 40 seeded AI-vs-AI runs per policy, battles end in
15–16 rounds; the raiders win 14–26 under side phases and 27–13 alternating
([0034](../adr/0034-scenario-ai-orders.md), [0036](../adr/0036-p0-skills-and-conditions.md)).

**Content-driven parts**, each benchmarked on the recovered game ([0002](../adr/0002-remake-from-scratch.md)):

| Part | Model | Benchmark in this repo |
|---|---|---|
| Damage and hit formulas | Pure functions of attacker, defender, terrain, conditions; specified in [RULES.md](RULES.md) | `UnitCommonFunc.GetRawAttackDamage`, `GetAttackSuccessRate` (ARM64, decompilable) |
| Skills and special abilities | Data (shape, range, effect list) + a few code hooks | 234 `Skill*` and 475 `SpAction###` classes |
| Conditions (buffs and debuffs) | Data: type, stacking, duration, cancel rules | `Conditions` table schema |
| AI | Utility scoring: candidate actions × weighted features, per-officer tendency profile, PRNG tie-break | `nAI.*` scoring, `AITendencies` (cDistance, cGoal, cTarget, cDefeat …) |
| Battle events | Triggers (turn start, unit defeated, area entered, HP below) → action list (dialogue, spawn, retreat, change objective) | 216 `EventCmd` types; 1998 EEX scripts |

## 4. Online battles

A `MatchDO` per battle is the referee. Each **seat** is one corps with a
controller, `player` or `ai` ([0012](../adr/0012-household-corps-seats.md)).
Auto-join swaps a seat's controller at the start of its next activation
([0011](../adr/0011-world-pacing-and-auto-join.md)).

**Implemented (P1 Arena)** in `apps/server` and `packages/protocol`
([0037](../adr/0037-websocket-json-protocol.md)–[0041](../adr/0041-p1-arena-defaults.md)).
One `MatchDO` per room code runs the whole room:

```mermaid
stateDiagram-v2
  [*] --> waiting: first hello (create)
  waiting --> draft: second player joins
  draft --> draft: pick (30 s, else automatic)
  draft --> deploy: 12th pick
  deploy --> battle: both ready, or 60 s
  battle --> battle: command, or the clock's AI move
  battle --> over: last side standing / round 20
  draft --> over: concede
  deploy --> over: concede
  battle --> over: concede
  over --> [*]: 24 h without activity
```

```mermaid
sequenceDiagram
  autonumber
  participant A as Seat A (조조팬)
  participant M as MatchDO
  participant B as Seat B (유비팬)
  A->>M: hello {room, key, name, create: true}
  M-->>A: welcome {seat A, room}
  B->>M: hello {room, key, name, create: false}
  M-->>B: welcome {seat B, room: draft}
  M-->>A: room {draft}
  B->>M: pick 여포
  M-->>A: room (each seat's own view)
  M-->>B: room
  Note over A,B: … 12 picks, deploy, both ready …
  M-->>A: snapshot {state without Prng, clock}
  M-->>B: snapshot
  A->>M: command {act 관우 → attack 장비}
  M->>M: validate + apply (rules), roll the secret Prng
  M-->>A: batch {seq 1, events, state, clock}
  M-->>B: batch {seq 1, …}
  Note over M: B's 45 s and reserve run out
  M->>M: alarm: chooseCommand for seat B
  M-->>A: batch {seq 2, by: ai, …}
  M-->>B: batch {seq 2, by: ai, …}
  B->>M: hello {…, lastSeq: 1} after a dropped connection
  M-->>B: welcome + batch {seq 2} (or a snapshot)
```

- **Clock** ([0040](../adr/0040-arena-turn-clock.md)): 45 s per activation, then
  a 3-minute reserve; one Durable Object alarm for the next deadline; at the
  deadline the AI plays that activation.
- **Secrets:** the battle seed comes from `crypto.getRandomValues`; states sent
  down carry no `Prng`; seats are claimed by SHA-256 hashes of device keys
  ([0038](../adr/0038-guest-identity-device-key.md)); each seat sees only its own
  deploy arrangement until the battle.
- **Persistence:** the room data (start state, current state, command log, clock)
  is stored after every change; recent batches live in memory, so a reconnect
  after the object slept gets a snapshot instead.
- **Reconnect and spectate:** snapshot plus missed batches; replays are the
  command log plus seed, stored in R2 later.
- **Solo battles (Story, Raids):** the client runs `apply` locally with a
  server-issued seed, then uploads the command log. `PlayerDO` re-simulates
  it before granting rewards.

```mermaid
sequenceDiagram
  participant C as Client
  participant P as PlayerDO
  C->>P: startBattle {chapter 3, battle 2}
  P-->>C: battleId + seed + roster snapshot
  Note over C: plays locally with the same rules package
  C->>P: finishBattle {battleId, commands[], claimed result}
  P->>P: re-simulate, compare result + hash
  P-->>C: rewards (or reject)
```

## 5. Server topology

| Durable Object | Key | Owns | Notes |
|---|---|---|---|
| `PlayerDO` | account id | profile, Story roster, unlocks, legacy, cosmetics | re-simulates solo battles (P2) |
| `LobbyDO` | region / friend group | presence, room lists, chat | later; P1 rooms are joined by link |
| `MatchDO` | room code | one room: seats, draft, deploy, the battle's authoritative state, clock | **implemented (P1)**: WebSocket Hibernation, alarms; replays to R2 later |
| `WorldDO` | season id / campaign id | map, households, NPC lords, clock, anchors | alarms for monthly ticks ([WORLD_ENGINE.md](WORLD_ENGINE.md)) |
| `BossDO` | raid id | shared boss HP, contribution log | atomic damage application |

**Protocol** ([0037](../adr/0037-websocket-json-protocol.md)): plain WebSocket with
typed JSON messages from `packages/protocol`, validated on arrival. The Worker
routes `/api/rooms/CODE/ws` to the room's `MatchDO` and serves the client build
(Workers Static Assets) for everything else.

## 6. Data and storage

| Data | Where | Why |
|---|---|---|
| Game content (officers, classes, items, maps, scenarios, anchors) | `packages/content`, typed modules bundled into the client and the Worker; `P0_RULES_HASH` versions it | same data in the client and the DOs |
| Account, Story roster, unlocks, legacy ([0014](../adr/0014-scoped-progression-fair-arena.md)) | `PlayerDO` storage (SQLite-backed DO) | single writer per account |
| World state (households, armies, cities, clock) | `WorldDO` storage | single writer per world |
| Ladders, season archives, hall of fame | D1 | queries across many entities |
| Replays, user uploads | R2 | large blobs |

## 7. Client

```mermaid
flowchart LR
  IN["input<br/>tap / drag / keys"] --> CMD["command builder<br/>legalCommands() for hints"]
  CMD -->|online| NET["protocol client<br/>WebSocket"]
  CMD -->|solo| RL["rules.apply()"]
  NET --> EV["event stream"]
  RL --> EV
  EV --> ST["view state"]
  EV --> AQ["animation queue"]
  AQ --> PX["PixiJS battle view<br/>map, units, effects"]
  ST --> UI["Svelte 5 DOM overlay<br/>menus, unit panels, dialogue"]
```

**Implemented (P0 sandbox, P1 Arena)** in `apps/client/src/` ([0031](../adr/0031-svelte-dom-overlay.md), [0033](../adr/0033-p0-sandbox-client-defaults.md), [0037](../adr/0037-websocket-json-protocol.md)–[0041](../adr/0041-p1-arena-defaults.md)):

```mermaid
flowchart LR
  H["#/ home<br/>name, create, join"] -->|create / join| R["#/r/CODE<br/>OnlineRoom"]
  H --> S["#/sandbox · #/demo<br/>local Session"]
  R --> L["lobby<br/>invite link"]
  L --> D["draft<br/>snake picks"]
  D --> P["deploy<br/>swap on mini-map"]
  P --> B["battle<br/>BattleScreen"]
  S --> B
```

| Module | Responsibility |
|---|---|
| `lib/session.svelte.ts` | The battle store: rules state, command log, selection, pending move, aim and target, settings; every option derived from `legalCommands()`; runs AI activations with the scenario's orders locally, or sends commands to the referee and animates its batches online |
| `lib/online.svelte.ts` | `OnlineRoom`: the room socket, `hello` with the last batch seen, reconnect with backoff, room view, clock and server-time offset |
| `lib/identity.ts` | Guest device key and display name in `localStorage` ([0038](../adr/0038-guest-identity-device-key.md)) |
| `screens/*.svelte` | Home, sandbox, room (lobby, draft, deploy) and the shared battle screen |
| `lib/field.ts` | Pixi renderer: terrain, overlays (reach, strike range, targets, danger), units with ▲/▼ condition marks, effects; animates each command's events, then shows the new state. Placeholder shapes, or the local classic skin's tiles, 1998 map pictures and animated sprites ([0042](../adr/0042-local-classic-skin.md)) |
| `lib/skin.svelte.ts`, `lib/path.ts`, `lib/classic-battle.ts` | The classic skin loader (dev server only), the walking route for move animations, and sandbox battles on a 1998 map |
| `lib/fx/*.ts` | Presentation ([0044](../adr/0044-modern-battlefield-presentation.md)): effect textures, a particle system, effect recipes (strikes, defeats, auras, one look per skill school), weather and ambience, terrain painting with soft edges (our procedural art or the skin's tiles), our medallion tokens |
| `lib/text.ts` | Korean UI strings and event-log lines |
| `components/*.svelte` | Top bar, unit card (condition stacks with turns left), command menu with the skill list and forecast floating beside the unit ([0043](../adr/0043-floating-command-menu.md)), skill cut-in, portraits, tile info, battle log |

The battle view draws in layers ([0044](../adr/0044-modern-battlefield-presentation.md));
the top of the diagram is drawn last:

```mermaid
flowchart TB
  subgraph stage["Pixi stage, screen space"]
    direction TB
    FL["flash: critical hits, lightning"]
    VG["vignette"]
    RS["rain streaks"]
    subgraph world["world: camera zoom, pan and shake, colour grade by weather"]
      direction TB
      LB["damage numbers, selection chevron"]
      AM["weather: light shafts, cloud shadows, leaves (clipped to the map)"]
      PT["particles, with bloom"]
      UN["units by row: shadow, team ring, sprite or token, HP bar"]
      OV["overlays: reach, targets, danger, pulsing choice"]
      FG["fire glow, grid"]
      ED["soft terrain edges and structures (cached)"]
      WT["water, with shimmer"]
      GR["ground tiles or a 1998 map picture (cached)"]
    end
  end
```

Each command's events play as a short scene, then the field shows the new state:

```mermaid
flowchart LR
  E["events of one command"] --> MV["moved: walk the route,<br/>dust at each step"]
  E --> ST["strike: camera in, swing,<br/>impact (slash, sparks, shake,<br/>drain bar, number)"]
  E --> SK["stratagem: cut-in, sigil,<br/>the school's particles"]
  E --> DF["defeated: flicker,<br/>smoke and embers"]
  MV --> OUT["camera out,<br/>show the new state"]
  ST --> OUT
  SK --> OUT
  DF --> OUT
```

```mermaid
stateDiagram-v2
  [*] --> Idle
  Idle --> Selected: tap own ready unit
  Selected --> Idle: tap elsewhere / Esc
  Selected --> Pending: tap a reachable tile
  Pending --> Pending: tap another reachable tile
  Pending --> Selected: undo move / Esc
  Pending --> Skills: 책략 ▸
  Skills --> Pending: ◂ / Esc
  Skills --> Aiming: pick a skill
  Pending --> Aiming: attack
  Pending --> Applying: wait
  Aiming --> Pending: back / Esc
  Aiming --> Forecast: tap a target
  Forecast --> Aiming: back / Esc
  Forecast --> Applying: tap target again / confirm
  Applying --> AITurns: apply() + animate events
  AITurns --> Idle: human's turn again
  AITurns --> [*]: battle over
```

Until confirmation nothing leaves the store, so moves undo freely
([0028](../adr/0028-undo-moves-until-action.md)). `?demo` (or `#demo`) has the AI play both
sides, battle after battle. Headless Chromium tests drive the
real UI through `?debug` (store on `window.tk`) and the canvas's `data-geometry`.

- The battle view only consumes events, so solo and online play share the same renderer path.
- A **danger toggle** tints `dangerTiles()`; the forecast shows the damage
  breakdown, `×2`, counter and *breaks* ([0030](../adr/0030-modern-techniques-adopted.md)).
- Speed controls (2×/3×), skip and auto-battle for cleared maps are client
  defaults; auto-battle runs `chooseCommand` with the player's gambits.
- Move undo is client-side only: the unit is shown on candidate tiles with exact
  previews, and nothing is sent until an action is confirmed ([0028](../adr/0028-undo-moves-until-action.md)).
- **PWA** ([0005](../adr/0005-web-first-distribution.md)): a manifest with our own icon,
  and a service worker that serves hashed build files from its cache and the page
  network-first, so the app opens offline once visited. Room sockets are never cached.
- Assets will stream per chapter or map once art exists.

## 8. Testing and budgets

| Check | How |
|---|---|
| Determinism | Golden replays (seed + commands → expected hash) run in CI on Node and in a Workers runtime |
| Rules correctness | Unit tests per formula; property tests (no unit on an illegal cell, HP never negative …) |
| Balance and AI | Headless AI-vs-AI soak runs of thousands of battles in CI; win-rate reports per class |
| Server CPU | Target under 10 ms per command and under 200 ms per AI activation in a DO ([0004](../adr/0004-server-authoritative-referee.md)) |
| Client load | First load ≤ 10 MB, interactive under 3 s on a mid-range phone ([0005](../adr/0005-web-first-distribution.md)); the P0 sandbox build is about 200 KB of JavaScript gzipped (rules, content, Svelte and Pixi) |
| Client UI | `svelte-check` (types in markup) and Biome in `bun run check`; headless-Chromium scripts play a sandbox battle and a two-browser Arena match (create, join, draft, deploy, moves, reload, concede) through the UI before releases |
| Live server | `apps/server/scripts/smoke.ts` plays whole matches over real WebSockets against `wrangler dev` (local workerd), including a reconnect; with `CLOCK_SCALE` the alarms hand an idle seat's turns to the AI. The room engine itself is unit-tested with a fake clock |
