/**
 * A small particle system for battle effects: sparks, embers, smoke, droplets,
 * leaves, rain. Particles are plain sprites in one container, updated each frame.
 * Display only: it uses Math.random and never touches the rules.
 */
import { Container, Sprite, type Texture } from "pixi.js";

type Range = number | readonly [number, number];

export interface Burst {
  texture: Texture;
  x: number;
  y: number;
  count: number;
  /** Start position jitter (radius, px). */
  spread?: number;
  /** Direction in radians (0 = right, −π/2 = up). */
  angle?: Range;
  /** px per second. */
  speed?: Range;
  /** ms. */
  life?: Range;
  /** Start and end scale. */
  scale?: Range;
  endScale?: number;
  alpha?: number;
  tint?: number | readonly number[];
  /** px per second², downwards. */
  gravity?: number;
  /** Fraction of speed kept per second. */
  drag?: number;
  /** Radians per second. */
  spin?: Range;
  additive?: boolean;
  /** Point along the motion (streaks). */
  align?: boolean;
  /** Fixed rotation (radians) instead of a random one. */
  rotation?: number;
  /** Fade in over the first part of life too. */
  fadeIn?: boolean;
  /** ms before the particle appears. */
  delay?: Range;
}

interface Particle {
  s: Sprite;
  vx: number;
  vy: number;
  gravity: number;
  drag: number;
  spin: number;
  life: number;
  age: number;
  scale0: number;
  scale1: number;
  alpha: number;
  align: boolean;
  fadeIn: boolean;
}

const pick = (r: Range | undefined, fallback: number): number =>
  r === undefined ? fallback : typeof r === "number" ? r : r[0] + Math.random() * (r[1] - r[0]);

export class Particles {
  readonly layer = new Container();
  private live: Particle[] = [];

  /** Particles alive now (for tests and quality limits). */
  get count(): number {
    return this.live.length;
  }

  emit(b: Burst): void {
    for (let i = 0; i < b.count; i++) {
      const s = new Sprite(b.texture);
      s.anchor.set(0.5);
      const r = (b.spread ?? 0) * Math.sqrt(Math.random());
      const ra = Math.random() * Math.PI * 2;
      s.position.set(b.x + Math.cos(ra) * r, b.y + Math.sin(ra) * r);
      const a = pick(b.angle, Math.random() * Math.PI * 2);
      const v = pick(b.speed, 60);
      const tints = b.tint;
      s.tint =
        typeof tints === "number"
          ? tints
          : tints && tints.length > 0
            ? (tints[Math.floor(Math.random() * tints.length)] ?? 0xffffff)
            : 0xffffff;
      if (b.additive) s.blendMode = "add";
      const scale0 = pick(b.scale, 1);
      s.scale.set(scale0);
      s.alpha = b.fadeIn ? 0 : (b.alpha ?? 1);
      s.rotation = b.align ? a : (b.rotation ?? Math.random() * Math.PI * 2);
      const p: Particle = {
        s,
        vx: Math.cos(a) * v,
        vy: Math.sin(a) * v,
        gravity: b.gravity ?? 0,
        drag: b.drag ?? 1,
        spin: pick(b.spin, 0),
        life: pick(b.life, 600),
        age: -pick(b.delay, 0),
        scale0,
        scale1: b.endScale === undefined ? scale0 : scale0 * b.endScale,
        alpha: b.alpha ?? 1,
        align: b.align ?? false,
        fadeIn: b.fadeIn ?? false,
      };
      s.visible = p.age >= 0;
      this.layer.addChild(s);
      this.live.push(p);
    }
  }

  update(ms: number): void {
    const dt = ms / 1000;
    let w = 0;
    for (const p of this.live) {
      p.age += ms;
      if (p.age >= p.life) {
        p.s.destroy();
        continue;
      }
      this.live[w++] = p;
      if (p.age < 0) continue;
      p.s.visible = true;
      const k = p.age / p.life;
      if (p.drag !== 1) {
        const keep = p.drag ** dt;
        p.vx *= keep;
        p.vy *= keep;
      }
      p.vy += p.gravity * dt;
      p.s.x += p.vx * dt;
      p.s.y += p.vy * dt;
      if (p.align) p.s.rotation = Math.atan2(p.vy, p.vx);
      else p.s.rotation += p.spin * dt;
      p.s.scale.set(p.scale0 + (p.scale1 - p.scale0) * k);
      const fadeIn = p.fadeIn ? Math.min(1, k * 5) : 1;
      p.s.alpha = p.alpha * fadeIn * (k < 0.6 ? 1 : 1 - (k - 0.6) / 0.4);
    }
    this.live.length = w;
  }

  clear(): void {
    for (const p of this.live) p.s.destroy();
    this.live = [];
  }
}
