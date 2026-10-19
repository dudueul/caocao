<script lang="ts">
import { CLASSES, OFFICERS } from "@tk/content";
import Backdrop from "../../components/Backdrop.svelte";
import Portrait from "../../components/Portrait.svelte";
import { story } from "../../lib/story.svelte.ts";

/** The end of the chapter: who fought, how far they grew, and what comes next. */
const roster = $derived(story.campaign.roster);
</script>

<main class="ending">
  <Backdrop kind="dawn" />
  <div class="roll">
    <p class="kicker">제1장</p>
    <h1>관도 · 완</h1>
    <p class="lead">
      다섯 번의 싸움 끝에 하북의 대군이 무너졌다. 조조는 이 싸움으로 북방의 패권을 손에 넣는다.
    </p>
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
    <p class="muted">제2장은 준비 중입니다. 전과는 자동 저장에 남아 있습니다.</p>
    <a class="button primary" href="#/">타이틀로</a>
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
