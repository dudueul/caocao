/**
 * Room codes and device keys (ADR 0038, ADR 0041). Codes use 31 characters
 * with no look-alikes (no 0/O, 1/I/L); keys are 128-bit hex secrets.
 */
export const ROOM_ALPHABET = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
export const ROOM_CODE_LENGTH = 6;
export const ROOM_CODE = /^[23456789ABCDEFGHJKMNPQRSTUVWXYZ]{6}$/;
export const DEVICE_KEY = /^[0-9a-f]{32}$/;
export const NAME_MAX = 12;

/** A room code from random bytes (crypto-quality bytes are the caller's job). */
export function roomCodeFrom(bytes: Uint8Array): string {
  let out = "";
  for (let i = 0; i < ROOM_CODE_LENGTH; i++)
    out += ROOM_ALPHABET[(bytes[i] ?? 0) % ROOM_ALPHABET.length];
  return out;
}

/** Normalizes a typed code: upper case, spaces and dashes dropped. */
export function normalizeRoomCode(input: string): string {
  return input.toUpperCase().replace(/[\s-]/g, "");
}

/** Trims a display name and checks its length (1–12 characters). */
export function cleanName(input: string): string | null {
  const name = input.trim().replace(/\s+/g, " ");
  const length = [...name].length;
  return length >= 1 && length <= NAME_MAX ? name : null;
}
