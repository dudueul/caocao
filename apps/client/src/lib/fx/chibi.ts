/**
 * Our own characters (ADR 0045): rigged 2D chibi figures at tile scale, seen
 * from the battle map's bird's-eye three-quarter view. Each figure is a few
 * painted parts (legs, torso, head, arms, weapon, shield or bow, horse or
 * stone-thrower) on a small skeleton, posed procedurally: ready, walk, attack,
 * guard, hurt, slumped and cheering, in three facings (front, back, side; the
 * side is mirrored for left).
 *
 * Dress and gear follow the late Eastern Han, around 200 AD (ADR 0046,
 * docs/remake/CHARACTER_LOOK.md): topknots under cloth caps or lamellar
 * helmets, cross-collared robes over trousers and leg wraps, iron lamellar
 * armour, ring-pommel sabres with tall lacquered shields, the ji halberd,
 * recurve bows, horses without stirrups, and Cao Cao's traction stone-thrower.
 * The parts are painted in code as interim art; an artist's drawings replace
 * them without changing the skeleton.
 */
import { Container, Sprite, Texture } from "pixi.js";

export type Facing = "down" | "up" | "side";
export type RigPose = "ready" | "walk" | "stand" | "weak" | "attack" | "guard" | "hurt" | "cheer";
export type Kind = "infantry" | "archer" | "cavalry" | "strategist" | "martialArtist" | "catapult";

/** Texture pixels per tile. */
const U = 128;
const INK = "#2a1a12";
const LW = 0.02;
const SHOE = "#2f2622";
const TROUSERS = "#b9aa86";
const TROUSERS_DARK = "#8e805f";
const WRAP = "#7d6e50";
const HEMP = "#b8a37a";
const HEMP_DARK = "#8f7c56";
const WOOD = "#7a5234";
const WOOD_DARK = "#54371f";
const IRON = "#767b84";
const IRON_DARK = "#4c5058";
const BLADE = "#d9e0e7";
const BLADE_DARK = "#8f9aa6";
const BRONZE = "#c0913e";
const BRONZE_DARK = "#7f5c22";
const LEATHER = "#6b4529";
const LEATHER_DARK = "#4a2e1b";
const LACQUER = "#2b2421";
const LACQUER_RED = "#a33328";
const SILK_CAP = "#26304a";
const FEATHER = "#8a6a44";
const COAT = "#8a5a34";
const COAT_DARK = "#64401f";
const MANE = "#2a1d14";

/**
 * Side colours, as Han dyes: madder red for our side, indigo for the enemy,
 * then green and ochre (ADR 0055). They dye the
 * robe, the armour lacing, the helmet tassel and the saddle flaps, so the
 * sides read apart on the map (a game convention; Han armies told units apart
 * by their banners).
 */
interface Pal {
  robe: string;
  robeDark: string;
  edge: string;
  lace: string;
  tassel: string;
}

const SIDES: Pal[] = [
  { robe: "#9b2f28", robeDark: "#6b1d18", edge: "#dccaa0", lace: "#d65a4c", tassel: "#d8392c" },
  { robe: "#2e4a78", robeDark: "#1f3354", edge: "#dccaa0", lace: "#7096d4", tassel: "#3f74d6" },
  { robe: "#3f6b46", robeDark: "#2a4a30", edge: "#dccaa0", lace: "#72b07b", tassel: "#43a85a" },
  { robe: "#a2772c", robeDark: "#6e501b", edge: "#efe3c0", lace: "#d8aa4c", tassel: "#e2b23c" },
];
const SKIN = [
  ["#f3cfab", "#dca67e"],
  ["#e9c09a", "#cb9269"],
] as const;
const HAIR = "#1e1816";

/** What a unit looks like: its class, side colour and a few traits from its id. */
export interface Look {
  kind: Kind;
  side: number;
  /** 0 clean-shaven, 1 moustache and goatee, 2 full beard. */
  beard: number;
  skin: number;
  /** Tier-2 classes (ADR 0048): gilded edges and lacing. */
  elite: boolean;
}

/** Promoted classes draw as their family's figure, with gilded trim. */
const PROMOTED: Readonly<Record<string, Kind>> = {
  heavyInfantry: "infantry",
  longbow: "archer",
  shockCavalry: "cavalry",
  tactician: "strategist",
  guardian: "martialArtist",
  thunderCart: "catapult",
};

const KINDS: readonly Kind[] = [
  "infantry",
  "archer",
  "cavalry",
  "strategist",
  "martialArtist",
  "catapult",
];

export function lookFor(classId: string, unitId: string, side: number): Look {
  let h = 7;
  for (const ch of unitId) h = (h * 31 + ch.charCodeAt(0)) >>> 0;
  const promoted = PROMOTED[classId];
  const kind =
    promoted ?? ((KINDS as readonly string[]).includes(classId) ? (classId as Kind) : "infantry");
  // Grown men of the period mostly wore beards; a few are shown clean-shaven.
  return {
    kind,
    side,
    beard: [1, 2, 1, 0, 2][h % 5] ?? 1,
    skin: (h >>> 5) % 2,
    elite: promoted !== undefined,
  };
}

// ---------------------------------------------------------------- painting

interface Part {
  tex: Texture;
  ax: number;
  ay: number;
}

type Path = (g: CanvasRenderingContext2D) => void;

/** A part painted in tile units: `w`×`h` with its pivot at (`px`, `py`) from the top left. */
function paint(
  w: number,
  h: number,
  px: number,
  py: number,
  draw: (g: CanvasRenderingContext2D) => void,
): Part {
  const c = document.createElement("canvas");
  c.width = Math.ceil(w * U);
  c.height = Math.ceil(h * U);
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  g.translate(px * U, py * U);
  g.scale(U, U);
  g.lineJoin = "round";
  g.lineCap = "round";
  draw(g);
  return { tex: Texture.from(c), ax: px / w, ay: py / h };
}

/** Flat fill, a shadow band left of `shadeX` (light from the upper right), an ink outline. */
function cel(g: CanvasRenderingContext2D, path: Path, fill: string, shade?: string, shadeX = 0) {
  g.save();
  path(g);
  g.fillStyle = fill;
  g.fill();
  if (shade) {
    g.clip();
    g.fillStyle = shade;
    g.fillRect(-2, -2, 2 + shadeX, 4);
  }
  g.restore();
  g.save();
  path(g);
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke();
  g.restore();
}

/** Strokes inside a clipped shape (seams, plate rows, patterns). */
function inside(g: CanvasRenderingContext2D, path: Path, draw: () => void) {
  g.save();
  path(g);
  g.clip();
  draw();
  g.restore();
}

function line(g: CanvasRenderingContext2D, color: string, width: number, ...pts: number[]) {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i < pts.length; i += 2) g.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
  g.stroke();
}

const rr =
  (x: number, y: number, w: number, h: number, r: number): Path =>
  (g) => {
    g.beginPath();
    g.roundRect(x, y, w, h, r);
  };
const ell =
  (x: number, y: number, rx: number, ry: number, rot = 0): Path =>
  (g) => {
    g.beginPath();
    g.ellipse(x, y, rx, ry, rot, 0, Math.PI * 2);
  };
