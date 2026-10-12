// index.ts — CAOCAAO serverless game server entrypoint.
// [RE] Rebuild of the original Node Socket.IO backend (evidence: client
// SocketIOComponent + `ws://{0}/socket.io/?EIO=4&transport=websocket`).
// Worker accepts the WS upgrade, hands the socket to a GameServer DO.

import { DurableObject } from "cloudflare:workers";
import { encodeOpen, decodeFrame, sid, type Handshake } from "./engineio";
import { decodeIo, connectAck, event, type IoPacket } from "./socketio";

export interface Env {
  GAME_SERVER: DurableObjectNamespace;
  DB: D1Database;
}

const HS: Omit<Handshake, "sid"> = {
  pingInterval: 25000,   // [RE] original client field default (SocketIOComponent)
  pingTimeout: 20000,
  maxPayload: 1_000_000,
};

export default {
  async fetch(request: Request, env: Env): Promise<Response> {
    const url = new URL(request.url);

    if (url.pathname === "/socket.io/" && request.headers.get("Upgrade") === "websocket") {
      // shard by sid-less connect: single DO for now; scale-out key can be region/room
      const id = env.GAME_SERVER.idFromName("global");
      const stub = env.GAME_SERVER.get(id);
      return stub.fetch(request);
    }

    if (url.pathname === "/healthz") return new Response("ok");

    return new Response("not found", { status: 404 });
  },
};

export class GameServer extends DurableObject {
  override async fetch(request: Request): Promise<Response> {
    const pair = new WebSocketPair();
    const [client, server] = Object.values(pair) as [WebSocket, WebSocket];

    this.ctx.acceptWebSocket(server);

    const session = sid();
    server.send(encodeOpen({ ...HS, sid: session }));

    this.ctx.setWebSocketAutoResponse(
      new WebSocketRequestResponsePair("2", "3")); // engine.io ping/pong at the edge

    this.sessions.set(server, session);
    return new Response(null, { status: 101, webSocket: client });
  }

  private sessions = new Map<WebSocket, string>();

  override async webSocketMessage(ws: WebSocket, message: string | ArrayBuffer): Promise<void> {
    if (typeof message !== "string") return;      // binary frames unused by this protocol
    const frame = decodeFrame(message);
    if (!frame) return;

    switch (frame.type) {
      case "ping":
        ws.send("3");                              // in case auto-response is off
        break;
      case "message": {
        const pkt = decodeIo(frame.data);
        if (!pkt) return;
        await this.handleIo(ws, pkt);
        break;
      }
      case "close":
        this.sessions.delete(ws);
        break;
    }
  }

  override async webSocketClose(ws: WebSocket): Promise<void> {
    const s = this.sessions.get(ws);
    if (s) {
      this.sessions.delete(ws);
      this.broadcast(event("playerLeft", { sid: s }), ws);
    }
  }

  private async handleIo(ws: WebSocket, pkt: IoPacket): Promise<void> {
    switch (pkt.kind) {
      case "connect":
        ws.send(connectAck({ sid: this.sessions.get(ws) }));
        break;
      case "event":
        // Event catalog lands in Phase 2 (analysis/protocol_events.md).
        // Echo for bring-up; zod schemas + D1 persistence next.
        ws.send(event("serverEcho", pkt.eventName, pkt.args));
        break;
      case "ack":
        break;
    }
  }

  private broadcast(text: string, except?: WebSocket): void {
    for (const ws of this.ctx.getWebSockets()) {
      if (ws !== except) ws.send(text);
    }
  }
}
