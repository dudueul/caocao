# EKD5 — 1998 PC 삼국지 조조전 (Koei *Sousouden*, Korean release) reference data

The mobile game's story mode, items, officers, classes and strategies descend
from Koei's 1998 PC game. These tables were decoded from the owner's copy of the
Korean PC release (`EKD5.EXE` + `*.E5` files) to pre-fill the remake's data
templates where the mobile values were lost.

**Different game, different numbers.** Use these as a starting point or for
relative balance (who is stronger, what costs more), not as the mobile game's
values. Stats are the 1998 raw values (max 50; the game may show them ×2).

| File | Rows | Content |
|---|---|---|
| `officers.csv` | 512 | name, face, class, STR/CMD/INT/AGI/LUCK, HP, MP, level, starting weapon/armor/accessory |
| `items.csv` | 104 | type, category, treasure flag, effect, power, price, icon |
| `jobs.csv` | 53 | class family/tier, move, attack range (dx,dy), stat growth grades, equipment, per-terrain bonus % and move cost |
| `strategies.csv` | 68 | MP cost, cast range and effect area (dx,dy), learn level per class |
| `battlemaps.csv` | 58 | battle name, size, terrain grid, map image + script file pairing |
| `terrain.csv` | 30 | terrain ids/names used by the grids |
| `shops.csv` | 58 | shop stock per stage |
| `sprite_map.json` | 53 + 1 | class (and officer) → unit sprite set, with confidence; read by `scripts/ekd5/export_art.py` (`docs/re/CLASSIC_ART.md`) |

Columns ending in `?` are not fully understood; see `docs/re/EKD5_FORMATS.md`
for byte layouts and evidence. Prose (officer bios, retreat lines, item and
strategy descriptions) and the 13,411-line story dialogue are **not** committed:
they stay with the original game files and `scripts/ekd5/extract.py`
regenerates them locally.

## Regenerate

```bash
python3 -I scripts/ekd5/extract.py <path/to/1998/game> /tmp/ekd5_out   # reads only, runs nothing
python3 -I scripts/ekd5/make_reference.py /tmp/ekd5_out data/reference/ekd5
scripts/tables/build.sh                                                # re-merges into data/tables/templates
```

Formats decoded: Koei **LS12** archives (same as LS11 apart from the magic: byte-permutation
dictionary + LZ bitstream, `scripts/ekd5/ls12.py`), `IMSG.E5` fixed 216-byte CP949
records, `HEXZMAP.E5` terrain grids, `HITAREA`/`EFFAREA` range pictures, `EEX` scripts.
