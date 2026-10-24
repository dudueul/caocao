/**
 * The horse (ADR 0046, ADR 0073): no stirrups, a low saddle with saddle flaps
 * in the side's dye, a bronze face ornament (當盧), a trimmed upright mane, a
 * tied forelock and a knotted tail, as on Qin and Han horse figures. Chunky
 * like its rider, and painted in the same cel style. Tier-2 shock cavalry
 * ride barded horses. The gait is a two-step walk; the strike lunges.
 */
import { BRONZE, BRONZE_DARK, BRONZE_LIGHT, GOLD } from "../portrait/palette.ts";
import {
  capsule,
  cel,
  circle,
  dot,
  hullShape,
  INK,
  inside,
  LW2,
  line,
  polygon,
  type Tri,
} from "./draw.ts";
import { add, type Pt, rotX, type V3 } from "./geom.ts";
import type { SpriteLook } from "./look.ts";
import type { Item, Proj } from "./person.ts";

const MANE: Tri = ["#2a1d14", "#140e0a", "#4a3a2e"];
const HOOF: Tri = ["#3a2e26", "#1e1612", "#5a4a40"];

/** The horse's stance: legs (front right, front left, back right, back left) as forward swing and lift. */
function legs(gait: number): [number, number][] {
  switch (gait) {
    case 1:
      return [
        [0.08, 0.06],
        [-0.05, 0],
        [-0.05, 0],
        [0.08, 0.05],
      ];
    case 2:
      return [
        [-0.05, 0],
        [0.08, 0.06],
        [0.08, 0.05],
        [-0.05, 0],
      ];
    case 3:
      // The lunge of a strike: forelegs reaching.
      return [
        [0.14, 0.1],
        [0.1, 0.06],
        [-0.08, 0],
        [-0.06, 0],
      ];
    default:
      return [
        [0, 0],
        [0, 0],
        [0, 0],
        [0, 0],
      ];
  }
}

