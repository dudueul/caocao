"""Extract the 239 baked data-table schemas from Il2CppDumper's dump.cs.

Pattern (all evidenced in dump.cs):
  [Serializable] public class _<Table> : ScriptableObject { bool isMatrix; string key; List<__<Row>> data; }
  [Serializable] public class __<Row> { <raw columns in serialized order> ; CopyTo(<Row> dest) }
  public class <Table> : PermanentBase { List<<Row>> data; ... RefineData(_<Table> src); Load(ref <Table>) }
  public class <Row> : PermanentBase { ObscuredX m_<col> ... }
"""
import json, re, sys
dump = sys.argv[1]
lines = open(dump, encoding='utf-8', errors='replace').read().split('\n')

cls_re = re.compile(r'^(?:public|internal|private)?\s*(?:sealed |abstract |static )*class (\S+)(?: : ([^/]+?))?\s*// TypeDefIndex: (\d+)')
field_re = re.compile(r'^\t(?:\[[^\]]*\]\s*)*(public|private|protected|internal)\s+(?:readonly\s+)?(?!static|const)([^;=]+?)\s+([A-Za-z_][A-Za-z0-9_<>]*); // 0x([0-9A-F]+)')
classes = {}
i = 0
while i < len(lines):
    m = cls_re.match(lines[i])
    if m:
        name, base, tdi = m.group(1), (m.group(2) or '').strip(), int(m.group(3))
        ns = ''
        for k in range(i - 1, max(i - 4, -1), -1):
            if lines[k].startswith('// Namespace:'):
                ns = lines[k][len('// Namespace:'):].strip(); break
        fields, methods = [], []
        j = i + 1
        while j < len(lines) and not lines[j].startswith('}'):
            fm = field_re.match(lines[j])
            if fm and 'static' not in lines[j].split('//')[0] and ' const ' not in lines[j]:
                fields.append({'name': fm.group(3), 'type': fm.group(2).strip(), 'offset': int(fm.group(4), 16)})
            mm = re.match(r'^\t(?:public|private|protected|internal)[^(]*?\s([A-Za-z_.<>]+)\(([^)]*)\) \{ \}', lines[j])
            if mm: methods.append(mm.group(1) + '(' + mm.group(2) + ')')
            j += 1
        if name not in classes or ns == '':
            classes[name] = {'name': name, 'base': base, 'typeDefIndex': tdi, 'namespace': ns, 'fields': fields, 'methods': methods}
        i = j
    i += 1

tables = []
for name, c in classes.items():
    if not name.startswith('_') or name.startswith('__') or 'ScriptableObject' not in c['base']:
        continue
    fn = {f['name']: f for f in c['fields']}
    if not {'isMatrix', 'key', 'data'} <= set(fn):
        continue
    dm = re.match(r'List<(\S+)>$', fn['data']['type'])
    raw_row = dm.group(1) if dm else None
    table = name[1:]
    refined = classes.get(table)
    refined_row = None
    if refined:
        rd = next((f for f in refined['fields'] if f['name'] == 'data'), None)
        if rd:
            mm = re.match(r'List<(\S+)>$', rd['type']); refined_row = mm.group(1) if mm else None
    rr = classes.get(raw_row) if raw_row else None
    tables.append({
        'table': table,
        'rawClass': name, 'rawTypeDefIndex': c['typeDefIndex'],
        'rawRowClass': raw_row, 'rawRowTypeDefIndex': rr['typeDefIndex'] if rr else None,
        'refinedClass': table if refined else None, 'refinedRowClass': refined_row,
        'columns': [{'name': f['name'], 'type': f['type']} for f in (rr['fields'] if rr else [])],
        'rawDataType': fn['data']['type'],
    })
tables.sort(key=lambda t: t['table'])
json.dump(tables, open(sys.argv[2], 'w', encoding='utf-8'), ensure_ascii=False, indent=1)
print('tables', len(tables))
print('no raw row class resolved:', [t['table'] for t in tables if not t['columns']][:20])
from collections import Counter
print('column types:', Counter(c['type'] for t in tables for c in t['columns']).most_common(25))
print('total columns', sum(len(t['columns']) for t in tables))
