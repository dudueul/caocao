// engineio.ts — Engine.IO v4 framing (server side).
// [RE] Contract from docs/lab/07-protocol-reconstruction.md §7.2, recovered
// from the original client (SocketIOComponent, EIO=4 URL template).

export type EioPacketType = "open" | "ping" | "pong" | "message" | "close";

export const EIO = {
  open: "0",
  ping: "2",
  pong: "3",
  message: "4",
  close: "6",
} as const;

export interface Handshake {
  sid: string;
  pingInterval: number;
  pingTimeout: number;
  maxPayload: number;
}

export function encodeOpen(h: Handshake): string {
  return EIO.open + JSON.stringify(h);
}

/** Parse a raw engine.io text frame. Returns null for invalid frames. */
export function decodeFrame(frame: string):
  | { type: EioPacketType; data: string }
  | null {
  if (frame.length === 0) return null;
  const t = frame[0];
  const data = frame.slice(1);
  switch (t) {
    case EIO.open: return { type: "open", data };
    case EIO.ping: return { type: "ping", data };
    case EIO.pong: return { type: "pong", data };
    case EIO.message: return { type: "message", data };
    case EIO.close: return { type: "close", data };
    default: return null;
  }
}

export function sid(len = 16): string {
  const bytes = new Uint8Array(len);
  crypto.getRandomValues(bytes);
  return Array.from(bytes, (b) => b.toString(16).padStart(2, "0")).join("");
}
