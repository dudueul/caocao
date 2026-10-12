# client/ — Unity 6 rebuild of CAOCAAO

Staged from AssetRipper export (0 failed files). `ProjectSettings` still
declare `2018.2.16f1` — **open in Unity 6 and accept the upgrade prompt**;
Unity re-serializes assets in place (keep a git snapshot before first open).

## Layout

```
Assets/
  Scenes/        18 original scenes (YAML) — rebuild milestone order:
                 Login → Loading → Entrance → Town → WorldMap → …
  Scripts/       5,978 field-correct stubs (original GUIDs). We fill bodies
                 system-by-system from decompiled logic (docs/re/).
  GameObject/    prefabs; Sprite/Texture2D/AudioClip/… exported content
                 (tk2d collections are legacy 2D Toolkit — see migration memo)
  Plugins/       third-party plugins found in the original build
Packages/        add: com.unity.nuget.newtonsoft-json (LitJson replacement),
                 FlatBuffers runtime (we vendor a minimal reader — see dsa-02)
```

## Rebuild rules (from Phase 1 evidence)

1. **Never rename stub scripts or their GUIDs** — scenes reference them.
2. Provenance comments: every recovered method body starts with
   `// [RE] <Class>::<Method> @<RVA> (from libil2cpp.so)`.
3. Deep explanations live in `docs/lab/dsa/` docs, cross-referenced inline.
4. Networking: re-implement Socket.IO v4 client over UnityWebRequest
   WebSocket (`Networking/SocketIOMin.cs`), JSON packets via Newtonsoft
   (mirrors `PacketUtil` semantics).
5. Server URL config: `ScriptableServerSetting` mirrors `IServerSetting`
   (NAME, GAME_SERVER_HOST_URL, CDN_URL, USE_PACKET_ENCRYPT, NPA_SERVICE_ID…)
   pointing at the Cloudflare backend.
