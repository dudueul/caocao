/**
 * The stone-thrower (ADR 0046, ADR 0073): a traction trebuchet on a wheeled
 * cart, after the "thunderbolt carts" (霹靂車) 曹操 built at Guandu, with a
 * crew hauling ropes on the short arm and a banner in the side's dye. The
 * tier-2 벽력거 is bigger and iron-shod, with a mantlet and a second hauler.
 * Its attack is the throw: the crew haul, the long arm whips over, the sling
 * lets go.
 */
import {
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  IRON,
  IRON_DARK,
  IRON_LIGHT,
  WOOD,
  WOOD_DARK,
  WOOD_LIGHT,
} from "../portrait/palette.ts";
import {
  capsule,
  cel,
  circle,
  dot,
  ellipse,
  type G,
  hullShape,
  inside,
  LW2,
  line,
  mix,
  polygon,
  type Tri,
} from "./draw.ts";
import { add, mul, type Pt, type V3 } from "./geom.ts";
import type { SpriteLook } from "./look.ts";
import type { Item, Proj } from "./person.ts";

const WOOD3: Tri = [WOOD, WOOD_DARK, WOOD_LIGHT];
const DARK3: Tri = [WOOD_DARK, "#3a2414", WOOD];
const ROPE = "#cbb98f";

/** The beam's long-arm angle (radians above the backward horizontal) for a beam setting. */
function beamAngle(b: number): number {
  const table: [number, number][] = [
    [0, -0.62],
    [0.4, -0.25],
    [1, -0.45],
    [2, 1.95],
    [2.4, 2.4],
  ];
  for (let i = 1; i < table.length; i++) {
    const [b0, a0] = table[i - 1] as [number, number];
    const [b1, a1] = table[i] as [number, number];
    if (b <= b1) return a0 + ((a1 - a0) * (b - b0)) / (b1 - b0 || 1);
  }
  return (table[table.length - 1] as [number, number])[1];
}

