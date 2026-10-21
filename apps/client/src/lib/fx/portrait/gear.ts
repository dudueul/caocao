/**
 * Things held in front (below the chin line) and gear behind the shoulders,
 * cropped by the frame so they never shrink the man (ADR 0070). A hero's own
 * prop or back shape comes from his row; otherwise the class gives the ji,
 * the bow, the fan or the slips.
 */
import {
  alpha,
  cel,
  curve,
  dot,
  ellipse,
  type G,
  INK,
  inside,
  LW,
  LW2,
  line,
  mix,
  type Pt,
  path,
  poly,
  ribbon,
  stroke,
} from "./draw.ts";
import type { Look } from "./look.ts";
import {
  BAMBOO,
  BAMBOO_DARK,
  BAMBOO_LIGHT,
  BLADE,
  BLADE_DARK,
  BLADE_MID,
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  GOLD,
  GOLD_DARK,
  GOLD_LIGHT,
  LACQUER,
  LACQUER_DARK,
  LACQUER_LIGHT,
  LACQUER_RED,
  type Pal,
  SEAL_JADE,
  SEAL_JADE_DARK,
  SEAL_JADE_LIGHT,
  WOOD,
  WOOD_DARK,
  WOOD_LIGHT,
  YELLOW,
  YELLOW_DARK,
  YELLOW_FOLD,
  YELLOW_LIGHT,
} from "./palette.ts";
import type { Back, Prop } from "./types.ts";

/** Draw in a frame mirrored about the card's centre when the man turns left. */
function mirrored(g: G, L: Look, draw: () => void): void {
  g.save();
  if (L.flip) {
    g.translate(256, 0);
    g.scale(-1, 1);
  }
  draw();
  g.restore();
}

// ---------------------------------------------------------------- weapons behind

/** The head of a ji (戟) at the origin, pointing up: a spike, a side blade, a tassel. */
function jiHead(g: G, P: Pal, crescent = false): void {
  const spike = path((p) => {
    p.moveTo(-4, 2);
    p.quadraticCurveTo(-6, -20, 0, -40);
    p.quadraticCurveTo(6, -20, 4, 2);
    p.closePath();
  });
  cel(g, spike, BLADE_MID, BLADE_DARK, 2, BLADE);
  const side = crescent
    ? path((p) => {
        // 方天畫戟: crescent blades either side (the novel).
        p.moveTo(-3, -4);
        p.quadraticCurveTo(-34, -18, -30, 6);
        p.quadraticCurveTo(-22, -2, -3, 12);
        p.closePath();
        p.moveTo(3, -4);
        p.quadraticCurveTo(34, -18, 30, 6);
        p.quadraticCurveTo(22, -2, 3, 12);
        p.closePath();
      })
    : path((p) => {
        p.moveTo(-3, 0);
        p.lineTo(-30, -6);
        p.quadraticCurveTo(-38, 0, -34, 8);
        p.lineTo(-3, 12);
        p.closePath();
      });
  cel(g, side, BLADE_MID, BLADE_DARK, 2, BLADE);
  cel(g, poly(-8, 14, 8, 14, 11, 30, -11, 30), P.tassel, P.robeDark, 3);
}

function haft(g: G, top: number, w = 8): void {
  cel(
    g,
    path((p) => p.roundRect(-w / 2, top, w, 10 - top, 3)),
    WOOD_DARK,
    "#3a2414",
    2,
    WOOD,
    LW2,
  );
}

/** A long weapon held upright behind the far shoulder. */
function poleArm(
  g: G,
  L: Look,
  kind: "ji" | "jiPennon" | "trident" | "axe" | "crescent" | "spear" | "glaive",
): void {
  const { P } = L;
  mirrored(g, L, () => {
    g.save();
    g.translate(240, 330);
    g.rotate(kind === "ji" || kind === "jiPennon" ? -0.07 : -0.1);
    const top = kind === "axe" || kind === "trident" ? -262 : kind === "glaive" ? -360 : -300;
    haft(g, top, kind === "glaive" ? 10 : 8);
    g.translate(0, top);
    if (kind === "ji" || kind === "crescent") jiHead(g, P, kind === "crescent");
    else if (kind === "jiPennon") {
      jiHead(g, P);
      // A long pennant streaming back.
      const pn = ribbon([0, 40], [-40, 70], [-96, 66], 26, 8);
      cel(g, pn, P.robe, P.robeDark, 4, mix(P.robe, "#ffffff", 0.3));
    } else if (kind === "spear") {
      const head = path((p) => {
        p.moveTo(-6, 4);
        p.quadraticCurveTo(-8, -20, 0, -46);
        p.quadraticCurveTo(8, -20, 6, 4);
        p.closePath();
      });
      cel(g, head, BLADE_MID, BLADE_DARK, 2, BLADE);
      const white = L.t.accent === "white";
      cel(
        g,
        poly(-9, 6, 9, 6, 13, 30, -13, 30),
        white ? "#f1efe8" : P.tassel,
        white ? "#c9c5ba" : P.robeDark,
        3,
      );
    } else if (kind === "trident") {
      const blade = path((p) => {
        p.moveTo(-10, 0);
        p.lineTo(-14, -24);
        p.lineTo(-17, -44);
        p.lineTo(-6, -32);
        p.lineTo(0, -62);
        p.lineTo(6, -32);
        p.lineTo(17, -44);
        p.lineTo(14, -24);
        p.lineTo(10, 0);
        p.closePath();
      });
      cel(g, blade, BLADE_MID, BLADE_DARK, 3, BLADE, LW);
      cel(g, poly(-14, -2, 14, -2, 11, 10, -11, 10), BRONZE, BRONZE_DARK, 2, BRONZE_LIGHT);
      cel(g, poly(-8, 10, 8, 10, 12, 30, -12, 30), P.tassel, P.robeDark, 3);
    } else if (kind === "axe") {
      const blade = path((p) => {
        p.moveTo(-3, -6);
        p.lineTo(-18, -14);
        p.quadraticCurveTo(-56, -30, -60, 16);
        p.quadraticCurveTo(-56, 60, -18, 44);
        p.lineTo(-3, 36);
        p.closePath();
      });
      cel(g, blade, BLADE_MID, BLADE_DARK, 4, BLADE, LW);
      curve(g, alpha("#ffffff", 0.8), 2, -56, -8, -62, 16, -54, 46);
      cel(g, ellipse(-18, 15, 5, 5), BRONZE, BRONZE_DARK, 2);
    } else {
      // The glaive's pole only: the blade stays out of the frame (關羽).
      cel(g, poly(-7, 0, 7, 0, 9, 18, -9, 18), BRONZE, BRONZE_DARK, 2, BRONZE_LIGHT);
    }
    g.restore();
  });
}

