import { describe, expect, it } from "bun:test";
import {
  CHAPTER_LIST,
  CHAPTERS,
  FIRST_CHAPTER,
  GUANDU,
  ITEMS,
  P0_RULES,
  validateChapter,
  validateProgression,
} from "@tk/content";
import { apply, chooseCommand, createBattle, hashState, replay } from "@tk/rules";
import {
  buy,
  type CampaignState,
  checkSortie,
  continueTo,
  currentBattle,
  currentStep,
  defaultSortie,
  emptyLedger,
  equip,
  finishScene,
  gainExp,
  growthAt,
  isFinished,
  leaveCamp,
  newCampaign,
  nextChapter,
  type Officer,
  PROGRESSION,
  promote,
  promotionFor,
  readSave,
  recordEvents,
  sell,
  settle,
  storyOrders,
  storySetup,
  unequip,
  writeSave,
} from "../src/index.ts";

const must = <T>(r: { ok: true; state: T } | { ok: false; reason: string }): T => {
  if (!r.ok) throw new Error(r.reason);
  return r.state;
};

describe("content for the Story (ADR 0048–0050)", () => {
  it("every chapter and the progression tables are valid", () => {
    expect(validateProgression(P0_RULES)).toEqual([]);
    for (const ch of CHAPTER_LIST) expect(validateChapter(P0_RULES, ch)).toEqual([]);
  });

  it("chapters are numbered in order and point at chapters that exist (ADR 0056)", () => {
    expect(CHAPTER_LIST.map((c) => c.number)).toEqual(
      [...CHAPTER_LIST.map((c) => c.number)].sort((a, b) => a - b),
    );
    expect(FIRST_CHAPTER).toBe(CHAPTER_LIST[0]?.id ?? "");
    for (const c of CHAPTER_LIST) if (c.next) expect(CHAPTERS[c.next]).toBeDefined();
    const battles = CHAPTER_LIST.flatMap((c) =>
      c.steps.flatMap((s) => (s.kind === "battle" ? [s.battle.id] : [])),
    );
    expect(new Set(battles).size).toBe(battles.length);
  });

  it("validation catches a broken chapter", () => {
    const step = GUANDU.steps.find((s) => s.kind === "battle");
    if (step?.kind !== "battle") throw new Error("fixture");
    const broken = {
      ...GUANDU,
      steps: [
        {
          kind: "battle" as const,
          battle: {
            ...step.battle,
            required: ["nobody"],
            rewards: { gold: 0, items: ["nothing"] },
          },
        },
      ],
    };
    const problems = validateChapter(P0_RULES, broken);
    expect(problems.some((p) => p.includes("required nobody"))).toBe(true);
    expect(problems.some((p) => p.includes("unknown reward nothing"))).toBe(true);
    expect(problems.some((p) => p.includes("gold"))).toBe(true);
  });
});

