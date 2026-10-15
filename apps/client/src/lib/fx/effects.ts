/**
 * Battle effect recipes built from particles: strikes, misses, footsteps,
 * defeats, auras, burning tiles, and one look per stratagem school.
 */
import type { StratagemSchool } from "@tk/rules";
import type { Particles } from "./particles.ts";
import type { FxTextures } from "./textures.ts";

export interface FxContext {
  p: Particles;
  tex: FxTextures;
  tile: number;
}

export interface Point {
  x: number;
  y: number;
}

/** Sparks and a slash across the target; `angle` is the swing's direction. */
export function strikeHit(c: FxContext, at: Point, angle: number, crit: boolean): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.slash,
    x: at.x,
    y: at.y - t * 0.1,
    count: 1,
    speed: 0,
    life: 240,
    scale: (t / 128) * (crit ? 1.5 : 1.15),
    endScale: 1.25,
    rotation: angle + Math.PI / 4,
    tint: crit ? 0xffe27a : 0xffffff,
    additive: true,
  });
  c.p.emit({
    texture: c.tex.streak,
    x: at.x,
    y: at.y - t * 0.1,
    count: crit ? 22 : 12,
    angle: [angle - 0.9, angle + 0.9],
    speed: [t * 3, t * (crit ? 9 : 6)],
    drag: 0.02,
    life: [180, 380],
    scale: [0.35, 0.7],
    tint: crit ? [0xffffff, 0xffd23f, 0xff9a3c] : [0xffffff, 0xffe2a8],
    additive: true,
    align: true,
  });
  c.p.emit({
    texture: c.tex.glow,
    x: at.x,
    y: at.y - t * 0.1,
    count: 1,
    speed: 0,
    life: 220,
    scale: (t / 128) * (crit ? 2.6 : 1.6),
    endScale: 1.4,
    tint: crit ? 0xffd27a : 0xfff4e0,
    additive: true,
  });
  if (crit)
    c.p.emit({
      texture: c.tex.star,
      x: at.x,
      y: at.y - t * 0.1,
      count: 5,
      spread: t * 0.45,
      speed: [5, 20],
      life: [300, 520],
      scale: [0.4, 0.8],
      endScale: 0.2,
      tint: 0xfff6c8,
      additive: true,
    });
}

/** A whiff: a puff of dust and a streak past the target. */
export function strikeMiss(c: FxContext, at: Point, angle: number): void {
  const t = c.tile;
  dust(c, { x: at.x, y: at.y + t * 0.3 }, 5);
  c.p.emit({
    texture: c.tex.streak,
    x: at.x,
    y: at.y,
    count: 3,
    angle: [angle - 0.15, angle + 0.15],
    speed: t * 5,
    life: 200,
    scale: [0.8, 1.2],
    alpha: 0.6,
    align: true,
  });
}

/** Footstep dust. */
export function dust(c: FxContext, at: Point, n = 2): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.smoke,
    x: at.x,
    y: at.y,
    count: n,
    spread: t * 0.12,
    angle: [-Math.PI, 0],
    speed: [t * 0.2, t * 0.6],
    drag: 0.1,
    life: [380, 620],
    scale: (t / 64) * 0.35,
    endScale: 1.8,
    alpha: 0.35,
    tint: 0xd8c9a4,
  });
}

/** A unit falls: dark smoke, embers and a fading flash. */
export function defeat(c: FxContext, at: Point): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.smoke,
    x: at.x,
    y: at.y,
    count: 10,
    spread: t * 0.25,
    angle: [-2.6, -0.5],
    speed: [t * 0.3, t * 0.9],
    drag: 0.2,
    life: [700, 1200],
    scale: (t / 64) * 0.5,
    endScale: 2.2,
    alpha: 0.55,
    tint: [0x2b2622, 0x4a403a],
  });
  c.p.emit({
    texture: c.tex.dot,
    x: at.x,
    y: at.y,
    count: 14,
    spread: t * 0.3,
    angle: [-2.4, -0.7],
    speed: [t * 0.6, t * 1.6],
    gravity: t * 0.4,
    life: [600, 1100],
    scale: [0.12, 0.22],
    tint: [0xffb347, 0xff7a2f, 0xffe08a],
    additive: true,
  });
}

