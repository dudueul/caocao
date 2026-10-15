/**
 * The eight P0 conditions (ADR 0035, ADR 0036). Modifiers are per stack; a
 * second stack of the same condition counts half, a third a quarter (tuning
 * `stackDiscount`). Durations live on the skills that apply them. Starting
 * tuning, our own numbers.
 */
import type { ConditionDef } from "@tk/rules";

const LIST: readonly ConditionDef[] = [
  { id: "confusion", name: "혼란", kind: "debuff", blocks: "act" },
  { id: "bind", name: "마비", kind: "debuff", blocks: "move" },
  { id: "seal", name: "금책", kind: "debuff", blocks: "stratagem" },
  { id: "poison", name: "독", kind: "debuff", dot: 60 },
  { id: "weaken", name: "약화", kind: "debuff", atk: -120, def: -120 },
  { id: "rally", name: "고무", kind: "buff", atk: 150 },
  { id: "guard", name: "견고", kind: "buff", def: 150 },
  { id: "haste", name: "신속", kind: "buff", move: 1, hit: 80 },
];

export const CONDITIONS: Readonly<Record<string, ConditionDef>> = Object.fromEntries(
  LIST.map((c) => [c.id, c]),
);
