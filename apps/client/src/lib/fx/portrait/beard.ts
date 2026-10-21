/**
 * Beards and moustaches drawn from strands (ADR 0070): tapered ribbons rooted
 * along the jaw and chin of the head model, hanging by their own weight. All
 * strands are inked first and filled after, so only the beard's outer edge
 * keeps the ink line; a sheen and a shadow run down each strand.
 */
import {
  alpha,
  cel,
  clamp,
  curve,
  dot,
  type G,
  INK,
  inside,
  lerp,
  line,
  mix,
  onCurve,
  type Pt,
  path,
  ribbon,
  rng,
} from "./draw.ts";
import { mouthPts } from "./face.ts";
import { DEG, type Head } from "./head.ts";
import type { Look } from "./look.ts";
import { BRONZE, BRONZE_DARK, GAUZE, GAUZE_THREAD, GOLD } from "./palette.ts";
import type { BeardKind, StacheKind } from "./types.ts";

/** The usual moustache for each beard: the hanging Han moustache unless said otherwise. */
export const STACHE_FOR: Record<BeardKind, StacheKind> = {
  none: "none",
  stub: "none",
  mous: "tong",
  tong: "tong",
  goat: "thin",
  slim: "thin",
  three: "tong",
  spade: "tong",
  full: "tong",
  bag: "long",
  tiger: "bristle",
  wild: "bristle",
  fork: "tong",
  chops: "tong",
  braid: "long",
  wisp: "thin",
  whiteLong: "long",
};

interface Strand {
  a: Pt;
  c: Pt;
  b: Pt;
  w0: number;
  w1: number;
  z: number;
}

/** The visible azimuth range of the face's cross-section at level y, inset a little. */
function vis(H: Head, th: number, y: number): number {
  const [l0, l1] = H.limbs(Math.max(H.rw(y), 1), Math.max(H.df(y), 1), Math.max(H.db(y), 1));
  return clamp(th, l0 + 4 * DEG, l1 - 4 * DEG);
}

/** A root on the jawline: u -1 near sideburn, 0 chin, +1 far sideburn. */
function root(H: Head, u: number): [number, number, number] {
  const au = Math.abs(u);
  const y = lerp(10, H.ycb - 3, (1 - au) ** 0.75);
  const th = vis(H, u * 84 * DEG, y);
  const p = H.proj(th, y, 1.03);
  return [p.x, p.y, p.z];
}

/** The upper edge of the beard on the cheek, from sideburn to under the lip. */
function cheekLine(H: Head, u: number, high: number): Pt {
  const au = Math.abs(u);
  const y =
    au > 0.6
      ? lerp(6 - high * 6, 22 - high * 4, (1 - au) / 0.4)
      : au > 0.3
        ? lerp(22 - high * 4, H.my - 2, (0.6 - au) / 0.3)
        : lerp(H.my - 2, H.my + 9, (0.3 - au) / 0.3);
  const th = vis(H, u * 82 * DEG, y);
  return H.pt(th, y, 1.01);
}

function drawStrands(g: G, L: Look, strands: Strand[], mass: Path2D | null, light = false): void {
  const [hc0, hd0, hl0] = L.hair;
  const hc = light ? mix(hc0, "#ffffff", 0.1) : hc0;
  const hd = hd0;
  const hl = hl0;
  strands.sort((p, q) => p.z - q.z);
  const ribs = strands.map((s) => ribbon(s.a, s.c, s.b, s.w0, s.w1, 10));
  g.lineJoin = "round";
  // The ink pass: every strand and the mass, wide; the fills then cover the inner edges.
  g.strokeStyle = INK;
  g.lineWidth = 4;
  if (mass) g.stroke(mass);
  for (const r of ribs) g.stroke(r);
  if (mass) cel(g, mass, hc, hd, 5, undefined, 0);
  for (const [i, r] of ribs.entries()) {
    const s = strands[i] as Strand;
    g.fillStyle = s.z < 0 ? hd : hc;
    g.fill(r);
  }
  // Shadow and sheen down each strand.
  for (const s of strands) {
    const [x1, y1] = onCurve(s.a, s.c, s.b, 0.15);
    const [x2, y2] = onCurve(s.a, s.c, s.b, 0.85);
    curve(g, alpha(hd, 0.85), 1.4, x1 - 1.5, y1, s.c[0] - 1.5, s.c[1], x2 - 1, y2);
    curve(g, alpha(hl, 0.8), 1.3, x1 + 1.5, y1, s.c[0] + 1.5, s.c[1], x2 + 0.5, y2);
  }
}

