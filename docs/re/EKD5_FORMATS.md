# EKD5 (삼국지 조조전, 1998 Korean PC) data formats

All text is CP949. Integers are little-endian unless noted (only the LS12 member table is big-endian).
Tools: `ls12.py` (decompressor), `extract.py GAME_DIR OUT_DIR` (all CSVs). Decompressed members are in `ext/<FILE>/<nnn>.bin`.

## 1. LS12 container (`*.E5` starting with `Ls12`) — fully decoded

| offset | size | meaning |
|---|---|---|
| 0x000 | 16 | magic `Ls12` + 12 spaces |
| 0x010 | 256 | dictionary (byte permutation) |
| 0x110 | 12·N | member table, **big-endian** u32 `(comp_size, uncomp_size, offset)`, terminated by u32 0 |

Each member is an MSB-first bitstream. `get_code()`: count 1-bits n until a 0 bit, read n+1 bits v, `code = v + 2^(n+1) − 2`. Code < 256 → literal `dict[code]`. Code ≥ 256 → back-reference: distance = `code − 256` (copy from `out[len−distance]`, **no +1 bias**), length = `get_code() + 3`. `comp == uncomp` means stored raw. This is identical to LS11 except for the magic.
Verified: all 21 Ls12 files, 1,976 members decode to exactly `uncomp_size` with no errors (`ls12_members.txt`).

The Ls12 archives are DATA, EFFAREA, FACE (228×5120 = 64×80 px?), GATE, HEXZMAP, HITAREA, ITEM (104×1024 = 32×32 item icons), LOGO, LOGO_P, MEFF, MMAP, PMAP, PMAPOBJ, PMPALET, SMLMAP, SPALET, UNIT_ATK/MOV/SPC (151 each, unit sprites), U_SELECT and WEATHER. Only DATA, HEXZMAP, HITAREA and EFFAREA were decoded as data. The others are graphics and were not examined beyond their sizes.

## 2. DATA.E5 (6 members)

### #0 officers: 512 × 32 bytes → officers.csv
| byte | field | evidence |
|---|---|---|
| 0–11 (12) | name, NUL-padded | 조조, 하후돈, … 민중8 |
| 12 | always 0 (name terminator / pad) | |
| 13 | face id (portrait) | equals id for core cast; the "99" variants (유비99…) use 207–215 |
| 14 | always 0 | |
| 15 | persona id? | equals the original's id for duplicates (노숙99 → 78, 제갈양1–4 → 35); generic soldiers all have 170 |
| 16 | unknown 0–9 (`b16?`) | the 99 variants are all 4; 보병 0 / 궁병 7 / 기병 2 |
| 17 | always 255 | |
| 18–22 | 무력 STR, 통솔 CMD, 지력 INT, 민첩 AGI, 운 LUCK (base, max 50) | 여포 50/50/6/47/42, 제갈양 17/46/50/34/41, 곽가 INT 49, 초선 AGI 50. The display may be ×2 (unconfirmed). |
| 23–24 | HP (u16) at level 1 | 방덕 120, 곽가 80. Save files hold growing values here (105 at Lv1 → 130 at Lv7). |
| 25 | MP at level 1 | strategists 40–60, warriors 5–20 |
| 26 | job (class) id 0–52 | 허저 = 적병, 초선 = 무용수, 사마의 = 기마책사, matching their in-game classes |
| 27 | level (always 1) | the save-file mirror holds 1 or 7 |
| 28 | exp (always 0) | |
| 29 / 30 / 31 | weapon / armor / accessory item id (255 = none) | 0 = 단검, 3 = 단창, 38 = 가죽갑옷, 41 = 무명옷 |