export function horseItems(L: SpriteLook, P: Proj, gait: number, k: number, shift: V3): Item[] {
  const coat = L.coat ?? (["#8a5a34", "#5e3b1f", "#b07a4e"] as Tri);
  const items: Item[] = [];
  // Rearing a little in the lunge.
  const pitch = gait === 3 ? -0.12 : 0;
  const pivot: V3 = [0, -0.2 * k, -0.25 * k];
  const T = (p: V3): V3 =>
    add(
      add(rotX([p[0] * k - pivot[0], p[1] * k - pivot[1], p[2] * k - pivot[2]], pitch), pivot),
      shift,
    );
  const pt = (x: number, y: number, z: number) => P.p(T([x, y, z]));
  const dep = (x: number, y: number, z: number) => P.d(T([x, y, z]));
  // Legs: hips and shoulders to knees to hooves.
  const L4 = legs(gait);
  const roots: [number, number][] = [
    [-0.075, 0.2],
    [0.075, 0.2],
    [-0.075, -0.2],
    [0.075, -0.2],
  ];
  roots.forEach(([x, z], i) => {
    const [sw, lift] = L4[i] as [number, number];
    const top: V3 = [x, -0.32, z];
    const knee: V3 = [x, -0.15 + lift * 0.5, z + sw * 0.6 + (i < 2 ? 0.01 : -0.02)];
    const hoof: V3 = [x, -0.02 + lift, z + sw];
    const far = x > 0;
    const col: Tri = far ? [coat[1], coat[1], coat[0]] : coat;
    items.push({
      // Under the barrel, the near legs over the far.
      d: dep(0, -0.37, 0) - 0.05 + dep(x, -0.2, z) * 0.01,
      f: (g) => {
        cel(
          g,
          capsule(pt(...top), pt(...knee), 0.05 * k, 0.034 * k),
          col[0],
          col[1],
          0.012,
          col[2],
        );
        cel(
          g,
          capsule(pt(...knee), pt(...hoof), 0.033 * k, 0.028 * k),
          col[0],
          col[1],
          0.01,
          col[2],
        );
        const h = pt(...hoof);
        cel(
          g,
          capsule([h[0], h[1] - 0.01 * k], [h[0], h[1] + 0.015 * k], 0.034 * k, 0.034 * k),
          HOOF[0],
          HOOF[1],
          0.006,
          HOOF[2],
        );
      },
    });
  });
  // The barrel: an ellipsoid along the horse.
  const barrel = (): Pt[] => {
    const pts: Pt[] = [];
    for (let i = 0; i <= 8; i++) {
      const z = -0.27 + (0.56 * i) / 8;
      const s = Math.sqrt(Math.max(0, 1 - ((z - 0.01) / 0.3) ** 2));
      for (let j = 0; j < 12; j++) {
        const a = (j / 12) * Math.PI * 2;
        pts.push(pt(Math.cos(a) * 0.15 * s, -0.37 + Math.sin(a) * 0.14 * s, z));
      }
    }
    return pts;
  };
  items.push({
    d: dep(0, -0.37, 0),
    f: (g) => {
      // The knotted tail.
      const root = pt(0, -0.43, -0.29);
      const end = pt(0, -0.18, -0.38);
      cel(g, capsule(root, end, 0.03 * k, 0.045 * k), MANE[0], MANE[1], 0.008, MANE[2]);
      cel(g, circle(root[0], root[1], 0.035 * k), MANE[0], MANE[1], 0.006, MANE[2], LW2);
      const b = hullShape(barrel());
      cel(g, b, coat[0], coat[1], 0.04, coat[2]);
      // Saddle flaps (障泥) in the side's dye, and the low saddle.
      for (const s of [-1, 1]) {
        if (P.dv([s, 0, 0]) < -0.2) continue;
        const f = polygon([
          pt(s * 0.13, -0.42, -0.1),
          pt(s * 0.13, -0.42, 0.1),
          pt(s * 0.135, -0.27, 0.1),
          pt(s * 0.135, -0.27, -0.1),
        ]);
        cel(g, f, L.P.robe, L.P.robeDark, 0.012);
        g.lineWidth = 0.012;
        g.strokeStyle = L.gilt ? GOLD : L.P.edge;
        g.stroke(f.p);
        g.lineWidth = LW2;
        g.strokeStyle = INK;
        g.stroke(f.p);
      }
      cel(
        g,
        hullShape([
          pt(-0.1, -0.47, -0.1),
          pt(0.1, -0.47, -0.1),
          pt(0.1, -0.47, 0.1),
          pt(-0.1, -0.47, 0.1),
          pt(0, -0.52, -0.11),
          pt(0, -0.5, 0.11),
        ]),
        "#6b4529",
        "#4a2e1b",
        0.01,
        "#8d6440",
        LW2,
      );
      if (L.barded)
        // Lamellar barding over the chest and flanks.
        inside(g, b, () => {
          for (let i = 0; i < 4; i++) {
            const y = -0.34 + i * 0.05;
            const a = pt(-0.14, y, 0.25);
            const c = pt(0.14, y, 0.25);
            line(g, "#16181c", 0.01, a[0], a[1], c[0], c[1]);
          }
          const chest = hullShape([
            pt(-0.13, -0.42, 0.2),
            pt(0.13, -0.42, 0.2),
            pt(0.13, -0.26, 0.24),
            pt(-0.13, -0.26, 0.24),
            pt(0, -0.24, 0.3),
          ]);
          cel(g, chest, "#767b84", "#4c5058", 0.012, "#a9aeb6", LW2);
        });
    },
  });
  // Neck and head, reaching forward and down; mane, forelock, ears, face ornament.
  const neckA: V3 = [0, -0.43, 0.2];
  const neckB: V3 = [0, -0.62, 0.36];
  const headB: V3 = [0, -0.5, 0.56];
  items.push({
    d: dep(0, -0.55, 0.38),
    f: (g) => {
      cel(
        g,
        capsule(pt(...neckA), pt(...neckB), 0.1 * k, 0.072 * k),
        coat[0],
        coat[1],
        0.02,
        coat[2],
      );
      // The trimmed, upright mane along the neck's top.
      for (let i = 0; i <= 5; i++) {
        const t = i / 5;
        const p = pt(0, -0.5 - 0.15 * t, 0.15 + 0.17 * t);
        cel(
          g,
          polygon([
            [p[0] - 0.018 * k, p[1] + 0.01 * k],
            [p[0], p[1] - 0.045 * k],
            [p[0] + 0.018 * k, p[1] + 0.01 * k],
          ]),
          MANE[0],
          MANE[1],
          0,
          undefined,
          LW2 * 0.8,
        );
      }
      const ha = pt(...neckB);
      const hb = pt(...headB);
      cel(g, capsule(ha, hb, 0.072 * k, 0.052 * k), coat[0], coat[1], 0.015, coat[2]);
      // Ears and the tied forelock.
      for (const s of [-1, 1]) {
        const e = pt(s * 0.04, -0.69, 0.35);
        cel(
          g,
          polygon([
            [e[0] - 0.012 * k, e[1] + 0.01 * k],
            [e[0] + s * 0.004, e[1] - 0.05 * k],
            [e[0] + 0.014 * k, e[1] + 0.01 * k],
          ]),
          coat[0],
          coat[1],
          0,
          undefined,
          LW2,
        );
      }
      const fl = pt(0, -0.67, 0.4);
      dot(g, MANE[0], fl[0], fl[1], 0.02 * k);
      // The bronze face ornament, the bridle, the eyes.
      if (P.dv([0, 0, 1]) > -0.2) {
        const o = pt(0, -0.6, 0.47);
        cel(
          g,
          polygon([
            [o[0] - 0.018 * k, o[1] - 0.03 * k],
            [o[0] + 0.018 * k, o[1] - 0.03 * k],
            [o[0] + 0.01 * k, o[1] + 0.035 * k],
            [o[0] - 0.01 * k, o[1] + 0.035 * k],
          ]),
          BRONZE,
          BRONZE_DARK,
          0.005,
          BRONZE_LIGHT,
          LW2 * 0.8,
        );
      }
      const nose = pt(0, -0.49, 0.6);
      const cheek = pt(0, -0.62, 0.4);
      line(g, "#4a2e1b", 0.01, cheek[0], cheek[1], nose[0], nose[1]);
      for (const s of [-1, 1]) {
        if (P.dv([s, 0, 0.6]) < 0) continue;
        const e = pt(s * 0.058, -0.61, 0.43);
        dot(g, "#1c1214", e[0], e[1], 0.012 * k);
      }
    },
  });
  return items;
}
