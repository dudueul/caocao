/**
 * Our own characters (ADR 0045): rigged 2D chibi figures at tile scale, seen
 * from the battle map's bird's-eye three-quarter view. Each figure is a few
 * painted parts (legs, torso, head, arms, weapon, shield or bow, cape, horse or
 * cart) on a small skeleton, posed procedurally: ready, walk, attack, guard,
 * hurt, slumped and cheering, in three facings (front, back, side; the side is
 * mirrored for left).
 *
 * The parts are painted in code as interim art in the chosen style; drawn rigs
 * from an artist replace these paintings without changing the skeleton.
 */
import { Container, Sprite, Texture } from "pixi.js";

export type Facing = "down" | "up" | "side";
export type RigPose = "ready" | "walk" | "stand" | "weak" | "attack" | "guard" | "hurt" | "cheer";
export type Kind = "infantry" | "archer" | "cavalry" | "strategist" | "martialArtist" | "catapult";

/** Texture pixels per tile. */
const U = 128;
const INK = "#2a1a12";
const LW = 0.02;
const BOOT = "#4a3326";
const WOOD = "#7a5234";
const WOOD_DARK = "#54371f";
const STEEL = "#e3e9ef";
const STEEL_DARK = "#9aa6b2";
const COAT = "#8a5a34";
const COAT_DARK = "#64401f";

interface Pal {
  cloth: string;
  clothDark: string;
  armor: string;
  armorDark: string;
  trim: string;
  trimDark: string;
  plume: string;
}

const SIDES: Pal[] = [
  {
    cloth: "#2f6fa3",
    clothDark: "#1f4e78",
    armor: "#e6e3da",
    armorDark: "#aeaa9e",
    trim: "#e2b65c",
    trimDark: "#a97e2c",
    plume: "#3d8ee0",
  },
  {
    cloth: "#b03a2e",
    clothDark: "#7a231b",
    armor: "#c9b38a",
    armorDark: "#927d58",
    trim: "#e2b65c",
    trimDark: "#a97e2c",
    plume: "#e0453a",
  },
  {
    cloth: "#3c8f55",
    clothDark: "#27613a",
    armor: "#d8d6cc",
    armorDark: "#a19e93",
    trim: "#e2b65c",
    trimDark: "#a97e2c",
    plume: "#4cc06a",
  },
  {
    cloth: "#b8892c",
    clothDark: "#7e5c1a",
    armor: "#d9cfb6",
    armorDark: "#a39878",
    trim: "#f0d48e",
    trimDark: "#b08a3a",
    plume: "#f0c040",
  },
];
const SKIN = [
  ["#f6d2b0", "#e1a983"],
  ["#ecc39c", "#cf966c"],
] as const;
const HAIR = ["#231c1a", "#3a2a22", "#2b2430"];

/** What a unit looks like: its class, side colour and a few traits from its id. */
export interface Look {
  kind: Kind;
  side: number;
  /** 0 topknot, 1 ponytail, 2 short. */
  hair: number;
  beard: boolean;
  skin: number;
}

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
  const kind = (KINDS as readonly string[]).includes(classId) ? (classId as Kind) : "infantry";
  return { kind, side, hair: h % 3, beard: (h >>> 3) % 3 === 0, skin: (h >>> 5) % 2 };
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

// ---------------------------------------------------------------- parts

function leg(P: Pal, near: boolean, facing: Facing): Part {
  return paint(0.16, 0.24, 0.08, 0.02, (g) => {
    cel(
      g,
      rr(-0.045, -0.01, 0.09, 0.14, 0.04),
      near ? P.cloth : P.clothDark,
      near ? P.clothDark : undefined,
      -0.012,
    );
    if (facing === "side") cel(g, rr(-0.045, 0.1, 0.11, 0.065, 0.03), BOOT);
    else cel(g, rr(-0.05, 0.1, 0.1, 0.07, 0.03), BOOT);
  });
}

