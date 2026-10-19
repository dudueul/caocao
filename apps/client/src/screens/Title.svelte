<script lang="ts">
import { CHAPTER_LIST } from "@tk/content";
import Backdrop from "../components/Backdrop.svelte";
import SaveSlots from "../components/SaveSlots.svelte";
import { audio } from "../lib/audio/index.ts";
import { latestSave, readSlot, type SlotId } from "../lib/saves.ts";
import { story } from "../lib/story.svelte.ts";
import { ui } from "../lib/ui.svelte.ts";

/** The main menu (ADR 0054): Story, Arena, practice battles, settings. The game's name is ADR 0058. */
const hasServer = import.meta.env.VITE_NO_SERVER !== "1";
let latest = $state(latestSave());
let loading = $state(false);
let choosing = $state(false);
let confirmNew = $state(false);
/** A chapter picked from the list, waiting for a second tap when a save exists. */
let confirmChapter = $state<string | null>(null);
let note = $state("");

$effect(() => {
  audio.music("title");
});

function go(hash: string) {
  audio.play("confirm");
  location.hash = hash;
}

function newGame() {
  if (latest && !confirmNew) {
    confirmNew = true;
    return;
  }
  story.start();
  go("#/story");
}

/** Starts a chapter fresh, with its own roster (ADR 0056). */
function startChapter(id: string) {
  if (latest && confirmChapter !== id) {
    confirmChapter = id;
    return;
  }
  story.start(id);
  go("#/story");
}

function load(slot: SlotId) {
  const s = readSlot(slot);
  if (!s) {
    note = "저장을 읽을 수 없습니다";
    latest = latestSave();
    return;
  }
  story.load(s, slot === "auto" ? null : slot);
  go("#/story");
}
</script>

