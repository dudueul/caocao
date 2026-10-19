<script lang="ts">
import { chapterOf } from "@tk/campaign";
import { CLASSES, OFFICERS } from "@tk/content";
import Backdrop from "../../components/Backdrop.svelte";
import Portrait from "../../components/Portrait.svelte";
import { story } from "../../lib/story.svelte.ts";

/** The end of a chapter: who fought, how far they grew, and the chapter that follows (ADR 0056). */
const chapter = $derived(chapterOf(story.campaign));
const roster = $derived(story.campaign.roster);
const next = $derived(story.upcoming);
/** Chapters between this one and the next that are not written yet. */
const skipped = $derived(next ? next.number - chapter.number - 1 : 0);
</script>

<main class="ending">
  <Backdrop kind="dawn" />
  <div class="roll">
    <p class="kicker">{chapter.name}</p>
    <h1>{chapter.ending.title}</h1>
    <p class="lead">{chapter.ending.lead}</p>
    <ul class="roster">
      {#each roster as o (o.id)}
        <li>
          <Portrait id={o.id} classId={o.classId} side={0} size={52} mood="smile" />
          <span class="name">{OFFICERS[o.id]?.name ?? o.id}</span>
          <span class="muted">{CLASSES[o.classId]?.name ?? o.classId} · Lv {o.level}</span>
          <span class="muted small">출전 {o.battles} · 격파 {o.defeated}</span>
        </li>
      {/each}
    </ul>
    {#if next}
      {#if skipped > 0}
        <p class="muted">
          {chapter.number + 1}장은 준비 중입니다. 지금의 장수들을 이끌고 {next.name}
          {next.title}로 이어 갈 수 있습니다.
        </p>
      {:else}
        <p class="muted">장수와 장비, 군자금을 그대로 이끌고 다음 장으로 갑니다.</p>
      {/if}
      <button type="button" class="primary next" onclick={() => story.continueNext()}>
        {next.name} {next.title}로
        <small>{next.years} · Lv {next.floor} 미만인 장수는 Lv {next.floor}로 오릅니다</small>
      </button>
      <a class="button" href="#/">타이틀로</a>
    {:else}
      <p class="muted">다음 장은 준비 중입니다. 전과는 자동 저장에 남아 있습니다.</p>
      <a class="button primary" href="#/">타이틀로</a>
    {/if}
  </div>
</main>

<style>
.ending {
  position: fixed;
  inset: 0;
  overflow-y: auto;
}
.roll {
  position: relative;
  display: grid;
  justify-items: center;
  gap: 14px;
  max-width: 760px;
  margin: 0 auto;
  padding: 48px 16px 64px;
  text-align: center;
  animation: up 1.4s cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.kicker {
  margin: 0;
  letter-spacing: 0.5em;
  color: #f2e4c4;
}
h1 {
  margin: 0;
  font-family: var(--display);
  font-size: clamp(40px, 9vw, 72px);
  letter-spacing: 0.2em;
  background: linear-gradient(180deg, #fff6d8, #e8c26a 60%, #9a6a22);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 4px 12px rgba(0, 0, 0, 0.7));
}
.lead {
  margin: 0;
  max-width: 520px;
  line-height: 1.7;
  word-break: keep-all;
  text-shadow: 0 1px 4px #000;
}
.roster {
  list-style: none;
  margin: 8px 0;
  padding: 0;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 10px;
  width: 100%;
}
.roster li {
  display: grid;
  justify-items: center;
  gap: 2px;
  padding: 10px 6px;
  background: rgba(14, 12, 9, 0.72);
  border: 1px solid var(--gold-line);
  border-radius: 12px;
}
.name {
  font-weight: 800;
}
.next {
  display: grid;
  justify-items: center;
  gap: 2px;
  min-height: 56px;
  padding: 8px 24px;
  font-size: 17px;
}
.next small {
  font-size: 11px;
  font-weight: 600;
  opacity: 0.8;
}
.small {
  font-size: 11px;
}
@keyframes up {
  from {
    opacity: 0;
    transform: translateY(24px);
  }
}
@media (prefers-reduced-motion: reduce) {
  .roll {
    animation: none;
  }
}
</style>
