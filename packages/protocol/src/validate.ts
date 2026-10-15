/**
 * Boundary checks for messages from clients. The referee trusts nothing that
 * arrives on a socket: anything malformed is dropped before it reaches the rules
 * (ADR 0004, ADR 0037). Server messages are trusted by the client and only parsed.
 */
import type { Action, Cell, Command } from "@tk/rules";
import { DEVICE_KEY, ROOM_CODE } from "./codes.ts";
import type { ClientMessage, ServerMessage } from "./messages.ts";

/** Largest message a client may send, in characters. */
export const MAX_CLIENT_MESSAGE = 4096;

type Obj = Record<string, unknown>;

const isObj = (v: unknown): v is Obj => typeof v === "object" && v !== null && !Array.isArray(v);
const isInt = (v: unknown): v is number => Number.isSafeInteger(v);
const isId = (v: unknown): v is string =>
  typeof v === "string" && v.length >= 1 && v.length <= 64 && /^[A-Za-z0-9_-]+$/.test(v);

function cell(v: unknown): Cell | null {
  if (!isObj(v) || !isInt(v.x) || !isInt(v.y)) return null;
  if (v.x < 0 || v.y < 0 || v.x > 255 || v.y > 255) return null;
  return { x: v.x, y: v.y };
}

function action(v: unknown): Action | null {
  if (!isObj(v)) return null;
  if (v.kind === "wait") return { kind: "wait" };
  if (v.kind === "attack" && isId(v.target)) return { kind: "attack", target: v.target };
  if (v.kind === "stratagem" && isId(v.id)) {
    const at = cell(v.at);
    return at ? { kind: "stratagem", id: v.id, at } : null;
  }
  return null;
}

/** A well-formed command (legality is the rules' job), rebuilt without extra fields. */
export function parseCommand(v: unknown): Command | null {
  if (!isObj(v)) return null;
  if (v.kind === "endPhase") return { kind: "endPhase" };
  if (v.kind !== "act" || !isId(v.unit)) return null;
  const to = cell(v.to);
  const act = action(v.action);
  return to && act ? { kind: "act", unit: v.unit, to, action: act } : null;
}

/** Parses one client frame; null when it is malformed in any way. */
export function parseClientMessage(raw: string): ClientMessage | null {
  if (raw.length > MAX_CLIENT_MESSAGE) return null;
  let v: unknown;
  try {
    v = JSON.parse(raw);
  } catch {
    return null;
  }
  if (!isObj(v)) return null;
  switch (v.t) {
    case "hello": {
      const { v: version, room, key, name, lastSeq } = v;
      if (!isInt(version) || typeof room !== "string" || !ROOM_CODE.test(room)) return null;
      if (typeof key !== "string" || !DEVICE_KEY.test(key)) return null;
      if (typeof name !== "string" || name.length > 64) return null;
      if (lastSeq !== null && (!isInt(lastSeq) || lastSeq < 0)) return null;
      return { t: "hello", v: version, room, key, name, lastSeq };
    }
    case "pick":
      return isId(v.entry) ? { t: "pick", entry: v.entry } : null;
    case "arrange":
      return Array.isArray(v.order) && v.order.length <= 16 && v.order.every(isId)
        ? { t: "arrange", order: [...v.order] }
        : null;
    case "ready":
      return { t: "ready" };
    case "command": {
      const command = parseCommand(v.command);
      return command ? { t: "command", command } : null;
    }
    case "concede":
      return { t: "concede" };
    case "ping":
      return isInt(v.at) ? { t: "ping", at: v.at } : null;
    default:
      return null;
  }
}

/** Parses a server frame on the client. */
export function parseServerMessage(raw: string): ServerMessage | null {
  try {
    const v: unknown = JSON.parse(raw);
    return isObj(v) && typeof v.t === "string" ? (v as unknown as ServerMessage) : null;
  } catch {
    return null;
  }
}
