# PyGhidra post-script for Ghidra 12.x headless.
# Applies Il2CppDumper script.json: renames 163k methods, labels string
# literals and Il2CppClass metadata so decompiled output is readable.
# Usage: -postScript il2cpp_rename.py <path-to-script.json>
# @category Il2Cpp
import json

args = getScriptArgs()
if not args:
    raise Exception("usage: il2cpp_rename.py <script.json>")
data = json.load(open(args[0]))

fm = currentProgram.getFunctionManager()
base = currentProgram.getImageBase()
USER_DEFINED = ghidra.program.model.symbol.SourceType.USER_DEFINED

done = skipped = 0
for m in data.get('ScriptMethod', []):
    try:
        addr = base.add(m['Address'])
        name = m['Name'].replace(' ', '-')
        f = fm.getFunctionAt(addr)
        if f is None:
            f = createFunction(addr, None)
        if f is not None:
            f.setName(name, USER_DEFINED)
        else:
            createLabel(addr, name, True, USER_DEFINED)
        done += 1
    except Exception:
        skipped += 1
print('methods renamed: %d, skipped: %d' % (done, skipped))

done = 0
for s in data.get('ScriptString', []):
    try:
        addr = base.add(s['Address'])
        name = s['Name'].replace(' ', '-').replace('"', '')
        if len(name) > 180:
            name = name[:180]
        createLabel(addr, 'str_' + name, True, USER_DEFINED)
        done += 1
    except Exception:
        pass
print('string labels: %d' % done)

done = 0
for md in data.get('ScriptMetadata', []):
    try:
        addr = base.add(md['Address'])
        name = md['Name'].replace(' ', '-').replace('/', '.')
        if len(name) > 180:
            name = name[:180]
        createLabel(addr, 'class_' + name, True, USER_DEFINED)
        done += 1
    except Exception:
        pass
print('metadata labels: %d' % done)

done = 0
for mm in data.get('ScriptMetadataMethod', []):
    try:
        addr = base.add(mm['Address'])
        name = mm['Name'].replace(' ', '-')
        if len(name) > 180:
            name = name[:180]
        createLabel(addr, 'mm_' + name, True, USER_DEFINED)
        done += 1
    except Exception:
        pass
print('metadata methods: %d' % done)
print('ALL DONE')
