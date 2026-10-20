/**
 * Stage props (ADR 0063), painted in code as clean, simple sprites in the
 * figures' style (flat fills, a shadow band, an ink line), dressed for the
 * late Eastern Han around 200 AD (docs/remake/CHARACTER_LOOK.md): a low
 * lacquered dais rather than a tall chair, black-and-red lacquer, bronze
 * braziers and oil lamps on stands, hemp canvas tents, timber gates under tiled
 * roofs, two-wheeled carts, ji halberds on the rack. Our own art throughout.
 *
 * A prop stands on its tile: (0, 0) of the drawing is the tile's ground point
 * (the middle of the tile, a little below its centre); units are tiles and y
 * grows downwards, so a pillar reaches up to about y = -2.
 */
import type { PropDef, PropKind } from "@tk/content";
import { Container, Sprite, Texture } from "pixi.js";

/** Texture pixels per tile. */
const PU = 128;
const INK = "#2a1a12";
const LW = 0.02;
const LACQUER = "#2b2421";
const LACQUER_HI = "#4a3c36";
const LACQUER_RED = "#a33328";
const WOOD = "#7a5234";
const WOOD_DARK = "#54371f";
const WOOD_LIGHT = "#9b7048";
const BRONZE = "#c0913e";
const BRONZE_DARK = "#7f5c22";
const STONE = "#aaa395";
const STONE_DARK = "#7c7566";
const CANVAS = "#e4d8ba";
const CANVAS_DARK = "#c2b28d";
const HEMP = "#c2ad80";
const HEMP_DARK = "#97835a";
const ROOF = "#55595e";
const ROOF_DARK = "#3a3d42";
const PILLAR = "#a5352a";
const PILLAR_DARK = "#74221a";
const COAT = "#8a5a34";
const COAT_DARK = "#64401f";
const MANE = "#2a1d14";

/** Army colours as Han dyes, as the figures wear them (ADR 0055). */
const SIDE = [
  { cloth: "#a3322a", dark: "#6b1d18", light: "#d65a4c" },
  { cloth: "#2e4a78", dark: "#1f3354", light: "#7096d4" },
  { cloth: "#3f6b46", dark: "#2a4a30", light: "#72b07b" },
  { cloth: "#a2772c", dark: "#6e501b", light: "#d8aa4c" },
] as const;
const sideOf = (n: number) => SIDE[n % SIDE.length] ?? SIDE[0];

type Path = (g: CanvasRenderingContext2D) => void;
type Draw = (g: CanvasRenderingContext2D) => void;

interface Art {
  tex: Texture;
  ax: number;
  ay: number;
}

