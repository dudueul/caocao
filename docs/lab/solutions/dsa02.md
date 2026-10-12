# Solutions — dsa-02 (FlatBuffers layout)

## 3-A: decode of `flatbuffer_sample.bin` (32 bytes)

```
00: 10 00 00 00   root uoffset = 16        → table starts at 16
04: 0A 00         vtable size = 10         (4 + 2*3 fields)
06: 10 00         table size  = 16         (4 soffset + 3*4 scalars)
08: 04 00         field0 (PPlaceId)  at table+4
0A: 08 00         field1 (HistoryId) at table+8
0C: 0C 00         field2 (HistoryKey) at table+12
0E: 00 00         alignment padding (table start must be 4-aligned)
10: 0C 00 00 00   soffset = table(16) − vtable(4) = 12  → vt = 16−12 = 4
14: 07 00 00 00   PPlaceId   = 7
18: 2A 00 00 00   HistoryId  = 42
1C: 39 05 00 00   HistoryKey = 1337
```

Walk: root@0 → table@16; vt = 16 − soffset@16 = 4; field0 offset = u16@4+4=4 →
value = i32@16+4 = **7**; field1 = u16@4+6=8 → i32@24 = **42**; field2 = 12 →
i32@28 = **1337**.

## 3-C: vtable dedup

Safe when two tables have identical *field presence pattern and layout* —
entries compare equal as u16 arrays. Unsafe when field sets differ (an absent
field is encoded as 0 in one table but present in the other). FNV-1a over the
vtable bytes with an open-addressing set achieves near-100% hit rate on
generated schemas (fields are homogeneous), measured in the exercise harness.

## 3-D: notes

`Createkey_building_history(builder, a, b, c)` — builder call order inside is
`StartObject(3); PrependInt32(2, c, default); PrependInt32(1, b, default);
PrependInt32(0, a, default); Offset(r) = EndObject()` — fields written in
reverse id order because the buffer grows downward. Your `.fbs` generator
maps `int → int`, `short → short`, `string → string`, etc., and nested tables
by dependency order.
