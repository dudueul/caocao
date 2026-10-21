/**
 * The body below the head: the robe in the side's dye (ADR 0055), its collar
 * closing right over left (交領右衽) whichever way the man turns, and the
 * class's dress over it (ADR 0046): lamellar armour, an archer's vest, a
 * strategist's sash, a martial artist's hemp, a crew's rope; the frontier's
 * fleece and fur, the elite's neck guard, the 烏桓's felt coat with its
 * embroidered leather edges. Builds run from small to huge.
 */
import {
  alpha,
  cel,
  curve,
  dot,
  ellipse,
  furRoll,
  type G,
  INK,
  inside,
  LW,
  lamellae,
  line,
  mix,
  onCurve,
  type Pt,
  path,
  poly,
  rng,
} from "./draw.ts";
import { has, type Look } from "./look.ts";
import {
  ACCENTS,
  BLACK_IRON,
  BLACK_IRON_DARK,
  BLACK_IRON_LIGHT,
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  DRAB,
  DRAB_DARK,
  DRAB_LIGHT,
  FLEECE,
  FLEECE_DARK,
  FLEECE_LIGHT,
  FUR,
  GOLD,
  GOLD_DARK,
  GOLD_LIGHT,
  HAN_RED,
  HAN_RED_DARK,
  HEMP,
  HEMP_DARK,
  HEMP_LIGHT,
  IMPERIAL,
  IMPERIAL_DARK,
  IMPERIAL_LIGHT,
  IRON,
  IRON_DARK,
  IRON_LIGHT,
  LACQUER,
  LACQUER_DARK,
  LACQUER_LIGHT,
  LACQUER_RED,
  LEATHER,
  LEATHER_DARK,
  LEATHER_LIGHT,
  STEEL,
  STEEL_DARK,
  STEEL_LIGHT,
  TIGER,
  TIGER_DARK,
  TIGER_LIGHT,
  WHITE_CLOTH,
  WHITE_SHADE,
} from "./palette.ts";
import type { Build } from "./types.ts";

/** Shoulder width by build. */
export const SHOULDERS: Record<Build, number> = {
  small: 0.86,
  slight: 0.92,
  lean: 0.96,
  normal: 1,
  broad: 1.08,
  stout: 1.12,
  huge: 1.28,
};

/** Maps the body's x (drawn for a turn to the viewer's right) to the canvas: scaled by build, mirrored when turned left. */
export type Bx = (x: number) => number;

export function bxOf(L: Look): Bx {
  const s = SHOULDERS[L.t.build];
  // From the front the neckline sits centred; three-quarter shifts it toward the near side.
  const shift = (1 - L.bodyTurn) * 6;
  return L.flip ? (x) => 128 - (x - 128 + shift) * s : (x) => 128 + (x - 128 + shift) * s;
}

interface Cloth {
  robe: string;
  dark: string;
  light: string;
  edge: string;
  edgeDark: string;
  /** Coarse cloth: a visible weave and an open neck, no white under-robe. */
  coarse: boolean;
}

export function clothOf(L: Look): Cloth {
  const { P, fam, t } = L;
  const acc = t.accent && t.accent !== "black" && t.accent !== "white" ? ACCENTS[t.accent] : null;
  const edge = acc ? acc[0] : P.edge;
  const edgeDark = acc ? acc[1] : mix(P.edge, "#5a4a30", 0.35);
  if (t.dress === "bandit")
    return {
      robe: DRAB,
      dark: DRAB_DARK,
      light: DRAB_LIGHT,
      edge: DRAB_DARK,
      edgeDark: "#3a2f1f",
      coarse: true,
    };
  if (fam === "martialArtist" || (t.dress === "rebel" && fam !== "strategist"))
    return {
      robe: HEMP,
      dark: HEMP_DARK,
      light: HEMP_LIGHT,
      edge: acc ? edge : HEMP_DARK,
      edgeDark: acc ? edgeDark : "#6e5f41",
      coarse: true,
    };
  if (t.dress === "hemp")
    return {
      robe: HEMP,
      dark: HEMP_DARK,
      light: HEMP_LIGHT,
      edge: P.robe,
      edgeDark: P.robeDark,
      coarse: false,
    };
  if (t.dress === "imperial")
    return {
      robe: IMPERIAL,
      dark: IMPERIAL_DARK,
      light: IMPERIAL_LIGHT,
      edge: HAN_RED,
      edgeDark: HAN_RED_DARK,
      coarse: false,
    };
  if (t.dress === "servant") {
    const robe = mix(P.robe, HEMP, 0.4);
    return {
      robe,
      dark: mix(P.robeDark, HEMP_DARK, 0.4),
      light: mix(robe, "#ffffff", 0.2),
      edge: HEMP,
      edgeDark: HEMP_DARK,
      coarse: false,
    };
  }
  if (t.dress === "steppe")
    // Felt and woven wool (氀毼) in the side's dye, edged in embroidered leather (刺韋).
    return {
      robe: P.robe,
      dark: P.robeDark,
      light: mix(P.robe, "#ffffff", 0.18),
      edge: LEATHER,
      edgeDark: LEATHER_DARK,
      coarse: true,
    };
  const dust = has(L, "dust") ? 0.3 : 0;
  const robe = mix(P.robe, "#9a9284", dust);
  return {
    robe,
    dark: mix(P.robeDark, "#5a564c", dust),
    light: mix(robe, "#ffffff", 0.22),
    edge,
    edgeDark,
    coarse: false,
  };
}

