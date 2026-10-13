# dsa-08 — String Tables & Interning: 19,897 literals, one blob

**Grounding evidence:** `extracted/il2cpp/stringliteral.json` — 19,897 string
literals, each `{value, address}`. IL2CPP stores ALL literal strings in a
single data blob inside `global-metadata.dat`; code references them by
`(offset, length)`. (Our URL discoveries — `ws://{0}/socket.io/...`,
`nxm-caocao-ww.nexon.com:9080` — all came from mining this table.)

## 1. Why interning wins (measure, don't memorize)

- **Memory:** one copy of each distinct string; references are 8-byte
  pointers/offsets. 19,897 literals avg ~30 chars ≈ ~0.6 MB — vs naive
  per-use copies multiplying that by call-site count.
- **Speed:** equality on interned strings = pointer compare (O(1)) vs
  byte-compare (O(n)). Protocol dispatch (compare event name) hits this
  millions of times.
- **Cache:** contiguous blob = sequential prefetch; a heap of scattered
  std::string objects is a cache-miss festival.

**Exercise 8-A:** load `stringliteral.json`; build (a) naive per-string
objects, (b) one blob + (offset,len) table; compare memory (Python: measure
with `sys.getsizeof` sums + the blob size) and lookup cost (pointer-eq vs
string-eq in a dict keyed 1M times). Report both tables.

## 2. The metadata string table is *sorted by nothing* — how lookups work

IL2CPP's literal table is just an array of (length, offset) pairs; the
*literal index* is what code embeds. That's compile-time binding — the
lookup problem only exists for tools like Il2CppDumper (which re-derives
index→value once, O(n), then never again). Contrast with the *runtime* string
heap (type names) where binary search + cache line alignment matter; that's
why `global-metadata.dat` keeps name data in its own section
(header field `stringLiteralDataOffset` — Lab 02 §2.4).

## 3. Compression & the game's own trick

The game ships text assets (`Localization_*.txt` per language). Text asset
sizes matter on 2016 phones. **Exercise 3-A:** take the 5 localization files
from the export; compute gzip/brotli sizes; explain why games ship
*per-language* rather than monolingual-plus-patch (delta downloads), and
design the patch format: `bsdiff`-style binary diff vs key-value append-only
log — argue trade-offs for a 5MB text table.

## 4. Auto-grading

`verify-lab.sh dsa08`: checks 8-A's blob construction (round-trip equality
for all 19,897 values) and that your (offset,len) table uses < 1/3 the
memory of naive.

## 5. Measured reference (2026-10-12, on the real 19,897 literals)

`exercises/dsa08-interning.py`:

```
naive ≈ 1.5 MB | blob+(offset,len) ≈ 0.8 MB (57% of naive)
full blob decode: 4.7 ms
```

The 49%-saving is *before* pointer/BIAS accounting of a real runtime (C# adds
object headers per string — ~24B each → naive ≈ 2.1 MB → blob = 38%). Note
Python strings are themselves interned here — the exercise deliberately
simulates the un-interned layout via size accounting.
