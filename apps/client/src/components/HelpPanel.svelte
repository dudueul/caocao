<script lang="ts">
import { P0_RULES } from "@tk/content";
import type { TerrainId } from "@tk/rules";
import { ui } from "../lib/ui.svelte.ts";

/** How to play, in short: controls, the counter triangle, 일기토, terrain and skills. */
function close() {
  ui.help = false;
}

const TERRAIN_NOTES: [TerrainId, string][] = [
  ["forest", "엄폐가 높아 화살과 공격을 피하기 쉽습니다. 기병은 느려집니다."],
  ["hills", "궁병이 강해집니다. 엄폐가 있습니다."],
  ["village", "매 라운드 체력을 회복합니다."],
  ["fort", "가장 단단한 자리. 회복과 큰 엄폐."],
  ["camp", "조금 회복하고 조금 숨을 수 있습니다."],
  ["ford", "건널 수 있지만 느리고 약해집니다."],
];
</script>

<svelte:window onkeydown={(e) => e.key === "Escape" && close()} />

<button type="button" class="scrim" aria-label="닫기" onclick={close}></button>
<div class="panel" role="dialog" aria-modal="true" aria-labelledby="help-title">
  <header>
    <h2 id="help-title">도움말</h2>
    <button type="button" onclick={close} aria-label="닫기">✕</button>
  </header>

  <section>
    <h3>조작</h3>
    <ul>
      <li>장수를 누르고, 이동할 칸을 누른 뒤, 떠 있는 메뉴에서 공격·책략·대기를 고릅니다.</li>
      <li>
        행동을 확정하기 전까지 이동은 몇 번이든 바꿀 수 있습니다. <kbd>Esc</kbd>는 한 단계 뒤로.
      </li>
      <li>대상을 한 번 누르면 예상 피해와 명중률, 반격이 보이고, 한 번 더 누르면 실행합니다.</li>
      <li>
        <kbd>E</kbd>
        턴 종료 · <kbd>D</kbd> 적의 공격 범위 · 적을 누르면 그 적이 닿는 칸이 보입니다.
      </li>
    </ul>
  </section>

  <section>
    <h3>상성</h3>
    <p class="triangle">
      <span>보병</span>
      ▶ <span>기병</span> ▶ <span>궁병·포차</span> ▶ <span>보병</span>
    </p>
    <p class="muted">
      유리한 상대에게 피해 +20%, 불리한 상대에게 −20%. 책사와 무도가는 상성 밖입니다.
    </p>
  </section>

  <section>
    <h3>일기토</h3>
    <ul>
      <li>
        이름난 장수가 적장 곁에 서면 메뉴에 일기토가 뜹니다. 상대가 응하지 않으면 행동이 끝나고,
        상대 진영이 약화됩니다.
      </li>
      <li>
        합마다 자세를 고릅니다: <kbd>1</kbd> 공격 · <kbd>2</kbd> 강공 · <kbd>3</kbd> 방어 ·
        <kbd>Esc</kbd>
        물러나기. 다섯 합 안에 쓰러뜨리면 이깁니다.
      </li>
    </ul>
    <p class="triangle">
      <span>강공</span>
      ▶ <span>공격</span> ▶ <span>방어</span> ▶ <span>강공</span>
    </p>
    <p class="muted">
      이긴 쪽 주위는 고무되고 진 쪽 주위는 약화됩니다. ★ 역사의 일기토는 역사대로 끝납니다.
    </p>
  </section>

  <section>
    <h3>지형</h3>
    <ul class="terrain">
      {#each TERRAIN_NOTES as [id, note] (id)}
        <li><strong>{P0_RULES.terrains[id].name}</strong> {note}</li>
      {/each}
    </ul>
  </section>

  <section>
    <h3>책략과 상태</h3>
    <ul>
      <li>
        화계는 풀과 숲을 태우고 바람에 강해지며 비에 약해집니다. 수계는 물가에서, 낙석은 산과
        언덕에서 강합니다.
      </li>
      <li>
        버프(▲)와 디버프(▼)는 겹쳐 걸리고, 겹칠수록 효과가 줄어듭니다. 남은 턴은 장수 정보에
        나옵니다.
      </li>
      <li>운이 높으면 치명타가 잦고, 책략에 천운(효과 증가)이 따르기도 합니다.</li>
    </ul>
  </section>

  <section>
    <h3>이야기</h3>
    <ul>
      <li>
        전투마다 승리 조건이 있습니다. 적장(★)을 쓰러뜨리면 다른 적이 남아도 이깁니다. ◆는 지켜야 할
        장수입니다.
      </li>
      <li>
        공격을 맞히고 적을 쓰러뜨리면 경험치를 얻습니다. 20레벨부터 군영에서 승급할 수 있습니다.
      </li>
      <li>군영에서 장비를 사고 바꾸며, 저장할 수 있습니다. 전투에서 이기면 자동 저장됩니다.</li>
    </ul>
  </section>
</div>

<style>
.scrim {
  position: fixed;
  inset: 0;
  width: 100%;
  padding: 0;
  border: none;
  border-radius: 0;
  box-shadow: none;
  background: rgba(0, 0, 0, 0.55);
  z-index: 40;
}
.panel {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: min(560px, calc(100% - 24px));
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
  line-height: 1.6;
  word-break: keep-all;
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
  margin: 0 0 4px;
  font-size: 14px;
  color: var(--accent);
}
section p,
ul {
  margin: 0;
  font-size: 13px;
}
ul {
  padding-left: 18px;
}
.terrain {
  list-style: none;
  padding: 0;
}
.triangle {
  font-size: 15px;
  font-weight: 700;
}
.triangle span {
  padding: 1px 8px;
  border: 1px solid var(--gold-line);
  border-radius: 999px;
}
kbd {
  padding: 0 5px;
  font-size: 11px;
  border: 1px solid var(--line);
  border-radius: 4px;
  background: rgba(255, 255, 255, 0.06);
}
</style>
