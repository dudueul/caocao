/**
 * Key poses (ADR 0073): every frame of every pose, written once in body space
 * and projected for each facing. The frame grammar is the classic's, measured
 * and not copied: a one-frame stand, a two-frame step, a four-frame attack
 * (wind-up, strike, follow-through, recover) on larger cells, one frame each
 * for guard, hurt and cheer, a two-frame slump when weak. Scenes add held
 * poses (bow, kneel, sit, point, fall) and short sequences (nod, shake, jump,
 * run). Frames are painted whole; nothing is interpolated between them.
 *
 * Hands and weapons are placed in the torso's frame (relative to the middle
 * of the shoulders, before the torso leans or turns); feet on the ground.
 * The right hand (x < 0) holds the weapon.
 */
import type { V3 } from "./geom.ts";
import type { Grip } from "./look.ts";

export type Facing = "down" | "up" | "side";

export type Eyes = "open" | "shut" | "squeeze" | "wide" | "low";
export type Mouth = "shut" | "open" | "shout";

/** A strike's trail: an arc swept by the weapon's tip, a thrust's speed lines, a fan's gust, a fist's smear. */
export interface Smear {
  kind: "arc" | "thrust" | "gust" | "punch" | "shot" | "sling";
  /** The weapon's direction (torso frame) where the stroke began. */
  from: V3;
  /** Where the right hand was. */
  fromHand?: V3;
}

export interface PoseSpec {
  /** The whole figure lifted (negative) or set down. */
  hop?: number | undefined;
  /** The whole figure moved (a lunge, a recoil). */
  shift?: V3 | undefined;
  /** The hips lowered (a crouch, a kneel). */
  sink?: number | undefined;
  /** Torso pitch, forward positive. */
  lean?: number | undefined;
  /** Torso yaw, toward the left hand positive. */
  twist?: number | undefined;
  /** Torso roll. */
  tilt?: number | undefined;
  footR?: V3 | undefined;
  footL?: V3 | undefined;
  /** Knees forced onto the ground (kneeling, sitting). */
  kneeR?: V3 | undefined;
  kneeL?: V3 | undefined;
  handR?: V3 | undefined;
  handL?: V3 | undefined;
  /** The weapon's direction from the right hand. */
  w?: V3 | undefined;
  /** The left hand's item's direction. */
  o?: V3 | undefined;
  /** Head pitch (down positive), yaw (toward the left hand), roll. */
  head?: readonly [number, number, number] | undefined;
  eyes?: Eyes | undefined;
  mouth?: Mouth | undefined;
  smear?: Smear | undefined;
  /** The engine's beam angle (stone-throwers): 0 at rest, 1 cocked, 2 thrown high. */
  beam?: number | undefined;
  /** The bowstring drawn to the cheek, or just loosed. */
  bow?: "drawn" | "loosed" | undefined;
}

/** Every pose the painter draws, with its frames. */
export type PoseName =
  | "stand"
  | "walk"
  | "attack"
  | "guard"
  | "hurt"
  | "cheer"
  | "weak"
  | "bow"
  | "kneel"
  | "sit"
  | "point"
  | "down"
  | "nod"
  | "shake"
  | "jump"
  | "run";

/** Frames per pose. */
export const FRAMES: Record<PoseName, number> = {
  stand: 1,
  walk: 2,
  attack: 4,
  guard: 1,
  hurt: 1,
  cheer: 1,
  weak: 2,
  bow: 2,
  kneel: 1,
  sit: 1,
  point: 1,
  down: 1,
  nod: 1,
  shake: 2,
  jump: 2,
  run: 2,
};

/** Battle poses come first; scene poses are painted only when a scene asks for one. */
export const BATTLE_POSES: readonly PoseName[] = [
  "stand",
  "walk",
  "attack",
  "guard",
  "hurt",
  "cheer",
  "weak",
];
export const SCENE_POSES: readonly PoseName[] = [
  "bow",
  "kneel",
  "sit",
  "point",
  "down",
  "nod",
  "shake",
  "jump",
  "run",
];

// ---------------------------------------------------------------- shared limbs

const HANG_R: V3 = [-0.2, 0.235, 0.03];
const HANG_L: V3 = [0.2, 0.235, 0.03];
const FOOT_R: V3 = [-0.075, 0, 0];
const FOOT_L: V3 = [0.075, 0, 0];

