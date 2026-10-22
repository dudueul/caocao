/**
 * UI strings (Korean first; open question 10 in docs/remake/README.md) and
 * event-log lines. Every user-facing word lives here so a second language is
 * one more table.
 */
import type {
  BattleEvent,
  BattleState,
  Fate,
  Ruleset,
  Stance,
  StratagemDef,
  StratagemSchool,
  TurnPolicyId,
  WeatherKind,
} from "@tk/rules";

export const T = {
  title: "전투 샌드박스",
  round: "라운드",
  attack: "공격",
  stratagem: "책략",
  wait: "대기",
  undoMove: "이동 취소",
  confirm: "확인",
  back: "뒤로",
  endPhase: "턴 종료",
  danger: "위험 범위",
  auto: "자동 전투",
  speed: "속도",
  skip: "생략",
  newBattle: "새 전투",
  log: "전황",
  hit: "명중",
  damage: "피해",
  crit: "치명",
  double: "2회 공격",
  counter: "반격",
  noCounter: "반격 없음",
  breaks: "와해: 반격 불가",
  twist: "운의 반전",
  windfall: "천운",
  mishap: "불운",
  mp: "MP",
  hp: "HP",
  move: "이동",
  cover: "엄폐",
  heal: "회복",
  effect: "지형 효과",
  impassable: "통과 불가",
  acted: "행동 완료",
  confused: "혼란",
  broken: "와해",
  thinking: "적군 행동 중…",
  alliesThinking: "우군 행동 중…",
  yourTurn: "아군 차례",
  victory: "승리",
  defeat: "패배",
  draw: "무승부",
  noTargets: "대상 없음",
  pickTile: "이동할 칸을 고르세요",
  pickAction: "행동을 고르세요",
  pickTarget: "대상을 고르세요",
  ignites: "점화",
  push: "밀어내기",
  chance: "성공",
  turns: "턴",
  resisted: "저항",
  cleansed: "정화",
  conditions: "상태",
  opponentTurn: "상대 차례…",
  conceded: "기권",
  concede: "기권",
  concedeConfirm: "정말 기권?",
  home: "홈",
  arena: "아레나",
  reserve: "예비",
  reconnecting: "다시 연결 중…",
  sandbox: "샌드박스",
  watchDemo: "관전 데모",
  createRoom: "아레나 방 만들기",
  joinRoom: "참가",
  roomCode: "방 코드",
  yourName: "이름",
  inviteFriend: "친구에게 이 링크를 보내세요",
  copy: "복사",
  copied: "복사됨",
  waitingForFriend: "친구를 기다리는 중…",
  draft: "드래프트",
  myPick: "내 차례: 장수를 고르세요",
  theirPick: "상대가 고르는 중…",
  pick: "선택",
  deploy: "배치",
  deployHint: "두 장수를 차례로 눌러 자리를 바꾸세요",
  ready: "준비 완료",
  waitingReady: "상대를 기다리는 중…",
  you: "나",
  roomMissing: "방에 들어갈 수 없습니다",
  cancel: "취소",
  noMp: "MP 부족",
  sealed: "책략 불가",
  map: "지도",
  defaultMap: "기본 지도",
  duel: "일기토",
  storyDuel: "역사의 일기토",
  martial: "무력",
  acceptChance: "응할 확률",
  alwaysAccepts: "반드시 응한다",
  blow: "일격",
  refuseNote: "응하지 않으면 행동 끝 · 상대 진영 약화",
  triangle: "강공 > 공격 > 방어 > 강공",
  withdraw: "물러나기",
  bout: "합",
  fallen: "전사",
  withdrew: "물러남",
  continue: "계속",
  pickStance: "자세를 고르세요",
} as const;

/** The three 일기토 stances (ADR 0071): a name, a glyph, and what each beats and loses to. */
export const STANCE: Record<Stance, { name: string; glyph: string; hint: string }> = {
  attack: { name: "공격", glyph: "攻", hint: "방어를 뚫고 강공에 진다" },
  power: { name: "강공", glyph: "強", hint: "공격을 꺾고 방어에 진다" },
  guard: { name: "방어", glyph: "防", hint: "강공을 받아치고 공격에 진다" },
};

/**
 * What happened when two stances met, in a few words. `hitA` and `hitB` say
 * who took a blow: a story duel bends the blows to history, and then the
 * words follow the blows rather than the stances.
 */
export function clashWord(a: Stance, b: Stance, hitA: boolean, hitB: boolean): string {
  const beats = (x: Stance, y: Stance) =>
    (x === "power" && y === "attack") ||
    (x === "guard" && y === "power") ||
    (x === "attack" && y === "guard");
  const both = a === b && a !== "guard";
  const asStances =
    a === b ? hitA === both && hitB === both : hitA === beats(b, a) && hitB === beats(a, b);
  if (!asStances) {
    if (hitA && hitB) return "서로 칼을 주고받는다!";
    return hitA || hitB ? "빈틈을 파고든다!" : "칼끝이 비껴간다";
  }
  if (a === b)
    return a === "attack" ? "맞부딪친다!" : a === "power" ? "강공끼리 격돌!" : "서로 노려본다";
  const w = beats(a, b) ? a : b;
  if (w === "power") return "강공이 공격을 꺾는다";
  if (w === "guard") return "방어가 강공을 받아친다";
  return "공격이 방어를 뚫는다";
}

