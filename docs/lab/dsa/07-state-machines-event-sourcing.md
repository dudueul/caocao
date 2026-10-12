# dsa-07 — State Machines & Event Sourcing for a Turn-Based Server

**Grounding evidence:** the client's flow controllers are explicit state
machines — `GameFlowController`, `WorldMapControlFlow`,
`GuildSiegeWarFlowController` (all `Singleton<T>`, dump.cs) and PlayMaker FSMs
everywhere; the multiplayer path is `MultiplayManager` with `RollbackBattle`
(rollback!) and `Finish*.SendXxxBattleEnd` result packets. Our server rebuild
must be the *authoritative twin* of this client machine.

## 1. FSMs: the client already taught you the shape

A game-flow FSM = states, transition guards, entry/exit actions. PlayMaker
serialized each FSM as data (the exported `Fsm*` MonoScripts and FSM assets
prove it). Lesson: *data-driven FSMs* (states as rows, transitions as edges)
beat switch-statements the moment designers iterate — which is why the
original chose PlayMaker.

**Exercise 7-A:** transcribe `GuildWarLobby`'s flow (from exported FSM asset
YAML, Phase 2) into a table; implement it as `Dictionary<(State,Event),State>`.

## 2. Event sourcing for battle state

Turn-based battle is the *ideal* event-sourcing domain:

```
state = fold(apply, initialState, events[])
```

- `events` = the `DuelCommands` stream (StartBattle=0 … EndBattle=8) plus
  server-derived randomness commitments.
- Determinism requirements: seeded RNG (dsa-05), fixed iteration orders
  (sort by (initiative, unitId)), and *no wall-clock reads inside apply*.
- `RollbackBattle` in the original = optimistic client prediction + server
  correction — the same architecture as FPS netcode, minus the latency budget
  (turn-based affords seconds, not frames).

**Exercise 7-B:** implement `apply(state, event)` for the DuelCommands subset
{StartBattle, DuelAttack1, Die, EndBattle} with a snapshot every N events
(copy-on-write vs full copy — measure both on 10k-event replays).

## 3. The Durable Object mapping

| Concept | Cloudflare primitive |
|---|---|
| one battle | one DO instance (actor) |
| event log | DO storage, append-only list |
| snapshots | periodic state blob in DO storage |
| player disconnect | WS Hibernation + resume replay from snapshot+tail |
| cross-battle queries | D1 (leaderboards, guild rosters) |

**Exercise 7-C:** write the DO's `fetch` handler skeleton that (a) validates
the frame is socket.io EVENT, (b) applies the event, (c) broadcasts the
*resulting* event (server-authoritative: clients render results, they don't
compute them). Cross-ref Lab 07's grammar.

## 4. Idempotency & replay safety

A retried `DuelAttack1` must not double-apply. Include a client-generated
`(seq)` in the event envelope; the DO keeps a last-seen seq per connection.
**Exercise 7-D:** implement the dedup gate + prove it with the test harness
(replay the same event 3×, assert one state change).

## 5. Auto-grading

`verify-lab.sh dsa07` (lands with Phase 4): replay tests + dedup tests +
snapshot-restore equivalence (state after replay == state after
snapshot+tail).