/** A drawing `w`×`h` tiles whose ground point is (`px`, `py`) from its top left. */
function art(w: number, h: number, px: number, py: number, draw: Draw): Art {
  const c = document.createElement("canvas");
  c.width = Math.ceil(w * PU);
  c.height = Math.ceil(h * PU);
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  g.translate(px * PU, py * PU);
  g.scale(PU, PU);
  g.lineJoin = "round";
  g.lineCap = "round";
  draw(g);
  return { tex: Texture.from(c), ax: px / w, ay: py / h };
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

/** Flat fill, a shadow band left of `shadeX` (light from the upper right), an ink outline. */
function cel(g: CanvasRenderingContext2D, path: Path, fill: string, shade?: string, shadeX = 0) {
  g.save();
  path(g);
  g.fillStyle = fill;
  g.fill();
  if (shade) {
    g.clip();
    g.fillStyle = shade;
    g.fillRect(-4, -4, 4 + shadeX, 8);
  }
  g.restore();
  g.save();
  path(g);
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke();
  g.restore();
}

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

/** A soft contact shadow on the ground. */
function shadow(g: CanvasRenderingContext2D, x: number, y: number, rx: number, ry: number) {
  g.save();
  g.translate(x, y);
  g.scale(1, ry / rx);
  const grad = g.createRadialGradient(0, 0, 0, 0, 0, rx);
  grad.addColorStop(0, "rgba(0,0,0,0.42)");
  grad.addColorStop(0.65, "rgba(0,0,0,0.22)");
  grad.addColorStop(1, "rgba(0,0,0,0)");
  g.fillStyle = grad;
  g.fillRect(-rx, -rx, rx * 2, rx * 2);
  g.restore();
}

/** A red cloud scroll, as painted on Han lacquer, along a band. */
function cloudScroll(g: CanvasRenderingContext2D, x0: number, x1: number, y: number, r: number) {
  g.strokeStyle = LACQUER_RED;
  g.lineWidth = r * 0.35;
  for (let x = x0 + r; x < x1 - r * 0.5; x += r * 2.6) {
    g.beginPath();
    g.arc(x, y, r * 0.7, Math.PI, Math.PI * 2.6);
    g.stroke();
    g.beginPath();
    g.moveTo(x + r * 0.7, y);
    g.quadraticCurveTo(x + r * 1.3, y + r * 0.8, x + r * 1.9, y);
    g.stroke();
  }
}

// ---------------------------------------------------------------- the props

/** A low lacquered dais (榻) with a cushion, an armrest and a low back rail. */
function throne(): Art {
  return art(1.5, 1.5, 0.75, 1.2, (g) => {
    shadow(g, 0, -0.04, 0.68, 0.17);
    // The low back rail.
    cel(g, rr(-0.56, -0.98, 1.12, 0.36, 0.04), LACQUER, LACQUER_HI, -0.2);
    inside(g, rr(-0.56, -0.98, 1.12, 0.36, 0.04), () => {
      g.fillStyle = "#3a2c26";
      g.fillRect(-0.5, -0.93, 1.0, 0.24);
      cloudScroll(g, -0.48, 0.48, -0.81, 0.07);
    });
    cel(g, rr(-0.6, -1.02, 0.08, 0.42, 0.02), LACQUER, LACQUER_HI, -0.58);
    cel(g, rr(0.52, -1.02, 0.08, 0.42, 0.02), LACQUER, LACQUER_HI, 0.54);
    // The platform's top and front.
    cel(g, poly(-0.6, -0.62, 0.6, -0.62, 0.64, -0.24, -0.64, -0.24), "#3a2e28", LACQUER, -0.4);
    inside(g, poly(-0.6, -0.62, 0.6, -0.62, 0.64, -0.24, -0.64, -0.24), () => {
      line(
        g,
        LACQUER_RED,
        0.025,
        -0.56,
        -0.58,
        0.56,
        -0.58,
        0.59,
        -0.28,
        -0.59,
        -0.28,
        -0.56,
        -0.58,
      );
    });
    cel(g, rr(-0.64, -0.24, 1.28, 0.2, 0.02), LACQUER, undefined);
    inside(g, rr(-0.64, -0.24, 1.28, 0.2, 0.02), () => {
      g.fillStyle = "#3a2c26";
      g.fillRect(-0.6, -0.2, 1.2, 0.12);
      cloudScroll(g, -0.58, 0.58, -0.14, 0.05);
    });
    for (const x of [-0.62, 0.55])
      cel(g, rr(x, -0.06, 0.07, 0.07, 0.01), BRONZE, BRONZE_DARK, x + 0.02);
    // A silk cushion and the curved armrest (凭几) before it.
    cel(g, rr(-0.32, -0.6, 0.64, 0.26, 0.08), "#8e3a2a", "#6a2a1e", -0.15);
    inside(g, rr(-0.32, -0.6, 0.64, 0.26, 0.08), () => {
      line(g, "#e0b860", 0.018, -0.28, -0.56, 0.28, -0.56);
      line(g, "#e0b860", 0.018, -0.28, -0.38, 0.28, -0.38);
    });
    g.lineWidth = 0.05;
    g.strokeStyle = LACQUER;
    g.beginPath();
    g.moveTo(-0.3, -0.3);
    g.quadraticCurveTo(0, -0.4, 0.3, -0.3);
    g.stroke();
    for (const x of [-0.26, 0.26]) cel(g, rr(x - 0.015, -0.32, 0.03, 0.09, 0.01), LACQUER);
  });
}

/** A low lacquered table (案), black with a red rim; slips and a cup, or a map. */
function table(variant: number, joinL: boolean, joinR: boolean): Art {
  return art(1.1, 1.0, 0.55, 0.75, (g) => {
    const l = joinL ? -0.55 : -0.46;
    const r = joinR ? 0.55 : 0.46;
    shadow(g, 0, -0.02, 0.52, 0.12);
    // Legs (front pair), then the top and its edge.
    for (const x of [l + 0.06, r - 0.1]) {
      if ((x < 0 && joinL) || (x > 0 && joinR)) continue;
      cel(g, rr(x, -0.26, 0.04, 0.24, 0.01), LACQUER, undefined);
    }
    cel(
      g,
      poly(l + 0.02, -0.56, r - 0.02, -0.56, r, -0.32, l, -0.32),
      "#352a25",
      LACQUER,
      l + 0.12,
    );
    inside(g, poly(l + 0.02, -0.56, r - 0.02, -0.56, r, -0.32, l, -0.32), () => {
      const a = joinL ? -0.6 : l + 0.04;
      const b = joinR ? 0.6 : r - 0.04;
      line(g, LACQUER_RED, 0.018, a, -0.535, b, -0.535);
      line(g, LACQUER_RED, 0.018, a, -0.345, b, -0.345);
      if (!joinL) line(g, LACQUER_RED, 0.018, l + 0.05, -0.535, l + 0.035, -0.345);
      if (!joinR) line(g, LACQUER_RED, 0.018, r - 0.05, -0.535, r - 0.035, -0.345);
    });
    cel(g, rr(l, -0.32, r - l, 0.06, 0.01), LACQUER_RED, "#7a241c", l + 0.1);
    if (variant % 2 === 0) {
      // A silk map unrolled, held flat by two weights.
      cel(g, poly(-0.3, -0.52, 0.32, -0.52, 0.34, -0.36, -0.32, -0.36), "#e6d9b4", "#cbbb92", -0.2);
      inside(g, poly(-0.3, -0.52, 0.32, -0.52, 0.34, -0.36, -0.32, -0.36), () => {
        line(g, "#5a7aa0", 0.012, -0.3, -0.42, -0.1, -0.47, 0.08, -0.4, 0.33, -0.46);
        line(g, "#8a6a44", 0.01, -0.15, -0.52, -0.05, -0.36);
        g.fillStyle = "#a33328";
        for (const [x, y] of [
          [-0.08, -0.43],
          [0.18, -0.48],
        ] as const) {
          g.beginPath();
          g.arc(x, y, 0.018, 0, Math.PI * 2);
          g.fill();
        }
      });
      for (const x of [-0.3, 0.31]) cel(g, ell(x, -0.44, 0.03, 0.022), BRONZE, BRONZE_DARK, x);
    } else {
      // Bound bamboo slips and an eared wine cup (耳杯), red inside.
      cel(g, rr(-0.32, -0.52, 0.3, 0.12, 0.03), "#cdb77d", "#a8935c", -0.25);
      inside(g, rr(-0.32, -0.52, 0.3, 0.12, 0.03), () => {
        for (let x = -0.3; x < -0.03; x += 0.035)
          line(g, "rgba(90,70,30,0.6)", 0.006, x, -0.52, x, -0.4);
        line(g, "#6b1d18", 0.012, -0.32, -0.46, -0.02, -0.46);
      });
      cel(g, ell(0.18, -0.44, 0.1, 0.05), LACQUER);
      cel(g, ell(0.18, -0.445, 0.07, 0.03), LACQUER_RED);
      cel(g, ell(0.075, -0.445, 0.025, 0.018), LACQUER);
      cel(g, ell(0.285, -0.445, 0.025, 0.018), LACQUER);
    }
  });
}

/** A red lacquered column on a stone base, with a timber bracket under the beam. */
function pillar(): Art {
  return art(0.8, 2.4, 0.4, 2.25, (g) => {
    shadow(g, 0, -0.02, 0.3, 0.1);
    cel(g, ell(0, -0.06, 0.24, 0.1), STONE, STONE_DARK, -0.08);
    cel(g, rr(-0.24, -0.12, 0.48, 0.07, 0.02), STONE, STONE_DARK, -0.1);
    cel(g, ell(0, -0.13, 0.22, 0.08), "#bdb6a6", STONE, -0.06);
    cel(g, rr(-0.15, -2.0, 0.3, 1.9, 0.03), PILLAR, PILLAR_DARK, -0.06);
    inside(g, rr(-0.15, -2.0, 0.3, 1.9, 0.03), () => {
      g.fillStyle = "rgba(255,190,160,0.25)";
      g.fillRect(0.04, -2.0, 0.04, 1.9);
    });
    // The bracket: a block and two arms (斗拱), painted green-blue and gold.
    cel(g, rr(-0.34, -2.12, 0.68, 0.08, 0.02), WOOD, WOOD_DARK, -0.25);
    cel(g, rr(-0.12, -2.06, 0.24, 0.1, 0.02), "#3f6b6a", "#2d4f4e", -0.06);
    for (const x of [-0.3, 0.22])
      cel(g, rr(x, -2.2, 0.08, 0.09, 0.02), "#3f6b6a", "#2d4f4e", x + 0.02);
    line(g, "#e0b860", 0.012, -0.12, -2.01, 0.12, -2.01);
  });
}

/** A banner: a tall pole with a spear-tip finial, a streamer in the army's colours. */
function bannerPole(side: number): Art {
  const P = sideOf(side);
  return art(0.7, 2.6, 0.35, 2.45, (g) => {
    shadow(g, 0, -0.02, 0.22, 0.08);
    // A stand of crossed timbers weighed with stones.
    cel(g, poly(-0.2, -0.02, 0.2, -0.02, 0.06, -0.12, -0.06, -0.12), WOOD, WOOD_DARK, -0.1);
    cel(g, ell(-0.15, -0.03, 0.06, 0.04), STONE, STONE_DARK, -0.16);
    cel(g, ell(0.14, -0.03, 0.06, 0.04), STONE, STONE_DARK, 0.12);
    cel(g, rr(-0.025, -2.2, 0.05, 2.15, 0.02), WOOD, WOOD_DARK, -0.01);
    cel(g, poly(-0.035, -2.2, 0.035, -2.2, 0, -2.36), BRONZE, BRONZE_DARK, -0.01);
    // The crossbar the streamer hangs from, and a tassel.
    cel(g, rr(-0.03, -2.14, 0.3, 0.035, 0.01), WOOD, WOOD_DARK, 0);
    cel(g, ell(0, -2.15, 0.045, 0.035), P.light, P.dark, -0.02);
  });
}

/** The streamer itself (a separate sprite so it can stir in the wind). */
function bannerFlag(side: number): Art {
  const P = sideOf(side);
  return art(0.4, 1.25, 0.02, 0.02, (g) => {
    const flag = poly(0, 0, 0.3, 0, 0.3, 1.05, 0.15, 0.95, 0, 1.05);
    cel(g, flag, P.cloth, P.dark, 0.06);
    inside(g, flag, () => {
      g.fillStyle = "#e8d8a8";
      g.fillRect(0, 0, 0.3, 0.045);
      g.fillRect(0.255, 0, 0.045, 1.1);
      // A pale disc with a dark mark: the army's device.
      g.fillStyle = "#efe2bd";
      g.beginPath();
      g.arc(0.14, 0.3, 0.09, 0, Math.PI * 2);
      g.fill();
      g.fillStyle = P.dark;
      g.fillRect(0.1, 0.27, 0.08, 0.016);
      g.fillRect(0.132, 0.24, 0.016, 0.12);
      g.fillRect(0.105, 0.32, 0.07, 0.016);
      g.fillStyle = "rgba(255,255,255,0.12)";
      g.fillRect(0.04, 0.5, 0.18, 0.025);
      g.fillRect(0.04, 0.62, 0.18, 0.025);
    });
  });
}

/** A hemp canvas tent with a hipped roof, the door open, bands in the army's colours. */
function tent(side: number): Art {
  const P = sideOf(side);
  return art(2.0, 1.9, 1.0, 1.55, (g) => {
    shadow(g, 0, -0.08, 0.95, 0.22);
    // Guy ropes and pegs.
    line(g, "#6b5a40", 0.012, -0.86, -0.7, -0.98, -0.05);
    line(g, "#6b5a40", 0.012, 0.86, -0.7, 0.98, -0.05);
    cel(g, rr(-1.0, -0.08, 0.04, 0.06, 0.01), WOOD, WOOD_DARK);
    cel(g, rr(0.96, -0.08, 0.04, 0.06, 0.01), WOOD, WOOD_DARK);
    // The front wall and the door.
    const wall = poly(-0.82, -0.72, 0.82, -0.72, 0.84, -0.06, -0.84, -0.06);
    cel(g, wall, CANVAS, CANVAS_DARK, -0.55);
    inside(g, wall, () => {
      line(g, "rgba(120,100,70,0.35)", 0.01, -0.45, -0.72, -0.45, -0.06);
      line(g, "rgba(120,100,70,0.35)", 0.01, 0.45, -0.72, 0.45, -0.06);
      g.fillStyle = P.cloth;
      g.fillRect(-0.9, -0.16, 1.8, 0.07);
    });
    cel(g, poly(-0.2, -0.06, -0.17, -0.6, 0.17, -0.6, 0.2, -0.06), "#2a2018", undefined);
    inside(g, poly(-0.2, -0.06, -0.17, -0.6, 0.17, -0.6, 0.2, -0.06), () => {
      const glow = g.createLinearGradient(0, -0.06, 0, -0.6);
      glow.addColorStop(0, "rgba(255,190,110,0.25)");
      glow.addColorStop(1, "rgba(0,0,0,0)");
      g.fillStyle = glow;
      g.fillRect(-0.2, -0.6, 0.4, 0.54);
    });
    // Door flaps tied back.
    cel(g, poly(-0.17, -0.6, -0.34, -0.6, -0.36, -0.28, -0.2, -0.4), CANVAS_DARK, "#a8996f", -0.3);
    cel(g, poly(0.17, -0.6, 0.34, -0.6, 0.36, -0.28, 0.2, -0.4), CANVAS, CANVAS_DARK, 0.18);
    // The roof: a hipped canvas roof, the ridge shorter than the eaves.
    const roof = poly(-0.52, -1.38, 0.52, -1.38, 0.96, -0.7, -0.96, -0.7);
    cel(g, roof, "#d6c9a6", "#b5a57d", -0.4);
    inside(g, roof, () => {
      for (const x of [-0.25, 0, 0.25])
        line(g, "rgba(110,90,60,0.3)", 0.01, x * 0.95, -1.38, x * 1.7, -0.7);
      g.fillStyle = P.cloth;
      g.fillRect(-1, -0.8, 2, 0.08);
    });
    cel(g, rr(-0.56, -1.42, 1.12, 0.06, 0.02), P.cloth, P.dark, -0.3);
    // A pennant on the ridge pole.
    cel(g, rr(0.38, -1.75, 0.025, 0.36, 0.01), WOOD, WOOD_DARK);
    cel(g, poly(0.405, -1.74, 0.6, -1.68, 0.405, -1.6), P.light, P.cloth, 0.45);
  });
}

/** A bronze brazier on three legs, coals glowing in the bowl. */
function brazier(): Art {
  return art(0.8, 1.1, 0.4, 0.95, (g) => {
    shadow(g, 0, -0.02, 0.28, 0.09);
    for (const [x0, x1] of [
      [-0.17, -0.24],
      [0.17, 0.24],
      [0, 0],
    ] as const)
      cel(
        g,
        poly(x0 - 0.02, -0.42, x0 + 0.02, -0.42, x1 + 0.025, -0.02, x1 - 0.015, -0.02),
        BRONZE_DARK,
      );
    const bowl: Path = (c) => {
      c.beginPath();
      c.moveTo(-0.27, -0.6);
      c.quadraticCurveTo(-0.24, -0.36, 0, -0.34);
      c.quadraticCurveTo(0.24, -0.36, 0.27, -0.6);
      c.closePath();
    };
    cel(g, bowl, BRONZE, BRONZE_DARK, -0.08);
    inside(g, bowl, () => {
      line(g, "rgba(60,40,10,0.5)", 0.015, -0.26, -0.52, 0.26, -0.52);
      for (let x = -0.2; x <= 0.2; x += 0.1)
        line(g, "rgba(60,40,10,0.35)", 0.01, x, -0.5, x * 0.9, -0.38);
    });
    cel(g, ell(0, -0.6, 0.27, 0.07), "#3a1e10");
    inside(g, ell(0, -0.6, 0.27, 0.07), () => {
      const coal = g.createRadialGradient(0, -0.6, 0, 0, -0.6, 0.26);
      coal.addColorStop(0, "#ffd27a");
      coal.addColorStop(0.45, "#ff7a2f");
      coal.addColorStop(1, "#5a1a08");
      g.fillStyle = coal;
      g.fillRect(-0.3, -0.7, 0.6, 0.2);
    });
    for (const x of [-0.3, 0.3]) cel(g, ell(x, -0.58, 0.04, 0.03), BRONZE, BRONZE_DARK);
  });
}

/** A folding screen (屏風): three lacquered panels painted with cloud scrolls. */
function screen(): Art {
  return art(1.4, 1.7, 0.7, 1.45, (g) => {
    shadow(g, 0, -0.04, 0.62, 0.12);
    const panels: [number, number, number][] = [
      [-0.62, -0.2, -0.06],
      [-0.2, 0.2, 0],
      [0.2, 0.62, -0.06],
    ];
    for (const [a, b, dy] of panels) {
      const p = poly(a, -1.25 + dy, b, -1.25 - dy, b, -0.1 - dy, a, -0.1 + dy);
      cel(g, p, LACQUER, undefined);
      const q = poly(
        a + 0.05,
        -1.19 + dy,
        b - 0.05,
        -1.19 - dy,
        b - 0.05,
        -0.17 - dy,
        a + 0.05,
        -0.17 + dy,
      );
      cel(g, q, "#d9c9a0", "#c1ae82", a + 0.12);
      inside(g, q, () => {
        g.strokeStyle = LACQUER_RED;
        g.lineWidth = 0.025;
        for (let i = 0; i < 3; i++) {
          const y = -0.95 + i * 0.3 + dy * 0.5;
          const cx = (a + b) / 2;
          g.beginPath();
          g.arc(cx - 0.06, y, 0.07, Math.PI * 0.9, Math.PI * 2.4);
          g.stroke();
          g.beginPath();
          g.moveTo(cx, y + 0.02);
          g.quadraticCurveTo(cx + 0.1, y + 0.1, cx + 0.14, y - 0.02);
          g.stroke();
        }
        line(g, "#3a5a48", 0.02, a + 0.05, -0.32, b - 0.05, -0.38);
      });
    }
    for (const x of [-0.6, -0.02, 0.56]) cel(g, rr(x, -0.12, 0.06, 0.1, 0.01), LACQUER);
  });
}

/** A bronze oil lamp on a tall stand (燈). */
function lantern(): Art {
  return art(0.6, 1.7, 0.3, 1.55, (g) => {
    shadow(g, 0, -0.02, 0.18, 0.06);
    cel(g, ell(0, -0.05, 0.14, 0.05), BRONZE, BRONZE_DARK, -0.04);
    cel(g, poly(-0.13, -0.05, 0.13, -0.05, 0.05, -0.16, -0.05, -0.16), BRONZE, BRONZE_DARK, -0.03);
    cel(g, rr(-0.022, -1.2, 0.044, 1.06, 0.02), BRONZE, BRONZE_DARK, -0.005);
    cel(g, ell(0, -0.7, 0.045, 0.025), BRONZE, BRONZE_DARK);
    cel(g, poly(-0.15, -1.24, 0.15, -1.24, 0.08, -1.17, -0.08, -1.17), BRONZE, BRONZE_DARK, -0.05);
    cel(g, ell(0, -1.24, 0.15, 0.04), "#5a3a14");
  });
}

/** A gateway under a tiled roof, the door leaves standing open. */
function gate(joinL: boolean, joinR: boolean): Art {
  return art(1.3, 2.3, 0.65, 2.0, (g) => {
    const l = -0.5;
    const r = 0.5;
    // Open door leaves, studded with bronze, seen edge-on against the posts.
    for (const [x, open] of [
      [l + 0.09, !joinL],
      [r - 0.17, !joinR],
    ] as const) {
      if (!open) continue;
      cel(g, rr(x, -1.18, 0.08, 1.12, 0.01), WOOD_DARK, "#3e2814", x + 0.03);
      for (let y = -1.05; y < -0.1; y += 0.2) cel(g, ell(x + 0.04, y, 0.014, 0.012), BRONZE);
    }
    // Posts.
    if (!joinL) cel(g, rr(l, -1.32, 0.11, 1.3, 0.02), PILLAR, PILLAR_DARK, l + 0.04);
    if (!joinR) cel(g, rr(r - 0.11, -1.32, 0.11, 1.3, 0.02), PILLAR, PILLAR_DARK, r - 0.07);
    for (const x of [joinL ? null : l - 0.02, joinR ? null : r - 0.13])
      if (x !== null) cel(g, rr(x, -0.08, 0.15, 0.07, 0.02), STONE, STONE_DARK, x + 0.05);
    // The lintel and the roof.
    const a = joinL ? -0.66 : l - 0.06;
    const b = joinR ? 0.66 : r + 0.06;
    cel(g, rr(a, -1.4, b - a, 0.12, 0.02), WOOD, WOOD_DARK, a + 0.2);
    inside(g, rr(a, -1.4, b - a, 0.12, 0.02), () => cloudScroll(g, a, b, -1.34, 0.035));
    const ra = joinL ? -0.66 : l - 0.16;
    const rb = joinR ? 0.66 : r + 0.16;
    const roof = poly(
      ra + (joinL ? 0 : 0.14),
      -1.82,
      rb - (joinR ? 0 : 0.14),
      -1.82,
      rb,
      -1.42,
      ra,
      -1.42,
    );
    cel(g, roof, ROOF, ROOF_DARK, ra + 0.3);
    inside(g, roof, () => {
      for (let x = -0.7; x <= 0.7; x += 0.09)
        line(g, "rgba(20,22,26,0.45)", 0.012, x, -1.82, x * 1.1, -1.42);
      g.fillStyle = "rgba(255,255,255,0.08)";
      g.fillRect(-1, -1.82, 2, 0.05);
    });
    cel(g, rr(ra - 0.02, -1.48, rb - ra + 0.04, 0.07, 0.02), ROOF_DARK);
    cel(
      g,
      rr(
        ra + (joinL ? 0 : 0.1),
        -1.88,
        rb - ra - (joinL ? 0 : 0.1) - (joinR ? 0 : 0.1),
        0.07,
        0.03,
      ),
      ROOF_DARK,
    );
    for (const x of [joinL ? null : ra + 0.1, joinR ? null : rb - 0.1])
      if (x !== null)
        cel(
          g,
          poly(x - 0.05, -1.86, x + 0.05, -1.86, x + (x < 0 ? -0.08 : 0.08), -1.97),
          ROOF_DARK,
        );
  });
}

/** A standing horse, saddled, without a rider (no stirrups, as in the period). */
function horse(side: number): Art {
  const P = sideOf(side);
  return art(1.6, 1.3, 0.8, 1.05, (g) => {
    shadow(g, 0, -0.03, 0.6, 0.13);
    // Far legs, darker.
    for (const x of [-0.36, 0.3]) cel(g, rr(x, -0.42, 0.07, 0.4, 0.03), COAT_DARK);
    // Tail, knotted.
    cel(g, poly(-0.52, -0.66, -0.6, -0.6, -0.66, -0.3, -0.58, -0.34, -0.5, -0.56), MANE);
    // Body.
    cel(g, ell(-0.04, -0.6, 0.48, 0.2), COAT, COAT_DARK, -0.3);
    // Neck and head.
    cel(
      g,
      poly(0.26, -0.72, 0.38, -0.98, 0.5, -1.02, 0.46, -0.78, 0.4, -0.55),
      COAT,
      COAT_DARK,
      0.3,
    );
    cel(
      g,
      poly(0.38, -1.04, 0.52, -1.06, 0.72, -0.9, 0.7, -0.82, 0.6, -0.82, 0.44, -0.88),
      COAT,
      COAT_DARK,
      0.45,
    );
    cel(g, poly(0.33, -0.98, 0.46, -1.04, 0.4, -0.78, 0.3, -0.7), MANE);
    cel(g, poly(0.43, -1.04, 0.45, -1.13, 0.49, -1.05), COAT_DARK);
    cel(g, ell(0.53, -0.95, 0.016, 0.016), "#111");
    cel(g, ell(0.62, -0.92, 0.035, 0.03), BRONZE, BRONZE_DARK);
    line(g, LACQUER, 0.014, 0.5, -0.88, 0.7, -0.86);
    // The saddle with flaps in the army's colour.
    cel(g, rr(-0.22, -0.82, 0.36, 0.1, 0.04), LACQUER, LACQUER_HI, -0.1);
    cel(g, rr(-0.2, -0.74, 0.32, 0.2, 0.04), P.cloth, P.dark, -0.12);
    inside(g, rr(-0.2, -0.74, 0.32, 0.2, 0.04), () => {
      g.fillStyle = "#e8d8a8";
      g.fillRect(-0.2, -0.57, 0.32, 0.025);
    });
    // Near legs.
    for (const x of [-0.3, 0.24]) {
      cel(g, rr(x, -0.44, 0.08, 0.42, 0.03), COAT, COAT_DARK, x + 0.03);
      cel(g, rr(x - 0.005, -0.05, 0.09, 0.05, 0.015), "#2b2420");
    }
  });
}

/** A two-wheeled supply cart with sacks under a cord. */
function cart(): Art {
  return art(1.7, 1.3, 0.85, 1.05, (g) => {
    shadow(g, 0, -0.04, 0.68, 0.14);
    // Shafts resting on the ground.
    line(g, WOOD_DARK, 0.04, 0.3, -0.42, 0.78, -0.04);
    // The bed and its load.
    cel(g, rr(-0.6, -0.56, 1.0, 0.14, 0.02), WOOD, WOOD_DARK, -0.4);
    inside(g, rr(-0.6, -0.56, 1.0, 0.14, 0.02), () => {
      for (let x = -0.5; x < 0.4; x += 0.15)
        line(g, "rgba(50,30,14,0.5)", 0.01, x, -0.56, x, -0.42);
    });
    for (const [x, y, w] of [
      [-0.55, -0.8, 0.34],
      [-0.22, -0.82, 0.34],
      [0.08, -0.78, 0.3],
      [-0.4, -0.98, 0.32],
      [-0.08, -0.98, 0.3],
    ] as const)
      cel(g, rr(x, y, w, 0.26, 0.09), HEMP, HEMP_DARK, x + 0.08);
    line(g, "#6b5a40", 0.014, -0.58, -0.62, -0.25, -0.98, 0.1, -0.98, 0.36, -0.6);
    // The great spoked wheel.
    cel(g, ell(-0.1, -0.33, 0.3, 0.3), WOOD_DARK);
    cel(g, ell(-0.1, -0.33, 0.24, 0.24), "#4a3a2a");
    g.save();
    g.beginPath();
    g.ellipse(-0.1, -0.33, 0.24, 0.24, 0, 0, Math.PI * 2);
    g.clip();
    g.fillStyle = "rgba(0,0,0,0)";
    for (let i = 0; i < 10; i++) {
      const a = (i / 10) * Math.PI * 2;
      line(
        g,
        WOOD_LIGHT,
        0.022,
        -0.1,
        -0.33,
        -0.1 + Math.cos(a) * 0.24,
        -0.33 + Math.sin(a) * 0.24,
      );
    }
    g.restore();
    cel(g, ell(-0.1, -0.33, 0.06, 0.06), BRONZE, BRONZE_DARK);
  });
}

/** Hemp sacks of grain, stacked, with a pottery jar. */
function grain(): Art {
  return art(1.1, 1.0, 0.55, 0.8, (g) => {
    shadow(g, 0, -0.04, 0.48, 0.12);
    const sack = (x: number, y: number, w: number, h: number) => {
      cel(g, rr(x, y, w, h, 0.1), HEMP, HEMP_DARK, x + w * 0.3);
      cel(
        g,
        poly(x + w * 0.35, y + 0.02, x + w * 0.5, y - 0.07, x + w * 0.65, y + 0.02),
        HEMP_DARK,
      );
      line(g, "#6b5a40", 0.014, x + w * 0.36, y + 0.03, x + w * 0.64, y + 0.03);
    };
    sack(-0.44, -0.36, 0.32, 0.34);
    sack(-0.14, -0.38, 0.32, 0.36);
    sack(-0.3, -0.64, 0.32, 0.32);
    // A storage jar.
    const jar: Path = (c) => {
      c.beginPath();
      c.moveTo(0.24, -0.5);
      c.quadraticCurveTo(0.12, -0.44, 0.16, -0.22);
      c.quadraticCurveTo(0.2, -0.02, 0.32, -0.02);
      c.quadraticCurveTo(0.44, -0.02, 0.48, -0.22);
      c.quadraticCurveTo(0.52, -0.44, 0.4, -0.5);
      c.closePath();
    };
    cel(g, jar, "#9a6b44", "#73492b", 0.26);
    cel(g, ell(0.32, -0.5, 0.08, 0.03), "#5a3a22");
  });
}

/** A weapon rack: ji halberds and spears standing in a timber frame. */
function rack(): Art {
  return art(1.1, 2.0, 0.55, 1.8, (g) => {
    shadow(g, 0, -0.03, 0.46, 0.1);
    for (const x of [-0.44, 0.38])
      cel(g, rr(x, -0.95, 0.06, 0.93, 0.02), WOOD, WOOD_DARK, x + 0.02);
    cel(g, rr(-0.46, -0.94, 0.92, 0.05, 0.02), WOOD, WOOD_DARK, -0.3);
    cel(g, rr(-0.46, -0.24, 0.92, 0.05, 0.02), WOOD, WOOD_DARK, -0.3);
    for (const [i, x] of [-0.3, -0.12, 0.06, 0.24].entries()) {
      cel(g, rr(x - 0.012, -1.55, 0.024, 1.5, 0.01), WOOD_DARK);
      if (i % 2 === 0) {
        // The ji: a spear point with a side blade.
        cel(g, poly(x - 0.025, -1.55, x + 0.025, -1.55, x, -1.72), "#d9e0e7", "#8f9aa6", x);
        cel(
          g,
          poly(x + 0.012, -1.5, x + 0.12, -1.53, x + 0.13, -1.47, x + 0.012, -1.45),
          "#d9e0e7",
          "#8f9aa6",
          x + 0.05,
        );
      } else {
        cel(g, poly(x - 0.02, -1.55, x + 0.02, -1.55, x, -1.68), "#d9e0e7", "#8f9aa6", x);
        cel(g, ell(x, -1.52, 0.03, 0.025), "#b5382b");
      }
    }
  });
}

/** A well: a stone curb, a timber frame and a pulley with its rope and bucket. */
function well(): Art {
  return art(1.1, 1.7, 0.55, 1.5, (g) => {
    shadow(g, 0, -0.03, 0.44, 0.12);
    for (const x of [-0.38, 0.32]) cel(g, rr(x, -1.15, 0.06, 0.9, 0.02), WOOD, WOOD_DARK, x + 0.02);
    // The curb: its top ring, the dark water inside, and its front.
    cel(g, ell(0, -0.42, 0.38, 0.13), STONE, STONE_DARK, -0.2);
    cel(g, ell(0, -0.42, 0.28, 0.08), "#1d2a33");
    inside(g, ell(0, -0.42, 0.28, 0.08), () => {
      g.fillStyle = "rgba(140,190,220,0.35)";
      g.fillRect(0.02, -0.46, 0.14, 0.02);
    });
    const front: Path = (c) => {
      c.beginPath();
      c.moveTo(-0.38, -0.42);
      c.lineTo(-0.38, -0.1);
      c.quadraticCurveTo(0, 0.04, 0.38, -0.1);
      c.lineTo(0.38, -0.42);
      c.quadraticCurveTo(0, -0.28, -0.38, -0.42);
      c.closePath();
    };
    cel(g, front, STONE, STONE_DARK, -0.2);
    inside(g, front, () => {
      for (const y of [-0.3, -0.18]) line(g, "rgba(60,56,48,0.4)", 0.01, -0.4, y, 0.4, y + 0.02);
      for (const x of [-0.2, 0.05, 0.25]) line(g, "rgba(60,56,48,0.35)", 0.01, x, -0.36, x, -0.25);
    });
    // Crossbeam, pulley and rope.
    cel(g, rr(-0.42, -1.2, 0.84, 0.06, 0.02), WOOD, WOOD_DARK, -0.3);
    cel(g, ell(0, -1.1, 0.07, 0.07), WOOD_DARK);
    line(g, "#8a7650", 0.012, 0.06, -1.1, 0.06, -0.62);
    cel(g, rr(0.01, -0.66, 0.1, 0.1, 0.02), WOOD, WOOD_DARK, 0.04);
    // A small tiled roof over the frame.
    cel(g, poly(-0.38, -1.4, 0.38, -1.4, 0.5, -1.2, -0.5, -1.2), ROOF, ROOF_DARK, -0.15);
  });
}

/**
 * A palisade of sharpened stakes lashed to rails. It joins its neighbours left
 * and right (a run across the stage) and up and down (a run toward the
 * viewer, seen edge-on as a file of stakes), and turns corners.
 */
function fence(joinL: boolean, joinR: boolean, joinU: boolean, joinD: boolean): Art {
  return art(1.1, 2.0, 0.55, 1.78, (g) => {
    const stake = (x: number, y: number, h: number, w = 0.045) =>
      cel(
        g,
        poly(x - w, y, x + w, y, x + w, y - h + w * 1.3, x, y - h, x - w, y - h + w * 1.3),
        WOOD,
        WOOD_DARK,
        x - w * 0.2,
      );
    const across = joinL || joinR || !(joinU || joinD);
    const along = joinU || joinD;
    if (along) {
      // A run toward the viewer, seen end-on: a file of stout stakes down the
      // middle of the tile, staggered as in a double palisade, from its back
      // edge (or the middle) to its front edge (or the middle).
      const y0 = joinU ? -0.84 : -0.36;
      const y1 = joinD ? 0.16 : -0.02;
      shadow(g, 0.03, (y0 + y1) / 2, 0.16, (y1 - y0) / 2 + 0.05);
      let i = 0;
      for (let y = y0; y <= y1 + 0.001; y += 0.13) {
        stake(i % 2 === 0 ? -0.035 : 0.035, y, 0.66, 0.075);
        i += 1;
      }
      cel(g, rr(-0.11, y1 - 0.42, 0.22, 0.045, 0.015), WOOD_DARK);
      cel(g, rr(-0.11, y1 - 0.2, 0.22, 0.045, 0.015), WOOD_DARK);
    }
    if (across) {
      shadow(g, 0, -0.04, 0.5, 0.06);
      const a = joinL ? -0.55 : along ? -0.02 : -0.42;
      const b = joinR ? 0.55 : along ? 0.02 : 0.42;
      for (let x = a + 0.05; x < b - 0.02; x += 0.13) stake(x, -0.02, 0.72 + ((x * 97) % 0.06));
      if (b - a > 0.1) {
        cel(g, rr(a, -0.5, b - a, 0.05, 0.02), WOOD_DARK);
        cel(g, rr(a, -0.24, b - a, 0.05, 0.02), WOOD_DARK);
      }
    }
  });
}

/** A lacquered chest (笥), black with red bands and bronze fittings. */
function chest(): Art {
  return art(0.9, 0.9, 0.45, 0.72, (g) => {
    shadow(g, 0, -0.03, 0.36, 0.1);
    cel(g, poly(-0.32, -0.52, 0.32, -0.52, 0.36, -0.38, -0.36, -0.38), LACQUER_HI, LACQUER, -0.2);
    cel(g, rr(-0.36, -0.38, 0.72, 0.34, 0.03), LACQUER, undefined);
    inside(g, rr(-0.36, -0.38, 0.72, 0.34, 0.03), () => {
      g.fillStyle = LACQUER_RED;
      g.fillRect(-0.4, -0.33, 0.8, 0.035);
      g.fillRect(-0.4, -0.12, 0.8, 0.035);
      cloudScroll(g, -0.3, 0.3, -0.22, 0.04);
    });
    cel(g, rr(-0.05, -0.4, 0.1, 0.1, 0.02), BRONZE, BRONZE_DARK, -0.02);
    for (const x of [-0.36, 0.3]) cel(g, rr(x, -0.06, 0.06, 0.05, 0.01), BRONZE, BRONZE_DARK);
  });
}

/**
 * A stone statue of an official on a stepped plinth: an ancestor's or a
 * worthy's likeness, as stood before Han shrines and tombs. He wears the
 * scholar-official's cap (進賢冠) and a long robe with wide sleeves, his hands
 * joined before him on a tablet.
 */
function statue(): Art {
  const LIGHT = "#c3bcad";
  return art(1.0, 1.9, 0.5, 1.75, (g) => {
    shadow(g, 0, -0.03, 0.38, 0.1);
    // The plinth: a broad step and a block.
    cel(g, poly(-0.36, -0.14, 0.36, -0.14, 0.38, -0.03, -0.38, -0.03), STONE, STONE_DARK, -0.22);
    cel(g, poly(-0.33, -0.2, 0.33, -0.2, 0.36, -0.14, -0.36, -0.14), LIGHT, STONE, -0.2);
    cel(g, rr(-0.26, -0.36, 0.52, 0.17, 0.01), STONE, STONE_DARK, -0.14);
    cel(g, poly(-0.24, -0.4, 0.24, -0.4, 0.27, -0.36, -0.27, -0.36), LIGHT, STONE, -0.12);
    g.fillStyle = "rgba(90,110,70,0.35)";
    for (const [x, y] of [
      [-0.3, -0.07],
      [0.22, -0.05],
      [-0.18, -0.2],
    ] as const) {
      g.beginPath();
      g.arc(x, y, 0.025, 0, Math.PI * 2);
      g.fill();
    }
    // The long robe, flaring a little to the hem, with carved folds.
    const robe = poly(-0.15, -1.1, 0.15, -1.1, 0.19, -0.6, 0.21, -0.4, -0.21, -0.4, -0.19, -0.6);
    cel(g, robe, STONE, STONE_DARK, -0.06);
    inside(g, robe, () => {
      for (const x of [-0.1, -0.03, 0.05, 0.12])
        line(g, "rgba(70,64,54,0.35)", 0.01, x * 0.9, -0.74, x * 1.15, -0.41);
      line(g, "rgba(70,64,54,0.5)", 0.014, -0.17, -0.74, 0.17, -0.74);
    });
    // Wide sleeves hanging from the shoulders, meeting before the chest.
    cel(g, rr(-0.24, -1.07, 0.14, 0.42, 0.06), STONE, STONE_DARK, -0.2);
    cel(g, rr(0.1, -1.07, 0.14, 0.42, 0.06), LIGHT, STONE, 0.14);
    cel(g, ell(0, -0.86, 0.15, 0.08), LIGHT, STONE, -0.05);
    // The tablet held upright in the joined hands.
    cel(g, rr(-0.028, -1.0, 0.056, 0.2, 0.012), LIGHT, STONE, -0.01);
    // The cross collar.
    line(g, "rgba(70,64,54,0.55)", 0.014, -0.1, -1.09, 0.03, -0.93);
    line(g, "rgba(70,64,54,0.55)", 0.014, 0.1, -1.09, -0.02, -0.96);
    // Head, a carved beard, and the cap with its slanting front beam.
    cel(g, ell(0, -1.2, 0.095, 0.105), LIGHT, STONE, -0.03);
    cel(g, poly(-0.05, -1.15, 0.05, -1.15, 0.03, -1.06, 0, -1.04, -0.03, -1.06), STONE, STONE_DARK);
    line(g, "rgba(60,56,48,0.6)", 0.012, -0.045, -1.21, -0.015, -1.21);
    line(g, "rgba(60,56,48,0.6)", 0.012, 0.015, -1.21, 0.045, -1.21);
    cel(g, rr(-0.1, -1.3, 0.2, 0.05, 0.015), STONE, STONE_DARK, -0.05);
    cel(g, poly(-0.07, -1.3, 0.07, -1.3, 0.05, -1.44, -0.01, -1.4), STONE, STONE_DARK, -0.02);
    // Weathering.
    g.strokeStyle = "rgba(60,56,48,0.25)";
    g.lineWidth = 0.008;
    g.beginPath();
    g.moveTo(0.16, -0.62);
    g.lineTo(0.12, -0.55);
    g.lineTo(0.14, -0.48);
    g.stroke();
  });
}

/** A low couch-bed (榻) with a quilt and a pillow. */
function bed(): Art {
  return art(1.2, 1.0, 0.6, 0.8, (g) => {
    shadow(g, 0, -0.03, 0.54, 0.12);
    for (const x of [-0.5, 0.44]) cel(g, rr(x, -0.22, 0.06, 0.2, 0.01), LACQUER);
    cel(g, rr(-0.52, -0.3, 1.04, 0.1, 0.02), LACQUER, undefined);
    inside(g, rr(-0.52, -0.3, 1.04, 0.1, 0.02), () => cloudScroll(g, -0.5, 0.5, -0.25, 0.03));
    cel(g, poly(-0.5, -0.64, 0.5, -0.64, 0.52, -0.3, -0.52, -0.3), "#4a3c36", LACQUER, -0.3);
    // The quilt, folded back at the head.
    cel(g, poly(-0.2, -0.62, 0.46, -0.62, 0.5, -0.32, -0.24, -0.32), "#6a5a8a", "#4e4268", -0.05);
    inside(g, poly(-0.2, -0.62, 0.46, -0.62, 0.5, -0.32, -0.24, -0.32), () => {
      g.strokeStyle = "rgba(230,210,160,0.35)";
      g.lineWidth = 0.012;
      for (let x = -0.1; x < 0.5; x += 0.14) {
        g.beginPath();
        g.arc(x, -0.47, 0.04, 0, Math.PI * 2);
        g.stroke();
      }
    });
    cel(g, rr(-0.46, -0.6, 0.22, 0.14, 0.05), "#d9cba6", "#b9a983", -0.4);
  });
}

/** A sluice: stone piers and timber gate boards, a windlass on top. */
function sluice(): Art {
  return art(1.3, 1.7, 0.65, 1.4, (g) => {
    for (const x of [-0.5, 0.36]) {
      cel(g, rr(x, -0.92, 0.14, 0.9, 0.02), STONE, STONE_DARK, x + 0.05);
      inside(g, rr(x, -0.92, 0.14, 0.9, 0.02), () => {
        for (let y = -0.8; y < 0; y += 0.15) line(g, "rgba(60,56,48,0.4)", 0.01, x, y, x + 0.14, y);
      });
    }
    cel(g, rr(-0.36, -0.7, 0.72, 0.62, 0.01), WOOD, WOOD_DARK, -0.2);
    inside(g, rr(-0.36, -0.7, 0.72, 0.62, 0.01), () => {
      for (let y = -0.62; y < -0.08; y += 0.1)
        line(g, "rgba(40,24,10,0.6)", 0.012, -0.36, y, 0.36, y);
      line(g, "rgba(0,0,0,0.3)", 0.02, -0.36, -0.12, 0.36, -0.12);
    });
    cel(g, rr(-0.55, -1.0, 1.1, 0.09, 0.02), WOOD, WOOD_DARK, -0.35);
    for (const x of [-0.3, 0.3]) cel(g, rr(x - 0.02, -1.2, 0.04, 0.2, 0.01), WOOD_DARK);
    cel(g, rr(-0.32, -1.2, 0.64, 0.06, 0.03), WOOD, WOOD_DARK, -0.2);
    line(g, "#8a7650", 0.014, 0, -1.17, 0, -0.7);
  });
}

// ---------------------------------------------------------------- building

export interface PropView {
  readonly kind: PropKind;
  /** At the tile's ground point, in tile units (the stage scales it to the tile). */
  readonly root: Container;
  /** Drawn behind figures on the same tile (a gateway they step out of). */
  readonly behind: boolean;
  /** A light it gives (more at night): offset from the ground point, reach and colour. */
  readonly light: { dx: number; dy: number; reach: number; color: number } | null;
  /** Flames rise from this point (braziers). */
  readonly flame: { dx: number; dy: number } | null;
  /**
   * How far it rises over the tiles behind it (half-width and height, in tiles):
   * the stage fades it while someone stands there, so nobody is hidden.
   */
  readonly cover: { w: number; h: number } | null;
  /** Small motions: a banner stirring, a lamp's flame, a horse shifting. */
  tick(clock: number): void;
}

/** Props tall enough to hide a figure standing behind them. */
const COVER: Partial<Record<PropKind, { w: number; h: number }>> = {
  pillar: { w: 0.25, h: 2.2 },
  banner: { w: 0.35, h: 2.4 },
  tent: { w: 0.95, h: 1.45 },
  lantern: { w: 0.18, h: 1.3 },
  rack: { w: 0.5, h: 1.75 },
  statue: { w: 0.3, h: 1.45 },
  well: { w: 0.45, h: 1.4 },
  screen: { w: 0.65, h: 1.3 },
  sluice: { w: 0.55, h: 1.2 },
  cart: { w: 0.7, h: 1.0 },
  horse: { w: 0.7, h: 1.15 },
  throne: { w: 0.6, h: 1.0 },
};

const cache = new Map<string, Art>();

function cached(key: string, make: () => Art): Art {
  let a = cache.get(key);
  if (!a) {
    a = make();
    cache.set(key, a);
  }
  return a;
}

function sprite(a: Art): Sprite {
  const s = new Sprite(a.tex);
  s.anchor.set(a.ax, a.ay);
  s.scale.set(1 / PU);
  return s;
}

/** Light glows from small lamps and braziers; the stage draws them additively. */
let flameTex: Texture | null = null;
/** A teardrop of flame, white-hot at its root (lamps, and the stage's fires). */
export function flameTexture(): Texture {
  if (flameTex) return flameTex;
  const c = document.createElement("canvas");
  c.width = 32;
  c.height = 48;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  const grad = g.createRadialGradient(16, 34, 2, 16, 30, 18);
  grad.addColorStop(0, "rgba(255,250,210,1)");
  grad.addColorStop(0.35, "rgba(255,190,80,0.95)");
  grad.addColorStop(1, "rgba(255,90,20,0)");
  g.fillStyle = grad;
  g.beginPath();
  g.moveTo(16, 2);
  g.quadraticCurveTo(30, 30, 16, 46);
  g.quadraticCurveTo(2, 30, 16, 2);
  g.fill();
  flameTex = Texture.from(c);
  return flameTex;
}

/**
 * The display for one prop. `near(dx, dy)` says whether the same kind of
 * prop stands next to it (tables, fences and gates join up).
 */
export function buildProp(def: PropDef, near: (dx: number, dy: number) => boolean): PropView {
  const side = def.side ?? 0;
  const root = new Container();
  const v = (def.at.x * 7 + def.at.y * 13) % 4;
  const L = near(-1, 0);
  const R = near(1, 0);
  const U = near(0, -1);
  const D = near(0, 1);
  let light: PropView["light"] = null;
  let flame: PropView["flame"] = null;
  let tick: (clock: number) => void = () => {};
  let behind = false;
  switch (def.kind) {
    case "throne":
      root.addChild(sprite(cached("throne", throne)));
      break;
    case "table":
      root.addChild(sprite(cached(`table${v % 2}${L}${R}`, () => table(v, L, R))));
      break;
    case "pillar":
      root.addChild(sprite(cached("pillar", pillar)));
      break;
    case "banner": {
      root.addChild(sprite(cached(`pole${side}`, () => bannerPole(side))));
      const flag = sprite(cached(`flag${side}`, () => bannerFlag(side)));
      flag.position.set(0.02, -2.12);
      root.addChild(flag);
      const phase = def.at.x * 1.7 + def.at.y;
      tick = (t) => {
        const w = Math.sin(t * 2.1 + phase) * 0.06 + Math.sin(t * 3.7 + phase * 2) * 0.03;
        flag.skew.y = w;
        flag.scale.x = (1 - Math.abs(w) * 0.8) / PU;
      };
      break;
    }
    case "tent":
      root.addChild(sprite(cached(`tent${side}`, () => tent(side))));
      break;
    case "brazier":
      root.addChild(sprite(cached("brazier", brazier)));
      light = { dx: 0, dy: -0.62, reach: 2.6, color: 0xff9a3c };
      flame = { dx: 0, dy: -0.62 };
      break;
    case "screen":
      root.addChild(sprite(cached("screen", screen)));
      break;
    case "lantern": {
      root.addChild(sprite(cached("lantern", lantern)));
      const f = new Sprite(flameTexture());
      f.anchor.set(0.5, 0.95);
      f.blendMode = "add";
      f.position.set(0, -1.25);
      root.addChild(f);
      const phase = def.at.x * 2.3 + def.at.y * 1.1;
      tick = (t) => {
        const k = 1 + Math.sin(t * 9 + phase) * 0.08 + Math.sin(t * 23 + phase) * 0.05;
        f.scale.set(0.0062 * k, 0.0068 * k * k);
      };
      light = { dx: 0, dy: -1.25, reach: 2.2, color: 0xffc06a };
      break;
    }
    case "gate":
      root.addChild(sprite(cached(`gate${L}${R}`, () => gate(L, R))));
      behind = true;
      break;
    case "horse": {
      const h = sprite(cached(`horse${side}`, () => horse(side)));
      // Horses face the middle of the stage.
      if (v % 2 === 1) h.scale.x = -1 / PU;
      root.addChild(h);
      const phase = def.at.x + def.at.y * 0.7;
      tick = (t) => {
        h.scale.y = (1 + Math.sin(t * 1.9 + phase) * 0.012) / PU;
      };
      break;
    }
    case "cart":
      root.addChild(sprite(cached("cart", cart)));
      break;
    case "grain":
      root.addChild(sprite(cached("grain", grain)));
      break;
    case "rack":
      root.addChild(sprite(cached("rack", rack)));
      break;
    case "well":
      root.addChild(sprite(cached("well", well)));
      break;
    case "fence":
      root.addChild(sprite(cached(`fence${L}${R}${U}${D}`, () => fence(L, R, U, D))));
      break;
    case "chest":
      root.addChild(sprite(cached("chest", chest)));
      break;
    case "statue":
      root.addChild(sprite(cached("statue", statue)));
      break;
    case "bed":
      root.addChild(sprite(cached("bed", bed)));
      break;
    case "sluice":
      root.addChild(sprite(cached("sluice", sluice)));
      behind = true;
      break;
  }
  return { kind: def.kind, root, behind, light, flame, tick, cover: COVER[def.kind] ?? null };
}

/** Horses look toward the middle of the stage. */
export function faceHorse(view: PropView, towardLeft: boolean): void {
  if (view.kind !== "horse") return;
  const s = view.root.children[0];
  if (s) s.scale.x = (towardLeft ? -1 : 1) * Math.abs(s.scale.x);
}
