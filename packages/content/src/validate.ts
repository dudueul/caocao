/**
 * Content checks run by the tests (and later by the editors): cross-references,
 * table completeness, integer-only numbers, and scenario placement. Each check
 * returns a list of problems; an empty list means the content is valid.
 */
import { type BattleMap, type Cell, canonical, type Ruleset, type TerrainId } from "@tk/rules";
import { GROWTH, PROMOTIONS } from "./classes.ts";
import { GEAR, ITEMS } from "./items.ts";
import { parseMap } from "./maps.ts";
import { OFFICERS } from "./officers.ts";
import type { ScenarioDef, ScenarioUnit } from "./scenarios.ts";
import type { ChapterDef, JoinDef, Line, StoryBattleDef } from "./story.ts";
import { TERRAIN_IDS } from "./terrain.ts";

const isPositive = (n: number) => Number.isSafeInteger(n) && n > 0;
const isPermille = (n: number) => Number.isSafeInteger(n) && n >= 0 && n <= 1000;

export function validateRuleset(rules: Ruleset): string[] {
  const problems: string[] = [];
  const say = (p: string) => problems.push(p);
  try {
    canonical(rules);
  } catch (e) {
    say(`ruleset holds a non-integer or non-plain value: ${(e as Error).message}`);
  }

  for (const id of TERRAIN_IDS) {
    const t = rules.terrains[id];
    if (!t) say(`terrain ${id} is missing`);
    else {
      if (t.id !== id) say(`terrain ${id} has id ${t.id}`);
      if (!isPermille(t.cover)) say(`terrain ${id}: cover ${t.cover} is not 0–1000`);
      if (!isPermille(t.heal)) say(`terrain ${id}: heal ${t.heal} is not 0–1000`);
    }
  }

  const families = new Set<string>();
  for (const [key, c] of Object.entries(rules.classes)) {
    families.add(c.family);
    if (c.id !== key) say(`class ${key} has id ${c.id}`);
    for (const f of ["hp", "move", "atk", "def", "doubleAttackGap"] as const)
      if (!isPositive(c[f])) say(`class ${key}: ${f} ${c[f]} must be a positive integer`);
    if (!Number.isSafeInteger(c.mp) || c.mp < 0) say(`class ${key}: mp ${c.mp} must be ≥ 0`);
    if (c.range.length === 0) say(`class ${key}: empty attack range`);
    if (c.range.some((o) => o.dx === 0 && o.dy === 0)) say(`class ${key}: range includes itself`);
    if (!rules.affinity[c.family]) say(`class ${key}: no affinity row for family ${c.family}`);
    for (const s of c.stratagems)
      if (!rules.stratagems[s]) say(`class ${key}: unknown stratagem ${s}`);
    // Max MP = class MP + 지력 × mpPerIntellect‰, so 0 class MP is fine while 지력 gives MP.
    if (c.stratagems.length > 0 && c.mp === 0 && rules.tuning.mpPerIntellect === 0)
      say(`class ${key}: casts stratagems but can never have MP`);
  }

  for (const [family, row] of Object.entries(rules.affinity))
    for (const id of TERRAIN_IDS) {
      const a = row[id as TerrainId];
      if (!a) say(`affinity ${family}: terrain ${id} is missing`);
      else {
        if (a.move !== null && !isPositive(a.move))
          say(`affinity ${family}/${id}: move ${a.move} must be a positive integer or null`);
        if (!isPositive(a.effect)) say(`affinity ${family}/${id}: effect ${a.effect} must be > 0`);
      }
    }

  for (const [key, s] of Object.entries(rules.stratagems)) {
    if (s.id !== key) say(`stratagem ${key} has id ${s.id}`);
    if (!isPositive(s.mp)) say(`stratagem ${key}: mp ${s.mp} must be > 0`);
    if (s.range.length === 0) say(`stratagem ${key}: empty range`);
    if (s.area.length === 0) say(`stratagem ${key}: empty area`);
    if (s.effect === "condition" && s.condition === undefined)
      say(`stratagem ${key}: condition stratagems need a condition`);
    if (s.condition !== undefined) {
      if (!rules.conditions[s.condition]) say(`stratagem ${key}: unknown condition ${s.condition}`);
      const [min, max] = s.turns ?? [0, 0];
      if (!isPositive(min) || !Number.isSafeInteger(max) || max < min)
        say(`stratagem ${key}: turns must be a range [min, max] with 1 ≤ min ≤ max`);
    }
    if (s.effect === "heal" && s.targets !== "allies") say(`stratagem ${key}: heals target allies`);
    for (const [t, m] of Object.entries(s.terrain ?? {}))
      if (!isPositive(m)) say(`stratagem ${key}: terrain ${t} multiplier ${m} must be > 0`);
    for (const [w, m] of Object.entries(s.weather ?? {}))
      if (!isPositive(m)) say(`stratagem ${key}: weather ${w} multiplier ${m} must be > 0`);
  }

  for (const [key, c] of Object.entries(rules.conditions)) {
    if (c.id !== key) say(`condition ${key} has id ${c.id}`);
    if (c.dot !== undefined && !isPermille(c.dot))
      say(`condition ${key}: dot ${c.dot} is not 0–1000`);
    const effects = [c.atk, c.def, c.hit, c.move, c.dot, c.blocks].filter((v) => v !== undefined);
    if (effects.length === 0) say(`condition ${key} does nothing`);
  }

  for (const [a, row] of Object.entries(rules.relations)) {
    if (!families.has(a)) say(`relations: no class of family ${a}`);
    for (const [d, m] of Object.entries(row)) {
      if (!families.has(d)) say(`relations ${a}: no class of family ${d}`);
      if (!isPositive(m)) say(`relations ${a}→${d}: ${m} must be > 0`);
    }
  }
  return problems;
}