export function torsoPath(L: Look, bx: Bx): Path2D {
  const stout = L.t.build === "stout" || L.t.build === "huge" ? 1 : 0;
  return path((p) => {
    p.moveTo(bx(74), 222);
    p.bezierCurveTo(bx(56), 240 - stout * 6, bx(26), 246 - stout * 8, bx(6), 266 - stout * 6);
    p.quadraticCurveTo(bx(-14), 290, bx(-18), 340);
    p.lineTo(bx(276), 340);
    p.quadraticCurveTo(bx(276), 288, bx(250), 266 - stout * 6);
    p.bezierCurveTo(bx(230), 250 - stout * 8, bx(196), 242 - stout * 6, bx(166), 230);
    p.closePath();
  });
}

/** The robe, its shadow side, and the back of the collar behind the neck. */
export function body(g: G, L: Look): void {
  const bx = bxOf(L);
  const C = clothOf(L);
  const torso = torsoPath(L, bx);
  cel(g, torso, C.robe, C.dark, 10, C.light, LW);
  inside(g, torso, () => {
    // A broad shadow on the side away from the light (always the viewer's left).
    g.fillStyle = C.dark;
    g.beginPath();
    const edge = Math.min(bx(58), bx(198));
    g.moveTo(-40, 240);
    g.lineTo(edge, 236);
    g.bezierCurveTo(edge - 18, 270, edge - 6, 300, edge - 18, 340);
    g.lineTo(-40, 340);
    g.closePath();
    g.fill();
    if (C.coarse)
      for (let x = -20; x < 280; x += 6) line(g, alpha(C.dark, 0.3), 1, x, 230, x - 20, 340);
    if (has(L, "brocade")) brocade(g, C.robe);
  });
  cel(
    g,
    path((p) => {
      p.moveTo(bx(62), 250);
      p.quadraticCurveTo(bx(64), 226, bx(80), 214);
      p.lineTo(bx(146), 220);
      p.quadraticCurveTo(bx(158), 228, bx(162), 240);
      p.lineTo(bx(150), 246);
      p.lineTo(bx(84), 246);
      p.closePath();
    }),
    C.edge,
    C.edgeDark,
    5,
  );
}

/** Figured silk (綺): a lozenge lattice woven a shade lighter, with a dot in each lozenge. */
function brocade(g: G, robe: string): void {
  const thread = alpha(mix(robe, GOLD_LIGHT, 0.45), 0.55);
  const lattice = new Path2D();
  for (let k = -360; k < 400; k += 22) {
    lattice.moveTo(k, 220);
    lattice.lineTo(k + 130, 350);
    lattice.moveTo(k + 130, 220);
    lattice.lineTo(k, 350);
  }
  g.strokeStyle = thread;
  g.lineWidth = 1.4;
  g.stroke(lattice);
  g.fillStyle = thread;
  for (let j = 20; j <= 31; j++)
    for (let i = -2 + ((j + 1) % 2); i <= 26; i += 2) {
      g.beginPath();
      g.arc(2 + 11 * i, 10 + 11 * j, 1.8, 0, Math.PI * 2);
      g.fill();
    }
}

