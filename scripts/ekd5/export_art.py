#!/usr/bin/env python3
"""Export the 1998 PC 조조전 (EKD5) art as PNG/JSON placeholders for the Unity client.

Usage:
  python3 -I scripts/ekd5/export_art.py GAME_DIR [OUT_DIR] [--only KINDS] [--contact]

  GAME_DIR  folder of your own copy of the game (EKD5.EXE, *.E5, HM*.E5, SE*.WAV ...)
  OUT_DIR   default: client/Assets/Resources/ClassicArt  (git-ignored: Koei art is
            never committed; every developer exports it from their own copy)
  --only    comma list of: units,faces,items,maps,backgrounds,sfx (default: all);
            "--only manifest" just rewrites classic_art.json after editing
            data/reference/ekd5/sprite_map.json
  --contact also write contact/*.png overview sheets (10 cells per row, so
            cell index = row * 10 + column) for checking sprite_map.json

Reads only; never executes anything from GAME_DIR. Standard library only.

Output (formats in docs/re/EKD5_FORMATS.md §2b):
  units/unit_###_mov.png   11 frames of 48x48 in one row (528x48)
  units/unit_###_atk.png   12 frames of 64x64            (768x64)
  units/unit_###_spc.png    5 frames of 48x48            (240x48)
  faces/face_###.png       64x80 portraits (index = officers.csv face)
  items/item_###.png       32x32 icons      (index = items.csv id)
  maps/map_##.png          battle map, 48 px per tile, palette SPALET[map]
  maps/map_##_grid.json    {id, name, width, height, tileSize, terrain[] row-major, terrainNames[]}
  backgrounds/bg_###.png   640x400 scene pictures (MMAP.E5, palette PMPALET[i])
  sfx/*.wav                sound effects, copied unchanged
  classic_art.json         manifest: counts, frame layout and the class -> sprite map
"""
import argparse
import json
import os
import shutil
import struct
import sys
import zlib

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)
import extract  # noqa: E402  (shared IMSG / EXE string loaders)
import ls12  # noqa: E402

REPO = os.path.normpath(os.path.join(HERE, "..", ".."))
DEFAULT_OUT = os.path.join(REPO, "client", "Assets", "Resources", "ClassicArt")
SPRITE_MAP = os.path.join(REPO, "data", "reference", "ekd5", "sprite_map.json")
KINDS = ("units", "faces", "items", "maps", "backgrounds", "sfx")

TILE = 48
MOV = (48, 11)  # (frame size, frame count)
ATK = (64, 12)
SPC = (48, 5)


# ---------------------------------------------------------------- PNG + palette
class Palette:
    """256 colours stored G,B,R. Index 0 (a near-magenta, 247,0,255 in SPALET[0]) is
    the transparency key of unit sprites and item icons."""

    def __init__(self, raw, keyed):
        raw = bytes(raw[:768]).ljust(768, b"\0")
        self.plte = bytes(raw[i * 3 + k] for i in range(256) for k in (1, 2, 0))
        self.keyed = keyed


def write_png(path, w, h, idx, pal):
    """8-bit indexed PNG (PLTE, plus tRNS making index 0 transparent when keyed)."""
    raw = bytearray()
    for y in range(h):
        raw.append(0)
        raw += idx[y * w:(y + 1) * w]

    def chunk(t, b):
        return struct.pack(">I", len(b)) + t + b + struct.pack(">I", zlib.crc32(t + b) & 0xffffffff)

    with open(path, "wb") as f:
        f.write(b"\x89PNG\r\n\x1a\n"
                + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 3, 0, 0, 0))
                + chunk(b"PLTE", pal.plte)
                + (chunk(b"tRNS", b"\0") if pal.keyed else b"")
                + chunk(b"IDAT", zlib.compress(bytes(raw), 6))
                + chunk(b"IEND", b""))


def frames_to_strip(blob, size, count):
    """Frames stored one after another (size*size each) -> one row, row-major pixels."""
    fs = size * size
    w = size * count
    out = bytearray(w * size)
    for f in range(count):
        frame = blob[f * fs:(f + 1) * fs]
        for y in range(size):
            out[y * w + f * size:y * w + (f + 1) * size] = frame[y * size:(y + 1) * size]
    return out, w, size


def tiles_to_image(blob, wt, ht, tile=TILE):
    """HMkk.E5: tiles stored one after another, row-major -> one row-major picture."""
    ts = tile * tile
    w, h = wt * tile, ht * tile
    out = bytearray(w * h)
    for t in range(wt * ht):
        tx, ty = t % wt, t // wt
        src = blob[t * ts:(t + 1) * ts]
        for y in range(tile):
            o = (ty * tile + y) * w + tx * tile
            out[o:o + tile] = src[y * tile:(y + 1) * tile]
    return out, w, h