/** A name with the right particle: `josa("화웅", "이", "가")` is 화웅이, `josa("관우", "이", "가")` 관우가. */
export function josa(word: string, withFinal: string, without: string): string {
  const c = word.charCodeAt(word.length - 1);
  if (Number.isNaN(c) || c < 0xac00 || c > 0xd7a3) return `${word}${withFinal}(${without})`;
  return word + ((c - 0xac00) % 28 > 0 ? withFinal : without);
}

/** The line floated over an officer who turns a challenge down. */
export function refusal(name: string): string {
  return `${josa(name, "이", "가")} 응하지 않는다!`;
}

/** Display name of a condition. */
export function conditionName(rules: Ruleset, id: string): string {
  return rules.conditions[id]?.name ?? id;
}

export const STATS = [
  ["martial", "무력"],
  ["command", "통솔"],
  ["intellect", "지력"],
  ["agility", "민첩"],
  ["fortune", "운"],
] as const;

export const POLICY: Record<TurnPolicyId, string> = {
  sidePhases: "진영 턴",
  alternating: "교대 행동",
};

export const WEATHER: Record<WeatherKind, string> = {
  sunny: "맑음",
  cloudy: "흐림",
  rain: "비",
  wind: "바람",
};

export const FATE: Record<Fate, string> = {
  escaped: "퇴각",
  captured: "포로",
  killed: "전사",
};

export const STAGE: Record<string, string> = {
  base: "기본",
  relation: "상성",
  role: "반격",
  critical: "치명",
  mode: "모드",
};

export const SCHOOL_COLOR: Record<StratagemSchool, number> = {
  fire: 0xff7a2f,
  water: 0x3fa7ff,
  earth: 0xb08850,
  wind: 0x6fe0c8,
  support: 0x7ee07a,
  disruption: 0xc58cff,
};

/** One line on what a stratagem does, for the skill menu. */
export function stratagemSummary(rules: Ruleset, def: StratagemDef): string {
  const parts: string[] = [];
  if (def.effect === "damage") parts.push(T.damage);
  if (def.effect === "heal") parts.push(T.heal);
  if (def.condition) {
    const [lo, hi] = def.turns ?? [1, 1];
    parts.push(
      `${conditionName(rules, def.condition)} ${lo === hi ? lo : `${lo}–${hi}`}${T.turns}`,
    );
  }
  if (def.cleanse) parts.push(T.cleansed);
  if (def.push) parts.push(`${T.push} ${def.push}`);
  if (def.ignites) parts.push(T.ignites);
  const reach = Math.max(0, ...def.range.map((o) => Math.abs(o.dx) + Math.abs(o.dy)));
  parts.push(reach === 0 ? "자신" : `사거리 ${reach}`);
  if (def.area.length > 1) parts.push(`범위 ${def.area.length}칸`);
  return parts.join(" · ");
}

/** ‰ as a whole percent, for display. */
export function pct(permille: number): string {
  return `${Math.round(permille / 10)}%`;
}

export interface LogLine {
  id: number;
  text: string;
  tone: "info" | "good" | "bad" | "twist" | "turn";
}

