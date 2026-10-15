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
} as const;

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
    case "battleEnd":
      return {
        text: e.winner < 0 ? T.draw : `${sideNames[e.winner] ?? e.winner} ${T.victory}`,
        tone: "turn",
      };
    default:
      return null;
  }
}