/** 典韋's pair of heavy ji (雙戟), crossed behind his back. */
function twinJi(g: G, L: Look): void {
  for (const [x, y, rot] of [
    [186, 350, -0.5],
    [70, 350, 0.5],
  ] as const) {
    g.save();
    g.translate(x, y);
    g.rotate(rot);
    haft(g, -322, 10);
    g.translate(0, -322);
    g.scale(rot < 0 ? 1.1 : -1.1, 1.1);
    jiHead(g, L.P);
    g.restore();
  }
}

/** A recurve bow behind the near shoulder; `high`, held up by a long arm (太史慈). */
function bow(g: G, L: Look, high: boolean): void {
  mirrored(g, L, () => {
    g.save();
    if (high) {
      g.translate(-10, -120);
      g.rotate(-0.15);
    }
    const limb = path((p) => {
      p.moveTo(30, 340);
      p.quadraticCurveTo(4, 250, 24, 170);
      p.quadraticCurveTo(30, 150, 18, 140);
    });
    g.lineCap = "round";
    stroke(g, INK, 11, limb);
    stroke(g, "#8a5a2c", 6, limb);
    line(g, alpha("#f0ecdc", 0.9), 1.6, 18, 142, 56, 340);
    g.restore();
  });
}

/** 董卓's two quivers (雙帶兩鞬, 三國志), crossed behind his back. */
function quivers(g: G, L: Look): void {
  for (const [x, rot] of [
    [60, -0.35],
    [196, 0.35],
  ] as const) {
    g.save();
    g.translate(x, 330);
    g.rotate(rot);
    const q = path((p) => p.roundRect(-14, -150, 28, 150, 6));
    cel(g, q, LACQUER, LACQUER_DARK, 4, LACQUER_LIGHT, LW);
    inside(g, q, () => {
      for (const y of [-136, -60]) line(g, GOLD, 4, -14, y, 14, y);
    });
    for (let i = 0; i < 4; i++) {
      const x0 = -9 + i * 6;
      line(g, INK, 3, x0, -150, x0 + 1, -176);
      line(g, "#e9e2cf", 5, x0 - 2, -176, x0 + 3, -190);
    }
    g.restore();
  }
  void L;
}

/** 甘寧's plumes (負毦), rising behind his shoulders. */
function plumes(g: G): void {
  for (const [x, dir] of [
    [70, -1],
    [196, 1],
  ] as const) {
    for (let i = 0; i < 3; i++) {
      const a: Pt = [x + dir * i * 6, 250];
      const f = ribbon(
        a,
        [a[0] + dir * (20 + i * 8), 160 - i * 10],
        [a[0] + dir * (6 + i * 14), 70 - i * 6],
        16,
        4,
      );
      cel(
        g,
        f,
        i === 1 ? "#d8392c" : "#e9e2cf",
        i === 1 ? "#8e2614" : "#b9b2a2",
        3,
        "#ffffff",
        LW2,
      );
    }
  }
}

/** 顏良's command parasol (麾蓋) over his head. */
function parasol(g: G): void {
  g.save();
  const rim: Pt[] = [];
  for (let i = 0; i <= 12; i++) {
    const a = Math.PI + (i / 12) * Math.PI;
    rim.push([128 + Math.cos(a) * 150, 40 + Math.sin(a) * 30]);
  }
  const canopy = path((p) => {
    p.moveTo(-30, 46);
    p.quadraticCurveTo(128, -60, 286, 46);
    p.lineTo(286, 58);
    for (let x = 286; x >= -30; x -= 16) p.lineTo(x, 58 + ((x / 16) % 2 ? 8 : 0));
    p.closePath();
  });
  cel(g, canopy, "#2e4a78", "#1f3354", 6, "#5a78a8", LW);
  inside(g, canopy, () => {
    for (let x = -30; x < 290; x += 26) line(g, alpha(GOLD, 0.8), 2, 128, -20, x, 60);
  });
  line(g, INK, 7, 230, 40, 236, 340);
  line(g, GOLD, 3, 230, 40, 236, 340);
  g.restore();
}

/** 周瑜's zither (琴) slung behind his back. */
function zither(g: G, L: Look): void {
  mirrored(g, L, () => {
    g.save();
    g.translate(150, 250);
    g.rotate(-0.9);
    const body = path((p) => p.roundRect(-150, -18, 300, 36, 14));
    cel(g, body, LACQUER, LACQUER_DARK, 5, LACQUER_LIGHT, LW);
    inside(g, body, () => {
      for (let i = 0; i < 7; i++)
        line(g, alpha("#e8dcc0", 0.85), 1, -140, -12 + i * 4, 140, -12 + i * 4);
    });
    for (let i = 0; i < 7; i++) dot(g, "#e8dcc0", -100 + i * 26, 22, 2.4);
    g.restore();
  });
}

/** A straight row of banners behind (于禁's discipline). */
function banners(g: G, L: Look): void {
  for (let i = 0; i < 4; i++) {
    const x = 16 + i * 74;
    line(g, INK, 5, x, 10, x, 300);
    line(g, WOOD, 2.4, x, 10, x, 300);
    const f = path((p) => p.rect(x, 14, 30, 64));
    cel(g, f, L.P.robe, L.P.robeDark, 4, mix(L.P.robe, "#ffffff", 0.3), LW2);
  }
}

/** The top rungs of a scaling ladder behind the shoulder (樂進, first up the wall). */
function ladder(g: G, L: Look): void {
  mirrored(g, L, () => {
    for (const x of [196, 240]) {
      line(g, INK, 9, x, 20, x + 8, 330);
      line(g, WOOD, 5, x, 20, x + 8, 330);
    }
    for (let y = 40; y < 300; y += 36) {
      line(g, INK, 7, 196 + y * 0.025, y, 240 + y * 0.025, y);
      line(g, WOOD_LIGHT, 3.5, 196 + y * 0.025, y, 240 + y * 0.025, y);
    }
  });
}

