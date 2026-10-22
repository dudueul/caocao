/**
 * Content checks run by the tests (and later by the editors): cross-references,
 * table completeness, integer-only numbers, and scenario placement. Each check
 * returns a list of problems; an empty list means the content is valid.
 */
import { type BattleMap, type Cell, canonical, type Ruleset, type TerrainId } from "@tk/rules";
import { GROWTH, PROMOTIONS } from "./classes.ts";
import { holds } from "./flags.ts";
import { GEAR, ITEMS } from "./items.ts";
import { parseMap } from "./maps.ts";
import { OFFICERS } from "./officers.ts";
import type { ScenarioDef, ScenarioUnit } from "./scenarios.ts";
import { parseStage, type StageGrid, walkable } from "./stage.ts";
import type {
  Actor,
  CampDef,
  ChapterDef,
  Cmd,
  Cond,
  JoinDef,
  Line,
  SceneDef,
  StageDef,
  StoryBattleDef,
} from "./story.ts";
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
    // Works (gates, ADR 0069) never move or strike; everyone else must.
    const work = c.family === "structure";
    for (const f of ["hp", "move", "atk", "def", "doubleAttackGap"] as const) {
      if (work && (f === "move" || f === "atk")) {
        if (c[f] !== 0) say(`class ${key}: a work's ${f} must be 0`);
      } else if (!isPositive(c[f])) say(`class ${key}: ${f} ${c[f]} must be a positive integer`);
    }
    if (!Number.isSafeInteger(c.mp) || c.mp < 0) say(`class ${key}: mp ${c.mp} must be ≥ 0`);
    if (c.range.length === 0 && !work) say(`class ${key}: empty attack range`);
    if (c.range.length > 0 && work) say(`class ${key}: a work can't strike`);
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

/** Flag names and values: short lowercase words, as saves require. */
const FLAG_TEXT = /^[a-z0-9-]{1,40}$/;

type Flags = Record<string, string>;

/** Every value each flag can take, from the choices, fates and assumptions of these chapters. */
export function flagDomains(chapters: readonly ChapterDef[]): Map<string, Set<string>> {
  const out = new Map<string, Set<string>>();
  const add = (flag: string, value: string) => {
    const set = out.get(flag) ?? new Set<string>();
    out.set(flag, set);
    set.add(value);
  };
  const visit = (cmds: readonly Cmd[]) => {
    for (const c of cmds) {
      if (c.do === "all") visit(c.cmds);
      else if (c.do === "choice") for (const o of c.options) add(c.flag, o.value);
    }
  };
  for (const ch of chapters) {
    for (const [k, v] of Object.entries(ch.assumes ?? {})) add(k, v);
    for (const step of ch.steps) {
      if (step.kind === "scene") visit(step.scene.script);
      if (step.kind === "battle")
        for (const f of step.battle.fates ?? []) {
          add(f.flag, f.standing);
          add(f.flag, f.fallen);
        }
    }
  }
  return out;
}

/** Every condition anywhere in a chapter. */
function chapterConds(ch: ChapterDef): Cond[] {
  const out: Cond[] = [];
  const push = (c: Cond | undefined) => {
    if (c) out.push(c);
  };
  const visit = (cmds: readonly Cmd[]) => {
    for (const c of cmds) {
      push(c.if);
      if (c.do === "all") visit(c.cmds);
    }
  };
  for (const j of ch.roster) push(j.if);
  for (const step of ch.steps) {
    push(step.if);
    if (step.kind === "scene") {
      const sc = step.scene;
      visit(sc.script);
      for (const a of sc.cast) push(a.if);
      for (const j of sc.joins ?? []) push(j.if);
      for (const l of sc.leaves ?? []) if (typeof l !== "string") push(l.if);
    } else if (step.kind === "battle") {
      const b = step.battle;
      for (const u of [...b.guests, ...(b.allies ?? []), ...b.enemies]) push(u.if);
      for (const g of b.goals) push(g.if);
      for (const sh of b.shifts ?? []) push(sh.if);
      for (const d of b.duels ?? []) push(d.if);
      for (const t of b.talk) push(t.if);
    } else {
      for (const t of step.camp.talk ?? []) push(t.if);
    }
  }
  return out;
}

/**
 * The flag values a chapter can be played with: one assignment per path
 * through its choices and fates, and through the flags it tests from the
 * chapters before. A flag the chapter sets takes its path value when set.
 */
