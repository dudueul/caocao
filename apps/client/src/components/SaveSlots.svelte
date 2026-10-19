<script lang="ts">
import { audio } from "../lib/audio/index.ts";
import {
  deleteSlot,
  listSaves,
  type SaveMeta,
  SLOTS,
  type SlotId,
  slotName,
} from "../lib/saves.ts";
import { story } from "../lib/story.svelte.ts";

/** The autosave and three manual slots: save into one, or load / delete one. */
let { mode, onload }: { mode: "save" | "load"; onload?: (slot: SlotId) => void } = $props();

const L = {
  empty: "비어 있음",
  autoOnly: "자동 저장 전용",
  current: "현재",
  tapSave: "눌러서 저장",
  tapLoad: "눌러서 불러오기",
  overwrite: "이 슬롯에 덮어쓸까요?",
  save: "저장",
  saved: "저장했습니다",
  failed: "저장하지 못했습니다",
  remove: "삭제",
  removeAsk: "이 기록을 지울까요?",
  cancel: "취소",
  gold: "군자금",
} as const;

let saves = $state<SaveMeta[]>(listSaves());
/** The slot waiting for an overwrite or delete confirmation. */
let asking = $state<{ slot: SlotId; what: "overwrite" | "delete" } | null>(null);
let done = $state<{ slot: SlotId; ok: boolean } | null>(null);
let doneTimer: ReturnType<typeof setTimeout> | undefined;

const metaOf = (slot: SlotId) => saves.find((m) => m.slot === slot) ?? null;
const refresh = () => (saves = listSaves());

function when(ms: number): string {
  try {
    return new Date(ms).toLocaleString("ko-KR", { dateStyle: "medium", timeStyle: "short" });
  } catch {
    return "";
  }
}

function save(slot: SlotId) {
  const ok = story.save(slot);
  audio.play(ok ? "confirm" : "cancel");
  asking = null;
  refresh();
  done = { slot, ok };
  clearTimeout(doneTimer);
  doneTimer = setTimeout(() => (done = null), 1800);
}

function tap(slot: SlotId) {
  const meta = metaOf(slot);
  if (mode === "load") {
    if (!meta) return;
    audio.play("confirm");
    onload?.(slot);
    return;
  }
  if (slot === "auto") return;
  if (meta) {
    asking = { slot, what: "overwrite" };
    audio.play("select");
  } else save(slot);
}

function askDelete(slot: SlotId) {
  asking = { slot, what: "delete" };
  audio.play("select");
}

function remove(slot: SlotId) {
  deleteSlot(slot);
  audio.play("cancel");
  asking = null;
  refresh();
}

function cancel() {
  asking = null;
  audio.play("cancel");
}
</script>

