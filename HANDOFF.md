# Handoff — CAOCAAO Source Recovery & Rebuild

**Date:** 2026-10-12 · **Repo:** `~/work/caocao` (initial commit `86695ca8` by owner)

## What this is

The owner lost the C# source + server of their shipped Unity game
(`com.nexon.caocao`, Unity 2018.2.16f1 IL2CPP, Socket.IO online SRPG — 18
scenes, guild wars, arenas, a full economy). This repo contains: the complete
RE pipeline outputs, a rebuilt client (Unity 6 project) + server (Cloudflare
Workers/D1/DO), and a lab textbook generated from the actual work.

## State (all committed in chunks)

| System | State | Proof |
|---|---|---|
| Dumps | dump.cs 707,942 lines / 12,414 types / 163,902 methods; jadx 5,205 files; assets 3,615 (0 failed) | `extracted/` |
| Ghidra | program with ALL functions renamed + 19,513 string labels | `analysis/ghidra_proj` |
| Protocol | Engine.IO v4 + Socket.IO v4; 492 REST endpoints cataloged; event names from decompiled call sites | `analysis/protocol_events.md`, `rest_endpoints.txt` |
| Server | auth/rooms/chat/economy/battle-logs/guild/FlatBuffers world snapshots — **wire tests green** | `server/test/protocol_test.py` |
| Client | wire engine (real WebSocketSharp+LitJson vendored), SessionImpl hub, Item/UserItem/UserItems, AStar+QuickPathFinder (original shape), BattlePlayManager queue, executor, MultiplayManager bridge | `client/Assets/Scripts/` |
| Textbook | Labs 00–08 + dsa-01..08, fixtures, solutions, `verify-lab.sh` (8/8) | `docs/lab/` |

## Immediate next steps

1. **Unity 6 first open** (owner installing — Windows Hub + editor):
   - copy `client/` to a Windows-native path first
   - accept the 2018.2.16 → Unity 6 upgrade
   - expect compile errors: tk2d/PlayMaker stub surface + my authored files;
     paste Console output back to the agent → fix loop
2. **Battle scene executor**: `ApplyControl` is a stubbed hook — wire per-
   ControlType into unit animations using the recovered ClientConst tuning.
3. **Damage formula**: currently `[RE-APPROX]` — extract from `SpAction*`
   decompiles (queue: `scripts/DecompileFuncs.java "SpAction" ...`).
4. **Deploy**: `cd server && wrangler deploy` (owner runs `wrangler login`
   first); local dev already green via `wrangler dev`.

## Key facts cheat-sheet

- Server hosts (dead, reference): this APK is the **KOR Live build** (revision
  78592): game server `nxm-caocao.nexon.com:9080`, CDN
  `dxu1t8x55g49k.cloudfront.net`; the Global build used `nxm-caocao-ww.nexon.com:9080`
  / `nxm-caocao-ww.akamaized.net` — **packets were encrypted** (USE_PACKET_ENCRYPT=1).
  Download flow, bundle crypto and recovery playbook: `docs/re/CDN_ASSET_BUNDLES.md`
- Login wire: `["login", {userId, authKey}]`; rooms: `enterToWorld`/
  `want_room_id`/`broadcastTo`; controls: JSON `BattleControl` records
- Military power coefs (recovered): Almighty {100,100,75,65,60},
  Military {100,100,25,85,85}, Civil {10,75,135,50,100}, ETC flat
- Item schema: 49 fields (`Item.cs`); all 239 table schemas + localization-prefilled
  templates in `data/tables/`. Balance values shipped in CDN bundles (lost) —
  catalog content is author-supplied (`server/seed/items.json`)
- Placeholder art: `scripts/ekd5/export_art.py <1998 game>` exports the PC
  조조전 units / maps / portraits / icons / SFX into the git-ignored
  `client/Assets/Resources/ClassicArt/`; `Rebuild.Classic.ClassicArtPreview`
  shows them in Play mode (`docs/re/CLASSIC_ART.md`). Never commit that folder.
- fndid.bytes: encrypted local file, not yet cracked (bonus lab)

## Toolchain (~/tools, root-free)

JDK 21.0.12, Ghidra 12.1.4 (+ DisablePid.java prescript — **never run default
analysis on this binary**, see Lab 06 war stories), jadx 1.5.6, dotnet 8,
Il2CppDumper 6.7.46, AssetRipper 2.0.0 (headless web :18888), REA 6.4.0
(+ pinned jadx-headless-mcp 0.7.1), Node 26 (Linux fnm — **verify PATH after
host crashes**, Windows node can shadow it), wrangler 4.149.