/** 張飛's serpent spear (蛇矛, the novel) held crosswise behind. */
function snake(g: G): void {
  g.save();
  g.translate(128, 230);
  g.rotate(-0.42);
  cel(
    g,
    path((p) => p.roundRect(-190, -5, 360, 10, 4)),
    "#2a2622",
    "#141210",
    2,
    "#55504e",
    LW2,
  );
  const blade = path((p) => {
    p.moveTo(168, -7);
    for (let i = 0; i <= 6; i++) p.lineTo(176 + i * 8, (i % 2 ? -9 : -3) - i * 0.6);
    p.lineTo(232, 0);
    for (let i = 6; i >= 0; i--) p.lineTo(176 + i * 8, (i % 2 ? 9 : 3) + i * 0.6);
    p.lineTo(168, 7);
    p.closePath();
  });
  cel(g, blade, BLADE_MID, BLADE_DARK, 2, BLADE, LW2);
  g.restore();
}

/** A long sabre (刀) held upright behind. */
function dao(g: G, L: Look): void {
  mirrored(g, L, () => {
    g.save();
    g.translate(236, 330);
    g.rotate(-0.06);
    haft(g, -170);
    g.translate(0, -170);
    const blade = path((p) => {
      p.moveTo(-5, 0);
      p.lineTo(-8, -110);
      p.quadraticCurveTo(-4, -140, 14, -150);
      p.quadraticCurveTo(10, -100, 8, 0);
      p.closePath();
    });
    cel(g, blade, BLADE_MID, BLADE_DARK, 3, BLADE, LW);
    g.restore();
  });
}

/** The red horse's head at his shoulder (赤兔). */
function horse(g: G, L: Look): void {
  mirrored(g, L, () => {
    const head = path((p) => {
      p.moveTo(206, 340);
      p.quadraticCurveTo(196, 250, 214, 200);
      p.quadraticCurveTo(226, 170, 252, 168);
      p.lineTo(272, 178);
      p.lineTo(272, 340);
      p.closePath();
    });
    cel(g, head, "#a8402a", "#6a2416", 7, "#d0705a", LW);
    inside(g, head, () => {
      for (let i = 0; i < 6; i++)
        line(g, "#2a120c", 4, 206 + i * 4, 186 + i * 22, 196 + i * 4, 206 + i * 22);
    });
    dot(g, INK, 238, 196, 3);
    cel(g, ellipse(222, 176, 6, 10, -0.5), "#a8402a", "#6a2416", 2, undefined, LW2);
    cel(g, ellipse(244, 230, 8, 8), BRONZE, BRONZE_DARK, 2, BRONZE_LIGHT);
  });
}

export function backGear(g: G, L: Look): void {
  const t = L.t;
  const back: Back | undefined =
    t.back ?? (L.fam === "cavalry" ? "ji" : L.fam === "archer" ? "bow" : undefined);
  switch (back) {
    case "ji":
    case "jiPennon":
    case "trident":
    case "axe":
    case "crescent":
    case "spear":
    case "glaive":
      poleArm(g, L, back);
      break;
    case "twinJi":
      twinJi(g, L);
      break;
    case "bow":
      bow(g, L, false);
      break;
    case "bowHigh":
      bow(g, L, true);
      break;
    case "quivers":
      quivers(g, L);
      break;
    case "plumes":
      plumes(g);
      break;
    case "parasol":
      parasol(g);
      break;
    case "zither":
      zither(g, L);
      break;
    case "banners":
      banners(g, L);
      break;
    case "ladder":
      ladder(g, L);
      break;
    case "snake":
      snake(g);
      break;
    case "dao":
      dao(g, L);
      break;
    case "horse":
      horse(g, L);
      poleArm(g, L, "crescent");
      break;
    default:
      break;
  }
}

// ---------------------------------------------------------------- in front

/** Held up at the shoulder in the near hand: a fan, slips, staff and the like. */
function held(g: G, L: Look, x: number, y: number, rot: number, draw: () => void): void {
  mirrored(g, L, () => {
    g.save();
    g.translate(x, y);
    g.rotate(rot);
    draw();
    g.restore();
  });
}

function fan(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-5, 10, 10, 50, 4)),
    "#8a5a2c",
    "#5a3a1c",
    2,
  );
  const f = path((p) => {
    p.moveTo(0, 14);
    p.bezierCurveTo(-46, -10, -44, -84, 0, -96);
    p.bezierCurveTo(44, -84, 46, -10, 0, 14);
    p.closePath();
  });
  cel(g, f, "#f8f4ec", "#d4cdbf", 6, "#ffffff", LW);
  inside(g, f, () => {
    for (let i = -5; i <= 5; i++) line(g, alpha("#9a9080", 0.7), 1.4, 0, 12, i * 9, -96);
  });
  cel(
    g,
    path((p) => p.roundRect(-8, 6, 16, 10, 3)),
    "#3a2a20",
    "#20150f",
    2,
  );
}

function slips(g: G, cord: string): void {
  const roll = path((p) => p.roundRect(-15, -48, 30, 96, 13));
  cel(g, roll, "#d6bb82", "#a8884e", 6, "#f2deb0", LW);
  inside(g, roll, () => {
    for (let x = -12; x < 14; x += 5) line(g, alpha("#7d6236", 0.55), 1.2, x, -50, x, 50);
  });
  for (const y of [-22, 20]) {
    line(g, INK, 6, -16, y, 16, y + 2);
    line(g, cord, 3.4, -16, y, 16, y + 2);
  }
  cel(g, ellipse(0, -46, 13, 5), "#e8d3a2", "#bfa36c", 2);
}

function tablet(g: G): void {
  // A court tablet (手版) of ivory-white wood held upright.
  const t = path((p) => p.roundRect(-9, -70, 18, 110, 6));
  cel(g, t, "#efe6d0", "#c9bc9c", 4, "#ffffff", LW);
}