function body(P: Pal, kind: Kind, facing: Facing): Part {
  return paint(0.46, 0.52, 0.23, 0.34, (g) => {
    const side = facing === "side";
    const back = facing === "up";
    const hw = side ? 0.105 : 0.14;
    const long = kind === "strategist";
    const torso: Path = (c) => {
      c.beginPath();
      c.moveTo(-hw, -0.27);
      c.quadraticCurveTo(0, -0.3, hw, -0.27);
      if (long) {
        c.lineTo(hw + 0.03, 0.13);
        c.quadraticCurveTo(0, 0.17, -hw - 0.03, 0.13);
      } else {
        c.lineTo(hw - 0.01, 0.02);
        c.quadraticCurveTo(0, 0.05, -hw + 0.01, 0.02);
      }
      c.closePath();
    };
    cel(g, torso, P.cloth, P.clothDark, side ? -0.03 : -0.06);
    const belt = () => {
      cel(g, rr(-hw + 0.005, -0.035, (hw - 0.005) * 2, 0.045, 0.015), "#5a3a26");
      if (!back)
        cel(
          g,
          rr(side ? 0.03 : -0.025, -0.04, 0.05, 0.055, 0.01),
          P.trim,
          P.trimDark,
          side ? 0.04 : -0.01,
        );
    };
    switch (kind) {
      case "infantry":
      case "cavalry": {
        const plate = side
          ? poly(-0.02, -0.25, 0.1, -0.25, 0.09, -0.07, -0.02, -0.07)
          : poly(-0.1, -0.25, 0.1, -0.25, 0.09, -0.07, -0.09, -0.07);
        cel(g, plate, P.armor, P.armorDark, side ? 0.02 : -0.04);
        if (!back) cel(g, ell(side ? 0.05 : 0, -0.17, 0.03, 0.03), P.trim, P.trimDark, -0.01);
        belt();
        if (!side)
          for (const s of [-1, 1])
            cel(
              g,
              poly(s * 0.13, 0.01, s * 0.01, 0.01, s * 0.02, 0.085, s * 0.14, 0.075),
              P.armor,
              P.armorDark,
              -0.2,
            );
        else
          cel(g, poly(-0.09, 0.01, 0.1, 0.01, 0.11, 0.08, -0.1, 0.08), P.armor, P.armorDark, -0.02);
        for (const s of side ? [0.02] : [-1, 1])
          cel(
            g,
            ell(side ? s : s * 0.145, -0.25, 0.06, 0.045, side ? 0 : s * 0.3),
            P.armor,
            P.armorDark,
            side ? 0 : s * 0.145 - 0.02,
          );
        break;
      }
      case "archer": {
        const vest = side ? rr(-0.06, -0.26, 0.15, 0.22, 0.03) : rr(-0.11, -0.26, 0.22, 0.22, 0.04);
        cel(g, vest, "#7a5434", "#5a3a22", side ? -0.01 : -0.05);
        if (back || side) {
          g.save();
          g.translate(side ? -0.09 : 0.03, -0.18);
          g.rotate(side ? 0.35 : -0.45);
          cel(g, rr(-0.03, -0.12, 0.06, 0.2, 0.02), "#6a4429", "#4a2e1b", -0.01);
          for (const dx of [-0.018, 0, 0.018]) cel(g, ell(dx, -0.14, 0.01, 0.025), "#f4f0e6");
          g.restore();
        } else {
          g.strokeStyle = "#4a2e1b";
          g.lineWidth = 0.025;
          g.beginPath();
          g.moveTo(-0.11, -0.25);
          g.lineTo(0.1, -0.04);
          g.stroke();
        }
        belt();
        break;
      }
      case "strategist": {
        if (!back) {
          cel(
            g,
            poly(side ? 0.0 : -0.06, -0.28, side ? 0.1 : 0.06, -0.28, side ? 0.05 : 0, -0.15),
            P.trim,
            P.trimDark,
            -0.2,
          );
          g.strokeStyle = P.clothDark;
          g.lineWidth = 0.012;
          g.beginPath();
          g.moveTo(side ? 0.05 : 0, -0.15);
          g.lineTo(side ? 0.06 : 0, 0.15);
          g.stroke();
        }
        cel(g, rr(-hw, -0.06, hw * 2, 0.04, 0.015), P.trim, P.trimDark, -hw + 0.03);
        break;
      }
      case "martialArtist": {
        if (!back && !side)
          cel(g, poly(-0.05, -0.28, 0.05, -0.28, 0, -0.1), SKIN[0][0], SKIN[0][1], -0.02);
        if (side) cel(g, poly(0.04, -0.28, 0.1, -0.27, 0.09, -0.12), SKIN[0][0]);
        cel(g, rr(-hw, -0.06, hw * 2, 0.05, 0.02), P.plume, P.clothDark, -hw + 0.03);
        if (!side) cel(g, ell(back ? 0 : 0.07, -0.035, 0.035, 0.03), P.plume, P.clothDark, 0.05);
        break;
      }
      default:
        break;
    }
  });
}

