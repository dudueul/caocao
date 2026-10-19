<script lang="ts">
import {
  battleProfile,
  chapterOf,
  type Officer,
  PROGRESSION,
  promotionFor,
  sellPrice,
} from "@tk/campaign";
import { fits, ITEMS, type ItemDef, OFFICERS, P0_RULES, PROMOTIONS, type Slot } from "@tk/content";
import { type ClassDef, forMode } from "@tk/rules";
import { onDestroy } from "svelte";
import Portrait from "../../components/Portrait.svelte";
import SaveSlots from "../../components/SaveSlots.svelte";
import { audio } from "../../lib/audio/index.ts";
import { story } from "../../lib/story.svelte.ts";
import { STATS } from "../../lib/text.ts";

/** 군영 between battles: the roster and its gear, the shop, and saving. */
const L = {
  camp: "군영",
  tabs: { roster: "장수", shop: "상점", save: "저장" },
  go: "출진 준비",
  guest: "객장",
  tier: (n: number) => `${n}차 병과`,
  exp: "EXP",
  battles: (n: number) => `출전 ${n}`,
  defeated: (n: number) => `격파 ${n}`,
  promote: "승급",
  promoteReady: "승급 가능",
  promoteAt: (lv: number, name: string) => `Lv ${lv}에 ${name}(으)로 승급`,
  combat: "전투 능력",
  stats: "능력치",
  gear: "장비",
  none: "없음",
  unequip: "해제",
  noFit: "맞는 예비 장비가 없습니다. 상점에서 구할 수 있습니다.",
  fitsHint: "예비 장비",
  hp: "HP",
  mp: "MP",
  atk: "공격",
  def: "방어",
  move: "이동",
  buy: "구입",
  sell: "판매",
  sellTitle: "예비 장비 판매",
  nothingToSell: "팔 수 있는 예비 장비가 없습니다.",
  owned: (n: number) => `보유 ${n}`,
  users: "사용",
  nobody: "쓸 수 있는 장수 없음",
  short: "군자금 부족",
  bought: (name: string) => `${name} 구입`,
  sold: (name: string, g: number) => `${name} 판매 +${g}`,
  equipped: (name: string) => `${name} 장착`,
  removed: (name: string) => `${name} 해제`,
  all: "전체",
  promoTitle: "승급",
  promoClass: "병과 기본 능력",
  promoSkills: "새 책략",
  promoConfirm: "승급한다",
  cancel: "취소",
  promoted: (name: string) => `${name}(으)로 승급!`,
} as const;

const SLOT_NAME: Record<Slot, string> = { weapon: "무기", armour: "갑옷", accessory: "장신구" };
const SLOTS: readonly Slot[] = ["weapon", "armour", "accessory"];
const TUNING = forMode(P0_RULES, "story").tuning;
const PER = PROGRESSION.expPerLevel;
const reduce =
  typeof matchMedia === "function" && matchMedia("(prefers-reduced-motion: reduce)").matches;

type Tab = "roster" | "shop" | "save";
const TABS: readonly Tab[] = ["roster", "shop", "save"];
let tab = $state<Tab>("roster");
let selected = $state<string | null>(null);
let openSlot = $state<Slot | null>(null);
let promoting = $state(false);
let flash = $state<string | null>(null);
let shopFilter = $state<Slot | null>(null);
let toast = $state<{ text: string; n: number } | null>(null);
let toastTimer: ReturnType<typeof setTimeout> | undefined;
let flashTimer: ReturnType<typeof setTimeout> | undefined;
onDestroy(() => {
  clearTimeout(toastTimer);
  clearTimeout(flashTimer);
});

const campaign = $derived(story.campaign);
const roster = $derived(campaign.roster);
const officer = $derived(roster.find((o) => o.id === selected) ?? roster[0] ?? null);
const chapter = $derived.by(() => {
  const ch = chapterOf(campaign);
  return `${ch.name} ${ch.title}`;
});

const nameOf = (id: string) => OFFICERS[id]?.name ?? id;
const classOf = (id: string): ClassDef | undefined => P0_RULES.classes[id];

function say(text: string) {
  toast = { text, n: (toast?.n ?? 0) + 1 };
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => (toast = null), 1600);
}

function pick(t: Tab) {
  if (t === tab) return;
  tab = t;
  audio.play("click");
}

function choose(o: Officer) {
  selected = o.id;
  openSlot = null;
  audio.play("select");
}

/** Numbers an item adds, as short labels. */
function nums(item: ItemDef): string[] {
  const out: string[] = [];
  if (item.atk) out.push(`${L.atk} +${item.atk}`);
  if (item.def) out.push(`${L.def} +${item.def}`);
  for (const [k, label] of STATS) {
    const v = item.stats?.[k];
    if (v) out.push(`${label} +${v}`);
  }
  return out;
}

/** What swapping `cur` for `next` changes, per number. */
function delta(cur: ItemDef | undefined, next: ItemDef): { label: string; d: number }[] {
  const out: { label: string; d: number }[] = [];
  const add = (label: string, a: number | undefined, b: number | undefined) => {
    const d = (b ?? 0) - (a ?? 0);
    if (d !== 0) out.push({ label, d });
  };
  add(L.atk, cur?.atk, next.atk);
  add(L.def, cur?.def, next.def);
  for (const [k, label] of STATS) add(label, cur?.stats?.[k], next.stats?.[k]);
  return out;
}

