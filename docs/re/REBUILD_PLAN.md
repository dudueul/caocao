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

## Milestone 2 — Server skeleton (Cloudflare)
- Worker WS route → Durable Object `GameServer` speaking engine.io v4 +
  socket.io v4 event layer (P §2), JSON in/out via zod
- D1 schema: players, inventory, progress; auth by guest token
- `HttpRequest` REST endpoints mirrored (notices/terms placeholders)

## Milestone 3 — Town & progression
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

## Milestone 5 — Async multiplayer
- Raid/Instance/WorldBoss flows (`RaidManager` [552963], Finish* senders)
- Server: battle turn resolution service, result packets per P §3
- Chatting (channel/whisper/guild) on same socket; `Chatting` [396491]

## Milestone 6 — Realtime PvP (the FlatBuffers mode)
- `MultiplayManager` matchmaking [622273], `Duel` scene, `DuelCommands`
  command stream [6402], rollback strategy (`RollbackBattle`)
- DO rooms with Durable Object per match; GuildWar world-state streaming in
  FlatBuffers (schemas: analysis/fbs/GuildWarFlatBuffer.fbs — 67 tables)

## Milestone 7 — Meta & economy
- Guild system (`GuildSiegeWarFlowController` [479535], guild scenes)
- Arena/Competition/AnnihilationWar/ThousandBattle modes
- Gacha, pedlar (shop rotation), fair-rule anti-cheat parity

## Milestone 8 — Ship-config
- Replace/stub SDKs: Tapjoy→(optional ads stub), NPA→own auth, Adjust→noop,
  Crashlytics→Sentry, IAB→Play Billing v7 re-implementation
- Keystore/signing, store listing assets from export (icon found in res/)

## Textbook sync
Labs 6–8 + dsa-04..08 written as each milestone completes; all verify via
`scripts/verify-lab.sh`.
