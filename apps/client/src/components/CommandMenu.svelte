<script lang="ts">
import { hasBlock, stratagemDef } from "@tk/rules";
import { tick } from "svelte";
import type { Session } from "../lib/session.svelte.ts";
import { SCHOOL_COLOR, stratagemSummary, T } from "../lib/text.ts";

/**
 * The command menu beside the unit's chosen tile: 공격 · 책략 ▸ · 일기토 · 대기 ·
 * 이동 취소. 책략 opens the unit's skills in place; 일기토 shows only when a named
 * officer stands next to an enemy officer, with ★ when history has them meet
 * (ADR 0071). Arrows move, Enter picks, Esc steps back.
 */
let { session }: { session: Session } = $props();
let skills = $state(false);
let box: HTMLDivElement;

const unit = $derived(session.unit);
const options = $derived.by(() =>
  session.stratagemOptions.map((o) => {
    const def = stratagemDef(session.rules, o.id);
    const reason =
      o.cells.length > 0
        ? null
        : unit && hasBlock(session.rules, unit, "stratagem")
          ? T.sealed
          : unit && unit.mp < o.mp
            ? T.noMp
            : T.noTargets;
    return {
      ...o,
      reason,
      color: `#${SCHOOL_COLOR[def.school].toString(16).padStart(6, "0")}`,
      summary: stratagemSummary(session.rules, def),
    };
  }),
);
const castable = $derived(options.some((o) => o.reason === null));
/** History's foe for this officer here, if the 일기토 on offer is a story duel. */
const storyFoe = $derived(
  session.storyDuels.length > 0
    ? (session.state.units.find((u) => u.id === session.storyDuels[0])?.name ?? null)
    : null,
);

function buttons(): HTMLButtonElement[] {
  return [...box.querySelectorAll<HTMLButtonElement>("button:not(:disabled)")];
}

async function focusFirst() {
  await tick();
  buttons()[0]?.focus();
}

// A new tile, or switching between the main list and the skills, puts the focus on the first item.
$effect(() => {
  void session.to;
  void skills;
  void focusFirst();
});

function openSkills() {
  skills = true;
}

function onKey(e: KeyboardEvent) {
  const list = buttons();
  const i = list.indexOf(document.activeElement as HTMLButtonElement);
  if (e.key === "ArrowDown" || e.key === "ArrowUp") {
    const step = e.key === "ArrowDown" ? 1 : -1;
    list[(i + step + list.length) % list.length]?.focus();
  } else if (e.key === "ArrowRight" && !skills && castable) openSkills();
  else if ((e.key === "ArrowLeft" || e.key === "Escape") && skills) skills = false;
  else if (e.key === "Escape") session.back();
  else return;
  e.preventDefault();
  e.stopPropagation();
}
</script>

<!-- svelte-ignore a11y_no_noninteractive_element_interactions -->
<div class="menu" role="menu" tabindex="-1" bind:this={box} onkeydown={onKey}>
  {#if skills}
    <button type="button" class="head" role="menuitem" onclick={() => (skills = false)}>
      ◂ {T.stratagem}
    </button>
    {#each options as o (o.id)}
      <button
        type="button"
        class="skill"
        role="menuitem"
        disabled={o.reason !== null}
        onclick={() => session.chooseStratagem(o.id)}
      >
        <span class="dot" style:background={o.color}></span>
        <span class="name">{o.name}</span>
        <span class="mp">MP {o.mp}</span>
        <span class="sub">{o.reason ?? o.summary}</span>
      </button>
    {/each}
  {:else}
    <button
      type="button"
      role="menuitem"
      disabled={session.attackCells.length === 0}
      onclick={() => session.chooseAttack()}
    >
      <span class="icon">⚔</span>{T.attack}
    </button>
    {#if options.length > 0}
      <button type="button" role="menuitem" disabled={!castable} onclick={openSkills}>
        <span class="icon">✦</span>{T.stratagem}<span class="more">▸</span>
      </button>
    {/if}
    {#if session.duelCells.length > 0}
      <button
        type="button"
        role="menuitem"
        class="duel"
        class:story={storyFoe !== null}
        onclick={() => session.chooseDuel()}
      >
        <span class="icon">{storyFoe ? "★" : "⚑"}</span>
        <span class="dname">{T.duel}</span>
        {#if storyFoe}
          <span class="dsub">{T.storyDuel} · {storyFoe}</span>
        {/if}
      </button>
    {/if}
    <button type="button" role="menuitem" onclick={() => session.wait()}>
      <span class="icon">⏸</span>{T.wait}
    </button>
    <button type="button" role="menuitem" onclick={() => session.undoMove()}>
      <span class="icon">↶</span>{T.undoMove}
    </button>
  {/if}
</div>

<style>
.menu {
  display: grid;
  gap: 4px;
  min-width: 132px;
  outline: none;
}
.menu button {
  display: flex;
  align-items: center;
  gap: 8px;
  text-align: left;
  padding: 7px 10px;
}
.menu button:focus-visible {
  outline: 2px solid var(--accent);
  outline-offset: -1px;
}
.icon {
  width: 16px;
  text-align: center;
  color: var(--accent);
}
.more {
  margin-left: auto;
  color: var(--muted);
}
.head {
  font-weight: 700;
  color: var(--accent);
}
.menu button.duel {
  border-color: rgba(232, 194, 106, 0.45);
}
.menu button.duel.story {
  display: grid;
  grid-template-columns: 16px 1fr;
  grid-template-areas: "icon name" ". sub";
  gap: 0 8px;
  background: linear-gradient(180deg, rgba(110, 82, 30, 0.95), rgba(58, 42, 16, 0.95));
  border-color: var(--accent);
  box-shadow:
    inset 0 1px 0 rgba(255, 240, 200, 0.2),
    0 0 14px rgba(232, 194, 106, 0.3);
}
.duel.story .icon {
  grid-area: icon;
  color: #ffe08a;
  text-shadow: 0 0 8px rgba(255, 210, 100, 0.8);
}
.duel.story .dname {
  grid-area: name;
  font-weight: 800;
}
.duel.story .dsub {
  grid-area: sub;
  font-size: 11.5px;
  color: #f2d58a;
}
.menu button.skill {
  display: grid;
  grid-template-columns: 10px 1fr auto;
  grid-template-areas: "dot name mp" ". sub sub";
  gap: 1px 8px;
  min-width: 220px;
}
.dot {
  grid-area: dot;
  width: 10px;
  height: 10px;
  border-radius: 50%;
}
.name {
  grid-area: name;
  font-weight: 700;
}
.mp {
  grid-area: mp;
  font-size: 12px;
  color: #9cc4ff;
  font-variant-numeric: tabular-nums;
}
.sub {
  grid-area: sub;
  font-size: 11.5px;
  color: var(--muted);
}
</style>