describe("progression (ADR 0048)", () => {
  const officer = (): Officer => ({
    id: "cao-cao",
    classId: "infantry",
    level: 9,
    exp: 90,
    stats: { martial: 70, command: 99, intellect: 90, agility: 70, fortune: 85 },
    gear: { weapon: null, armour: null, accessory: null },
    guest: false,
    battles: 0,
    defeated: 0,
  });

  it("growth is steady: rates in ‰ add up exactly over levels", () => {
    let command = 0;
    for (let l = 1; l <= 10; l++) command += growthAt("infantry", l).command ?? 0;
    expect(command).toBe(8); // 800‰ × 10 levels
  });

  it("level-ups carry over spare experience and cap stats at 100", () => {
    const o = officer();
    const ups = gainExp(o, 215);
    expect(ups.map((u) => u.level)).toEqual([10, 11, 12]);
    expect(o.exp).toBe(5);
    expect(o.stats.command).toBe(100);
  });

  it("promotion opens at level 20 and keeps the family", () => {
    const o = officer();
    expect(promotionFor(o)).toBeNull();
    o.level = 20;
    expect(promotionFor(o)).toBe("heavyInfantry");
  });

  it("experience comes from landed strikes and defeats, with a commander bonus", () => {
    const state = must(finishScene(must(finishScene(newCampaign("guandu")))));
    const b = currentBattle(state);
    if (!b) throw new Error("expected the first battle");
    const start = createBattle(P0_RULES, storySetup(state, b, defaultSortie(state, b), "exp"));
    const ledger = emptyLedger();
    recordEvents(ledger, start, start, [
      {
        kind: "strike",
        attacker: "guan-yu",
        defender: "yan-liang",
        role: "first",
        hit: true,
        crit: false,
        damage: 300,
        hpAfter: 0,
        broke: false,
      },
      { kind: "defeated", unit: "yan-liang", fate: "killed" },
      {
        kind: "strike",
        attacker: "xu-chu",
        defender: "yuan-infantry-1",
        role: "first",
        hit: false,
        crit: false,
        damage: 0,
        hpAfter: 200,
        broke: false,
      },
    ]);
    // 안량 is two levels below 관우: the strike and the defeat each lose a little.
    const P = PROGRESSION;
    expect(ledger.exp["guan-yu"]).toBe(
      P.strikeBase -
        2 * P.strikePerLevel +
        (P.defeatBase - 2 * P.defeatPerLevel) +
        P.commanderBonus,
    );
    expect(ledger.exp["xu-chu"]).toBeUndefined();
    expect(ledger.defeated["guan-yu"]).toBe(1);
  });
});

describe("gear and the shop (ADR 0050)", () => {
  it("buys, equips, swaps, unequips and sells", () => {
    let s: CampaignState = { ...newCampaign("guandu"), gold: 1000 };
    s = must(buy(s, "ring-dao", ["ring-dao"]));
    expect(s.gold).toBe(1000 - (ITEMS["ring-dao"]?.price ?? 0));
    expect(buy(s, "sunzi", ["ring-dao"]).ok).toBe(false);
    s = must(equip(s, "cao-cao", "ring-dao"));
    expect(s.roster[0]?.gear.weapon).toBe("ring-dao");
    expect(s.inventory["refined-dao"]).toBe(1); // the old weapon went back
    expect(equip(s, "xun-you", "refined-dao").ok).toBe(false); // strategists carry fans
    s = must(unequip(s, "cao-cao", "armour"));
    expect(s.inventory.lamellar).toBe(1);
    const gold = s.gold;
    s = must(sell(s, "lamellar"));
    expect(s.gold).toBe(gold + 150);
  });
});

describe("saves (ADR 0051)", () => {
  it("round-trips and rejects damaged files", () => {
    const s = newCampaign("guandu");
    const back = readSave(writeSave(s));
    expect(back.ok && back.state).toEqual(s);
    expect(readSave("{").ok).toBe(false);
    const bad = JSON.parse(writeSave(s));
    bad.roster[0].stats.martial = 900;
    expect(readSave(JSON.stringify(bad)).ok).toBe(false);
    bad.roster[0].stats.martial = 70;
    bad.roster[0].gear.weapon = "feather-fan"; // a fan on a cavalry officer
    expect(readSave(JSON.stringify(bad)).ok).toBe(false);
  });
});

