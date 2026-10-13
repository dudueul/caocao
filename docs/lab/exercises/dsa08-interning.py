#!/usr/bin/env python3
"""dsa08: string-table interning over the REAL 19,897 IL2CPP literals —
blob+(offset,len) vs naive objects: memory + lookup cost comparison."""
import json, os, sys, time

LIT = os.path.expanduser("~/work/caocao/extracted/il2cpp/stringliteral.json")
literals = [x["value"] for x in json.load(open(LIT))]

# 1. naive: one Python string object per literal (they're already interned by
#    the loader — simulate the un-interned cost via repeated copies)
naive_mem = sum(len(s.encode()) + 49 for s in literals)  # ~49B object overhead

# 2. blob + (offset, len): one bytes blob + 8-byte pairs
blob = "\x00".join(literals).encode()
table = []
off = 0
for s in literals:
    table.append((off, len(s.encode())))
    off += len(s.encode()) + 1
blob_mem = len(blob) + len(table) * 16

# lookup: decode round-trip for every entry
t0 = time.perf_counter()
for o, l in table:
    blob[o:o + l].decode()
t1 = time.perf_counter()

print(f"literals={len(literals)}")
print(f"naive ≈ {naive_mem/1e6:.1f} MB | blob ≈ {blob_mem/1e6:.1f} MB "
      f"({100*blob_mem/naive_mem:.0f}% of naive)")
print(f"blob decode of all {len(table)}: {1000*(t1-t0):.1f} ms")
