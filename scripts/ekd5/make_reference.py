#!/usr/bin/env python3
"""make_reference.py — copy the structured EKD5 (1998 PC 조조전) tables into the repo.

Run extract.py on your own copy of the 1998 game first, then:
    python3 -I scripts/ekd5/make_reference.py <extract_out_dir> data/reference/ekd5

Only structured data is kept (stats, ids, ranges, terrain grids). Prose columns
(officer bios, retreat lines, item/strategy descriptions) and dialogue.csv are
left out on purpose: the remake has its own text in Localization_0.txt, and the
1998 script stays with the original game files. extract.py regenerates them
locally whenever they are needed.
"""
import csv, os, sys

PROSE = {'bio', 'retire_line', 'description'}
TABLES = ['officers', 'items', 'jobs', 'strategies', 'battlemaps', 'terrain', 'shops']


def main(src, dst):
    os.makedirs(dst, exist_ok=True)
    for t in TABLES:
        rows = list(csv.DictReader(open(os.path.join(src, f'{t}.csv'), encoding='utf-8')))
        cols = [c for c in rows[0].keys() if c not in PROSE] if rows else []
        with open(os.path.join(dst, f'{t}.csv'), 'w', encoding='utf-8', newline='') as f:
            w = csv.DictWriter(f, fieldnames=cols, extrasaction='ignore')
            w.writeheader()
            w.writerows(rows)
        print(f'{t}: {len(rows)} rows, {len(cols)} columns')


if __name__ == '__main__':
    if len(sys.argv) != 3:
        sys.exit(__doc__)
    main(sys.argv[1], sys.argv[2])