function head(P: Pal, look: Look, facing: Facing): Part {
  const [skin, skinDark] = SKIN[look.skin] ?? SKIN[0];
  const hair = HAIR[look.hair] ?? HAIR[0] ?? "#231c1a";
  const kind = look.kind;
  return paint(0.62, 0.68, 0.31, 0.6, (g) => {
    const side = facing === "side";
    const back = facing === "up";
    const cy = -0.19;
    // Behind the head: ponytail, headband tails, scholar ribbons.
    if (look.hair === 1 && kind !== "infantry" && kind !== "cavalry") {
      const px = side ? -0.17 : back ? 0 : 0.14;
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(px - 0.03, cy - 0.06);
          c.quadraticCurveTo(
            px + (side ? -0.12 : 0.04),
            cy + 0.08,
            px + (side ? -0.07 : 0.02),
            cy + 0.2,
          );
          c.quadraticCurveTo(px + 0.02, cy + 0.06, px + 0.04, cy - 0.04);
          c.closePath();
        },
        hair,
      );
    }
    if (kind === "martialArtist" || kind === "strategist") {
      const tx = side ? -0.17 : back ? 0 : -0.15;
      const color = kind === "strategist" ? "#1f1c22" : P.plume;
      for (const k of [0, 1])
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(tx, cy - (kind === "strategist" ? 0.12 : 0.08));
            c.quadraticCurveTo(tx - 0.08 - k * 0.03, cy + 0.02, tx - 0.06 - k * 0.05, cy + 0.12);
            c.lineTo(tx - 0.03 - k * 0.04, cy + 0.12);
            c.quadraticCurveTo(tx - 0.03, cy, tx + 0.02, cy - 0.08);
            c.closePath();
          },
          color,
        );
    }
    // Neck.
    cel(g, rr(-0.035, -0.06, 0.07, 0.08, 0.02), skinDark);
    // Face.
    cel(
      g,
      ell(side ? 0.01 : 0, cy, side ? 0.175 : 0.185, 0.175),
      skin,
      skinDark,
      side ? -0.09 : -0.1,
    );
    if (side) cel(g, ell(-0.035, cy + 0.02, 0.03, 0.045), skin, skinDark, -0.04);
    // Hair cap.
    if (back) cel(g, ell(0, cy - 0.01, 0.19, 0.175), hair);
    else if (side)
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(0.16, cy - 0.11);
          c.quadraticCurveTo(0.06, cy - 0.23, -0.1, cy - 0.17);
          c.quadraticCurveTo(-0.22, cy - 0.06, -0.12, cy + 0.12);
          c.lineTo(-0.07, cy + 0.06);
          c.quadraticCurveTo(-0.09, cy - 0.03, -0.0, cy - 0.04);
          c.quadraticCurveTo(0.06, cy - 0.1, 0.12, cy - 0.06);
          c.quadraticCurveTo(0.17, cy - 0.07, 0.16, cy - 0.11);
          c.closePath();
        },
        hair,
      );
    else
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.19, cy - 0.01);
          c.quadraticCurveTo(-0.19, cy - 0.19, 0, cy - 0.19);
          c.quadraticCurveTo(0.19, cy - 0.19, 0.19, cy - 0.01);
          c.quadraticCurveTo(0.14, cy - 0.08, 0.09, cy - 0.05);
          c.quadraticCurveTo(0.05, cy - 0.11, 0.01, cy - 0.05);
          c.quadraticCurveTo(-0.04, cy - 0.11, -0.08, cy - 0.05);
          c.quadraticCurveTo(-0.13, cy - 0.08, -0.19, cy - 0.01);
          c.closePath();
        },
        hair,
      );
    // Face details.
    if (!back) {
      const eyes = side ? [0.1] : [-0.068, 0.068];
      for (const ex of eyes) {
        g.fillStyle = "#1c1214";
        g.beginPath();
        g.ellipse(ex, cy + 0.03, side ? 0.022 : 0.028, 0.042, 0, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = look.side === 1 ? "#7a3a2a" : "#3d5a8a";
        g.beginPath();
        g.ellipse(ex + 0.004, cy + 0.045, side ? 0.014 : 0.019, 0.024, 0, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = "#ffffff";
        g.beginPath();
        g.arc(ex + 0.01, cy + 0.015, 0.011, 0, Math.PI * 2);
        g.fill();
      }
      g.strokeStyle = INK;
      g.lineWidth = 0.016;
      g.beginPath();
      for (const ex of eyes) {
        g.moveTo(ex - 0.03, cy - 0.035);
        g.lineTo(ex + 0.03, cy - 0.045);
      }
      g.stroke();
      g.fillStyle = "rgba(230,120,110,0.45)";
      for (const bx of side ? [0.07] : [-0.11, 0.11]) {
        g.beginPath();
        g.ellipse(bx, cy + 0.09, 0.03, 0.017, 0, 0, Math.PI * 2);
        g.fill();
      }
      g.strokeStyle = "#7a3a30";
      g.lineWidth = 0.014;
      g.beginPath();
      if (side) {
        g.moveTo(0.12, cy + 0.11);
        g.lineTo(0.15, cy + 0.105);
      } else {
        g.moveTo(-0.025, cy + 0.11);
        g.quadraticCurveTo(0, cy + 0.125, 0.025, cy + 0.11);
      }
      g.stroke();
      if (look.beard)
        cel(
          g,
          (c) => {
            c.beginPath();
            if (side) {
              c.moveTo(0.02, cy + 0.12);
              c.quadraticCurveTo(0.08, cy + 0.22, 0.15, cy + 0.13);
              c.quadraticCurveTo(0.09, cy + 0.15, 0.04, cy + 0.11);
            } else {
              c.moveTo(-0.1, cy + 0.12);
              c.quadraticCurveTo(0, cy + 0.24, 0.1, cy + 0.12);
              c.quadraticCurveTo(0, cy + 0.16, -0.1, cy + 0.12);
            }
            c.closePath();
          },
          hair,
        );
    }
    // Headgear by class.
    const top = cy - 0.19;
    switch (kind) {
      case "infantry":
      case "cavalry": {
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.205, cy - 0.02);
            c.quadraticCurveTo(-0.2, top - 0.04, 0, top - 0.04);
            c.quadraticCurveTo(0.2, top - 0.04, 0.205, cy - 0.02);
            c.lineTo(0.205, cy);
            c.lineTo(-0.205, cy);
            c.closePath();
          },
          P.armor,
          P.armorDark,
          side ? -0.08 : -0.1,
        );
        if (!back) cel(g, rr(-0.21, cy - 0.045, 0.42, 0.045, 0.02), P.trim, P.trimDark, -0.1);
        if (kind === "cavalry")
          cel(
            g,
            (c) => {
              c.beginPath();
              c.moveTo(0, top - 0.04);
              c.quadraticCurveTo(-0.06, top - 0.2, -0.2, top - 0.17);
              c.quadraticCurveTo(-0.12, top - 0.1, -0.03, top - 0.02);
              c.closePath();
            },
            P.plume,
            P.clothDark,
            -0.12,
          );
        else cel(g, ell(0, top - 0.07, 0.04, 0.05), P.plume, P.clothDark, -0.015);
        cel(g, ell(0, top - 0.035, 0.022, 0.022), P.trim);
        break;
      }
      case "archer": {
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.195, cy - 0.03);
            c.quadraticCurveTo(-0.18, top - 0.05, 0.02, top - 0.03);
            c.quadraticCurveTo(0.2, top - 0.02, 0.195, cy - 0.04);
            c.quadraticCurveTo(0, cy - 0.1, -0.195, cy - 0.03);
            c.closePath();
          },
          P.cloth,
          P.clothDark,
          -0.1,
        );
        g.save();
        g.translate(side ? -0.08 : -0.1, top + 0.02);
        g.rotate(-0.5);
        cel(g, ell(0, -0.08, 0.025, 0.09), "#f4f0e6", "#c9c2b4", -0.005);
        g.restore();
        break;
      }
      case "strategist": {
        cel(g, rr(-0.13, top - 0.1, 0.26, 0.14, 0.05), "#1f1c22", "#141217", -0.06);
        cel(g, rr(-0.135, top + 0.01, 0.27, 0.035, 0.015), P.trim, P.trimDark, -0.08);
        break;
      }
      case "martialArtist": {
        for (const sx of side ? [-0.1, -0.02, 0.06] : [-0.12, -0.04, 0.04, 0.12])
          cel(g, poly(sx - 0.05, top + 0.05, sx + 0.01, top - 0.08, sx + 0.05, top + 0.05), hair);
        cel(g, rr(-0.19, cy - 0.1, 0.38, 0.05, 0.02), P.plume, P.clothDark, -0.1);
        break;
      }
      default:
        break;
    }
    if (look.hair === 0 && (kind === "archer" || kind === "martialArtist")) {
      cel(g, ell(side ? -0.04 : 0, top - 0.04, 0.055, 0.05), hair);
      cel(g, rr(side ? -0.075 : -0.035, top - 0.01, 0.07, 0.02, 0.008), P.trim);
    }
  });
}