const poly =
  (...pts: number[]): Path =>
  (g) => {
    g.beginPath();
    g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
    for (let i = 2; i < pts.length; i += 2) g.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
    g.closePath();
  };

/** Iron lamellae: rows of small plates, staggered, laced with the side's cord. */
function lamellae(g: CanvasRenderingContext2D, path: Path, P: Pal, shadeX: number) {
  cel(g, path, IRON, IRON_DARK, shadeX);
  inside(g, path, () => {
    for (let row = 0, y = -0.6; y < 0.6; row++, y += 0.03) {
      line(g, IRON_DARK, 0.007, -0.6, y, 0.6, y);
      for (let x = -0.6 + (row % 2) * 0.013; x < 0.6; x += 0.026)
        line(g, "rgba(30,32,36,0.55)", 0.005, x, y, x, y + 0.03);
      if (row % 2 === 0)
        for (let x = -0.6; x < 0.6; x += 0.052) {
          g.fillStyle = P.lace;
          g.fillRect(x, y - 0.004, 0.012, 0.008);
        }
    }
  });
  g.save();
  path(g);
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke();
  g.restore();
}

// ---------------------------------------------------------------- parts

/** Trousers, cloth leg wraps below the knee, black cloth shoes. */
function leg(near: boolean, facing: Facing): Part {
  return paint(0.16, 0.24, 0.08, 0.02, (g) => {
    cel(
      g,
      rr(-0.045, -0.01, 0.09, 0.13, 0.035),
      near ? TROUSERS : TROUSERS_DARK,
      near ? TROUSERS_DARK : undefined,
      -0.012,
    );
    const wrap = rr(-0.042, 0.055, 0.084, 0.065, 0.015);
    cel(g, wrap, WRAP);
    inside(g, wrap, () => {
      for (const y of [0.07, 0.09, 0.11])
        line(g, "rgba(0,0,0,0.3)", 0.007, -0.05, y, 0.05, y + 0.012);
    });
    if (facing === "side") cel(g, rr(-0.045, 0.105, 0.105, 0.055, 0.025), SHOE);
    else cel(g, rr(-0.048, 0.105, 0.096, 0.06, 0.025), SHOE);
  });
}

function body(P: Pal, kind: Kind, facing: Facing): Part {
  return paint(0.5, 0.58, 0.25, 0.36, (g) => {
    const side = facing === "side";
    const back = facing === "up";
    const hw = side ? 0.105 : 0.14;
    const long = kind === "strategist";
    const hem = long ? 0.16 : 0.085;
    const flare = long ? 0.045 : 0.03;
    const plain = kind === "martialArtist";
    // The robe: cross-collared, belted, knee-length (ankle-length for officials).
    const robe: Path = (c) => {
      c.beginPath();
      c.moveTo(-hw, -0.27);
      c.quadraticCurveTo(0, -0.3, hw, -0.27);
      c.lineTo(hw + 0.005, -0.02);
      c.lineTo(hw + flare, hem);
      c.quadraticCurveTo(0, hem + 0.03, -hw - flare, hem);
      c.lineTo(-hw - 0.005, -0.02);
      c.closePath();
    };
    cel(g, robe, plain ? HEMP : P.robe, plain ? HEMP_DARK : P.robeDark, side ? -0.03 : -0.06);
    inside(g, robe, () => {
      g.fillStyle = plain ? HEMP_DARK : P.edge;
      g.fillRect(-0.4, hem - 0.016, 0.8, 0.08);
    });
    // Right-over-left collar (交領右衽): the outer lapel runs from the wearer's left
    // shoulder down to the right hip, which is right to left as we face them.
    const edge = plain ? HEMP_DARK : P.edge;
    if (back) cel(g, rr(-0.06, -0.295, 0.12, 0.035, 0.012), edge);
    else if (side) cel(g, poly(0.02, -0.285, 0.05, -0.285, 0.105, -0.12, 0.09, -0.1), edge);
    else {
      cel(g, poly(-0.075, -0.285, -0.045, -0.285, 0.025, -0.185, 0.005, -0.165), edge);
      cel(g, poly(0.065, -0.29, 0.1, -0.282, -0.075, -0.04, -0.105, -0.06), edge);
    }
    // Armour: an iron lamellar cuirass with shoulder flaps.
    if (kind === "infantry" || kind === "cavalry") {
      const vest = side
        ? poly(-0.09, -0.262, 0.1, -0.262, 0.098, -0.03, -0.092, -0.03)
        : poly(-0.12, -0.262, 0.12, -0.262, 0.116, -0.03, -0.116, -0.03);
      lamellae(g, vest, P, side ? -0.03 : -0.05);
      for (const sx of side ? [0.0] : [-0.15, 0.15])
        lamellae(g, ell(sx, -0.255, 0.058, 0.046, side ? 0 : Math.sign(sx) * 0.35), P, sx - 0.02);
    }
    // Belt, with a bronze belt hook at the front.
    cel(
      g,
      rr(-hw - 0.004, -0.045, (hw + 0.004) * 2, 0.042, 0.014),
      LEATHER,
      LEATHER_DARK,
      -hw + 0.02,
    );
    if (!back) cel(g, ell(side ? 0.08 : 0, -0.024, 0.022, 0.014), BRONZE, BRONZE_DARK, -0.01);
    switch (kind) {
      case "archer": {
        // A quiver at the hip, arrows fletched with feathers.
        const qx = side ? -0.1 : back ? 0.12 : -0.13;
        g.save();
        g.translate(qx, -0.02);
        g.rotate(side ? 0.25 : back ? -0.2 : 0.2);
        for (const dx of [-0.016, 0, 0.016])
          cel(g, poly(dx - 0.008, -0.13, dx, -0.17, dx + 0.008, -0.13), "#efe8d8");
        cel(g, rr(-0.032, -0.12, 0.064, 0.19, 0.02), LEATHER, LEATHER_DARK, -0.01);
        cel(g, rr(-0.034, -0.12, 0.068, 0.025, 0.01), P.robeDark);
        g.restore();
        break;
      }
      case "strategist": {
        // The sash's long ends hang at the front.
        if (!back)
          for (const dx of side ? [0.07] : [-0.025, 0.02])
            cel(g, rr(dx - 0.012, -0.02, 0.024, 0.12, 0.008), P.edge, undefined);
        break;
      }
      case "martialArtist": {
        // Hemp work clothes, tied with a sash in the side's colour.
        cel(
          g,
          rr(-hw - 0.004, -0.05, (hw + 0.004) * 2, 0.05, 0.016),
          P.tassel,
          P.robeDark,
          -hw + 0.02,
        );
        if (!side) cel(g, ell(back ? 0 : 0.06, -0.025, 0.03, 0.025), P.tassel, P.robeDark, 0.05);
        break;
      }
      default:
        break;
    }
  });
}