/** Facial hair on the cheeks and chin: the mass between the cheek line and the jaw. */
function cheekMass(H: Head, from: number, to: number, high: number, below: number): Path2D {
  const n = 16;
  const top: Pt[] = [];
  const bot: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const u = from + ((to - from) * i) / n;
    top.push(cheekLine(H, u, high));
    const [x, y] = root(H, u);
    bot.push([x, y + below * (1 - Math.abs(u) * 0.6)]);
  }
  return path((p) => {
    p.moveTo((top[0] as Pt)[0], (top[0] as Pt)[1]);
    for (const q of top) p.lineTo(q[0], q[1]);
    for (let i = bot.length - 1; i >= 0; i--) p.lineTo((bot[i] as Pt)[0], (bot[i] as Pt)[1]);
    p.closePath();
  });
}

interface Hang {
  /** Roots from u0 to u1 along the jaw; n strands. */
  u0: number;
  u1: number;
  n: number;
  /** Length at the chin and at the ends of the root range. */
  drop: number;
  dropSide: number;
  /** 0 the tips spread as the roots, 1 they meet at one point. */
  meet: number;
  /** Spread of the tips beyond the roots (px), a square bottom when > 0. */
  spread: number;
  w: number;
  /** Shapes: one point, two points (fork), or a square bottom. */
  shape: "point" | "fork" | "square" | "wild" | "radiate";
  sway: number;
}

function hang(H: Head, _L: Look, o: Hang, seed: number): Strand[] {
  const r = rng(seed);
  const out: Strand[] = [];
  const [cx] = root(H, 0);
  for (let i = 0; i < o.n; i++) {
    const u = o.n === 1 ? (o.u0 + o.u1) / 2 : o.u0 + ((o.u1 - o.u0) * i) / (o.n - 1);
    const [x, y, z] = root(H, u);
    const au = Math.abs(u);
    const span = Math.max(Math.abs(o.u0), Math.abs(o.u1)) || 1;
    const k = au / span;
    let len = lerp(o.drop, o.dropSide, k) * (0.9 + r() * 0.2);
    let tx: number;
    let ty: number;
    switch (o.shape) {
      case "fork": {
        const side = u < 0 ? -1 : 1;
        tx = cx + side * (10 + o.spread) + (x - cx) * 0.15;
        ty = y + len;
        break;
      }
      case "square": {
        tx = x + (x - cx) * 0.35 + (r() - 0.5) * 3;
        ty = Math.max(y + 6, H.ycb + len * 0.9 + (r() - 0.5) * 4);
        len = ty - y;
        break;
      }
      case "radiate": {
        // Tiger whiskers: stiff tufts thrust outward from the jaw.
        const dx = x - cx;
        const dy = y - (H.my + 4);
        const dl = Math.hypot(dx, dy) || 1;
        tx = x + (dx / dl) * len + (r() - 0.5) * 6;
        ty = y + (dy / dl) * len * 0.8 + len * 0.25;
        break;
      }
      case "wild": {
        tx = lerp(x, cx, o.meet) + (r() - 0.5) * o.spread * 2;
        ty = y + len;
        break;
      }
      default: {
        tx = lerp(x, cx + o.sway, o.meet) + (x - cx) * (o.spread / 40);
        ty = y + len;
      }
    }
    const bend = (r() - 0.5) * (o.shape === "wild" ? 18 : 8);
    const c: Pt = [
      (x + tx) / 2 + bend + o.sway * 0.4,
      (y + ty) / 2 - (o.shape === "radiate" ? 0 : len * 0.05),
    ];
    out.push({
      a: [x, y - 2],
      c,
      b: [tx, ty],
      w0: o.w * (1 - k * 0.25),
      w1: o.shape === "radiate" ? 1 : 1.5,
      z: z - (1 - k) * 5,
    });
  }
  return out;
}