function arm(P: Pal, kind: Kind, near: boolean): Part {
  const [skin, skinDark] = SKIN[0];
  return paint(0.2, 0.26, 0.1, 0.04, (g) => {
    const cloth = near ? P.cloth : P.clothDark;
    if (kind === "strategist") {
      cel(
        g,
        poly(-0.04, 0, 0.04, 0, 0.075, 0.13, -0.075, 0.13),
        cloth,
        near ? P.clothDark : undefined,
        -0.03,
      );
      cel(g, rr(-0.075, 0.115, 0.15, 0.025, 0.01), P.trim);
    } else if (kind === "martialArtist") {
      cel(g, rr(-0.03, -0.005, 0.06, 0.14, 0.03), skin, skinDark, -0.01);
      cel(g, rr(-0.034, 0.085, 0.068, 0.045, 0.012), "#f4f0e6", "#c9c2b4", -0.015);
    } else {
      cel(g, rr(-0.035, -0.005, 0.07, 0.135, 0.035), cloth, near ? P.clothDark : undefined, -0.012);
      if (kind === "infantry" || kind === "cavalry")
        cel(g, rr(-0.037, 0.08, 0.074, 0.05, 0.015), P.armor, P.armorDark, -0.015);
    }
    const r = kind === "martialArtist" ? 0.044 : 0.036;
    cel(g, ell(0, 0.155, r, r), skin, skinDark, -0.012);
  });
}

