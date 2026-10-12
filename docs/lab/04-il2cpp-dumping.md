# Lab 04 — IL2CPP: metadata dumping with Il2CppDumper

**Goal:** turn a 75 MB stripped native library back into a *structured
inventory* of the C# program: 12,414 types, 163,902 named methods — and learn
what IL2CPP did and did not destroy.

---

## 4.1 The run

```bash
dotnet Il2CppDumper.dll libil2cpp.so global-metadata.dat out/il2cpp
# Il2Cpp Version: 24
# Searching...
# CodeRegistration : 45d5ce8
# MetadataRegistration : 45d5d58
# Dumping... Done!
# Generate struct... Done!  Generate dummy dll... Done!
```

**How it found those addresses** (worth understanding, not just running):
IL2CPP's runtime needs its own registration tables at startup, so
`CodeRegistration`/`MetadataRegistration` are always referenced by the module's
init code. The tool scans for the known init patterns (per metadata version),
resolves the two structures, and everything else — every type, method, string
literal, and address — is reachable from them via pointer chains in
`global-metadata.dat`.

## 4.2 Outputs and their uses

| File | Size | What it is | Used by |
|---|---|---|---|
| `dump.cs` | 25.5 MB / **707,942 lines** | every class: fields w/ offsets, method signatures w/ **RVA addresses** | reading, planning, REA queries |
| `script.json` | 68.7 MB | `ScriptMethod[] {Address, Name, Signature}` ×163,902 + strings + metadata | Ghidra symbol restore (Lab 06) |
| `il2cpp.h` | 58.7 MB | C structs for every C# type (field layouts) | Ghidra struct typing |
| `stringliteral.json` | 1.6 MB | 19,897 string literals with addresses | protocol hunting (this is how we found the server URLs!) |
| `DummyDll/` | 38 assemblies | stub assemblies (types/methods, empty bodies) | static analysis, tooling, AssetRipper script stubs |

## 4.3 Reading dump.cs (the format)

```csharp
public class ServerSetting : IServerSetting // TypeDefIndex: 10919
{
	// Fields
	private static IServerSetting ms_cur; // 0x0

	// Methods
	// RVA: 0x14C7018 Offset: 0x14C7018 VA: 0x14C7018
	public static IServerSetting get_instance() { }
}
```

- `// 0x0` after a field = **offset within the object** → exact memory layout
  (reconstructs the C# class layout perfectly).
- `// RVA:` after a method = **runtime virtual address of the compiled body** —
  your pointer into Ghidra. Empty body `{ }` because IL2CPP stripped the code;
  the *address* is the gold.
- `TypeDefIndex` = stable type id into metadata.

## 4.4 What survived / what died

| Survived (from metadata) | Destroyed (compiled away) |
|---|---|
| class/namespace/method names | method **bodies** (logic) |
| field names + offsets | constants folded into code (some survive as literals) |
| string literals (19,897!) | generic instantiations structure (partially) |
| inheritance, interfaces, enums+values | compiler temporaries |

**Class exercise:** open `dump.cs`, find `SocketIOComponent` (search
`class SocketIOComponent`), and read its field list. You are looking at the
original source's private fields. Now imagine you wrote this class in 2016.

## 4.5 The evidence trail this lab produced for the whole course

- `IServerSetting` with `GAME_SERVER_HOST_URL` / `USE_PACKET_ENCRYPT`
- `PacketUtil.JsonString(object[])` → JSON wire protocol
- `SocketIOComponent` fields → engine.io v4 state machine
- `EnumData.DuelCommands` → PvP command protocol
- `MultiplayManager` → matchmaking + rollback
- Server URLs, CDN endpoints, Tapjoy/NPA ids (via `stringliteral.json`)

## 4.6 Exercises

- **4-A** Dump the same APK yourself; verify `sha256sum dump.cs` matches the
  course value in `scripts/verify-lab.sh`. Determinism check: same input bytes
  → same output bytes (metadata parsing is deterministic — why that matters
  for reproducible research).
- **4-B** Write a script that builds `type_index.json`: parse `// Namespace:`
  comments + type declarations, output counts per namespace. (Our version:
  12,414 types, 8,012 global-namespace.) Then answer: why do 8k game classes
  sit in the *global* namespace? (C# style choice of 2015-era Korean studios +
  Unity default assembly.)
- **4-C** `stringliteral.json` hunt: write a filter that yields every string
  containing `://` or `:9080`, excluding `.w3.org`/`microsoft.com`. Compare
  with the recovered endpoint list in `analysis/ARCHITECTURE.md §4`.
- **4-D (DSA)** `script.json` maps 163,902 addresses to names. Design the
  lookup structure the Ghidra script needs (dict by address) and explain why
  it must be exact-match O(1) for 163k entries. → `dsa/01-...`.

Solutions: `solutions/04.md`.
