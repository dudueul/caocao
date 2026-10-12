#!/usr/bin/env python3
"""gen_fbs.py — regenerate FlatBuffers schemas from Il2CppDumper dump.cs.

Parses every `IFlatbufferObject` struct (GuildWarFlatBuffer namespace and any
other FlatBuffers namespaces) and emits .fbs schema files. Grounded in the
real generated-code pattern:

    public struct key_building : IFlatbufferObject
    {
        public int PPlaceId { get; }        -> PPlaceId:int;
        public static Offset<key_building> Createkey_building(FlatBufferBuilder b, int PPlaceId)
    }

Usage: gen_fbs.py <dump.cs> <output_dir>
"""
import re, os, sys

TYPE_MAP = {
    "int": "int", "uint": "uint", "short": "short", "ushort": "ushort",
    "long": "long", "ulong": "ulong", "byte": "ubyte", "sbyte": "byte",
    "bool": "bool", "float": "float", "double": "double",
    "string": "string", "long": "long",
}

STRUCT_RE = re.compile(
    r"// Namespace: (\S+)\s*\npublic struct (\w+) : IFlatbufferObject\s*// TypeDefIndex: (\d+)\s*\{(.*?)\n\}",
    re.S)
PROP_RE = re.compile(r"public ([\w<>,\. ]+?) (\w+) \{ get; \}")
CREATE_RE = re.compile(r"public static Offset<\w+> Create(\w+)\(FlatBufferBuilder builder,\s*(.*?)\) \{ \}")


def parse(dump_path):
    src = open(dump_path, errors="ignore").read()
    tables = []
    for m in STRUCT_RE.finditer(src):
        ns, name, tdi, body = m.groups()
        if ns not in ("GuildWarFlatBuffer",) and "FlatBuffer" not in ns:
            continue
        props = PROP_RE.findall(body)
        # ByteBuffer property is scaffolding; skip it
        props = [(t, n) for t, n in props if n != "ByteBuffer"]
        tables.append({"ns": ns, "name": name, "tdi": tdi, "fields": props})
    return tables


def to_fbs(tables):
    by_ns = {}
    for t in tables:
        by_ns.setdefault(t["ns"], []).append(t)
    out = {}
    for ns, ts in by_ns.items():
        lines = [f"// regenerated from Il2CppDumper dump.cs — namespace {ns}",
                 f"// {len(ts)} tables; field order = vtable slot order", ""]
        for t in ts:
            lines.append(f"table {t['name']} {{")
            for ftype, fname in t["fields"]:
                lines.append(f"  {snake(fname)}:{TYPE_MAP.get(ftype, ftype)};")
            lines.append("}")
            lines.append("")
        out[ns] = "\n".join(lines)
    return out


def snake(name):
    # PPlaceId -> place_id is wrong for wire compat; keep original member names
    return name


def main(dump_path, out_dir):
    tables = parse(dump_path)
    print(f"parsed {len(tables)} flatbuffer structs")
    os.makedirs(out_dir, exist_ok=True)
    for ns, text in to_fbs(tables).items():
        path = os.path.join(out_dir, f"{ns}.fbs")
        open(path, "w").write(text)
        print(f"wrote {path} ({text.count(chr(10))} lines)")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2]))
