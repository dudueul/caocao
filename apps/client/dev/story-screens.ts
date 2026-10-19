// Gallery of the Story screens with prepared campaigns (dev server only):
// /dev/story-screens.html?view=sortie|result|loss|camp|load
import "../src/app.css";
import {
  type BattleReport,
  type CampaignState,
  finishScene,
  gainExp,
  newCampaign,
  type OfficerReport,
  promotionFor,
  type StatGains,
} from "@tk/campaign";
import type { Stats } from "@tk/rules";
import { mount } from "svelte";
import SaveSlots from "../src/components/SaveSlots.svelte";
import { writeSlot } from "../src/lib/saves.ts";
import { story } from "../src/lib/story.svelte.ts";
import CampView from "../src/screens/story/CampView.svelte";
import ResultView from "../src/screens/story/ResultView.svelte";
import SortieView from "../src/screens/story/SortieView.svelte";

// Saves go to memory here, so this page never touches the real save slots.
const memory = new Map<string, string>();
const fakeStorage: Storage = {
  get length() {
    return memory.size;
  },
  clear: () => memory.clear(),
  getItem: (k) => memory.get(k) ?? null,
  key: (i) => [...memory.keys()][i] ?? null,
  removeItem: (k) => void memory.delete(k),
  setItem: (k, v) => void memory.set(k, String(v)),
};
Object.defineProperty(window, "localStorage", { value: fakeStorage, configurable: true });

const target = document.getElementById("app") as HTMLElement;
const view = new URLSearchParams(location.search).get("view") ?? "sortie";

function next(s: CampaignState): CampaignState {
  const r = finishScene(s);
  if (!r.ok) throw new Error(r.reason);
  return r.state;
}

/** The campaign at the first battle (Baima). */
function atBaima(): CampaignState {
  return next(next(newCampaign("guandu")));
}

/** A won Baima with real level-ups, and the report for it. */
function wonBaima(): { state: CampaignState; report: BattleReport } {
  const s = structuredClone(atBaima());
  const zl = s.roster.find((o) => o.id === "zhang-liao");
  if (zl) {
    zl.level = 19;
    zl.exp = 40;
  }
  const amounts: Record<string, number> = {
    "cao-cao": 96,
    "guan-yu": 142,
    "xu-chu": 118,
    "xiahou-yuan": 64,
    "zhang-liao": 175,
    "xu-huang": 48,
    "xun-you": 230,
  };
  const officers: OfficerReport[] = [];
  for (const o of s.roster) {
    const exp = amounts[o.id] ?? 30;
    const levelBefore = o.level;
    const ups = gainExp(o, exp);
    const gains: StatGains = {};
    for (const u of ups)
      for (const [k, v] of Object.entries(u.gains) as [keyof Stats, number][])
        gains[k] = (gains[k] ?? 0) + v;
    o.battles += 1;
    officers.push({
      id: o.id,
      exp,
      levelBefore,
      level: o.level,
      ups,
      gains,
      promotion: promotionFor(o),
    });
  }
  s.gold += 800;
  s.inventory["great-ji"] = 1;
  s.inventory.lamellar = 1;
  s.cleared.push("baima");
  s.step += 1;
  return { state: s, report: { won: true, gold: 800, items: ["great-ji", "lamellar"], officers } };
}

/** The first camp, with spares and one officer ready to promote. */
function atCamp(): CampaignState {
  const s = next(wonBaima().state);
  const zl = s.roster.find((o) => o.id === "zhang-liao");
  if (zl) {
    zl.level = 20;
    zl.exp = 15;
    zl.defeated = 4;
  }
  s.inventory.jade = 1;
  s.inventory["feather-fan"] = 1;
  return s;
}

function seedSaves() {
  writeSlot("auto", atBaima());
  writeSlot("1", atCamp());
}

switch (view) {
  case "result": {
    const w = wonBaima();
    story.load(w.state, null);
    story.report = w.report;
    story.phase = "result";
    mount(ResultView, { target });
    break;
  }
  case "loss": {
    story.load(atBaima(), null);
    story.report = { won: false, gold: 0, items: [], officers: [] };
    story.phase = "result";
    mount(ResultView, { target });
    break;
  }
  case "camp":
    seedSaves();
    story.load(atCamp(), "1");
    mount(CampView, { target });
    break;
  case "load": {
    seedSaves();
    const box = document.createElement("div");
    box.style.cssText = "max-width:640px;margin:0 auto;padding:16px;height:100%;overflow:auto";
    target.append(box);
    mount(SaveSlots, {
      target: box,
      props: { mode: "load", onload: (slot) => alert(`load ${slot}`) },
    });
    break;
  }
  default:
    story.start();
    story.endScene();
    story.endScene();
    mount(SortieView, { target });
}

// Stand-in for StoryScreen's menu button, to check that the footers leave room for it.
const menu = document.createElement("button");
menu.textContent = "☰";
menu.style.cssText =
  "position:fixed;left:10px;bottom:10px;width:40px;height:34px;padding:0;z-index:20";
document.body.append(menu);