export function validateScenario(rules: Ruleset, s: ScenarioDef): string[] {
  const problems: string[] = [];
  const say = (p: string) => problems.push(`scenario ${s.id}: ${p}`);
  let map: ReturnType<typeof parseMap>;
  try {
    map = parseMap(s.map);
  } catch (e) {
    say((e as Error).message);
    return problems;
  }
  if (s.sides.length < 2) say("needs at least two sides");
  if (!isPositive(s.roundLimit)) say(`round limit ${s.roundLimit} must be > 0`);
  const used = new Set<string>();
  const officers = new Set<string>();
  const sidesWithUnits = new Set<number>();
  for (const u of s.units) {
    const id = u.id ?? u.officer;
    const where = `${id} at ${u.x},${u.y}`;
    if (!OFFICERS[u.officer]) say(`unknown officer ${u.officer}`);
    else if (OFFICERS[u.officer]?.generic && !u.id) say(`troop ${u.officer} needs an id`);
    if (officers.has(id)) say(`unit ${id} appears twice`);
    officers.add(id);
    const cls = rules.classes[u.classId];
    if (!cls) {
      say(`${where}: unknown class ${u.classId}`);
      continue;
    }
    if (!isPositive(u.level)) say(`${where}: level ${u.level} must be > 0`);
    if (u.side < 0 || u.side >= s.sides.length) say(`${where}: side ${u.side} has no name`);
    sidesWithUnits.add(u.side);
    if (u.x < 0 || u.y < 0 || u.x >= map.width || u.y >= map.height) {
      say(`${where}: off the map`);
      continue;
    }
    const key = `${u.x},${u.y}`;
    if (!u.arrives && used.has(key)) say(`${where}: tile already taken`);
    if (u.arrives !== undefined && !(u.arrives >= 2))
      say(`${where}: arrives must be round 2 or later`);
    if (!u.arrives) used.add(key);
    const terrain = map.tiles[u.y * map.width + u.x] as TerrainId;
    if (rules.affinity[cls.family]?.[terrain]?.move == null)
      say(`${where}: ${cls.family} can't stand on ${terrain}`);
  }
  if (sidesWithUnits.size < 2) say("needs units on at least two sides");
  return problems;
}

