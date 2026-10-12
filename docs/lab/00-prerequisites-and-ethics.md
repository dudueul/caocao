# Lab 00 — Course Overview, Ethics & Environment Setup

> **Course:** Reverse Engineering a Real Android Game → Rebuilding it in Unity 6 + Cloudflare
> **Target:** `_release/caocao.apk` (Unity 2018.2.16f1, IL2CPP, Socket.IO online game)
> **Level:** First contact with binary RE. No prior decompilation experience assumed.

---

## 0.1 Why this course is different

Every artifact you will study in this course is **real**: a commercially shipped
Android game (Unity IL2CPP build, 94 MB APK, 3,533 data files, 75 MB native
library). We are not doing toy crackmes. The project's own owner lost the
source code; we recover behavior and assets from the shipped binary and rebuild
a faithful client + a brand-new serverless backend.

Everything you do here you can **reproduce** — every command in this textbook
was actually executed to produce the results shown.

## 0.2 Ethics & legality (read before touching any tool)

Reverse engineering is a legitimate discipline — interoperability, security
research, malware analysis, software preservation, and **recovering your own
lost work**. It is also regulated:

1. **Authorization.** Only analyze software you own or are authorized to
   examine. REA's own disclaimer states: *analysis of software you own or are
   authorized to use*. In this course, the APK's owner commissioned the work.
2. **DRM/circumvention laws** vary by jurisdiction (DMCA §1201 in the US,
   EUCD in the EU, etc.). Interoperability and preservation exceptions exist;
   know yours.
3. **Never redistribute** decompiled third-party code (the APK embeds Nexon,
   Tapjoy, Facebook, Adjust SDKs — we treat those as *reference only*).
4. **Live-service targets.** Never probe live commercial servers without
   permission. Our target's official servers are long dead; our protocol study
   is purely static, and our rebuilt server is our own code.

**Lab exercise 0-A:** Draft a one-page "authorization memo" for a target you
analyze: who owns it, why you may analyze it, what you will and will not do
with the results. (Solution: `solutions/00-A.md`.)

## 0.3 The map: what is inside an Android game APK

An APK is just a ZIP. Ours decompresses to 366 MB:

```
caocao.apk (zip)
├── AndroidManifest.xml          ← binary XML: package, activities, permissions
├── classes.dex / classes2.dex   ← Dalvik bytecode (Java side): 3,370 classes
├── resources.arsc + res/       ← compiled Android resources (icons, layouts)
├── lib/arm64-v8a/*.so           ← native libraries (ELF, ARM64):
│     libil2cpp.so   75.8 MB    ←  YOUR GAME LOGIC, compiled C++ from C#
│     libunity.so    16.3 MB    ←  the Unity engine
│     libngsm.so, libBlueDoveMediaRender.so   ← vendor plugins
├── assets/bin/Data/             ← Unity content: 3,533 hash-named serialized files
│     globalgamemanagers          ← engine config + build settings + scene list
│     Managed/Metadata/global-metadata.dat  ← IL2CPP type/method/string catalog
└── META-INF/                    ← signatures (v1 JAR signing here)
```

**The single most important fact of this course:** Unity ships two scripting
backends. *Mono* keeps your C# as IL inside `Assembly-CSharp.dll` (trivially
decompilable). *IL2CPP* transpiles C# to C++ and compiles to machine code —
but keeps a **metadata catalog** (`global-metadata.dat`) that names every
class, method, field and string literal so the GC and reflection can work.
That catalog is what makes IL2CPP reversing tractable. We will exploit it in
Lab 04.

## 0.4 Toolchain (install once)

All tools install **without root** into `~/tools`. Reproduce with:

```bash
mkdir -p ~/tools
# Node.js ≥ 22.19 (REA needs it) — via fnm
curl -fsSL https://fnm.vercel.app/install | bash && fnm install 24
# JDK 21 (Temurin) — Ghidra needs it
curl -fL -o ~/tools/jdk21.tar.gz \
  https://api.adoptium.net/v3/binary/latest/21/ga/linux/x64/jdk/hotspot/normal/eclipse
tar xzf ~/tools/jdk21.tar.gz -C ~/tools
# Ghidra 12.1.x (native RE workbench)
curl -fL -o ~/tools/ghidra.zip https://github.com/NationalSecurityAgency/ghidra/releases/download/Ghidra_12.1.4_build/ghidra_12.1.4_PUBLIC_20260921.zip
unzip -q ~/tools/ghidra.zip -d ~/tools
# jadx (DEX→Java decompiler)
curl -fL -o ~/tools/jadx.zip https://github.com/skylot/jadx/releases/download/v1.5.6/jadx-1.5.6.zip
mkdir -p ~/tools/jadx && unzip -q ~/tools/jadx.zip -d ~/tools/jadx
# .NET (Il2CppDumper + AssetRipper are C# apps)
curl -sSL https://dot.net/v1/dotnet-install.sh -o dotnet-install.sh
./dotnet-install.sh --channel 8.0 --install-dir ~/.dotnet
# REA — Reverse Engineer Anything (agent-driven RE: MCP + CLI)
npm install --global rea-agents
```

Then wire Ghidra into REA and verify:

```bash
export JAVA_HOME=~/tools/jdk-21.0.12.1+1
export GHIDRA_INSTALL_DIR=~/tools/ghidra_12.1.4_PUBLIC
rea doctor --provider ghidra --json    # → {"healthy": true, ...}
```

Actual result on the course machine (2026-10-12): every ghidra-* check
`healthy`, JDK accepted `21.0.12.1; 64-bit`.

## 0.5 Glossary starters

| Term | Meaning (as used here) |
|---|---|
| APK / DEX / ARSC | Android package / Dalvik bytecode / compiled resources |
| IL2CPP | Unity's C#→C++→native compiler pipeline |
| global-metadata.dat | IL2CPP's runtime type/string catalog (magic `0xFAB11BAF`) |
| SerializedFile | Unity's object-container format (version int, object table) |
| GUID / content hash | why our 3,533 data files have names like `0025d751...` |
| ELF / AARCH64 | Linux native object format / 64-bit ARM instruction set |
| Engine.IO / Socket.IO | WebSocket transport framing / event layer on top |

## 0.6 Exercises

- **0-A:** ethics memo (above).
- **0-B:** Run `file caocao.apk` and `unzip -l caocao.apk | wc -l`. Explain why
  the ZIP entry count (4,863) differs from the file count inside `assets/`.
  *Hint: directories are entries too.*
- **0-C:** `sha256sum caocao.apk` — record it; every lab will re-verify the
  same artifact. (Result: recorded in `scripts/verify-lab.sh`.)

➡ Next: **Lab 01 — APK Anatomy & First Contact**.