/** Stubble: fine dots over the beard's ground. */
function stubble(g: G, L: Look, H: Head, area: Path2D): void {
  const [hc] = L.hair;
  const r = rng(L.seed ^ 0x51ed27);
  inside(g, H.outline(), () => {
    g.fillStyle = alpha(hc, 0.14);
    g.fill(area);
    inside(g, area, () => {
      g.fillStyle = alpha(hc, 0.42);
      const [l, rr] = H.extentAt(40);
      for (let i = 0; i < 260; i++) g.fillRect(l + r() * (rr - l), 4 + r() * (H.ycb + 4), 1.1, 1.1);
    });
  });
}

export function beard(g: G, L: Look): void {
  const H = L.H;
  const t = L.t;
  const kind = t.beard;
  if (kind === "none" || kind === "mous") return;
  const seed = L.seed ^ 0x51ed27;
  if (kind === "stub") {
    stubble(g, L, H, cheekMass(H, -1, 1, 0, 6));
    return;
  }
  const strands: Strand[] = [];
  let mass: Path2D | null = null;
  const age = t.age;
  switch (kind) {
    case "goat":
      mass = cheekMass(H, -0.28, 0.28, 0, 4);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.16,
            u1: 0.16,
            n: 5,
            drop: 14 + age * 3,
            dropSide: 10,
            meet: 0.7,
            spread: 0,
            w: 7,
            shape: "point",
            sway: 2,
          },
          seed,
        ),
      );
      break;
    case "tong":
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.1,
            u1: 0.1,
            n: 3,
            drop: 12 + age * 3,
            dropSide: 10,
            meet: 0.8,
            spread: 0,
            w: 6,
            shape: "point",
            sway: 1,
          },
          seed,
        ),
      );
      break;
    case "slim":
      // 曹操's one long slim tail (細眼長髯): a narrow beard from the chin.
      mass = cheekMass(H, -0.22, 0.22, 0, 3);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.12,
            u1: 0.12,
            n: 5,
            drop: 58,
            dropSide: 44,
            meet: 0.9,
            spread: 0,
            w: 6,
            shape: "point",
            sway: 5,
          },
          seed,
        ),
      );
      break;
    case "three": {
      // 三綹: a long strand from the chin and one from each cheek.
      mass = cheekMass(H, -0.55, 0.55, 0, 3);
      const long = 46 + age * 6;
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.12,
            u1: 0.12,
            n: 5,
            drop: long,
            dropSide: long - 6,
            meet: 0.85,
            spread: 0,
            w: 7,
            shape: "point",
            sway: 3,
          },
          seed,
        ),
      );
      for (const sd of [-1, 1])
        strands.push(
          ...hang(
            H,
            L,
            {
              u0: sd * 0.42,
              u1: sd * 0.52,
              n: 3,
              drop: long * 0.55,
              dropSide: long * 0.5,
              meet: 0.75,
              spread: 0,
              w: 6,
              shape: "point",
              sway: -sd * 2,
            },
            seed + sd,
          ),
        );
      break;
    }
    case "spade":
      mass = cheekMass(H, -0.95, 0.95, 0.2, 8);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.75,
            u1: 0.75,
            n: 13,
            drop: 12,
            dropSide: 4,
            meet: 0,
            spread: 6,
            w: 10,
            shape: "square",
            sway: 0,
          },
          seed,
        ),
      );
      break;
    case "full":
      mass = cheekMass(H, -0.98, 0.98, 0.25, 10);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.85,
            u1: 0.85,
            n: 17,
            drop: 34 + age * 4,
            dropSide: 12,
            meet: 0.45,
            spread: 4,
            w: 10,
            shape: "point",
            sway: 2,
          },
          seed,
        ),
      );
      break;
    case "whiteLong":
      mass = cheekMass(H, -0.98, 0.98, 0.25, 10);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.85,
            u1: 0.85,
            n: 19,
            drop: 76,
            dropSide: 26,
            meet: 0.55,
            spread: 2,
            w: 10,
            shape: "point",
            sway: 6,
          },
          seed,
        ),
      );
      break;
    case "bag":
      mass = cheekMass(H, -0.98, 0.98, 0.3, 10);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.85,
            u1: 0.85,
            n: 19,
            drop: 90,
            dropSide: 40,
            meet: 0.5,
            spread: 0,
            w: 12,
            shape: "point",
            sway: 6,
          },
          seed,
        ),
      );
      break;
    case "tiger":
      mass = cheekMass(H, -1, 1, 0.3, 8);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.95,
            u1: 0.95,
            n: 21,
            drop: 22,
            dropSide: 20,
            meet: 0,
            spread: 0,
            w: 9,
            shape: "radiate",
            sway: 0,
          },
          seed,
        ),
      );
      break;
    case "wild":
      mass = cheekMass(H, -1, 1, 0.3, 10);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.9,
            u1: 0.9,
            n: 19,
            drop: 30,
            dropSide: 18,
            meet: 0.25,
            spread: 9,
            w: 10,
            shape: "wild",
            sway: 0,
          },
          seed,
        ),
      );
      break;
    case "fork":
      mass = cheekMass(H, -0.95, 0.95, 0.2, 8);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.7,
            u1: 0.7,
            n: 14,
            drop: 44,
            dropSide: 18,
            meet: 0,
            spread: 6,
            w: 9,
            shape: "fork",
            sway: 0,
          },
          seed,
        ),
      );
      break;
    case "chops":
      // Side whiskers along the jaw, the chin shaved.
      for (const sd of [-1, 1]) {
        const m = cheekMass(H, sd < 0 ? -1 : 0.4, sd < 0 ? -0.4 : 1, 0.2, 8);
        strands.push(
          ...hang(
            H,
            L,
            {
              u0: sd * 0.95,
              u1: sd * 0.5,
              n: 6,
              drop: 12,
              dropSide: 14,
              meet: 0,
              spread: 2,
              w: 9,
              shape: "point",
              sway: 0,
            },
            seed + sd,
          ),
        );
        mass = mass
          ? path((p) => {
              p.addPath(mass as Path2D);
              p.addPath(m);
            })
          : m;
      }
      break;
    case "braid":
      mass = cheekMass(H, -0.9, 0.9, 0.2, 8);
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.6,
            u1: 0.6,
            n: 11,
            drop: 18,
            dropSide: 8,
            meet: 0.6,
            spread: 0,
            w: 9,
            shape: "point",
            sway: 0,
          },
          seed,
        ),
      );
      break;
    case "wisp":
      strands.push(
        ...hang(
          H,
          L,
          {
            u0: -0.3,
            u1: 0.3,
            n: 7,
            drop: 40 + age * 8,
            dropSide: 26,
            meet: 0.8,
            spread: 0,
            w: 3.6,
            shape: "point",
            sway: 4,
          },
          seed,
        ),
      );
      break;
  }
  drawStrands(g, L, strands, mass, kind === "whiteLong");
  if (kind === "bag") beardBag(g, L);
  if (kind === "braid") braid(g, L);
}

