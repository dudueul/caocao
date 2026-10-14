#!/usr/bin/env python3
"""merge_ekd5.py — add 1998 PC 조조전 (EKD5) reference values to the table templates.

For each pair below, template rows whose Korean name equals an EKD5 name get
`#ekd5_*` helper columns (1998 values — a different game with different scales,
so they never overwrite the baked columns). EKD5 names that exist as a key in
Localization_0.txt but have no template row (removed by the localization's
de-duplication, e.g. 조조 / 관우 / 사마의) are appended as new rows with
`#confidence = ekd5`.

Old 1998 spellings are matched to the remake's spelling only through
Hangul initial-consonant variants (ㅇ→ㄹ/ㄴ in non-initial syllables, e.g.
제갈양 → 제갈량, 감영 → 감녕), and only when the variant is a localization key.

usage: merge_ekd5.py <data/reference/ekd5> <data/tables> <Localization_0.txt>
"""
import csv, itertools, os, re, sys

ref_dir, tables_dir, loc_path = sys.argv[1:4]
loc_rows = list(csv.reader(open(loc_path, encoding='utf-8-sig')))
EN = loc_rows[0].index('English')
loc = {}
for i, r in enumerate(loc_rows[1:]):
    loc.setdefault(r[0], (r[EN], i + 2))


def variants(name):
    """Spellings of `name` with ㅇ-initial syllables (after the first) switched to ㄹ/ㄴ."""
    sy = list(name)
    pos = [i for i, ch in enumerate(sy) if i > 0 and 0xAC00 <= ord(ch) <= 0xD7A3
           and (ord(ch) - 0xAC00) // 588 == 11]
    out = []
    for k in range(1, min(2, len(pos)) + 1):
        for chosen in itertools.combinations(pos, k):
            for inits in itertools.product((5, 2), repeat=k):           # ㄹ, ㄴ
                s = sy[:]
                for p, ini in zip(chosen, inits):
                    c = ord(s[p]) - 0xAC00
                    s[p] = chr(0xAC00 + ini * 588 + c % 588)
                out.append(''.join(s))
    return out


def canonical(name, known):
    if name in known:
        return name
    for v in variants(name):
        if v in known:
            return v
    return None


def read_ref(t):
    return list(csv.DictReader(open(os.path.join(ref_dir, f'{t}.csv'), encoding='utf-8')))


PAIRS = [
    # template, ref table, ref name column, skip predicate, {helper: ref column}
    ('Units', 'officers', 'name', lambda r: bool(re.search(r'\d$', r['name'])),
     {'id': 'id', 'job': 'job_name', 'STR': 'STR', 'CMD': 'CMD', 'INT': 'INT', 'AGI': 'AGI',
      'LUCK': 'LUCK', 'HP': 'HP', 'MP': 'MP', 'level': 'level', 'weapon': 'weapon', 'armor': 'armor'}),
    ('Items', 'items', 'name', None,
     {'id': 'id', 'kind': 'kind', 'category': 'category', 'treasure': 'is_treasure',
      'effect': 'effect_name', 'power': 'power', 'effect_value': 'effect_value?', 'price': 'price?'}),
    ('Skills', 'strategies', 'name', None,
     {'id': 'id', 'mp': 'mp_cost', 'cast_range': 'cast_range(dx,dy)',
      'effect_area': 'effect_area(dx,dy)', 'learn': 'learn_level_by_class'}),
    ('Jobs', 'jobs', 'name', None,
     {'id': 'id', 'family': 'family', 'tier': 'tier', 'move': 'move',
      'attack_range': 'attack_range(dx,dy)', 'equip': 'equip_types'}),
    ('Tiles', 'terrain', 'name', None, {'id': 'id'}),
    ('BattleMaps', 'battlemaps', 'battle_name', None,
     {'map_id': 'map_id', 'width': 'width', 'height': 'height', 'hm': 'hm_image', 'script': 'battle_script'}),
]

report = []
for table, ref_t, ref_col, skip, helpers in PAIRS:
    path = os.path.join(tables_dir, 'templates', f'{table}.csv')
    rows = list(csv.reader(open(path, encoding='utf-8')))
    header, body = rows[0], rows[1:]
    name_col = header.index('name')
    existing = {r[name_col] for r in body}
    known = existing | set(loc)
    by_name = {}
    for r in read_ref(ref_t):
        if skip and skip(r):
            continue
        key = canonical(r[ref_col], known)
        if key and key not in by_name:
            by_name[key] = r
    hcols = [f'#ekd5_{h}' for h in helpers]
    for c in ('#name_en', '#loc_row', '#confidence'):
        if c not in header:
            header.append(c)
    base = len(header)
    header += hcols
    matched = added = 0
    for r in body:
        r += [''] * (base - len(r))
        ref = by_name.get(r[name_col])
        r += [ref[c] for c in helpers.values()] if ref else [''] * len(hcols)
        matched += bool(ref)
    for key, ref in by_name.items():
        if key in existing:
            continue
        en, line = loc[key]
        new = [''] * base
        new[name_col] = key
        new[header.index('#name_en')] = en
        new[header.index('#loc_row')] = str(line)
        new[header.index('#confidence')] = 'ekd5'
        body.append(new + [ref[c] for c in helpers.values()])
        added += 1
    with open(path, 'w', encoding='utf-8', newline='') as f:
        csv.writer(f).writerows([header] + body)
    report.append((table, ref_t, matched, added))
    print(f'{table:<11} <- {ref_t:<11} matched {matched:3d} rows, appended {added:3d}')
