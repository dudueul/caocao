# World engine: Living History and Warlord Campaign

> Living document; reflects ADRs [0009](../adr/0009-living-history-anchored-history.md)–[0013](../adr/0013-one-world-engine.md).
> Numbers marked *tunable* are starting points for playtests.

## 1. Overview

One `WorldDO` per world runs a strategic map of the late Han with **households**
(players and NPCs), **NPC lords** following history, a **world clock**, and
**anchor events**. When armies meet, a grid battle opens in a `MatchDO`
(see [ARCHITECTURE.md §4](ARCHITECTURE.md#4-online-battles)). The same engine
runs public seasons and private campaigns through presets ([0013](../adr/0013-one-world-engine.md)).

```mermaid
flowchart LR
  subgraph WorldDO["WorldDO (one world)"]
    CL["clock<br/>1 month per 2 h"]
    MAP["strategic map<br/>cities, routes, armies"]
    HH["households<br/>players + NPCs"]
    NPC["NPC lords + officers<br/>AI driven"]
    AN["anchor events<br/>+ what-if variants"]
  end
  CL --> MAP
  CL --> AN
  AN --> NPC
  HH -->|"queued orders"| MAP
  NPC -->|"AI orders"| MAP
  MAP -->|"armies meet"| MB["MatchDO<br/>grid battle"]
  MB -->|"result"| MAP
  WorldDO -->|"season archive"| D1[("D1")]
```

## 2. Calendar and clock

**Span 161–280 AD, 1 real day = 1 game year, so day *d* = year 160 + *d*.**
One game month passes every 2 real hours (12 ticks a day, local midnight KST by
default). There are three 120-day seasons per calendar year ([0009](../adr/0009-living-history-anchored-history.md)):

```mermaid
gantt
  title Living History calendar, 2027 (normal year)
  dateFormat YYYY-MM-DD
  axisFormat %b
  section Seasons
  S1 years 161 to 280      :s1, 2027-01-01, 120d
  break                    :crit, b1, after s1, 1d
  S2 years 161 to 280      :s2, after b1, 120d
  break                    :crit, b2, after s2, 1d
  S3 years 161 to 280      :s3, after b2, 120d
  year-end recap           :crit, b3, after s3, 3d
```

| | Normal year | Leap year |
|---|---|---|
| S1 | Jan 1 – Apr 30 | Jan 1 – Apr 29 |
| Break | May 1 | Apr 30 – May 1 |
| S2 | May 2 – Aug 29 | May 2 – Aug 29 |
| Break | Aug 30 | Aug 30 |
| S3 | Aug 31 – Dec 28 | Aug 31 – Dec 28 |
| Recap | Dec 29 – 31 | Dec 29 – 31 |

| Real time | Game time |
|---|---|
| 1 day | 1 year |
| 2 hours | 1 month (one tick) |
| 10 minutes | about 2.5 days |
| 6 h skirmish window | 3 months |

## 3. Anchor events

An anchor is a dated historical event with **preconditions**, a historical
outcome and authored **what-if variants**. Anchors are content (`packages/content`)
evaluated by the World engine.

```mermaid
stateDiagram-v2
  [*] --> Scheduled
  Scheduled --> Announced: one day before its date<br/>preconditions checked (preview)
  Announced --> Firing: date reached
  Firing --> Historical: preconditions hold
  Firing --> Variant: a what-if variant matches
  Firing --> Skipped: nothing matches
  Historical --> Resolving: spawns battles, decrees, deaths
  Variant --> Resolving
  Resolving --> Done: outcome applied<br/>(battle results decide)
  Skipped --> Done
  Done --> [*]
```

```yaml
# content/anchors/guandu.yaml (sketch)
id: guandu
date: { year: 200, month: 2 }          # day 40
pre:
  - alive: [cao_cao, yuan_shao]
  - borders: [cao_cao, yuan_shao]
historical:
  war: { attacker: yuan_shao, defender: cao_cao, fronts: [baima, guandu_camp, wuchao_raid] }
  primeTime: true                       # ADR 0011
variants:
  - id: yuan_shao_unopposed
    when: [alive: yuan_shao, dead: cao_cao]
    effect: { yuan_shao: { expand: [yan_province] } }
```

First-draft anchor list (about 40 planned):

```mermaid
timeline
  title Anchor events, day = year minus 160
  section Prelude
    day 8 (168) : Emperor Ling enthroned
    day 9 (169) : Second Partisan Prohibition
  section Fall of Han
    day 24 (184) : Yellow Turban Rebellion
    day 29 (189) : Dong Zhuo seizes Luoyang
    day 30 (190) : Coalition against Dong Zhuo
    day 32 (192) : Dong Zhuo assassinated
    day 36 (196) : Emperor Xian moved to Xu
    day 38 (198) : Lü Bu falls at Xiapi
  section Rise of the three
    day 40 (200) : Battle of Guandu
    day 47 (207) : Three visits to Zhuge Liang
    day 48 (208) : Battle of Red Cliffs
    day 54 (214) : Liu Bei takes Yi
    day 59 (219) : Fan Castle, Guan Yu falls
  section Three Kingdoms
    day 60 (220) : Cao Pi founds Wei
    day 62 (222) : Battle of Yiling
    day 68 (228) : Northern Expeditions begin
    day 74 (234) : Wuzhang Plains
  section Unification
    day 89 (249) : Incident at Gaoping Tombs
    day 103 (263) : Shu falls
    day 106 (266) : Jin founded
    day 120 (280) : Wu falls, Jin unifies
```

## 4. Households and lineage

The player's unit of play is a household ([0010](../adr/0010-lineage-households.md)).
NPC households follow the same rules.

```mermaid
stateDiagram-v2
  [*] --> Founded: create founder (born 145-160)
  Founded --> Active
  Active --> Active: serve, fight, marry,<br/>adopt, swear brotherhood
  Active --> HeadDied: battle, duel, execution,<br/>illness, old age
  HeadDied --> Succession: heir exists
  Succession --> Active: heir inherits share of fame,<br/>wealth, retainers, 1 skill
  HeadDied --> Extinct: no heir
  Extinct --> Founded: new founder at current year<br/>with legacy points
  Active --> Archived: season ends
  Archived --> [*]: hall of fame (account legacy)
```

| Rule | Starting value (*tunable*) |
|---|---|
| Founder birth year | 145–160 |
| Adulthood | 16 game years (16 real days) |
| Natural lifespan | 50–75, modified by events and traits |
| Heir inherits | 50% fame, wealth share, loyal retainers, 1 skill |
| Season score | household fame + titles + historical feats |

## 5. Pacing and battles

Players check in for 20–40 minutes a day and queue orders. Battles open when
armies meet ([0011](../adr/0011-world-pacing-and-auto-join.md)). Each corps is
a seat controlled by its player or the AI ([0012](../adr/0012-household-corps-seats.md)).

```mermaid
flowchart TB
  E["armies meet on a tick"] --> Q{"part of an anchor war?"}
  Q -->|yes| PT["schedule at prime time<br/>(default 21:00 KST), announced a day ahead"]
  PT --> FR["split into fronts<br/>one MatchDO each, ≤ 6 seats a side"]
  Q -->|no| SK["skirmish MatchDO<br/>6 h play window"]
  SK --> J{"any player seat joins<br/>before the window closes?"}
  J -->|yes| PL["play: online seats live,<br/>absent seats AI"]
  J -->|no| AR["auto-resolve<br/>from army strength"]
  FR --> PL
  PL --> RES["result"]
  AR --> RES
  RES --> AP["applied on the next tick<br/>armies locked until then"]
  CON["player connects"] -.->|"auto-join: seat switches to the player<br/>at its next activation"| PL
```

## 6. Tick pipeline

Every 2 hours the `WorldDO` alarm runs one game month:

```mermaid
flowchart LR
  A["1 lock orders<br/>for the month"] --> B["2 movement<br/>armies advance"]
  B --> C["3 encounters<br/>open battles"]
  C --> D["4 apply finished<br/>battle results"]
  D --> E["5 economy<br/>tax, harvest, recruits"]
  E --> F["6 events<br/>anchors + ambient"]
  F --> G["7 AI decisions<br/>NPC lords, absent households"]
  G --> H["8 life<br/>ageing, births, deaths, succession"]
  H --> I["9 end conditions"]
  I --> J["10 persist + broadcast,<br/>set next alarm"]
```

Budget *(tunable)*: one tick under 2 s of DO CPU for about 100 households plus
about 500 NPC officers. Larger public seasons shard by region (see
[`HYPERSCALE_ROADMAP.md`](../server/HYPERSCALE_ROADMAP.md)).

## 7. Season lifecycle

```mermaid
stateDiagram-v2
  [*] --> Scheduled
  Scheduled --> Signup: break day before start
  Signup --> Running: day 1 (year 161)
  Running --> Running: tick every 2 h
  Running --> Ended: day 120 (year 280)
  Running --> Ended: early end condition<br/>(unification, collapse)
  Ended --> Recap: rankings, hall of fame,<br/>archive to D1
  Recap --> [*]
```

## 8. Presets

| Setting | Living History | Warlord Campaign |
|---|---|---|
| Joining | Public | Invite only |
| Calendar | Fixed, 3 seasons a year | Host starts it |
| Pace | 1 month per 2 h | Same, slower, or session-based |
| Start | 161 | Scenario: 161, 184, 190, 200, 208 … |
| Role | Created household | Created household or historical lord |
| End | Day 120 or early end | Host-configured |

## 9. Open questions

| Question | Leaning |
|---|---|
| What happens between an early end and the next season's start date? | An epilogue: free play with no ranking changes, or the world closes for recap |
| Joining mid-season | Found a household as a youth in the current year, with a catch-up bonus |
| Strategic map granularity | A city/route graph like RTK; grid maps only for battles |
| Player-to-player diplomacy | Alliances, marriages, sworn brotherhood; betrayal allowed |
| Early-end thresholds | One faction holding ≥ N% of cities, set by playtest |
