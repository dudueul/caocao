/**
 * Composing one frame (ADR 0073): solve the pose, turn the figure to its
 * facing, gather the draw items of the person, the horse or the engine and
 * its crew, the gear and the strike's trail, and paint them back to front.
 */
import { drawn, type G } from "./draw.ts";
import { crewSpots, engineItems } from "./engine.ts";
import { backItem, smearItem, weaponItems } from "./gear.ts";
import { type V3, View } from "./geom.ts";
import { drawHead, headSpace } from "./head.ts";
import type { SpriteLook } from "./look.ts";
import { horseItems } from "./mount.ts";
import { type Item, Proj, person } from "./person.ts";
import { type Facing, type PoseName, poseFrames } from "./poses.ts";
import { solve } from "./rig.ts";

/** The view for each facing: the side frames are a strong three-quarter turn to the right. */
const YAW: Record<Facing, number> = { down: 0, up: Math.PI, side: (72 * Math.PI) / 180 };
/** In side frames the head turns a little back toward the viewer, so the face reads. */
const SIDE_TURN = -0.6;
/** Forward points sit lower (the bird's-eye view), sideways ones a little. */
const KF = 0.75;
const KL = 0.12;

export function viewFor(facing: Facing): View {
  return new View(YAW[facing], KF, KL);
}

/** A crewman stands a little smaller than a soldier, beside his engine. */
const CREW_SCALE = 0.68;

function personItems(
  L: SpriteLook,
  name: PoseName,
  index: number,
  facing: Facing,
  V: View,
  o: { origin?: V3; scale?: number; grip?: SpriteLook["grip"] } = {},
): Item[] {
  const grip = o.grip ?? L.grip;
  const frames = poseFrames(name, grip, facing);
  const spec = frames[Math.min(index, frames.length - 1)] ?? {};
  const mounted = L.mounted;
  const J = solve(L, spec, facing, { mounted, ...(o.scale ? { scale: o.scale } : {}) });
  const P = new Proj(V, o.origin ?? [0, 0, 0]);
  const H = headSpace(L, J, P, facing === "side" ? SIDE_TURN : 0);
  const gearL: SpriteLook = grip === L.grip ? L : { ...L, grip, offhand: "none", back: "none" };
  const parts = {
    ...weaponItems(gearL, J, P),
    ...(gearL.back !== "none" ? { back: backItem(gearL, J, P) } : {}),
    head: (g: G) => drawHead(g, H),
  };
  const items = person(gearL, J, P, parts, mounted ? 0.06 : 0);
  if (mounted) {
    const gait =
      name === "walk" || name === "run"
        ? 1 + (index % 2)
        : name === "attack" && (index === 1 || index === 2)
          ? 3
          : 0;
    const shift = spec.shift ?? [0, 0, 0];
    items.push(...horseItems(L, P, gait, J.scale, [shift[0] * J.scale, 0, shift[2] * J.scale]));
  }
  const sm = smearItem(gearL, J, P);
  if (sm) items.push(sm);
  return items;
}

/** The draw items of one frame. */
export function frameItems(L: SpriteLook, name: PoseName, index: number, facing: Facing): Item[] {
  const V = viewFor(facing);
  if (L.fam !== "catapult") return personItems(L, name, index, facing, V);
  const frames = poseFrames(name, "engine", facing);
  const spec = frames[Math.min(index, frames.length - 1)] ?? {};
  const roll = name === "walk" || name === "run" ? index * 0.5 : 0;
  const items = engineItems(L, new Proj(V), spec.beam ?? 0, roll);
  for (const spot of crewSpots(L))
    items.push(
      ...personItems(L, name, index, facing, V, {
        origin: spot,
        scale: CREW_SCALE,
        grip: "engine",
      }),
    );
  return items;
}

/** Paints a frame with the context set to tiles, the feet at the origin. */
export function paintFrame(
  g: G,
  L: SpriteLook,
  name: PoseName,
  index: number,
  facing: Facing,
): void {
  // Riders get down to kneel, sit or lie.
  const look =
    L.mounted && (name === "kneel" || name === "sit" || name === "down")
      ? { ...L, mounted: false }
      : L;
  const items = frameItems(look, name, index, facing);
  items.sort((a, b) => a.d - b.d);
  const fallen = name === "down" && look.fam !== "catapult";
  g.save();
  if (fallen) g.transform(...FALL);
  g.lineJoin = "round";
  g.lineCap = "round";
  for (const it of items) {
    g.save();
    it.f(g);
    g.restore();
  }
  g.restore();
  if (fallen && drawn.any) {
    // The trim box, tipped over with the figure.
    const xs: number[] = [];
    const ys: number[] = [];
    for (const [x, y] of [
      [drawn.x0, drawn.y0],
      [drawn.x1, drawn.y0],
      [drawn.x0, drawn.y1],
      [drawn.x1, drawn.y1],
    ] as const) {
      xs.push(FALL[0] * x + FALL[2] * y + FALL[4]);
      ys.push(FALL[1] * x + FALL[3] * y + FALL[5]);
    }
    drawn.x0 = Math.min(...xs);
    drawn.x1 = Math.max(...xs);
    drawn.y0 = Math.min(...ys);
    drawn.y1 = Math.max(...ys);
  }
}

/**
 * Lying fallen: the standing figure tipped over backwards about its feet,
 * laid across its tile and foreshortened a little, as seen from above.
 */
const FALL: [number, number, number, number, number, number] = (() => {
  const a = -1.48;
  const c = Math.cos(a);
  const s = Math.sin(a);
  const sy = 0.82;
  // x' = c·x − s·y + tx; y' = sy·(s·x + c·y) + ty
  return [c, s * sy, -s, c * sy, 0.58, -0.08];
})();