function staff(g: G): void {
  const rod = path((p) => p.roundRect(-5, -292, 10, 300, 4));
  cel(g, rod, BAMBOO, BAMBOO_DARK, 3, BAMBOO_LIGHT, LW2);
  for (let i = 0; i < 9; i++) {
    const y = -276 + i * 31;
    line(g, INK, 4.4, -6.5, y, 6.5, y);
    line(g, BAMBOO_LIGHT, 1.6, -5, y - 2, 5, y - 2);
  }
  for (const k of [0, 1])
    cel(
      g,
      ribbon([0, -262], [-22 - k * 8, -244 + k * 10], [-14 - k * 18, -206 + k * 14], 9, 5),
      YELLOW,
      YELLOW_DARK,
      3,
      YELLOW_LIGHT,
    );
}

function seal(g: G): void {
  g.scale(1.3, 1.3);
  const silk = path((p) => {
    p.moveTo(-50, 12);
    p.quadraticCurveTo(-10, -2, 44, 6);
    p.quadraticCurveTo(64, 30, 60, 60);
    p.lineTo(-56, 60);
    p.quadraticCurveTo(-64, 34, -50, 12);
    p.closePath();
  });
  cel(g, silk, YELLOW, YELLOW_DARK, 6, YELLOW_LIGHT, LW);
  inside(g, silk, () => curve(g, alpha(YELLOW_FOLD, 0.7), 1.8, -40, 24, -8, 40, -16, 60));
  cel(g, poly(-30, -10, 0, 2, 0, 30, -30, 18), SEAL_JADE, SEAL_JADE_DARK, 4, undefined, LW);
  cel(
    g,
    poly(0, 2, 28, -8, 28, 20, 0, 30),
    mix(SEAL_JADE, SEAL_JADE_DARK, 0.45),
    SEAL_JADE_DARK,
    3,
    undefined,
    LW,
  );
  cel(g, poly(-30, -10, -2, -22, 28, -8, 0, 2), SEAL_JADE_LIGHT, SEAL_JADE, 3, undefined, LW);
  cel(g, poly(-30, 6, -30, 18, -18, 23, -20, 10), GOLD, GOLD_DARK, 2, GOLD_LIGHT, LW2);
  const knob = path((p) => {
    p.moveTo(-14, -10);
    p.bezierCurveTo(-18, -30, -2, -40, 6, -30);
    p.bezierCurveTo(14, -36, 22, -24, 14, -12);
    p.quadraticCurveTo(0, -4, -14, -10);
    p.closePath();
  });
  cel(g, knob, SEAL_JADE, SEAL_JADE_DARK, 3, SEAL_JADE_LIGHT, LW);
}

function cudgel(g: G, staves: boolean): void {
  const club = path((p) => {
    p.moveTo(-5, 0);
    p.lineTo(-9, -178);
    p.quadraticCurveTo(-9, -192, 0, -192);
    p.quadraticCurveTo(9, -192, 9, -178);
    p.lineTo(5, 0);
    p.closePath();
  });
  cel(g, club, WOOD, WOOD_DARK, 4, mix(WOOD, "#ffffff", 0.25), LW);
  if (staves)
    inside(g, club, () => {
      const five = ["#3f6b9a", "#b3362a", "#d8b23a", "#efe8d8", "#24201e"];
      for (let i = 0; i < 10; i++) {
        g.fillStyle = five[i % 5] ?? WOOD;
        g.fillRect(-12, -192 + i * 19, 24, 19);
      }
    });
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke(club);
}

function bells(g: G): void {
  line(g, INK, 4, 150, 262, 196, 300);
  line(g, "#7a3a28", 2, 150, 262, 196, 300);
  for (const [x, y] of [
    [162, 276],
    [178, 290],
    [194, 304],
  ] as const) {
    const b = path((p) => {
      p.moveTo(x - 7, y + 10);
      p.quadraticCurveTo(x - 7, y - 2, x, y - 2);
      p.quadraticCurveTo(x + 7, y - 2, x + 7, y + 10);
      p.closePath();
    });
    cel(g, b, GOLD, GOLD_DARK, 3, GOLD_LIGHT);
    dot(g, INK, x, y + 9, 2);
  }
}

/** A Han lacquer ear-cup (耳杯): oval, red inside, black outside, with two flat handles. */
function cup(g: G): void {
  for (const sd of [-1, 1])
    cel(g, ellipse(sd * 20, -2, 8, 4), LACQUER, LACQUER_DARK, 1, undefined, LW2);
  const body = path((p) => {
    p.moveTo(-20, -4);
    p.quadraticCurveTo(-16, 14, 0, 14);
    p.quadraticCurveTo(16, 14, 20, -4);
    p.closePath();
  });
  cel(g, body, LACQUER, LACQUER_DARK, 3, LACQUER_LIGHT, LW);
  cel(g, ellipse(0, -4, 20, 6), LACQUER_RED, "#6e2019", 2, "#d8665a", LW2);
  cel(g, ellipse(0, -3, 13, 3.4), "#c9a35a", "#8a6a2c", 1, undefined, 0);
}

/** A short hand-ji (手戟) held across the chest: 曹操's escape from 張讓's house. */
function handJi(g: G, P: Pal): void {
  g.rotate(-0.75);
  haft(g, -150, 8);
  g.translate(0, -150);
  g.scale(0.85, 0.85);
  jiHead(g, P);
}

/** Strings of cash (五銖) over the arm. */
function cash(g: G): void {
  for (let k = 0; k < 3; k++) {
    const pts: Pt[] = [];
    for (let i = 0; i <= 10; i++) {
      const u = i / 10;
      pts.push([-46 + u * 92, 10 + k * 12 + Math.sin(u * Math.PI) * (22 - k * 4)]);
    }
    for (const [x, y] of pts) {
      cel(g, ellipse(x, y, 6, 6), BRONZE, BRONZE_DARK, 1.5, BRONZE_LIGHT, 1.6);
      g.fillStyle = INK;
      g.fillRect(x - 1.6, y - 1.6, 3.2, 3.2);
    }
  }
}

function purse(g: G): void {
  const b = path((p) => {
    p.moveTo(-10, -18);
    p.quadraticCurveTo(-30, 0, -24, 22);
    p.quadraticCurveTo(0, 34, 24, 22);
    p.quadraticCurveTo(30, 0, 10, -18);
    p.closePath();
  });
  cel(g, b, "#7a4a2a", "#4e2e18", 4, "#a87250", LW);
  line(g, INK, 5, -12, -18, 12, -18);
  line(g, GOLD, 2.4, -12, -18, 12, -18);
  cel(g, ellipse(0, 6, 7, 7), GOLD, GOLD_DARK, 2, GOLD_LIGHT, 1.6);
}