/** Where the hands rest and the weapon points, by grip. */
function rest(grip: Grip): PoseSpec {
  switch (grip) {
    case "one":
      return { handR: [-0.21, 0.2, 0.09], w: [-0.36, -0.82, 0.44], handL: [0.2, 0.17, 0.1] };
    case "pole":
      return { handR: [-0.21, 0.16, 0.07], w: [-0.05, -1, 0.1], handL: [0.19, 0.22, 0.05] };
    case "twin":
      return {
        handR: [-0.21, 0.17, 0.08],
        w: [-0.25, -0.95, 0.15],
        handL: [0.21, 0.17, 0.08],
        o: [0.25, -0.95, 0.15],
      };
    case "bow":
      return { handR: [-0.19, 0.22, 0.05], handL: [0.2, 0.15, 0.12], o: [0.06, -1, 0.15] };
    case "crossbow":
      return { handR: [-0.08, 0.17, 0.14], handL: [0.1, 0.12, 0.17], w: [0.2, -0.05, 1] };
    case "fists":
      return { handR: [-0.19, 0.13, 0.1], handL: [0.19, 0.13, 0.1] };
    case "fan":
      return { handR: [-0.13, 0.1, 0.16], w: [-0.1, -1, 0.2], handL: [0.19, 0.22, 0.05] };
    case "engine":
      return { handR: HANG_R, handL: HANG_L };
  }
}

const merge = (...specs: PoseSpec[]): PoseSpec => Object.assign({}, ...specs);

// ---------------------------------------------------------------- battle poses

function stand(grip: Grip): PoseSpec[] {
  const wide =
    grip === "fists" ? { footR: [-0.1, 0, 0.02] as V3, footL: [0.1, 0, -0.01] as V3 } : {};
  return [merge(rest(grip), { footR: FOOT_R, footL: FOOT_L }, wide)];
}

/** Two crisp steps: one foot lifted and forward, the arms swinging against the legs. */
function walk(grip: Grip): PoseSpec[] {
  const r = rest(grip);
  const swing = (k: number): PoseSpec => {
    const handR = (r.handR ?? HANG_R) as V3;
    const handL = (r.handL ?? HANG_L) as V3;
    const free = grip === "bow" || grip === "pole" || grip === "one" || grip === "fan";
    return {
      footR: k > 0 ? [-0.07, -0.1, 0.02] : [-0.075, 0, -0.03],
      footL: k > 0 ? [0.075, 0, -0.03] : [0.07, -0.1, 0.02],
      handR: [handR[0], handR[1] - 0.01, handR[2] - 0.06 * k],
      handL: free ? [handL[0], handL[1] - 0.01, handL[2] + 0.07 * k] : handL,
      twist: 0.08 * k,
      tilt: -0.05 * k,
    };
  };
  return [merge(r, swing(1)), merge(r, swing(-1))];
}