function weapon(P: Pal, kind: Kind): Part | null {
  switch (kind) {
    case "infantry":
      return paint(0.14, 0.44, 0.07, 0.08, (g) => {
        cel(g, rr(-0.015, -0.05, 0.03, 0.08, 0.01), "#5a3a26");
        cel(g, rr(-0.055, 0.025, 0.11, 0.025, 0.01), P.trim, P.trimDark, -0.02);
        cel(
          g,
          poly(-0.018, 0.05, 0.018, 0.05, 0.016, 0.3, 0, 0.34, -0.016, 0.3),
          STEEL,
          STEEL_DARK,
          0,
        );
      });
    case "cavalry":
      return paint(0.16, 0.9, 0.08, 0.26, (g) => {
        cel(g, rr(-0.012, -0.24, 0.024, 0.74, 0.012), WOOD, WOOD_DARK, 0);
        for (const y of [-0.2, 0.42]) cel(g, rr(-0.017, y, 0.034, 0.03, 0.008), P.trim);
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.025, 0.5);
            c.quadraticCurveTo(-0.03, 0.56, 0, 0.62);
            c.quadraticCurveTo(0.03, 0.56, 0.025, 0.5);
            c.closePath();
          },
          STEEL,
          STEEL_DARK,
          0,
        );
        cel(g, poly(-0.01, 0.47, 0.01, 0.47, 0.035, 0.53, -0.035, 0.53), P.plume);
      });
    case "strategist":
      return paint(0.3, 0.3, 0.15, 0.05, (g) => {
        cel(g, rr(-0.012, -0.03, 0.024, 0.08, 0.01), P.trim, P.trimDark, 0);
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(0, 0.04);
            c.arc(0, 0.04, 0.18, Math.PI * 0.32, Math.PI * 0.68);
            c.closePath();
          },
          "#f6f2ea",
          "#d6d0c4",
          0,
        );
        g.strokeStyle = "rgba(120,110,100,0.6)";
        g.lineWidth = 0.008;
        for (let i = 0; i < 5; i++) {
          const a = Math.PI * (0.36 + i * 0.07);
          g.beginPath();
          g.moveTo(0, 0.05);
          g.lineTo(Math.cos(a) * 0.17, 0.04 + Math.sin(a) * 0.17);
          g.stroke();
        }
      });
    default:
      return null;
  }
}