Bios: IMSG[450+id] (ids 0–149 and 162–173). Retreat lines: IMSG[650+id] (ids 0–25, the player's Wei officers).
Save files `SV??D.E5S` contain the same structure with 32-byte records at about 0x14CA–0x14E5: 7-byte header, *current* stats (5), base stats (5), HP, 0, MP, job, level, exp, then (id, lv, exp) triplets for weapon, armor and accessory. This was used only as a cross-check.

### #1 items: 104 × 25 bytes → items.csv
| byte | field | evidence |
|---|---|---|
| 0–16 | name | 단검 … 경험의 열매 |
| 17 | type code. Below 18: equipment `category*2 + treasure` with categories 검 창 활 곤봉 포차 부채 보검 갑옷 옷 (EXE table). 18 and up: item effect code. | |
| 18 | special effect code of treasure equipment (255 = none) | 의천검 53 = "공격 방어 보조" ("방패로서 사용"), 청룡언월도 44 = "무반격 공격" |
| 19 | price, unit unknown (`price?`). 255 = cannot be bought. | common tiers 5/10/30, 콩 1, 복숭아 10 |
| 20 | icon id (= item id, ITEM.E5 member) | |
| 21 | power: atk/def for common equipment (10/45/80), HP for 콩/쌀 (30/80, 255 = full), +2 for 열매, 100 exp for 경험의 열매, the unlocked strategy id for 보옥 (64–67 = 청룡/주작/현무/백호). Treasure equipment only has 12–18 here, a different scale (probably per-level growth). | |
| 22 | effect magnitude? (청공검 50, 백은갑옷 50, 파초선 20, 백우선 2) | |
| 23 | commons 10, treasures 9 (max level?). On accessories: the family that may equip it (투구 1 = 보병, 기마갑옷 3 = 기병, 바람바퀴 5 = 포차, 몰우전 7 = 적병, 보옥 8–11 = 책사/풍수사/도사/기마책사, all matching IMSG text). | |
| 24 | treasure flag | |

Effect codes 18–76 map to the EXE list "매턴 HP회복 … 클래스 상승" (`effect_name`). Every code was cross-checked against the IMSG description. Descriptions come from IMSG[item id].

### #2 shop stock: 58 × 36 bytes → shops.csv
Two u16 header values of unknown meaning (`hdr_a?`, `hdr_b?`; record 0 is 186/204 with no items), then 32 item-id slots (255 = empty). The stock grows across the 58 stages: tier-1 → tier-3 weapons, 인수 from stage 15.

### #3 jobs: 53 × 27 bytes → jobs.csv
| byte | field | evidence |
|---|---|---|
| 0 | move | 기병 6/6/7, 보병 4/4/5, 포차 3/3/4 |
| 1 | attack range = HITAREA member | 궁병 2→3→4 (larger rings), 포차 7/7/8, 물자대 11 (self = cannot attack) |
| 2–6 | growth grade (1..4, assumed C B A S from the EXE string list) for 공격력, 방어력, 정신력, 순발력, 사기 | 보병 has DEF 4, 책사 SPI 4, 무도가/무희 AGI 4, 궁병/적병 MOR 4. Order matches the IMSG class descriptions. |
| 7 | HP growth? (3–6) | 보병/곰/흙인형 6, 풍수사 3 |
| 8 | MP growth? (1–3) | 1 for military classes, 2 for civil classes, 3 for 주술사/선인 |
| 9–26 | can equip item type 0..17 (0/1 flags) | 기병 → 창, 궁병 → 활, 책사 → 부채, 풍수/도사 → 보검, 포차/무도/책사 → 옷 |

Class names: EXE table (군웅 영웅 패왕 경보병 … 민중). Jobs 0–38 form 13 families × 3 tiers. Jobs 39–52 are single specials. Family = job//3 for jobs below 39, otherwise 13+(job−39), which gives 27 families. Descriptions: IMSG[350+job].

### #4 terrain table: 27 families × (30 + 30) bytes → jobs.csv columns
For each family, 30 terrain-effect bytes (×10 = %, e.g. 11 = 110%) then 30 move-cost bytes (255 = impassable), in terrain-id order. Row order = family order (군주 … 민중). Supporting evidence: mounted families (군주, 기병, 궁기병, 기마책사, 물자대) pay 2/3 on 숲/산지, 적병 pays 1 on 산지, 해적/제독 pay 1 on water, 선인 pays 1 everywhere.

### #5 strategies: 73 × 70 bytes (68 used) → strategies.csv
| byte | field |
|---|---|
| 0–12 | name |
| 13 | cast range = HITAREA member (11 = self, 10 = whole map) |
| 14 | effect area = EFFAREA member (0 single, 1 plus, 2 3×3, 5 line of 5 from the caster for 폭염, 6 diamond r3 for 청룡) |
| 15 | MP cost (초열 6, 업화 10, 화룡 20, 청룡 40, 헌책 48, 명상 0) |
| 16 | type/animation id? (0 fire, 1 water, 2 wind, 3 earth, 7 heal…, 31 distinct values, so it is not the EXE effect-name list) |
| 17–69 | learn level for each of the 53 classes (0 = never). Example: 초열 = 책사/참모/군사 1, 기마책사 5, 주술사 1, 선인 1. |

Descriptions: IMSG[150+id]. **Not found:** base power, hit-rate and terrain/weather restriction tables. They are probably hard-coded in EKD5.EXE; the restrictions only appear as description text.

## 2b. Graphics archives (verified by rendering, 2026-10-14)

All are Ls12 archives of raw **8-bit indexed** images, fixed size per member:

| Archive | Members | Member = | Notes |
|---|---|---|---|
| FACE.E5 | 228 | one 64×80 portrait (5,120 B) | index = officer face id |
| UNIT_MOV.E5 | 151 | 11 frames × 48×48 (25,344 B) | move/idle frames |
| UNIT_ATK.E5 | 151 | 12 frames × 64×64 (49,152 B) | attack frames |
| UNIT_SPC.E5 | 151 | 5 frames × 48×48 (11,520 B) | special poses |
| ITEM.E5 | 104 | one 32×32 icon (1,024 B) | index = item id |
| MMAP.E5 | 115 | 640×400 (256,000 B) | scene backgrounds |
| PMAP.E5 | 74 | 20,016 B | not decoded |

**Palette:** SPALET.E5 (58 members, per battle map) and PMPALET.E5 (115) hold
256 RGB triplets stored in **G, B, R** order — display colour = `(p[1], p[2], p[0])`.
Index colour **magenta (255,0,255)** is the transparency key on sprites and icons.
With this order portraits, unit sprites and icons render with correct colours.
Mobile comparison: the remake's battle tile highlights are 96×96 px (exactly 2×
the 48×48 tiles), while its portraits (200×256) are new painted art, not upscales.