/**
 * The collar's lapels. Drawn for a man turned to the viewer's right, the outer
 * lapel runs from his left shoulder (far) down across to his right side.
 * Mirrored for a man turned left, it would close the wrong way (左衽), so the
 * mirror lapels are drawn crossed the other way first.
 */
function lapels(g: G, L: Look, bx: Bx, C: Cloth, open: number): Path2D {
  const mirror = L.flip;
  const m = (x: number) => (mirror ? 246 - x : x);
  const X = (x: number) => bx(m(x));
  const innerLapel = path((p) => {
    p.moveTo(X(70), 240);
    p.quadraticCurveTo(X(84), 248, X(92), 252);
    p.quadraticCurveTo(X(108), 280, X(124 + open), 304);
    p.lineTo(X(106 + open), 318);
    p.quadraticCurveTo(X(84), 284, X(60), 254);
    p.closePath();
  });
  cel(g, innerLapel, C.edge, C.edgeDark, 4);
  const outerLapel = path((p) => {
    p.moveTo(X(150), 236);
    p.quadraticCurveTo(X(166), 238, X(180), 244);
    p.quadraticCurveTo(X(150), 300, X(96 + open), 344);
    p.lineTo(X(66 + open), 344);
    p.quadraticCurveTo(X(124), 292, X(150), 236);
    p.closePath();
  });
  cel(g, outerLapel, C.edge, C.edgeDark, 5, mix(C.edge, "#ffffff", 0.4));
  return outerLapel;
}

/** The back of the robe, for a man turned away: the collar round the nape, a seam down the back. */
function backOfRobe(g: G, bx: Bx, C: Cloth): void {
  const collar = path((p) => {
    p.moveTo(bx(56), 252);
    p.quadraticCurveTo(bx(112), 226, bx(170), 246);
    p.lineTo(bx(168), 262);
    p.quadraticCurveTo(bx(112), 244, bx(58), 268);
    p.closePath();
  });
  cel(g, collar, C.edge, C.edgeDark, 4, mix(C.edge, "#ffffff", 0.4));
  line(g, alpha(C.dark, 0.9), 2.4, bx(114), 252, bx(118), 340);
  // A sash knotted at the back.
  const sashP = path((p) => {
    p.moveTo(bx(-20), 312);
    p.quadraticCurveTo(bx(128), 326, bx(280), 308);
    p.lineTo(bx(280), 336);
    p.lineTo(bx(-20), 340);
    p.closePath();
  });
  cel(g, sashP, C.dark, mix(C.dark, "#000000", 0.3), 5);
}