/** The four attack frames for each grip. */
function attack(grip: Grip): PoseSpec[] {
  const stepIn = { footR: [-0.08, 0, 0.09] as V3, footL: [0.08, 0, -0.06] as V3 };
  switch (grip) {
    case "one": {
      const up: V3 = [-0.5, -0.62, -0.6];
      return [
        // Wind-up: the blade high over the shoulder, leaning back.
        {
          lean: -0.16,
          twist: -0.25,
          handR: [-0.13, -0.32, -0.08],
          w: up,
          handL: [0.17, 0.08, 0.17],
        },
        // Strike: a fast cut down and forward, stepping in.
        {
          ...stepIn,
          shift: [0, 0, 0.06],
          lean: 0.26,
          twist: 0.2,
          handR: [-0.09, 0.12, 0.27],
          w: [0.12, 0.4, 0.9],
          handL: [0.18, 0.12, 0.08],
          mouth: "shout",
          smear: { kind: "arc", from: up, fromHand: [-0.13, -0.32, -0.08] },
        },
        // Follow-through: the blade carried across and low.
        {
          ...stepIn,
          shift: [0, 0, 0.06],
          lean: 0.3,
          twist: 0.32,
          handR: [0.05, 0.2, 0.2],
          w: [0.75, 0.55, 0.35],
          handL: [0.2, 0.13, 0.04],
        },
        // Recover.
        {
          ...stepIn,
          lean: 0.08,
          handR: [-0.2, 0.08, 0.14],
          w: [-0.3, -0.75, 0.6],
          handL: [0.19, 0.14, 0.1],
        },
      ];
    }
    case "pole": {
      const back: V3 = [0.05, -0.2, 0.98];
      return [
        // Wind-up: the shaft drawn back level, the point toward the foe.
        {
          lean: -0.12,
          twist: -0.2,
          handR: [-0.16, 0.12, -0.12],
          handL: [0.04, 0.06, 0.12],
          w: back,
          footR: [-0.08, 0, -0.04],
          footL: [0.08, 0, 0.05],
        },
        // Strike: a long thrust, both arms out.
        {
          ...stepIn,
          shift: [0, 0, 0.1],
          lean: 0.24,
          twist: 0.12,
          handR: [-0.06, 0.08, 0.2],
          handL: [0.04, 0.04, 0.36],
          w: [0.02, 0.05, 1],
          mouth: "shout",
          smear: { kind: "thrust", from: back },
        },
        // Follow-through: still out, the point dipping.
        {
          ...stepIn,
          shift: [0, 0, 0.1],
          lean: 0.28,
          twist: 0.1,
          handR: [-0.06, 0.11, 0.2],
          handL: [0.04, 0.09, 0.35],
          w: [0.05, 0.3, 0.95],
        },
        // Recover: drawn back to the guard.
        {
          lean: 0.04,
          handR: [-0.18, 0.13, 0.03],
          handL: [0.08, 0.08, 0.15],
          w: [-0.05, -0.55, 0.85],
        },
      ];
    }
    case "twin": {
      const up: V3 = [-0.2, -0.85, -0.5];
      const upL: V3 = [0.2, -0.85, -0.5];
      return [
        {
          lean: -0.14,
          handR: [-0.15, -0.3, -0.04],
          handL: [0.15, -0.3, -0.04],
          w: up,
          o: upL,
          mouth: "open",
        },
        {
          ...stepIn,
          shift: [0, 0, 0.07],
          lean: 0.28,
          handR: [-0.1, 0.12, 0.26],
          handL: [0.1, 0.12, 0.26],
          w: [0.25, 0.45, 0.85],
          o: [-0.25, 0.45, 0.85],
          mouth: "shout",
          smear: { kind: "arc", from: up, fromHand: [-0.15, -0.3, -0.04] },
        },
        {
          ...stepIn,
          shift: [0, 0, 0.07],
          lean: 0.32,
          handR: [0.02, 0.18, 0.2],
          handL: [-0.02, 0.18, 0.2],
          w: [0.7, 0.55, 0.4],
          o: [-0.7, 0.55, 0.4],
        },
        merge(rest("twin"), { lean: 0.06 }),
      ];
    }
    case "bow": {
      // Draw to the cheek, loose, hold, lower.
      const aim = { handL: [0.08, -0.02, 0.27] as V3, o: [0.02, -1, 0.12] as V3, twist: -0.35 };
      return [
        {
          ...aim,
          bow: "drawn",
          handR: [-0.06, -0.06, 0.03],
          w: [0, 0, 1],
          footR: [-0.09, 0, -0.03],
          footL: [0.09, 0, 0.05],
        },
        {
          ...aim,
          handR: [-0.2, -0.09, -0.08],
          footR: [-0.09, 0, -0.03],
          footL: [0.09, 0, 0.05],
          eyes: "low",
          bow: "loosed",
          smear: { kind: "shot", from: [0, 0, 1] },
        },
        { ...aim, handR: [-0.22, -0.05, -0.06], footR: [-0.09, 0, -0.03], footL: [0.09, 0, 0.05] },
        merge(rest("bow"), { twist: -0.12 }),
      ];
    }
    case "crossbow": {
      const aim = { handR: [-0.06, -0.02, 0.18] as V3, handL: [0.06, -0.02, 0.3] as V3 };
      return [
        { ...aim, w: [0, -0.05, 1], twist: -0.1 },
        {
          handR: [-0.06, -0.05, 0.13],
          handL: [0.06, -0.06, 0.25],
          w: [0, -0.18, 1],
          lean: -0.1,
          shift: [0, 0, -0.03],
          smear: { kind: "shot", from: [0, 0, 1] },
        },
        { ...aim, w: [0, -0.1, 1], lean: -0.04 },
        rest("crossbow"),
      ];
    }
    case "fists":
      return [
        // Wind-up: low, the right fist drawn back.
        {
          sink: 0.05,
          lean: -0.06,
          twist: -0.35,
          handR: [-0.17, 0.1, -0.12],
          handL: [0.12, 0.02, 0.22],
          footR: [-0.11, 0, -0.06],
          footL: [0.1, 0, 0.08],
        },
        // Strike: a straight punch.
        {
          sink: 0.03,
          shift: [0, 0, 0.1],
          lean: 0.18,
          twist: 0.38,
          handR: [-0.03, 0.02, 0.4],
          handL: [0.17, 0.1, -0.02],
          footR: [-0.1, 0, 0.12],
          footL: [0.1, 0, -0.08],
          mouth: "shout",
          smear: { kind: "punch", from: [0, 0, 1], fromHand: [-0.17, 0.1, -0.12] },
        },
        // Follow-through: the other fist.
        {
          sink: 0.03,
          shift: [0, 0, 0.1],
          lean: 0.2,
          twist: -0.3,
          handR: [-0.15, 0.08, 0.06],
          handL: [0.04, 0.0, 0.38],
          footR: [-0.1, 0, 0.12],
          footL: [0.1, 0, -0.08],
          mouth: "open",
        },
        merge(stand("fists")[0] as PoseSpec, { sink: 0.02 }),
      ];
    case "fan": {
      const up: V3 = [-0.4, -0.85, -0.3];
      return [
        { lean: -0.1, twist: -0.3, handR: [-0.16, -0.3, -0.02], w: up, handL: [0.19, 0.2, 0.06] },
        {
          lean: 0.2,
          twist: 0.3,
          shift: [0, 0, 0.04],
          handR: [0.02, 0.06, 0.28],
          w: [0.7, 0.2, 0.7],
          handL: [0.19, 0.2, 0.0],
          mouth: "open",
          smear: { kind: "gust", from: up, fromHand: [-0.16, -0.3, -0.02] },
        },
        {
          lean: 0.22,
          twist: 0.36,
          shift: [0, 0, 0.04],
          handR: [0.12, 0.12, 0.2],
          w: [0.95, 0.25, 0.1],
          handL: [0.19, 0.2, -0.02],
        },
        rest("fan"),
      ];
    }
    case "engine":
      return [
        { beam: 1, handR: [-0.1, 0.12, 0.2], handL: [0.1, 0.12, 0.2], lean: -0.25 },
        {
          beam: 2,
          handR: [-0.1, 0.22, 0.12],
          handL: [0.1, 0.22, 0.12],
          lean: -0.38,
          smear: { kind: "sling", from: [0, 0, 1] },
        },
        { beam: 2.4, handR: [-0.1, 0.2, 0.14], handL: [0.1, 0.2, 0.14], lean: -0.1 },
        { beam: 0.4 },
      ];
  }
}

