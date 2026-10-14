"""Recover the order in which the client loads its baked data tables.

PermanentData.Reload calls `<Table>.Load(ref <Table>)` once per table. We scan
libil2cpp.so for ARM64 `BL` instructions whose target is one of the 239
`<Table>$$Load` entry points (addresses from Il2CppDumper's script.json) and
group the call sites by containing method.

usage: load_order.py <libil2cpp.so> <script.json> <schema.json> <out.json>
"""
import bisect, json, re, struct, sys

so_path, script_path, schema_path, out_path = sys.argv[1:5]
so = open(so_path, 'rb').read()
sj = json.load(open(script_path))
methods = sorted((m['Address'], m['Name']) for m in sj['ScriptMethod'])
addrs = [ad for ad, _ in methods]
tables = {t['table'] for t in json.load(open(schema_path, encoding='utf-8'))}

load_addr = {}
for ad, name in methods:
    m = re.match(r'^(\w+)\$\$Load$', name)
    if m and m.group(1) in tables:
        load_addr[ad] = m.group(1)

lo, hi = addrs[0], min(len(so), addrs[-1] + 0x4000)
callers = {}
for off in range(lo, hi - 3, 4):
    w = struct.unpack_from('<I', so, off)[0]
    if (w & 0xFC000000) != 0x94000000:          # BL imm26
        continue
    imm = w & 0x03FFFFFF
    if imm & 0x02000000:
        imm -= 0x04000000
    target = off + imm * 4
    if target in load_addr:
        k = bisect.bisect_right(addrs, off) - 1
        callers.setdefault(methods[k][1], []).append((off, load_addr[target]))

result = {fn: [t for _, t in sorted(calls)] for fn, calls in callers.items()}
json.dump(result, open(out_path, 'w'), indent=1)
for fn, calls in sorted(result.items(), key=lambda x: -len(x[1])):
    print(f'{fn}: {len(calls)} Load calls')