/** Plays the whole chapter with the AI on both sides; promotes and equips like a player would. */
function playChapter(seedBase: string, from: CampaignState = newCampaign("guandu")) {
  let s = from;
  const log: string[] = [];
  let attempts = 0;
  while (!isFinished(s)) {
    const step = currentStep(s);
    if (!step) break;
    if (step.kind === "scene") {
      s = must(finishScene(s));
      continue;
    }
    if (step.kind === "camp") {
      for (const o of s.roster) if (promotionFor(o)) s = must(promote(s, o.id));
      for (const id of Object.keys(s.inventory))
        for (const o of s.roster) {
          const r = equip(s, o.id, id);
          const item = ITEMS[id];
          const current = o.gear[item?.slot ?? "weapon"];
          const better = item && (!current || (ITEMS[current]?.price ?? 0) < item.price) && r.ok;
          if (better && r.ok) {
            s = r.state;
            break;
          }
        }
      s = must(leaveCamp(s));
      continue;
    }
    const b = step.battle;
    const sortie = defaultSortie(s, b);
    expect(checkSortie(s, b, sortie)).toBeNull();
    const start = createBattle(
      P0_RULES,
      storySetup(s, b, sortie, `${seedBase}-${b.id}-${attempts}`),
    );
    const orders = storyOrders(b);
    let state = start;
    const ledger = emptyLedger();
    const commands = [];
    for (let i = 0; i < 4000 && state.winner === null; i++) {
      const cmd = chooseCommand(state, P0_RULES, orders);
      if (!cmd) break;
      const r = apply(state, P0_RULES, cmd);
      if (!r.ok) throw new Error(r.reason);
      recordEvents(ledger, state, r.state, r.events);
      commands.push(cmd);
      state = r.state;
    }
    expect(hashState(replay(start, P0_RULES, commands))).toBe(hashState(state));
    const out = settle(s, b, sortie, state, ledger);
    log.push(`${b.id}: winner ${state.winner} round ${state.round}`);
    if (state.winner === 0) {
      s = out.state;
      attempts = 0;
    } else if (++attempts > 6) {
      throw new Error(`could not win ${b.id}: ${log.join(" | ")}`);
    }
  }
  return { s, log };
}

describe("carrying a campaign into the next chapter (ADR 0056)", () => {
  it("keeps the roster and gear, raises levels to the floor and adds the chapter's officers", () => {
    const done: CampaignState = {
      ...newCampaign(FIRST_CHAPTER),
      step: CHAPTERS[FIRST_CHAPTER]?.steps.length ?? 0,
      gold: 1234,
      inventory: { jade: 1 },
      cleared: ["luoyang"],
    };
    const cao = done.roster[0] as Officer;
    expect(continueTo(newCampaign(FIRST_CHAPTER), "guandu").ok).toBe(false); // not finished
    const next = nextChapter(done);
    expect(next?.id).toBe("guandu");
    const r = continueTo(done, "guandu");
    if (!r.ok) throw new Error(r.reason);
    const s = r.state;
    expect([s.chapter, s.step, s.gold, s.inventory.jade, s.cleared]).toEqual([
      "guandu",
      0,
      1234,
      1,
      ["luoyang"],
    ]);
    const c = s.roster.find((o) => o.id === "cao-cao") as Officer;
    expect(c.level).toBe(GUANDU.floor);
    expect(c.stats.command).toBeGreaterThanOrEqual(cao.stats.command);
    expect(c.gear).toEqual(cao.gear);
    expect(s.roster.map((o) => o.id)).toContain("xu-chu");
    expect(s.roster.every((o) => o.level >= GUANDU.floor)).toBe(true);
  });
});

describe("the whole story from chapter 1, AI on both sides", () => {
  it("plays chapter 1, carries over, and plays Guandu", () => {
    const first = playChapter("chain", newCampaign(FIRST_CHAPTER));
    expect(isFinished(first.s)).toBe(true);
    const r = continueTo(first.s, nextChapter(first.s)?.id ?? "");
    if (!r.ok) throw new Error(r.reason);
    const second = playChapter("chain2", r.state);
    expect(isFinished(second.s)).toBe(true);
    expect(second.s.cleared).toContain("shouzhang");
    expect(second.s.cleared).toContain("guandu-final");
  }, 120_000);
});

describe("the whole Guandu chapter, AI on both sides", () => {
  it("is winnable end to end and levels the roster toward promotion", () => {
    const { s, log } = playChapter("soak");
    if (process.env.TK_SOAK_LOG) {
      console.log(log.join("\n"));
      console.log(s.roster.map((o) => `${o.id} ${o.classId} Lv${o.level}`).join("\n"));
    }
    expect(isFinished(s)).toBe(true);
    expect(s.cleared.sort()).toEqual(["baima", "guandu-camp", "guandu-final", "wuchao", "yanjin"]);
    expect(s.roster.map((o) => o.id)).toContain("zhang-he");
    expect(s.roster.map((o) => o.id)).not.toContain("guan-yu");
    const cao = s.roster.find((o) => o.id === "cao-cao");
    expect(cao?.level).toBeGreaterThanOrEqual(20);
  }, 60_000);
});
