#!/usr/bin/env python3
"""gen_fb_cs.py — fill the 67 GuildWarFlatBuffer stub structs with real
FlatBuffers generated-style bodies.

GROUNDED IN THE REAL BINARY: decompiled `Createkey_building_history`
(entry 0x24c3640) shows Prep(4,12) + reversed PutInt + get_Offset — these
are FlatBuffers **structs** (inline, no vtable), not tables. Layout rule:
sequential fields, each aligned to its size; struct size padded to max align.

Reads: analysis/fbs/GuildWarFlatBuffer.fbs (from gen_fbs.py)
Writes: client/Assets/Scripts/Assembly-CSharp/GuildWarFlatBuffer/*.cs
(matched to the vendored flatbuffers v1.6.0 C# API, namespace `FlatBuffers`)
"""
import os, re

ROOT = os.path.expanduser("~/work/caocao")
FBS = os.path.join(ROOT, "analysis/fbs/GuildWarFlatBuffer.fbs")
OUT = os.path.join(ROOT, "client/Assets/Scripts/Assembly-CSharp/GuildWarFlatBuffer")

GET = {"int": "GetInt", "short": "GetShort", "uint": "GetUint", "long": "GetLong",
       "ulong": "GetUlong", "float": "GetFloat", "double": "GetDouble", "ubyte": "Get"}
PUT = {"int": "PutInt", "short": "PutShort", "uint": "PutUint", "long": "PutLong",
       "ulong": "PutUlong", "float": "PutFloat", "double": "PutDouble", "ubyte": "PutByte"}
CSHARP_TYPE = {"int": "int", "short": "short", "uint": "uint", "long": "long",
               "ulong": "ulong", "float": "float", "double": "double", "ubyte": "byte"}
SIZES = {"int": 4, "uint": 4, "float": 4, "long": 8, "ulong": 8, "double": 8,
         "short": 2, "ubyte": 1}


def struct_layout(fields):
    offsets, off, max_align = [], 0, 1
    for fname, ftype in fields:
        size = SIZES[ftype]
        align = size
        off = (off + align - 1) // align * align
        offsets.append((off, size))
        off += size
        max_align = max(max_align, align)
    total = (off + max_align - 1) // max_align * max_align
    return offsets, total, max_align


def gen(name, fields):
    offsets, total, max_align = struct_layout(fields)
    L = ["using FlatBuffers;", "", "namespace GuildWarFlatBuffer", "{",
         "\tpublic struct " + name + " : IFlatbufferObject", "\t{",
         "\t\tprivate Struct __p;", "",
         "\t\tpublic ByteBuffer ByteBuffer", "\t\t{", "\t\t\tget", "\t\t\t{",
         "\t\t\t\treturn __p.bb;", "\t\t\t}", "\t\t}", ""]
    for (fname, ftype), (offset, size) in zip(fields, offsets):
        typ = CSHARP_TYPE[ftype]
        getter = GET[ftype]
        cast = "(" + typ + ")" if getter == "Get" else ""
        L.append("\t\tpublic " + typ + " " + fname)
        L.append("\t\t{")
        L.append("\t\t\tget")
        L.append("\t\t\t{")
        L.append("\t\t\t\treturn " + cast + "__p.bb." + getter + "(__p.bb_pos + " + str(offset) + ");")
        L.append("\t\t\t}")
        L.append("\t\t}")
        L.append("")
    L.append("\t\tpublic void __init(int _i, ByteBuffer _bb)")
    L.append("\t\t{")
    L.append("\t\t\t__p = new Struct(_i, _bb);")
    L.append("\t\t}")
    L.append("")
    L.append("\t\tpublic " + name + " __assign(int _i, ByteBuffer _bb)")
    L.append("\t\t{")
    L.append("\t\t\t__p = new Struct(_i, _bb);")
    L.append("\t\t\treturn this;")
    L.append("\t\t}")
    L.append("")
    args = ", ".join(CSHARP_TYPE[t] + " " + f for f, t in fields)
    L.append("\t\tpublic static Offset<" + name + "> Create" + name + "(FlatBufferBuilder builder, " + args + ")")
    L.append("\t\t{")
    L.append("\t\t\tbuilder.Prep(" + str(max_align) + ", " + str(total) + ");")
    for fname, ftype in reversed(fields):
        L.append("\t\t\tbuilder." + PUT[ftype] + "(" + fname + ");")
    L.append("\t\t\treturn new Offset<" + name + ">(builder.Offset);")
    L.append("\t\t}")
    L.append("\t}")
    L.append("}")
    return "\n".join(L) + "\n"


def parse_fbs(path):
    tables = []
    cur = None
    for line in open(path):
        m = re.match(r"\s*table (\w+) \{", line)
        if m:
            cur = (m.group(1), [])
            continue
        if line.strip() == "}":
            if cur:
                tables.append(cur)
            cur = None
            continue
        m = re.match(r"\s+(\w+):(\w+);", line)
        if m and cur:
            cur[1].append((m.group(1), m.group(2)))
    return tables


def main():
    tables = parse_fbs(FBS)
    print("parsed %d structs from .fbs" % len(tables))
    for name, fields in tables:
        open(os.path.join(OUT, name + ".cs"), "w").write(gen(name, fields))
    print("generated %d STRUCTS into %s" % (len(tables), OUT))


if __name__ == "__main__":
    main()
