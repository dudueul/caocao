<script lang="ts">
import type { Forecast, Session } from "../lib/session.svelte.ts";
import { conditionName, pct, STAGE, T } from "../lib/text.ts";
import Portrait from "./Portrait.svelte";

let { session, forecast }: { session: Session; forecast: Forecast } = $props();
const name = (id: string) => session.state.units.find((u) => u.id === id)?.name ?? id;
</script>

<section class="forecast">
  {#if forecast.kind === "attack"}
    {@const p = forecast.preview}
    {@const strikes = p.double ? 2 : 1}
    {@const left = Math.max(0, forecast.defender.hp - p.damage * strikes)}
    <div class="vs">
      <div>
        <div class="who">
          <Portrait
            id={forecast.attacker.id}
            classId={forecast.attacker.classId}
            side={forecast.attacker.side}
            size={40}
          />
          <strong>{forecast.attacker.name}</strong>
        </div>
        <div class="big">
          {p.damage}
          {#if p.double}
            <span class="x2">×2</span>
          {/if}
        </div>
        <div class="muted">{T.hit} {pct(p.hit)} · {T.crit} {pct(p.crit)} ({p.critDamage})</div>
      </div>
      <div class="arrow">▶</div>
      <div>
        <div class="who">
          <Portrait
            id={forecast.defender.id}
            classId={forecast.defender.classId}
            side={forecast.defender.side}
            size={40}
          />
          <strong>{forecast.defender.name}</strong>
        </div>
        <div class="big">{forecast.defender.hp} → {left}</div>
        {#if p.counter && !(p.damage >= forecast.defender.hp)}
          <div class="muted">{T.counter} {p.counter.damage} · {T.hit} {pct(p.counter.hit)}</div>
        {:else}
          <div class="muted">{T.noCounter}</div>
        {/if}
      </div>
    </div>
    <div class="tags">
      {#if p.breaks && p.counter}
        <span class="chip breaks">{T.breaks}</span>
      {/if}
      {#if p.damage * strikes >= forecast.defender.hp}
        <span class="chip kill">격파 가능</span>
      {/if}
    </div>
    <div class="steps">
      {#each p.breakdown.filter(
        (step, i) => i === 0 || step.value !== p.breakdown[i - 1]?.value,
      ) as step, i (step.stage)}
        {#if i > 0}
          <span class="muted">→</span>
        {/if}
        <span class="chip">{STAGE[step.stage] ?? step.stage} {step.value}</span>
      {/each}
    </div>
  {:else}
    {@const p = forecast.preview}
    {@const def = session.rules.stratagems[forecast.id]}
    <div class="who">
      <Portrait
        id={forecast.caster.id}
        classId={forecast.caster.classId}
        side={forecast.caster.side}
        size={40}
      />
      <div><strong>{def?.name}</strong> <span class="muted">MP {def?.mp}</span></div>
    </div>
    {#each p.targets as t (t.unit)}
      <div class="target">
        <span>{name(t.unit)}</span>
        {#if t.damage > 0}
          <span>{T.damage} {t.damage}</span>
        {/if}
        {#if t.heal > 0}
          <span class="good">{T.heal} +{t.heal}</span>
        {/if}
        {#if p.condition && t.chance < 1000}
          <span>{T.chance} {pct(t.chance)}</span>
        {/if}
      </div>
    {/each}
    <div class="tags">
      {#if p.condition && p.turns}
        <span class="chip" class:buff={session.rules.conditions[p.condition]?.kind === "buff"}>
          {conditionName(session.rules, p.condition)}
          {p.turns[0] === p.turns[1] ? p.turns[0] : `${p.turns[0]}–${p.turns[1]}`}{T.turns}
        </span>
      {/if}
      {#if p.cleanse}
        <span class="chip">{T.cleansed}</span>
      {/if}
      {#if p.ignites}
        <span class="chip">{T.ignites}</span>
      {/if}
      {#if p.push > 0}
        <span class="chip">{T.push} {p.push}</span>
      {/if}
      <span class="chip muted">{T.twist} {pct(p.twist)} · {T.windfall} {pct(p.windfall)}</span>
    </div>
  {/if}
  <div class="row">
    <button
      type="button"
      class="primary"
      disabled={session.target === null}
      onclick={() => session.confirm()}
    >
      {T.confirm}
    </button>
    <button type="button" onclick={() => session.back()}>{T.back}</button>
  </div>
</section>

<style>
.who {
  display: flex;
  align-items: center;
  gap: 6px;
}
.vs {
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  gap: 8px;
  align-items: center;
}
.big {
  font-size: 20px;
  font-weight: 700;
  font-variant-numeric: tabular-nums;
}
.x2 {
  font-size: 13px;
  color: var(--twist);
  margin-left: 4px;
}
.arrow {
  color: var(--muted);
}
.muted {
  font-size: 12px;
}
.tags,
.steps,
.row {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  align-items: center;
  margin-top: 6px;
}
.breaks {
  border-color: #ff9f43;
  color: #ffc58a;
}
.kill {
  border-color: var(--bad);
  color: var(--bad);
}
.target {
  display: flex;
  gap: 10px;
  font-variant-numeric: tabular-nums;
}
.good {
  color: var(--good);
}
.buff {
  border-color: var(--good);
  color: var(--good);
}
</style>