/** 劉備's white yak-tail tassel (毦), half plaited (三國志 注:魏略). */
function tassel(g: G): void {
  const t = path((p) => {
    p.moveTo(-8, -30);
    p.quadraticCurveTo(-26, 10, -18, 46);
    p.quadraticCurveTo(0, 56, 18, 46);
    p.quadraticCurveTo(26, 10, 8, -30);
    p.closePath();
  });
  cel(g, t, "#f4f1ea", "#c9c3b4", 4, "#ffffff", LW);
  inside(g, t, () => {
    for (let x = -20; x < 22; x += 4) line(g, alpha("#a39c8c", 0.8), 1.2, x * 0.4, -26, x, 52);
  });
  for (let i = 0; i < 4; i++)
    cel(
      g,
      ellipse(0, -36 - i * 7, 5, 4, i % 2 ? 0.5 : -0.5),
      "#e9e4d8",
      "#b9b2a2",
      1,
      undefined,
      1.4,
    );
  cel(
    g,
    path((p) => p.roundRect(-7, -66, 14, 8, 3)),
    VERMILION_C,
    "#8e2614",
    2,
  );
}

const VERMILION_C = "#d4452c";

/** A crossbow (弩) held at the chest: stock, arms, bronze lock. */
function crossbow(g: G): void {
  const arms = path((p) => {
    p.moveTo(-70, 6);
    p.quadraticCurveTo(0, -16, 70, 6);
    p.quadraticCurveTo(0, -6, -70, 6);
    p.closePath();
  });
  cel(g, arms, "#8a5a2c", "#5a3a1c", 2, "#b07a44", LW2);
  line(g, alpha("#f0ecdc", 0.9), 1.4, -70, 6, 0, 40, 70, 6);
  cel(
    g,
    path((p) => p.roundRect(-6, -10, 12, 80, 3)),
    WOOD,
    WOOD_DARK,
    3,
    WOOD_LIGHT,
    LW2,
  );
  cel(
    g,
    path((p) => p.roundRect(-8, 26, 16, 18, 3)),
    BRONZE,
    BRONZE_DARK,
    2,
    BRONZE_LIGHT,
    1.6,
  );
}

/** A bundled reed torch with its flame (皇甫嵩's fire attack at 長社). */
function torch(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-7, -40, 14, 120, 4)),
    STRAW_C,
    "#8e7438",
    3,
    "#ead391",
    LW2,
  );
  for (const y of [-30, 0, 30]) line(g, "#5a4020", 3, -7, y, 7, y + 3);
  const fl = path((p) => {
    p.moveTo(-16, -40);
    p.quadraticCurveTo(-22, -70, -2, -100);
    p.quadraticCurveTo(4, -80, 12, -86);
    p.quadraticCurveTo(22, -60, 16, -40);
    p.closePath();
  });
  cel(g, fl, "#f08a2a", "#c0451c", 4, "#ffd26a", LW2);
  cel(g, ellipse(0, -52, 6, 12), "#ffe08a", undefined, 0, undefined, 0);
}

const STRAW_C = "#c9ad6a";

/** A straight sword held upright before him, point up. */
function sword(g: G): void {
  const blade = path((p) => {
    p.moveTo(-5, 0);
    p.lineTo(-5, -150);
    p.lineTo(0, -166);
    p.lineTo(5, -150);
    p.lineTo(5, 0);
    p.closePath();
  });
  cel(g, blade, BLADE_MID, BLADE_DARK, 2, BLADE, LW2);
  line(g, alpha(BLADE_DARK, 0.9), 1.2, 0, -4, 0, -150);
  cel(
    g,
    path((p) => p.roundRect(-16, -2, 32, 8, 3)),
    BRONZE,
    BRONZE_DARK,
    2,
    BRONZE_LIGHT,
    LW2,
  );
  cel(
    g,
    path((p) => p.roundRect(-5, 6, 10, 34, 3)),
    "#3a2a20",
    "#20150f",
    2,
    undefined,
    LW2,
  );
}

/** The ring-pommel sabre (環首刀). */
function sabre(g: G): void {
  const blade = path((p) => {
    p.moveTo(-4, 0);
    p.lineTo(-5, -150);
    p.quadraticCurveTo(-2, -164, 5, -162);
    p.lineTo(5, 0);
    p.closePath();
  });
  cel(g, blade, BLADE_MID, BLADE_DARK, 2, BLADE, LW2);
  cel(
    g,
    path((p) => p.roundRect(-4, 4, 9, 34, 3)),
    "#3a2a20",
    "#20150f",
    2,
    undefined,
    LW2,
  );
  const ring = ellipse(0, 46, 8, 8);
  stroke(g, INK, 6, ring);
  stroke(g, BRONZE, 3, ring);
}

function rope(g: G): void {
  for (let i = 0; i < 3; i++) {
    const p = ellipse(i * 4, i * 3, 26, 12, -0.4);
    g.lineWidth = 8;
    g.strokeStyle = INK;
    g.stroke(p);
    g.lineWidth = 4.4;
    g.strokeStyle = i === 1 ? "#b29a6c" : "#cbb98f";
    g.stroke(p);
  }
}

/** A wine jar (侯成's wine for the feast that cost him his post). */
function jar(g: G): void {
  const j = path((p) => {
    p.moveTo(-12, -40);
    p.lineTo(-14, -30);
    p.quadraticCurveTo(-40, -20, -36, 20);
    p.quadraticCurveTo(-30, 50, 0, 50);
    p.quadraticCurveTo(30, 50, 36, 20);
    p.quadraticCurveTo(40, -20, 14, -30);
    p.lineTo(12, -40);
    p.closePath();
  });
  cel(g, j, "#7d6a52", "#4e4032", 5, "#a8937a", LW);
  cel(
    g,
    path((p) => p.roundRect(-14, -46, 28, 8, 3)),
    "#b3362a",
    "#74201a",
    2,
    undefined,
    LW2,
  );
  inside(g, j, () => line(g, alpha("#e8dcc0", 0.6), 2, -36, 0, 36, 4));
}

