<script lang="ts">
import type { Session } from "../lib/session.svelte.ts";
import { settings } from "../lib/settings.svelte.ts";

/**
 * First-battle tips (ADR 0054): one short hint for what the player is doing
 * right now. Each tip shows once; "팁 끄기" turns them off in the settings.
 */
let { session, top }: { session: Session; top: number } = $props();

const seen = $state<Record<string, boolean>>({});

interface Tip {
  id: string;
  text: string;
}

const tip = $derived.by<Tip | null>(() => {
  const s = session;
  if (!settings.hints || !s.myTurn) return null;
  const acted = s.state.units.some((u) => u.side === s.humanSide && u.acted);
  const options: Tip[] = [];
  if (s.state.round === 1 && !s.selected)
    options.push({
      id: "goal",
      text: "왼쪽 위의 승리 조건을 확인하세요. 적장을 쓰러뜨리면 다른 적이 남아도 이깁니다.",
    });
  if (!s.selected) options.push({ id: "select", text: "아군 장수를 눌러 선택하세요." });
  if (s.selected && s.unitCommands.length > 0 && !s.to)
    options.push({
      id: "move",
      text: "밝은 칸이 이동할 수 있는 곳, 붉은 칸이 공격할 수 있는 적입니다. 칸을 눌러 이동하세요.",
    });
  if (s.to && !s.aim)
    options.push({
      id: "act",
      text: "행동을 고르세요. 행동을 확정하기 전에는 이동을 몇 번이든 취소할 수 있습니다.",
    });
  if (s.aim?.kind === "attack")
    options.push({
      id: "forecast",
      text: "적을 누르면 예상 피해와 명중률, 반격이 나옵니다. 한 번 더 누르면 공격합니다.",
    });
  if (acted)
    options.push({
      id: "end",
      text: "남은 장수를 쉬게 하려면 '턴 종료'(E)를. '위험 범위'(D)는 적이 닿는 칸을 보여 줍니다.",
    });
  if (s.state.units.some((u) => u.side === s.humanSide && u.hp > 0 && u.hp * 2 < u.maxHp))
    options.push({
      id: "heal",
      text: "다친 장수는 마을·진영·성채에서 매 라운드 회복합니다. 책사의 치료도 있습니다.",
    });
  return options.find((t) => !seen[t.id]) ?? null;
});
</script>

{#if tip}
  <div class="coach" style:top="{top + 70}px" role="status">
    <span class="badge">팁</span>
    <p>{tip.text}</p>
    <div class="row">
      <button type="button" onclick={() => (seen[tip.id] = true)}>알겠습니다</button>
      <button type="button" class="quiet" onclick={() => (settings.hints = false)}>팁 끄기</button>
    </div>
  </div>
{/if}

<style>
.coach {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  width: min(440px, calc(100% - 24px));
  display: grid;
  gap: 8px;
  padding: 12px 14px;
  background: linear-gradient(180deg, rgba(36, 30, 18, 0.94), rgba(18, 15, 10, 0.94));
  border: 1px solid rgba(232, 194, 106, 0.7);
  border-radius: 14px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
  z-index: 6;
  animation: pop 0.25s ease-out;
}
.badge {
  justify-self: start;
  padding: 1px 10px;
  font-size: 11px;
  font-weight: 800;
  color: #22180a;
  background: var(--accent);
  border-radius: 999px;
}
p {
  margin: 0;
  font-size: 14px;
  line-height: 1.55;
  word-break: keep-all;
}
.row {
  display: flex;
  gap: 8px;
  justify-content: flex-end;
}
.quiet {
  background: transparent;
  box-shadow: none;
  color: var(--muted);
}
@keyframes pop {
  from {
    opacity: 0;
    transform: translate(-50%, -6px);
  }
}
@media (prefers-reduced-motion: reduce) {
  .coach {
    animation: none;
  }
}
</style>
