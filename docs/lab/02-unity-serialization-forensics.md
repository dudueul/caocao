# Lab 02 — Unity Serialization Forensics (reading bytes like the engine does)

**Goal:** understand Unity's `SerializedFile` container by parsing its header
*by hand* — the same bytes we recovered from the APK. This lab teaches binary
format reading: endianness, header fields, and how versioning is encoded.

---

## 2.1 The evidence (real bytes from caocao.apk)

Three different data files, first 48 bytes each (xxd):

```
0000000000000000f000000000000000 (142,932 B)
00000000: 0000 00e8 0002 2e54 0000 0011 0000 1000
00000010: 0000 0000 3230 3138 2e32 2e31 3666 3100
00000020: 0d00 0000 0002 0000 0015 0000 0000 ffff

0025d75154693ff4a93878299b17b911 (6,536 B)
00000000: 0000 0399 0000 1988 0000 0011 0000 1000
00000010: 0000 0000 3230 3138 2e32 2e31 3666 3100
00000020: 0d00 0000 0009 0000 0001 0000 0000 ffff

0122fd93b0c38dc4d8fbc6c1e5e8dbdb (1,074,848 B)
00000000: 0000 64b6 0010 66a0 0000 0011 0000 64d0
00000010: 0000 0000 3230 3138 2e32 2e31 3666 3100
00000020: 0d00 0000 0023 0000 0001 0000 0000 ffff
```

## 2.2 Field-by-field decode (Unity SerializedFile, header version ≥ 7)

**The trap that makes this a real exercise:** the header's first four integers
are **big-endian** — a legacy compatibility choice — while the platform field
*after the version string* is little-endian like the rest of the file. The
header mixes endianness. Wrong-byte-order readings give plausible-looking
garbage (`version = 285,212,672`), which is exactly how students learn to
distrust their first parse.

Correct layout for these files:

| Offset | Size | Field | File 1 value | Byte order |
|---|---|---|---|---|
| 0x00 | 4 | metadata size (legacy) | 232 | **BE** |
| 0x04 | 4 | file size (legacy) | 142,932 (matches real size!) | **BE** |
| 0x08 | 4 | **version** | **17** | **BE** |
| 0x0C | 4 | data offset | 0x1000 | **BE** |
| 0x10 | 4 | reserved | 0 | — |
| 0x14 | 12 | version string | `2018.2.16f1\0` | ASCII, 4-aligned |
| 0x20 | 4 | target platform | 13 (Android) | LE |

> The version string is NUL-terminated and padded to a 4-byte boundary so
> readers can memcpy word-at-a-time. The first-byte-order quirk survives from
> Unity's earliest formats — **formats accrete, they rarely clean up** (and a
> format that must keep reading decade-old files has no choice).

**Exercise 2-A:** parse the three headers above and fill a table: version,
data offset, version string, target platform. Why is the version string
padded to a 4-byte boundary? (Answer: Unity aligns strings to 4 bytes for
fast reads.)

## 2.3 Why the files have hash names

Unity assigns every imported asset a **GUID**. This build pipeline stores each
SerializedFile under its 32-hex GUID name. The special
`0000000000000000f000000000000000` is the engine-reserved "resource" container
(the `f000` block marks the built-in resource set).

**Content-addressing vs GUIDs:** our files are *named by identity* (GUID), not
by content hash — Lab 01's hypothesis test resolves: SHA-256 of contents ≠
filename. But *we can build* a content-addressed index over them, which is
exactly the DSA exercise in `dsa/01-hashing-content-addressing.md` (hash
tables, Merkle-style dedup, and why deduplication saves 100s of MB in games).

## 2.4 The IL2CPP metadata header (the other critical container)

```
global-metadata.dat bytes 0..15:
00000000: af1b b1fa 1800 0000 1001 0000 c86d 0200
```

| Offset | Value | Meaning |
|---|---|---|
| 0x00 | `0xFAB11BAF` | magic ("FABULOUS" — Il2CppDumper literally checks this) |
| 0x04 | 24 (0x18) | metadata version (Unity 2018.x era) |
| 0x08 | 0x110 | string literal data offset |
| 0x0C | 0x26dc8 | string literal data size |

From version 24, Il2CppDumper parses ~30 more header pairs (offset/size) that
point to type definitions, method definitions, field default values, string
literals... — a **structured database of your entire C# codebase**. That is
why IL2CPP games lose *implementation* but not *interface*: names, hierarchy,
field layouts all survive.

## 2.5 Exercises

- **2-B** Write a 30-line Python that opens any file from `bin/Data`, parses
  the header, and prints version/platform/offset. Verify on 10 random files —
  all must say 17 / Android / 2018.2.16f1. (Starter in `exercises/02-B.py`.)
- **2-C** The `f000...` file is 142,932 B but its header says file size
  0x542e0200. Explain the discrepancy (hint: that field is *legacy* and no
  longer the true size; true size is in the container entry table).
- **2-D (DSA preview)** The header uses offset/size *pairs* instead of
  pointers. Compare with FAT inodes and C structs; why are pairs ideal for a
  memory-mapped, zero-copy loader? Continue in `dsa/02-flatbuffers-layout.md`.

Solutions: `solutions/02.md`.
