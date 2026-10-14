#!/usr/bin/env python3
"""Extract EKD5 (Sangokushi Sousouden, 1998 Korean PC) data tables to CSV.

Usage: python3 -I extract.py GAME_DIR OUT_DIR
Reads only; never executes anything from GAME_DIR.
"""
import csv
import glob
import os
import re
import struct
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import ls12  # noqa: E402

ENC = "cp949"


def cstr(b):
    return b.split(b"\0")[0].decode(ENC, "replace")


# ---------------------------------------------------------------- loaders
def load_ls12(game, name):
    outs, _ = ls12.decompress_file(os.path.join(game, name))
    return outs


def load_imsg(game):
    d = open(os.path.join(game, "IMSG.E5"), "rb").read()
    recs = []
    for i in range(len(d) // 216):
        raw = d[i * 216:(i + 1) * 216].split(b"\0")[0]
        # filler records are 4 junk bytes like b'\x8e\x9e\x9e\x5b'
        try:
            t = raw.decode(ENC)
        except UnicodeDecodeError:
            t = ""
        if len(raw) <= 4 and not re.search("[가-힣A-Za-z0-9]{2,}", t):
            t = ""
        recs.append(t)
    return recs


def load_exe_tables(game):
    """Sequential NUL-padded CP949 string tables in EKD5.EXE (~0x93cd4..0x944a8)."""
    d = open(os.path.join(game, "EKD5.EXE"), "rb").read()
    e = lambda s: re.escape(s.encode(ENC))
    m = re.search(e("검") + b"\0+" + e("창") + b"\0+" + e("활") + b"\0+" + e("곤봉"), d)
    if not m:
        raise SystemExit("EXE string anchor not found")
    p = m.start()
    out = []
    while len(out) < 212:
        while d[p] == 0:
            p += 1
        q = d.find(b"\0", p)
        out.append(d[p:q].decode(ENC))
        p = q
    t = {
        "anchor": m.start(),
        "item_cats": out[0:11],        # 검 창 활 곤봉 포차 부채 보검 갑옷 옷 보조 도구
        "item_effects": out[11:70],    # item effect codes 18..76
        "strat_effects": out[70:98],   # strategy effect names (index unknown)
        "terrain": out[98:128],        # terrain ids 0..29
        "grades": out[128:132],        # C B A S
        "families": out[132:159],      # 27 unit families / movement types
        "classes": out[159:212],       # 53 job classes
    }
    assert t["terrain"][0] == "평지" and t["classes"][-1] == "민중", t
    return t


# ---------------------------------------------------------------- area shapes
BG = 0x1D


def area_shape(img):
    """HITAREA/EFFAREA member = 64x64 8-bit picture of a cell grid (pitch 9 => 7x7
    cells, pitch 6 => 11x11 cells). Background 0x1d, grid line 0x1f, highlighted
    cells 0x28/0x57, centre (unit/caster) 0x3a/0x2b.
    Returns (rows as strings, list of (dx,dy) offsets relative to centre, has_centre)."""
    w = h = 64
    cand = [y for y in range(h) if BG not in img[y * w:(y + 1) * w]]
    pitch = None
    for pc in (9, 6):
        for off in range(pc):
            lines = list(range(off, h, pc))
            if all(l in cand for l in lines) and len(cand) < h:
                pitch = (pc, off)
                break
        if pitch:
            break
    if pitch is None:
        return ["#"], [], False   # fully highlighted: whole map
    pc, off = pitch
    lines = list(range(off, h, pc))
    cs = []
    prev = -1
    for l in lines + [h]:
        if l - prev > 2:
            cs.append((prev + 1 + l - 1) // 2)
        prev = l
    g = []
    for y in cs:
        r = ""
        for x in cs:
            v = img[y * w + x]
            r += "." if v == BG else ("@" if v in (0x3A, 0x2B) else "#")
        g.append(r)
    cx = cy = None
    for yy, r in enumerate(g):
        if "@" in r:
            cy, cx = yy, r.index("@")
    pts = []
    if cx is not None:
        for yy, r in enumerate(g):
            for xx, c in enumerate(r):
                if c == "#":
                    pts.append((xx - cx, yy - cy))
    return g, pts, (cx is not None)


def shape_str(g, pts, has_c, include_centre=False):
    if not has_c:
        if all(set(r) <= {"."} for r in g):
            return "none"
        if all(set(r) <= {"#"} for r in g):
            return "all"
        return "nocentre:" + "/".join(g)
    if include_centre:
        pts = [(0, 0)] + pts
    if not pts:
        return "self"
    return ";".join("%d,%d" % p for p in sorted(pts, key=lambda p: (p[1], p[0])))


# ---------------------------------------------------------------- main
def main(game, out):
    os.makedirs(out, exist_ok=True)
    data = load_ls12(game, "DATA.E5")
    imsg = load_imsg(game)
    T = load_exe_tables(game)
    classes, families, terrain = T["classes"], T["families"], T["terrain"]
    grades = {1: "C", 2: "B", 3: "A", 4: "S"}

    def w(name, header, rows):
        with open(os.path.join(out, name), "w", newline="", encoding="utf-8") as f:
            cw = csv.writer(f)
            cw.writerow(header)
            cw.writerows(rows)
        print("%s: %d rows" % (name, len(rows)))

    # ---- items (DATA member 1: 104 x 25 bytes)
    items = []
    d = data[1]
    for i in range(len(d) // 25):
        r = d[i * 25:(i + 1) * 25]
        items.append((cstr(r[:17]),) + tuple(r[17:]))
    item_name = {i: it[0] for i, it in enumerate(items)}

    def iname(x):
        return "" if x == 255 else item_name.get(x, "?%d" % x)

    def effname(c):
        if c == 255 or c < 18:
            return ""
        k = c - 18
        return T["item_effects"][k] if k < len(T["item_effects"]) else "?%d" % c

    rows = []
    for i, (nm, typ, spec, price, icon, power, effval, b23, treas) in enumerate(items):
        if typ < 18:
            cat = T["item_cats"][typ // 2]
            kind = "weapon" if typ < 14 else "armor"
            eff = spec
        else:
            eff = typ
            cat = "도구" if 63 <= typ <= 76 else "보조"
            kind = "consumable" if 63 <= typ <= 76 else "accessory"
        rows.append([
            i, nm, typ, kind, cat, treas,
            "" if eff == 255 else eff, effname(eff),
            power, effval, "" if price == 255 else price, icon,
            "" if b23 == 255 else b23,
            (families[b23] if kind == "accessory" and b23 < len(families) else ""),
            imsg[i] if i < 104 else "",
        ])
    w("items.csv",
      ["id", "name", "type_code", "kind", "category", "is_treasure",
       "effect_code", "effect_name", "power", "effect_value?", "price?",
       "icon_id", "b23_maxlv_or_family?", "equip_family?", "description"], rows)

    # ---- jobs (DATA member 3: 53 x 27 bytes) + terrain table (member 4: 27 x 60)
    hit = load_ls12(game, "HITAREA.E5")
    eff = load_ls12(game, "EFFAREA.E5")
    hit_shapes = [shape_str(*area_shape(m)) for m in hit]
    eff_shapes = [shape_str(*area_shape(m), include_centre=True) for m in eff]
    terr = data[4]
    d = data[3]
    rows = []
    for j in range(len(d) // 27):
        r = d[j * 27:(j + 1) * 27]
        fam = j // 3 if j < 39 else 13 + (j - 39)
        equip = [T["item_cats"][t // 2] + ("(보물)" if t % 2 else "")
                 for t in range(18) if r[9 + t]]
        te = terr[fam * 60:fam * 60 + 30]
        tc = terr[fam * 60 + 30:fam * 60 + 60]
        rows.append([
            j, classes[j], fam, families[fam], (j % 3 + 1) if j < 39 else "",
            r[0], r[1], hit_shapes[r[1]],
            r[2], r[3], r[4], r[5], r[6], r[7], r[8],
            "|".join(equip),
            " ".join(str(x * 10) for x in te),
            " ".join("X" if x == 255 else str(x) for x in tc),
            imsg[350 + j] if 350 + j < len(imsg) else "",
        ])
    w("jobs.csv",
      ["id", "name", "family_id", "family", "tier",
       "move", "attack_range_id", "attack_range(dx,dy)",
       "grow_atk(1=C..4=S)", "grow_def", "grow_spi", "grow_agi", "grow_mor",
       "hp_growth?", "mp_growth?", "equip_types",
       "terrain_effect_pct[" + " ".join(terrain) + "]",
       "terrain_move_cost[" + " ".join(terrain) + "]",
       "description"], rows)

    # terrain reference
    w("terrain.csv", ["id", "name"], [[i, t] for i, t in enumerate(terrain)])

    # ---- strategies (DATA member 5: 73 x 70 bytes)
    d = data[5]
    rows = []
    for s in range(len(d) // 70):
        r = d[s * 70:(s + 1) * 70]
        nm = r[:13].split(b"\0")[0]
        try:
            nm = nm.decode(ENC)
        except UnicodeDecodeError:
            continue
        if not re.match("^[가-힣]+$", nm) or not any(r[13:]):
            continue
        learn = ["%s:%d" % (classes[j], r[17 + j]) for j in range(53) if r[17 + j]]
        rows.append([
            s, nm, r[15], r[13], hit_shapes[r[13]], r[14], eff_shapes[r[14]], r[16],
            "|".join(learn), imsg[150 + s],
        ])
    w("strategies.csv",
      ["id", "name", "mp_cost", "cast_range_id", "cast_range(dx,dy)",
       "effect_area_id", "effect_area(dx,dy)", "anim_or_type?",
       "learn_level_by_class", "description"], rows)

    # ---- officers (DATA member 0: 512 x 32 bytes)
    d = data[0]
    rows = []
    for i in range(len(d) // 32):
        r = d[i * 32:(i + 1) * 32]
        nm = cstr(r[:12])
        hp = struct.unpack("<H", r[23:25])[0]
        job = r[26]
        bio = imsg[450 + i] if 450 + i < 649 else ""
        retire = imsg[650 + i] if 650 + i < 699 else ""
        rows.append([
            i, nm, r[13], r[15], r[16],
            job, classes[job] if job < 53 else "?",
            r[18], r[19], r[20], r[21], r[22],
            hp, r[25], r[27], r[28],
            "" if r[29] == 255 else r[29], iname(r[29]),
            "" if r[30] == 255 else r[30], iname(r[30]),
            "" if r[31] == 255 else r[31], iname(r[31]),
            bio, retire,
        ])
    w("officers.csv",
      ["id", "name", "face_id", "persona_id?", "b16?",
       "job_id", "job_name", "STR", "CMD", "INT", "AGI", "LUCK",
       "HP", "MP", "level", "exp?",
       "weapon_id", "weapon", "armor_id", "armor", "accessory_id", "accessory",
       "bio", "retire_line"], rows)

    # ---- shops (DATA member 2: 58 x 36 bytes)
    d = data[2]
    rows = []
    for k in range(len(d) // 36):
        r = d[k * 36:(k + 1) * 36]
        a, b = struct.unpack("<HH", r[:4])
        ids = [x for x in r[4:] if x != 255]
        rows.append([k, imsg[250 + k], a, b, " ".join(map(str, ids)),
                     "|".join(item_name.get(x, "?") for x in ids)])
    w("shops.csv", ["stage", "battle_name", "hdr_a?", "hdr_b?", "item_ids", "items"], rows)

    # ---- battle maps (HEXZMAP.E5: 58 members, [w*3, h*3] + w*h terrain bytes)
    hz = load_ls12(game, "HEXZMAP.E5")
    rows = []
    for k, m in enumerate(hz):
        wt, ht = m[0] // 3, m[1] // 3
        grid = m[2:2 + wt * ht]
        assert len(grid) == wt * ht
        hm = "HM%02d.E5" % k
        hp = os.path.join(game, hm)
        ok = os.path.exists(hp) and os.path.getsize(hp) == wt * 48 * ht * 48
        # compact grid: one base-32 char per cell, rows separated by '/'
        alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUV"
        g = "/".join("".join(alpha[c] for c in grid[y * wt:(y + 1) * wt]) for y in range(ht))
        rows.append([k, imsg[250 + k], wt, ht, hm, "yes" if ok else "no",
                     "S_%02d.EEX" % k, g])
    w("battlemaps.csv",
      ["map_id", "battle_name", "width", "height", "hm_image", "hm_size_matches(w*48*h*48)",
       "battle_script", "terrain_grid(base32 per cell; rows '/'; see terrain.csv)"], rows)

    # ---- dialogue (R_/S_ EEX)
    officer_names = {i: cstr(d0[i * 32:i * 32 + 12]) for d0 in [data[0]] for i in range(512)}
    rows = extract_dialogue(game, officer_names)
    w("dialogue.csv",
      ["script", "seq", "offset", "opcode", "kind", "speaker", "speaker_src", "line"], rows)


STR_RE = re.compile(rb"\x05\x00((?:[\xa1-\xfe][\xa1-\xfe]|[\x09\x0a\x0d\x20-\x7e])+)\x00")
OPKIND = {
    0x14: "dialogue", 0x12: "choice", 0x16: "narration", 0x2C: "caption",
    0x17: "location", 0x18: "chapter_title", 0x19: "win_lose_conditions",
    0x1A: "objective", 0x69: "time_caption", 0x60: "duel", 0x63: "duel",
}


def extract_dialogue(game, names):
    rows = []
    for f in sorted(glob.glob(os.path.join(game, "R_*.EEX")) + glob.glob(os.path.join(game, "S_*.EEX"))):
        d = open(f, "rb").read()
        base = os.path.basename(f)
        duel = None
        seq = 0
        duel_re = re.compile(rb"\x68\x00\x02\x00(..)\x02\x00(..)", re.S)
        duel_marks = [(m.start(), struct.unpack("<H", m.group(1))[0], struct.unpack("<H", m.group(2))[0])
                      for m in duel_re.finditer(d)]
        for m in STR_RE.finditer(d):
            try:
                text = m.group(1).decode(ENC)
            except UnicodeDecodeError:
                continue
            if not re.search("[가-힣]", text):
                continue
            p = m.start()
            op = struct.unpack("<H", d[p - 2:p])[0]
            speaker, src, kind = "", "", OPKIND.get(op, "")
            # op 0x15: (02 anchor_unit) (02 speaker) (05 text)
            if d[p - 10:p - 8] == b"\x15\x00" and d[p - 8:p - 6] == b"\x02\x00" and d[p - 4:p - 2] == b"\x02\x00":
                op = 0x15
                kind = "unit_dialogue"
                spk = struct.unpack("<H", d[p - 2:p])[0]
                speaker, src = names.get(spk, "#%d" % spk), "param"
            elif d[p - 4:p - 2] == b"\x26\x00" and struct.unpack("<H", d[p - 6:p - 4])[0] in (0x60, 0x61, 0x62, 0x63, 0x64):
                op = struct.unpack("<H", d[p - 6:p - 4])[0]
                kind = "duel"
                side = struct.unpack("<H", d[p - 2:p])[0]
                prev = [x for x in duel_marks if x[0] < p]
                if prev:
                    _, a, b = prev[-1]
                    spk = a if side == 1 else b
                    speaker, src = names.get(spk, "#%d" % spk), "duel_side?"
            elif d[p - 8:p - 6] == b"\x67\x00" and d[p - 6:p - 4] == b"\x04\x00":
                op, kind = 0x67, "part_title"
            elif op == 0x02 and base.startswith("R_"):
                kind = "event_title?"
            elif op == 0x14 or text.lstrip("\n").startswith("&"):
                kind = kind or "dialogue"
            if kind == "":
                # titles / log entries / unknown text-bearing ops
                kind = "other_0x%x" % op
            # split &speaker segments
            if "&" in text and (kind in ("dialogue",) or text.lstrip("\n").startswith("&")):
                segs = re.split(r"(?:^|\n)&", text.lstrip("\n"))
                for sgm in segs:
                    if not sgm.strip():
                        continue
                    if "\n" in sgm:
                        spk_name, line = sgm.split("\n", 1)
                    else:
                        spk_name, line = sgm, ""
                    rows.append([base, seq, p, "0x%02x" % op, kind, spk_name.strip(), "inline",
                                 line.strip("\n").replace("\n", " / ")])
                    seq += 1
            else:
                rows.append([base, seq, p, "0x%02x" % op, kind, speaker, src,
                             text.strip("\n").replace("\n", " / ")])
                seq += 1
    return rows


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print(__doc__)
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])