function head(P: Pal, look: Look, facing: Facing): Part {
  const [skin, skinDark] = SKIN[look.skin] ?? SKIN[0];
  const kind = look.kind;
  return paint(0.64, 0.74, 0.32, 0.64, (g) => {
    const side = facing === "side";
    const back = facing === "up";
    const cy = -0.19;
    const top = cy - 0.19;
    // Behind the head: the scholar's cap ribbons.
    if (kind === "strategist")
      for (const k of [0, 1]) {
        const tx = side ? -0.14 : back ? -0.03 + k * 0.06 : -0.12 + k * 0.24;
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(tx - 0.015, cy - 0.1);
            c.quadraticCurveTo(tx - 0.04 - k * 0.02, cy + 0.04, tx - 0.03, cy + 0.16);
            c.lineTo(tx, cy + 0.16);
            c.quadraticCurveTo(tx - 0.01, cy + 0.04, tx + 0.015, cy - 0.1);
            c.closePath();
          },
          SILK_CAP,
        );
      }
    // Neck and face.
    cel(g, rr(-0.035, -0.06, 0.07, 0.08, 0.02), skinDark);
    cel(
      g,
      ell(side ? 0.01 : 0, cy, side ? 0.175 : 0.185, 0.175),
      skin,
      skinDark,
      side ? -0.09 : -0.1,
    );
    if (side) cel(g, ell(-0.035, cy + 0.02, 0.03, 0.045), skin, skinDark, -0.04);
    // Hair combed up to a topknot (髻), with a neat hairline.
    if (back) cel(g, ell(0, cy - 0.015, 0.19, 0.172), HAIR);
    else if (side)
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(0.14, cy - 0.12);
          c.quadraticCurveTo(0.04, cy - 0.21, -0.1, cy - 0.16);
          c.quadraticCurveTo(-0.2, cy - 0.06, -0.15, cy + 0.08);
          c.lineTo(-0.07, cy + 0.03);
          c.quadraticCurveTo(-0.07, cy - 0.06, 0.0, cy - 0.08);
          c.quadraticCurveTo(0.08, cy - 0.11, 0.14, cy - 0.12);
          c.closePath();
        },
        HAIR,
      );
    else
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.188, cy - 0.01);
          c.quadraticCurveTo(-0.19, cy - 0.19, 0, cy - 0.19);
          c.quadraticCurveTo(0.19, cy - 0.19, 0.188, cy - 0.01);
          c.quadraticCurveTo(0.15, cy - 0.085, 0.06, cy - 0.095);
          c.lineTo(0, cy - 0.075);
          c.lineTo(-0.06, cy - 0.095);
          c.quadraticCurveTo(-0.15, cy - 0.085, -0.188, cy - 0.01);
          c.closePath();
        },
        HAIR,
      );
    // Face.
    if (!back) {
      const eyes = side ? [0.1] : [-0.068, 0.068];
      for (const ex of eyes) {
        g.fillStyle = "#1c1214";
        g.beginPath();
        g.ellipse(ex, cy + 0.03, side ? 0.02 : 0.026, 0.036, 0, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = "#4a3428";
        g.beginPath();
        g.ellipse(ex + 0.004, cy + 0.04, side ? 0.013 : 0.017, 0.02, 0, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = "#ffffff";
        g.beginPath();
        g.arc(ex + 0.009, cy + 0.017, 0.009, 0, Math.PI * 2);
        g.fill();
      }
      // Firm brows.
      for (const ex of eyes) line(g, HAIR, 0.02, ex - 0.035, cy - 0.022, ex + 0.03, cy - 0.04);
      g.fillStyle = "rgba(220,120,100,0.35)";
      for (const bx of side ? [0.07] : [-0.11, 0.11]) {
        g.beginPath();
        g.ellipse(bx, cy + 0.09, 0.028, 0.015, 0, 0, Math.PI * 2);
        g.fill();
      }
      if (side) line(g, "#7a3a30", 0.014, 0.12, cy + 0.11, 0.15, cy + 0.105);
      else line(g, "#7a3a30", 0.014, -0.022, cy + 0.112, 0.022, cy + 0.112);
      if (look.beard === 1) {
        // Moustache and a short goatee.
        if (side) {
          line(g, HAIR, 0.016, 0.1, cy + 0.088, 0.16, cy + 0.096);
          cel(g, poly(0.1, cy + 0.13, 0.14, cy + 0.13, 0.115, cy + 0.2), HAIR);
        } else {
          line(g, HAIR, 0.016, -0.05, cy + 0.1, -0.008, cy + 0.09);
          line(g, HAIR, 0.016, 0.008, cy + 0.09, 0.05, cy + 0.1);
          cel(g, poly(-0.025, cy + 0.135, 0.025, cy + 0.135, 0, cy + 0.21), HAIR);
        }
      } else if (look.beard === 2)
        // A full beard falling to the chest.
        cel(
          g,
          (c) => {
            c.beginPath();
            if (side) {
              c.moveTo(0.0, cy + 0.08);
              c.quadraticCurveTo(0.02, cy + 0.26, 0.1, cy + 0.3);
              c.quadraticCurveTo(0.17, cy + 0.2, 0.16, cy + 0.12);
              c.quadraticCurveTo(0.09, cy + 0.14, 0.05, cy + 0.1);
            } else {
              c.moveTo(-0.13, cy + 0.06);
              c.quadraticCurveTo(-0.1, cy + 0.26, 0, cy + 0.31);
              c.quadraticCurveTo(0.1, cy + 0.26, 0.13, cy + 0.06);
              c.quadraticCurveTo(0.07, cy + 0.13, 0.03, cy + 0.12);
              c.lineTo(-0.03, cy + 0.12);
              c.quadraticCurveTo(-0.07, cy + 0.13, -0.13, cy + 0.06);
            }
            c.closePath();
          },
          HAIR,
        );
    }
    // Headwear by class.
    const knotX = side ? -0.04 : 0;
    switch (kind) {
      case "infantry":
      case "cavalry": {
        // An iron lamellar helmet (兜鍪) with cheek and neck flaps and a tassel (纓).
        const flaps: Path = (c) => {
          c.beginPath();
          if (side) {
            c.moveTo(-0.19, cy - 0.04);
            c.lineTo(-0.2, cy + 0.11);
            c.lineTo(-0.06, cy + 0.11);
            c.lineTo(-0.04, cy - 0.04);
          } else if (back) {
            c.moveTo(-0.2, cy - 0.04);
            c.lineTo(-0.21, cy + 0.12);
            c.quadraticCurveTo(0, cy + 0.16, 0.21, cy + 0.12);
            c.lineTo(0.2, cy - 0.04);
          } else {
            c.moveTo(-0.2, cy - 0.05);
            c.lineTo(-0.215, cy + 0.1);
            c.lineTo(-0.14, cy + 0.11);
            c.lineTo(-0.15, cy - 0.04);
            c.moveTo(0.2, cy - 0.05);
            c.lineTo(0.215, cy + 0.1);
            c.lineTo(0.14, cy + 0.11);
            c.lineTo(0.15, cy - 0.04);
          }
          c.closePath();
        };
        lamellae(g, flaps, P, side ? -0.12 : -0.15);
        const dome: Path = (c) => {
          c.beginPath();
          c.moveTo(-0.205, cy - 0.03);
          c.quadraticCurveTo(-0.2, top - 0.05, 0, top - 0.055);
          c.quadraticCurveTo(0.2, top - 0.05, 0.205, cy - 0.03);
          c.quadraticCurveTo(0, cy - 0.07, -0.205, cy - 0.03);
          c.closePath();
        };
        cel(g, dome, IRON, IRON_DARK, side ? -0.08 : -0.1);
        inside(g, dome, () => {
          for (let x = -0.24; x <= 0.24; x += 0.035)
            line(g, "rgba(30,32,36,0.6)", 0.006, x * 0.25, top - 0.05, x, cy);
        });
        cel(g, rr(-0.208, cy - 0.06, 0.416, 0.032, 0.012), IRON_DARK);
        cel(g, rr(-0.022, top - 0.085, 0.044, 0.04, 0.01), BRONZE, BRONZE_DARK, -0.01);
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(0, top - 0.085);
            c.quadraticCurveTo(-0.05, top - 0.13, -0.035, top - 0.17);
            c.quadraticCurveTo(0, top - 0.13, 0.035, top - 0.17);
            c.quadraticCurveTo(0.05, top - 0.13, 0, top - 0.085);
            c.closePath();
          },
          P.tassel,
          P.robeDark,
          -0.01,
        );
        if (kind === "cavalry")
          // Two pheasant tail feathers, upright left and right, after the Han martial cap (鶡冠).
          for (const s of side ? [-1] : [-1, 1]) {
            g.save();
            g.translate(side ? -0.07 : s * 0.1, top - 0.03);
            g.rotate(side ? -0.45 : s * 0.3);
            const quill: Path = (c) => {
              c.beginPath();
              c.moveTo(-0.011, 0);
              c.quadraticCurveTo(-0.022, -0.09, s * 0.035, -0.17);
              c.quadraticCurveTo(0.024, -0.085, 0.011, 0);
              c.closePath();
            };
            cel(g, quill, FEATHER, "#5e452b", -0.004);
            inside(g, quill, () => {
              for (let y = -0.15; y < 0; y += 0.028)
                line(g, "#3a2a1a", 0.007, -0.03, y, 0.03, y - 0.01);
            });
            g.restore();
          }
        break;
      }
      case "archer": {
        // A soft cloth cap over the topknot (幘), with a raised back.
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.195, cy - 0.04);
            c.quadraticCurveTo(-0.19, top - 0.02, side ? -0.04 : 0, top - 0.03);
            c.quadraticCurveTo(side ? 0.12 : 0.19, top - 0.02, side ? 0.16 : 0.195, cy - 0.06);
            c.quadraticCurveTo(0, cy - 0.11, -0.195, cy - 0.04);
            c.closePath();
          },
          P.robeDark,
          "#141a26",
          -0.1,
        );
        cel(g, ell(knotX, top - 0.05, 0.065, 0.055), P.robeDark, "#141a26", knotX - 0.02);
        cel(g, rr(-0.196, cy - 0.075, 0.392, 0.03, 0.012), P.edge, undefined);
        break;
      }
      case "strategist": {
        // A silk headcloth (綸巾) over the topknot.
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.19, cy - 0.05);
            c.quadraticCurveTo(-0.18, top - 0.06, side ? -0.05 : 0, top - 0.075);
            c.quadraticCurveTo(side ? 0.1 : 0.18, top - 0.06, side ? 0.15 : 0.19, cy - 0.07);
            c.quadraticCurveTo(0, cy - 0.11, -0.19, cy - 0.05);
            c.closePath();
          },
          SILK_CAP,
          "#171d2e",
          -0.1,
        );
        inside(
          g,
          (c) => {
            c.beginPath();
            c.rect(-0.3, top - 0.2, 0.6, 0.3);
          },
          () => {
            for (const x of [-0.08, 0, 0.08])
              line(g, "rgba(255,255,255,0.12)", 0.01, x, top - 0.06, x * 1.6, cy - 0.08);
          },
        );
        break;
      }
      case "martialArtist": {
        // A bare topknot, tied with a strip of cloth in the side's colour.
        cel(g, ell(knotX, top - 0.035, 0.06, 0.052), HAIR);
        cel(
          g,
          rr(knotX - 0.064, top - 0.03, 0.128, 0.026, 0.01),
          P.tassel,
          P.robeDark,
          knotX - 0.02,
        );
        break;
      }
      default:
        break;
    }
  });
}

