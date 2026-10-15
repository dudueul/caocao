/**
 * Guest identity (ADR 0038): a random device key and a display name, kept in
 * this browser. Storage can be missing (private windows), so every access is
 * guarded and the key falls back to one per page load.
 */
const KEY = "tk.deviceKey";
const NAME = "tk.name";

function read(k: string): string | null {
  try {
    return localStorage.getItem(k);
  } catch {
    return null;
  }
}

function write(k: string, v: string): void {
  try {
    localStorage.setItem(k, v);
  } catch {
    // Storage blocked: the value lasts for this page only.
  }
}

let memoryKey: string | null = null;

export function deviceKey(): string {
  const stored = read(KEY);
  if (stored && /^[0-9a-f]{32}$/.test(stored)) return stored;
  memoryKey ??= [...crypto.getRandomValues(new Uint8Array(16))]
    .map((b) => b.toString(16).padStart(2, "0"))
    .join("");
  write(KEY, memoryKey);
  return memoryKey;
}

export function savedName(): string {
  return read(NAME) ?? "";
}

export function saveName(name: string): void {
  write(NAME, name);
}

/** Rooms this browser created and hasn't joined yet (so `hello` asks to create them). */
export function markCreating(code: string): void {
  try {
    sessionStorage.setItem(`tk.create.${code}`, "1");
  } catch {
    // Without session storage, a reload of a brand-new room just reports it missing.
  }
}

export function takeCreating(code: string): boolean {
  try {
    const v = sessionStorage.getItem(`tk.create.${code}`) === "1";
    return v;
  } catch {
    return false;
  }
}

export function doneCreating(code: string): void {
  try {
    sessionStorage.removeItem(`tk.create.${code}`);
  } catch {
    // Nothing to clean.
  }
}
