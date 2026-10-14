/**
 * Content checks run by the tests (and later by the editors): cross-references,
 * table completeness, integer-only numbers, and scenario placement. Each check
 * returns a list of problems; an empty list means the content is valid.
 */
import { canonical, type Ruleset, type TerrainId } from "@tk/rules";
import { parseMap } from "./maps.ts";
import { OFFICERS } from "./officers.ts";
import type { ScenarioDef } from "./scenarios.ts";
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
    if (c.stratagems.length > 0 && c.mp === 0) say(`class ${key}: casts stratagems but has 0 MP`);
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
    if (s.effect === "status" && (s.status === undefined || !isPositive(s.statusRounds ?? 0)))
      say(`stratagem ${key}: status stratagems need a status and statusRounds`);
    if (s.effect === "heal" && s.targets !== "allies") say(`stratagem ${key}: heals target allies`);
    for (const [t, m] of Object.entries(s.terrain ?? {}))
      if (!isPositive(m)) say(`stratagem ${key}: terrain ${t} multiplier ${m} must be > 0`);
    for (const [w, m] of Object.entries(s.weather ?? {}))
      if (!isPositive(m)) say(`stratagem ${key}: weather ${w} multiplier ${m} must be > 0`);
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
    const where = `${u.officer} at ${u.x},${u.y}`;
    if (!OFFICERS[u.officer]) say(`unknown officer ${u.officer}`);
    if (officers.has(u.officer)) say(`officer ${u.officer} appears twice`);
    officers.add(u.officer);
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
    if (used.has(key)) say(`${where}: tile already taken`);
    used.add(key);
    const terrain = map.tiles[u.y * map.width + u.x] as TerrainId;
    if (rules.affinity[cls.family]?.[terrain]?.move == null)
      say(`${where}: ${cls.family} can't stand on ${terrain}`);
  }
  if (sidesWithUnits.size < 2) say("needs units on at least two sides");
  return problems;
}