/** Front of the collar and everything worn over the chest; drawn after the neck. */
export function chest(g: G, L: Look): void {
  const { fam, t } = L;
  const bx = bxOf(L);
  const C = clothOf(L);
  if (L.away) {
    backOfRobe(g, bx, C);
    return;
  }
  const open = C.coarse ? 14 : 0;
  const vee = path((p) => {
    p.moveTo(bx(90), 248);
    p.quadraticCurveTo(bx(118), 262, bx(152), 240);
    p.lineTo(bx(156), 250);
    p.quadraticCurveTo(bx(140), 290, bx(122 + open), 310);
    p.quadraticCurveTo(bx(104), 280, bx(90), 248);
    p.closePath();
  });
  if (!C.coarse) {
    cel(g, vee, WHITE_CLOTH, WHITE_SHADE, 5);
    inside(g, vee, () =>
      curve(g, alpha(WHITE_SHADE, 0.9), 2, bx(96), 252, bx(118), 272, bx(150), 246),
    );
  } else {
    const [sk, skd] = L.skin;
    cel(g, vee, sk, skd, 6);
    inside(g, vee, () => curve(g, alpha(skd, 0.9), 2, bx(100), 266, bx(118), 270, bx(136), 262));
  }
  const outer = lapels(g, L, bx, C, open);
  if (t.dress === "steppe") embroidery(g, outer);
  if (L.tier2 || has(L, "gilt"))
    inside(g, outer, () => {
      const m = (x: number) => (L.flip ? 246 - x : x);
      curve(g, GOLD, 3, bx(m(176)), 246, bx(m(146)), 300, bx(m(92 + open)), 344);
    });
  if (has(L, "bare")) bareShoulder(g, L, bx);
  const unarmoured =
    has(L, "civil") ||
    t.dress === "servant" ||
    t.dress === "steppe" ||
    t.dress === "hemp" ||
    t.dress === "imperial";
  const waist = () =>
    cel(
      g,
      path((p) => {
        p.moveTo(bx(-20), 318);
        p.quadraticCurveTo(bx(128), 330, bx(280), 312);
        p.lineTo(bx(280), 340);
        p.lineTo(bx(-20), 340);
        p.closePath();
      }),
      t.dress === "hemp" ? L.P.robe : C.dark,
      mix(t.dress === "hemp" ? L.P.robeDark : C.dark, "#000000", 0.3),
      5,
    );
  switch (fam) {
    case "infantry":
    case "cavalry":
      if (unarmoured) {
        if (t.dress === "steppe") steppeBelt(g, L, bx);
        else waist();
      } else {
        armour(g, L, bx, true);
        if (has(L, "heavy")) gorget(g, L, bx);
      }
      break;
    case "catapult":
      if (!unarmoured) armour(g, L, bx, false);
      rope(g, bx);
      break;
    case "archer":
      if (t.dress === "steppe") steppeBelt(g, L, bx);
      else vest(g, L, bx);
      break;
    case "martialArtist":
      sash(g, L, bx);
      if (L.tier2 && !unarmoured) armour(g, L, bx, false);
      break;
    case "strategist":
      waist();
      break;
  }
  // Collars of fleece and fur.
  if (t.dress === "frontier") fleece(g, bx, false);
  else if (t.dress === "bing" || t.dress === "steppe" || has(L, "fur")) fleece(g, bx, true);
}

/** A bare shoulder and arm on the man's right side (許褚 stripped to fight, the novel). */
function bareShoulder(g: G, L: Look, bx: Bx): void {
  const [sk, skd, skl] = L.skin;
  const near = path((p) => {
    p.moveTo(bx(74), 224);
    p.bezierCurveTo(bx(56), 236, bx(26), 242, bx(4), 262);
    p.quadraticCurveTo(bx(-14), 290, bx(-18), 340);
    p.lineTo(bx(50), 340);
    p.quadraticCurveTo(bx(70), 290, bx(92), 252);
    p.closePath();
  });
  cel(g, near, sk, skd, 8, skl, LW);
  inside(g, near, () => {
    curve(g, alpha(skd, 1), 2.4, bx(10), 286, bx(30), 270, bx(56), 274);
    curve(g, alpha(skd, 0.9), 2, bx(60), 300, bx(70), 318, bx(64), 340);
  });
}

/** Leather lapels with stitched patterns, after the record that the 烏桓 embroidered leather (刺韋作文繡). */
function embroidery(g: G, lapel: Path2D): void {
  inside(g, lapel, () => {
    for (let y = 230; y < 350; y += 9) {
      line(g, alpha("#e8c47a", 0.85), 1.6, -40, y, 300, y - 60);
      for (let x = -40; x < 300; x += 14)
        dot(g, alpha("#5fb0b4", 0.8), x, y - (x + 40) * 0.18 + 4, 1.6);
    }
  });
}

/** A belt of leather hung with bronze plaques (gold for a chief), and a bow-case strap. */
function steppeBelt(g: G, L: Look, bx: Bx): void {
  const strap = poly(bx(214), 250, bx(232), 258, bx(40), 344, bx(16), 340);
  cel(g, strap, LEATHER_DARK, "#2f1d11", 4, LEATHER);
  const belt = path((p) => {
    p.moveTo(bx(-20), 302);
    p.quadraticCurveTo(bx(128), 314, bx(280), 296);
    p.lineTo(bx(280), 314);
    p.quadraticCurveTo(bx(128), 332, bx(-20), 320);
    p.closePath();
  });
  cel(g, belt, LEATHER, LEATHER_DARK, 4, LEATHER_LIGHT);
  const gold = has(L, "gilt") || L.tier2;
  for (let i = 0; i < 6; i++) {
    const u = (i + 0.5) / 6;
    const [x, y] = onCurve([bx(-20), 311], [bx(128), 323], [bx(280), 305], u);
    cel(
      g,
      path((p) => p.roundRect(x - 7, y - 6, 14, 12, 2)),
      gold ? GOLD : BRONZE,
      gold ? GOLD_DARK : BRONZE_DARK,
      2,
      gold ? GOLD_LIGHT : BRONZE_LIGHT,
      1.6,
    );
  }
}