/** Can every family in the ruleset stand on this cell? (Roster slots take anyone.) */
function standableByAll(rules: Ruleset, map: BattleMap, c: Cell): boolean {
  const terrain = map.tiles[c.y * map.width + c.x] as TerrainId;
  return Object.values(rules.affinity).every((row) => row[terrain]?.move != null);
}

/** Class growth, promotions and items: every reference resolves (ADR 0048, ADR 0050). */
export function validateProgression(rules: Ruleset): string[] {
  const problems: string[] = [];
  for (const id of Object.keys(rules.classes))
    if (!GROWTH[id]) problems.push(`class ${id} has no growth row`);
  for (const [from, to] of Object.entries(PROMOTIONS)) {
    const a = rules.classes[from];
    const b = rules.classes[to];
    if (!a || !b) problems.push(`promotion ${from} → ${to}: unknown class`);
    else if (a.family !== b.family || b.tier !== a.tier + 1)
      problems.push(`promotion ${from} → ${to} must keep the family and go up one tier`);
  }
  for (const item of Object.values(ITEMS)) {
    if (!isPositive(item.price)) problems.push(`item ${item.id}: price must be > 0`);
    if (item.slot !== "accessory" && item.kind === undefined)
      problems.push(`item ${item.id}: weapons and armour need a kind`);
  }
  return problems;
}

/** Can an officer of this class use this item? */
export function fits(rules: Ruleset, classId: string, itemId: string): boolean {
  const item = ITEMS[itemId];
  const cls = rules.classes[classId];
  if (!item || !cls) return false;
  if (item.slot === "accessory") return true;
  const gear = GEAR[cls.family];
  return item.slot === "weapon"
    ? gear.weapon.some((k) => k === item.kind)
    : gear.armour.some((k) => k === item.kind);
}

/**
 * A story chapter, walked step by step with the roster it would have at each
 * point: maps, slots, units, goals, talk, rewards, shops and roster changes.
 */
export function validateChapter(rules: Ruleset, ch: ChapterDef): string[] {
  const problems: string[] = [];
  const roster = new Set<string>();
  const checkJoin = (j: JoinDef, where: string) => {
    if (!OFFICERS[j.officer] || OFFICERS[j.officer]?.generic)
      problems.push(`${where}: unknown officer ${j.officer}`);
    if (!rules.classes[j.classId]) problems.push(`${where}: unknown class ${j.classId}`);
    if (!isPositive(j.level)) problems.push(`${where}: level must be > 0`);
    for (const g of j.gear ?? [])
      if (!fits(rules, j.classId, g)) problems.push(`${where}: ${j.officer} can't use ${g}`);
    if (roster.has(j.officer)) problems.push(`${where}: ${j.officer} is already in the roster`);
    roster.add(j.officer);
  };
  const checkLines = (lines: readonly Line[], where: string) => {
    if (lines.length === 0) problems.push(`${where}: no lines`);
    for (const l of lines) {
      if (l.who !== undefined && !OFFICERS[l.who])
        problems.push(`${where}: unknown speaker ${l.who}`);
      if (l.text.trim() === "") problems.push(`${where}: empty line`);
    }
  };
  for (const j of ch.roster) checkJoin(j, `chapter ${ch.id} roster`);
  if (!isPositive(ch.number)) problems.push(`chapter ${ch.id}: number must be > 0`);
  if (!isPositive(ch.floor)) problems.push(`chapter ${ch.id}: floor must be > 0`);
  for (const j of ch.roster)
    if (j.level < ch.floor) problems.push(`chapter ${ch.id}: ${j.officer} starts below the floor`);
  const ids = new Set<string>();
  for (const step of ch.steps) {
    if (step.kind === "scene") {
      const sc = step.scene;
      const where = `scene ${sc.id}`;
      if (ids.has(sc.id)) problems.push(`${where}: id used twice`);
      ids.add(sc.id);
      checkLines(sc.lines, where);
      for (const id of sc.leaves ?? []) {
        if (!roster.has(id)) problems.push(`${where}: ${id} leaves but is not in the roster`);
        roster.delete(id);
      }
      for (const j of sc.joins ?? []) checkJoin(j, where);
    } else if (step.kind === "camp") {
      for (const id of step.camp.shop)
        if (!ITEMS[id]) problems.push(`camp ${step.camp.id}: unknown item ${id}`);
    } else {
      problems.push(...validateStoryBattle(rules, step.battle, roster));
      if (ids.has(step.battle.id)) problems.push(`battle ${step.battle.id}: id used twice`);
      ids.add(step.battle.id);
    }
  }
  return problems;
}