/** A condition lands: a rising ring and motes in its colour. */
export function aura(c: FxContext, at: Point, color: number, rising: boolean): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.ring,
    x: at.x,
    y: at.y + t * 0.28,
    count: 1,
    speed: 0,
    life: 600,
    scale: (t / 128) * 0.4,
    endScale: 3,
    alpha: 0.85,
    tint: color,
    additive: true,
  });
  c.p.emit({
    texture: c.tex.dot,
    x: at.x,
    y: at.y + t * 0.2,
    count: 12,
    spread: t * 0.35,
    angle: rising ? [-1.9, -1.2] : [1.2, 1.9],
    speed: [t * 0.5, t * 1.2],
    drag: 0.3,
    life: [500, 900],
    scale: [0.12, 0.25],
    tint: color,
    additive: true,
    fadeIn: true,
  });
}

/** Healing: green-gold glints rising. */
export function heal(c: FxContext, at: Point): void {
  const t = c.tile;
  aura(c, at, 0x8cf58a, true);
  c.p.emit({
    texture: c.tex.star,
    x: at.x,
    y: at.y,
    count: 6,
    spread: t * 0.35,
    angle: [-1.8, -1.3],
    speed: [t * 0.4, t * 0.9],
    life: [600, 1000],
    scale: [0.25, 0.45],
    endScale: 0.3,
    tint: [0xd6ffb0, 0xfff1a8],
    additive: true,
    fadeIn: true,
  });
}

/** A burning tile, called a few times a second. */
export function flames(c: FxContext, at: Point): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.dot,
    x: at.x,
    y: at.y + t * 0.3,
    count: 2,
    spread: t * 0.32,
    angle: [-1.85, -1.3],
    speed: [t * 0.6, t * 1.2],
    life: [400, 750],
    scale: [0.35, 0.6],
    endScale: 0.15,
    tint: [0xff7a2f, 0xffb02e, 0xff5a1f],
    additive: true,
  });
  if (Math.random() < 0.35)
    c.p.emit({
      texture: c.tex.smoke,
      x: at.x,
      y: at.y - t * 0.1,
      count: 1,
      spread: t * 0.2,
      angle: [-1.8, -1.4],
      speed: [t * 0.4, t * 0.7],
      life: [900, 1400],
      scale: (t / 64) * 0.4,
      endScale: 2,
      alpha: 0.3,
      tint: 0x3a3330,
    });
}

/** The cast itself: a sigil under the caster in the school's colour. */
export function castSigil(c: FxContext, at: Point, color: number): void {
  const t = c.tile;
  c.p.emit({
    texture: c.tex.ring,
    x: at.x,
    y: at.y + t * 0.25,
    count: 2,
    speed: 0,
    life: 700,
    delay: [0, 180],
    scale: (t / 128) * 1.6,
    endScale: 0.4,
    alpha: 0.9,
    tint: color,
    additive: true,
  });
  c.p.emit({
    texture: c.tex.dot,
    x: at.x,
    y: at.y + t * 0.2,
    count: 16,
    spread: t * 0.5,
    angle: [-1.9, -1.2],
    speed: [t * 0.6, t * 1.4],
    life: [400, 800],
    scale: [0.1, 0.22],
    tint: color,
    additive: true,
  });
}