<main class="title">
  <Backdrop kind="dawn" calm />
  <div class="brand">
    <span class="hanja" aria-hidden="true">亂世奸雄</span>
    <h1>난세의 간웅</h1>
    <p class="sub">조조의 이야기 · 삼국 전술</p>
  </div>

  {#if loading}
    <section class="menu wide">
      <SaveSlots mode="load" onload={load} />
      <button type="button" onclick={() => (loading = false)}>뒤로</button>
    </section>
  {:else if choosing}
    <section class="menu wide" aria-label="장 선택">
      {#each CHAPTER_LIST as ch (ch.id)}
        <button type="button" class="chapter" onclick={() => startChapter(ch.id)}>
          <span class="no">{ch.name}</span>
          <span class="name">{ch.title}</span>
          <span class="years">{ch.years}</span>
          <small>
            {confirmChapter === ch.id ? "자동 저장을 덮어씁니다. 한 번 더 누르세요" : ch.summary}
          </small>
        </button>
      {/each}
      <p class="note quiet">장을 고르면 그 장의 기본 장수들로 새로 시작합니다.</p>
      <button type="button" onclick={() => (choosing = false)}>뒤로</button>
    </section>
  {:else}
    <nav class="menu" aria-label="메인 메뉴">
      {#if latest}
        <button type="button" class="primary big" onclick={() => load(latest?.slot ?? "auto")}>
          이어하기
          <small>{latest.chapter} · {latest.where}</small>
        </button>
      {/if}
      <button type="button" class:primary={!latest} class="big" onclick={newGame}>
        {confirmNew ? "정말 처음부터? (한 번 더 누르세요)" : "새로 시작"}
      </button>
      <div class="row">
        {#if latest}
          <button type="button" onclick={() => (loading = true)}>불러오기</button>
        {/if}
        <button type="button" onclick={() => (choosing = true)}>장 선택</button>
      </div>
      {#if hasServer}
        <button type="button" onclick={() => go("#/arena")}>아레나 · 친구와 대전</button>
      {/if}
      <div class="row">
        <button type="button" onclick={() => go("#/sandbox")}>연습 전투</button>
        <button type="button" onclick={() => go("#/demo")}>관전</button>
      </div>
      <div class="row">
        <button type="button" onclick={() => (ui.help = true)}>도움말</button>
        <button type="button" onclick={() => (ui.settings = true)}>설정</button>
      </div>
      <button type="button" class="small" onclick={() => (ui.credits = true)}>만든 이</button>
      {#if note}
        <p class="note">{note}</p>
      {/if}
    </nav>
  {/if}
  <footer>개발 중</footer>
</main>

<style>
.title {
  position: fixed;
  inset: 0;
  display: grid;
  grid-template-rows: 1fr auto auto;
  justify-items: center;
  align-items: center;
  overflow-y: auto;
  padding: max(24px, env(safe-area-inset-top, 0px)) 16px max(16px, env(safe-area-inset-bottom, 0px));
}
.brand {
  position: relative;
  display: grid;
  justify-items: center;
  gap: 4px;
  text-align: center;
  animation: rise 1.2s cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.hanja {
  font-family: var(--display);
  font-size: clamp(14px, 2.6vw, 20px);
  letter-spacing: 0.7em;
  margin-right: -0.7em;
  color: rgba(255, 236, 200, 0.75);
}
h1 {
  margin: 0;
  font-family: var(--display);
  font-size: clamp(40px, 11vw, 100px);
  font-weight: 900;
  letter-spacing: 0.12em;
  margin-right: -0.12em;
  white-space: nowrap;
  line-height: 1.05;
  background: linear-gradient(180deg, #fff6d8 10%, #e8c26a 55%, #9a6a22 95%);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 6px 18px rgba(0, 0, 0, 0.75));
}
.sub {
  margin: 0;
  font-size: 14px;
  letter-spacing: 0.3em;
  color: #f2e4c4;
  text-shadow: 0 1px 6px #000;
}
.menu {
  position: relative;
  display: grid;
  gap: 10px;
  width: min(340px, 100%);
  margin-bottom: 18px;
  animation: rise 1.2s 0.3s cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.menu.wide {
  width: min(520px, 100%);
}
.menu button {
  min-height: 46px;
  font-size: 15px;
  background: linear-gradient(180deg, rgba(40, 34, 24, 0.86), rgba(18, 15, 10, 0.86));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-color: rgba(232, 194, 106, 0.35);
}
.menu button.primary {
  background: linear-gradient(180deg, #f6dc94, #d7a84e 55%, #b4852f);
  color: #22180a;
}
.menu .big {
  min-height: 56px;
  font-size: 18px;
  font-weight: 800;
  display: grid;
  justify-items: center;
  align-content: center;
}
.menu small {
  font-size: 11px;
  font-weight: 600;
  opacity: 0.8;
}
.row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
}
.menu .small {
  min-height: 36px;
  font-size: 13px;
}
.chapter {
  display: grid;
  grid-template-columns: auto 1fr auto;
  align-items: baseline;
  gap: 2px 10px;
  min-height: 64px;
  padding: 10px 14px;
  text-align: left;
}
.chapter .no {
  font-size: 12px;
  color: var(--accent);
}
.chapter .name {
  font-family: var(--display);
  font-size: 18px;
  font-weight: 800;
}
.chapter .years {
  font-size: 12px;
  color: var(--muted);
  font-variant-numeric: tabular-nums;
}
.chapter small {
  grid-column: 1 / -1;
  font-size: 12px;
  line-height: 1.5;
  color: var(--muted);
  word-break: keep-all;
}
.note.quiet {
  color: var(--muted);
}
.note {
  margin: 0;
  text-align: center;
  color: var(--bad);
  font-size: 13px;
}
footer {
  position: relative;
  font-size: 11px;
  color: rgba(255, 240, 210, 0.6);
}
@keyframes rise {
  from {
    opacity: 0;
    transform: translateY(14px);
  }
}
@media (prefers-reduced-motion: reduce) {
  .brand,
  .menu {
    animation: none;
  }
}
</style>
