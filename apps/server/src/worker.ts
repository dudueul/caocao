/**
 * The Worker: `/api/rooms/CODE/ws` upgrades to the room's MatchDO; everything else
 * is the client's static build (Workers Static Assets, single-page fallback).
 */
import { ROOM_CODE } from "@tk/protocol";
import type { Env } from "./match.ts";

export { MatchDO } from "./match.ts";

export default {
  async fetch(request: Request, env: Env): Promise<Response> {
    const url = new URL(request.url);
    const ws = url.pathname.match(/^\/api\/rooms\/([^/]+)\/ws$/);
    if (ws) {
      const code = ws[1] ?? "";
      if (!ROOM_CODE.test(code)) return new Response("bad room code", { status: 400 });
      if (request.headers.get("Upgrade") !== "websocket")
        return new Response("expected a WebSocket", { status: 426 });
      return env.MATCH.get(env.MATCH.idFromName(code)).fetch(request);
    }
    if (url.pathname === "/api/health") return Response.json({ ok: true });
    if (url.pathname.startsWith("/api/")) return new Response("not found", { status: 404 });
    return env.ASSETS.fetch(request);
  },
} satisfies ExportedHandler<Env>;