function chapterPaths(ch: ChapterDef, domains: Map<string, Set<string>>): Flags[] {
  const own = flagDomains([{ ...ch, assumes: {} }]);
  const tested = new Set(chapterConds(ch).map((c) => c.flag));
  const names = [...new Set([...own.keys(), ...tested])].sort();
  let paths: Flags[] = [{}];
  for (const name of names) {
    const values = [...(own.get(name) ?? domains.get(name) ?? new Set<string>())];
    const assumed = ch.assumes?.[name];
    if (!own.has(name) && assumed === undefined) values.push(""); // unset
    const next: Flags[] = [];
    for (const p of paths)
      for (const v of values.length > 0 ? values : [""]) next.push({ ...p, [name]: v });
    paths = next;
  }
  return paths;
}

const inStage = (g: StageGrid, c: Cell) => c.x >= 0 && c.y >= 0 && c.x < g.width && c.y < g.height;

function checkLine(l: Line, say: (p: string) => void) {
  if (l.who !== undefined && !OFFICERS[l.who]) say(`unknown speaker ${l.who}`);
  if (l.text.trim() === "") say("empty line");
}

/** A stage's rows and props; returns its grid when it parses. */
function checkStage(stage: StageDef, say: (p: string) => void): StageGrid | null {
  let grid: StageGrid;
  try {
    grid = parseStage(stage);
  } catch (e) {
    say((e as Error).message);
    return null;
  }
  for (const p of stage.props ?? [])
    if (!inStage(grid, p.at)) say(`prop ${p.kind} is off the stage`);
  return grid;
}