function arm(P: Pal, kind: Kind, near: boolean): Part {
  const [skin, skinDark] = SKIN[0];
  return paint(0.2, 0.26, 0.1, 0.04, (g) => {
    const robe = near ? P.robe : P.robeDark;
    if (kind === "strategist") {
      // Wide sleeves with a contrasting hem.
      cel(
        g,
        poly(-0.04, 0, 0.04, 0, 0.08, 0.135, -0.08, 0.135),
        robe,
        near ? P.robeDark : undefined,
        -0.03,
      );
      cel(g, rr(-0.08, 0.12, 0.16, 0.025, 0.01), P.edge);
    } else if (kind === "martialArtist") {
      cel(
        g,
        rr(-0.035, -0.005, 0.07, 0.07, 0.03),
        near ? HEMP : HEMP_DARK,
        near ? HEMP_DARK : undefined,
        -0.012,
      );
      cel(g, rr(-0.028, 0.055, 0.056, 0.08, 0.025), skin, skinDark, -0.01);
      cel(g, rr(-0.032, 0.1, 0.064, 0.035, 0.012), "#efe8d8", "#c9c2b4", -0.015);
    } else {
      cel(g, rr(-0.035, -0.005, 0.07, 0.135, 0.035), robe, near ? P.robeDark : undefined, -0.012);
      cel(g, rr(-0.037, 0.11, 0.074, 0.022, 0.01), P.edge);
    }
    const r = kind === "martialArtist" ? 0.042 : 0.034;
    cel(g, ell(0, 0.155, r, r), skin, skinDark, -0.012);
  });
}

function weapon(P: Pal, kind: Kind): Part | null {
  switch (kind) {
    case "infantry":
      // A ring-pommel sabre (環首刀): single-edged, straight, a ring at the pommel.
      return paint(0.16, 0.48, 0.08, 0.12, (g) => {
        g.lineWidth = 0.014;
        g.strokeStyle = INK;
        g.beginPath();
        g.arc(0, -0.075, 0.026, 0, Math.PI * 2);
        g.stroke();
        g.strokeStyle = IRON;
        g.lineWidth = 0.009;
        g.stroke();
        cel(g, rr(-0.014, -0.05, 0.028, 0.085, 0.008), LEATHER, LEATHER_DARK, -0.004);
        cel(g, rr(-0.02, 0.03, 0.04, 0.012, 0.004), IRON_DARK);
        cel(
          g,
          poly(-0.013, 0.042, 0.016, 0.042, 0.016, 0.31, -0.013, 0.35),
          BLADE,
          BLADE_DARK,
          -0.002,
        );
      });
    case "cavalry":
      // A ji halberd (戟): a spike with a side blade, a tassel below the head.
      return paint(0.24, 0.96, 0.08, 0.28, (g) => {
        cel(g, rr(-0.012, -0.26, 0.024, 0.78, 0.012), WOOD_DARK, "#3a2414", 0);
        cel(g, rr(-0.016, -0.28, 0.032, 0.04, 0.01), BRONZE, BRONZE_DARK, 0);
        cel(g, poly(-0.03, 0.47, 0.03, 0.47, 0.045, 0.505, -0.045, 0.505), P.tassel, P.robeDark, 0);
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.02, 0.51);
            c.quadraticCurveTo(-0.025, 0.58, 0, 0.66);
            c.quadraticCurveTo(0.025, 0.58, 0.02, 0.51);
            c.closePath();
          },
          BLADE,
          BLADE_DARK,
          0,
        );
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(0.012, 0.53);
            c.lineTo(0.1, 0.52);
            c.quadraticCurveTo(0.13, 0.535, 0.12, 0.56);
            c.lineTo(0.012, 0.565);
            c.closePath();
          },
          BLADE,
          BLADE_DARK,
          0.05,
        );
      });
    case "strategist":
      // A feather fan (羽扇).
      return paint(0.3, 0.32, 0.15, 0.06, (g) => {
        cel(g, rr(-0.01, -0.04, 0.02, 0.1, 0.008), "#c9a861", "#9a7c3e", 0);
        const fan = ell(0, 0.15, 0.085, 0.1);
        cel(g, fan, "#f6f2ea", "#d6d0c4", -0.02);
        inside(g, fan, () => {
          for (let i = -3; i <= 3; i++)
            line(g, "rgba(120,110,100,0.55)", 0.006, 0, 0.06, i * 0.03, 0.26);
        });
        cel(g, rr(-0.02, 0.045, 0.04, 0.02, 0.008), "#3a2a20");
      });
    default:
      return null;
  }
}