/** A cangue's iron chain across the wrists (田豐 in prison). */
function chain(g: G): void {
  for (let i = 0; i < 9; i++) {
    const x = -80 + i * 20;
    const l = ellipse(x, Math.sin(i * 0.7) * 6, 11, 6, i % 2 ? 0 : 0);
    stroke(g, INK, 6, l);
    stroke(g, i % 2 ? IRON_C : "#9aa0a8", 3, l);
  }
}

const IRON_C = "#767b84";

/** A lacquer dish of sliced raw fish (陳登's illness, 三國志 華佗傳). */
function fish(g: G): void {
  cel(g, ellipse(0, 6, 46, 14), LACQUER, LACQUER_DARK, 3, LACQUER_LIGHT, LW);
  cel(g, ellipse(0, 3, 38, 10), LACQUER_RED, "#6e2019", 2, undefined, LW2);
  for (let i = 0; i < 6; i++) {
    const x = -26 + i * 10;
    cel(g, ellipse(x, 0 - (i % 2) * 3, 7, 4, 0.5), "#f2d0c0", "#d8a08c", 1, "#fff2ea", 1.4);
  }
}

/** A south-pointing figure (指南車), its arm held out (馬鈞 built one). */
function compass(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-30, 10, 60, 16, 4)),
    WOOD,
    WOOD_DARK,
    3,
    WOOD_LIGHT,
    LW2,
  );
  for (const sd of [-1, 1]) {
    const w = ellipse(sd * 26, 28, 10, 10);
    stroke(g, INK, 5, w);
    stroke(g, WOOD_LIGHT, 2.4, w);
  }
  cel(
    g,
    path((p) => p.roundRect(-6, -30, 12, 40, 4)),
    "#c9b48a",
    "#8f7c56",
    2,
    undefined,
    LW2,
  );
  cel(g, ellipse(0, -36, 7, 7), "#c9b48a", "#8f7c56", 2, undefined, LW2);
  line(g, INK, 5, 2, -24, 30, -40);
  line(g, "#c9b48a", 2.6, 2, -24, 30, -40);
}

/** A small wooden model of a traction trebuchet (劉曄's thunderbolt carts). */
function model(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-30, 14, 60, 10, 3)),
    WOOD,
    WOOD_DARK,
    2,
    WOOD_LIGHT,
    LW2,
  );
  for (const sd of [-1, 1]) {
    line(g, INK, 5, sd * 18, 16, 0, -20);
    line(g, WOOD_LIGHT, 2.4, sd * 18, 16, 0, -20);
  }
  line(g, INK, 5, -40, -4, 30, -34);
  line(g, WOOD_LIGHT, 2.6, -40, -4, 30, -34);
  line(g, alpha(INK, 0.8), 1.4, 30, -34, 34, -6);
  cel(g, ellipse(34, -4, 5, 5), "#9a8c78", "#6a5a48", 1, undefined, 1.4);
}

/** The envoy's 節: a tall staff with tiers of red yak-tail tufts (辛毗's mission). */
function jie(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-4, -280, 8, 300, 3)),
    "#3a2414",
    "#20150f",
    2,
    WOOD,
    LW2,
  );
  for (let i = 0; i < 3; i++) {
    const y = -262 + i * 30;
    const t = path((p) => {
      p.moveTo(-6, y);
      p.quadraticCurveTo(-24, y + 20, -16, y + 26);
      p.lineTo(16, y + 26);
      p.quadraticCurveTo(24, y + 20, 6, y);
      p.closePath();
    });
    cel(g, t, "#c0392b", "#7a1f17", 2, "#e46a5a", LW2);
  }
}

/** A writing brush and a wooden tablet (陳琳, 許劭). */
function brush(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-22, -40, 36, 76, 3)),
    "#e8d9b4",
    "#bfa978",
    4,
    "#fbf2dc",
    LW,
  );
  for (let y = -30; y < 30; y += 9) line(g, alpha("#2a1a12", 0.55), 1.4, -16, y, 6, y);
  g.rotate(0.5);
  cel(
    g,
    path((p) => p.roundRect(14, -60, 6, 64, 2)),
    BAMBOO_C,
    "#7c6a2f",
    2,
    undefined,
    LW2,
  );
  const tip = path((p) => {
    p.moveTo(14, -60);
    p.quadraticCurveTo(17, -76, 20, -60);
    p.closePath();
  });
  cel(g, tip, "#2a2222", "#100c0c", 1, undefined, 1.4);
}

const BAMBOO_C = "#b9a052";

/** Reins held out to the side (曹昂 giving his horse). */
function reins(g: G): void {
  g.lineCap = "round";
  curve(g, INK, 7, -60, -40, -10, 10, 40, -20);
  curve(g, "#6b4529", 3.6, -60, -40, -10, 10, 40, -20);
  curve(g, INK, 7, -60, -30, -14, 26, 46, -6);
  curve(g, "#6b4529", 3.6, -60, -30, -14, 26, 46, -6);
  cel(g, ellipse(40, -18, 7, 7), BRONZE, BRONZE_DARK, 2, BRONZE_LIGHT, 1.6);
}

/** A gold cake (餅金), the price of a betrayal (胡車兒). */
function ingot(g: G): void {
  const b = path((p) => {
    p.moveTo(-26, 0);
    p.quadraticCurveTo(-30, -18, 0, -20);
    p.quadraticCurveTo(30, -18, 26, 0);
    p.quadraticCurveTo(0, 12, -26, 0);
    p.closePath();
  });
  cel(g, b, GOLD, GOLD_DARK, 4, GOLD_LIGHT, LW);
  curve(g, alpha(GOLD_DARK, 0.9), 1.6, -16, -8, 0, -2, 16, -8);
}

/** A length of sugarcane held like a sword (曹丕 fencing 鄧展, 典論). */
function cane(g: G): void {
  const c = path((p) => p.roundRect(-5, -170, 10, 180, 4));
  cel(g, c, "#7a5a7a", "#4e3450", 2, "#a888a6", LW2);
  for (let y = -160; y < 10; y += 26) {
    line(g, INK, 3.4, -6, y, 6, y);
    line(g, "#c8b06a", 1.4, -5, y - 2, 5, y - 2);
  }
  for (const sd of [-1, 1])
    cel(
      g,
      ribbon([0, -168], [sd * 16, -190], [sd * 28, -200], 6, 2),
      "#6a9a5a",
      "#3e6a36",
      1,
      undefined,
      1.4,
    );
}

