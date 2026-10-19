<script lang="ts">
import { audio } from "../lib/audio/index.ts";
import { settings, type TextSpeed } from "../lib/settings.svelte.ts";
import { ui } from "../lib/ui.svelte.ts";

/** Settings (ADR 0054): sound, battle speed, effects, text speed, hints. Saved as they change. */
const SPEEDS = [
  [1, "1×"],
  [2, "2×"],
  [4, "4×"],
  [0, "생략"],
] as const;
const TEXT: [TextSpeed, string][] = [
  ["slow", "느리게"],
  ["normal", "보통"],
  ["fast", "빠르게"],
  ["instant", "즉시"],
];

function close() {
  ui.settings = false;
}
</script>

<svelte:window onkeydown={(e) => e.key === "Escape" && close()} />

<button type="button" class="scrim" aria-label="닫기" onclick={close}></button>
<div class="panel" role="dialog" aria-modal="true" aria-labelledby="settings-title">
  <header>
    <h2 id="settings-title">설정</h2>
    <button type="button" onclick={close} aria-label="닫기">✕</button>
  </header>

  <section>
    <h3>소리</h3>
    {#each [
      ["master", "전체"],
      ["music", "음악"],
      ["sfx", "효과음"],
    ] as [key, label] (key)}
      <label class="slider">
        <span>{label}</span>
        <input
          type="range"
          min="0"
          max="100"
          step="5"
          bind:value={settings[key as "master" | "music" | "sfx"]}
          onchange={() => audio.play("select")}
        >
        <output>{settings[key as "master" | "music" | "sfx"]}</output>
      </label>
    {/each}
  </section>

  <section>
    <h3>전투</h3>
    <div class="row">
      <span>애니메이션 속도</span>
      <div class="seg">
        {#each SPEEDS as [v, label] (v)}
          <button
            type="button"
            class:on={settings.speed === v}
            onclick={() => (settings.speed = v)}
          >
            {label}
          </button>
        {/each}
      </div>
    </div>
    <label class="row">
      <span>시작할 때 위험 범위 표시</span>
      <input type="checkbox" bind:checked={settings.danger}>
    </label>
    <label class="row">
      <span>가벼운 효과 (느린 기기)</span>
      <input type="checkbox" bind:checked={settings.lite}>
    </label>
    <label class="row">
      <span>첫 전투 도움말</span>
      <input type="checkbox" bind:checked={settings.hints}>
    </label>
  </section>

  <section>
    <h3>대화</h3>
    <div class="row">
      <span>글자 속도</span>
      <div class="seg">
        {#each TEXT as [v, label] (v)}
          <button
            type="button"
            class:on={settings.textSpeed === v}
            onclick={() => (settings.textSpeed = v)}
          >
            {label}
          </button>
        {/each}
      </div>
    </div>
  </section>
  <p class="muted note">가벼운 효과는 다음 전투부터 적용됩니다.</p>
</div>

<style>
.scrim {
  position: fixed;
  inset: 0;
  width: 100%;
  border: none;
  border-radius: 0;
  box-shadow: none;
  padding: 0;
  background: rgba(0, 0, 0, 0.55);
  z-index: 40;
}
.panel {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: min(460px, calc(100% - 24px));
  max-height: calc(100% - 32px);
  overflow-y: auto;
  display: grid;
  gap: 14px;
  padding: 16px 18px;
  background: linear-gradient(180deg, rgba(30, 26, 18, 0.97), rgba(14, 12, 9, 0.97));
  border: 1px solid rgba(232, 194, 106, 0.55);
  border-radius: 16px;
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.6);
  z-index: 41;
}
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
h2 {
  margin: 0;
  font-family: var(--display);
  letter-spacing: 0.15em;
  color: var(--accent);
}
h3 {
  margin: 0 0 6px;
  font-size: 13px;
  color: var(--muted);
  font-weight: 600;
}
section {
  display: grid;
  gap: 8px;
}
.slider {
  display: grid;
  grid-template-columns: 56px 1fr 34px;
  align-items: center;
  gap: 10px;
}
.slider input {
  width: 100%;
  accent-color: var(--accent);
}
output {
  text-align: right;
  font-variant-numeric: tabular-nums;
  color: var(--muted);
}
.row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  min-height: 36px;
}
.row input[type="checkbox"] {
  width: 22px;
  height: 22px;
  accent-color: var(--accent);
}
.seg {
  display: flex;
  gap: 4px;
}
.seg button {
  padding: 5px 9px;
  font-size: 12px;
}
.seg .on {
  border-color: var(--accent);
  color: var(--accent);
}
.note {
  margin: 0;
  font-size: 12px;
}
</style>