function offhand(P: Pal, kind: Kind, facing: Facing): Part | null {
  if (kind === "infantry")
    // A tall lacquered shield with a painted cloud scroll, rimmed in the side's colour.
    return paint(0.26, 0.4, 0.13, 0.2, (g) => {
      if (facing === "side") {
        cel(g, rr(-0.014, -0.16, 0.028, 0.32, 0.01), LACQUER);
        line(g, P.lace, 0.01, 0.0, -0.15, 0.0, 0.15);
        return;
      }
      const face = rr(-0.08, -0.16, 0.16, 0.32, 0.04);
      if (facing === "up") {
        cel(g, face, WOOD_DARK, "#3a2414", -0.03);
        cel(g, rr(-0.015, -0.06, 0.03, 0.12, 0.01), LEATHER);
        return;
      }
      cel(g, face, LACQUER, "#1c1716", -0.04);
      inside(g, face, () => {
        g.strokeStyle = LACQUER_RED;
        g.lineWidth = 0.014;
        for (const [cx, cy, s] of [
          [0, -0.07, 1],
          [0, 0.07, -1],
        ] as const) {
          g.beginPath();
          g.moveTo(cx - 0.05, cy);
          g.bezierCurveTo(cx - 0.05, cy - 0.05 * s, cx + 0.03, cy - 0.05 * s, cx + 0.02, cy);
          g.bezierCurveTo(cx + 0.015, cy + 0.025 * s, cx - 0.02, cy + 0.02 * s, cx - 0.01, cy);
          g.stroke();
        }
        line(g, "rgba(255,255,255,0.12)", 0.01, 0.055, -0.14, 0.055, 0.14);
      });
      g.save();
      face(g);
      g.lineWidth = 0.018;
      g.strokeStyle = P.lace;
      g.stroke();
      g.restore();
    });
  if (kind === "archer")
    // A composite recurve bow: the tips bend forward.
    return paint(0.18, 0.5, 0.05, 0.25, (g) => {
      line(g, "rgba(240,236,220,0.9)", 0.007, 0.03, -0.215, 0.03, 0.215);
      const limb = (c: CanvasRenderingContext2D) => {
        c.beginPath();
        c.moveTo(0.05, -0.23);
        c.quadraticCurveTo(0.0, -0.2, 0.04, -0.15);
        c.quadraticCurveTo(0.1, -0.08, 0.07, 0);
        c.quadraticCurveTo(0.1, 0.08, 0.04, 0.15);
        c.quadraticCurveTo(0.0, 0.2, 0.05, 0.23);
      };
      g.save();
      limb(g);
      g.strokeStyle = INK;
      g.lineWidth = 0.034;
      g.stroke();
      limb(g);
      g.strokeStyle = "#8a5a2c";
      g.lineWidth = 0.02;
      g.stroke();
      g.restore();
      cel(g, rr(0.05, -0.03, 0.035, 0.06, 0.01), LEATHER_DARK);
    });
  return null;
}

/** The horse: trimmed mane, tied forelock and knotted tail, a saddle with flaps; no stirrups. */
function horse(P: Pal, facing: Facing): Part {
  return paint(0.86, 0.68, 0.43, 0.58, (g) => {
    const hooves = (x: number, w: number, dark: boolean) => {
      cel(
        g,
        rr(x, -0.18, w, 0.17, 0.02),
        dark ? COAT_DARK : COAT,
        dark ? undefined : COAT_DARK,
        x + 0.015,
      );
      cel(g, rr(x - 0.003, -0.03, w + 0.006, 0.035, 0.01), MANE);
    };
    const flap = (x: number, y: number, w: number, h: number) => {
      const f = rr(x, y, w, h, 0.03);
      cel(g, f, P.robe, P.robeDark, x + 0.04);
      g.save();
      f(g);
      g.lineWidth = 0.014;
      g.strokeStyle = P.edge;
      g.stroke();
      g.restore();
    };
    if (facing === "side") {
      // Knotted tail.
      cel(g, ell(-0.27, -0.25, 0.04, 0.035), MANE);
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.28, -0.23);
          c.quadraticCurveTo(-0.33, -0.16, -0.3, -0.1);
          c.lineTo(-0.27, -0.12);
          c.quadraticCurveTo(-0.29, -0.17, -0.26, -0.22);
          c.closePath();
        },
        MANE,
      );
      hooves(-0.19, 0.05, true);
      hooves(0.12, 0.05, true);
      cel(g, ell(0, -0.23, 0.27, 0.11), COAT, COAT_DARK, -0.12);
      hooves(-0.15, 0.055, false);
      hooves(0.16, 0.055, false);
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(0.13, -0.29);
          c.quadraticCurveTo(0.2, -0.43, 0.29, -0.46);
          c.lineTo(0.35, -0.37);
          c.quadraticCurveTo(0.27, -0.31, 0.25, -0.18);
          c.closePath();
        },
        COAT,
        COAT_DARK,
        0.2,
      );
      cel(g, ell(0.34, -0.42, 0.095, 0.055, 0.45), COAT, COAT_DARK, 0.28);
      cel(g, poly(0.26, -0.5, 0.29, -0.56, 0.31, -0.48), COAT);
      // Trimmed, upright mane and a tied forelock.
      g.fillStyle = MANE;
      for (let i = 0; i < 7; i++) {
        const k = i / 6;
        const x = 0.13 + k * 0.15;
        const y = -0.3 - k * 0.15;
        g.beginPath();
        g.moveTo(x - 0.012, y + 0.01);
        g.lineTo(x - 0.02, y - 0.035);
        g.lineTo(x + 0.012, y - 0.005);
        g.closePath();
        g.fill();
      }
      cel(g, ell(0.3, -0.53, 0.018, 0.022), MANE);
      // Bridle with a bronze face ornament.
      line(g, LEATHER_DARK, 0.012, 0.28, -0.47, 0.39, -0.39);
      cel(g, ell(0.36, -0.44, 0.015, 0.02, 0.45), BRONZE, BRONZE_DARK, 0.355);
      g.fillStyle = "#1c1214";
      g.beginPath();
      g.arc(0.33, -0.45, 0.012, 0, Math.PI * 2);
      g.fill();
      // Saddle flap (障泥) and a low saddle.
      flap(-0.11, -0.3, 0.2, 0.14);
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.1, -0.33);
          c.quadraticCurveTo(-0.11, -0.37, -0.08, -0.37);
          c.lineTo(0.06, -0.37);
          c.quadraticCurveTo(0.09, -0.37, 0.09, -0.33);
          c.closePath();
        },
        LEATHER,
        LEATHER_DARK,
        -0.06,
      );
    } else {
      const back = facing === "up";
      if (back) {
        cel(g, ell(0, -0.46, 0.05, 0.05), COAT, COAT_DARK, -0.02);
        for (const s of [-1, 1])
          cel(g, poly(s * 0.03, -0.49, s * 0.05, -0.56, s * 0.065, -0.48), COAT);
      }
      hooves(-0.1, 0.05, back);
      hooves(0.05, 0.05, back);
      cel(g, ell(0, -0.25, 0.165, 0.125), COAT, COAT_DARK, -0.08);
      flap(-0.19, -0.31, 0.38, 0.12);
      if (back) {
        cel(g, ell(0, -0.2, 0.035, 0.03), MANE);
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.015, -0.18);
            c.quadraticCurveTo(-0.03, -0.12, -0.01, -0.07);
            c.lineTo(0.015, -0.08);
            c.quadraticCurveTo(0.0, -0.13, 0.015, -0.18);
            c.closePath();
          },
          MANE,
        );
      }
    }
  });
}