/** One readable line per event (null for events not worth a line). */
export function describe(
  e: BattleEvent,
  state: BattleState,
  rules: Ruleset,
  sideNames: readonly string[],
  humanSide: number,
): Omit<LogLine, "id"> | null {
  const name = (id: string) => state.units.find((u) => u.id === id)?.name ?? id;
  const sideOf = (id: string) => state.units.find((u) => u.id === id)?.side ?? -1;
  const tone = (id: string, good: boolean) =>
    (sideOf(id) === humanSide) === good ? "good" : "bad";
  switch (e.kind) {
    case "strike": {
      const role = e.role === "counter" ? ` (${T.counter})` : e.role === "second" ? " (2회째)" : "";
      if (!e.hit)
        return { text: `${name(e.attacker)} → ${name(e.defender)}: 빗나감${role}`, tone: "info" };
      const crit = e.crit ? ` ${T.crit}!` : "";
      const broke = e.broke ? ` ${T.broken}!` : "";
      return {
        text: `${name(e.attacker)} → ${name(e.defender)}: ${e.damage}${crit}${broke}${role}`,
        tone: tone(e.attacker, true),
      };
    }
    case "defeated":
      return { text: `${name(e.unit)} ${FATE[e.fate]}`, tone: tone(e.unit, false) };
    case "stratagem":
      return {
        text: `${name(e.caster)}: ${rules.stratagems[e.id]?.name ?? e.id}`,
        tone: "info",
      };
    case "stratagemEffect": {
      if (e.heal > 0)
        return { text: `${name(e.unit)} ${T.heal} +${e.heal}`, tone: tone(e.unit, true) };
      const parts: string[] = [];
      if (e.damage > 0) parts.push(`${T.damage} ${e.damage}`);
      if (e.condition) parts.push(`${conditionName(rules, e.condition)} ${e.turns}${T.turns}`);
      if (e.resisted) parts.push(T.resisted);
      if (parts.length === 0) return null;
      const buff = e.condition !== null && rules.conditions[e.condition]?.kind === "buff";
      return {
        text: `${name(e.unit)} ${parts.join(" · ")}`,
        tone: e.resisted && e.damage === 0 ? "info" : tone(e.unit, buff),
      };
    }
    case "cleansed":
      return {
        text: `${name(e.unit)} ${T.cleansed}: ${e.removed.map((id) => conditionName(rules, id)).join(", ")}`,
        tone: tone(e.unit, true),
      };
    case "conditionDamage":
      return { text: `${name(e.unit)} 독 ${e.damage}`, tone: tone(e.unit, false) };
    case "conditionEnded":
      return { text: `${name(e.unit)} ${conditionName(rules, e.condition)} 해제`, tone: "info" };
    case "twist":
      return {
        text: `${e.outcome === "windfall" ? T.windfall : T.mishap}! ${name(e.caster)} (${e.effect})`,
        tone: "twist",
      };
    case "pushed":
      return e.collided
        ? { text: `${name(e.unit)} 충돌 ${e.damage}`, tone: tone(e.unit, false) }
        : { text: `${name(e.unit)} 밀려남`, tone: "info" };
    case "ignited":
      return null;
    case "burned":
      return { text: `${name(e.unit)} 화상 ${e.damage}`, tone: tone(e.unit, false) };
    case "terrainHeal":
      return { text: `${name(e.unit)} 지형 ${T.heal} +${e.amount}`, tone: tone(e.unit, true) };
    case "turn":
      return { text: `${T.round} ${e.round} · ${sideNames[e.side] ?? e.side}`, tone: "turn" };
    case "arrived":
      return { text: `${name(e.unit)} 도착`, tone: tone(e.unit, true) };
    case "shifted":
      return {
        text: e.tiles.some((t) => t.terrain === "water") ? "물길이 터졌다!" : "지형이 바뀌었다!",
        tone: "twist",
      };
    case "swept":
      return { text: `${name(e.unit)} 물에 휩쓸림 ${e.damage}`, tone: tone(e.unit, false) };
    case "battleEnd":
      return {
        text: e.winner < 0 ? T.draw : `${sideNames[e.winner] ?? e.winner} ${T.victory}`,
        tone: "turn",
      };
    case "challenge":
      if (!e.accepted)
        return {
          text: `${josa(name(e.b), "이", "가")} ${name(e.a)}의 도전에 응하지 않음`,
          tone: "info",
        };
      return {
        text: `${e.script ? "★ " : ""}${name(e.a)} ⚔ ${name(e.b)}: ${e.script ? T.storyDuel : T.duel}!`,
        tone: "twist",
      };
    case "bout": {
      const blows = [
        e.damageB > 0 ? `${name(e.b)} -${e.damageB}` : "",
        e.damageA > 0 ? `${name(e.a)} -${e.damageA}` : "",
      ].filter((x) => x);
      return {
        text: `${T.duel} ${e.n}${T.bout} · ${name(e.a)} ${STANCE[e.stanceA].name} / ${name(e.b)} ${STANCE[e.stanceB].name} → ${blows.join(" · ") || "승부 없음"}`,
        tone: e.damageA === e.damageB ? "info" : tone(e.damageB > e.damageA ? e.a : e.b, true),
      };
    }
    case "duelEnd":
      if (e.winner && e.loser)
        return {
          text: `${josa(name(e.winner), "이", "가")} ${T.duel}에서 ${josa(name(e.loser), "을", "를")} 꺾다${e.fatal ? ` · ${name(e.loser)} ${T.fallen}` : ""}`,
          tone: tone(e.winner, true),
        };
      if (e.withdrew)
        return { text: `${name(e.withdrew)}, ${T.duel}에서 ${T.withdrew}`, tone: "info" };
      return { text: `${T.duel} ${T.draw}: ${name(e.a)} · ${name(e.b)}`, tone: "info" };
    case "morale": {
      const def = rules.conditions[e.condition];
      const names = e.units.slice(0, 3).map(name).join(", ");
      const more = e.units.length > 3 ? ` 외 ${e.units.length - 3}` : "";
      const first = e.units[0] ?? "";
      return {
        text: `${conditionName(rules, e.condition)} ${e.turns}${T.turns}: ${names}${more}`,
        tone: tone(first, def?.kind === "buff"),
      };
    }
    default:
      return null;
  }
}