/** A camp plan on a board (張郃 at 官渡). */
function plan(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-40, -30, 80, 60, 3)),
    "#e8d9b4",
    "#bfa978",
    4,
    "#fbf2dc",
    LW,
  );
  for (let i = 0; i < 4; i++)
    line(g, alpha("#5a3a1c", 0.8), 1.6, -30, -18 + i * 12, 30, -18 + i * 12);
  for (let i = 0; i < 3; i++) g.strokeRect(-24 + i * 18, -14, 10, 26);
  dot(g, "#b3262a", 12, 4, 3);
}

/** A tall lacquered shield with a cloud scroll (曹仁's defence; 高順's polished edge). */
function shield(g: G, P: Pal): void {
  const s = path((p) => p.roundRect(-34, -80, 68, 170, 16));
  cel(g, s, LACQUER, LACQUER_DARK, 6, LACQUER_LIGHT, LW);
  inside(g, s, () => {
    g.strokeStyle = P.lace;
    g.lineWidth = 7;
    g.strokeRect(-30, -76, 60, 162);
    curve(g, alpha(LACQUER_RED, 0.95), 3, -16, -40, 4, -60, 14, -36, 22, -16, 2, -10);
    curve(g, alpha(LACQUER_RED, 0.95), 3, 10, 20, -10, 0, -18, 24, -24, 50, 2, 56);
  });
}

/** A rattan rod of discipline (于禁). */
function rod(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-4, -200, 8, 210, 3)),
    "#a8884e",
    "#6e5630",
    2,
    "#d0b47a",
    LW2,
  );
  for (let y = -190; y < 0; y += 22) line(g, "#5a4020", 2.4, -5, y, 5, y + 2);
}

/** A command pennon (麾) on a short staff (袁紹). */
function pennon(g: G, color: string): void {
  cel(
    g,
    path((p) => p.roundRect(-3, -230, 6, 240, 3)),
    "#3a2414",
    "#20150f",
    2,
    WOOD,
    LW2,
  );
  const f = path((p) => {
    p.moveTo(3, -226);
    p.quadraticCurveTo(50, -230, 70, -214);
    p.lineTo(60, -192);
    p.lineTo(74, -170);
    p.quadraticCurveTo(40, -176, 3, -168);
    p.closePath();
  });
  cel(g, f, color, mix(color, "#000000", 0.4), 4, mix(color, "#ffffff", 0.35), LW);
  cel(g, ellipse(0, -232, 5, 5), GOLD, GOLD_DARK, 1, GOLD_LIGHT, 1.4);
}

/** A lantern on a short pole (蹇圖 out after the curfew). */
function lantern(g: G): void {
  line(g, INK, 5, 0, 40, 0, -60);
  line(g, WOOD, 2.4, 0, 40, 0, -60);
  const l = path((p) => p.roundRect(-20, -54, 40, 48, 12));
  cel(g, l, "#f1d48a", "#c9a24a", 4, "#fff2c4", LW);
  inside(g, l, () => {
    for (const y of [-42, -30, -18]) line(g, alpha("#8a6a2c", 0.8), 1.4, -20, y, 20, y);
  });
}

/** A gold belt plaque (the steppe's gold), held up. */
function plaque(g: G): void {
  const b = path((p) => p.roundRect(-26, -18, 52, 36, 6));
  cel(g, b, GOLD, GOLD_DARK, 4, GOLD_LIGHT, LW);
  inside(g, b, () => {
    curve(g, GOLD_DARK, 2, -18, 8, -6, -12, 6, 4, 14, -10, 20, 8);
    dot(g, GOLD_DARK, -12, -6, 2.4);
  });
}

/** The edict in a sash: a slip of silk tucked in the belt (獻帝's secret edict, the novel). */
function edict(g: G): void {
  const s = path((p) => p.roundRect(-10, -50, 20, 90, 4));
  cel(g, s, "#f1ead9", "#cdc3ad", 3, "#ffffff", LW);
  inside(g, s, () => {
    for (let y = -42; y < 34; y += 8) line(g, alpha("#b3262a", 0.7), 1.4, -4, y, 4, y);
  });
}

/** Coins dropping from an open hand (張邈's generosity). */
function coins(g: G): void {
  for (let i = 0; i < 8; i++) {
    const x = -26 + (i % 4) * 16 + (i > 3 ? 8 : 0);
    const y = -10 + Math.floor(i / 4) * 26 + (i % 2) * 6;
    cel(g, ellipse(x, y, 7, 6, 0.3), BRONZE, BRONZE_DARK, 1.5, BRONZE_LIGHT, 1.6);
    g.fillStyle = INK;
    g.fillRect(x - 1.6, y - 1.6, 3.2, 3.2);
  }
}

/** A riding whip (曹純's 虎豹騎). */
function whip(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-4, -10, 8, 50, 3)),
    "#3a2a20",
    "#20150f",
    2,
    undefined,
    LW2,
  );
  g.lineCap = "round";
  curve(g, INK, 5, 0, -10, -10, -60, 10, -110);
  curve(g, "#6b4529", 2.6, 0, -10, -10, -60, 10, -110);
}

/** A broken fetter (臧霸 freed his father from it). */
function fetter(g: G): void {
  const ring = ellipse(0, 0, 22, 18);
  stroke(g, INK, 10, ring);
  stroke(g, "#8a9098", 5.4, ring);
  for (let i = 0; i < 3; i++) {
    const l = ellipse(30 + i * 16, 10 + i * 6, 9, 5, 0.4);
    stroke(g, INK, 5, l);
    stroke(g, "#8a9098", 2.6, l);
  }
}

/** A sealed scroll of silk on its rod. */
function scroll(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-14, -46, 28, 92, 10)),
    "#efe2c0",
    "#c9b88f",
    4,
    "#fff6de",
    LW,
  );
  for (const y of [-50, 46]) cel(g, ellipse(0, y, 18, 6), "#3a2a20", "#20150f", 2, undefined, LW2);
  line(g, INK, 5, -16, 0, 16, 2);
  line(g, "#b3262a", 2.4, -16, 0, 16, 2);
}