## 3. IMSG.E5: 932 × 216-byte plaintext CP949 records
0–103 item descriptions · 149 "Magic Message Start" · 150–217 strategy descriptions · 250–307 battle names (58) · 350–402 class descriptions (53) · 450–623 officer bios by officer id · 650–675 retreat lines · 700–… critical-hit lines (not indexed by officer id) · ~800+ credits. Filler records are 4 junk bytes.

## 4. EKD5.EXE string tables (read statically)
Starting at file offset 0x93cd4, the strings are sequential, NUL-padded and 4-byte aligned: 11 item categories, 59 item effects (codes 18–76), 28 strategy-effect names, 30 terrain names (ids 0–29: 평지 초원 숲 황무지 산지 바위산 벼랑 설원 다리 여울 습지 연못 강 대하 울타리 성벽 성내 성문 성 관문 요새 마을 병영 가옥 보물고 도랑 불 배 제단 지중), 4 grade letters "C B A S", 27 family names and 53 class names. Other UI strings are in `exe_strings.txt`.

## 5. HITAREA.E5 / EFFAREA.E5: area diagrams
Each member is a 64×64 8-bit picture of a cell grid: background 0x1D, grid line 0x1F, highlighted cell 0x28/0x57, centre 0x3A/0x2B. Pitch 9 gives 7×7 cells and pitch 6 gives 11×11. `extract.py` reads the cell centres and outputs offsets `dx,dy` relative to the centre (the unit or caster). HITAREA 14/15 and EFFAREA 7–9 are empty.

## 6. Battle maps → battlemaps.csv
- **HEXZMAP.E5** has 58 members (map k ↔ battle k). Byte 0 = width×3 and byte 1 = height×3 (in 16-px units). It is followed by width×height terrain-id bytes, row-major. Ids are 0–29 (terrain.csv).
- **HMkk.E5** is the battle-map picture for map k: raw 8-bit indexed, **48×48 tiles stored tile-by-tile** (2304 bytes per tile, tiles in row-major order). The file size equals w·48·h·48 for all 58 maps. Rendering HM27 (적벽) with the HEXZMAP 27 grid overlaid matches exactly: river = 대하, forest edges, fences and houses (`preview_HM27_with_SPALET27.png`).
- SPALET.E5[k] (768 or 738 bytes) is the map palette. The channel order is not settled (BGR looked closer than RGB).
- The CSV grid uses one base-32 character per cell (0–9, A–T), with rows separated by `/`.
- MAPATR.DLL holds only 28 bitmap resources (48×48 8-bit, ids 100–127), which look like terrain tile/attribute pictures. It contains no tables.

## 7. EEX scripts → dialogue.csv
Header `EEX\0`, followed by u16-token bytecode. Instructions are an opcode u16 followed by tagged arguments: tag 0x02 = u16 (officer id/value), 0x04 = u32, 0x05 = NUL-terminated CP949 string, and other tags (0x26, 0x2b, 0x3e, 0x45, 0x07…) take a u16. Some Shift-JIS block labels (e.g. 一騎討ち処理) remain in the files.
Text-bearing opcodes found:

| opcode | meaning |
|---|---|
| 0x14 | dialogue with an inline `&speaker\n` prefix; one string can hold several `&` segments |
| 0x15 | `(02 anchor-unit)(02 speaker)(05 text)` |
| 0x16 | narration |
| 0x2C | caption |
| 0x17 | location |
| 0x18 | chapter title |
| 0x19 | win/lose conditions |
| 0x1A | objective |
| 0x12 | choices |
| 0x69 | "며칠 후" captions |
| 0x67 | part titles |
| 0x02 | event-log titles (R_ files) |
| 0x60/0x63 | duel lines `(26 side)(05 text)` |

Duel participants are set by `0x68 (02 A)(02 B)`. Side 1 is A and side 0 is B; this was checked on 관우 vs 화웅 and is marked `duel_side?`.
R_kk = story/camp scenes before battle k; S_kk = battle k (unit placements + battle dialogue).
**Not decoded:** the full opcode grammar and argument counts (unit placement records were partly identified: officer, x, y, …), branching and flags.

## 8. Not decoded / open questions
- Strategy power, accuracy, terrain and weather rules (not in DATA.E5).
- Treasure-weapon power scale and the per-level item growth rules. The meanings of item b19 (price unit), b22 and b23 for equipment.
- Officer byte 16 (0–9) and byte 15 semantics. The display scale of stats (raw ≤ 50).
- Job bytes 7 and 8 (assumed HP/MP growth). The grade-letter mapping 1..4 → C..S is assumed.
- The two u16 shop-header values.
- PMAP (story-scene maps) and effect animations (MCALL/MEFF); the other graphics are in §2b.
- Full EEX bytecode semantics. The save-file layout beyond the officer block.