/** A collar of sheepskin (the Liang frontier) or, with `fur`, dark shaggy fur (Bing, the north). */
function fleece(g: G, bx: Bx, fur: boolean): void {
  const segs: [Pt, Pt, Pt][] = [
    [
      [bx(34), 266],
      [bx(80), 276],
      [bx(102), 298],
    ],
    [
      [bx(102), 298],
      [bx(124), 312],
      [bx(150), 296],
    ],
    [
      [bx(150), 296],
      [bx(186), 268],
      [bx(218), 259],
    ],
  ];
  const pts: Pt[] = [];
  for (const [a, c, b] of segs)
    for (let i = 0; i < 8; i++) {
      const [x, y] = onCurve(a, c, b, i / 8);
      pts.push([x, y]);
    }
  pts.push([bx(218), 259]);
  if (fur) {
    furRoll(g, pts, 11, FUR);
    return;
  }
  const tufts = (r: number, dx: number, dy: number) =>
    path((p) => {
      for (const [i, [x, y]] of pts.entries()) {
        const rr = r + (i % 3 === 1 ? 1.5 : 0);
        p.moveTo(x + dx + rr, y + dy);
        p.arc(x + dx, y + dy, rr, 0, Math.PI * 2);
      }
    });
  const wool = tufts(10, 0, 0);
  g.strokeStyle = INK;
  g.lineWidth = 5;
  g.stroke(wool);
  g.fillStyle = FLEECE_DARK;
  g.fill(wool);
  g.fillStyle = FLEECE;
  g.fill(tufts(7.5, 1.5, -2.5));
  for (const [i, [x, y]] of pts.entries()) {
    if (i % 2) continue;
    curve(g, FLEECE_LIGHT, 2, x - 3, y - 2, x + 1, y - 7, x + 5, y - 2);
    curve(g, alpha(FLEECE_DARK, 0.9), 1.6, x - 4, y + 4, x - 1, y + 8, x + 3, y + 5);
  }
}

/** A lamellar neck guard (頸鎧) up to the chin: the elite's heavy armour. */
function gorget(g: G, L: Look, bx: Bx): void {
  const p = path((pp) => {
    pp.moveTo(bx(38), 270);
    pp.quadraticCurveTo(bx(46), 238, bx(68), 226);
    pp.quadraticCurveTo(bx(112), 218, bx(160), 220);
    pp.quadraticCurveTo(bx(198), 234, bx(214), 262);
    pp.quadraticCurveTo(bx(186), 272, bx(150), 298);
    pp.quadraticCurveTo(bx(124), 314, bx(102), 300);
    pp.quadraticCurveTo(bx(80), 278, bx(38), 270);
    pp.closePath();
  });
  lamellae(g, p, L.P.lace, {
    x0: bx(30),
    x1: bx(220),
    y0: 216,
    y1: 312,
    row: 10,
    plate: 7,
    sag: 8,
    base: BLACK_IRON,
    dark: BLACK_IRON_DARK,
    light: BLACK_IRON_LIGHT,
  });
  g.save();
  g.beginPath();
  g.moveTo(bx(46), 240);
  g.quadraticCurveTo(bx(56), 230, bx(68), 226);
  g.quadraticCurveTo(bx(112), 218, bx(160), 220);
  g.quadraticCurveTo(bx(180), 226, bx(194), 238);
  g.strokeStyle = INK;
  g.lineWidth = 7;
  g.stroke();
  g.strokeStyle = has(L, "gilt") ? GOLD : LEATHER;
  g.lineWidth = 4;
  g.stroke();
  g.restore();
}

/**
 * Iron lamellar cuirass and shoulder flaps (披膊), laced in the side's colour.
 * Rebels and bandits wear patched leather, levies plain leather, the south
 * black lacquer, the elite black iron, a few bright steel.
 */
