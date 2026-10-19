<script lang="ts">
import { attackPower, conditionModifier, defensePower, hasBlock, type Unit } from "@tk/rules";
import type { Session } from "../lib/session.svelte.ts";
import { STATS, T } from "../lib/text.ts";
import Portrait from "./Portrait.svelte";

let { session, unit }: { session: Session; unit: Unit } = $props();
const cls = $derived(session.rules.classes[unit.classId]);
const side = $derived(session.sideNames[unit.side] ?? `${unit.side}`);
const atk = $derived(attackPower(session.rules, session.state, unit));
const def = $derived(defensePower(session.rules, session.state, unit));
const move = $derived(
  hasBlock(session.rules, unit, "move")
    ? 0
    : Math.max(0, (cls?.move ?? 0) + conditionModifier(session.rules, unit, "move")),
);

/** One chip per condition: its stacks' turns left, and what it does. */
const groups = $derived.by(() => {
  const out: { id: string; name: string; kind: string; turns: number[]; summary: string }[] = [];
  for (const c of unit.conditions) {
    let g = out.find((x) => x.id === c.id);
    if (!g) {
      const d = session.rules.conditions[c.id];
      const parts: string[] = [];
      if (d?.atk) parts.push(`공격 ${d.atk > 0 ? "+" : ""}${d.atk / 10}%`);
      if (d?.def) parts.push(`방어 ${d.def > 0 ? "+" : ""}${d.def / 10}%`);
      if (d?.hit) parts.push(`명중 ${d.hit > 0 ? "+" : ""}${d.hit / 10}%`);
      if (d?.move) parts.push(`이동 ${d.move > 0 ? "+" : ""}${d.move}`);
      if (d?.dot) parts.push(`매 턴 HP −${d.dot / 10}%`);
      if (d?.blocks === "act") parts.push("행동 불가");
      if (d?.blocks === "move") parts.push("이동 불가");
      if (d?.blocks === "stratagem") parts.push("책략 불가");
      g = {
        id: c.id,
        name: d?.name ?? c.id,
        kind: d?.kind ?? "debuff",
        turns: [],
        summary: `${parts.join(", ")} (겹칠수록 효과 감소)`,
      };
      out.push(g);
    }
    g.turns.push(c.turns);
  }
  return out;
});
</script>

<section class="card" class:enemy={unit.side !== session.humanSide}>
  <header>
    <Portrait id={unit.id} classId={unit.classId} side={unit.side} size={52} />
    <strong>{unit.name}</strong>
    <span class="chip">{cls?.name}</span>
    <span class="chip">Lv {unit.level}</span>
    <span class="muted side">{side}</span>
  </header>
  <div class="bars">
    <div class="bar">
      <span>{T.hp}</span>
      <div class="track">
        <div class="fill hp" style:width="{(100 * unit.hp) / unit.maxHp}%"></div>
      </div>
      <span class="num">{unit.hp}/{unit.maxHp}</span>
    </div>
    {#if unit.maxMp > 0}
      <div class="bar">
        <span>{T.mp}</span>
        <div class="track">
          <div class="fill mp" style:width="{(100 * unit.mp) / unit.maxMp}%"></div>
        </div>
        <span class="num">{unit.mp}/{unit.maxMp}</span>
      </div>
    {/if}
  </div>
  <div class="stats">
    {#each STATS as [k, label] (k)}
      <div><span class="muted">{label}</span> {unit.stats[k]}</div>
    {/each}
    <div><span class="muted">공격</span> {atk}</div>
    <div><span class="muted">방어</span> {def}</div>
    <div><span class="muted">{T.move}</span> {move}</div>
  </div>
  <div class="tags">
    {#each groups as g (g.id)}
      <span class="chip {g.kind}" title={g.summary}>
        {g.name}
        {g.kind === "buff" ? "▲" : "▼"}
        {g.turns.join("·")}{T.turns}
      </span>
    {/each}
    {#if unit.broken}
      <span class="chip warn">{T.broken}</span>
    {/if}
    {#if unit.acted}
      <span class="chip">{T.acted}</span>
    {/if}
  </div>
</section>

<style>
.card {
  padding: 8px 10px;
  border-radius: 12px;
  border: 1px solid rgba(79, 142, 240, 0.45);
  background: linear-gradient(135deg, rgba(79, 142, 240, 0.16), rgba(255, 255, 255, 0.02) 55%);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.06);
}
.card.enemy {
  border-color: rgba(224, 100, 90, 0.5);
  background: linear-gradient(135deg, rgba(224, 100, 90, 0.16), rgba(255, 255, 255, 0.02) 55%);
}
header :global(.face) {
  border-color: rgba(232, 194, 106, 0.7);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.5);
}
header {
  display: flex;
  gap: 6px;
  align-items: center;
  flex-wrap: wrap;
}
header strong {
  font-family: var(--display);
  font-size: 18px;
  letter-spacing: 0.04em;
}
.side {
  margin-left: auto;
  font-size: 12px;
}
.bars {
  margin: 6px 0;
  display: grid;
  gap: 3px;
}
.bar {
  display: grid;
  grid-template-columns: 26px 1fr 64px;
  align-items: center;
  gap: 6px;
  font-size: 12px;
}
.track {
  height: 7px;
  background: rgba(0, 0, 0, 0.5);
  border-radius: 4px;
  overflow: hidden;
}
.fill {
  height: 100%;
}
.fill {
  border-radius: 4px;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.35);
}
.fill.hp {
  background: linear-gradient(90deg, #3fae55, #8ee87a);
}
.fill.mp {
  background: linear-gradient(90deg, #3d78e0, #8cc0ff);
}
.num {
  text-align: right;
  font-variant-numeric: tabular-nums;
}
.stats {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 2px 8px;
  font-size: 12px;
  font-variant-numeric: tabular-nums;
}
.tags {
  margin-top: 4px;
  display: flex;
  gap: 4px;
}
.buff {
  border-color: var(--good);
  color: var(--good);
}
.debuff,
.warn {
  border-color: #c58cff;
  color: #e2c8ff;
}
</style>