/** The front-view horse's head, drawn over the rider, with a bronze face ornament (當盧). */
function horseHead(): Part {
  return paint(0.3, 0.42, 0.15, 0.38, (g) => {
    for (const s of [-1, 1]) cel(g, poly(s * 0.03, -0.27, s * 0.06, -0.34, s * 0.075, -0.25), COAT);
    cel(g, ell(0, -0.17, 0.068, 0.125), COAT, COAT_DARK, -0.03);
    cel(g, ell(0, -0.31, 0.02, 0.025), MANE);
    cel(
      g,
      poly(-0.022, -0.27, 0.022, -0.27, 0.012, -0.15, -0.012, -0.15),
      BRONZE,
      BRONZE_DARK,
      -0.004,
    );
    line(g, LEATHER_DARK, 0.012, -0.065, -0.2, 0.065, -0.2);
    g.fillStyle = "#1c1214";
    for (const s of [-1, 1]) {
      g.beginPath();
      g.arc(s * 0.047, -0.22, 0.012, 0, Math.PI * 2);
      g.fill();
      g.beginPath();
      g.arc(s * 0.02, -0.07, 0.008, 0, Math.PI * 2);
      g.fill();
    }
  });
}

/**
 * The stone-thrower: Cao Cao's "thunderbolt cart" (霹靂車) at Guandu, a traction
 * trebuchet on a wheeled frame. A banner in the side's colour flies from the back.
 */
function cart(P: Pal, facing: Facing): Part {
  return paint(0.9, 0.78, 0.45, 0.64, (g) => {
    const wheel = (x: number, rx: number) => {
      cel(g, ell(x, -0.08, rx, 0.08), WOOD_DARK, "#3a2414", x - rx * 0.2);
      if (rx > 0.05)
        for (let i = 0; i < 3; i++) {
          const a = (i / 3) * Math.PI;
          line(
            g,
            "#3a2414",
            0.01,
            x + Math.cos(a) * rx,
            -0.08 + Math.sin(a) * 0.08,
            x - Math.cos(a) * rx,
            -0.08 - Math.sin(a) * 0.08,
          );
        }
      cel(g, ell(x, -0.08, Math.min(rx, 0.02), 0.02), BRONZE);
    };
    const post = (x0: number, y0: number, x1: number, y1: number) => {
      line(g, INK, 0.05, x0, y0, x1, y1);
      line(g, WOOD, 0.032, x0, y0, x1, y1);
    };
    const banner = (x: number) => {
      line(g, INK, 0.022, x, -0.17, x, -0.6);
      line(g, WOOD_DARK, 0.012, x, -0.17, x, -0.6);
      const flag = poly(x, -0.6, x - 0.13, -0.59, x - 0.12, -0.5, x, -0.49);
      cel(g, flag, P.robe, P.robeDark, x - 0.08);
      inside(g, flag, () => line(g, P.edge, 0.012, x - 0.13, -0.6, x - 0.12, -0.5));
    };
    if (facing === "side") {
      banner(-0.27);
      wheel(-0.2, 0.08);
      wheel(0.2, 0.08);
      cel(g, rr(-0.3, -0.19, 0.6, 0.07, 0.02), WOOD, WOOD_DARK, -0.25);
      post(-0.13, -0.17, 0.02, -0.44);
      post(0.17, -0.17, 0.02, -0.44);
      post(-0.07, -0.28, 0.11, -0.28);
      cel(g, ell(0.02, -0.44, 0.032, 0.032), BRONZE, BRONZE_DARK, 0.01);
    } else {
      banner(0.24);
      wheel(-0.22, 0.03);
      wheel(0.22, 0.03);
      cel(g, rr(-0.21, -0.21, 0.42, 0.09, 0.02), WOOD, WOOD_DARK, -0.16);
      post(-0.12, -0.18, -0.06, -0.46);
      post(0.12, -0.18, 0.06, -0.46);
      cel(g, rr(-0.1, -0.47, 0.2, 0.035, 0.01), WOOD_DARK);
      cel(g, rr(-0.2, -0.165, 0.4, 0.03, 0.01), P.robe, P.robeDark, -0.15);
    }
  });
}

/**
 * The trebuchet's beam (pivot: the axle). The long arm, with the sling and its
 * stone, points along +x; the hauling ropes hang from the short arm. At rest the
 * long arm lies down and back.
 */
function throwArm(): Part {
  const rest = 2.5;
  // Hang the ropes straight down when the beam is at rest.
  const dx = Math.sin(rest);
  const dy = Math.cos(rest);
  return paint(0.9, 0.64, 0.3, 0.32, (g) => {
    for (const off of [-0.012, 0, 0.012])
      line(g, "#cbb98f", 0.007, -0.15, off, -0.15 + dx * 0.28, off + dy * 0.28);
    cel(g, rr(-0.17, -0.02, 0.7, 0.04, 0.015), WOOD, WOOD_DARK, 0.1);
    line(g, "#cbb98f", 0.008, 0.53, 0, 0.56, 0.07);
    cel(g, ell(0.56, 0.085, 0.03, 0.022), LEATHER, LEATHER_DARK, 0.55);
    cel(g, ell(0.56, 0.075, 0.022, 0.02), "#8f8a80", "#6a655c", 0.555);
  });
}

// ---------------------------------------------------------------- art per look and facing

