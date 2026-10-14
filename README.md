# CAOCAAO — Source Recovery & Rebuild

Recovered from `_release/caocao.apk` (Unity 2018.2.16f1, IL2CPP, Socket.IO).
The original C# source and server were lost; everything here is rebuilt from
the shipped binary with an evidence chain.

```
client/          Unity 6 project (staged from AssetRipper export, 505MB, 0 fails)
server/          Cloudflare Workers + Durable Objects + D1 backend
  src/           engineio.ts, socketio.ts, index.ts (GameServer DO)
  test/          echo pair — stdlib-only protocol proof (Lab 07)
extracted/       dump outputs (never edit — regenerable)
  apk/           unpacked APK (3,518 Unity data files)
  il2cpp/        Il2CppDumper: dump.cs (707,942 lines), script.json (163,902 fns)
  jadx/          Java decompile (5,205 files)
  unity_export/  AssetRipper export (3,615 assets, 0 failed)
analysis/        evidence log, protocol docs, event catalog, recovered .fbs
data/tables/     239 data-table schemas + templates pre-filled from localization
data/reference/  ekd5/: 1998 PC 조조전 reference values + class → sprite map
docs/
  re/            reconstruction blueprints (REBUILD_PLAN.md, CDN_ASSET_BUNDLES.md,
                 EKD5_FORMATS.md, CLASSIC_ART.md: 1998 art as Unity placeholders)
  server/        online architecture review + game-server practices
                 (GAME_SERVER_PRACTICES.md) and scaling memo (HYPERSCALE_ROADMAP.md)
  lab/           THE TEXTBOOK: labs 00–08, dsa-01–08, exercises, solutions
scripts/         reproducible pipeline + verify-lab.sh (auto-grading);
                 tables/ (template build), bundles/ (bundle + list decryption),
                 ekd5/ (1998 game decoders + export_art.py → Resources/ClassicArt)
tools/           (~/tools) JDK 21, Ghidra 12.1.4, jadx, dotnet, REA — no sudo
```

## Quick reproduction

```bash
scripts/verify-lab.sh apk      # artifact identity (sha256)
scripts/verify-lab.sh lab02    # header parsing exercise
scripts/verify-lab.sh dsa01..dsa03, lab07
scripts/verify-lab.sh il2cpp   # dump presence
```

## Status

- Phase 0–1 complete (toolchain, dumps, asset export, labs 00–05)
- Phase 2 running (Ghidra rename pass) → REA analysis → event catalog
- Phase 3/4 early code in place: SocketIOMin.cs, server skeleton (protocol
  verified end-to-end vs Python client), ObscuredTypes shim
- Owner action: Unity 6 editor install in progress
