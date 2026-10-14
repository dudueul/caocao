"""Assign every Localization_0.txt row to the (table, column) it came from.

Model (evidenced, see data/tables/README.md): the localization file is the
de-duplicated union of every table's text columns, emitted table by table in
PermanentData.Reload order and column by column inside a table, followed by
UI/code strings. A monotone dynamic program assigns each row a slot using
string-shape scores; curated anchors (data/tables/anchors.json) pin rows whose
slot was verified by reading the content.

usage: align_localization.py <schema.json> <load_order.json> <anchors.json> <Localization_0.txt> <alignment.json> [--show]
"""
import csv, json, re, sys
import numpy as np

schema_path, order_path, anchors_path, loc_path, out_path = sys.argv[1:6]
show = '--show' in sys.argv
schema = {t['table']: t for t in json.load(open(schema_path, encoding='utf-8'))}
order = json.load(open(order_path))['PermanentData$$Reload']
anchors = json.load(open(anchors_path))
rows = list(csv.reader(open(loc_path, encoding='utf-8-sig')))[1:]

# string columns that hold resource names, dates, codes or script ids are not localized
NON_TEXT = re.compile(r'(atlas|icon|prefab|image|img|fx|sfx|^se$|SE$|snd|sound$|bgm|texture|sprite|anim|file|path|loader|^scene$|^key$|color|marker|thumbnail|emblem|date|time$|currencycode|serverprice|gpgsid|^code$|weekday|^slot$|dayofweek|monthkey|script$|^xml$|^alpha$|checkobject|^face$|bosshp|needambition|canusegamemode|effectrate|setcondition)', re.I)
SENT_COLS = re.compile(r'(desc|description|summary|message|txt$|^mid$|^tail$|alert|speech|dialog|msg)', re.I)

slots = []
for t in order:
    for c in schema[t]['columns']:
        if c['type'] in ('string', 'List<string>') and not NON_TEXT.search(c['name']):
            kind = 'SENT' if SENT_COLS.search(c['name']) else 'NAME'
            if re.search(r'(criticalmsg|defeatmsg|speech|dialog)', c['name'], re.I):
                kind = 'LINE'
            slots.append((t, c['name'], kind))
slots.append(('__UI__', 'text', 'ANY'))
slot_index = {f'{t}.{c}': i for i, (t, c, _) in enumerate(slots)}

def is_sentence(k):
    k2 = re.sub(r'\[[0-9A-Fa-f]{6,8}\]|\[-\]', '', k)
    return bool(re.search(r'[.!?。]\s*$|다\.?\s*$|요\.?\s*$|\\n|\n', k2)) or len(k2) > 40

kinds = np.array([{'NAME': 0, 'SENT': 1, 'LINE': 2, 'ANY': 3}[k] for _, _, k in slots])
N, M = len(rows), len(slots)
score = np.zeros((N, M), dtype=np.float32)
for r, row in enumerate(rows):
    sent = is_sentence(row[0])
    score[r] = np.where(kinds == 0, -1.0 if sent else 1.0,
               np.where(kinds == 1, 1.0 if sent else -0.6,
               np.where(kinds == 2, 0.3, -0.2)))
NEG = -1e9
for r, key in anchors:
    if key not in slot_index:
        sys.exit(f'unknown anchor slot {key}')
    keep = slot_index[key]
    score[r, :keep] = NEG
    score[r, keep + 1:] = NEG

PEN = 3.0                                  # cost of switching to a later slot
idx = np.arange(M)
back = np.zeros((N, M), dtype=np.int32)
dp = score[0].astype(np.float64)
for r in range(1, N):
    pm = np.maximum.accumulate(dp)
    is_new = np.r_[True, dp[1:] > pm[:-1]]
    pm_idx = np.maximum.accumulate(np.where(is_new, idx, 0))
    switch = np.r_[NEG, pm[:-1] - PEN]
    switch_idx = np.r_[0, pm_idx[:-1]]
    stay = dp >= switch
    dp = np.where(stay, dp, switch) + score[r]
    back[r] = np.where(stay, idx, switch_idx)
s = int(np.argmax(dp))
assign = [0] * N
for r in range(N - 1, -1, -1):
    assign[r] = s
    s = int(back[r, s])

segs = []
for r in range(N):
    if segs and segs[-1][0] == assign[r]:
        segs[-1][2] = r
    else:
        segs.append([assign[r], r, r])
out = [{'table': slots[sl][0], 'column': slots[sl][1], 'start': a, 'end': b, 'n': b - a + 1} for sl, a, b in segs]
json.dump({'slots': slots, 'segments': out, 'assign': assign}, open(out_path, 'w', encoding='utf-8'), ensure_ascii=False)
if show:
    for sg in out:
        print(f"{sg['start']:5d}-{sg['end']:5d} n={sg['n']:4d} {sg['table']}.{sg['column']} | "
              f"{rows[sg['start']][5][:40]!r} … {rows[sg['end']][5][:40]!r}")
print(f'slots {M}, segments {len(out)}')