interface Art {
  legF: Part | null;
  legB: Part | null;
  body: Part | null;
  head: Part | null;
  armF: Part | null;
  armB: Part | null;
  weapon: Part | null;
  offhand: Part | null;
  /** Unused: capes are not of the period (ADR 0046). The slot stays for drawn rigs. */
  cape: Part | null;
  mount: Part | null;
  mountFront: Part | null;
  throwArm: Part | null;
}

const artCache = new Map<string, Art>();

function artFor(look: Look, facing: Facing): Art {
  const key = `${look.kind}|${look.side}|${look.beard}|${look.skin}|${look.elite}|${facing}`;
  const hit = artCache.get(key);
  if (hit) return hit;
  const base = SIDES[look.side % SIDES.length] ?? (SIDES[0] as Pal);
  const P: Pal = look.elite ? { ...base, edge: "#f2d27e", lace: "#e3b24a" } : base;
  const k = look.kind;
  const art: Art =
    k === "catapult"
      ? {
          legF: null,
          legB: null,
          body: null,
          head: null,
          armF: null,
          armB: null,
          weapon: null,
          offhand: null,
          cape: null,
          mount: cart(P, facing),
          mountFront: null,
          throwArm: throwArm(),
        }
      : {
          legF: k === "cavalry" ? null : leg(true, facing),
          legB: k === "cavalry" ? null : leg(false, facing),
          body: body(P, k, facing),
          head: head(P, look, facing),
          armF: arm(P, k, true),
          armB: arm(P, k, facing !== "side"),
          weapon: weapon(P, k),
          offhand: offhand(P, k, facing),
          cape: null,
          mount: k === "cavalry" ? horse(P, facing) : null,
          mountFront: k === "cavalry" && facing === "down" ? horseHead() : null,
          throwArm: null,
        };
  artCache.set(key, art);
  return art;
}

// ---------------------------------------------------------------- the rig

type PartName = keyof Art;

/** Where parts sit, by facing (tile units; hip-relative for the torso's children). */
const LAYOUT: Record<
  Facing,
  {
    legF: [number, number];
    legB: [number, number];
    armF: [number, number];
    armB: [number, number];
    torso: PartName[];
  }
> = {
  down: {
    legF: [-0.065, -0.19],
    legB: [0.065, -0.19],
    armF: [-0.15, -0.25],
    armB: [0.15, -0.25],
    torso: ["cape", "body", "armB", "armF", "head"],
  },
  up: {
    legF: [0.065, -0.19],
    legB: [-0.065, -0.19],
    armF: [0.15, -0.25],
    armB: [-0.15, -0.25],
    torso: ["armF", "armB", "body", "cape", "head"],
  },
  side: {
    legF: [0.035, -0.19],
    legB: [-0.025, -0.19],
    armF: [0.02, -0.25],
    armB: [-0.05, -0.25],
    torso: ["cape", "armB", "body", "head", "armF"],
  },
};

const POSE_LENGTH: Record<RigPose, number> = {
  ready: 0,
  walk: 0,
  stand: 0,
  weak: 0,
  attack: 0.56,
  guard: 0.35,
  hurt: 0.45,
  cheer: 0,
};

const clamp01 = (v: number) => Math.max(0, Math.min(1, v));
const smooth = (v: number) => {
  const t = clamp01(v);
  return t * t * (3 - 2 * t);
};
const span = (k: number, a: number, b: number) => smooth((k - a) / (b - a));

export class ChibiRig {
  /** Positioned at the tile centre by the field; scaled to the tile size. */
  readonly root = new Container();
  private feet = new Container();
  private rider = new Container();
  private torso = new Container();
  private parts = {} as Record<PartName, Sprite>;
  private facing: Facing = "down";
  private built = false;
  private mirror = false;
  private size = 48;
  private pose: RigPose = "ready";
  private time = 0;
  private clock = Math.random() * 10;

  constructor(readonly look: Look) {
    const names: PartName[] = [
      "legF",
      "legB",
      "body",
      "head",
      "armF",
      "armB",
      "weapon",
      "offhand",
      "cape",
      "mount",
      "mountFront",
      "throwArm",
    ];
    for (const n of names) {
      const s = new Sprite();
      s.scale.set(1 / U);
      this.parts[n] = s;
    }
    const p = this.parts;
    this.root.addChild(this.feet);
    this.feet.position.set(0, 0.32);
    this.feet.sortableChildren = true;
    this.feet.addChild(p.mount, this.rider, p.mountFront, p.throwArm);
    p.mount.zIndex = 0;
    this.rider.zIndex = 1;
    p.throwArm.zIndex = 2;
    p.mountFront.zIndex = 3;
    this.rider.addChild(p.legB, p.legF, this.torso);
    this.torso.sortableChildren = true;
    this.torso.addChild(p.cape, p.armB, p.body, p.head, p.armF);
    p.armF.addChild(p.weapon);
    p.armB.addChild(p.offhand);
    // Children of the arms sit at the hand and keep the parent's 1/U scale undone.
    for (const n of ["weapon", "offhand"] as const) {
      p[n].scale.set(1);
      p[n].position.set(0, 0.155 * U);
    }
    this.setFacing("down", false);
  }

  /** The tile size in pixels. */
  setSize(tile: number): void {
    this.size = tile;
    this.root.scale.set(this.mirror ? -tile : tile, tile);
  }

  /** Side parts are painted facing right; `mirror` turns them to face left. */
  setFacing(f: Facing, mirror: boolean): void {
    this.mirror = mirror;
    this.root.scale.set(mirror ? -this.size : this.size, this.size);
    if (f === this.facing && this.built) return;
    this.built = true;
    this.facing = f;
    const art = artFor(this.look, f);
    for (const [name, sprite] of Object.entries(this.parts) as [PartName, Sprite][]) {
      const a = art[name];
      sprite.visible = a !== null;
      if (a) {
        sprite.texture = a.tex;
        sprite.anchor.set(a.ax, a.ay);
      }
    }
    const L = LAYOUT[f];
    const p = this.parts;
    p.legF.position.set(...L.legF);
    p.legB.position.set(...L.legB);
    p.armF.position.set(...L.armF);
    p.armB.position.set(...L.armB);
    p.head.position.set(f === "side" ? 0.01 : 0, -0.27);
    p.cape.position.set(f === "side" ? -0.02 : 0, -0.27);
    L.torso.forEach((n, i) => {
      p[n].zIndex = i;
    });
    this.torso.position.set(0, -0.19);
    this.rider.position.set(0, this.look.kind === "cavalry" ? -0.2 : 0);
    p.mount.position.set(0, 0);
    p.mountFront.position.set(0, 0);
    p.throwArm.position.set(f === "side" ? 0.02 : 0, -0.44);
  }

  setPose(pose: RigPose): void {
    const oneShot = POSE_LENGTH[pose] > 0;
    if (pose === this.pose && !oneShot) return;
    this.pose = pose;
    this.time = 0;
  }

  setTint(color: number): void {
    this.root.tint = color;
  }