/** The stage, cast and script of a scene, whatever the flags. */
function checkScene(rules: Ruleset, sc: SceneDef, say: (p: string) => void) {
  const grid = checkStage(sc.stage, say);
  if (!grid) return;
  const cast = new Map<string, Actor>();
  const starts = new Set<string>();
  for (const a of sc.cast) {
    if (cast.has(a.id)) say(`actor ${a.id} cast twice`);
    cast.set(a.id, a);
    if (!OFFICERS[a.officer ?? a.id]) say(`actor ${a.id}: unknown officer ${a.officer ?? a.id}`);
    if (a.classId !== undefined && !rules.classes[a.classId]) say(`actor ${a.id}: unknown class`);
    if (a.at) {
      if (!walkable(sc.stage, grid, a.at)) say(`actor ${a.id} starts on a blocked tile`);
      const key = `${a.at.x},${a.at.y}`;
      if (!a.if && starts.has(key)) say(`two actors start on ${key}`);
      if (!a.if) starts.add(key);
    }
  }
  const who = (id: string) => {
    if (!cast.has(id)) say(`script names ${id}, who is not in the cast`);
  };
  const open = (c: Cell, what: string) => {
    if (!inStage(grid, c)) say(`${what} ${c.x},${c.y} is off the stage`);
    else if (!walkable(sc.stage, grid, c)) say(`${what} ${c.x},${c.y} is blocked`);
  };
  let lines = 0;
  const visit = (cmds: readonly Cmd[]) => {
    for (const c of cmds) {
      if (c.if && !FLAG_TEXT.test(c.if.flag)) say(`bad flag name ${c.if.flag}`);
      switch (c.do) {
        case "say":
          lines += 1;
          checkLine(c.line, say);
          if (c.line.actor !== undefined) who(c.line.actor);
          break;
        case "walk":
          who(c.who);
          open(c.to, "walk to");
          break;
        case "enter":
          who(c.who);
          if (!inStage(grid, c.from)) say(`enter from ${c.from.x},${c.from.y} is off the stage`);
          open(c.to, "enter to");
          break;
        case "leave":
          who(c.who);
          if (!inStage(grid, c.to)) say(`leave to ${c.to.x},${c.to.y} is off the stage`);
          break;
        case "place":
          who(c.who);
          open(c.at, "place at");
          break;
        case "remove":
        case "emote":
        case "act":
          who(c.who);
          break;
        case "face":
          who(c.who);
          if (!["up", "down", "left", "right"].includes(c.to)) who(c.to);
          break;
        case "strike":
          who(c.who);
          who(c.target);
          break;
        case "camera":
          if (typeof c.at === "string") who(c.at);
          else if (!inStage(grid, c.at)) say("camera off the stage");
          if (c.zoom !== undefined && !(isPositive(c.zoom) && c.zoom <= 3000))
            say("camera zoom must be 1–3000‰");
          break;
        case "wait":
          if (!Number.isSafeInteger(c.ms) || c.ms < 0 || c.ms > 10_000)
            say("wait must be 0–10000 ms");
          break;
        case "sound":
          if (c.sfx === "") say("empty sound");
          break;
        case "fx":
          if (typeof c.at === "string") who(c.at);
          else if (c.at && !inStage(grid, c.at)) say("fx off the stage");
          break;
        case "fire":
          for (const f of c.cells) if (!inStage(grid, f)) say("fire off the stage");
          break;
        case "tiles":
          for (const t of c.tiles) {
            if (!inStage(grid, t)) say("tile change off the stage");
            if (!(TERRAIN_IDS as readonly string[]).includes(t.terrain))
              say(`unknown terrain ${t.terrain}`);
          }
          break;
        case "light":
          break;
        case "duel": {
          // A watched 일기토 (ADR 0071): two officers of the cast, a few bouts, history's ending.
          who(c.a);
          who(c.b);
          if (c.a === c.b) say(`duel: ${c.a} can't fight himself`);
          for (const id of [c.a, c.b]) {
            const a = cast.get(id);
            if (a && OFFICERS[a.officer ?? a.id]?.generic)
              say(`duel: ${id} is a troop, not an officer`);
          }
          if (!Number.isSafeInteger(c.bouts) || c.bouts < 1 || c.bouts > rules.tuning.duelBouts)
            say(`duel: bouts must be 1–${rules.tuning.duelBouts}`);
          if (!["a", "b", "draw"].includes(c.outcome)) say(`duel: unknown outcome ${c.outcome}`);
          break;
        }
        case "choice": {
          if (!FLAG_TEXT.test(c.flag)) say(`bad flag name ${c.flag}`);
          if (c.prompt.trim() === "") say("choice without a prompt");
          if (c.options.length < 2) say(`choice ${c.flag} needs two options`);
          const values = new Set<string>();
          for (const o of c.options) {
            if (!FLAG_TEXT.test(o.value)) say(`bad choice value ${o.value}`);
            if (values.has(o.value)) say(`choice ${c.flag}: ${o.value} twice`);
            values.add(o.value);
            if (o.text.trim() === "") say(`choice ${c.flag}: empty option`);
            if (o.gold !== undefined && !isPositive(o.gold))
              say(`choice ${c.flag}: gold must be > 0`);
            for (const id of o.items ?? [])
              if (!ITEMS[id]) say(`choice ${c.flag}: unknown item ${id}`);
          }
          break;
        }
        case "all":
          visit(c.cmds);
          break;
      }
    }
  };
  visit(sc.script);
  if (lines === 0) say("no lines");
}

/** The camp's ground, shop and talk. */
function checkCamp(camp: CampDef, say: (p: string) => void) {
  for (const id of camp.shop) if (!ITEMS[id]) say(`unknown item ${id}`);
  const g = camp.ground;
  const grid = checkStage(g.stage, say);
  if (grid) {
    const open = (c: Cell, what: string) => {
      if (!walkable(g.stage, grid, c)) say(`${what} ${c.x},${c.y} is blocked or off the ground`);
    };
    open(g.start, "start");
    for (const st of g.stations) open(st.at, st.kind);
    for (const c of g.spots) open(c, "spot");
  }
  const kinds = g.stations.map((st) => st.kind);
  if (new Set(kinds).size !== kinds.length) say("a station kind is used twice");
  if (!kinds.includes("council")) say("no council tent to leave the camp by");
  for (const t of camp.talk ?? []) {
    if (!OFFICERS[t.officer]) say(`talk for unknown officer ${t.officer}`);
    if (t.lines.length === 0) say(`talk for ${t.officer} has no lines`);
    for (const l of t.lines) checkLine(l, say);
  }
}

/**
 * A story chapter, walked step by step along every path its flags allow, with
 * the roster it would have at each point: maps, stages, slots, units, goals,
 * talk, rewards, shops and roster changes. `domains` gives the values of flags
 * set by other chapters (see `flagDomains`).
 */
