<script lang="ts">
import { ui } from "../lib/ui.svelte.ts";

/** Who made this, and where the history comes from. */
function close() {
  ui.credits = false;
}
</script>

<svelte:window onkeydown={(e) => e.key === "Escape" && close()} />

<button type="button" class="scrim" aria-label="닫기" onclick={close}></button>
<div class="panel" role="dialog" aria-modal="true" aria-labelledby="credits-title">
  <h2 id="credits-title">만든 이</h2>
  <p>기획·감독 — 프로젝트 오너</p>
  <p>
    코드·그림·음악 — 모두 이 프로젝트에서 직접 만들었습니다. 캐릭터와 초상화, 전장, 음악과 효과음은
    코드로 그려지고 합성됩니다.
  </p>
  <p>
    역사 — 『삼국지』(진수)와 『후한서』의 기록을 바탕으로, 대사와 장면은 새로 썼습니다. 일부 일화는
    『삼국지연의』에서 빌렸습니다.
  </p>
  <p class="muted">
    조조전과 그 리메이크에 대한 애정에서 시작했지만, 그 게임들의 데이터나 그림은 쓰지 않습니다.
  </p>
  <button type="button" class="primary" onclick={close}>닫기</button>
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
  display: grid;
  gap: 10px;
  padding: 18px 20px;
  background: linear-gradient(180deg, rgba(30, 26, 18, 0.97), rgba(14, 12, 9, 0.97));
  border: 1px solid rgba(232, 194, 106, 0.55);
  border-radius: 16px;
  z-index: 41;
  line-height: 1.6;
  word-break: keep-all;
}
h2 {
  margin: 0;
  font-family: var(--display);
  color: var(--accent);
  letter-spacing: 0.15em;
}
p {
  margin: 0;
  font-size: 14px;
}
</style>