const detail = $derived.by(() => {
  const o = officer;
  if (!o) return null;
  const cls = classOf(o.classId);
  const prof = battleProfile(o);
  const atkBase = (cls?.atk ?? 0) + o.stats.martial;
  const defBase = (cls?.def ?? 0) + o.stats.command;
  const atk = (cls?.atk ?? 0) + prof.stats.martial + prof.atkBonus;
  const def = (cls?.def ?? 0) + prof.stats.command + prof.defBonus;
  const promo = promotionFor(o);
  const next = PROMOTIONS[o.classId];
  return {
    o,
    cls,
    prof,
    hp: (cls?.hp ?? 0) + prof.stats.command + TUNING.hpPerLevel * o.level,
    mp: (cls?.mp ?? 0) + Math.floor((prof.stats.intellect * TUNING.mpPerIntellect) / 1000),
    atk,
    atkGear: atk - atkBase,
    def,
    defGear: def - defBase,
    promo,
    promoClass: promo ? classOf(promo) : undefined,
    later: !promo && next ? classOf(next) : undefined,
  };
});

/** Spare items that fit the open slot of the shown officer. */
const options = $derived.by(() => {
  const o = officer;
  const slot = openSlot;
  if (!o || !slot) return [];
  return Object.entries(campaign.inventory)
    .filter(([id, n]) => n > 0 && ITEMS[id]?.slot === slot && fits(P0_RULES, o.classId, id))
    .map(([id, n]) => ({ item: ITEMS[id] as ItemDef, n }));
});

function toggleSlot(slot: Slot) {
  openSlot = openSlot === slot ? null : slot;
  audio.play("click");
}

function doEquip(o: Officer, item: ItemDef) {
  if (story.equip(o.id, item.id)) {
    audio.play("equip");
    say(L.equipped(item.name));
    openSlot = null;
  }
}

function doUnequip(o: Officer, slot: Slot) {
  const id = o.gear[slot];
  if (id && story.unequip(o.id, slot)) {
    audio.play("equip");
    say(L.removed(ITEMS[id]?.name ?? id));
  }
}

function confirmPromote() {
  const o = officer;
  const to = detail?.promoClass;
  if (!o || !to) return;
  if (story.promote(o.id)) {
    audio.play("promote");
    promoting = false;
    flash = o.id;
    say(L.promoted(to.name));
    clearTimeout(flashTimer);
    flashTimer = setTimeout(() => (flash = null), 1800);
  }
}

function openPromote() {
  promoting = true;
  audio.play("select");
}

function closePromote() {
  promoting = false;
  audio.play("cancel");
}

// ------------------------------------------------------------ shop

const shop = $derived(
  (story.camp?.shop ?? [])
    .map((id) => ITEMS[id])
    .filter((it): it is ItemDef => it !== undefined && (!shopFilter || it.slot === shopFilter)),
);

const spares = $derived(
  Object.entries(campaign.inventory)
    .filter(([id, n]) => n > 0 && ITEMS[id])
    .map(([id, n]) => ({ item: ITEMS[id] as ItemDef, n }))
    .sort(
      (a, b) =>
        SLOTS.indexOf(a.item.slot) - SLOTS.indexOf(b.item.slot) || a.item.price - b.item.price,
    ),
);

/** Roster officers who can use an item: "전체", their names, or null for nobody. */
function users(item: ItemDef): string | null {
  const names = roster.filter((o) => fits(P0_RULES, o.classId, item.id)).map((o) => nameOf(o.id));
  if (names.length === 0) return null;
  return names.length === roster.length ? L.all : names.join(" · ");
}

/** Brings an opened picker into view. */
function reveal(node: HTMLElement) {
  node.scrollIntoView({ block: "nearest", behavior: reduce ? "auto" : "smooth" });
}

function buy(item: ItemDef) {
  if (story.buy(item.id)) {
    audio.play("coin");
    say(L.bought(item.name));
  } else audio.play("cancel");
}

function sell(item: ItemDef) {
  const g = sellPrice(item.id);
  if (story.sell(item.id)) {
    audio.play("coin");
    say(L.sold(item.name, g));
  }
}

function leave() {
  audio.play("confirm");
  story.leaveCamp();
}

function classRows(a: ClassDef, b: ClassDef) {
  return [
    { label: L.hp, a: a.hp, b: b.hp },
    { label: L.mp, a: a.mp, b: b.mp },
    { label: L.move, a: a.move, b: b.move },
    { label: L.atk, a: a.atk, b: b.atk },
    { label: L.def, a: a.def, b: b.def },
  ];
}

function newSkills(a: ClassDef, b: ClassDef): string[] {
  return b.stratagems
    .filter((s) => !a.stratagems.includes(s))
    .map((s) => P0_RULES.stratagems[s]?.name ?? s);
}

const signed = (d: number) => (d > 0 ? `+${d}` : `${d}`);
</script>