export function validateChapter(
  rules: Ruleset,
  ch: ChapterDef,
  domains: Map<string, Set<string>> = flagDomains([ch]),
): string[] {
  const problems = new Set<string>();
  const add = (p: string) => problems.add(p);
  if (!isPositive(ch.number)) add(`chapter ${ch.id}: number must be > 0`);
  if (!isPositive(ch.floor)) add(`chapter ${ch.id}: floor must be > 0`);
  for (const j of ch.roster)
    if (j.level < ch.floor) add(`chapter ${ch.id}: ${j.officer} starts below the floor`);
  for (const [k, v] of Object.entries(ch.assumes ?? {}))
    if (!FLAG_TEXT.test(k) || !FLAG_TEXT.test(v)) add(`chapter ${ch.id}: bad assumed flag ${k}`);
  // Path-independent checks, once per step.
  const ids = new Set<string>();
  for (const step of ch.steps) {
    if (step.kind === "scene") {
      const where = `scene ${step.scene.id}`;
      if (ids.has(step.scene.id)) add(`${where}: id used twice`);
      ids.add(step.scene.id);
      checkScene(rules, step.scene, (p) => add(`${where}: ${p}`));
    } else if (step.kind === "camp") {
      checkCamp(step.camp, (p) => add(`camp ${step.camp.id}: ${p}`));
    } else {
      if (ids.has(step.battle.id)) add(`battle ${step.battle.id}: id used twice`);
      ids.add(step.battle.id);
    }
  }
  // Roster-dependent checks along every path.
  for (const path of chapterPaths(ch, domains)) {
    const flags: Flags = {};
    for (const [k, v] of Object.entries(ch.assumes ?? {})) flags[k] = v;
    const own = flagDomains([{ ...ch, assumes: {} }]);
    for (const [k, v] of Object.entries(path)) if (!own.has(k) && v !== "") flags[k] = v;
    const roster = new Set<string>();
    const checkJoin = (j: JoinDef, where: string) => {
      if (!holds(j.if, flags)) return;
      if (!OFFICERS[j.officer] || OFFICERS[j.officer]?.generic)
        add(`${where}: unknown officer ${j.officer}`);
      if (!rules.classes[j.classId]) add(`${where}: unknown class ${j.classId}`);
      if (!isPositive(j.level)) add(`${where}: level must be > 0`);
      for (const g of j.gear ?? [])
        if (!fits(rules, j.classId, g)) add(`${where}: ${j.officer} can't use ${g}`);
      if (roster.has(j.officer)) add(`${where}: ${j.officer} is already in the roster`);
      roster.add(j.officer);
    };
    for (const j of ch.roster) checkJoin(j, `chapter ${ch.id} roster`);
    for (const step of ch.steps) {
      if (!holds(step.if, flags)) continue;
      if (step.kind === "scene") {
        const sc = step.scene;
        const where = `scene ${sc.id}`;
        // Who stands on the stage as the script runs on this path: a duel needs both there.
        const cast = (id: string) => sc.cast.some((a) => a.id === id && holds(a.if, flags));
        const onStage = new Set(sc.cast.filter((a) => a.at && holds(a.if, flags)).map((a) => a.id));
        const visit = (cmds: readonly Cmd[]) => {
          for (const c of cmds) {
            if (!holds(c.if, flags)) continue;
            if (c.do === "all") visit(c.cmds);
            else if (c.do === "choice") {
              const v = path[c.flag];
              flags[c.flag] =
                v && c.options.some((o) => o.value === v) ? v : (c.options[0]?.value ?? "");
            } else if ((c.do === "enter" || c.do === "place") && cast(c.who)) onStage.add(c.who);
            else if (c.do === "leave" || c.do === "remove") onStage.delete(c.who);
            else if (c.do === "duel")
              for (const id of [c.a, c.b])
                if (!onStage.has(id)) add(`${where}: duel ${c.a}–${c.b}: ${id} is not on stage`);
          }
        };
        visit(sc.script);
        for (const l of sc.leaves ?? []) {
          if (typeof l !== "string" && !holds(l.if, flags)) continue;
          const id = typeof l === "string" ? l : l.officer;
          // Leaving someone not in the roster only marks them gone (장료 at 백문루).
          if (!OFFICERS[id] || OFFICERS[id]?.generic) add(`${where}: unknown officer ${id} leaves`);
          roster.delete(id);
        }
        for (const j of sc.joins ?? []) checkJoin(j, where);
      } else if (step.kind === "battle") {
        const b = step.battle;
        for (const p of validateStoryBattle(rules, b, roster, flags)) add(p);
        for (const f of b.fates ?? []) {
          const v = path[f.flag];
          flags[f.flag] = v === f.standing || v === f.fallen ? v : f.standing;
        }
      }
      // Camp talk may name anyone: a carried-over roster holds officers this
      // chapter's own roster never had, and the camp shows only those present.
    }
  }
  return [...problems];
}

