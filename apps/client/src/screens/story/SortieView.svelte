<script lang="ts">
import { battleProfile, chapterOf, type Officer, type Placement } from "@tk/campaign";
import { OFFICERS, P0_RULES, parseMap } from "@tk/content";
import { type Cell, forMode, type WeatherKind } from "@tk/rules";
import Portrait from "../../components/Portrait.svelte";
import { audio } from "../../lib/audio/index.ts";
import { TERRAIN_COLOR } from "../../lib/field.ts";
import { story } from "../../lib/story.svelte.ts";
import { WEATHER } from "../../lib/text.ts";

/** 출진: pick who goes out and where they stand, then launch the battle. */
const L = {
  kicker: "출진 준비",
  win: "승리",
  lose: "패배",
  field: "전장",
  rounds: (n: number) => `제한 ${n}라운드`,
  foes: (n: number) => `적 ${n}`,
  reinforce: (n: number) => `증원 ${n}`,
  roster: "출진 장수",
  required: "필수",
  guest: "객장",
  go: "출진",
  ready: (n: number) => `${n}명 출진`,
  full: (n: number) => `최대 ${n}명까지 출진할 수 있습니다`,
  locked: "꼭 출진해야 하는 장수입니다",
  hintMap: "지도에서 아군을 눌러 자리를 옮기거나 바꿀 수 있습니다",
  hintPick: (name: string) => `${name}: 옮길 칸을 누르세요`,
  mine: "아군",
  ally: "우군",
  guestTroops: "아군 부대",
  foe: "적군",
  boss: "적장",
  slot: "배치 칸",
  hp: "HP",
  atk: "공",
  def: "방",
  move: "이",
} as const;

const TUNING = forMode(P0_RULES, "story").tuning;
const battle = $derived(story.battle);
const roster = $derived(story.campaign.roster);
const map = $derived(battle ? parseMap(battle.map) : null);
const cap = $derived(battle ? Math.min(battle.max, battle.slots.length) : 0);
const error = $derived(story.sortieError());
const chapter = $derived.by(() => {
  const ch = chapterOf(story.campaign);
  return `${ch.name} ${ch.title}`;
});
const foes = $derived(battle?.enemies.filter((u) => u.arrives === undefined).length ?? 0);
const later = $derived(battle?.enemies.filter((u) => u.arrives !== undefined).length ?? 0);
/** Units the player must defeat to win. */
const bosses = $derived(
  new Set(
    battle?.goals.flatMap((g) => (g.side === 0 && g.when.kind === "defeat" ? g.when.units : [])) ??
      [],
  ),
);

/** The officer picked on the map, to move or swap. */
let picked = $state<string | null>(null);
let notice = $state<string | null>(null);
let noticeTimer: ReturnType<typeof setTimeout> | undefined;
/** Bumped to replay the shake on a locked card. */
let shake = $state<{ id: string; n: number } | null>(null);

const same = (a: Cell, b: Cell) => a.x === b.x && a.y === b.y;
const hex = (n: number) => `#${n.toString(16).padStart(6, "0")}`;
const nameOf = (id: string) => OFFICERS[id]?.name ?? id;

function say(text: string) {
  notice = text;
  clearTimeout(noticeTimer);
  noticeTimer = setTimeout(() => (notice = null), 1800);
}

function placementOf(id: string): Placement | undefined {
  return story.sortie.find((p) => p.officer === id);
}

function slotIndex(c: Cell): number {
  return battle?.slots.findIndex((s) => same(s, c)) ?? -1;
}

function toggle(o: Officer) {
  if (!battle) return;
  if (placementOf(o.id)) {
    if (battle.required.includes(o.id)) {
      audio.play("cancel");
      shake = { id: o.id, n: (shake?.n ?? 0) + 1 };
      say(L.locked);
      return;
    }
    story.sortie = story.sortie.filter((p) => p.officer !== o.id);
    if (picked === o.id) picked = null;
    audio.play("cancel");
    return;
  }
  const free = battle.slots.find((c) => !story.sortie.some((p) => same(p.at, c)));
  if (story.sortie.length >= cap || !free) {
    audio.play("cancel");
    say(L.full(cap));
    return;
  }
  story.sortie = [...story.sortie, { officer: o.id, at: { x: free.x, y: free.y } }];
  audio.play("select");
}