<div class="slots">
  {#each SLOTS as slot (slot)}
    {@const meta = metaOf(slot)}
    {@const locked = mode === "save" ? slot === "auto" : meta === null}
    <div
      class="slot"
      class:filled={meta !== null}
      class:auto={slot === "auto"}
      class:current={meta !== null && story.slot === slot}
    >
      {#if asking?.slot === slot}
        <div class="ask">
          <span>{asking.what === "overwrite" ? L.overwrite : L.removeAsk}</span>
          <div class="askBtns">
            <button type="button" onclick={cancel}>{L.cancel}</button>
            {#if asking.what === "overwrite"}
              <button type="button" class="primary" onclick={() => save(slot)}>{L.save}</button>
            {:else}
              <button type="button" class="danger" onclick={() => remove(slot)}>{L.remove}</button>
            {/if}
          </div>
        </div>
      {:else}
        <button type="button" class="main" disabled={locked} onclick={() => tap(slot)}>
          <span class="badge">{slot === "auto" ? "A" : slot}</span>
          <span class="text">
            <span class="head">
              <strong>{slotName(slot)}</strong>
              {#if meta !== null && story.slot === slot}
                <span class="tag">{L.current}</span>
              {/if}
              {#if done?.slot === slot}
                <span class="tag" class:ok={done.ok} class:fail={!done.ok}>
                  {done.ok ? L.saved : L.failed}
                </span>
              {/if}
            </span>
            {#if meta}
              <span class="where">{meta.chapter} · {meta.where}</span>
              <span class="info">
                <span>{meta.lord}</span>
                <span>{L.gold} {meta.gold.toLocaleString("ko-KR")}</span>
              </span>
              <span class="date">{when(meta.savedAt)}</span>
            {:else}
              <span class="where muted">{L.empty}</span>
            {/if}
            {#if mode === "save" && slot === "auto"}
              <span class="date">{L.autoOnly}</span>
            {/if}
          </span>
          {#if !locked}
            <span class="act">
              <svg viewBox="0 0 24 24" aria-hidden="true">
                {#if mode === "save"}
                  <path
                    d="M12 4v10M7.5 10l4.5 4.5 4.5-4.5M5 19h14"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2.4"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  />
                {:else}
                  <path d="M9 6.5v11l8.5-5.5z" fill="currentColor" />
                {/if}
              </svg>
            </span>
          {/if}
        </button>
        {#if mode === "load" && meta}
          <button
            type="button"
            class="del"
            aria-label="{slotName(slot)} {L.remove}"
            onclick={() => askDelete(slot)}
          >
            ✕
          </button>
        {/if}
      {/if}
    </div>
  {/each}
</div>

<style>
.slots {
  display: grid;
  gap: 8px;
}
.slot {
  position: relative;
  display: flex;
  align-items: stretch;
  gap: 6px;
  min-height: 76px;
  border-radius: 16px;
  background: rgba(0, 0, 0, 0.28);
  border: 1px dashed rgba(255, 255, 255, 0.14);
}
.slot.filled {
  border-style: solid;
  border-color: var(--line);
  background: linear-gradient(160deg, rgba(52, 47, 37, 0.92), rgba(22, 20, 16, 0.94));
}
.slot.current {
  border-color: rgba(232, 194, 106, 0.7);
  box-shadow: 0 0 14px rgba(232, 194, 106, 0.15);
}
.main {
  flex: 1;
  min-width: 0;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px 12px;
  text-align: left;
  border: none;
  border-radius: 16px;
  background: transparent;
  box-shadow: none;
}
.main:hover:not(:disabled) {
  box-shadow: inset 0 0 0 1px var(--accent);
}
.main:disabled {
  opacity: 1;
}
.slot:not(.filled) .main:disabled {
  opacity: 0.55;
}
.badge {
  flex: none;
  width: 42px;
  height: 42px;
  display: grid;
  place-items: center;
  border-radius: 12px;
  font-family: var(--display);
  font-size: 20px;
  font-weight: 900;
  color: var(--accent);
  background: radial-gradient(circle at 40% 30%, rgba(232, 194, 106, 0.22), rgba(0, 0, 0, 0.45));
  border: 1px solid var(--gold-line);
}
.auto .badge {
  color: #9fd0ff;
  border-color: rgba(143, 196, 255, 0.4);
  background: radial-gradient(circle at 40% 30%, rgba(79, 142, 240, 0.22), rgba(0, 0, 0, 0.45));
}
.text {
  flex: 1;
  min-width: 0;
  display: grid;
  gap: 1px;
}
.head {
  display: flex;
  align-items: center;
  gap: 6px;
  flex-wrap: wrap;
}
.head strong {
  font-size: 14px;
}
.tag {
  padding: 0 7px;
  border-radius: 999px;
  font-size: 10px;
  font-weight: 800;
  line-height: 17px;
  color: #2a1c06;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
}
.tag.ok {
  color: #0d2a10;
  background: var(--good);
}
.tag.fail {
  color: #2a0d0a;
  background: var(--bad);
}
.where {
  font-weight: 700;
  font-size: 13px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.info {
  display: flex;
  flex-wrap: wrap;
  gap: 0 10px;
  font-size: 12px;
  color: #d5cdbd;
}
.date {
  font-size: 11px;
  color: var(--muted);
  font-variant-numeric: tabular-nums;
}
.act {
  flex: none;
  width: 32px;
  height: 32px;
  display: grid;
  place-items: center;
  border-radius: 50%;
  font-weight: 900;
  color: #22180a;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.4);
}
.act svg {
  width: 18px;
  height: 18px;
}
.del {
  flex: none;
  align-self: center;
  width: 44px;
  height: 44px;
  margin-right: 8px;
  padding: 0;
  border-radius: 12px;
  color: var(--bad);
}
.ask {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 8px 12px;
  padding: 10px 12px;
  font-weight: 700;
}
.askBtns {
  display: flex;
  gap: 6px;
}
.askBtns button {
  min-width: 72px;
  min-height: 44px;
  border-radius: 12px;
}
.danger {
  color: #fff;
  background: linear-gradient(180deg, #e0645a, #a8382f);
  border-color: #ff9f95;
}
</style>