{#snippet coinIcon()}
  <svg viewBox="0 0 24 24" class="coin" aria-hidden="true">
    <circle cx="12" cy="12" r="10" fill="url(#camp-coin)" stroke="#7a5418" stroke-width="1.2" />
    <circle cx="12" cy="12" r="7.6" fill="none" stroke="#a87724" stroke-width="0.8" />
    <rect x="9" y="9" width="6" height="6" fill="#3a2508" stroke="#7a5418" stroke-width="0.8" />
  </svg>
{/snippet}

{#snippet slotIcon(
  slot: Slot,
  kind?: string,
)}
  <svg viewBox="0 0 24 24" class="sicon" aria-hidden="true">
    {#if slot === "weapon" && kind === "polearm"}
      <path d="M4 20L17 7" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
      <path d="M15.5 4.5L21 3l-1.5 5.5-2.5.5-1-1z" fill="currentColor" />
      <path d="M13 8l3 3" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
    {:else if slot === "weapon" && kind === "bow"}
      <path
        d="M8 3c7 2 10 6 10 9s-3 7-10 9"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
      />
      <path d="M8 3v18" stroke="currentColor" stroke-width="1.2" />
      <path d="M3 12h15M15 9.5l3 2.5-3 2.5" fill="none" stroke="currentColor" stroke-width="1.8" />
    {:else if slot === "weapon" && kind === "fan"}
      <path d="M12 21L3.5 9.5a12 12 0 0 1 17 0z" fill="currentColor" opacity="0.85" />
      <path
        d="M12 21L7 7.5M12 21V6.5M12 21l5-13.5"
        stroke="#1a140c"
        stroke-width="1.2"
        opacity="0.6"
      />
    {:else if slot === "weapon" && kind === "shot"}
      <circle cx="9" cy="14" r="5" fill="currentColor" />
      <circle cx="17" cy="8" r="3.5" fill="currentColor" opacity="0.7" />
    {:else if slot === "weapon"}
      <path d="M19 3l2 2-11 11-2-2z" fill="currentColor" />
      <path
        d="M6 13l5 5M4.5 17.5l2 2M7 16l-3.5 3.5"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
      />
    {:else if slot === "armour"}
      <path
        d="M12 3l7 3v5c0 5-3 8.5-7 10-4-1.5-7-5-7-10V6z"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linejoin="round"
      />
      <path d="M8 9h8M8 12.5h8M9 16h6" stroke="currentColor" stroke-width="1.6" />
    {:else}
      <path d="M12 3v4" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
      <circle cx="12" cy="13" r="6" fill="none" stroke="currentColor" stroke-width="2" />
      <circle cx="12" cy="13" r="2" fill="currentColor" />
    {/if}
  </svg>
{/snippet}

<svg width="0" height="0" class="defs" aria-hidden="true">
  <defs>
    <radialGradient id="camp-coin" cx="0.35" cy="0.3" r="0.8">
      <stop offset="0" stop-color="#fff0b8" />
      <stop offset="0.55" stop-color="#e8c26a" />
      <stop offset="1" stop-color="#a8792a" />
    </radialGradient>
  </defs>
</svg>

<main class="camp">
  <header class="top">
    <div class="titles">
      <div class="kicker">{chapter}</div>
      <h1>{L.camp}</h1>
    </div>
    <div class="gold">
      {@render coinIcon()}
      {#key campaign.gold}
        <b class="pulse">{campaign.gold.toLocaleString("ko-KR")}</b>
      {/key}
    </div>
  </header>
  <nav class="tabs">
    {#each TABS as t (t)}
      <button type="button" class:on={tab === t} aria-pressed={tab === t} onclick={() => pick(t)}>
        {L.tabs[t]}
      </button>
    {/each}
  </nav>

  <div class="scroll">
    {#if tab === "roster"}
      <div class="rosterTab">
        <div class="list">
          {#each roster as o (o.id)}
            {@const c = classOf(o.classId)}
            <button
              type="button"
              class="who"
              class:on={officer?.id === o.id}
              aria-pressed={officer?.id === o.id}
              onclick={() => choose(o)}
            >
              <span class="face">
                <Portrait id={o.id} classId={o.classId} side={0} size={44} />
              </span>
              <span class="wtext">
                <span class="wname">{nameOf(o.id)}</span>
                <span class="wsub">{c?.name} · Lv {o.level}</span>
              </span>
              {#if promotionFor(o)}
                <span class="ready" title={L.promoteReady}>▲</span>
              {/if}
              {#if o.guest}
                <span class="gtag">{L.guest}</span>
              {/if}
            </button>
          {/each}
        </div>

        {#if detail}
          {@const o = detail.o}
          <section class="detail" class:flash={flash === o.id}>
            <div class="hero">
              <div class="bigface">
                <Portrait
                  id={o.id}
                  classId={o.classId}
                  side={0}
                  size={112}
                  mood={flash === o.id ? "smile" : "calm"}
                />
              </div>
              <div class="heroText">
                <div class="nameRow">
                  <h2>{nameOf(o.id)}</h2>
                  <span class="hanja">{OFFICERS[o.id]?.hanja}</span>
                </div>
                <div class="tags">
                  <span class="chip cls">{detail.cls?.name}</span>
                  <span class="chip">{L.tier(detail.cls?.tier ?? 1)}</span>
                  {#if o.guest}
                    <span class="chip guest">{L.guest}</span>
                  {/if}
                </div>
                <div class="lvline">
                  <span class="lv">Lv <b>{o.level}</b></span>
                  <div class="bar">
                    <div class="fill" style:width="{(100 * o.exp) / PER}%"></div>
                  </div>
                  <span class="expn">{o.exp}/{PER}</span>
                </div>
                <div class="record">
                  <span>{L.battles(o.battles)}</span><span>{L.defeated(o.defeated)}</span>
                </div>
                {#if detail.promoClass}
                  <button type="button" class="primary promoteBtn" onclick={openPromote}>
                    ▲ {L.promote} · {detail.promoClass.name}
                  </button>
                {:else if detail.later}
                  <div class="later">
                    {L.promoteAt(PROGRESSION.promoteLevel, detail.later.name)}
                  </div>
                {/if}
              </div>
            </div>

            <div class="combat">
              <div><span>{L.hp}</span><b>{detail.hp}</b></div>
              {#if (detail.cls?.mp ?? 0) > 0}
                <div><span>{L.mp}</span><b>{detail.mp}</b></div>
              {/if}
              <div>
                <span>{L.atk}</span>
                <b
                  >{detail.atk}
                  {#if detail.atkGear > 0}
                    <em>+{detail.atkGear}</em>
                  {/if}</b
                >
              </div>
              <div>
                <span>{L.def}</span>
                <b
                  >{detail.def}
                  {#if detail.defGear > 0}
                    <em>+{detail.defGear}</em>
                  {/if}</b
                >
              </div>
              <div><span>{L.move}</span><b>{detail.cls?.move}</b></div>
            </div>

            <div class="cols">
              <div class="block">
                <h3>{L.stats}</h3>
                <div class="stats">
                  {#each STATS as [k, label] (k)}
                    {@const base = o.stats[k]}
                    {@const bonus = detail.prof.stats[k] - base}
                    <div class="stat">
                      <span class="sl">{label}</span>
                      <div class="sbar">
                        <div class="sb base" style:width="{base}%"></div>
                        <div class="sb plus" style:width="{bonus}%"></div>
                      </div>
                      <span class="sv"
                        >{base}
                        {#if bonus > 0}
                          <em>+{bonus}</em>
                        {/if}</span
                      >
                    </div>
                  {/each}
                </div>
              </div>

              <div class="block gearBlock">
                <h3>{L.gear}</h3>
                <div class="gear">
                  {#each SLOTS as slot (slot)}
                    {@const id = o.gear[slot]}
                    {@const it = id ? ITEMS[id] : undefined}
                    <button
                      type="button"
                      class="gslot"
                      class:open={openSlot === slot}
                      class:empty={!it}
                      aria-expanded={openSlot === slot}
                      onclick={() => toggleSlot(slot)}
                    >
                      <span class="gicon">{@render slotIcon(slot, it?.kind)}</span>
                      <span class="gtext">
                        <span class="gname">
                          <small>{SLOT_NAME[slot]}</small>
                          {it ? it.name : L.none}
                        </span>
                        {#if it}
                          <span class="gnums">{nums(it).join(" · ")}</span>
                        {/if}
                      </span>
                      <span class="chev" aria-hidden="true">{openSlot === slot ? "▴" : "▾"}</span>
                    </button>
                    {#if openSlot === slot}
                      <div class="picker" {@attach reveal}>
                        {#if it}
                          <button type="button" class="unequip" onclick={() => doUnequip(o, slot)}>
                            {L.unequip}: {it.name}
                          </button>
                        {/if}
                        {#each options as opt (opt.item.id)}
                          <button type="button" class="opt" onclick={() => doEquip(o, opt.item)}>
                            <span class="oname">
                              {opt.item.name}
                              <small>×{opt.n}</small>
                            </span>
                            <span class="odelta">
                              {#each delta(it, opt.item) as d (d.label)}
                                <span class:good={d.d > 0} class:bad={d.d < 0}>
                                  {d.label}
                                  {signed(d.d)}
                                </span>
                              {/each}
                            </span>
                          </button>
                        {:else}
                          <p class="muted nofit">{L.noFit}</p>
                        {/each}
                      </div>
                    {/if}
                  {/each}
                </div>
              </div>
            </div>
          </section>
        {/if}
      </div>
    {:else if tab === "shop"}
      <div class="shopTab">
        <div class="filters">
          <button type="button" class:on={shopFilter === null} onclick={() => (shopFilter = null)}>
            {L.all}
          </button>
          {#each SLOTS as s (s)}
            <button type="button" class:on={shopFilter === s} onclick={() => (shopFilter = s)}>
              {SLOT_NAME[s]}
            </button>
          {/each}
        </div>
        <div class="wares">
          {#each shop as it (it.id)}
            {@const owned = campaign.inventory[it.id] ?? 0}
            {@const short = campaign.gold < it.price}
            {@const who = users(it)}
            <div class="ware">
              <span class="wicon {it.slot}">{@render slotIcon(it.slot, it.kind)}</span>
              <div class="winfo">
                <div class="wtitle">
                  <strong>{it.name}</strong>
                  <span class="hanja">{it.hanja}</span>
                  {#if owned > 0}
                    <span class="owned">{L.owned(owned)}</span>
                  {/if}
                </div>
                <div class="wnums">
                  {#each nums(it) as n (n)}
                    <span>{n}</span>
                  {/each}
                </div>
                <p class="wdesc">{it.text}</p>
                {#if who}
                  <div class="wusers"><span>{L.users}</span> {who}</div>
                {:else}
                  <div class="wusers none">{L.nobody}</div>
                {/if}
              </div>
              <button
                type="button"
                class="buy"
                class:short
                disabled={short}
                aria-label="{it.name} {L.buy} {it.price}"
                onclick={() => buy(it)}
              >
                <span class="bprice">{@render coinIcon()}{it.price.toLocaleString("ko-KR")}</span>
                <span class="blabel">{short ? L.short : L.buy}</span>
              </button>
            </div>
          {/each}
        </div>

        <h3 class="selltitle">{L.sellTitle}</h3>
        <div class="spares">
          {#each spares as s (s.item.id)}
            <div class="spare">
              <span class="wicon small {s.item.slot}"
                >{@render slotIcon(s.item.slot, s.item.kind)}</span
              >
              <span class="sname">
                <strong>{s.item.name}</strong>
                <small>×{s.n}</small>
                <span class="snums">{nums(s.item).join(" · ")}</span>
              </span>
              <button type="button" class="sellbtn" onclick={() => sell(s.item)}>
                {@render coinIcon()}+{sellPrice(s.item.id).toLocaleString("ko-KR")}
                <span>{L.sell}</span>
              </button>
            </div>
          {:else}
            <p class="muted">{L.nothingToSell}</p>
          {/each}
        </div>
      </div>
    {:else}
      <div class="saveTab"><SaveSlots mode="save" /></div>
    {/if}
  </div>

  {#if toast}
    {#key toast.n}
      <div class="toast" role="status">{toast.text}</div>
    {/key}
  {/if}

  <footer>
    <button type="button" class="primary go" onclick={leave}>{L.go}</button>
  </footer>

  {#if promoting && detail?.cls && detail.promoClass}
    {@const from = detail.cls}
    {@const to = detail.promoClass}
    <button type="button" class="scrim" aria-label={L.cancel} onclick={closePromote}></button>
    <div class="sheet" role="dialog" aria-modal="true" aria-labelledby="promo-title">
      <div class="phead1">
        <span class="face">
          <Portrait id={detail.o.id} classId={to.id} side={0} size={44} mood="smile" />
        </span>
        <h3 id="promo-title">{L.promoTitle} · {nameOf(detail.o.id)}</h3>
      </div>
      <div class="ptrack">
        <div class="pcls">
          <span class="pname">{from.name}</span>
          <small>{L.tier(from.tier)}</small>
        </div>
        <span class="parrow" aria-hidden="true">➜</span>
        <div class="pcls to">
          <span class="pname">{to.name}</span>
          <small>{L.tier(to.tier)}</small>
        </div>
      </div>
      <div class="ptable">
        <div class="phead">{L.promoClass}</div>
        {#each classRows(from, to) as r (r.label)}
          {#if r.a > 0 || r.b > 0}
            <div class="prow">
              <span class="pl">{r.label}</span>
              <span class="pa">{r.a}</span>
              <span class="pto" aria-hidden="true">→</span>
              <span class="pb">{r.b}</span>
              <span class="pd" class:good={r.b > r.a}>{r.b !== r.a ? signed(r.b - r.a) : ""}</span>
            </div>
          {/if}
        {/each}
      </div>
      {#if newSkills(from, to).length > 0}
        <div class="pskills">
          <span>{L.promoSkills}</span>
          {#each newSkills(from, to) as s (s)}
            <span class="chip skill">{s}</span>
          {/each}
        </div>
      {/if}
      <div class="pactions">
        <button type="button" onclick={closePromote}>{L.cancel}</button>
        <button type="button" class="primary" onclick={confirmPromote}>{L.promoConfirm}</button>
      </div>
    </div>
  {/if}
</main>

<style>
.camp {
  position: fixed;
  inset: 0;
  display: flex;
  flex-direction: column;
  background:
    radial-gradient(110% 45% at 50% -8%, rgba(232, 194, 106, 0.16), transparent 62%),
    radial-gradient(60% 40% at 0% 100%, rgba(79, 142, 240, 0.08), transparent 60%),
    linear-gradient(180deg, #1c1912, #0e0d0a);
}
.defs {
  position: absolute;
}
.top {
  flex: none;
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 12px;
  padding: max(12px, env(safe-area-inset-top, 0px)) max(16px, env(safe-area-inset-right, 0px)) 8px
    max(16px, env(safe-area-inset-left, 0px));
}
.kicker {
  font-size: 12px;
  letter-spacing: 0.14em;
  color: var(--accent);
}
h1 {
  margin: 0;
  font-family: var(--display);
  font-size: 30px;
  font-weight: 900;
  letter-spacing: 0.12em;
  line-height: 1.15;
  background: linear-gradient(180deg, #fff6d6, #e8c26a 62%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}
.gold {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 6px 14px 6px 8px;
  border-radius: 999px;
  background: rgba(0, 0, 0, 0.45);
  border: 1px solid var(--gold-line);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.06);
}
.gold b {
  font-size: 18px;
  font-weight: 900;
  color: #ffe7a3;
  font-variant-numeric: tabular-nums;
}
.pulse {
  animation: pulse 0.5s;
}
.coin {
  width: 20px;
  height: 20px;
  flex: none;
  filter: drop-shadow(0 1px 2px rgba(0, 0, 0, 0.6));
}
.tabs {
  flex: none;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 4px;
  margin: 0 max(16px, env(safe-area-inset-right, 0px)) 10px
    max(16px, env(safe-area-inset-left, 0px));
  padding: 4px;
  border-radius: 14px;
  background: rgba(0, 0, 0, 0.4);
  border: 1px solid var(--line);
}
.tabs button {
  min-height: 40px;
  border: 1px solid transparent;
  background: transparent;
  box-shadow: none;
  border-radius: 10px;
  font-weight: 700;
  color: var(--muted);
}
.tabs button.on {
  color: #22180a;
  background: linear-gradient(180deg, #f6dc94, #d7a84e 55%, #b4852f);
  border-color: #f8e3a8;
  box-shadow: 0 2px 8px rgba(232, 194, 106, 0.25);
}
@media (min-width: 900px) {
  .tabs {
    width: 420px;
    margin-left: max(16px, calc((100% - 1180px) / 2));
  }
  .top {
    padding-left: max(16px, calc((100% - 1180px) / 2));
    padding-right: max(16px, calc((100% - 1180px) / 2));
  }
}
.scroll {
  flex: 1;
  min-height: 0;
  overflow-x: hidden;
  overflow-y: auto;
  padding: 2px max(16px, env(safe-area-inset-right, 0px)) 20px
    max(16px, env(safe-area-inset-left, 0px));
}
.rosterTab,
.shopTab,
.saveTab {
  width: min(1180px, 100%);
  margin: 0 auto;
}
.rosterTab {
  display: grid;
  gap: 12px;
}
.list {
  display: flex;
  gap: 8px;
  overflow-x: auto;
  margin: -6px -16px 0;
  padding: 6px 16px 12px;
  scrollbar-width: thin;
}
.who {
  position: relative;
  flex: none;
  width: 78px;
  display: grid;
  justify-items: center;
  gap: 4px;
  padding: 6px 4px 6px;
  border-radius: 14px;
  background: linear-gradient(160deg, rgba(58, 53, 42, 0.9), rgba(26, 24, 19, 0.92));
}
.who.on {
  border-color: var(--accent);
  box-shadow:
    0 0 0 1px rgba(232, 194, 106, 0.4),
    0 4px 14px rgba(232, 194, 106, 0.2);
  background: linear-gradient(160deg, rgba(110, 88, 38, 0.85), rgba(40, 33, 20, 0.92));
}
.face,
.bigface {
  flex: none;
  overflow: hidden;
  background: #0006;
  box-shadow:
    0 0 0 1px rgba(232, 194, 106, 0.45),
    0 2px 6px rgba(0, 0, 0, 0.5);
}
.face {
  width: 44px;
  height: 55px;
  border-radius: 10px;
}
.face :global(img),
.face :global(canvas),
.bigface :global(img),
.bigface :global(canvas) {
  display: block;
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 0;
}
.wtext {
  display: grid;
  justify-items: center;
  min-width: 0;
}
.wname {
  font-family: var(--display);
  font-weight: 800;
  font-size: 14px;
  white-space: nowrap;
}
.wsub {
  font-size: 10px;
  color: var(--muted);
  white-space: nowrap;
}
.ready {
  position: absolute;
  top: 4px;
  right: 4px;
  width: 18px;
  height: 18px;
  display: grid;
  place-items: center;
  border-radius: 50%;
  font-size: 10px;
  color: #2a1c06;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
  box-shadow: 0 0 8px rgba(232, 194, 106, 0.8);
}
.gtag {
  position: absolute;
  top: 4px;
  left: 4px;
  padding: 0 4px;
  border-radius: 6px;
  font-size: 9px;
  font-weight: 800;
  color: #d6ebff;
  background: rgba(30, 60, 110, 0.85);
  border: 1px solid rgba(143, 196, 255, 0.6);
}
@media (min-width: 900px) {
  .rosterTab {
    grid-template-columns: 250px minmax(0, 1fr);
    align-items: start;
  }
  .list {
    flex-direction: column;
    overflow: visible;
    margin: 0;
    padding: 0;
  }
  .who {
    width: auto;
    display: flex;
    justify-items: start;
    align-items: center;
    gap: 10px;
    padding: 6px 10px 6px 6px;
    text-align: left;
  }
  .wtext {
    justify-items: start;
  }
  .wname {
    font-size: 16px;
  }
  .wsub {
    font-size: 12px;
  }
  .ready {
    top: 50%;
    right: 10px;
    margin-top: -9px;
  }
  .gtag {
    top: auto;
    left: auto;
    right: 34px;
    font-size: 10px;
  }
}
.detail {
  display: grid;
  gap: 12px;
  padding: 14px;
  border-radius: 18px;
  background: var(--panel);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid var(--gold-line);
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.4);
  min-width: 0;
}
.hero {
  display: flex;
  gap: 14px;
  align-items: flex-start;
}
.bigface {
  width: 100px;
  height: 125px;
  border-radius: 14px;
  background: radial-gradient(circle at 50% 30%, rgba(232, 194, 106, 0.25), rgba(0, 0, 0, 0.5));
}
.detail.flash .bigface {
  animation: promoted 1.6s ease-out;
}
.heroText {
  flex: 1;
  min-width: 0;
  display: grid;
  gap: 6px;
}
.nameRow {
  display: flex;
  align-items: baseline;
  gap: 8px;
  flex-wrap: wrap;
}
h2 {
  margin: 0;
  font-family: var(--display);
  font-size: 26px;
  font-weight: 900;
  letter-spacing: 0.06em;
  line-height: 1.1;
}
.hanja {
  font-family: var(--display);
  font-size: 13px;
  color: var(--muted);
}
.tags {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
}
.chip.cls {
  border-color: var(--gold-line);
  color: #f3dfae;
}
.chip.guest {
  border-color: rgba(143, 196, 255, 0.6);
  color: #d6ebff;
}
.lvline {
  display: grid;
  grid-template-columns: auto 1fr auto;
  align-items: center;
  gap: 8px;
}
.lv {
  font-size: 12px;
  color: var(--muted);
}
.lv b {
  font-size: 18px;
  color: var(--text);
}
.bar {
  height: 8px;
  border-radius: 4px;
  background: rgba(0, 0, 0, 0.55);
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.6);
  overflow: hidden;
}
.fill {
  height: 100%;
  border-radius: 4px;
  background: linear-gradient(90deg, #3d78e0, #7fc2ff);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.45);
}
.expn {
  font-size: 11px;
  color: var(--muted);
  font-variant-numeric: tabular-nums;
}
.record {
  display: flex;
  gap: 10px;
  font-size: 12px;
  color: var(--muted);
}
.promoteBtn {
  justify-self: start;
  min-height: 40px;
  padding: 6px 14px;
  border-radius: 12px;
  animation: glow 2.2s ease-in-out infinite;
}
.later {
  font-size: 12px;
  color: var(--muted);
}
.cols {
  display: grid;
  gap: 12px;
}
@media (max-width: 1099px) {
  .gearBlock {
    order: -1;
  }
}
@media (min-width: 1100px) {
  .cols {
    grid-template-columns: 1fr 1fr;
    align-items: start;
  }
}
.block {
  display: grid;
  gap: 8px;
  min-width: 0;
}
h3 {
  margin: 4px 0 0;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.14em;
  color: var(--muted);
}
.combat {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(60px, 1fr));
  gap: 6px;
}
.combat div {
  display: grid;
  padding: 6px 8px;
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.3);
  border: 1px solid var(--line);
}
.combat span {
  font-size: 11px;
  color: var(--muted);
}
.combat b {
  font-size: 17px;
  white-space: nowrap;
  font-variant-numeric: tabular-nums;
}
.combat em,
.sv em {
  margin-left: 3px;
  font-style: normal;
  font-size: 11px;
  font-weight: 800;
  color: var(--good);
}
.stats {
  display: grid;
  gap: 5px;
}
.stat {
  display: grid;
  grid-template-columns: 32px 1fr 52px;
  align-items: center;
  gap: 8px;
  font-size: 13px;
}
.sl {
  color: var(--muted);
}
.sbar {
  display: flex;
  height: 8px;
  border-radius: 4px;
  background: rgba(0, 0, 0, 0.5);
  overflow: hidden;
}
.sb.base {
  background: linear-gradient(90deg, #a8792a, #f2cf75);
}
.sb.plus {
  background: linear-gradient(90deg, #4cc46a, #9af08a);
}
.sv {
  text-align: right;
  font-weight: 700;
  font-variant-numeric: tabular-nums;
}
.gear {
  display: grid;
  gap: 6px;
}
.gslot {
  display: flex;
  align-items: center;
  gap: 10px;
  min-height: 56px;
  padding: 8px 12px 8px 8px;
  text-align: left;
  border-radius: 12px;
}
.gslot.open {
  border-color: var(--accent);
}
.gicon {
  flex: none;
  width: 38px;
  height: 38px;
  display: grid;
  place-items: center;
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.35);
  border: 1px solid var(--gold-line);
  color: var(--accent);
}
.sicon {
  width: 20px;
  height: 20px;
}
.gtext {
  flex: 1;
  min-width: 0;
  display: grid;
}
.gname {
  font-weight: 800;
}
.gslot.empty .gname {
  color: var(--muted);
}
.gname small {
  margin-right: 6px;
  font-weight: 400;
  font-size: 11px;
  color: var(--muted);
}
.gnums {
  font-size: 12px;
  color: var(--good);
}
.chev {
  color: var(--muted);
}
.picker {
  display: grid;
  gap: 6px;
  margin: -2px 0 4px 18px;
  padding: 8px;
  border-left: 2px solid var(--gold-line);
  border-radius: 0 10px 10px 0;
  background: rgba(0, 0, 0, 0.25);
  animation: drop 0.18s ease-out;
}
.opt,
.unequip {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 4px 10px;
  min-height: 44px;
  text-align: left;
  border-radius: 10px;
}
.unequip {
  color: var(--bad);
}
.oname {
  font-weight: 700;
}
.oname small {
  color: var(--muted);
}
.odelta {
  display: flex;
  flex-wrap: wrap;
  gap: 2px 8px;
  font-size: 12px;
  font-weight: 700;
}
.good {
  color: var(--good);
}
.bad {
  color: var(--bad);
}
.nofit {
  margin: 0;
  font-size: 12px;
}
.filters {
  display: flex;
  gap: 6px;
  margin-bottom: 10px;
  flex-wrap: wrap;
}
.filters button {
  min-height: 36px;
  padding: 4px 14px;
  border-radius: 999px;
}
.wares {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(min(100%, 340px), 1fr));
  gap: 8px;
}
.ware {
  display: flex;
  gap: 10px;
  align-items: flex-start;
  padding: 10px;
  border-radius: 14px;
  background: linear-gradient(160deg, rgba(52, 47, 37, 0.9), rgba(22, 20, 16, 0.92));
  border: 1px solid var(--line);
}
.wicon {
  flex: none;
  width: 44px;
  height: 44px;
  display: grid;
  place-items: center;
  border-radius: 12px;
  border: 1px solid var(--gold-line);
  color: var(--accent);
  background: radial-gradient(circle at 40% 30%, rgba(232, 194, 106, 0.25), rgba(0, 0, 0, 0.4));
}
.wicon.armour {
  color: #9fd0ff;
  background: radial-gradient(circle at 40% 30%, rgba(79, 142, 240, 0.25), rgba(0, 0, 0, 0.4));
}
.wicon.accessory {
  color: #b9f0a8;
  background: radial-gradient(circle at 40% 30%, rgba(126, 224, 122, 0.22), rgba(0, 0, 0, 0.4));
}
.wicon .sicon {
  width: 24px;
  height: 24px;
}
.wicon.small {
  width: 34px;
  height: 34px;
  border-radius: 10px;
}
.wicon.small .sicon {
  width: 18px;
  height: 18px;
}
.winfo {
  flex: 1;
  min-width: 0;
  display: grid;
  gap: 3px;
}
.wtitle {
  display: flex;
  align-items: baseline;
  flex-wrap: wrap;
  gap: 6px;
}
.wtitle strong {
  font-family: var(--display);
  font-size: 16px;
}
.owned {
  font-size: 11px;
  color: var(--accent);
}
.wnums {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
}
.wnums span {
  padding: 0 7px;
  border-radius: 6px;
  font-size: 11px;
  font-weight: 800;
  color: var(--good);
  background: rgba(126, 224, 122, 0.1);
  border: 1px solid rgba(126, 224, 122, 0.3);
}
.wdesc {
  margin: 0;
  font-size: 12px;
  color: #d5cdbd;
}
.wusers {
  font-size: 11px;
  color: var(--muted);
}
.wusers.none {
  opacity: 0.7;
}
.wusers span {
  color: var(--accent);
  margin-right: 2px;
}
.buy {
  flex: none;
  align-self: center;
  min-width: 84px;
  min-height: 52px;
  display: grid;
  justify-items: center;
  gap: 1px;
  padding: 6px 10px;
  border-radius: 12px;
  color: #22180a;
  background: linear-gradient(180deg, #f6dc94, #d7a84e 55%, #b4852f);
  border-color: #f8e3a8;
}
.buy.short {
  color: var(--bad);
  background: rgba(0, 0, 0, 0.35);
  border-color: rgba(255, 138, 122, 0.4);
}
.bprice {
  display: inline-flex;
  align-items: center;
  gap: 3px;
  font-weight: 900;
  font-variant-numeric: tabular-nums;
}
.bprice .coin {
  width: 15px;
  height: 15px;
}
.blabel {
  font-size: 11px;
  font-weight: 700;
}
.selltitle {
  margin: 18px 0 8px;
}
.spares {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(min(100%, 300px), 1fr));
  gap: 6px;
}
.spare {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 6px 8px;
  border-radius: 12px;
  background: rgba(0, 0, 0, 0.28);
  border: 1px solid var(--line);
}
.sname {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-wrap: wrap;
  align-items: baseline;
  gap: 2px 6px;
}
.sname small {
  color: var(--muted);
}
.snums {
  width: 100%;
  font-size: 11px;
  color: var(--good);
}
.sellbtn {
  flex: none;
  min-height: 44px;
  display: inline-flex;
  align-items: center;
  gap: 4px;
  font-weight: 800;
  font-variant-numeric: tabular-nums;
  color: #ffe7a3;
}
.sellbtn .coin {
  width: 15px;
  height: 15px;
}
.sellbtn span {
  margin-left: 4px;
  font-size: 12px;
  color: var(--text);
}
.saveTab {
  max-width: 640px;
}
.toast {
  position: absolute;
  left: 50%;
  bottom: calc(86px + env(safe-area-inset-bottom, 0px));
  transform: translateX(-50%);
  max-width: calc(100% - 32px);
  padding: 8px 16px;
  border-radius: 999px;
  font-size: 13px;
  font-weight: 700;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  background: rgba(10, 9, 7, 0.88);
  border: 1px solid var(--gold-line);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.5);
  pointer-events: none;
  z-index: 5;
  animation: toast 1.6s ease both;
}
footer {
  flex: none;
  display: flex;
  justify-content: center;
  padding: 10px max(16px, env(safe-area-inset-right, 0px))
    max(10px, env(safe-area-inset-bottom, 0px)) calc(64px + env(safe-area-inset-left, 0px));
  background: linear-gradient(180deg, rgba(14, 12, 9, 0.7), rgba(14, 12, 9, 0.95));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-top: 1px solid var(--gold-line);
  box-shadow: 0 -8px 24px rgba(0, 0, 0, 0.4);
}
.go {
  width: min(420px, 100%);
  min-height: 52px;
  border-radius: 14px;
  font-family: var(--display);
  font-size: 20px;
  letter-spacing: 0.2em;
  text-indent: 0.2em;
}
.scrim {
  position: absolute;
  inset: 0;
  z-index: 8;
  padding: 0;
  border: none;
  border-radius: 0;
  background: rgba(0, 0, 0, 0.6);
  box-shadow: none;
  backdrop-filter: blur(3px);
  -webkit-backdrop-filter: blur(3px);
  cursor: default;
}
.scrim:hover:not(:disabled) {
  border: none;
  box-shadow: none;
}
.sheet {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: min(440px, calc(100% - 24px));
  max-height: calc(100% - 32px);
  overflow-y: auto;
  z-index: 9;
  display: grid;
  gap: 12px;
  padding: 18px;
  border-radius: 20px;
  background:
    radial-gradient(120% 70% at 50% 0%, rgba(232, 194, 106, 0.22), transparent 70%), #16130e;
  border: 1px solid rgba(232, 194, 106, 0.6);
  box-shadow:
    0 0 40px rgba(232, 194, 106, 0.2),
    0 20px 50px rgba(0, 0, 0, 0.7);
  animation: sheet 0.3s cubic-bezier(0.2, 0.9, 0.3, 1.1);
}
.sheet h3 {
  margin: 0;
  font-family: var(--display);
  font-size: 20px;
  letter-spacing: 0.08em;
  color: var(--text);
}
.phead1 {
  display: flex;
  align-items: center;
  gap: 12px;
}
.ptrack {
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  align-items: center;
  gap: 8px;
}
.pcls {
  display: grid;
  justify-items: center;
  padding: 10px;
  border-radius: 14px;
  background: rgba(0, 0, 0, 0.35);
  border: 1px solid var(--line);
}
.pcls.to {
  border-color: var(--accent);
  background: linear-gradient(180deg, rgba(232, 194, 106, 0.22), rgba(0, 0, 0, 0.3));
  box-shadow: 0 0 16px rgba(232, 194, 106, 0.25);
}
.pname {
  font-family: var(--display);
  font-size: 18px;
  font-weight: 900;
}
.pcls small {
  font-size: 11px;
  color: var(--muted);
}
.parrow {
  font-size: 20px;
  color: var(--accent);
}
.ptable {
  display: grid;
  gap: 4px;
}
.phead {
  font-size: 12px;
  letter-spacing: 0.12em;
  color: var(--muted);
}
.prow {
  display: grid;
  grid-template-columns: 48px 1fr auto 1fr 44px;
  align-items: center;
  gap: 6px;
  padding: 4px 8px;
  border-radius: 8px;
  background: rgba(0, 0, 0, 0.25);
  font-variant-numeric: tabular-nums;
}
.pl {
  font-size: 12px;
  color: var(--muted);
}
.pa {
  text-align: right;
}
.pto {
  color: var(--muted);
}
.pb {
  font-weight: 800;
}
.pd {
  text-align: right;
  font-size: 12px;
  font-weight: 800;
}
.pskills {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 6px;
  font-size: 12px;
  color: var(--muted);
}
.chip.skill {
  color: #e2c8ff;
  border-color: #c58cff;
}
.pactions {
  display: grid;
  grid-template-columns: 1fr 1.6fr;
  gap: 8px;
}
.pactions button {
  min-height: 48px;
  border-radius: 12px;
}
@keyframes pulse {
  40% {
    transform: scale(1.18);
    color: #fff;
  }
}
@keyframes glow {
  50% {
    box-shadow:
      inset 0 1px 0 rgba(255, 255, 255, 0.55),
      0 0 18px rgba(232, 194, 106, 0.6);
  }
}
@keyframes drop {
  from {
    opacity: 0;
    transform: translateY(-6px);
  }
}
@keyframes toast {
  from {
    opacity: 0;
    transform: translate(-50%, 8px);
  }
  12%,
  80% {
    opacity: 1;
    transform: translate(-50%, 0);
  }
  to {
    opacity: 0;
  }
}
@keyframes sheet {
  from {
    opacity: 0;
    transform: translate(-50%, calc(-50% + 30px));
  }
}
@keyframes promoted {
  0% {
    box-shadow:
      0 0 0 2px #fff6d0,
      0 0 40px 12px rgba(255, 226, 150, 0.9);
    filter: brightness(1.6);
  }
}
@media (prefers-reduced-motion: reduce) {
  .pulse,
  .promoteBtn,
  .picker,
  .toast,
  .sheet,
  .detail.flash .bigface {
    animation: none;
  }
}
</style>