/** A map with a red mark (許攸's 烏巢). */
function map(g: G): void {
  const m = path((p) => {
    p.moveTo(-44, -30);
    p.lineTo(40, -36);
    p.lineTo(44, 30);
    p.lineTo(-40, 36);
    p.closePath();
  });
  cel(g, m, "#e8d9b4", "#bfa978", 4, "#fbf2dc", LW);
  curve(g, alpha("#5a7ab0", 0.8), 3, -40, 10, -10, -4, 40, 16);
  curve(g, alpha("#5a3a1c", 0.7), 1.6, -30, -20, 0, -10, 30, -24);
  dot(g, "#b3262a", 14, -12, 5);
}

/** The official seal on its cord (印綬): a bronze block with a knob (陶謙's 徐州). */
function officialSeal(g: G): void {
  g.lineCap = "round";
  curve(g, INK, 7, 0, -10, -30, 30, -8, 60);
  curve(g, "#7a3fa0", 4, 0, -10, -30, 30, -8, 60);
  cel(
    g,
    path((p) => p.roundRect(-16, -6, 32, 26, 3)),
    BRONZE,
    BRONZE_DARK,
    3,
    BRONZE_LIGHT,
    LW,
  );
  cel(g, ellipse(0, -10, 9, 7), BRONZE, BRONZE_DARK, 2, BRONZE_LIGHT, LW2);
}

/** A paper talisman with a red seal (張角's healing charms). */
function talisman(g: G): void {
  cel(
    g,
    path((p) => p.roundRect(-12, -40, 24, 70, 2)),
    "#efe2c0",
    "#c9b88f",
    3,
    undefined,
    LW2,
  );
  for (let i = 0; i < 4; i++) line(g, "#b3262a", 2, -6, -30 + i * 14, 6, -26 + i * 14);
}

export function frontGear(g: G, L: Look): void {
  // Turned away, his hands are out of sight.
  if (L.away) return;
  const t = L.t;
  const capped =
    L.hat === "jx1" ||
    L.hat === "jx2" ||
    L.hat === "jx3" ||
    L.hat === "zeRoof" ||
    L.hat === "zeFlat" ||
    L.hat === "faguan";
  const prop: Prop | undefined =
    t.front ??
    (t.dress === "rebel" && L.fam === "strategist"
      ? "staff"
      : L.fam === "strategist" && L.hat !== "mian"
        ? capped
          ? "slips"
          : "fan"
        : undefined);
  const at = (x: number, y: number, rot: number, draw: () => void) => held(g, L, x, y, rot, draw);
  switch (prop) {
    case "fan":
      at(222, 292, -0.35, () => fan(g));
      break;
    case "slips":
      at(212, 300, -0.42, () => slips(g, L.P.tassel));
      break;
    case "book":
      at(70, 314, 0.5, () => {
        g.scale(0.62, 0.62);
        slips(g, L.P.tassel);
      });
      break;
    case "tablet":
      at(196, 300, -0.12, () => tablet(g));
      break;
    case "staff":
      at(226, 340, -0.1, () => staff(g));
      break;
    case "talisman":
      at(206, 292, -0.2, () => talisman(g));
      break;
    case "seal":
      at(196, 296, -0.1, () => officialSeal(g));
      break;
    case "jadeSeal":
      at(194, 282, -0.12, () => seal(g));
      break;
    case "cudgel":
    case "staves":
      at(222, 344, -0.22, () => cudgel(g, prop === "staves"));
      break;
    case "bells":
      mirrored(g, L, () => bells(g));
      break;
    case "handJi":
      at(150, 330, 0, () => handJi(g, L.P));
      break;
    case "cup":
      at(204, 290, -0.1, () => cup(g));
      break;
    case "cash":
      at(70, 300, 0.2, () => cash(g));
      break;
    case "purse":
      at(200, 306, 0.05, () => purse(g));
      break;
    case "tassel":
      at(128, 296, 0, () => tassel(g));
      break;
    case "crossbow":
      at(186, 288, -0.2, () => crossbow(g));
      break;
    case "torch":
      at(222, 330, -0.15, () => torch(g));
      break;
    case "sword":
      at(200, 330, -0.04, () => sword(g));
      break;
    case "sabre":
      at(214, 330, -0.18, () => sabre(g));
      break;
    case "rope":
      at(200, 300, 0, () => rope(g));
      break;
    case "jar":
      at(206, 300, 0, () => jar(g));
      break;
    case "chain":
      at(128, 312, 0, () => chain(g));
      break;
    case "fish":
      at(196, 300, -0.08, () => fish(g));
      break;
    case "compass":
      at(206, 292, 0, () => compass(g));
      break;
    case "model":
      at(204, 294, 0, () => model(g));
      break;
    case "jie":
      at(232, 330, -0.05, () => jie(g));
      break;
    case "brush":
      at(200, 296, -0.15, () => brush(g));
      break;
    case "reins":
      at(196, 300, 0, () => reins(g));
      break;
    case "ingot":
      at(200, 300, -0.1, () => ingot(g));
      break;
    case "cane":
      at(214, 330, -0.12, () => cane(g));
      break;
    case "plan":
      at(196, 296, -0.15, () => plan(g));
      break;
    case "shield":
      at(170, 300, -0.05, () => shield(g, L.P));
      break;
    case "rod":
      at(224, 330, -0.06, () => rod(g));
      break;
    case "pennon":
      at(226, 330, -0.05, () => pennon(g, L.t.accent === "gold" ? GOLD : L.P.robe));
      break;
    case "lantern":
      at(208, 300, 0, () => lantern(g));
      break;
    case "plaque":
      at(196, 296, -0.15, () => plaque(g));
      break;
    case "edict":
      at(176, 300, 0.1, () => edict(g));
      break;
    case "coins":
      at(200, 296, 0, () => coins(g));
      break;
    case "whip":
      at(208, 320, -0.3, () => whip(g));
      break;
    case "fetter":
      at(190, 300, 0, () => fetter(g));
      break;
    case "scroll":
      at(206, 300, -0.3, () => scroll(g));
      break;
    case "map":
      at(190, 296, -0.15, () => map(g));
      break;
    default:
      break;
  }
}