function offhand(P: Pal, kind: Kind, facing: Facing): Part | null {
  if (kind === "infantry" && facing !== "up")
    return paint(0.26, 0.26, 0.13, 0.13, (g) => {
      const rx = facing === "side" ? 0.045 : 0.1;
      cel(g, ell(0, 0, rx, 0.1), P.cloth, P.clothDark, -rx * 0.3);
      g.save();
      ell(0, 0, rx, 0.1)(g);
      g.lineWidth = 0.022;
      g.strokeStyle = P.trim;
      g.stroke();
      g.restore();
      cel(g, ell(0, 0, rx * 0.3, 0.03), P.trim, P.trimDark, -0.01);
    });
  if (kind === "archer")
    return paint(0.14, 0.48, 0.04, 0.24, (g) => {
      g.strokeStyle = "rgba(240,236,220,0.9)";
      g.lineWidth = 0.008;
      g.beginPath();
      g.moveTo(0, -0.2);
      g.lineTo(0, 0.2);
      g.stroke();
      g.strokeStyle = INK;
      g.lineWidth = 0.036;
      g.beginPath();
      g.moveTo(0, -0.2);
      g.quadraticCurveTo(0.11, 0, 0, 0.2);
      g.stroke();
      g.strokeStyle = WOOD;
      g.lineWidth = 0.02;
      g.stroke();
      cel(g, rr(0.02, -0.03, 0.035, 0.06, 0.01), "#4a2e1b");
    });
  return null;
}

function cape(P: Pal, facing: Facing): Part {
  return paint(0.5, 0.42, 0.25, 0.02, (g) => {
    if (facing === "side")
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(0.02, 0);
          c.quadraticCurveTo(-0.12, 0.1, -0.2, 0.3);
          c.lineTo(-0.07, 0.31);
          c.quadraticCurveTo(-0.04, 0.15, 0.06, 0.02);
          c.closePath();
        },
        P.cloth,
        P.clothDark,
        -0.12,
      );
    else
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.15, 0);
          c.quadraticCurveTo(-0.21, 0.15, -0.2, 0.33);
          c.quadraticCurveTo(0, 0.37, 0.2, 0.33);
          c.quadraticCurveTo(0.21, 0.15, 0.15, 0);
          c.closePath();
        },
        facing === "up" ? P.cloth : P.clothDark,
        P.clothDark,
        facing === "up" ? -0.08 : 2,
      );
    if (facing === "up") cel(g, rr(-0.15, -0.01, 0.3, 0.035, 0.015), P.trim, P.trimDark, -0.08);
  });
}

