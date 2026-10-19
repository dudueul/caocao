/**
 * Who is speaking a line of dialogue: the name to show, the army colour and
 * the class for the portrait. Lines may override the side (장합 changes sides).
 */
import type { CampaignState } from "@tk/campaign";
import { CHAPTERS, type Line, OFFICERS } from "@tk/content";

/** Each officer's usual army and class in the chapters, from the battles they fight in. */
const HOME = new Map<string, { side: number; classId: string }>();
for (const ch of Object.values(CHAPTERS)) {
  for (const j of ch.roster) HOME.set(j.officer, { side: 0, classId: j.classId });
  for (const step of ch.steps) {
    if (step.kind === "scene")
      for (const j of step.scene.joins ?? [])
        if (!HOME.has(j.officer)) HOME.set(j.officer, { side: 0, classId: j.classId });
    if (step.kind === "battle")
      for (const u of [...step.battle.guests, ...step.battle.enemies])
        if (!HOME.has(u.officer)) HOME.set(u.officer, { side: u.side, classId: u.classId });
  }
}

export interface Speaker {
  id: string;
  name: string;
  side: number;
  classId: string;
}

export function speakerOf(line: Line, campaign: CampaignState | null): Speaker | null {
  if (!line.who) return null;
  const inRoster = campaign?.roster.find((o) => o.id === line.who);
  const home = HOME.get(line.who);
  return {
    id: line.who,
    name: line.name ?? OFFICERS[line.who]?.name ?? line.who,
    side: line.side ?? (inRoster ? 0 : (home?.side ?? 0)),
    classId: inRoster?.classId ?? home?.classId ?? "strategist",
  };
}
