/**
 * Weather and ambience over the battlefield: sun shafts and drifting dust in
 * clear weather, cloud shadows when overcast, rain with the odd lightning flash,
 * and leaves carried by the wind. Purely visual; the rules own the weather.
 */
import type { WeatherKind } from "@tk/rules";
import { Container, Graphics, Sprite } from "pixi.js";
import { Particles } from "./particles.ts";
import type { FxTextures } from "./textures.ts";

export class Ambient {
  /** World space, above the units (shafts, dust, leaves, cloud shadows). */
  readonly world = new Container();
  /** Screen space (rain, lightning). */
  readonly screen = new Container();
  private dust = new Particles();
  private rain = new Particles();
  private shafts: Sprite[] = [];
  private clouds: Sprite[] = [];
  private flash = new Graphics();
  private weather: WeatherKind = "sunny";
  private mapW = 0;
  private mapH = 0;
  private tile = 48;
  private sw = 0;
  private sh = 0;
  private clock = 0;
  private emitAcc = 0;
  private nextBolt = 9000;
  private boltAge = -1;

  constructor(
    private readonly tex: FxTextures,
    private readonly lite: boolean,
  ) {
    this.world.addChild(this.dust.layer);
    this.screen.addChild(this.rain.layer, this.flash);
    this.world.eventMode = "none";
    this.screen.eventMode = "none";
  }

  layout(mapW: number, mapH: number, tile: number, sw: number, sh: number): void {
    this.mapW = mapW;
    this.mapH = mapH;
    this.tile = tile;
    this.sw = sw;
    this.sh = sh;
    this.rebuild();
  }

  setWeather(kind: WeatherKind): void {
    if (kind === this.weather) return;
    this.weather = kind;
    this.rebuild();
  }

  private rebuild(): void {
    for (const s of [...this.shafts, ...this.clouds]) s.destroy();
    this.shafts = [];
    this.clouds = [];
    this.dust.clear();
    this.rain.clear();
    if (this.weather === "sunny" && !this.lite)
      for (let i = 0; i < 4; i++) {
        const s = new Sprite(this.tex.beam);
        s.anchor.set(0.5);
        s.blendMode = "add";
        s.tint = 0xffe9b8;
        s.rotation = -0.55;
        s.width = this.tile * (1.6 + i * 0.5);
        s.height = Math.hypot(this.mapW, this.mapH) * 1.1;
        s.position.set(this.mapW * (0.15 + i * 0.25), this.mapH * 0.45);
        this.world.addChildAt(s, 0);
        this.shafts.push(s);
      }
    if (this.weather === "cloudy" || this.weather === "rain")
      for (let i = 0; i < 3; i++) {
        const s = new Sprite(this.tex.cloud);
        s.anchor.set(0.5);
        s.alpha = this.weather === "rain" ? 0.45 : 0.32;
        s.width = this.tile * 9;
        s.height = this.tile * 5.6;
        s.position.set(this.mapW * (i / 3) + this.tile, this.mapH * (0.2 + 0.3 * i));
        this.world.addChildAt(s, 0);
        this.clouds.push(s);
      }
  }

  update(ms: number): void {
    this.clock += ms;
    const t = this.clock / 1000;
    for (const [i, s] of this.shafts.entries()) {
      s.alpha = 0.05 + 0.035 * Math.sin(t * 0.4 + i * 1.7);
      s.x += Math.sin(t * 0.15 + i) * 0.05;
    }
    for (const s of this.clouds) {
      s.x += (ms / 1000) * this.tile * 0.35;
      if (s.x - s.width / 2 > this.mapW) s.x = -s.width / 2;
    }
    this.emitAcc += ms;
    const step = 50;
    while (this.emitAcc >= step) {
      this.emitAcc -= step;
      this.emit();
    }
    this.dust.update(ms);
    this.rain.update(ms);
    this.lightning(ms);
  }

  private emit(): void {
    const k = this.lite ? 0.5 : 1;
    const area = (this.mapW * this.mapH) / (this.tile * this.tile);
    switch (this.weather) {
      case "sunny":
        if (Math.random() < 0.02 * area * k * 0.1)
          this.dust.emit({
            texture: this.tex.dot,
            x: Math.random() * this.mapW,
            y: Math.random() * this.mapH,
            count: 1,
            angle: [-1.9, -1.2],
            speed: [4, 12],
            life: [3500, 6500],
            scale: [0.08, 0.16],
            alpha: 0.55,
            tint: 0xfff3c4,
            additive: true,
            fadeIn: true,
          });
        break;
      case "wind":
        if (Math.random() < 0.6 * k)
          this.dust.emit({
            texture: this.tex.leaf,
            x: -10,
            y: Math.random() * this.mapH,
            count: 1,
            angle: [-0.25, 0.3],
            speed: [this.tile * 2.2, this.tile * 3.6],
            life: [6000, 9000],
            scale: [0.9, 1.4],
            spin: [-5, 5],
            tint: [0x8fb24d, 0xc2a43c, 0x6e8f38, 0xd08a3a],
            alpha: 0.9,
          });
        if (Math.random() < 0.25 * k)
          this.dust.emit({
            texture: this.tex.streak,
            x: Math.random() * this.mapW * 0.6,
            y: Math.random() * this.mapH,
            count: 1,
            angle: 0.05,
            speed: this.tile * 7,
            life: [700, 1100],
            scale: [2, 3],
            alpha: 0.14,
            align: true,
            fadeIn: true,
          });
        break;
      case "rain": {
        const n = Math.round(((this.sw * this.sh) / 30000) * k);
        for (let i = 0; i < n; i++)
          this.rain.emit({
            texture: this.tex.drop,
            x: Math.random() * (this.sw + 200),
            y: -20 - Math.random() * 60,
            count: 1,
            angle: [1.85, 1.95],
            speed: [this.sh * 1.4, this.sh * 1.8],
            life: [500, 800],
            scale: [0.7, 1.1],
            alpha: 0.5,
            tint: 0xd5e4ff,
            align: true,
          });
        if (Math.random() < 0.8 * k)
          this.dust.emit({
            texture: this.tex.ring,
            x: Math.random() * this.mapW,
            y: Math.random() * this.mapH,
            count: 1,
            speed: 0,
            life: 450,
            scale: 0.02,
            endScale: 9,
            alpha: 0.35,
            tint: 0xdfeaff,
          });
        break;
      }
      default:
        break;
    }
  }

  private lightning(ms: number): void {
    const f = this.flash;
    if (this.weather !== "rain") {
      f.clear();
      return;
    }
    this.nextBolt -= ms;
    if (this.nextBolt <= 0) {
      this.nextBolt = 9000 + Math.random() * 9000;
      this.boltAge = 0;
    }
    if (this.boltAge < 0) return;
    this.boltAge += ms;
    const a = this.boltAge < 80 ? 0.28 : this.boltAge < 140 ? 0.05 : this.boltAge < 220 ? 0.2 : 0;
    f.clear();
    if (a > 0) f.rect(0, 0, this.sw, this.sh).fill({ color: 0xe8f0ff, alpha: a });
    if (this.boltAge > 600) this.boltAge = -1;
  }
}
