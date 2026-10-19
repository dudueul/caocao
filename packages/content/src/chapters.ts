/** The Story's chapters in order (ADR 0056). Chapter 2 is still being written. */
import { GUANDU } from "./guandu.ts";
import { RISE } from "./rise.ts";
import type { ChapterDef } from "./story.ts";

export const CHAPTER_LIST: readonly ChapterDef[] = [RISE, GUANDU];

export const CHAPTERS: Readonly<Record<string, ChapterDef>> = Object.fromEntries(
  CHAPTER_LIST.map((c) => [c.id, c]),
);

/** Where a new game begins. */
export const FIRST_CHAPTER: string = RISE.id;
