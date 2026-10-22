import { describe, expect, it } from "bun:test";
import {
  CHAPTER_LIST,
  type ChapterDef,
  type Cmd,
  flagDomains,
  HEBEI,
  LUBU,
  P0_RULES,
  RISE,
  type StoryBattleDef,
  validateChapter,
} from "../src/index.ts";

const battles = CHAPTER_LIST.flatMap((ch) =>
  ch.steps.flatMap((s) => (s.kind === "battle" ? [s.battle] : [])),
);
const storyDuels = battles.flatMap((b) => (b.duels ?? []).map((d) => ({ battle: b.id, ...d })));

/** Every command of a script, `all` opened up. */
const flat = (cmds: readonly Cmd[]): Cmd[] =>
  cmds.flatMap((c) => (c.do === "all" ? [c, ...flat(c.cmds)] : [c]));

/** A battle of a chapter, by id. */
function battleOf(ch: ChapterDef, id: string): StoryBattleDef {
  for (const s of ch.steps) if (s.kind === "battle" && s.battle.id === id) return s.battle;
  throw new Error(`no battle ${id} in ${ch.id}`);
}

/** A chapter with one battle swapped for another version of it. */
function withBattle(ch: ChapterDef, b: StoryBattleDef): ChapterDef {
  return {
    ...ch,
    steps: ch.steps.map((s) =>
      s.kind === "battle" && s.battle.id === b.id ? { ...s, battle: b } : s,
    ),
  };
}

describe("story duels (ADR 0071)", () => {
  it("stages the famous duels in their battles, as history ends them", () => {
    expect(
      storyDuels.map((d) => [
        d.battle,
        d.a.join("+"),
        d.b,
        d.play ? "played" : "watched",
        d.outcome,
        d.fatal,
      ]),
    ).toEqual([
      ["sishui", "guan-yu", "hua-xiong", "watched", "a", true],
      ["sishui", "zhang-fei+guan-yu+liu-bei", "lu-bu", "watched", "bWithdraws", false],
      ["baima", "guan-yu", "yan-liang", "played", "a", true],
      ["yanjin", "guan-yu", "wen-chou", "played", "a", true],
      ["bailang", "zhang-liao", "ta-dun", "played", "a", true],
      ["bailang", "lu-bu", "ta-dun", "played", "a", true],
    ]);
  });

  it("every story duel names officers present on its battle's paths", () => {
    const domains = flagDomains(CHAPTER_LIST);
    for (const ch of CHAPTER_LIST)
      expect(validateChapter(P0_RULES, ch, domains).filter((p) => p.includes("duel"))).toEqual([]);
  });

  it("every story duel has talk when it starts and when it ends", () => {
    for (const b of battles)
      for (const d of b.duels ?? [])
        for (const at of ["duel", "duelEnd"])
          expect([
            d.id,
            at,
            b.talk.some((t) => t.when.at === at && "duel" in t.when && t.when.duel === d.id),
          ]).toEqual([d.id, at, true]);
  });

  it("허저 joins in 196, in the scene of his draw with 전위, before 완성", () => {
    const at = (pred: (s: ChapterDef["steps"][number]) => boolean) => LUBU.steps.findIndex(pred);
    const scene = at(
      (s) =>
        s.kind === "scene" &&
        flat(s.scene.script).some(
          (c) => c.do === "duel" && c.a === "dian-wei" && c.b === "xu-chu" && c.outcome === "draw",
        ),
    );
    const step = LUBU.steps[scene];
    if (step?.kind !== "scene") throw new Error("no 허저 scene");
    expect(step.scene.when).toContain("196");
    expect(step.scene.joins?.map((j) => j.officer)).toEqual(["xu-chu"]);
    expect(scene).toBeLessThan(at((s) => s.kind === "battle" && s.battle.id === "wancheng"));
    const joins = LUBU.steps.flatMap((s) =>
      s.kind === "scene" ? (s.scene.joins ?? []).map((j) => j.officer) : [],
    );
    expect(joins.filter((id) => id === "xu-chu")).toHaveLength(1);
  });
});

describe("validation catches broken story duels", () => {
  const domains = flagDomains(CHAPTER_LIST);

  it("a duel that names an officer absent on a path", () => {
    // Without its condition, 여포's duel at 백랑산 is also staged when 장료 serves instead.
    const bailang = battleOf(HEBEI, "bailang");
    const b: StoryBattleDef = {
      ...bailang,
      duels: (bailang.duels ?? []).map(({ if: _, ...d }) => d),
    };
    const problems = validateChapter(P0_RULES, withBattle(HEBEI, b), domains);
    expect(problems).toContain("battle bailang: duel bailang-lu-bu names unknown unit lu-bu");
    expect(problems).toContain(
      "battle bailang: duel bailang-zhang-liao names unknown unit zhang-liao",
    );
  });

  it("a challenger on the foe's team, a fatal draw, and talk for a duel that never happens", () => {
    const sishui = battleOf(RISE, "sishui");
    const b: StoryBattleDef = {
      ...sishui,
      duels: [
        // An ally (side 2) is on our team: he can't duel our own 조조.
        {
          id: "brothers-quarrel",
          a: ["zhang-fei"],
          b: "cao-cao",
          when: { kind: "round", round: 2 },
          play: false,
          outcome: "draw",
          fatal: true,
        },
        {
          id: "never",
          a: ["guan-yu"],
          b: "hua-xiong",
          when: { kind: "adjacent" },
          play: false,
          outcome: "a",
          fatal: true,
          if: { flag: "nobody", is: "here" },
        },
      ],
      talk: [...sishui.talk, { when: { at: "duel", duel: "never" }, lines: [{ text: "…" }] }],
    };
    const problems = validateChapter(P0_RULES, withBattle(RISE, b), domains);
    expect(problems).toContain(
      "battle sishui: duel brothers-quarrel: a challenger is on the foe's side",
    );
    expect(problems).toContain(
      "battle sishui: duel brothers-quarrel: nobody falls in a draw, so it can't be fatal",
    );
    expect(problems).toContain(
      "battle sishui: talk waits for duel never, which does not happen with these flags",
    );
  });

  it("a scene duel needs both officers on stage, and fights a few bouts", () => {
    const strip = (cmds: readonly Cmd[]): Cmd[] =>
      cmds
        .filter((c) => !(c.do === "enter" && c.who === "dian-wei"))
        .map((c) => (c.do === "all" ? { ...c, cmds: strip(c.cmds) } : c))
        .map((c) => (c.do === "duel" ? { ...c, bouts: 9 } : c));
    const broken: ChapterDef = {
      ...LUBU,
      steps: LUBU.steps.map((s) =>
        s.kind === "scene" && s.scene.id === "lubu-xu-chu"
          ? { ...s, scene: { ...s.scene, script: strip(s.scene.script) } }
          : s,
      ),
    };
    const problems = validateChapter(P0_RULES, broken, domains);
    expect(problems).toContain("scene lubu-xu-chu: duel dian-wei–xu-chu: dian-wei is not on stage");
    expect(problems).toContain("scene lubu-xu-chu: duel: bouts must be 1–5");
  });
});