function guard(grip: Grip): PoseSpec[] {
  const brace = { sink: 0.03, lean: -0.05, shift: [0, 0, -0.03] as V3 };
  switch (grip) {
    case "one":
      return [
        { ...brace, handL: [0.07, -0.02, 0.24], handR: [-0.2, 0.12, 0.06], w: [-0.2, -0.9, 0.35] },
      ];
    case "pole":
    case "crossbow":
      return [
        {
          ...brace,
          handR: [-0.17, 0.0, 0.18],
          handL: [0.17, -0.04, 0.18],
          w: [0.98, -0.18, 0],
        },
      ];
    case "twin":
      return [
        {
          ...brace,
          handR: [-0.08, -0.02, 0.2],
          handL: [0.08, -0.02, 0.2],
          w: [0.55, -0.8, 0.2],
          o: [-0.55, -0.8, 0.2],
        },
      ];
    case "bow":
      return [
        { ...brace, handL: [0.05, -0.05, 0.22], o: [0.7, -0.7, 0.1], handR: [-0.2, 0.15, 0.05] },
      ];
    case "fists":
      return [{ ...brace, handR: [-0.05, -0.1, 0.2], handL: [0.05, -0.06, 0.22] }];
    case "fan":
      return [{ ...brace, handR: [-0.03, -0.12, 0.2], w: [0, -1, 0.15], handL: [0.19, 0.2, 0.05] }];
    case "engine":
      return [{ ...brace, handR: [-0.06, 0.0, 0.2], handL: [0.06, 0.0, 0.2] }];
  }
}

