/**
 * The figure's skeleton for one key pose (ADR 0073): where the hips, chest,
 * head, elbows, hands, knees and feet sit in body space, solved once per
 * painted frame. Chunky proportions for 48–64 px tiles: a big head (about a
 * third of the height) for the headgear and beard to read, short legs, the
 * feet on the ground at the origin.
 */
import { add, bend, mul, norm, reach, rotX, rotY, rotZ, type V3 } from "./geom.ts";
import type { SpriteLook } from "./look.ts";
import type { Eyes, Facing, Mouth, PoseSpec, Smear } from "./poses.ts";

/** Standing proportions, in tiles, before the build scales them. */
export const BODY = {
  hipY: -0.3,
  hipX: 0.078,
  thigh: 0.155,
  shin: 0.15,
  /** The middle of the shoulders above the hips. */
  chest: 0.285,
  shoulderX: 0.172,
  upperArm: 0.135,
  foreArm: 0.13,
  hand: 0.043,
  /** The head's centre above the middle of the shoulders, and its radius. */
  headUp: 0.255,
  headR: 0.232,
  /** A rider's hips above a man's standing hips. */
  saddle: 0.2,
};

export interface Joints {
  scale: number;
  pelvis: V3;
  chest: V3;
  neck: V3;
  head: V3;
  /** Torso pitch, yaw and roll (lean, twist, tilt). */
  lean: number;
  twist: number;
  tilt: number;
  /** Head pitch, yaw and roll, added to the torso's. */
  hp: number;
  hy: number;
  hr: number;
  shR: V3;
  shL: V3;
  elR: V3;
  elL: V3;
  haR: V3;
  haL: V3;
  hipR: V3;
  hipL: V3;
  knR: V3;
  knL: V3;
  ftR: V3;
  ftL: V3;
  /** Directions of the weapon in the right hand and the item in the left (body space). */
  wDir: V3 | null;
  oDir: V3 | null;
  /** The torso's frame: its axes in body space. */
  ax: V3;
  ay: V3;
  az: V3;
  eyes: Eyes;
  mouth: Mouth;
  smear: (Omit<Smear, "fromHand"> & { fromDir: V3; fromHand: V3 | null }) | null;
  facing: Facing;
  beam: number;
  bow: "drawn" | "loosed" | null;
}

/** Turns torso-frame vectors into body space. */
function torsoFrame(lean: number, twist: number, tilt: number): (v: V3) => V3 {
  return (v) => rotY(rotX(rotZ(v, tilt), lean), twist);
}

/** Solves a pose for a look. `mounted` raises the rider into the saddle. */
export function solve(
  look: SpriteLook,
  pose: PoseSpec,
  facing: Facing,
  opts: { mounted: boolean; scale?: number } = { mounted: false },
): Joints {
  const k = (opts.scale ?? 1) * look.scale;
  const sw = look.shoulders;
  const lean = pose.lean ?? 0;
  const twist = pose.twist ?? 0;
  const tilt = pose.tilt ?? 0;
  const T = torsoFrame(lean, twist, tilt);
  const shift = pose.shift ?? [0, 0, 0];
  const lift = (pose.hop ?? 0) + (opts.mounted ? -BODY.saddle * k : 0);
  const base: V3 = [shift[0] * k, lift, shift[2] * k];
  const pelvis: V3 = add(base, [0, BODY.hipY * k + (pose.sink ?? 0) * k, 0]);
  const chest = add(pelvis, mul(T([0, -BODY.chest, 0]), k));
  const neck = add(pelvis, mul(T([0, -BODY.chest - 0.05, 0]), k));
  const head = add(pelvis, mul(T([0, -BODY.chest - BODY.headUp, 0]), k));
  const sh = (s: number): V3 => add(chest, mul(T([s * BODY.shoulderX * sw, 0.012, 0]), k));
  const shR = sh(-1);
  const shL = sh(1);
  const armMax = (BODY.upperArm + BODY.foreArm) * k;
  // Hands are placed for standard shoulders, then spread with them.
  const spread = (BODY.shoulderX / 0.158) * sw * 1.1;
  const handAt = (v: V3 | undefined, s: number): V3 => {
    const h = v ?? [s * 0.2, 0.235, 0.03];
    return add(chest, mul(T([h[0] * spread, h[1], h[2]]), k));
  };
  const haR = reach(shR, handAt(pose.handR, -1), armMax);
  const haL = reach(shL, handAt(pose.handL, 1), armMax);
  // Elbows point out, down and back.
  const elR = bend(shR, haR, BODY.upperArm * k, BODY.foreArm * k, T([-0.7, 0.35, -0.5]));
  const elL = bend(shL, haL, BODY.upperArm * k, BODY.foreArm * k, T([0.7, 0.35, -0.5]));
  // Legs: hips over the feet, knees forward. A rider's feet hang at the horse's flanks.
  const hip = (s: number): V3 => add(pelvis, mul(rotY([s * BODY.hipX, 0, 0], twist * 0.4), k));
  const hipR = hip(-1);
  const hipL = hip(1);
  const legMax = (BODY.thigh + BODY.shin) * k;
  const foot = (v: V3 | undefined, s: number): V3 => {
    if (opts.mounted) return add(pelvis, mul([s * 0.19, 0.21, 0.06], k));
    const f = v ?? [s * 0.075, 0, 0];
    return add(base, mul(f, k));
  };
  const ftR = reach(hipR, foot(pose.footR, -1), legMax);
  const ftL = reach(hipL, foot(pose.footL, 1), legMax);
  const knee = (h: V3, f: V3, forced: V3 | undefined, s: number): V3 => {
    if (forced) return add(base, mul(forced, k));
    if (opts.mounted) return add(h, mul([s * 0.17, 0.05, 0.11], k));
    return bend(h, f, BODY.thigh * k, BODY.shin * k, [s * 0.15, 0, 1]);
  };
  const knR = knee(hipR, ftR, pose.kneeR, -1);
  const knL = knee(hipL, ftL, pose.kneeL, 1);
  const smear = pose.smear
    ? {
        ...pose.smear,
        fromDir: norm(T(pose.smear.from)),
        fromHand: pose.smear.fromHand ? add(chest, mul(T(pose.smear.fromHand), k)) : null,
      }
    : null;
  const head3 = pose.head ?? [0, 0, 0];
  return {
    scale: k,
    pelvis,
    chest,
    neck,
    head,
    lean,
    twist,
    tilt,
    hp: head3[0],
    hy: head3[1],
    hr: head3[2],
    shR,
    shL,
    elR,
    elL,
    haR,
    haL,
    hipR,
    hipL,
    knR,
    knL,
    ftR,
    ftL,
    wDir: pose.w ? norm(T(pose.w)) : null,
    oDir: pose.o ? norm(T(pose.o)) : null,
    ax: T([1, 0, 0]),
    ay: T([0, 1, 0]),
    az: T([0, 0, 1]),
    eyes: pose.eyes ?? "open",
    mouth: pose.mouth ?? "shut",
    smear,
    facing,
    beam: pose.beam ?? 0,
    bow: pose.bow ?? null,
  };
}