/** 關羽's beard in its gauze bag (the novel, ch. 50 comment on his beard): a long dark sack over the lower beard. */
function beardBag(g: G, L: Look): void {
  const H = L.H;
  const [cx, cy] = root(H, 0);
  const top = cy + 22;
  const sway = 6;
  const bag = path((p) => {
    p.moveTo(cx - 24, top);
    p.quadraticCurveTo(cx + sway - 34, top + 60, cx + sway - 16, top + 130);
    p.quadraticCurveTo(cx + sway, top + 146, cx + sway + 16, top + 130);
    p.quadraticCurveTo(cx + sway + 34, top + 60, cx + 24, top);
    p.closePath();
  });
  g.fillStyle = alpha(GAUZE, 0.62);
  g.fill(bag);
  inside(g, bag, () => {
    for (let k = -60; k < 180; k += 6)
      line(g, alpha(GAUZE_THREAD, 0.35), 1, cx - 30, top + k, cx + 40, top + k - 30);
    // A brocade band in his green.
    g.fillStyle = "#3f7a4a";
    g.fillRect(cx - 40, top - 2, 90, 12);
    for (let x = cx - 36; x < cx + 46; x += 9) dot(g, alpha("#f7dd8a", 0.9), x, top + 4, 1.6);
  });
  g.lineWidth = 2.4;
  g.strokeStyle = INK;
  g.stroke(bag);
  // The drawstring.
  line(g, INK, 4, cx - 26, top + 10, cx + 26, top + 9);
  line(g, GOLD, 2, cx - 26, top + 10, cx + 26, top + 9);
}