/** Engine items: wheels, the cart, uprights, beam, sling, banner. Crew are drawn by the caller. */
export function engineItems(L: SpriteLook, P: Proj, beam: number, roll: number): Item[] {
  const big = L.tier2;
  const k = big ? 1.26 : 1.12;
  const metal: Tri = big ? [IRON, IRON_DARK, IRON_LIGHT] : [BRONZE, BRONZE_DARK, BRONZE_LIGHT];
  const pt = (x: number, y: number, z: number): Pt => P.p([x * k, y * k, z * k]);
  const dep = (x: number, y: number, z: number): number => P.d([x * k, y * k, z * k]);
  const items: Item[] = [];
  const axleY = -0.62;
  const bedTop = -0.25;
  const bedBot = -0.1;
  const beamA = beamAngle(beam);
  // The long arm lies back at rest (−z) and swings up and over to the front.
  const longDir: V3 = [0, -Math.sin(beamA), -Math.cos(beamA)];
  const longEnd: V3 = add([0, axleY, 0], mul(longDir, 0.68));
  const shortEnd: V3 = add([0, axleY, 0], mul(longDir, -0.24));
  const post = (g: G, a: Pt, b: Pt, w = 0.034, col = WOOD3) =>
    cel(g, capsule(a, b, w * k, w * k), col[0], col[1], 0.01, col[2], LW2 * 1.4);
  // Wheels: under the cart, the near ones over it.
  const side = Math.abs(P.dv([1, 0, 0]));
  for (const x of [-0.27, 0.27])
    for (const z of [-0.22, 0.22]) {
      const near = P.dv([x, 0, 0]) > 0;
      items.push({
        d: dep(0, -0.17, 0) + (near ? 0.02 : -0.02) + dep(x, 0, z) * 0.001,
        f: (g) => {
          const c = pt(x, -0.12, z);
          const r = 0.12 * k;
          const rx = r * Math.max(0.32, side);
          const w = ellipse(c[0], c[1], rx, r);
          cel(g, w, DARK3[0], DARK3[1], 0.012, DARK3[2], LW2 * 1.4);
          inside(g, w, () => {
            for (let i = 0; i < 3; i++) {
              const a = roll + (i * Math.PI) / 3;
              const dx = Math.cos(a) * rx;
              const dy = Math.sin(a) * r;
              line(g, "#3a2414", 0.012, c[0] - dx, c[1] - dy, c[0] + dx, c[1] + dy);
            }
            const rim = ellipse(c[0], c[1], rx * 0.78, r * 0.78);
            g.strokeStyle = "#3a2414";
            g.lineWidth = 0.01;
            g.stroke(rim.p);
          });
          cel(g, circle(c[0], c[1], 0.024 * k), metal[0], metal[1], 0.004, metal[2], LW2);
        },
      });
    }
  // The cart's bed: a plank box, its near side darker.
  items.push({
    d: dep(0, -0.17, 0),
    f: (g) => {
      const corners: Pt[] = [];
      for (const x of [-0.24, 0.24])
        for (const y of [bedTop, bedBot]) for (const z of [-0.33, 0.33]) corners.push(pt(x, y, z));
      const box = hullShape(corners);
      cel(g, box, WOOD3[0], WOOD3[1], 0.02, WOOD3[2]);
      inside(g, box, () => {
        // Planks along the bed's top, and its visible side and end in shadow.
        for (const x of [-0.12, 0, 0.12]) {
          const a = pt(x, bedTop, -0.33);
          const b = pt(x, bedTop, 0.33);
          line(g, WOOD_DARK, 0.009, a[0], a[1], b[0], b[1]);
        }
        const sx = P.dv([1, 0, 0]) > 0 ? 0.24 : -0.24;
        const face = polygon([
          pt(sx, bedTop, -0.33),
          pt(sx, bedTop, 0.33),
          pt(sx, bedBot, 0.33),
          pt(sx, bedBot, -0.33),
        ]);
        g.fillStyle = "rgba(40,24,12,0.35)";
        g.fill(face.p);
        const fz = P.dv([0, 0, 1]) > 0 ? 0.33 : -0.33;
        const end = polygon([
          pt(-0.24, bedTop, fz),
          pt(0.24, bedTop, fz),
          pt(0.24, bedBot, fz),
          pt(-0.24, bedBot, fz),
        ]);
        g.fillStyle = "rgba(40,24,12,0.2)";
        g.fill(end.p);
      });
      // Metal straps across the bed.
      for (const z of [-0.3, 0.3]) {
        const a = pt(-0.24, bedTop, z);
        const b = pt(0.24, bedTop, z);
        line(g, metal[1], 0.016, a[0], a[1], b[0], b[1]);
      }
    },
  });
  // The uprights (two A-frames), their braces, the axle.
  items.push({
    d: dep(0, -0.4, 0) + 0.01,
    f: (g) => {
      for (const x of [-0.14, 0.14]) {
        for (const z of [-0.24, 0.24]) post(g, pt(x * 1.45, bedTop, z), pt(x, axleY, 0));
        const mid = (bedTop + axleY) / 2;
        post(g, pt(x * 1.2, mid, -0.12), pt(x * 1.2, mid, 0.12), 0.022);
      }
      post(g, pt(-0.19, axleY, 0), pt(0.19, axleY, 0), 0.026, DARK3);
      for (const x of [-0.14, 0.14]) {
        const c = pt(x, axleY, 0);
        cel(g, circle(c[0], c[1], 0.03 * k), metal[0], metal[1], 0.006, metal[2], LW2);
      }
    },
  });
  if (big)
    // A mantlet of planks at the front.
    items.push({
      d: dep(0, -0.3, 0.36),
      f: (g) => {
        const m = polygon([
          pt(-0.27, bedTop + 0.02, 0.36),
          pt(0.27, bedTop + 0.02, 0.36),
          pt(0.27, -0.5, 0.4),
          pt(-0.27, -0.5, 0.4),
        ]);
        cel(g, m, WOOD, WOOD_DARK, 0.015, WOOD_LIGHT);
        inside(g, m, () => {
          for (const x of [-0.13, 0, 0.13]) {
            const a = pt(x, bedTop, 0.36);
            const b = pt(x, -0.5, 0.4);
            line(g, WOOD_DARK, 0.008, a[0], a[1], b[0], b[1]);
          }
          const a = pt(-0.27, -0.38, 0.38);
          const b = pt(0.27, -0.38, 0.38);
          line(g, IRON_DARK, 0.014, a[0], a[1], b[0], b[1]);
        });
      },
    });
  // The banner at the back corner.
  items.push({
    d: dep(0.24, -0.4, -0.33),
    f: (g) => {
      const bot = pt(0.24, bedTop, -0.33);
      const top = pt(0.24, -1.0, -0.33);
      post(g, bot, top, 0.014, DARK3);
      const back = P.dv([0, 0, 1]) < 0 ? 1 : -1;
      const flag = polygon([
        top,
        [top[0] + back * 0.22 * k, top[1] + 0.02],
        [top[0] + back * 0.21 * k, top[1] + 0.18 * k],
        [top[0], top[1] + 0.16 * k],
      ]);
      cel(g, flag, L.P.robe, L.P.robeDark, 0.012, mix(L.P.robe, "#ffffff", 0.3), LW2);
      inside(g, flag, () =>
        line(
          g,
          L.P.edge,
          0.014,
          top[0] + back * 0.035,
          top[1],
          top[0] + back * 0.035,
          top[1] + 0.18 * k,
        ),
      );
    },
  });
  // The beam, the sling with its stone, the hauling ropes.
  items.push({
    d: dep(0, axleY, 0) + 0.03 + Math.max(0, P.d(longEnd)) * 0.2,
    f: (g) => {
      const a = pt(...longEnd);
      const b = pt(...shortEnd);
      cel(g, capsule(b, a, 0.042 * k, 0.026 * k), WOOD3[0], WOOD3[1], 0.01, WOOD3[2], LW2 * 1.4);
      for (const t of [0.25, 0.75]) {
        const p: Pt = [b[0] + (a[0] - b[0]) * t, b[1] + (a[1] - b[1]) * t];
        dot(g, metal[1], p[0], p[1], 0.016 * k);
      }
      const thrown = beam >= 1.5;
      const pouch: Pt = thrown
        ? [a[0] + (a[0] - b[0]) * 0.22, a[1] + (a[1] - b[1]) * 0.22]
        : [a[0], a[1] + 0.16 * k];
      line(g, ROPE, 0.009, a[0], a[1], pouch[0], pouch[1]);
      cel(
        g,
        ellipse(pouch[0], pouch[1], 0.034 * k, 0.024 * k),
        "#6b4529",
        "#4a2e1b",
        0.006,
        undefined,
        LW2,
      );
      if (!thrown)
        cel(
          g,
          circle(pouch[0], pouch[1] - 0.014, 0.032 * k),
          "#8f8a80",
          "#6a655c",
          0.006,
          "#c8c2b8",
          LW2,
        );
      // Ropes hang from the short arm to the crew.
      for (const dx of [-0.025, 0.025])
        line(g, ROPE, 0.008, b[0] + dx, b[1], b[0] + dx * 3, b[1] + 0.34 * k);
    },
  });
  return items;
}

/** Where the crew stand, relative to the engine: one beside it, a second for the 벽력거. */
export function crewSpots(L: SpriteLook): V3[] {
  return L.tier2
    ? [
        [-0.36, 0, 0.36],
        [0.38, 0, 0.3],
      ]
    : [[-0.34, 0, 0.36]];
}