function armour(g: G, L: Look, bx: Bx, cuirass: boolean): void {
  const { P, t } = L;
  const steel = has(L, "silver");
  const rough = t.dress === "rebel" || t.dress === "bandit";
  const leather = rough || t.dress === "levy";
  const lacquer = t.dress === "south";
  const black = has(L, "heavy");
  const heavy = (L.tier2 && L.fam === "infantry") || t.dress === "frontier" || black;
  const [base, dark, light] = leather
    ? [LEATHER, LEATHER_DARK, LEATHER_LIGHT]
    : lacquer
      ? [LACQUER, LACQUER_DARK, LACQUER_LIGHT]
      : black
        ? [BLACK_IRON, BLACK_IRON_DARK, BLACK_IRON_LIGHT]
        : steel
          ? [STEEL, STEEL_DARK, STEEL_LIGHT]
          : [IRON, IRON_DARK, IRON_LIGHT];
  const rowLine = lacquer ? LACQUER_RED : undefined;
  const lo = (a: number, b: number) => Math.min(bx(a), bx(b));
  const hi = (a: number, b: number) => Math.max(bx(a), bx(b));
  if (cuirass) {
    const plate = path((p) => {
      p.moveTo(bx(40), 268);
      p.quadraticCurveTo(bx(80), 278, bx(102), 300);
      p.quadraticCurveTo(bx(124), 314, bx(150), 298);
      p.quadraticCurveTo(bx(186), 270, bx(212), 262);
      p.lineTo(bx(250), 300);
      p.lineTo(bx(250), 340);
      p.lineTo(bx(10), 340);
      p.closePath();
    });
    lamellae(g, plate, P.lace, {
      x0: lo(0, 260),
      x1: hi(0, 260),
      y0: 262,
      y1: 342,
      row: 11,
      plate: 8,
      sag: 6,
      base,
      dark,
      light,
      rowLine,
    });
    if (rough) patches(g, plate, L.seed, bx);
    g.save();
    g.beginPath();
    g.moveTo(bx(40), 268);
    g.quadraticCurveTo(bx(80), 278, bx(102), 300);
    g.quadraticCurveTo(bx(124), 314, bx(150), 298);
    g.quadraticCurveTo(bx(186), 270, bx(212), 262);
    g.strokeStyle = INK;
    g.lineWidth = 8;
    g.stroke();
    g.strokeStyle = L.tier2 || has(L, "gilt") ? GOLD : lacquer ? LACQUER_RED : LEATHER;
    g.lineWidth = 5;
    g.stroke();
    g.restore();
  }
  // Shoulder flaps: the near side larger in three-quarter view, both alike from the front.
  const rows = heavy ? 4 : leather ? 2 : 3;
  const front = L.bodyTurn < 0.5;
  for (let i = rows - 1; i >= 0; i--) {
    const near = path((p) => {
      const y = 252 + i * 15;
      p.moveTo(bx(70 - i * 4), y - 6);
      p.quadraticCurveTo(bx(20 - i * 6), y - 16, bx(-16 - i * 6), y + 12);
      p.lineTo(bx(-12 - i * 6), y + 30);
      p.quadraticCurveTo(bx(28 - i * 6), y + 14, bx(76 - i * 4), y + 16);
      p.closePath();
    });
    lamellae(g, near, P.lace, {
      x0: lo(-40, 90),
      x1: hi(-40, 90),
      y0: 236 + i * 15,
      y1: 300 + i * 15,
      row: 9,
      plate: 7,
      sag: -10,
      base,
      dark,
      light,
      rowLine,
    });
    const far = front
      ? path((p) => {
          const y = 252 + i * 15;
          p.moveTo(bx(186 + i * 4), y - 6);
          p.quadraticCurveTo(bx(236 + i * 6), y - 16, bx(272 + i * 6), y + 12);
          p.lineTo(bx(268 + i * 6), y + 30);
          p.quadraticCurveTo(bx(228 + i * 6), y + 14, bx(180 + i * 4), y + 16);
          p.closePath();
        })
      : path((p) => {
          const y = 250 + i * 14;
          p.moveTo(bx(196 + i * 3), y - 4);
          p.quadraticCurveTo(bx(236 + i * 5), y - 10, bx(270 + i * 4), y + 16);
          p.lineTo(bx(270 + i * 4), y + 32);
          p.quadraticCurveTo(bx(234 + i * 4), y + 12, bx(194 + i * 3), y + 16);
          p.closePath();
        });
    lamellae(g, far, P.lace, {
      x0: lo(170, 300),
      x1: hi(170, 300),
      y0: 236 + i * 14,
      y1: 300 + i * 14,
      row: 9,
      plate: 6,
      sag: -8,
      base,
      dark,
      light,
      rowLine,
    });
  }
}