function validateStoryBattle(
  rules: Ruleset,
  b: StoryBattleDef,
  roster: ReadonlySet<string>,
): string[] {
  const problems: string[] = [];
  const say = (p: string) => problems.push(`battle ${b.id}: ${p}`);
  const units: ScenarioUnit[] = [...b.guests, ...b.enemies];
  // Roster officers fill the slots later; stand one in for side 0 if no guest does.
  const first = b.slots[0];
  const standIn: ScenarioUnit[] =
    b.guests.length > 0 || !first
      ? []
      : [{ officer: "cao-cao", classId: "infantry", level: 1, side: 0, x: first.x, y: first.y }];
  problems.push(
    ...validateScenario(rules, {
      id: b.id,
      name: b.name,
      year: 200,
      summary: b.summary,
      sides: b.sides,
      map: b.map,
      weather: b.weather,
      policy: "sidePhases",
      roundLimit: b.roundLimit,
      units: [...units, ...standIn],
    }),
  );
  let map: BattleMap;
  try {
    map = parseMap(b.map);
  } catch {
    return problems;
  }
  if (b.guests.some((g) => g.side !== 0)) say("guests must be on side 0");
  if (b.enemies.some((e) => e.side === 0)) say("enemies can't be on side 0");
  if (b.slots.length < b.max) say(`only ${b.slots.length} slots for ${b.max} officers`);
  const taken = new Set(units.filter((u) => !u.arrives).map((u) => `${u.x},${u.y}`));
  for (const c of b.slots) {
    if (c.x < 0 || c.y < 0 || c.x >= map.width || c.y >= map.height)
      say(`slot ${c.x},${c.y} is off the map`);
    else if (!standableByAll(rules, map, c)) say(`slot ${c.x},${c.y} is not open to every class`);
    if (taken.has(`${c.x},${c.y}`)) say(`slot ${c.x},${c.y} is taken by a unit`);
  }
  for (const id of b.required) if (!roster.has(id)) say(`required ${id} is not in the roster`);
  const known = new Set([...roster, ...units.map((u) => u.id ?? u.officer)]);
  for (const g of b.goals) {
    if (g.when.kind === "defeat")
      for (const id of g.when.units) if (!known.has(id)) say(`goal names unknown unit ${id}`);
    if (g.when.kind === "hold" && g.when.round > b.roundLimit + 1)
      say("hold goal is past the round limit");
  }
  if (!b.goals.some((g) => g.side === 0)) say("needs a goal for the player");
  for (const t of b.talk) {
    const w = t.when;
    if ((w.at === "defeated" || w.at === "arrived") && !known.has(w.unit))
      say(`talk names unknown unit ${w.unit}`);
    if (w.at === "arrived" && !units.some((u) => (u.id ?? u.officer) === w.unit && u.arrives))
      say(`talk waits for ${w.unit}, who never arrives`);
    for (const l of t.lines)
      if (l.who !== undefined && !OFFICERS[l.who]) say(`unknown speaker ${l.who}`);
  }
  for (const id of b.rewards.items) if (!ITEMS[id]) say(`unknown reward ${id}`);
  if (!isPositive(b.rewards.gold)) say("reward gold must be > 0");
  return problems;
}
