// The painted figures' looks and key poses (ADR 0073): pure functions, checked without a canvas.
import { describe, expect, it } from "bun:test";
import { isFigure, spriteLook } from "../src/lib/fx/sprite/look.ts";
import { type Facing, FRAMES, type PoseName, poseFrames } from "../src/lib/fx/sprite/poses.ts";

const FACINGS: Facing[] = ["down", "up", "side"];
const POSES = Object.keys(FRAMES) as PoseName[];

describe("sprite looks", () => {
  it("are a pure function of unit, class and side", () => {
    const a = spriteLook("yuan-infantry-3", "infantry", 1);
    const b = spriteLook("yuan-infantry-3", "infantry", 1);
    expect(a.key).toBe(b.key);
    expect(spriteLook("yuan-infantry-3", "infantry", 0).key).not.toBe(a.key);
    expect(spriteLook("yuan-infantry-3", "heavyInfantry", 1).key).not.toBe(a.key);
  });

  it("carry each hero's own signature from his portrait", () => {
    const guan = spriteLook("guan-yu", "cavalry", 0);
    expect(guan.weapon).toBe("glaive");
    expect(guan.hat).toBe("hood");
    expect(guan.beard).toBe("bag");
    expect(guan.mounted).toBe(true);
    const lu = spriteLook("lu-bu", "cavalry", 1);
    expect(lu.weapon).toBe("crescent");
    expect(lu.hat).toBe("crownTails");
    expect(lu.coat?.[0]).toBe("#a8402a");
    expect(spriteLook("zhang-fei", "infantry", 2).weapon).toBe("snake");
    expect(spriteLook("dian-wei", "martialArtist", 0).grip).toBe("twin");
    const cao = spriteLook("cao-cao", "cavalry", 0);
    expect(cao.weapon).toBe("handJi");
    expect(cao.hat).toBe("qia");
    expect(spriteLook("xiahou-dun", "infantry", 0).marks.has("patch")).toBe(true);
  });

  it("dress soldiers in their army's kit and their class's gear", () => {
    expect(spriteLook("huangjin-infantry-1", "infantry", 1).hat).toMatch(/huangjin|helmYellow/);
    expect(spriteLook("cao-archer-1", "archer", 0).grip).toBe("bow");
    expect(spriteLook("cao-archer-1", "longbow", 0).weapon).toBe("longbow");
    expect(spriteLook("cao-cavalry-1", "shockCavalry", 0).barded).toBe(true);
    expect(spriteLook("cao-infantry-1", "heavyInfantry", 0).offhand).toBe("bigShield");
    expect(spriteLook("cao-catapult-1", "catapult", 0).grip).toBe("engine");
  });

  it("let riders get down", () => {
    expect(spriteLook("guan-yu", "cavalry", 0, { afoot: true }).mounted).toBe(false);
  });

  it("leave works to their own picture", () => {
    expect(isFigure("gate-1", "gate")).toBe(false);
    expect(isFigure("cao-cao", "cavalry")).toBe(true);
  });
});

describe("key poses", () => {
  it("give every pose its frame count in every facing and grip", () => {
    const grips = ["one", "pole", "twin", "bow", "crossbow", "fists", "fan", "engine"] as const;
    for (const grip of grips)
      for (const facing of FACINGS)
        for (const pose of POSES) expect(poseFrames(pose, grip, facing).length).toBe(FRAMES[pose]);
  });

  it("keep the classic's frame grammar", () => {
    expect([
      FRAMES.stand,
      FRAMES.walk,
      FRAMES.attack,
      FRAMES.guard,
      FRAMES.hurt,
      FRAMES.cheer,
      FRAMES.weak,
    ]).toEqual([1, 2, 4, 1, 1, 1, 2]);
  });
});