  /** `real`: wall-clock ms; `fast`: ms scaled by the battle speed (for one-shot actions). */
  update(real: number, fast: number): void {
    const oneShot = POSE_LENGTH[this.pose] > 0;
    this.time += (oneShot ? fast : real) / 1000;
    this.clock += real / 1000;
    const k = oneShot ? clamp01(this.time / POSE_LENGTH[this.pose]) : 0;
    const t = this.clock;
    const p = this.parts;
    const side = this.facing === "side";
    const flip = this.facing === "up" ? -1 : 1;
    const kind = this.look.kind;
    let armF = side ? -0.2 : 0.18;
    let armB = side ? 0.2 : -0.18;
    let lean = 0;
    let lunge = 0;
    let bob = 0;
    let squash = 1 + Math.sin(t * 2.6) * 0.018;
    let headTilt = Math.sin(t * 1.3) * 0.03;
    let legSwing = 0;
    let hop = 0;
    // The trebuchet's long arm rests down and back, then swings up over the top.
    let arm = side ? 2.5 : -Math.PI / 2;
    switch (this.pose) {
      case "ready":
        bob = -Math.abs(Math.sin(t * 2.6)) * 0.008;
        break;
      case "walk": {
        const w = Math.sin(t * 13);
        legSwing = w * 0.55;
        armF += -w * 0.4;
        armB += w * 0.4;
        bob = -Math.abs(Math.cos(t * 13)) * 0.025;
        break;
      }
      case "stand":
        squash = 1 + Math.sin(t * 1.6) * 0.01;
        break;
      case "weak":
        lean = 0.18;
        headTilt = 0.28;
        squash = 0.95 + Math.sin(t * 1.4) * 0.01;
        armF = side ? 0.05 : 0.08;
        armB = side ? 0.1 : -0.08;
        break;
      case "cheer":
        armF = side ? -2.8 : 2.7;
        armB = side ? -2.5 : -2.7;
        hop = -Math.abs(Math.sin(t * 6)) * 0.05;
        break;
      case "hurt": {
        const h = 1 - span(k, 0.5, 1);
        lean = -0.28 * h;
        headTilt = -0.25 * h;
        armF += (side ? 0.7 : 0.6) * h;
        armB += (side ? 0.9 : -0.6) * h;
        lunge = -0.06 * h;
        break;
      }
      case "guard": {
        const g = Math.sin(clamp01(k) * Math.PI);
        armB = side ? -1.1 * g + 0.2 * (1 - g) : 1.1 * g - 0.18 * (1 - g);
        squash = 1 - 0.05 * g;
        lunge = -0.03 * g;
        break;
      }
      case "attack": {
        const wind = span(k, 0, 0.38);
        const strike = span(k, 0.38, 0.46);
        const back = span(k, 0.72, 1);
        const held = strike * (1 - back);
        if (kind === "archer") {
          armB = lerp(armB, side ? -1.5 : 0.9, Math.max(wind, held));
          armF = lerp(armF, side ? -1.0 : 0.6, wind);
          armF = lerp(armF, side ? -1.5 : 0.2, held);
          armF = lerp(armF, side ? -0.2 : 0.18, back);
        } else if (kind === "catapult") {
          arm = side ? lerp(lerp(2.5, 2.3, wind), 5.4, strike) : -Math.PI / 2;
          arm = lerp(arm, side ? 2.5 : -Math.PI / 2, back);
          lunge = (0.03 * wind - 0.05 * strike) * (1 - back);
        } else if (kind === "martialArtist") {
          armF = lerp(lerp(armF, side ? 0.7 : 0.9, wind), side ? -1.55 : -0.3, strike);
          armF = lerp(armF, side ? -0.2 : 0.18, back);
          lunge = 0.18 * held;
          lean = (0.12 * held - 0.06 * wind) * (1 - back);
        } else if (kind === "cavalry") {
          armF = lerp(lerp(armF, side ? 0.6 : 1.0, wind), side ? -0.45 : -0.2, strike);
          armF = lerp(armF, side ? -0.2 : 0.18, back);
          lunge = 0.22 * held - 0.04 * wind * (1 - strike);
        } else {
          armF = lerp(lerp(armF, side ? 2.6 : 2.8, wind), side ? -1.2 : -0.35, strike);
          armF = lerp(armF, side ? -0.2 : 0.18, back);
          lean = (0.16 * held - 0.12 * wind * (1 - strike)) * (1 - back);
          lunge = 0.16 * held;
        }
        break;
      }
    }
    // Lunge toward the facing: right in side view, toward the viewer in front, away in back.
    const lx = side ? lunge : 0;
    const ly = side ? 0 : this.facing === "down" ? lunge * 0.5 : -lunge * 0.5;
    this.rider.position.x = lx;
    this.feet.position.set(kind === "cavalry" || kind === "catapult" ? lx : 0, 0.32 + ly + hop);
    if (kind === "cavalry" || kind === "catapult") this.rider.position.x = 0;
    this.rider.position.y = (kind === "cavalry" ? -0.2 : 0) + bob;
    this.torso.rotation = (side ? lean : 0) * flip;
    this.torso.scale.y = squash;
    if (!side) this.torso.position.y = -0.19 + lean * 0.06;
    else this.torso.position.y = -0.19;
    p.head.rotation = (side ? headTilt : headTilt * 0.4) * flip;
    if (!side) p.head.position.y = -0.27 + Math.max(0, headTilt) * 0.04;
    p.armF.rotation = armF * flip;
    p.armB.rotation = armB * flip;
    // The shield and the bow stay upright whatever the arm does.
    p.offhand.rotation = -p.armB.rotation;
    p.weapon.rotation = weaponRest(kind, this.facing) * flip;
    if (side) {
      p.legF.rotation = legSwing;
      p.legB.rotation = -legSwing;
      p.legF.position.y = LAYOUT.side.legF[1];
      p.legB.position.y = LAYOUT.side.legB[1];
    } else {
      p.legF.rotation = 0;
      p.legB.rotation = 0;
      p.legF.position.y = LAYOUT[this.facing].legF[1] - Math.max(0, legSwing) * 0.05;
      p.legB.position.y = LAYOUT[this.facing].legB[1] - Math.max(0, -legSwing) * 0.05;
    }
    p.cape.rotation = (Math.sin(t * 2.2) * 0.05 - (side ? lean * 0.6 : 0)) * flip;
    p.throwArm.rotation = arm;
    // Seen from the front or back, the arm swings toward or away from the viewer: it shortens and flips.
    if (!side && kind === "catapult") {
      const swing = this.pose === "attack" ? span(k, 0.38, 0.46) * (1 - span(k, 0.72, 1)) : 0;
      p.throwArm.scale.set((1 / U) * (0.45 - 1.1 * swing), (1 / U) * 0.8);
    } else p.throwArm.scale.set(1 / U);
    if (kind === "cavalry")
      p.mount.position.y =
        Math.abs(Math.sin(t * (this.pose === "walk" ? 13 : 2.6))) *
        (this.pose === "walk" ? -0.02 : -0.004);
  }
}

function lerp(a: number, b: number, k: number): number {
  return a + (b - a) * k;
}

/** How a weapon sits in the hand: the halberd lies forward in side view and stands up in front view. */
function weaponRest(kind: Kind, facing: Facing): number {
  if (kind === "cavalry") return facing === "side" ? -1.45 : Math.PI - 0.15;
  if (kind === "strategist") return facing === "side" ? -0.6 : 0.4;
  return facing === "side" ? -0.3 : 0.2;
}