/** Map taps: pick an officer, then a slot to move to (swapping with whoever stands there). */
function tapSlot(c: Cell) {
  const here = story.sortie.find((p) => same(p.at, c));
  if (picked === null) {
    if (here) {
      picked = here.officer;
      audio.play("click");
    }
    return;
  }
  const from = placementOf(picked)?.at;
  if (!from || here?.officer === picked) {
    picked = null;
    audio.play("cancel");
    return;
  }
  const mover = picked;
  story.sortie = story.sortie.map((p) =>
    p.officer === mover
      ? { officer: p.officer, at: { x: c.x, y: c.y } }
      : here && p.officer === here.officer
        ? { officer: p.officer, at: { x: from.x, y: from.y } }
        : p,
  );
  picked = null;
  audio.play("select");
}

function launch() {
  if (error) return;
  audio.play("confirm");
  story.launch();
}

type Mark =
  | { kind: "slot"; index: number; officer: string | null }
  | { kind: "foe"; boss: boolean; name: string }
  | { kind: "guest"; name: string }
  | { kind: "friend"; name: string }
  | { kind: "gate"; name: string };

const marks = $derived.by(() => {
  const m = new Map<string, Mark>();
  if (!battle) return m;
  battle.slots.forEach((c, index) => {
    const p = story.sortie.find((q) => same(q.at, c));
    m.set(`${c.x},${c.y}`, { kind: "slot", index, officer: p?.officer ?? null });
  });
  for (const u of battle.enemies)
    if (u.arrives === undefined)
      m.set(
        `${u.x},${u.y}`,
        u.classId === "gate"
          ? { kind: "gate", name: nameOf(u.officer) }
          : { kind: "foe", boss: bosses.has(u.id ?? u.officer), name: nameOf(u.officer) },
      );
  for (const u of battle.guests)
    if (u.arrives === undefined) m.set(`${u.x},${u.y}`, { kind: "guest", name: nameOf(u.officer) });
  for (const u of battle.allies ?? [])
    if (u.arrives === undefined)
      m.set(`${u.x},${u.y}`, { kind: "friend", name: nameOf(u.officer) });
  return m;
});

function numbers(o: Officer) {
  const cls = P0_RULES.classes[o.classId];
  const p = battleProfile(o);
  return {
    cls: cls?.name ?? o.classId,
    hp: (cls?.hp ?? 0) + p.stats.command + TUNING.hpPerLevel * o.level,
    atk: (cls?.atk ?? 0) + p.stats.martial + p.atkBonus,
    def: (cls?.def ?? 0) + p.stats.command + p.defBonus,
    move: cls?.move ?? 0,
  };
}
</script>