/** A braid gathering the beard, bound with bronze rings. */
function braid(g: G, L: Look): void {
  const H = L.H;
  const [cx, cy] = root(H, 0);
  const [hc, hd, hl] = L.hair;
  const n = 6;
  for (let i = 0; i < n; i++) {
    const y = cy + 8 + i * 9;
    const x = cx + 2 + (i % 2 ? 2 : -2);
    cel(
      g,
      path((p) => p.ellipse(x, y, 7 - i * 0.5, 6, i % 2 ? 0.4 : -0.4, 0, Math.PI * 2)),
      hc,
      hd,
      2,
      hl,
      1.8,
    );
  }
  cel(
    g,
    path((p) => p.roundRect(cx - 5, cy + 14, 12, 5, 2)),
    BRONZE,
    BRONZE_DARK,
    2,
  );
  cel(
    g,
    path((p) => p.roundRect(cx - 4, cy + 50, 11, 5, 2)),
    BRONZE,
    BRONZE_DARK,
    2,
  );
}

// ---------------------------------------------------------------- moustache

/**
 * The moustache over the upper lip, in two halves either side of the
 * philtrum. `tong` hangs its ends straight down past the mouth (the shape of
 * 同), the Han grammar; `long` hangs further, `bristle` is a stiff brush.
 */
export function moustache(g: G, L: Look): void {
  const t = L.t;
  const kind = t.stache;
  if (kind === "none") return;
  const _H = L.H;
  const { xl, xr, mx, my } = mouthPts(L);
  const [hc, hd, hl] = L.hair;
  const y0 = my - 8;
  const th = kind === "thin" ? 2.6 : kind === "bristle" ? 6.5 : 4.2;
  const drop = kind === "tong" ? 16 + t.age * 2 : kind === "long" ? 32 : kind === "thin" ? 2 : 3;
  for (const [d, x1] of [
    [-1, xl],
    [1, xr],
  ] as const) {
    const reach = Math.abs(x1 - mx) + (kind === "bristle" ? 7 : 3);
    if (reach < 3) continue;
    const tx = mx + d * reach;
    const ty = my - 1 + drop;
    const half = path((p) => {
      p.moveTo(mx + d * 1.2, y0 - th * 0.6);
      if (kind === "bristle") {
        const ex = tx + d * 4;
        const ey = my + 2;
        p.quadraticCurveTo(mx + d * reach * 0.6, y0 - th * 1.1, ex, ey);
        for (let i = 5; i >= 1; i--) {
          const u = i / 6;
          const bx = mx + d * (reach + 4) * u;
          const by = y0 + th * 0.5 + u * (ey - y0 - th * 0.5);
          p.lineTo(bx + d * 2, by + 4);
          p.lineTo(bx - d * 2, by);
        }
        p.lineTo(mx + d * 1.2, y0 + th * 0.5);
      } else if (drop > 8) {
        // Along the lip to the corner, then straight down.
        p.bezierCurveTo(mx + d * reach * 0.7, y0 - th, tx + d * 2, y0, tx + d * 1.5, my + 4);
        p.lineTo(tx + d * 0.5, ty);
        p.lineTo(tx - d * 2.2, ty - 2);
        p.lineTo(tx - d * 2.5, my + 4);
        p.bezierCurveTo(
          tx - d * 3,
          y0 + 4,
          mx + d * reach * 0.5,
          y0 + th * 0.6,
          mx + d * 1.2,
          y0 + th * 0.5,
        );
      } else {
        p.quadraticCurveTo(mx + d * reach * 0.7, y0 - th * 0.9, tx, my - 1 + drop);
        p.quadraticCurveTo(mx + d * reach * 0.6, y0 + th * 0.7, mx + d * 1.2, y0 + th * 0.5);
      }
      p.closePath();
    });
    cel(g, half, hc, hd, 2, hl, 1.6);
  }
}