/** The horse under a rider (pivot: between the hooves). */
function horse(P: Pal, facing: Facing): Part {
  return paint(0.84, 0.66, 0.42, 0.56, (g) => {
    const hooves = (x: number, w: number, dark: boolean) => {
      cel(
        g,
        rr(x, -0.18, w, 0.17, 0.02),
        dark ? COAT_DARK : COAT,
        dark ? undefined : COAT_DARK,
        x + 0.015,
      );
      cel(g, rr(x - 0.003, -0.03, w + 0.006, 0.035, 0.01), "#2a1d14");
    };
    if (facing === "side") {
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(-0.25, -0.25);
          c.quadraticCurveTo(-0.38, -0.2, -0.34, -0.05);
          c.quadraticCurveTo(-0.29, -0.15, -0.23, -0.21);
          c.closePath();
        },
        "#2a1d14",
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
      cel(
        g,
        (c) => {
          c.beginPath();
          c.moveTo(0.12, -0.3);
          c.quadraticCurveTo(0.18, -0.45, 0.28, -0.49);
          c.lineTo(0.25, -0.44);
          c.quadraticCurveTo(0.18, -0.38, 0.17, -0.27);
          c.closePath();
        },
        "#2a1d14",
      );
      g.fillStyle = "#1c1214";
      g.beginPath();
      g.arc(0.35, -0.44, 0.012, 0, Math.PI * 2);
      g.fill();
      cel(g, rr(-0.11, -0.34, 0.2, 0.12, 0.03), P.cloth, P.clothDark, -0.06);
      g.save();
      rr(-0.11, -0.34, 0.2, 0.12, 0.03)(g);
      g.lineWidth = 0.014;
      g.strokeStyle = P.trim;
      g.stroke();
      g.restore();
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
      cel(g, rr(-0.18, -0.33, 0.36, 0.12, 0.03), P.cloth, P.clothDark, -0.12);
      g.save();
      rr(-0.18, -0.33, 0.36, 0.12, 0.03)(g);
      g.lineWidth = 0.014;
      g.strokeStyle = P.trim;
      g.stroke();
      g.restore();
      if (back)
        cel(
          g,
          (c) => {
            c.beginPath();
            c.moveTo(-0.025, -0.2);
            c.quadraticCurveTo(-0.05, -0.1, -0.02, -0.03);
            c.lineTo(0.02, -0.03);
            c.quadraticCurveTo(0.05, -0.1, 0.025, -0.2);
            c.closePath();
          },
          "#2a1d14",
        );
    }
  });
}

/** The front-view horse's head, drawn over the rider. */
function horseHead(): Part {
  return paint(0.3, 0.4, 0.15, 0.36, (g) => {
    for (const s of [-1, 1]) cel(g, poly(s * 0.03, -0.27, s * 0.06, -0.34, s * 0.075, -0.25), COAT);
    cel(g, ell(0, -0.17, 0.068, 0.125), COAT, COAT_DARK, -0.03);
    cel(g, ell(0, -0.17, 0.016, 0.08), "#f4ede0");
    cel(g, poly(-0.03, -0.3, 0.03, -0.3, 0, -0.24), "#2a1d14");
    g.fillStyle = "#1c1214";
    for (const s of [-1, 1]) {
      g.beginPath();
      g.arc(s * 0.045, -0.22, 0.012, 0, Math.PI * 2);
      g.fill();
      g.beginPath();
      g.arc(s * 0.02, -0.07, 0.008, 0, Math.PI * 2);
      g.fill();
    }
  });
}

