// socketio.ts — Socket.IO v4 packet layer over engine.io messages.
// [RE] Grammar per Lab 07 §7.3; namespace is always "/" in this game.

export const SIO = {
  connect: "0",
  disconnect: "1",
  event: "2",
  ack: "3",
  connectError: "4",
} as const;

/** Wrap a socket.io packet into an engine.io message frame. */
export function wrapIo(inner: string): string {
  return "4" + inner;
}

export function connectAck(data: Record<string, unknown>): string {
  return wrapIo(SIO.connect + JSON.stringify(data));
}

export function event(name: string, ...args: unknown[]): string {
  return wrapIo(SIO.event + JSON.stringify([name, ...args]));
}

export function ack(id: number, data: unknown[]): string {
  return wrapIo(SIO.ack + id + JSON.stringify(data));
}

export function disconnect(): string {
  return wrapIo(SIO.disconnect);
}

export interface IoPacket {
  kind: "connect" | "disconnect" | "event" | "ack" | "connectError";
  ackId?: number;
  eventName?: string;
  args: unknown[];
}

/** Parse the socket.io payload carried inside an engine.io message. */
export function decodeIo(data: string): IoPacket | null {
  if (!data) return null;
  let i = 0;
  if (data[0] === "/") {
    // namespace prefix — this game uses only default "/", but be strict
    const comma = data.indexOf(",");
    if (comma < 0) return null;
    if (data.slice(0, comma) !== "/") return null; // reject foreign namespaces
    i = comma + 1;
  }
  if (i >= data.length) return null;
  const p = data[i];
  let body = data.slice(i + 1);

  switch (p) {
    case SIO.connect:
      return { kind: "connect", args: [] };
    case SIO.disconnect:
      return { kind: "disconnect", args: [] };
    case SIO.event:
    case SIO.ack: {
      // optional numeric ack id, then "#"? — v4 puts id directly: "3<json>"
      // with id digits only when kind=ack.
      let ackId: number | undefined;
      if (p === SIO.ack) {
        const m = /^(\d+)(.*)$/s.exec(body);
        if (!m) return null;
        ackId = parseInt(m[1], 10);
        body = m[2];
      } else if (/^\d+#/.test(body)) {
        const m = /^(\d+)#(.*)$/s.exec(body);
        ackId = parseInt(m![1], 10);
        body = m![2];
      }
      const arr = body ? JSON.parse(body) : [];
      return {
        kind: p === SIO.ack ? "ack" : "event",
        ackId,
        eventName: typeof arr[0] === "string" ? arr[0] : undefined,
        args: arr.slice(1),
      };
    }
    case SIO.connectError:
      return { kind: "connectError", args: [body] };
    default:
      return null;
  }
}
