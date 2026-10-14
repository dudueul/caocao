<script lang="ts">
import { attackPower, defensePower, type Unit } from "@tk/rules";
import type { Session } from "../lib/session.svelte.ts";
import { STATS, T } from "../lib/text.ts";

let { session, unit }: { session: Session; unit: Unit } = $props();
const cls = $derived(session.rules.classes[unit.classId]);
const side = $derived(session.scenario.sides[unit.side] ?? `${unit.side}`);
const atk = $derived(attackPower(session.rules, session.state, unit));
const def = $derived(defensePower(session.rules, session.state, unit));
</script>

<section class="card" class:enemy={unit.side !== session.humanSide}>
  <header>
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
    <div><span class="muted">{T.move}</span> {cls?.move}</div>
  </div>
  <div class="tags">
    {#if unit.status}
      <span class="chip warn">{T.confused} {unit.status.rounds}</span>
    {/if}
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
  border-left: 3px solid var(--ally);
  padding-left: 10px;
}
.card.enemy {
  border-left-color: var(--enemy);
}
header {
  display: flex;
  gap: 6px;
  align-items: center;
  flex-wrap: wrap;
}
header strong {
  font-size: 16px;
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
.fill.hp {
  background: var(--good);
}
.fill.mp {
  background: #6fa8ff;
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
.warn {
  border-color: #c58cff;
  color: #e2c8ff;
}
</style>