def contact_sheet(path, cells, cw, ch, pal, cols=10, pad=2):
    """cells: index blobs of cw x ch, 10 per row (cell index = row * 10 + column)."""
    rows = (len(cells) + cols - 1) // cols
    W, H = cols * (cw + pad) + pad, rows * (ch + pad) + pad
    cv = bytearray(W * H)
    for k, img in enumerate(cells):
        x0, y0 = pad + (k % cols) * (cw + pad), pad + (k // cols) * (ch + pad)
        for y in range(ch):
            o = (y0 + y) * W + x0
            cv[o:o + cw] = img[y * cw:(y + 1) * cw]
    write_png(path, W, H, cv, pal)


# ---------------------------------------------------------------- game data
class Game:
    def __init__(self, root):
        self.root = root
        self._cache = {}

    def path(self, name):
        p = os.path.join(self.root, name)
        if os.path.exists(p):
            return p
        # case-insensitive lookup (copies from CD/Steam may differ in case)
        low = name.lower()
        for f in os.listdir(self.root):
            if f.lower() == low:
                return os.path.join(self.root, f)
        return None

    def need(self, name):
        p = self.path(name)
        if not p:
            raise SystemExit("missing %s in %s" % (name, self.root))
        return p

    def ls12(self, name):
        if name not in self._cache:
            self._cache[name], _ = ls12.decompress_file(self.need(name))
        return self._cache[name]

    def battle_names(self):
        """IMSG.E5 records 250.. are the battle names."""
        if not self.path("IMSG.E5"):
            return {}
        imsg = extract.load_imsg(self.root)
        return {k: imsg[250 + k] for k in range(64) if 250 + k < len(imsg)}

    def terrain_names(self):
        """30 terrain names from the EXE string table."""
        if not self.path("EKD5.EXE"):
            return []
        try:
            return extract.load_exe_tables(self.root)["terrain"]
        except (SystemExit, AssertionError):
            return []


# ---------------------------------------------------------------- exporters
def mkdir(*parts):
    p = os.path.join(*parts)
    os.makedirs(p, exist_ok=True)
    return p


def export_units(g, out, contact):
    mov, atk, spc = g.ls12("UNIT_MOV.E5"), g.ls12("UNIT_ATK.E5"), g.ls12("UNIT_SPC.E5")
    pal = Palette(g.ls12("SPALET.E5")[0], keyed=True)
    d = mkdir(out, "units")
    n = min(len(mov), len(atk), len(spc))
    for i in range(n):
        for blob, (size, count), tag in ((mov[i], MOV, "mov"), (atk[i], ATK, "atk"), (spc[i], SPC, "spc")):
            img, w, h = frames_to_strip(blob, size, count)
            write_png(os.path.join(d, "unit_%03d_%s.png" % (i, tag)), w, h, img, pal)
    if contact:
        c = mkdir(out, "contact")
        contact_sheet(os.path.join(c, "units_stand_down.png"),
                      [m[6 * 2304:7 * 2304] for m in mov[:n]], 48, 48, pal)
        contact_sheet(os.path.join(c, "units_attack_side.png"),
                      [a[9 * 4096:10 * 4096] for a in atk[:n]], 64, 64, pal)
    return n


def export_faces(g, out, contact):
    faces = g.ls12("FACE.E5")
    pal = Palette(g.ls12("SPALET.E5")[0], keyed=False)  # framed portraits, no key
    d = mkdir(out, "faces")
    for i, f in enumerate(faces):
        write_png(os.path.join(d, "face_%03d.png" % i), 64, 80, f[:64 * 80], pal)
    if contact:
        contact_sheet(os.path.join(mkdir(out, "contact"), "faces.png"),
                      [f[:64 * 80] for f in faces], 64, 80, pal)
    return len(faces)


def export_items(g, out, contact):
    items = g.ls12("ITEM.E5")
    pal = Palette(g.ls12("SPALET.E5")[0], keyed=True)
    d = mkdir(out, "items")
    for i, f in enumerate(items):
        write_png(os.path.join(d, "item_%03d.png" % i), 32, 32, f[:32 * 32], pal)
    if contact:
        contact_sheet(os.path.join(mkdir(out, "contact"), "items.png"),
                      [f[:32 * 32] for f in items], 32, 32, pal)
    return len(items)


def export_maps(g, out, contact):
    grids = g.ls12("HEXZMAP.E5")
    pals = g.ls12("SPALET.E5")
    names = g.battle_names()
    tnames = g.terrain_names()
    d = mkdir(out, "maps")
    done = []
    for k, m in enumerate(grids):
        wt, ht = m[0] // 3, m[1] // 3
        grid = list(m[2:2 + wt * ht])
        hp = g.path("HM%02d.E5" % k)
        if not hp or os.path.getsize(hp) != wt * ht * TILE * TILE or k >= len(pals):
            print("  skip map %02d (image missing or size mismatch)" % k)
            continue
        img, w, h = tiles_to_image(open(hp, "rb").read(), wt, ht)
        write_png(os.path.join(d, "map_%02d.png" % k), w, h, img, Palette(pals[k], keyed=False))
        meta = {"id": k, "name": names.get(k, ""), "width": wt, "height": ht, "tileSize": TILE,
                "terrain": grid, "terrainNames": tnames}
        with open(os.path.join(d, "map_%02d_grid.json" % k), "w", encoding="utf-8") as f:
            json.dump(meta, f, ensure_ascii=False, separators=(",", ":"))
        done.append({"id": k, "name": meta["name"], "width": wt, "height": ht})
    return done


def export_backgrounds(g, out, contact):
    bgs = g.ls12("MMAP.E5")
    pals = g.ls12("PMPALET.E5")
    d = mkdir(out, "backgrounds")
    n = 0
    for i, b in enumerate(bgs):
        if len(b) < 640 * 400 or i >= len(pals):
            continue
        write_png(os.path.join(d, "bg_%03d.png" % i), 640, 400, b[:640 * 400], Palette(pals[i], keyed=False))
        n += 1
    return n


def export_sfx(g, out, contact):
    d = mkdir(out, "sfx")
    names = sorted(f for f in os.listdir(g.root) if f.upper().startswith("SE") and f.upper().endswith(".WAV"))
    for f in names:
        shutil.copyfile(os.path.join(g.root, f), os.path.join(d, f.upper()))
    return [os.path.splitext(f.upper())[0] for f in names]


# ---------------------------------------------------------------- main
def main(argv):
    ap = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    ap.add_argument("game")
    ap.add_argument("out", nargs="?", default=DEFAULT_OUT)
    ap.add_argument("--only", default=",".join(KINDS))
    ap.add_argument("--contact", action="store_true")
    a = ap.parse_args(argv)
    kinds = [k.strip() for k in a.only.split(",") if k.strip()]
    bad = [k for k in kinds if k not in KINDS + ("manifest",)]
    if bad:
        ap.error("unknown kind(s): %s" % ", ".join(bad))
    g = Game(a.game)
    os.makedirs(a.out, exist_ok=True)

    man_path = os.path.join(a.out, "classic_art.json")
    manifest = {}
    if os.path.exists(man_path):  # keep counts of kinds not re-exported this run
        with open(man_path, encoding="utf-8") as f:
            manifest = json.load(f)
    with open(SPRITE_MAP, encoding="utf-8") as f:
        sm = json.load(f)

    if "units" in kinds:
        manifest["unitSets"] = export_units(g, a.out, a.contact)
        print("units: %d sprite sets" % manifest["unitSets"])
    if "faces" in kinds:
        manifest["faces"] = export_faces(g, a.out, a.contact)
        print("faces: %d" % manifest["faces"])
    if "items" in kinds:
        manifest["items"] = export_items(g, a.out, a.contact)
        print("items: %d" % manifest["items"])
    if "maps" in kinds:
        manifest["maps"] = export_maps(g, a.out, a.contact)
        print("maps: %d" % len(manifest["maps"]))
    if "backgrounds" in kinds:
        manifest["backgrounds"] = export_backgrounds(g, a.out, a.contact)
        print("backgrounds: %d" % manifest["backgrounds"])
    if "sfx" in kinds:
        manifest["sfx"] = export_sfx(g, a.out, a.contact)
        print("sfx: %d" % len(manifest["sfx"]))

    # Flattened for Unity's JsonUtility (no nested dictionaries).
    mv, at, sp = (sm["frames"][k] for k in ("move", "attack", "special"))
    manifest.update({
        "source": "1998 PC 삼국지 조조전 (Koei), exported by scripts/ekd5/export_art.py",
        "tileSize": TILE,
        "moveFrame": mv["size"], "moveFrames": mv["count"],
        "attackFrame": at["size"], "attackFrames": at["count"],
        "specialFrame": sp["size"], "specialFrames": sp["count"],
        "anims": [{"name": n, "sheet": sheet, "frames": fr}
                  for sheet, spec in (("mov", mv), ("atk", at), ("spc", sp))
                  for n, fr in spec["anims"].items()],
        "jobs": [{"job": j["job"], "name": j["name"], "set": j["set"], "colors": j["colors"],
                  "confidence": j["confidence"]} for j in sm["jobs"]],
        "officers": [{"officer": o["officer"], "name": o["name"], "set": o["set"], "colors": o["colors"],
                      "confidence": o["confidence"]} for o in sm["officers"]],
    })
    with open(man_path, "w", encoding="utf-8") as f:
        json.dump(manifest, f, ensure_ascii=False, indent=1)
    print("wrote %s" % man_path)
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