function hurt(grip: Grip): PoseSpec {
  const r = rest(grip);
  // Knocked back: the body thrown back and down, the head snapped back, arms flung.
  return {
    ...r,
    shift: [0, 0, -0.09],
    sink: 0.04,
    lean: -0.16,
    tilt: 0.12,
    head: [-0.3, 0.15, 0.15],
    handR: [-0.27, 0.06, -0.05],
    handL: grip === "one" ? [0.2, 0.0, 0.14] : [0.26, 0.04, -0.03],
    w: grip === "pole" ? [-0.45, -0.85, -0.2] : r.w ? [-0.75, 0.2, -0.35] : undefined,
    o: grip === "twin" ? [0.75, 0.2, -0.35] : r.o,
    footR: [-0.09, 0, 0.05],
    footL: [0.08, 0, -0.07],
    eyes: "squeeze",
    mouth: "open",
  } as PoseSpec;
}

function cheer(grip: Grip): PoseSpec {
  const r = rest(grip);
  const up = grip === "bow" ? {} : { w: [-0.25, -0.95, 0.05] as V3 };
  return {
    ...r,
    ...up,
    hop: -0.03,
    lean: -0.06,
    head: [-0.15, 0, 0],
    handR: [-0.2, -0.38, 0.04],
    handL: grip === "pole" || grip === "crossbow" ? [0.22, -0.32, 0.04] : [0.22, -0.36, 0.04],
    o: grip === "twin" ? [0.25, -0.95, 0.05] : r.o,
    mouth: "open",
    eyes: "wide",
    footR: [-0.09, 0, 0],
    footL: [0.09, 0, 0],
  };
}

function weak(grip: Grip): PoseSpec[] {
  const r = rest(grip);
  const slump = (k: number): PoseSpec => ({
    ...r,
    sink: 0.03 + 0.012 * k,
    lean: 0.32 + 0.06 * k,
    head: [0.28 + 0.05 * k, 0, 0.06],
    handR: [-0.18, 0.28 + 0.02 * k, 0.12],
    handL: [0.18, 0.28 + 0.02 * k, 0.1],
    w: r.w ? [-0.15, 0.7, 0.65] : undefined,
    o: grip === "twin" ? [0.15, 0.7, 0.65] : grip === "bow" ? [0.2, 0.9, 0.3] : r.o,
    footR: [-0.09, 0, 0.03],
    footL: [0.08, 0, -0.03],
    eyes: "low",
    mouth: k > 0 ? "open" : "shut",
  });
  return [slump(0), slump(1)] as PoseSpec[];
}

// ---------------------------------------------------------------- scene poses

/** Hands joined before the chest (揖): the weapon kept at the side. */
const JOINED = { handR: [-0.03, 0.06, 0.2] as V3, handL: [0.03, 0.06, 0.2] as V3 };

