import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleEvent,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  legalCommands,
  type Ruleset,
  replay,
  runBattle,
  type ScriptedDuel,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

/** The fixture rules, with duel morale: heart for the winner's side, dismay for the loser's. */
const R: Ruleset = { ...RULES, duelMorale: { win: "rally", lose: "weaken" } };

const named = { named: true };
const ROWS = [".......", ".......", "......."];

function ok(state: BattleState, c: Command) {
  const r = apply(state, R, c);
  if (!r.ok) throw new Error(r.reason);
  return r;
}

function challenge(state: BattleState, a: string, b: string) {
  const u = unitById(state, a);
  return ok(state, {
    kind: "act",
    unit: a,
    to: { x: u.x, y: u.y },
    action: { kind: "duel", target: b },
  });
}

/** A bold strong foe always accepts, a timid weak one almost never does. */
const BOLD = { martial: 99, intellect: 1 };

describe("일기토 (ADR 0071)", () => {
  it("only named officers side by side can challenge, and not in the Arena", () => {
    const s = createBattle(
      R,
      setup(ROWS, [
        unit("hero", 0, "infantry", 2, 1, {}, named),
        unit("rival", 1, "infantry", 3, 1, {}, named),
        unit("troop", 1, "infantry", 1, 1),
        unit("far", 1, "infantry", 4, 2, {}, named),
      ]),
    );
    const duels = legalCommands(s, R).filter((c) => c.kind === "act" && c.action.kind === "duel");
    const targets = new Set(
      duels.map((c) => (c.kind === "act" && c.action.kind === "duel" ? c.action.target : "")),
    );
    expect(targets.has("rival")).toBe(true);
    expect(targets.has("troop")).toBe(false);
    // "far" can be challenged only after walking next to him.
    expect(targets.has("far")).toBe(true);
    expect(
      duels.some(
        (c) =>
          c.kind === "act" && c.action.kind === "duel" && c.action.target === "far" && c.to.x !== 2,
      ),
    ).toBe(true);
    const arena: Ruleset = { ...R, modes: { arena: { duelBouts: 0 } } };
    const a = createBattle(arena, { ...setup(ROWS, s.units.map(toSetup)), mode: "arena" });
    expect(legalCommands(a, arena).some((c) => c.kind === "act" && c.action.kind === "duel")).toBe(
      false,
    );
  });

  it("a refused challenge ends the challenger's action and shakes the refuser's side", () => {
    const s = createBattle(
      R,
      setup(ROWS, [
        unit("hero", 0, "infantry", 2, 1, { martial: 99 }, named),
        unit("coward", 1, "infantry", 3, 1, { martial: 10, intellect: 99 }, named),
        unit("other", 0, "infantry", 0, 0),
      ]),
    );
    const r = challenge(s, "hero", "coward");
    const c = r.events.find((e) => e.kind === "challenge");
    expect(c?.kind === "challenge" && c.accepted).toBe(false);
    expect(r.state.duel).toBeNull();
    expect(unitById(r.state, "hero").acted).toBe(true);
    expect(unitById(r.state, "coward").conditions.map((x) => x.id)).toContain("weaken");
  });

  it("while a duel is under way only bouts and withdrawing are legal; withdrawing leaves no loser", () => {
    const s = createBattle(
      R,
      setup(ROWS, [
        unit("hero", 0, "infantry", 2, 1, {}, named),
        unit("rival", 1, "infantry", 3, 1, BOLD, named),
      ]),
    );
    const r = challenge(s, "hero", "rival");
    expect(r.state.duel).not.toBeNull();
    expect(legalCommands(r.state, R).map((c) => c.kind)).toEqual([
      "bout",
      "bout",
      "bout",
      "withdraw",
    ]);
    expect(apply(r.state, R, { kind: "endPhase" }).ok).toBe(false);
    const w = ok(r.state, { kind: "withdraw" });
    const end = w.events.find((e) => e.kind === "duelEnd");
    expect(end?.kind === "duelEnd" && end.winner === null && end.withdrew === "hero").toBe(true);
    expect(w.state.duel).toBeNull();
    expect(unitById(w.state, "hero").acted).toBe(true);
  });

  it("a knockout fells the loser (he retreats) and swings hearts on both sides", () => {
    // Find a seed where the bold rival takes up the challenge (he refuses now and then).
    let s: BattleState | null = null;
    for (let seed = 0; !s?.duel; seed++) {
      const start = createBattle(
        R,
        setup(
          ROWS,
          [
            unit("hero", 0, "infantry", 2, 1, { martial: 99 }, named),
            unit("mate", 0, "infantry", 1, 1),
            unit("rival", 1, "infantry", 3, 1, { martial: 99, intellect: 1 }, named),
            unit("guard", 1, "infantry", 4, 1),
          ],
          { seed: `ko-${seed}` },
        ),
      );
      unitById(start, "rival").hp = 1;
      s = challenge(start, "hero", "rival").state;
    }
    const events: BattleEvent[] = [];
    // The foe's stance is drawn the same whatever ours is: take the stance that lands.
    for (let i = 0; i < 5 && s.duel; i++) {
      const now: BattleState = s;
      const tries = (["attack", "power", "guard"] as const).map((stance) =>
        ok(now, { kind: "bout", stance }),
      );
      const r =
        tries.find((t) => t.events.some((e) => e.kind === "bout" && e.damageB > 0)) ??
        (tries[0] as (typeof tries)[number]);
      events.push(...r.events);
      s = r.state;
    }
    const end = events.find((e) => e.kind === "duelEnd");
    expect(end?.kind === "duelEnd" && end.winner).toBe("hero");
    expect(unitById(s, "rival").hp).toBe(0);
    expect(unitById(s, "rival").fate).toBe("escaped");
    expect(unitById(s, "mate").conditions.map((c) => c.id)).toContain("rally");
    expect(unitById(s, "guard").conditions.map((c) => c.id)).toContain("weaken");
  });

  it("the stance triangle: 강공 breaks 공격, 방어 turns 강공 back, 공격 wears 방어 down", () => {
    for (let seed = 0; seed < 40; seed++) {
      const s = createBattle(
        R,
        setup(
          ROWS,
          [
            unit("hero", 0, "infantry", 2, 1, {}, named),
            unit("rival", 1, "infantry", 3, 1, BOLD, named),
          ],
          { seed: `tri-${seed}` },
        ),
      );
      const started = challenge(s, "hero", "rival").state;
      if (!started.duel) continue; // even the bold refuse now and then
      for (const stance of ["attack", "power", "guard"] as const) {
        const r = ok(started, { kind: "bout", stance });
        const b = r.events.find((e) => e.kind === "bout");
        if (b?.kind !== "bout") throw new Error("no bout");
        const wins =
          (b.stanceA === "power" && b.stanceB === "attack") ||
          (b.stanceA === "guard" && b.stanceB === "power") ||
          (b.stanceA === "attack" && b.stanceB === "guard");
        const loses =
          (b.stanceB === "power" && b.stanceA === "attack") ||
          (b.stanceB === "guard" && b.stanceA === "power") ||
          (b.stanceB === "attack" && b.stanceA === "guard");
        if (wins) expect([b.damageA, b.damageB > 0]).toEqual([0, true]);
        if (loses) expect([b.damageA > 0, b.damageB]).toEqual([true, 0]);
        if (b.stanceA === "guard" && b.stanceB === "guard")
          expect([b.damageA, b.damageB]).toEqual([0, 0]);
      }
    }
  });

  it("a played story duel is always taken up, can't be left, and ends as history did", () => {
    const script: ScriptedDuel = {
      id: "baima",
      a: ["guan-yu"],
      b: "yan-liang",
      when: { kind: "adjacent" },
      play: true,
      outcome: "a",
      fatal: true,
    };
    let s = createBattle(
      R,
      setup(
        ROWS,
        [
          unit("guan-yu", 0, "infantry", 2, 1, { martial: 60 }, named),
          unit("yan-liang", 1, "infantry", 3, 1, { martial: 90, intellect: 99 }, named),
        ],
        { duels: [script] },
      ),
    );
    s = challenge(s, "guan-yu", "yan-liang").state;
    expect(s.duel?.script).toBe("baima");
    expect(legalCommands(s, R).some((c) => c.kind === "withdraw")).toBe(false);
    const events: BattleEvent[] = [];
    // Even all-guard (which lands nothing against guard) ends with 안량 dead by the last bout.
    while (s.duel) {
      const r = ok(s, { kind: "bout", stance: "guard" });
      events.push(...r.events);
      s = r.state;
    }
    expect(unitById(s, "yan-liang").fate).toBe("killed");
    expect(unitById(s, "guan-yu").hp).toBeGreaterThan(0);
    expect(s.duels).toEqual([]);
    expect(events.filter((e) => e.kind === "bout").length).toBeLessThanOrEqual(5);
  });

  it("watched story duels fire on their own: next to the foe, on a round, or when one arrives", () => {
    const duels: ScriptedDuel[] = [
      {
        id: "wine",
        a: ["guan-yu"],
        b: "hua-xiong",
        when: { kind: "adjacent" },
        play: false,
        outcome: "a",
        fatal: true,
      },
      {
        id: "brothers",
        a: ["zhang-fei", "guan-yu", "liu-bei"],
        b: "lu-bu",
        when: { kind: "arrived", unit: "lu-bu" },
        play: false,
        outcome: "bWithdraws",
        fatal: false,
      },
    ];
    let s = createBattle(
      R,
      setup(
        ["..........", "..........", ".........."],
        [
          unit("cao-cao", 0, "infantry", 0, 0, {}, named),
          unit("guan-yu", 2, "infantry", 4, 1, { martial: 97 }, named),
          unit("zhang-fei", 2, "infantry", 0, 2, { martial: 98 }, named),
          unit("liu-bei", 2, "infantry", 1, 2, { martial: 75 }, named),
          unit("hua-xiong", 1, "infantry", 6, 1, { martial: 85 }, named),
          unit("lu-bu", 1, "cavalry", 9, 2, { martial: 100 }, { named: true, arrives: 2 }),
          unit("dong", 1, "infantry", 9, 0),
        ],
        { duels, teams: [0, 1, 0], order: [0, 2, 1], policy: "sidePhases" },
      ),
    );
    // The player's phase passes; the ally 관우 walks up to 화웅 and the duel fires.
    s = ok(s, { kind: "endPhase" }).state;
    const r = ok(s, {
      kind: "act",
      unit: "guan-yu",
      to: { x: 5, y: 1 },
      action: { kind: "wait" },
    });
    expect(r.events.some((e) => e.kind === "challenge" && e.script === "wine")).toBe(true);
    expect(unitById(r.state, "hua-xiong").fate).toBe("killed");
    s = r.state;
    // Play on to round 2: 여포 arrives and the three brothers meet him; he withdraws.
    const all: BattleEvent[] = [];
    while (s.round < 2 && s.winner === null) {
      const c = legalCommands(s, R).find((x) => x.kind === "endPhase") ?? legalCommands(s, R)[0];
      const step = ok(s, c as Command);
      all.push(...step.events);
      s = step.state;
    }
    const end = all.find((e) => e.kind === "duelEnd");
    expect(end?.kind === "duelEnd" && end.withdrew).toBe("lu-bu");
    expect(unitById(s, "lu-bu").hp).toBe(0);
    expect(["zhang-fei", "guan-yu", "liu-bei"].every((id) => unitById(s, id).hp > 0)).toBe(true);
    const fighters = new Set(all.flatMap((e) => (e.kind === "bout" ? [e.a] : [])));
    expect(fighters.size).toBe(3);
  });

  it("battles with duels replay to the same hash, and the AI never challenges on its own", () => {
    const start = createBattle(
      R,
      setup(
        ["........", "........", "........"],
        [
          unit("a1", 0, "infantry", 0, 1, { martial: 80 }, named),
          unit("a2", 0, "cavalry", 0, 0, {}, named),
          unit("b1", 1, "infantry", 7, 1, { martial: 80 }, named),
          unit("b2", 1, "cavalry", 7, 2, {}, named),
        ],
        {
          seed: "duel-soak",
          duels: [
            {
              id: "meet",
              a: ["a1"],
              b: "b1",
              when: { kind: "round", round: 2 },
              play: false,
              outcome: "draw",
              fatal: false,
            },
          ],
        },
      ),
    );
    const run = runBattle(start, R);
    expect(run.commands.some((c) => c.kind === "act" && c.action.kind === "duel")).toBe(false);
    expect(hashState(replay(start, R, run.commands))).toBe(hashState(run.state));
  });
});

function toSetup(u: BattleState["units"][number]) {
  return {
    id: u.id,
    side: u.side,
    name: u.name,
    classId: u.classId,
    level: u.level,
    stats: u.stats,
    x: u.x,
    y: u.y,
    named: u.named === true,
  };
}
