# Lab 05 — Asset Forensics: rebuilding the content pipeline with AssetRipper

**Goal:** convert 3,533 opaque Unity SerializedFiles back into an editable
Unity project, and understand every format AssetRipper must decode to do it.

---

## 5.1 Running AssetRipper headless (Free 2.0 web API)

AssetRipper Free ships as a web UI — which makes it *scriptable*: it exposes
OpenAPI at `/openapi.json`.

```bash
# start detached (no browser):
( setsid ./AssetRipper.GUI.Free --headless --port 18888 >/tmp/ar.log 2>&1 & )
# load the APK (18s wall time on 12 cores):
curl -s -X POST http://127.0.0.1:18888/LoadFile \
  --data-urlencode "Path=/path/to/caocao.apk"
# export a Unity project:
curl -s -X POST http://127.0.0.1:18888/Export/UnityProject \
  --data-urlencode "Path=out/unity_export"
```

Real run evidence: load 18s; export 3,615 assets ≈ 6 min; **0 failed files**;
512 MB output:

```
ExportedProject/
├── Assets/{AnimationClip,AnimatorController,AudioClip,Font,GameObject,
│          Material,Mesh,MonoBehaviour,Plugins,Resources,Scenes,
│          Scripts,Shader,Sprite,Texture2D}
├── Packages/  ProjectSettings/
AuxiliaryFiles/{GameAssemblies, path_id_map.json}
```

Notice `tk2dMasking_WetLand`, `tk2dMasking_Water_AssaultShip` in the export
log — the game was built on **2D Toolkit (tk2d)**, a beloved-then-abandoned
2D plugin. Rebuild implication (Phase 3): tk2d sprite collections must be
ported to Unity's native SpriteRenderer/sprite atlas.

## 5.2 What AssetRipper had to decode (the theory you should retain)

1. **SerializedFile v17** containers (Lab 02) — object tables with
   `(fileID, pathID)` references across 3,533 files.
2. **TypeTree** metadata — per-object binary layout descriptions, either
   embedded or reconstructed from known Unity versions (2018.2.16f1!).
3. **Resource formats**: Texture2D (DXT/ETC/ASTC blocks), AudioClip (Vorbis/
   ADPCM), Mesh (vertex streams), Font (TTF+atlas), AnimatorController FSM.
4. **Cross-references**: a prefab references a texture by `pathID` in another
   container — AssetRipper rewrites these into Unity GUID references.
5. **Script stubs**: MonoBehaviour fields need their C# classes; Free edition
   emits field-layout stubs (full decompiled code is our Il2CppDumper path).

## 5.3 Verification discipline (never trust an export)

```bash
du -sh ExportedProject/Assets/* | sort -rh   # sanity: textures+audio dominate
curl -s localhost:18888/FailedFiles/View     # must be empty
```
Then open `ProjectSettings/ProjectVersion.txt` and `ProjectSettings/` — check
the export declares the *original* Unity version.

## 5.4 Exercises

- **5-A** Reproduce the export; record wall time and failure count in
  `analysis/export_report.md`.
- **5-B** Find the `Scenes/` folder — confirm all 18 `.unity` scenes exported;
  open `Login.unity` in a text editor: it is **YAML**. Find one object
  reference (`{fileID: ..., guid: ..., type: 3}`) and trace the GUID to its
  .meta file. This is Unity's reference graph, human-readable.
- **5-C (DSA)** `path_id_map.json` maps (container, pathID) → exported file.
  Model it as a hash of composite keys; explain collision handling and why
  pathIDs are *stable* across a game's lifetime (save-data compatibility).
- **5-D** tk2d inventory: count exported assets with `tk2d` in their name;
  write a short migration memo (tk2dSpriteCollection → SpriteAtlas).

Solutions: `solutions/05.md`.