function scene(name: PoseName, grip: Grip, facing: Facing): PoseSpec[] {
  const r = rest(grip);
  const keep = {
    w: grip === "pole" ? ([-0.6, 0.3, 0.75] as V3) : r.w ? ([-0.5, 0.6, 0.6] as V3) : undefined,
  };
  switch (name) {
    case "bow":
      return [
        { ...r, ...JOINED, ...keep, lean: 0.22, head: [0.15, 0, 0] } as PoseSpec,
        { ...r, ...JOINED, ...keep, lean: 0.55, head: [0.3, 0, 0], eyes: "shut" } as PoseSpec,
      ];
    case "kneel":
      return [
        {
          ...r,
          ...JOINED,
          ...keep,
          sink: 0.13,
          lean: 0.14,
          head: [0.18, 0, 0],
          footL: [0.08, 0, 0.12],
          footR: [-0.08, 0, -0.17],
          kneeR: [-0.08, -0.01, 0.0],
        } as PoseSpec,
      ];
    case "sit":
      // Kneeling upright on the heels (跪坐), hands on the knees.
      return [
        {
          ...r,
          ...keep,
          sink: 0.19,
          lean: 0.02,
          handR: [-0.13, 0.27, 0.16],
          handL: [0.13, 0.27, 0.16],
          footR: [-0.07, 0, -0.14],
          footL: [0.07, 0, -0.14],
          kneeR: [-0.08, -0.01, 0.1],
          kneeL: [0.08, -0.01, 0.1],
        } as PoseSpec,
      ];
    case "point":
      return [
        {
          ...r,
          twist: 0.2,
          handL: [0.12, -0.12, 0.3],
          footL: [0.08, 0, 0.05],
          head: [-0.05, 0, 0],
          mouth: "open",
        },
      ];
    case "down":
      return [
        {
          ...r,
          handR: [-0.24, 0.1, -0.06],
          handL: [0.25, 0.16, 0.02],
          w: r.w ? [-0.9, 0.1, 0.4] : undefined,
          eyes: "shut",
          mouth: "open",
          head: [-0.2, facing === "side" ? 0 : 0.3, 0],
        } as PoseSpec,
      ];
    case "nod":
      return [{ ...r, head: [0.36, 0, 0], eyes: "shut" }];
    case "shake":
      return [
        { ...r, head: [0.04, -0.5, 0] },
        { ...r, head: [0.04, 0.5, 0] },
      ];
    case "jump":
      return [
        { ...r, sink: 0.07, lean: 0.18, handR: [-0.22, 0.2, -0.12], handL: [0.22, 0.2, -0.12] },
        {
          ...r,
          hop: -0.02,
          lean: -0.08,
          handR: [-0.2, -0.36, 0.05],
          handL: [0.2, -0.36, 0.05],
          w: r.w ? [-0.3, -0.9, 0.2] : undefined,
          footR: [-0.08, -0.08, -0.04],
          footL: [0.08, -0.06, 0.04],
          mouth: "open",
          eyes: "wide",
        } as PoseSpec,
      ];
    case "run": {
      const stride = (k: number): PoseSpec => ({
        ...r,
        lean: 0.22,
        twist: 0.12 * k,
        footR: k > 0 ? [-0.075, -0.07, 0.12] : [-0.075, -0.02, -0.12],
        footL: k > 0 ? [0.075, -0.02, -0.12] : [0.075, -0.07, 0.12],
        handR: [-0.2, 0.1, ((r.handR?.[2] ?? 0.05) as number) - 0.12 * k],
        handL: [0.2, 0.1, ((r.handL?.[2] ?? 0.05) as number) + 0.12 * k],
      });
      return [stride(1), stride(-1)];
    }
    default:
      return [r];
  }
}

/**
 * Seen from behind, a blow forward would hide behind the body: the strike is
 * carried higher, so the weapon shows over the head, as a drawn sheet would.
 */
function overhead(p: PoseSpec, i: number): PoseSpec {
  if (i !== 1 && i !== 2) return p;
  const lift = (v: V3 | undefined): V3 | undefined => (v ? [v[0], Math.min(v[1], -0.45), v[2]] : v);
  return { ...p, w: lift(p.w), o: lift(p.o) };
}

/**
 * Seen from the front, a bow aimed at the viewer is a line: it is held out to
 * the side and canted, as an archer stands side-on to loose.
 */
function aimAcross(p: PoseSpec, i: number): PoseSpec {
  if (i > 2) return p;
  return { ...p, handL: [0.24, -0.04, 0.2], o: [0.55, -0.83, 0.1], twist: -0.55 };
}

/** The frames of a pose for a grip, seen from a facing. */
export function poseFrames(name: PoseName, grip: Grip, facing: Facing): PoseSpec[] {
  if (name === "attack" && facing === "up") return attack(grip).map(overhead);
  if (name === "attack" && facing === "down" && grip === "bow") return attack(grip).map(aimAcross);
  switch (name) {
    case "stand":
      return stand(grip);
    case "walk":
      return walk(grip);
    case "attack":
      return attack(grip);
    case "guard":
      return guard(grip);
    case "hurt":
      return [hurt(grip)];
    case "cheer":
      return [cheer(grip)];
    case "weak":
      return weak(grip);
    default:
      return scene(name, grip, facing);
  }
}
