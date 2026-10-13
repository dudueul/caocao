# CAOCAAO — Reverse Engineering & Rebuild Lab Course

A first-contact textbook where every page is grounded in a real shipped
Android game (`com.nexon.caocao`, Unity 2018.2.16f1, IL2CPP, Socket.IO).
The owner lost the source; the course rebuilds the client (Unity 6) and the
server (Cloudflare Workers) from the binary — and every artifact you study
here was captured **during that actual rebuild**.

## How to use this course

1. Read a lab → reproduce its commands against `_release/caocao.apk` →
   submit exercises → verify with `scripts/verify-lab.sh <lab>`.
2. Every lab's "evidence" blocks are literal outputs from this project —
   rerun them and you get the same bytes.
3. dsa-* documents teach data structures & algorithms **on the recovered
   code** (real pathfinders, real wire formats, real queues).

## Chapters

| Lab | Title | Verifier |
|---|---|---|
| 00 | [Prerequisites, ethics & environment](00-prerequisites-and-ethics.md) | `apk` |
| 01 | [APK anatomy & first contact](01-apk-anatomy.md) | `apk` |
| 02 | [Unity serialization forensics](02-unity-serialization-forensics.md) | `lab02` |
| 03 | [The Java layer: DEX → smali → Java](03-jadx-dex-layer.md) | manual |
| 04 | [IL2CPP: metadata dumping](04-il2cpp-dumping.md) | `il2cpp` |
| 05 | [Asset forensics (AssetRipper)](05-asset-ripping.md) | manual |
| 06 | [Native analysis: Ghidra + REA](06-ghidra-rea-native-analysis.md) | manual |
| 07 | [Protocol reconstruction](07-protocol-reconstruction.md) | `lab07` |
| 08 | [Rebuild & verification](08-rebuild-verification.md) | `lab07`,`dsa*` |
| 09 | [Cross-platform channels: Android, iOS, WebGL](09-cross-platform-channels.md) | `lab07` |

## DSA companions

| Doc | Topic | Practice target |
|---|---|---|
| [dsa-01](dsa/01-hashing-content-addressing.md) | Hashing, content addressing, O(1) lookups | 3,518 data files; 163,902-entry symbol table |
| [dsa-02](dsa/02-flatbuffers-layout.md) | FlatBuffers: tables vs structs (hand-decode) | real game wire formats |
| [dsa-03](dsa/03-astar-battle-grid.md) | A\*, priority queues, tie-breaking | `AStarPathFinder` (original, recovered + diffed) |
| [dsa-04](dsa/04-event-queues-acks.md) | Event queues, acks, timing wheels | `SocketIOComponent` design |
| [dsa-05](dsa/05-gacha-rng-fairness.md) | Weighted RNG, chi-square, determinism | gacha/treasure economy |
| [dsa-06](dsa/06-spatial-partitioning.md) | Grids, quadtrees, AoE templates | tile picking |
| [dsa-07](dsa/07-state-machines-event-sourcing.md) | FSMs, event sourcing, actors | battle log (D1) |
| [dsa-08](dsa/08-string-tables-interning.md) | String interning, compression | 19,897 IL2CPP literals |

## Exercises & solutions

- `exercises/` — starter code + binary fixtures (incl. the two FlatBuffer
  samples and the battle grid).
- `solutions/` — `00-01.md`, `02-05.md`, `06-08.md`, `dsa02.md`.

## Glossary

See Lab 00 §0.5 for the starter glossary; each chapter adds inline terms.

## Instructor notes

- All tool installs are root-free (`~/tools`); Lab 00 §0.4 is the setup.
- The pkill/self-match, PyGhidra-vs-Java, and analyzer-pipeline war stories
  (Labs 06 §6.2 / 07 §7.8) are first-class curriculum — real incidents beat
  hypothetical ones.
- License note: the game is the course owner's own product; third-party SDK
  code found inside (Nexon/NPA, Tapjoy, …) is *reference only* and never
  redistributed.
