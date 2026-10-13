# REBUILD PLAN — from evidence to Unity 6 + Cloudflare

Order and scope for the full rebuild. Each task cites its evidence source
(`A` = analysis/ARCHITECTURE.md, `P` = analysis/PROTOCOL.md, dump.cs TypeDef
indices in brackets).

## Milestone 0 — Project bring-up
- [x] Stage `client/` from AssetRipper export (505MB, 0 failed files)
- [ ] Open in Unity 6 (owner: user installs editor), accept upgrade, fix compile
      errors in stubs (expect tk2d plugin issues → port or stub)
- [ ] CI-less smoke: project opens, Login scene loads with placeholder scripts

## Milestone 1 — Boot & shell (scene order from BuildSettings)
1. `Login` scene: UI panel + `NPAController` [629012] port → replace NPA with
   pluggable `IAuthProvider` (device-id guest auth vs real NPA)
2. `Loading`/`Entrance`: `GameManager` [548349] boot order,
   `SyncStreamingAssetsLoader` [689939] (now: local content only)
3. `ServerSetting` [626888] → ScriptableObject with LOCAL env pointing to
   `ws://localhost:8787/socket.io/?EIO=4&transport=websocket`
4. `HeartBeats` [626162] + `SocketIOMin` client (engine.io v4 framing)
   — wire format from P §1–2

## Milestone 2 — Server skeleton (Cloudflare) ✅ 9360d7c4
- [x] Worker WS route → Durable Object `GameServer`: engine.io v4 + socket.io
      v4 (zod-validated), event catalog: login/enterToWorld/want_room_id/
      leaveRoom/broadcastTo/updateHistory (analysis/protocol_events.md)
- [x] D1 schema: players, inventory, progress (server/schema.d1.sql applied)
- [x] REST: /user/login (guest authKey issue), /user/getUserData,
      /user/updateHistory (heartbeat contract)
- [x] Hermetic acceptance: server/test/protocol_test.py → PROTOCOL_TEST_PASS

## Milestone 3 — Town & progression (client network hub ✅ 0fc5e7cf)
- [x] SocketIOComponent/Encoder/Decoder/Parser rebuilt on real
      WebSocketSharp+LitJson (vendored, replacing AssetRipper stubs)
- [x] SessionImpl: full login/enter-world state machine (PImpl.cs splice),
      SessionBoot guest-auth glue, HeartBeats, HttpRequest on UnityWebRequest
- `Town` scene controllers, `WorldMapControlFlow` [615678],
  `SceneDataPipe` [433454] (cross-scene state)
- Inventory/items from baked `Item` assets [11545] — write ObscuredTypes
  replacement (plain fields) + data repair script (decode exported values)
- Mail (`MailChecker` [546857]), shop (`ConsumingGoods*`), tutorial flow

## Milestone 4 — Single-player battle core
- `StageSelect` → `ScenarioMap` → `BattleMap` pipeline
- `BattlePlayManager` [394637] + turn engine, `UnitData` [10262/12031],
  `PendingEffectManager` [487748], skills (EnumData.SkillTypes)
- Pathfinding: our A* (dsa-03) diffed vs decompiled (analysis/diff_pathfinding.md)

## Milestone 5 — Async multiplayer ✅ (61335cea, 48f99f14, eb22dc4d)
- [x] Battle executor: per-ControlType dispatch + CombatPower coef (REAL,
      decompiled cctors) + 383-const tuning table
- [x] Battle endpoints: scenario/multi start→end (event-sourced D1 log)
- [x] Replays: /multiplay/getReplayData over the battle log
- [x] Multiplay bridge: getServerInfo handoff → socket rooms → battleControl
- [ ] Damage formula exact extraction (SpAction* decompile) — [RE-APPROX] now
- [ ] Chat UI panels (Chatting singleton logic ✓, UI pending)

## Milestone 6 — GuildWar world sync ✅ (0a8b51a2, 4504115b)
- [x] FlatBuffers v1.6.0 vendored (namespace-faithful); all 67 GuildWarFlatBuffer
      structs regenerated as inline STRUCTS (decompiled-proof grounded)
- [x] Server: guild CRUD/apply/applicants/motd + CWLD world snapshots
      (28B corps rows, 12B buildings) — python round-trip OK
- [ ] Client DataTableImpl snapshot/diff application (generic decode core)
- [ ] Rollback battle resolution (RollbackBattle decompile)

## Milestone 7 — Meta & economy
- Guild system (`GuildSiegeWarFlowController` [479535], guild scenes)
- Arena/Competition/AnnihilationWar/ThousandBattle modes
- Gacha, pedlar (shop rotation), fair-rule anti-cheat parity

## Milestone 7 — Platform channels ✅ core (27f3f082)
- [x] IWebSocketTransport seam: WebSocketSharp (native) / browser WS via
      .jslib (WebGL) — golden flow byte-identical through the seam
- [x] Server CORS preflight (WebGL-ready)
- [ ] iOS: needs macOS build host (owner decision: own Mac / CI Mac / Unity
      Cloud Build); IL2CPP-only ✓; wss:// in prod (ATS)
- [ ] WebGL: Brotli asset pipeline on R2; origin tightening in CORS

## Milestone 8 — Ship-config
- Replace/stub SDKs: Tapjoy→(optional ads stub), NPA→own auth, Adjust→noop,
  Crashlytics→Sentry, IAB→Play Billing v7 re-implementation
- Keystore/signing, store listing assets from export (icon found in res/)

## Textbook sync
Labs 6–8 + dsa-04..08 written as each milestone completes; all verify via
`scripts/verify-lab.sh`.