/** Patches of other hides sewn over worn leather, with coarse stitches. */
function patches(g: G, clip: Path2D, seed: number, bx: Bx): void {
  const r = rng(seed ^ 0x7a7c15);
  inside(g, clip, () => {
    for (let i = 0; i < 3; i++) {
      const x = Math.min(bx(30 + i * 70 + r() * 30), bx(30 + i * 70 + r() * 30 + 30));
      const y = 300 + r() * 24;
      const w = 22 + r() * 14;
      const h = 16 + r() * 10;
      const tilt = (r() - 0.5) * 6;
      const p = poly(x, y + tilt, x + w, y - tilt, x + w + 2, y + h - tilt, x - 1, y + h + tilt);
      cel(g, p, i === 1 ? HEMP_DARK : "#8a6040", LEATHER_DARK, 3, undefined, 1.6);
      g.setLineDash([3, 3]);
      g.strokeStyle = alpha("#e8dcc0", 0.8);
      g.lineWidth = 1.2;
      g.save();
      g.translate(x + w / 2, y + h / 2);
      g.scale(0.78, 0.7);
      g.translate(-(x + w / 2), -(y + h / 2));
      g.stroke(p);
      g.restore();
      g.setLineDash([]);
    }
  });
}

/** Archers: a light leather vest and the quiver's strap across the chest. */
function vest(g: G, L: Look, bx: Bx): void {
  const v = path((p) => {
    p.moveTo(bx(30), 276);
    p.quadraticCurveTo(bx(80), 284, bx(102), 306);
    p.quadraticCurveTo(bx(124), 318, bx(150), 302);
    p.quadraticCurveTo(bx(190), 276, bx(226), 272);
    p.lineTo(bx(240), 340);
    p.lineTo(bx(20), 340);
    p.closePath();
  });
  lamellae(g, v, alpha(L.P.lace, 0.9), {
    x0: Math.min(bx(0), bx(260)),
    x1: Math.max(bx(0), bx(260)),
    y0: 270,
    y1: 342,
    row: 14,
    plate: 12,
    sag: 6,
    base: LEATHER,
    dark: LEATHER_DARK,
    light: LEATHER_LIGHT,
  });
  const strap = poly(bx(214), 250, bx(232), 258, bx(40), 344, bx(16), 340);
  cel(g, strap, LEATHER_DARK, "#2f1d11", 4, LEATHER);
  cel(g, ellipse(bx(150), 292, 9, 7, -0.4), BRONZE, BRONZE_DARK, 3, BRONZE_LIGHT);
}

/** Martial artists: a sash tied across the chest, in the side's colour or the man's own (許褚's tiger stripes). */
function sash(g: G, L: Look, bx: Bx): void {
  const s = poly(bx(196), 246, bx(222), 254, bx(40), 344, bx(10), 336);
  if (L.t.accent === "tiger") {
    cel(g, s, TIGER, TIGER_DARK, 5, TIGER_LIGHT);
    inside(g, s, () => {
      for (let i = 0; i < 12; i++) {
        const u = i / 12;
        const x = bx(205) + (bx(25) - bx(205)) * u;
        const y = 250 + 90 * u;
        line(g, "#2a1a12", 3, x - 6, y - 8, x + 4, y + 6);
      }
    });
    return;
  }
  const acc = L.t.accent ? ACCENTS[L.t.accent] : null;
  const c = acc ? acc[0] : L.P.tassel;
  cel(g, s, c, acc ? acc[1] : L.P.robeDark, 5, mix(c, "#ffffff", 0.35));
}

/** Stone-thrower crews: a coil of hauling rope over the near shoulder. */
function rope(g: G, bx: Bx): void {
  for (let i = 0; i < 3; i++) {
    const p = ellipse(bx(34 + i * 4), 296 + i * 3, 30, 14, -0.5);
    g.lineWidth = 9;
    g.strokeStyle = INK;
    g.stroke(p);
    g.lineWidth = 5;
    g.strokeStyle = i === 1 ? "#b29a6c" : "#cbb98f";
    g.stroke(p);
  }
}
