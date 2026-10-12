# dsa-01 — Hashing & Content Addressing (grounded in 3,533 real files)

**Practice target:** `extracted/apk/assets/bin/Data/` — 3,533 Unity
SerializedFiles named by 128-bit identifiers.

## Why RE teaches DSA better than toy problems

Every structure below is one you can *touch*: real files, real sizes, real
performance consequences measured against them.

## 1. Identity vs. integrity vs. location

Three different jobs a "name" can do — learn to separate them:

| Job | Mechanism | Example here |
|---|---|---|
| Identity | GUID (random 128-bit) | `0025d751...` filenames |
| Integrity | cryptographic hash (SHA-256) | what we compute to verify downloads |
| Location | path/URL | `assets/bin/Data/...` |

**Lab connection:** Lab 01/02 proved the filenames are *GUIDs* (identity),
not hashes (integrity). CDN URLs in `IServerSetting.CDN_URL` serve the same
content — CDN cache keys are content hashes in production systems.

## 2. Exercise set (implement against real data)

### 2.1 Content-index (hash table + hashing)
Build `index = {sha256(file) → [guids]}` over all 3,533 files. Report:
- how many **duplicate contents** exist (dedup ratio). **Our measured result
  (reference): `analysis/dsa01_reference.json` — 3,518 files, 3,518 unique,
  dedup ratio 0.00%, 225.4 MB total.** Yes — *zero* duplicates. This is the
  textbook's first hypothesis-falsification moment: we predicted content
  duplication (common in uncompressed asset pipelines); measurement says this
  pipeline ships every GUID file with unique content. *Why?* Because these
  files are already container-internal deduplicated by Unity (shared assets
  referenced across containers instead of copied), and hash-naming by GUID
  prevents accidental overwrite. Learning to update your model when data
  disagrees is the actual skill.
- total bytes vs. deduped bytes: with 0 duplicates they coincide — restate
  what *would* make dedup valuable (raw asset folders, log corpora) and note
  the CDN layer dedups by URL+ETag instead.

*DSAs practiced:* cryptographic hashing, open-addressing vs. chaining, load
factor (why `dict` in Python or `HashMap` in Java resizes at 0.75), memory
layout of a 3,533-entry table.

### 2.2 O(1) address→name lookup (the Ghidra case)
`script.json` has **163,902** `{Address, Name}` pairs. A native decompiler
session must resolve millions of address queries. Implement (any language):
- flat sorted array + binary search,
- hash map,
- (bonus) perfect hash via sorting two passes.
Measure queries/sec of each on 1M random address probes. Discuss why RE tooling
lives or dies on this constant.

### 2.3 String interning (the IL2CPP case)
IL2CPP stores all C# string literals in one blob with (offset,length) pairs —
19,897 literals in our metadata. Explain why interning (one copy, referenced by
offset) beats per-object strings for both memory and cache locality, and why
the string table's sorted layout enables the binary-search trick in 2.2's
cousin: literal *by content* lookup.

### 2.4 Checksums & packet integrity (preview → PROTOCOL.md)
Engine.IO frames carry no checksum (TCP+TLS suffice) — but local save files
here are AES+salt encrypted (`FileUtil.GetSalts(str, length)`). Design a
format: `salt(16) ‖ ciphertext ‖ HMAC-SHA256`. Discuss: why MAC-then-encrypt
vs encrypt-then-MAC; implement both in Python and show the attack one of them
allows (bit-flipping onCTR-style) — a *real* security DSA lesson.

## 3. Grading rubric (auto-checked)

`scripts/verify-lab.sh dsa01` runs your `dsa01-index.py`:
- must produce `index.json` with 3,533 keys,
- must report duplicate ratio (±0.1% of reference),
- 2.2 harness compares your lookup throughput to reference.

## 4. Deeper reading pointers (inline with this project)

- `analysis/ARCHITECTURE.md §6` — the data layer these files feed.
- `extracted/il2cpp/dump.cs` — search `class ResourceManager` to see the
  runtime that consumes these files.