/** A light catapult cart (pivot: between the wheels on the ground). */
function cart(P: Pal, facing: Facing): Part {
  return paint(0.84, 0.56, 0.42, 0.44, (g) => {
    const wheel = (x: number, rx: number) => {
      cel(g, ell(x, -0.08, rx, 0.08), WOOD_DARK, "#3a2414", x - rx * 0.2);
      if (rx > 0.05) {
        g.strokeStyle = "#3a2414";
        g.lineWidth = 0.01;
        for (let i = 0; i < 4; i++) {
          const a = (i / 4) * Math.PI;
          g.beginPath();
          g.moveTo(x + Math.cos(a) * rx, -0.08 + Math.sin(a) * 0.08);
          g.lineTo(x - Math.cos(a) * rx, -0.08 - Math.sin(a) * 0.08);
          g.stroke();
        }
      }
      cel(g, ell(x, -0.08, Math.min(rx, 0.02), 0.02), P.trim);
    };
    if (facing === "side") {
      wheel(-0.2, 0.08);
      wheel(0.2, 0.08);
      cel(g, rr(-0.3, -0.19, 0.6, 0.07, 0.02), WOOD, WOOD_DARK, -0.25);
      for (const [x0, x1] of [
        [-0.1, 0.02],
        [0.14, 0.02],
      ] as const) {
        g.strokeStyle = INK;
        g.lineWidth = 0.05;
        g.beginPath();
        g.moveTo(x0, -0.17);
        g.lineTo(x1, -0.33);
        g.stroke();
        g.strokeStyle = WOOD;
        g.lineWidth = 0.032;
        g.stroke();
      }
      cel(g, rr(-0.06, -0.24, 0.16, 0.04, 0.01), P.cloth, P.clothDark, -0.02);
      cel(g, ell(0.02, -0.33, 0.03, 0.03), P.trim, P.trimDark, 0.01);
    } else {
      wheel(-0.22, 0.03);
      wheel(0.22, 0.03);
      cel(g, rr(-0.21, -0.21, 0.42, 0.09, 0.02), WOOD, WOOD_DARK, -0.16);
      for (const x of [-0.09, 0.09])
        cel(g, rr(x - 0.02, -0.36, 0.04, 0.16, 0.01), WOOD, WOOD_DARK, x - 0.005);
      cel(g, rr(-0.12, -0.36, 0.24, 0.035, 0.01), WOOD_DARK);
      cel(g, rr(-0.2, -0.165, 0.4, 0.03, 0.01), P.cloth, P.clothDark, -0.15);
    }
  });
}

/** The throwing arm (pivot: the axle). Points along +x at rotation 0. */
function throwArm(): Part {
  return paint(0.56, 0.2, 0.06, 0.1, (g) => {
    cel(g, rr(-0.03, -0.02, 0.44, 0.04, 0.015), WOOD, WOOD_DARK, 0.1);
    cel(
      g,
      (c) => {
        c.beginPath();
        c.arc(0.42, 0, 0.055, Math.PI * 1.1, Math.PI * 1.9 + Math.PI);
        c.closePath();
      },
      WOOD_DARK,
    );
    cel(g, ell(0.42, -0.035, 0.035, 0.032), "#8f8a80", "#6a655c", 0.42);
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
  cape: Part | null;
  mount: Part | null;
  mountFront: Part | null;
  throwArm: Part | null;
}

const artCache = new Map<string, Art>();

function artFor(look: Look, facing: Facing): Art {
  const key = `${look.kind}|${look.side}|${look.hair}|${look.beard}|${look.skin}|${facing}`;
  const hit = artCache.get(key);
  if (hit) return hit;
  const P = SIDES[look.side % SIDES.length] ?? (SIDES[0] as Pal);
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
          legF: k === "cavalry" ? null : leg(P, true, facing),
          legB: k === "cavalry" ? null : leg(P, false, facing),
          body: body(P, k, facing),
          head: head(P, look, facing),
          armF: arm(P, k, true),
          armB: arm(P, k, facing !== "side"),
          weapon: weapon(P, k),
          offhand: offhand(P, k, facing),
          cape: k === "infantry" || k === "cavalry" ? cape(P, facing) : null,
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
    p.throwArm.position.set(f === "side" ? 0.02 : 0, -0.33);
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
    let arm = side ? -2.3 : -Math.PI / 2;
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
          arm = side ? lerp(lerp(-2.3, -2.7, wind), -0.7, strike) : -Math.PI / 2;
          arm = lerp(arm, side ? -2.3 : -Math.PI / 2, back);
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
      p.throwArm.scale.set((1 / U) * (1 - 1.6 * swing), (1 / U) * 0.8);
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

/** How a weapon sits in the hand: the spear lies forward in side view and stands up in front view. */
function weaponRest(kind: Kind, facing: Facing): number {
  if (kind === "cavalry") return facing === "side" ? -1.45 : Math.PI - 0.15;
  if (kind === "strategist") return facing === "side" ? -0.6 : 0.4;
  return facing === "side" ? -0.3 : 0.2;
}