{#snippet weatherIcon(
  w: WeatherKind,
)}
  <svg viewBox="0 0 24 24" class="wx" aria-hidden="true">
    {#if w === "sunny"}
      <circle cx="12" cy="12" r="4.5" fill="currentColor" />
      <g stroke="currentColor" stroke-width="2" stroke-linecap="round">
        <path
          d="M12 2.5v2.5M12 19v2.5M2.5 12H5M19 12h2.5M5.3 5.3l1.8 1.8M16.9 16.9l1.8 1.8M5.3 18.7l1.8-1.8M16.9 7.1l1.8-1.8"
        />
      </g>
    {:else if w === "wind"}
      <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round">
        <path d="M3 9h11a3 3 0 1 0-3-3" />
        <path d="M3 14h15a3 3 0 1 1-3 3" />
        <path d="M3 19h7" />
      </g>
    {:else}
      <path
        d="M7 17h10.5a4 4 0 0 0 .4-8 5.5 5.5 0 0 0-10.6 1.2A3.4 3.4 0 0 0 7 17z"
        fill="currentColor"
      />
      {#if w === "rain"}
        <g stroke="currentColor" stroke-width="2" stroke-linecap="round">
          <path d="M8 20l-1 2.5M12.5 20l-1 2.5M17 20l-1 2.5" />
        </g>
      {/if}
    {/if}
  </svg>
{/snippet}

{#if battle && map}
  <main class="sortie">
    <div class="scroll">
      <div class="wrap">
        <section class="brief">
          <div class="kicker"><span>{L.kicker}</span><span class="dot"></span>{chapter}</div>
          <h1>{battle.name}</h1>
          <div class="chips">
            <span class="chip place">{battle.place}</span>
            <span class="chip">{@render weatherIcon(battle.weather)}{WEATHER[battle.weather]}</span>
            <span class="chip">{L.rounds(battle.roundLimit)}</span>
            <span class="chip foe">{L.foes(foes)}</span>
            {#if later > 0}
              <span class="chip foe soft">{L.reinforce(later)}</span>
            {/if}
          </div>
          <p class="summary">{battle.summary}</p>
          <div class="goals">
            <div class="goal win"><span class="tag">{L.win}</span>{battle.goalText.win}</div>
            <div class="goal lose"><span class="tag">{L.lose}</span>{battle.goalText.lose}</div>
          </div>

          <div class="mapcard">
            <div class="maphead">
              <strong>{L.field} · {battle.map.name}</strong>
              <span class="legend">
                <span><i class="lg mine"></i>{L.mine}</span>
                {#if battle.guests.length > 0}
                  <span><i class="lg guest"></i>{L.guestTroops}</span>
                {/if}
                {#if (battle.allies ?? []).length > 0}
                  <span><i class="lg friend"></i>{L.ally}</span>
                {/if}
                <span><i class="lg foe"></i>{L.foe}</span>
                <span><i class="lg boss"></i>{L.boss}</span>
              </span>
            </div>
            <div
              class="map"
              style:grid-template-columns="repeat({map.width}, minmax(0, 1fr))"
              style:aspect-ratio="{map.width} / {map.height}"
            >
              {#each map.tiles as t, i (i)}
                {@const x = i % map.width}
                {@const y = Math.floor(i / map.width)}
                {@const m = marks.get(`${x},${y}`)}
                {#if m?.kind === "slot"}
                  <button
                    type="button"
                    class="cell slot"
                    class:filled={m.officer !== null}
                    class:picked={m.officer !== null && m.officer === picked}
                    class:target={picked !== null && m.officer !== picked}
                    style:--t={hex(TERRAIN_COLOR[t])}
                    aria-label={m.officer ? nameOf(m.officer) : `${L.slot} ${m.index + 1}`}
                    onclick={() => tapSlot({ x, y })}
                  >
                    {#if m.officer}
                      <span class="token">{nameOf(m.officer).slice(0, 1)}</span>
                    {:else}
                      <span class="free">{m.index + 1}</span>
                    {/if}
                  </button>
                {:else}
                  <div class="cell" style:--t={hex(TERRAIN_COLOR[t])}>
                    {#if m?.kind === "foe"}
                      <span class="pin foe" class:boss={m.boss} title={m.name}></span>
                    {:else if m?.kind === "guest"}
                      <span class="pin guest" title={m.name}></span>
                    {:else if m?.kind === "friend"}
                      <span class="pin friend" title={m.name}></span>
                    {:else if m?.kind === "gate"}
                      <span class="pin gate" title={m.name}></span>
                    {/if}
                  </div>
                {/if}
              {/each}
            </div>
            <p class="hint" class:active={picked !== null}>
              {picked ? L.hintPick(nameOf(picked)) : L.hintMap}
            </p>
          </div>
        </section>

        <section class="roster">
          <div class="rhead">
            <h2>{L.roster}</h2>
            <span class="count"><b>{story.sortie.length}</b> / {cap}</span>
          </div>
          <div class="cards">
            {#each roster as o (o.id)}
              {@const p = placementOf(o.id)}
              {@const n = numbers(o)}
              {@const req = battle.required.includes(o.id)}
              {#key shake?.id === o.id ? shake.n : 0}
                <button
                  type="button"
                  class="card"
                  class:on={p !== undefined}
                  class:shake={shake?.id === o.id}
                  aria-pressed={p !== undefined}
                  onclick={() => toggle(o)}
                >
                  <span class="face">
                    <Portrait id={o.id} classId={o.classId} side={0} size={48} />
                  </span>
                  <span class="body">
                    <span class="nm">
                      <strong>{nameOf(o.id)}</strong>
                      {#if req}
                        <span class="badge req">{L.required}</span>
                      {/if}
                      {#if o.guest}
                        <span class="badge guest">{L.guest}</span>
                      {/if}
                    </span>
                    <span class="sub">{n.cls} · Lv {o.level}</span>
                    <span class="nums">
                      <span><i>{L.hp}</i>{n.hp}</span>
                      <span><i>{L.atk}</i>{n.atk}</span>
                      <span><i>{L.def}</i>{n.def}</span>
                      <span><i>{L.move}</i>{n.move}</span>
                    </span>
                  </span>
                  <span class="mark" aria-hidden="true">
                    {#if p}
                      {slotIndex(p.at) + 1}
                    {/if}
                  </span>
                </button>
              {/key}
            {/each}
          </div>
        </section>
      </div>
    </div>

    <footer>
      <div class="why" class:bad={notice !== null || error !== null}>
        {notice ?? error ?? L.ready(story.sortie.length)}
      </div>
      <button type="button" class="primary go" disabled={error !== null} onclick={launch}>
        <span>{L.go}</span>
      </button>
    </footer>
  </main>
{/if}

<style>
.sortie {
  position: fixed;
  inset: 0;
  display: flex;
  flex-direction: column;
  background:
    radial-gradient(120% 55% at 50% -12%, rgba(232, 194, 106, 0.2), transparent 62%),
    radial-gradient(70% 45% at 100% 100%, rgba(224, 100, 90, 0.1), transparent 60%),
    linear-gradient(180deg, #1d1a13, #0e0d0a);
}
.scroll {
  flex: 1;
  min-height: 0;
  overflow-x: hidden;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  padding: max(14px, env(safe-area-inset-top, 0px)) max(16px, env(safe-area-inset-right, 0px)) 18px
    max(16px, env(safe-area-inset-left, 0px));
}
.wrap {
  width: min(1180px, 100%);
  margin: 0 auto;
  display: grid;
  gap: 18px;
}
@media (min-width: 900px) {
  .wrap {
    grid-template-columns: minmax(0, 1.1fr) minmax(0, 1fr);
    align-items: start;
    gap: 22px;
  }
  .roster {
    position: sticky;
    top: 0;
  }
}
.brief {
  display: grid;
  gap: 12px;
  min-width: 0;
}
.kicker {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 12px;
  letter-spacing: 0.12em;
  color: var(--accent);
}
.kicker span:first-child {
  font-weight: 800;
}
.dot {
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background: var(--gold-line);
}
h1 {
  margin: -2px 0 0;
  font-family: var(--display);
  font-size: clamp(30px, 8vw, 44px);
  font-weight: 900;
  letter-spacing: 0.06em;
  line-height: 1.1;
  background: linear-gradient(180deg, #fff6d6, #e8c26a 62%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 2px 8px rgba(0, 0, 0, 0.6));
}
.chips {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}
.chips .chip {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  padding: 3px 10px;
  font-size: 12px;
  background: rgba(0, 0, 0, 0.3);
}
.chip.place {
  border-color: var(--gold-line);
  color: #f3dfae;
}
.chip.foe {
  border-color: rgba(224, 100, 90, 0.5);
  color: #ffb3aa;
}
.chip.soft {
  border-style: dashed;
}
.wx {
  width: 14px;
  height: 14px;
  color: #ffd98a;
}
.summary {
  margin: 0;
  font-size: 14px;
  line-height: 1.6;
  color: #e3dccd;
}
.goals {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 8px;
}
.goal {
  display: grid;
  gap: 3px;
  padding: 10px 12px;
  border-radius: 12px;
  font-size: 13px;
  font-weight: 700;
  background: var(--panel);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid var(--line);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05);
}
.goal .tag {
  font-size: 11px;
  font-weight: 800;
  letter-spacing: 0.14em;
}
.goal.win {
  border-color: rgba(232, 194, 106, 0.45);
  background: linear-gradient(135deg, rgba(232, 194, 106, 0.16), rgba(20, 18, 14, 0.8) 70%);
}
.goal.win .tag {
  color: var(--accent);
}
.goal.lose {
  border-color: rgba(224, 100, 90, 0.4);
  background: linear-gradient(135deg, rgba(224, 100, 90, 0.14), rgba(20, 18, 14, 0.8) 70%);
}
.goal.lose .tag {
  color: var(--enemy);
}
.mapcard {
  display: grid;
  gap: 8px;
  padding: 10px;
  border-radius: 14px;
  background: var(--panel);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid var(--gold-line);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.35);
}
.maphead {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  gap: 4px 10px;
  font-size: 13px;
}
.legend {
  display: flex;
  gap: 10px;
  font-size: 11px;
  color: var(--muted);
}
.legend span {
  display: inline-flex;
  align-items: center;
  gap: 4px;
}
.lg {
  display: inline-block;
  width: 9px;
  height: 9px;
  border-radius: 50%;
}
.lg.mine {
  background: var(--ally);
  box-shadow: 0 0 0 1px #fff8;
}
.lg.guest {
  background: #ffa898;
}
.lg.friend {
  background: var(--friend);
}
.lg.foe {
  background: var(--enemy);
}
.lg.boss {
  background: var(--enemy);
  box-shadow: 0 0 0 2px var(--accent);
}
.map {
  display: grid;
  width: 100%;
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid rgba(0, 0, 0, 0.6);
  box-shadow: inset 0 0 18px rgba(0, 0, 0, 0.45);
}
.cell {
  position: relative;
  display: grid;
  place-items: center;
  min-width: 0;
  aspect-ratio: 1;
  background-color: var(--t);
  background-image: linear-gradient(135deg, rgba(255, 255, 255, 0.1), rgba(0, 0, 0, 0.12));
  box-shadow: inset 0 0 0 0.5px rgba(0, 0, 0, 0.18);
}
button.cell {
  padding: 0;
  margin: 0;
  border: none;
  border-radius: 0;
  font-size: inherit;
  transform: none;
  transition: none;
}
.slot {
  box-shadow:
    inset 0 0 0 1.5px rgba(255, 226, 150, 0.85),
    inset 0 0 8px rgba(232, 194, 106, 0.55);
}
.slot:hover:not(:disabled) {
  box-shadow:
    inset 0 0 0 2px #fff1c2,
    inset 0 0 10px rgba(232, 194, 106, 0.8);
}
.slot.target:not(.filled) {
  animation: blink 1s ease-in-out infinite;
}
.free {
  font-size: clamp(8px, 1.6vw, 12px);
  font-weight: 800;
  color: rgba(255, 244, 210, 0.85);
  text-shadow: 0 1px 2px #000;
}
.token {
  width: 84%;
  aspect-ratio: 1;
  display: grid;
  place-items: center;
  border-radius: 50%;
  font-size: clamp(9px, 1.9vw, 14px);
  font-weight: 800;
  color: #fff;
  background: radial-gradient(circle at 35% 30%, #ffa59c, var(--ally) 55%, #8c2a22);
  box-shadow:
    0 0 0 1.5px rgba(255, 255, 255, 0.85),
    0 2px 4px rgba(0, 0, 0, 0.6);
}
.picked .token {
  box-shadow:
    0 0 0 2px var(--accent),
    0 0 10px 2px rgba(232, 194, 106, 0.9);
  animation: bob 0.8s ease-in-out infinite;
}
.pin {
  width: 58%;
  aspect-ratio: 1;
  border-radius: 50%;
  box-shadow:
    0 0 0 1px rgba(0, 0, 0, 0.55),
    0 1px 3px rgba(0, 0, 0, 0.6);
}
.pin.foe {
  background: radial-gradient(circle at 35% 30%, #8cb8ff, var(--enemy) 55%, #23498f);
}
.pin.foe.boss {
  width: 74%;
  box-shadow:
    0 0 0 2px var(--accent),
    0 0 8px rgba(232, 194, 106, 0.9);
}
.pin.guest {
  background: radial-gradient(circle at 35% 30%, #ffe0d8, #ffa898 55%, #b8584a);
}
.pin.gate {
  border-radius: 2px;
  background: linear-gradient(180deg, #8a857a, #4a463e);
}
.pin.friend {
  background: radial-gradient(circle at 35% 30%, #ffe2b0, var(--friend) 55%, #8a4e12);
}
.hint {
  margin: 0;
  font-size: 12px;
  color: var(--muted);
}
.hint.active {
  color: var(--accent);
  font-weight: 700;
}
.roster {
  display: grid;
  gap: 10px;
  min-width: 0;
}
.rhead {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}
h2 {
  margin: 0;
  font-family: var(--display);
  font-size: 18px;
  letter-spacing: 0.08em;
}
.count {
  font-size: 13px;
  color: var(--muted);
  font-variant-numeric: tabular-nums;
}
.count b {
  font-size: 20px;
  color: var(--accent);
}
.cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(156px, 1fr));
  gap: 8px;
}
.card {
  position: relative;
  display: flex;
  gap: 8px;
  align-items: center;
  min-height: 72px;
  padding: 8px 10px 8px 8px;
  text-align: left;
  border-radius: 14px;
  background: linear-gradient(160deg, rgba(58, 53, 42, 0.92), rgba(26, 24, 19, 0.94));
  border: 1px solid rgba(255, 255, 255, 0.1);
  overflow: hidden;
}
.card::after {
  content: "";
  position: absolute;
  inset: 0;
  border-radius: inherit;
  pointer-events: none;
  opacity: 0;
  transition: opacity 0.2s;
  background: linear-gradient(135deg, rgba(224, 100, 90, 0.3), transparent 60%);
}
.card.on {
  border-color: rgba(232, 194, 106, 0.85);
  box-shadow:
    inset 0 1px 0 rgba(255, 255, 255, 0.15),
    0 0 0 1px rgba(232, 194, 106, 0.35),
    0 4px 16px rgba(232, 194, 106, 0.18);
}
.card.on::after {
  opacity: 1;
}
.card.shake {
  animation: shake 0.35s;
}
.face {
  flex: none;
  width: 48px;
  height: 60px;
  border-radius: 10px;
  overflow: hidden;
  background: #0006;
  box-shadow:
    0 0 0 1px rgba(232, 194, 106, 0.45),
    0 2px 6px rgba(0, 0, 0, 0.5);
}
.face :global(img),
.face :global(canvas) {
  display: block;
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 0;
}
.body {
  display: grid;
  gap: 2px;
  min-width: 0;
  z-index: 1;
}
.card:not(.on) .face,
.card:not(.on) .body {
  opacity: 0.62;
}
.nm {
  display: flex;
  align-items: center;
  gap: 4px;
  flex-wrap: wrap;
}
.nm strong {
  font-family: var(--display);
  font-size: 16px;
  letter-spacing: 0.04em;
}
.sub {
  font-size: 12px;
  color: var(--muted);
}
.nums {
  display: flex;
  flex-wrap: wrap;
  gap: 0 7px;
  font-size: 11px;
  font-variant-numeric: tabular-nums;
}
.nums i {
  font-style: normal;
  color: var(--muted);
  margin-right: 2px;
}
.badge {
  padding: 0 6px;
  border-radius: 999px;
  font-size: 10px;
  font-weight: 800;
  line-height: 16px;
}
.badge.req {
  color: #2a1c06;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
}
.badge.guest {
  color: #d6ebff;
  border: 1px solid rgba(143, 196, 255, 0.6);
}
.mark {
  position: absolute;
  top: 6px;
  right: 6px;
  width: 22px;
  height: 22px;
  display: grid;
  place-items: center;
  border-radius: 50%;
  font-size: 12px;
  font-weight: 800;
  color: #2a1c06;
  z-index: 1;
}
.on .mark {
  background: linear-gradient(180deg, #f6dc94, #c9963e);
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5);
}
.card:not(.on) .mark {
  border: 1.5px dashed rgba(255, 255, 255, 0.25);
}
footer {
  flex: none;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px max(16px, env(safe-area-inset-right, 0px))
    max(10px, env(safe-area-inset-bottom, 0px)) calc(64px + env(safe-area-inset-left, 0px));
  background: linear-gradient(180deg, rgba(14, 12, 9, 0.7), rgba(14, 12, 9, 0.95));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-top: 1px solid var(--gold-line);
  box-shadow: 0 -8px 24px rgba(0, 0, 0, 0.4);
}
.why {
  flex: 1;
  min-width: 0;
  font-size: 13px;
  color: var(--muted);
}
.why.bad {
  color: var(--bad);
}
.go {
  flex: none;
  min-width: 148px;
  min-height: 52px;
  padding: 10px 26px;
  border-radius: 14px;
  font-family: var(--display);
  font-size: 22px;
  letter-spacing: 0.3em;
  text-indent: 0.3em;
}
.go:not(:disabled) {
  animation: glow 2.4s ease-in-out infinite;
}
@keyframes glow {
  50% {
    box-shadow:
      inset 0 1px 0 rgba(255, 255, 255, 0.55),
      0 0 22px rgba(232, 194, 106, 0.55);
  }
}
@keyframes bob {
  50% {
    transform: translateY(-2px);
  }
}
@keyframes blink {
  50% {
    filter: brightness(1.35);
  }
}
@keyframes shake {
  20%,
  60% {
    transform: translateX(-4px);
  }
  40%,
  80% {
    transform: translateX(4px);
  }
}
@media (prefers-reduced-motion: reduce) {
  .go:not(:disabled),
  .picked .token,
  .slot.target:not(.filled),
  .card.shake {
    animation: none;
  }
}
</style>
