# Lab 01 — APK Anatomy & First Contact (hands-on with caocao.apk)

**Goal:** dissect the APK byte-by-byte with zero assumptions. You will end this
lab knowing *what every byte-region is for* and how a game's identity is encoded
in files you never see on your phone.

---

## 1.1 Identity extraction without any RE tool

Even `unzip` is a powerful RE tool. Run exactly this:

```bash
file caocao.apk
# → Zip archive data, at least v2.0 to extract, compression method=deflate

unzip -l caocao.apk | tail -5
#    369729878 bytes total, 4863 files
```

**Question 1:** the APK is 94 MB but decompresses to 370 MB. Why?
*Answer shape: per-entry DEFLATE compression; textures/audio often stored
deflated too by Unity's build pipeline; `resources.arsc` must be uncompressed
and aligned on modern Android.*

### The manifest (binary XML)

`AndroidManifest.xml` inside an APK is **not** text — it is AXML: a binary
serialization with a string pool. Two extraction routes:

```bash
# route 1: string pool scrape (no tools) — what we actually did
unzip -p caocao.apk AndroidManifest.xml \
  | strings -e l | grep -E '^com\.|^io\.|uses-permission'
```

Actual harvested results (evidence):

```
com.nexon.caocao                  ← applicationId
unityplayer.UnityActivity         ← main activity = Unity's player
com.tapjoy.TJAdUnitActivity       ← ads SDK
com.android.vending.BILLING       ← IAB permission
android.permission.INTERNET
1265.MTI0ODI5NzQwMA.toy1265       ← Tapjoy key (base64 "1248297400" = Tapjoy app id)
com.nexon.nxgcmnoti.icon          ← Nexon push notification customization
```

**Decoding the Tapjoy string** (first mini-RE puzzle): `MTI0ODI5NzQwMA==`
base64-decodes to `1248297400`. The middle field of a Tapjoy key is always the
app id. *Lesson: vendor SDKs leak identity everywhere; follow strings.*

```bash
# route 2: the proper tool (used later) — jadx decodes AXML to real XML:
jadx caocao.apk   # outputs resources/AndroidManifest.xml as text
```

## 1.2 The scene list — the game's skeleton in one file

`assets/bin/Data/globalgamemanagers` is a Unity SerializedFile containing
**BuildSettings** — including the ordered scene list of the build:

```bash
unzip -p caocao.apk assets/bin/Data/globalgamemanagers | strings | grep -E '^Assets/.*\.unity$'
```

Evidence output (18 scenes — the whole game's UI topology):

```
Assets/Scenes/Login.unity        Assets/Scenes/Town.unity
Assets/Scenes/Loading.unity      Assets/Scenes/WorldMap.unity
Assets/Scenes/Entrance.unity     Assets/Scenes/ScenarioMap.unity
Assets/Scenes/StageSelect.unity  Assets/Scenes/BattleMap.unity
Assets/Scenes/Instance.unity     Assets/Scenes/Arena.unity
Assets/Scenes/Duel.unity         Assets/Scenes/Competition.unity
Assets/Scenes/GuildWar.unity     Assets/Scenes/GuildWarLobby.unity
Assets/Scenes/GuildSiegeWar.unity Assets/Scenes/AnnihilationWar.unity
Assets/Scenes/ThousandBattle.unity Assets/Scenes/Recon.unity
```

**Reading the list like a designer:** Login → Entrance → Town (hub) →
WorldMap → ScenarioMap → StageSelect → BattleMap is the single-player spine;
Arena/Duel/Competition/Guild*/AnnihilationWar/ThousandBattle are the online
PvP/multiplayer endgame; Instance = roguelike-ish dungeons; Recon/Restart/Movie
are utility scenes. This ordering will become our rebuild's milestone order.

## 1.3 Fingerprinting the engine & backend

```bash
unzip -p caocao.apk assets/bin/Data/globalgamemanagers | head -c 64 | strings | head -3
# → 2018.2.16f1        ← exact Unity version, first 12 bytes of the file
```

```bash
unzip -l caocao.apk | grep -E "lib/|Managed"
# lib/arm64-v8a/libil2cpp.so   75,886,208   ← IL2CPP! (Mono would ship Assembly-CSharp.dll)
# assets/bin/Data/Managed/Metadata/global-metadata.dat 15,830,176
# assets/bin/Data/Managed/SymbolMap-ARM64 2,433,472   ← Unity's own symbol map, a gift
```

**The decision tree you just walked** (memorize this — it is the first fork in
every Unity RE):

```
APK has assets/bin/Data/Managed/Metadata/global-metadata.dat?
├─ YES → IL2CPP build → need Il2CppDumper + native decompiler (Labs 4–6)
└─ NO  → Mono build   → assets/bin/Data/Managed/Assembly-CSharp.dll
                        → decompile directly with ILSpy/dnSpy (much easier)
```

## 1.4 The 3,533 hash-named files

```bash
unzip -l caocao.apk | grep -c "assets/bin/Data/"
# 3533 (plus the well-known fixed names)
```

Names look like `0025d75154693ff4a93878299b17b911` and one special
`0000000000000000f000000000000000`. **Hypothesis drill:** are these GUIDs or
content hashes? Test in Lab 02 by hexdumping a few and comparing content across
files; the answer (Unity's serialized-file "GUID naming" from this build
pipeline) unlocks automated asset indexing (Lab 05) — and becomes the base of
the hashing DSA deep-dive (`dsa/01-hashing-content-addressing.md`).

## 1.5 DEX census — how many ways does Java wrap the game?

```bash
unzip -p caocao.apk classes.dex | strings | grep -oE '^L(com|kr)/[a-z0-9_]+/' \
  | sort | uniq -c | sort -rn | head
```

Evidence:

```
563 com/tapjoy/    142 com/facebook/    84 com/google/
 59 com/nexon/      39 com/unity3d/     29 com/adjust/
 23 com/naver/      13 com/bumptech/    (glide image loader)
```

Reading: the *Java side is SDK glue* (login/ads/push), the *game is in
libil2cpp.so*. This asymmetry dictates the whole recovery strategy: Java = 5%
of effort (Lab 03), IL2CPP = 95% (Labs 4–8).

## 1.6 Exercises

- **1-A** Reproduce the Tapjoy key decode; then base64-decode
  `MTU5MjY1OTQ0OQ` from the notice URL and explain the difference in magnitude.
- **1-B** Count permissions in the manifest (string-pool route) and compare
  with what a modern Unity game needs. Which are legacy SDK artifacts?
- **1-C** Map each of the 18 scenes to a guess of what it does, then verify
  your guesses in Lab 06 against decompiled `GameFlowController`.
- **1-D (DSA preview)** Compute SHA-256 of three `bin/Data` files; observe no
  filename matches any hash. So what are the names? Write your hypothesis down.

Solutions: `solutions/01.md`.
