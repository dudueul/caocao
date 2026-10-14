# Game rules

> Living specification of the battle and character rules implemented by
> `packages/rules`. Every number here is **starting tuning** until playtests;
> decisions are in [`docs/adr/`](../adr/README.md). All maths is integer;
> probabilities are permille (‰) and multipliers are ×1000 fixed-point
> ([ADR 0006](../adr/0006-typescript-everywhere.md)).

## 1. Officer stats

Five stats, integers from 1 to 100, each with one battle role and one World role
([ADR 0020](../adr/0020-five-stats-with-distinct-roles.md)).

```mermaid
flowchart LR
  STR["무력<br/>Martial"] --> ATK["attack, counter,<br/>duel power"]
  CMD["통솔<br/>Command"] --> DEF["defense, troop HP,<br/>ally aura"]
  INT["지력<br/>Intellect"] --> STRAT["stratagem power<br/>+ resistance, MP"]
  AGI["민첩<br/>Agility"] --> HIT["hit + evasion,<br/>double attack, initiative"]
  LUK["운<br/>Fortune"] --> CRIT["critical hits + avoidance,<br/>status resistance"]
  STR -.-> W1["duels, feats"]
  CMD -.-> W2["corps size, morale"]
  INT -.-> W3["plots, diplomacy,<br/>administration"]
  AGI -.-> W4["march speed, scouting"]
  LUK -.-> W5["fate on defeat:<br/>escape / capture / death"]
```

Solid arrows are battle roles; dotted arrows are World / Living History roles
([WORLD_ENGINE.md](WORLD_ENGINE.md)).

| Stat | Battle | World / Living History |
|---|---|---|
| **무력** Martial | attack power, counter damage, duel power | duels, feats of arms |
| **통솔** Command | defense, troop HP bonus, aura for adjacent allies | corps size (retainers), morale, army upkeep |
| **지력** Intellect | stratagem power and resistance, MP | plots, diplomacy, administration |
| **민첩** Agility | hit and evasion, double attack, initiative | march speed, scouting |
| **운** Fortune | critical hits and their avoidance, status resistance | fate of a defeated officer |

## 2. Attack resolution (draft)

```mermaid
flowchart TB
  A["attack declared<br/>(legal target in range)"] --> H{"hit?<br/>hit‰ vs PRNG"}
  H -->|miss| M["event: missed"]
  H -->|hit| C{"critical?<br/>crit‰ vs PRNG"}
  C -->|yes| DC["damage × 1500/1000"]
  C -->|no| D["base damage"]
  DC --> APPLY["apply HP loss"]
  D --> APPLY
  APPLY --> K{"HP ≤ 0?"}
  K -->|yes| DEF["unit defeated →<br/>officer fate roll (운)"]
  K -->|no| CT{"defender can counter?<br/>(range, not disabled)"}
  CT -->|yes| CA["counter-attack<br/>(same steps, no counter)"]
  CT -->|no| END["end of action"]
  CA --> END
  DEF --> END
```

| Quantity | Formula (starting tuning) |
|---|---|
| Hit | `hit‰ = clamp(850 + 6·(민첩a − 민첩d) − terrainEvasion‰, 250, 990)` |
| Critical | `crit‰ = clamp(40 + 4·(운a − 운d), 10, 350)`, damage ×1.5 |
| Officer fate on defeat | `escape‰ = clamp(600 + 4·운, 0, 950)`; otherwise captured or killed |
| Base damage, double attack, counter damage, stratagems | *to be defined with the class system* |

Each random check draws once from the battle's `Prng` in a fixed order (hit,
then critical), so replays and the referee reproduce it exactly
([ADR 0004](../adr/0004-server-authoritative-referee.md)).

## 3. Still to decide

| Topic | Depends on |
|---|---|
| Class system: families, tiers, promotions, what a class adds | next design session |
| HP and damage scale (small numbers vs large) | class system |
| Terrain: movement costs, evasion and defense bonuses | class system |
| Stratagems, MP and status effects | stat roles (done), class system |
| Equipment | class system, progression ([ADR 0014](../adr/0014-scoped-progression-fair-arena.md)) |
| Arena normalization | [ADR 0014](../adr/0014-scoped-progression-fair-arena.md) |
