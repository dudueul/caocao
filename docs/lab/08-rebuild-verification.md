# Lab 08 — Rebuild & Verification: Unity 6 + Cloudflare, then prove it

**Goal:** the *payoff* lab. Everything recovered becomes a running game; every
recovered fact becomes a test.

---

## 8.1 The bring-up order (and why this order)

```
1. server/test/echo pair        — protocol layer proven without any game code
2. Worker + GameServer DO       — sid, connect ack, events, heartbeat (done day 1!)
3. Unity client SocketIOMin     — same grammar, client side
4. Login → Town → WorldMap      — the single-player spine (scene order §1.2)
5. Battle core                  — BattlePlayManager port + A* diff
6. Async multiplayer            — raid/instance flows
7. Realtime PvP (FlatBuffers)   — DuelCommands stream + rollback
```

Each step has a *binary verification*: a test that fails without the step and
passes with it. No vibes-based milestones.

## 8.2 Cross-checking client vs server (the lab's core skill)

Run both sides with the same frame log. Our harness:
`scripts/verify-lab.sh lab08` (lands with Milestone 3) replays a recorded
session through both implementations and diffs the byte streams.

**Exercise 8-A:** instrument `SocketIOMin` to log every frame
(direction, hex, decoded meaning) — reproduce the echo session and diff
against the Python client's log. Explain any difference (e.g., pong
deduplication at the edge).

## 8.3 Data repair pipeline (ObscuredInt decode)

The exported `Item` assets carry CodeStage-encrypted ints. Pipeline:

```
export YAML → decode script (XOR w/ per-asset key) → plain JSON catalog
            → server seeds D1 → client receives catalog over socket.io
```

**Exercise 8-A:** after Phase 2 confirms the CodeStage algorithm from the
decompiled getter, extend `scripts/repair_data.py` and validate: every decoded
price > 0, tiers within 0–9, icon paths resolve to exported textures.

## 8.4 The scene-by-scene port protocol

For each of the 18 scenes:

1. Open exported `.unity` — list root objects + their MonoBehaviours.
2. Map each MonoBehaviour to its stub in `Assets/Scripts/...` (GUID match).
3. Recover behavior from decompiled class (dump.cs RVA → REA function query).
4. Port with provenance tags; delete any code path that requires the dead
   original backend; route through our server.
5. Manual smoke test + automated scene-load test (Unity Test Framework).

## 8.5 Definition of "done" for the full rebuild

- [ ] All 18 scenes load without errors in Unity 6
- [ ] Guest login → Town flow works against `wrangler dev`
- [ ] One full PvE battle start→end with server-issued results
- [ ] One full async raid completion with persisted rewards (D1)
- [ ] One PvP duel via DuelCommands stream (FlatBuffers payloads)
- [ ] Item catalog served by server; client renders inventory from it
- [ ] Lab textbook complete: labs 00–08, dsa-01–08, all harnesses green

## 8.6 Exercises

- **8-B** Write the Unity Test Framework scene-load test (editmode test
  that opens each `.unity` and asserts no null script references).
- **8-C** Fuzz the DO: 1,000 malformed socket.io frames (random bytes, deep
  nesting, huge strings) — assert no unhandled exception escapes
  (`wrangler dev` console stays clean). Cross-ref: `zod` rejection paths.
- **8-D** Write the one-page *operations* runbook: how to deploy (`wrangler
  deploy`), how to rotate the packet-encryption key, how to restore a player
  from D1 history.