/** One school's effect on each tile of the area. */
export function schoolBurst(c: FxContext, school: StratagemSchool, cells: Point[]): void {
  const t = c.tile;
  for (const at of cells)
    switch (school) {
      case "fire":
        c.p.emit({
          texture: c.tex.glow,
          x: at.x,
          y: at.y,
          count: 1,
          speed: 0,
          life: 700,
          scale: (t / 128) * 2.2,
          endScale: 1.4,
          tint: 0xff8a3c,
          additive: true,
        });
        c.p.emit({
          texture: c.tex.dot,
          x: at.x,
          y: at.y + t * 0.3,
          count: 26,
          spread: t * 0.4,
          angle: [-1.9, -1.25],
          speed: [t * 1.2, t * 2.8],
          drag: 0.4,
          life: [450, 900],
          scale: [0.4, 0.8],
          endScale: 0.1,
          tint: [0xff5a1f, 0xff8a2f, 0xffc04a, 0xfff0a0],
          additive: true,
        });
        c.p.emit({
          texture: c.tex.smoke,
          x: at.x,
          y: at.y - t * 0.2,
          count: 4,
          spread: t * 0.3,
          angle: [-1.8, -1.35],
          speed: [t * 0.5, t],
          life: [900, 1500],
          delay: [150, 350],
          scale: (t / 64) * 0.5,
          endScale: 2.2,
          alpha: 0.4,
          tint: 0x2e2724,
        });
        break;
      case "water":
        c.p.emit({
          texture: c.tex.ring,
          x: at.x,
          y: at.y + t * 0.2,
          count: 3,
          speed: 0,
          delay: [0, 300],
          life: 650,
          scale: (t / 128) * 0.3,
          endScale: 6,
          alpha: 0.8,
          tint: 0x9fd8ff,
          additive: true,
        });
        c.p.emit({
          texture: c.tex.dot,
          x: at.x,
          y: at.y,
          count: 22,
          spread: t * 0.3,
          angle: [-2.6, -0.55],
          speed: [t * 1.5, t * 3.2],
          gravity: t * 9,
          life: [450, 750],
          scale: [0.15, 0.3],
          tint: [0x6fc3ff, 0xbfe6ff, 0x3f9fe8],
          additive: true,
        });
        break;
      case "earth":
        c.p.emit({
          texture: c.tex.rock,
          x: at.x,
          y: at.y - t * 2.2,
          count: 5,
          spread: t * 0.35,
          angle: [1.45, 1.7],
          speed: [t * 4, t * 6],
          gravity: t * 6,
          life: [380, 460],
          scale: [(t / 16) * 0.35, (t / 16) * 0.6],
          spin: [-8, 8],
          tint: [0x8a6e4e, 0x6f5a44, 0xa08466],
        });
        c.p.emit({
          texture: c.tex.smoke,
          x: at.x,
          y: at.y + t * 0.2,
          count: 6,
          spread: t * 0.35,
          angle: [-Math.PI, 0],
          speed: [t * 0.5, t * 1.2],
          drag: 0.1,
          delay: [320, 420],
          life: [600, 1000],
          scale: (t / 64) * 0.5,
          endScale: 2,
          alpha: 0.5,
          tint: 0xbfa985,
        });
        break;
      case "wind":
        for (let k = 0; k < 14; k++) {
          const a = (k / 14) * Math.PI * 2;
          c.p.emit({
            texture: c.tex.streak,
            x: at.x + Math.cos(a) * t * 0.45,
            y: at.y + Math.sin(a) * t * 0.3,
            count: 1,
            angle: a + Math.PI / 2,
            speed: t * 2.4,
            drag: 0.2,
            life: [380, 620],
            delay: k * 18,
            scale: [0.6, 0.9],
            alpha: 0.7,
            tint: [0xdffcf4, 0x8fe8d0],
            align: true,
            additive: true,
          });
        }
        c.p.emit({
          texture: c.tex.leaf,
          x: at.x,
          y: at.y,
          count: 6,
          spread: t * 0.3,
          speed: [t * 1, t * 2],
          spin: [-10, 10],
          life: [500, 800],
          scale: [0.6, 1],
          tint: [0x8fb24d, 0xc2a43c],
        });
        break;
      case "support":
        heal(c, at);
        break;
      case "disruption":
        c.p.emit({
          texture: c.tex.smoke,
          x: at.x,
          y: at.y,
          count: 7,
          spread: t * 0.3,
          speed: [t * 0.2, t * 0.6],
          drag: 0.2,
          life: [700, 1100],
          scale: (t / 64) * 0.45,
          endScale: 1.8,
          alpha: 0.6,
          tint: [0x7b4bb8, 0x5a2f8f, 0xb58cff],
        });
        c.p.emit({
          texture: c.tex.star,
          x: at.x,
          y: at.y - t * 0.3,
          count: 4,
          spread: t * 0.3,
          speed: [t * 0.2, t * 0.5],
          spin: [-6, 6],
          life: [500, 900],
          scale: [0.25, 0.4],
          tint: 0xe2c8ff,
          additive: true,
        });
        break;
    }
}
