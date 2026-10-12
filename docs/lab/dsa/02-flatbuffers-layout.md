# dsa-02 — FlatBuffers Memory Layout (decoding real game tables by hand)

**Grounding type (from `dump.cs`):**

```csharp
// Namespace: GuildWarFlatBuffer
public struct key_building_history : IFlatbufferObject // TypeDefIndex: 8528
{
    public int PPlaceId { get; }      // vtable slot → offset
    public int HistoryId { get; }
    public int HistoryKey { get; }
    public static Offset<key_building_history> Createkey_building_history(
        FlatBufferBuilder builder, int PPlaceId, int HistoryId, int HistoryKey) { }
}
```

Sixty-seven sibling types (`key_building`, `key_building_custom_function`, …)
in `GuildWarFlatBuffer` plus the generic `PImpl.DataTableImpl<...,FlatBufferDataType,...>`
loader are how this game stores every balance table. FlatBuffers was chosen by
the original developers for one reason: **zero-copy reads** — and that reason
is a masterclass in data-structure design.

## 1. The wire format (little-endian)

A FlatBuffers table sits in a buffer organized **backwards** (built bottom-up,
so the root is at the *front*):

```
offset 0:  root_offset:u32 ──┐
                             ▼
        [table header: soffset:i32 to vtable]
        [table fields: inline scalars or u32 offsets to strings/vectors]
vtable:  [vtable_size:u16][table_size:u16][field0_offset:u16][field1_offset:u16]...
```

To read field *N* of a table at address T:

```
vt = T - load_i32(T)          // vtable is BEFORE the table, negative soffset
fo = load_u16(vt + 4 + 2*N)   // field's offset from table start; 0 = absent
value = fo == 0 ? default : load_scalar(T + fo)
```

**That's the entire read path.** No allocation, no parsing — every access is
two loads and one add. Compare to JSON: tokenizing, tree building, string
copies. This is why a 2016 mobile MMO with 3,533 data tables chose it.

## 2. The DSA concepts hiding inside

| Concept | Where it appears |
|---|---|
| **Serpentine allocation** | builder writes grow *downward* while vtables grow *upward* — one buffer, two stacks |
| **Vtable deduplication** | identical vtables are reused (`FlatBufferBuilder` keeps a hash set — hashing over u16 arrays, exercise 2-C) |
| **Alignment** | scalars aligned to their size; strings prefixed by length; the *6-byte* minimum vtable |
| **Optional fields** | absent field = vtable entry 0 = default value — the "null island" pattern |
| **Versioning** | appending fields only grows the vtable: old readers keep working — schema evolution as a data structure |

## 3. Exercises (all auto-checkable)

- **3-A (hand decode)** Given the 32 bytes in `exercises/flatbuffer_sample.bin`
  (generated from the `key_building_history` schema), decode all three fields
  *with pen and paper*, then verify with the checker
  (`scripts/verify-lab.sh dsa02`).
- **3-B (implement a reader)** Write `read_table.py` implementing the 5-line
  read path above; run it over every FlatBuffer-looking file in `bin/Data`
  (files whose root_offset+4 chain resolves cleanly). Report how many of the
  3,518 are FlatBuffers and how many are Unity SerializedFiles.
- **3-C (vtable dedup)** Explain why deduping vtables is *safe* when tables
  share the field set but *unsafe* across different field sets, then implement
  the dedup (hash the u16 array, FNV-1a) and measure hit-rate on your corpus.
- **3-D (schema regeneration)** From `dump.cs`, extract every
  `GuildWarFlatBuffer` struct: property list + C# types → emit `caocao.fbs`.
  This is a *real deliverable* for the server rebuild (server reads the same
  tables to validate client actions). Script skeleton:
  `exercises/dsa02-fbs-gen.py`.

## 4. Reference answers / measured facts

- Field order in the vtable follows declaration order; scalar default for
  absent int = 0.
- `Createkey_building_history(builder, a, b, c)` shows exactly the builder
  call order: fields pushed **backwards** (c, b, a) then `StartObject(3)` /
  `Prep(...)` — because the buffer grows toward the front.
- Sample decoder output for `flatbuffer_sample.bin` is in `solutions/dsa02.md`.

## 5. Bridge to the rebuild

Our Cloudflare server will read the same tables: either (a) ship the original
FlatBuffers to Workers via R2 and decode with `@farcaster/flatbuffers`-style
reader we implement in dsa-02, or (b) transcode tables to D1 rows/JSON at
build time. Decision recorded in `analysis/PROTOCOL.md §6` once dsa-02
exercises are graded.