function validateStoryBattle(
  rules: Ruleset,
  b: StoryBattleDef,
  roster: ReadonlySet<string>,
  flags: Readonly<Record<string, string>> = {},
): string[] {
  const problems: string[] = [];
  const say = (p: string) => problems.push(`battle ${b.id}: ${p}`);
  const units: ScenarioUnit[] = [...b.guests, ...(b.allies ?? []), ...b.enemies]
    .filter((u) => holds(u.if, flags))
    .map(({ if: _, ...u }) => u);
  const guests = b.guests.filter((u) => holds(u.if, flags));
  const goals = b.goals.filter((g) => holds(g.if, flags));
  // Roster officers fill the slots later; stand one in for side 0 if no guest does.
  const first = b.slots[0];
  const standIn: ScenarioUnit[] =
    guests.length > 0 || !first
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
  if ((b.allies ?? []).some((a) => a.side !== 2)) say("allies must be on side 2");
  if ((b.allies ?? []).length > 0 && b.sides.length < 3) say("allies need a side name");
  if (b.enemies.some((e) => e.side !== 1)) say("enemies must be on side 1");
  if (b.slots.length < b.max) say(`only ${b.slots.length} slots for ${b.max} officers`);
  const taken = new Set(units.filter((u) => !u.arrives).map((u) => `${u.x},${u.y}`));
  for (const c of b.slots) {
    if (c.x < 0 || c.y < 0 || c.x >= map.width || c.y >= map.height)
      say(`slot ${c.x},${c.y} is off the map`);
    else if (!standableByAll(rules, map, c)) say(`slot ${c.x},${c.y} is not open to every class`);
    if (taken.has(`${c.x},${c.y}`)) say(`slot ${c.x},${c.y} is taken by a unit`);
  }
  for (const id of b.required) if (!roster.has(id)) say(`required ${id} is not in the roster`);
  for (const a of (b.absent ?? []).filter((x) => holds(x.if, flags))) {
    if (!OFFICERS[a.officer]) say(`absent officer ${a.officer} is unknown`);
    if (b.required.includes(a.officer)) say(`absent officer ${a.officer} is required`);
    if (a.note.length === 0) say(`absent officer ${a.officer} needs a note`);
  }
  const known = new Set([...roster, ...units.map((u) => u.id ?? u.officer)]);
  const inMap = (c: Cell) => c.x >= 0 && c.y >= 0 && c.x < map.width && c.y < map.height;
  for (const g of goals) {
    if (g.when.kind === "defeat")
      for (const id of g.when.units) if (!known.has(id)) say(`goal names unknown unit ${id}`);
    if (g.when.kind === "hold" && g.when.round > b.roundLimit + 1)
      say("hold goal is past the round limit");
    if (g.when.kind === "reach")
      for (const c of g.when.cells) if (!inMap(c)) say("goal tile off the map");
  }
  if (!goals.some((g) => g.side === 0)) say("needs a goal for the player");
  const shiftIds = new Set<string>();
  for (const sh of (b.shifts ?? []).filter((x) => holds(x.if, flags))) {
    if (shiftIds.has(sh.id)) say(`shift ${sh.id} twice`);
    shiftIds.add(sh.id);
    if (!isPermille(sh.damage)) say(`shift ${sh.id}: damage must be 0–1000‰`);
    if (sh.tiles.length === 0) say(`shift ${sh.id} changes nothing`);
    for (const t of sh.tiles) {
      if (!inMap(t)) say(`shift ${sh.id}: tile off the map`);
      if (!(TERRAIN_IDS as readonly string[]).includes(t.terrain))
        say(`shift ${sh.id}: unknown terrain`);
    }
    const w = sh.when;
    if (w.kind === "reach") {
      for (const c of w.cells) if (!inMap(c)) say(`shift ${sh.id}: trigger off the map`);
    } else if (w.round < 2 || w.round > b.roundLimit) {
      say(`shift ${sh.id}: round out of range`);
    }
  }
  for (const c of b.marks ?? []) if (!inMap(c)) say("mark off the map");
  const duelIds = new Set<string>();
  const sideOf = (id: string) =>
    roster.has(id) ? 0 : units.find((u) => (u.id ?? u.officer) === id)?.side;
  // Allies (side 2) fight on our team (ADR 0066).
  const teamOf = (id: string) => {
    const side = sideOf(id);
    return side === 2 ? 0 : side;
  };
  const away = new Set((b.absent ?? []).filter((x) => holds(x.if, flags)).map((x) => x.officer));
  for (const d of (b.duels ?? []).filter((x) => holds(x.if, flags))) {
    if (duelIds.has(d.id)) say(`duel ${d.id} twice`);
    duelIds.add(d.id);
    if (d.a.length === 0) say(`duel ${d.id} has no challenger`);
    if (d.a.includes(d.b) || new Set(d.a).size !== d.a.length)
      say(`duel ${d.id} names an officer twice`);
    for (const id of [...d.a, d.b]) {
      if (!known.has(id)) say(`duel ${d.id} names unknown unit ${id}`);
      else if (OFFICERS[id]?.generic) say(`duel ${d.id}: ${id} is a troop, not an officer`);
      if (away.has(id)) say(`duel ${d.id}: ${id} sits this battle out`);
    }
    const sb = teamOf(d.b);
    if (d.a.some((id) => teamOf(id) === sb)) say(`duel ${d.id}: a challenger is on the foe's side`);
    if (d.fatal && (d.outcome === "draw" || d.outcome === "bWithdraws"))
      say(`duel ${d.id}: nobody falls in a ${d.outcome}, so it can't be fatal`);
    if (d.when.kind === "round" && (d.when.round < 1 || d.when.round > b.roundLimit))
      say(`duel ${d.id}: round out of range`);
    if (d.play && (d.a.length !== 1 || sideOf(d.a[0] ?? "") !== 0))
      say(`duel ${d.id}: a played duel needs one challenger of ours`);
    if (d.play && d.when.kind !== "adjacent")
      say(`duel ${d.id}: a played duel is offered when adjacent`);
    if (
      d.when.kind === "arrived" &&
      !units.some((u) => (u.id ?? u.officer) === (d.when as { unit: string }).unit && u.arrives)
    )
      say(`duel ${d.id} waits for a unit that never arrives`);
  }
  for (const f of b.fates ?? []) {
    if (!known.has(f.unit)) say(`fate names unknown unit ${f.unit}`);
    if (!FLAG_TEXT.test(f.flag) || !FLAG_TEXT.test(f.standing) || !FLAG_TEXT.test(f.fallen))
      say(`fate ${f.flag}: bad flag text`);
    if (f.standing === f.fallen) say(`fate ${f.flag}: both outcomes are the same`);
  }
  for (const t of b.talk.filter((x) => holds(x.if, flags))) {
    const w = t.when;
    if ((w.at === "defeated" || w.at === "arrived") && !known.has(w.unit))
      say(`talk names unknown unit ${w.unit}`);
    if (w.at === "arrived" && !units.some((u) => (u.id ?? u.officer) === w.unit && u.arrives))
      say(`talk waits for ${w.unit}, who never arrives`);
    if (w.at === "shifted" && !(b.shifts ?? []).some((sh) => sh.id === w.shift))
      say(`talk waits for shift ${w.shift}, which does not exist`);
    if ((w.at === "duel" || w.at === "duelEnd") && !(b.duels ?? []).some((d) => d.id === w.duel))
      say(`talk waits for duel ${w.duel}, which does not exist`);
    else if ((w.at === "duel" || w.at === "duelEnd") && !duelIds.has(w.duel))
      say(`talk waits for duel ${w.duel}, which does not happen with these flags`);
    for (const l of t.lines)
      if (l.who !== undefined && !OFFICERS[l.who]) say(`unknown speaker ${l.who}`);
  }
  for (const id of b.rewards.items) if (!ITEMS[id]) say(`unknown reward ${id}`);
  if (!isPositive(b.rewards.gold)) say("reward gold must be > 0");
  return problems;
}
