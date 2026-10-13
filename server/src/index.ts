// index.ts — CAOCAAO serverless game server.
// [RE] Rebuild of the original Node Socket.IO backend. Wire contract:
//   analysis/protocol_events.md + docs/lab/07-protocol-reconstruction.md
// Socket.IO v4 events carry JSON arrays [event, payload]; REST mirrors the
// recovered 492-endpoint catalog (analysis/rest_endpoints.txt) as POST with
// [user_id, ...args] bodies (Session.addUserIdObjects convention).

import { DurableObject } from "cloudflare:workers";
import { encodeOpen, decodeFrame, sid, type Handshake } from "./engineio";
import { decodeIo, connectAck, event, ack as ioAck, type IoPacket } from "./socketio";
import {
  LoginPayload, EnterWorldPayload, WantRoomPayload, BroadcastPayload,
} from "./schema";

export interface Env {
  GAME_SERVER: DurableObjectNamespace;
  DB: D1Database;
  CDN: R2Bucket;
}

const HS: Omit<Handshake, "sid"> = {
  pingInterval: 25000,   // [RE] SocketIOComponent defaults
  pingTimeout: 20000,
  maxPayload: 1_000_000,
};

type Session = {
  ws: WebSocket;
  sid: string;
  userId?: string;
  authKey?: string;
  logined: boolean;
  rooms: Set<string>;
};

const CORS = {
  "access-control-allow-origin": "*",          // [RE] tighten per-channel in prod
  "access-control-allow-methods": "POST, GET, OPTIONS",
  "access-control-allow-headers": "content-type",
};

export default {
  async fetch(request: Request, env: Env): Promise<Response> {
    const url = new URL(request.url);

    // [RE] WebGL channel: browsers preflight every POST with content-type
    if (request.method === "OPTIONS")
      return new Response(null, { status: 204, headers: CORS });

    if (url.pathname === "/socket.io/" && request.headers.get("Upgrade") === "websocket") {
      const id = env.GAME_SERVER.idFromName("global");
      return env.GAME_SERVER.get(id).fetch(request);
    }

    // REST: mirror the recovered catalog. args = [user_id, ...rest].
    if (url.pathname.startsWith("/") && request.method === "POST") {
      const id = env.GAME_SERVER.idFromName("global");
      const resp = await env.GAME_SERVER.get(id).fetch(
        new Request("https://do/rest" + url.pathname, {
          method: "POST",
          body: await request.text(),
        }));
      if (resp.status !== 404) return resp;
    }

    // [RE] CDN route: /cdn/<key> serves R2 objects with the right encoding.
    // Upload pipeline: scripts/upload_cdn.sh (Brotli + manifest).
    if (url.pathname.startsWith("/cdn/")) {
      const key = decodeURIComponent(url.pathname.slice("/cdn/".length));
      const obj = await env.CDN.get(key);
      if (!obj) return new Response("not found", { status: 404, headers: CORS });
      const headers: Record<string, string> = { ...CORS };
      obj.writeHttpMetadata(headers);
      if (key.endsWith(".br")) headers["content-encoding"] = "br";
      if (key.endsWith(".json")) headers["content-type"] = "application/json";
      return new Response(obj.body, { headers });
    }

    if (url.pathname === "/healthz")
      return new Response("ok", { headers: CORS });
    return new Response("not found", { status: 404, headers: CORS });
  },
};

const CATALOG: CatalogRow[] = [
 {
  "item_id": 10001,
  "name": "sword_iron",
  "tier": 0,
  "slot": 1,
  "item_type": 1,
  "price": 0,
  "gold": 120,
  "atk": 8,
  "atk_up": 2,
  "max_lv": 5,
  "icon": "item_n_sword1",
  "atlas": "item"
 },
 {
  "item_id": 10002,
  "name": "sword_steel",
  "tier": 1,
  "slot": 1,
  "item_type": 1,
  "price": 0,
  "gold": 450,
  "atk": 15,
  "atk_up": 3,
  "max_lv": 5,
  "icon": "item_n_sword2",
  "atlas": "item"
 },
 {
  "item_id": 10003,
  "name": "sword_king",
  "tier": 2,
  "slot": 1,
  "item_type": 1,
  "price": 0,
  "gold": 1600,
  "atk": 26,
  "atk_up": 5,
  "max_lv": 8,
  "icon": "item_n_sword3",
  "atlas": "item"
 },
 {
  "item_id": 10011,
  "name": "spear_cavalry",
  "tier": 0,
  "slot": 1,
  "item_type": 2,
  "price": 0,
  "gold": 140,
  "atk": 7,
  "atk_up": 2,
  "mor": 1,
  "max_lv": 5,
  "icon": "item_n_spear1",
  "atlas": "item"
 },
 {
  "item_id": 10021,
  "name": "bow_short",
  "tier": 0,
  "slot": 1,
  "item_type": 3,
  "price": 0,
  "gold": 130,
  "atk": 6,
  "atk_up": 2,
  "agi": 2,
  "max_lv": 5,
  "icon": "item_n_bow1",
  "atlas": "item"
 },
 {
  "item_id": 10031,
  "name": "horse_light",
  "tier": 0,
  "slot": 2,
  "item_type": 4,
  "price": 0,
  "gold": 200,
  "agi": 4,
  "agi_up": 1,
  "hp": 5,
  "max_lv": 5,
  "icon": "item_n_horse1",
  "atlas": "item"
 },
 {
  "item_id": 10032,
  "name": "horse_heavy",
  "tier": 1,
  "slot": 2,
  "item_type": 4,
  "price": 0,
  "gold": 700,
  "agi": 2,
  "def": 6,
  "def_up": 2,
  "hp": 12,
  "max_lv": 5,
  "icon": "item_n_horse2",
  "atlas": "item"
 },
 {
  "item_id": 10041,
  "name": "armor_leather",
  "tier": 0,
  "slot": 3,
  "item_type": 5,
  "price": 0,
  "gold": 110,
  "def": 5,
  "def_up": 1,
  "max_lv": 5,
  "icon": "item_n_light1",
  "atlas": "item"
 },
 {
  "item_id": 10042,
  "name": "armor_chain",
  "tier": 1,
  "slot": 3,
  "item_type": 5,
  "price": 0,
  "gold": 480,
  "def": 11,
  "def_up": 2,
  "hp": 8,
  "max_lv": 5,
  "icon": "item_n_light2",
  "atlas": "item"
 },
 {
  "item_id": 10043,
  "name": "armor_plate",
  "tier": 2,
  "slot": 3,
  "item_type": 5,
  "price": 0,
  "gold": 1500,
  "def": 20,
  "def_up": 4,
  "hp": 15,
  "max_lv": 8,
  "icon": "item_n_light3",
  "atlas": "item"
 },
 {
  "item_id": 10051,
  "name": "helmet_iron",
  "tier": 0,
  "slot": 4,
  "item_type": 6,
  "price": 0,
  "gold": 100,
  "def": 3,
  "def_up": 1,
  "max_lv": 5,
  "icon": "item_n_helm1",
  "atlas": "item"
 },
 {
  "item_id": 10061,
  "name": "potion_heal",
  "tier": 0,
  "slot": 0,
  "item_type": 10,
  "price": 0,
  "gold": 60,
  "item_effect": 1,
  "effect_value": 50,
  "max_value": 200,
  "max_lv": 1,
  "icon": "item_n_potion1",
  "atlas": "item"
 },
 {
  "item_id": 10062,
  "name": "potion_mana",
  "tier": 0,
  "slot": 0,
  "item_type": 10,
  "price": 0,
  "gold": 80,
  "item_effect": 2,
  "effect_value": 30,
  "max_value": 100,
  "max_lv": 1,
  "icon": "item_n_potion2",
  "atlas": "item"
 },
 {
  "item_id": 10071,
  "name": "treasure_seal_1",
  "tier": 1,
  "slot": 5,
  "item_type": 20,
  "price": 0,
  "gold": 2500,
  "item_effect": 3,
  "effect_value": 5,
  "tresure_type": 1,
  "enhance_type": 1,
  "max_lv": 3,
  "icon": "icon_treasure1",
  "atlas": "treasure"
 },
 {
  "item_id": 10072,
  "name": "treasure_seal_2",
  "tier": 2,
  "slot": 5,
  "item_type": 20,
  "price": 0,
  "gold": 6000,
  "item_effect": 3,
  "effect_value": 8,
  "tresure_type": 1,
  "enhance_type": 1,
  "max_lv": 5,
  "icon": "icon_treasure2",
  "atlas": "treasure"
 },
 {
  "item_id": 10081,
  "name": "ticket_recruit",
  "tier": 0,
  "slot": 0,
  "item_type": 30,
  "price": 100,
  "gold": 0,
  "pay_type": 1,
  "max_lv": 1,
  "icon": "item_t_ticket1",
  "atlas": "item"
 },
 {
  "item_id": 10082,
  "name": "ticket_enhance",
  "tier": 0,
  "slot": 0,
  "item_type": 30,
  "price": 0,
  "gold": 500,
  "max_lv": 1,
  "icon": "icon_enhanceTicket",
  "atlas": "item"
 },
 {
  "item_id": 10091,
  "name": "book_skill_sword",
  "tier": 1,
  "slot": 6,
  "item_type": 40,
  "price": 0,
  "gold": 1200,
  "skill_id": 101,
  "skill_val": 1,
  "max_lv": 1,
  "icon": "item_n_book1",
  "atlas": "item"
 },
 {
  "item_id": 10101,
  "name": "unit_guanYu",
  "tier": 3,
  "slot": 7,
  "item_type": 50,
  "price": 0,
  "gold": 0,
  "unit_id": 1001,
  "unit_lv": 1,
  "job_type": 1,
  "country": 1,
  "max_lv": 10,
  "icon": "unit_1001",
  "atlas": "unit"
 },
 {
  "item_id": 10102,
  "name": "unit_zhangFei",
  "tier": 3,
  "slot": 7,
  "item_type": 50,
  "price": 0,
  "gold": 0,
  "unit_id": 1002,
  "unit_lv": 1,
  "job_type": 2,
  "country": 1,
  "max_lv": 10,
  "icon": "unit_1002",
  "atlas": "unit"
 },
 {
  "item_id": 10103,
  "name": "unit_xiaHouDun",
  "tier": 2,
  "slot": 7,
  "item_type": 50,
  "price": 0,
  "gold": 9000,
  "unit_id": 1003,
  "unit_lv": 1,
  "job_type": 1,
  "country": 2,
  "max_lv": 10,
  "icon": "unit_1003",
  "atlas": "unit"
 },
 {
  "item_id": 10104,
  "name": "unit_zhaoYun",
  "tier": 2,
  "slot": 7,
  "item_type": 50,
  "price": 0,
  "gold": 9500,
  "unit_id": 1004,
  "unit_lv": 1,
  "job_type": 3,
  "country": 1,
  "max_lv": 10,
  "icon": "unit_1004",
  "atlas": "unit"
 },
 {
  "item_id": 10111,
  "name": "skin_king_crown",
  "tier": 2,
  "slot": 8,
  "item_type": 60,
  "price": 300,
  "gold": 0,
  "pay_type": 1,
  "skin_id": 5001,
  "max_lv": 1,
  "icon": "skin_5001",
  "atlas": "skin"
 },
 {
  "item_id": 10121,
  "name": "box_gold_1",
  "tier": 0,
  "slot": 0,
  "item_type": 70,
  "box_type": 1,
  "price": 0,
  "gold": 300,
  "max_lv": 1,
  "icon": "item_n_box1",
  "atlas": "item"
 }
];

interface CatalogRow {
  item_id: number; name: string; tier: number; slot: number; item_type: number;
  box_type: number; price: number; gold: number; item_effect: number;
  effect_value: number; max_value: number; type_value: number; pay_type: number;
  atk: number; atk_up: number; def: number; def_up: number; inte: number;
  inte_up: number; agi: number; agi_up: number; mor: number; mor_up: number;
  hp: number; hp_up: number; mp: number; mp_up: number; max_lv: number;
  target_type: number; skill_id: number; unit_id: number; atlas: string;
  icon: string; country: number; job_type: number;
}

export class GameServer extends DurableObject {
  private migrated = false;
  private sessions = new Map<WebSocket, Session>();

  override async fetch(request: Request): Promise<Response> {
    const url = new URL(request.url);

    if (request.headers.get("Upgrade") === "websocket") {
      const pair = new WebSocketPair();
      const [client, server] = Object.values(pair) as [WebSocket, WebSocket];
      this.ctx.acceptWebSocket(server);
      const session: Session = {
        ws: server, sid: sid(), logined: false, rooms: new Set(),
      };
      this.sessions.set(server, session);
      server.send(encodeOpen({ ...HS, sid: session.sid }));
      this.ctx.setWebSocketAutoResponse(new WebSocketRequestResponsePair("2", "3"));
      return new Response(null, { status: 101, webSocket: client });
    }

    if (url.pathname.startsWith("/rest")) {
      return this.handleRest(url.pathname.slice("/rest".length), request);
    }
    return new Response("not found", { status: 404 });
  }

  // ---- REST ---------------------------------------------------------------

  private async handleRest(path: string, request: Request): Promise<Response> {
    let body: unknown[];
    try {
      body = await request.json();
      if (!Array.isArray(body)) return json({ error: "bad_args" }, 400);
    } catch {
      return json({ error: "bad_json" }, 400);
    }
    const userId = String(body[0] ?? "");
    const args = body.slice(1);

    switch (path) {
      // [RE] /user/* from rest_endpoints.txt — the login handshake REST part
      case "/user/login": {
        // args: [device_id?, npa_token?]; idempotent guest registration.
        const target = userId || String(args[0] ?? "");
        const row = await this.ensureUser(target);
        return json({ userId: row.user_id, authKey: row.auth_key, maintenance: false });
      }
      case "/user/getUserData": {
        // [RE] the big login payload — the original catalog rode this call
        // (era-typical server-driven balance; Item.cs schema is the contract)
        return json(await this.getUserData(userId));
      }
      case "/user/updateHistory":
        // [RE] HeartBeats consumes {success, seq, notice, entrustTurn/Battle}
        return json({ success: true, seq: 0, notice: "", entrustTurn: false, entrustBattle: false });

      // ---- item economy (schema: Item.cs 49-field contract) -------------
      case "/item/sellItems": {
        const [itemIds] = args as [number[]];
        if (!Array.isArray(itemIds)) return json({ error: "bad_payload" });
        const sold = await this.sellItems(userId, itemIds);
        return json(sold);
      }
      case "/item/craftItem": {
        const [itemId] = args as [number];
        const res = await this.buyItem(userId, Number(itemId));
        return json(res);
      }
      // ---- guild core (schema: /guild/* endpoints, 113 guild-family calls) --
      case "/guild/create": {
        const [name] = args as [string];
        const db = await this.migrate();
        if (!name || String(name).length > 24) return json({ error: "bad_name" });
        const existing = await db.prepare("SELECT guild_id FROM guild_members WHERE user_id = ?1").bind(userId).first();
        if (existing) return json({ error: "alreadyInGuild" });
        const created = await db.prepare(
          "INSERT INTO guilds (name, created_at) VALUES (?1, ?2) RETURNING guild_id")
          .bind(String(name), Date.now()).first<{ guild_id: number }>();
        await db.batch([
          db.prepare("INSERT INTO guild_members (guild_id, user_id, job, joined_at) VALUES (?1, ?2, 9, ?3)")  // [RE] 9 = guild master grade
            .bind(created!.guild_id, userId, Date.now()),
        ]);
        return json({ success: true, guildId: created!.guild_id });
      }
      case "/guild/get_me": {
        const db = await this.migrate();
        const me = await db.prepare(
          "SELECT g.guild_id, g.name, g.motd, g.emblem, m.job FROM guilds g JOIN guild_members m ON m.guild_id = g.guild_id WHERE m.user_id = ?1")
          .bind(userId).first();
        return json(me ?? { error: "noGuild" });
      }
      case "/guild/get_guild_list": {
        const db = await this.migrate();
        const list = await db.prepare(
          "SELECT g.guild_id, g.name, g.motd, (SELECT COUNT(*) FROM guild_members m WHERE m.guild_id = g.guild_id) AS members FROM guilds g ORDER BY members DESC LIMIT 50")
          .all();
        return json({ guilds: list.results ?? [] });
      }
      case "/guild/apply": {
        const [guildId] = args as [number];
        const db = await this.migrate();
        await db.prepare("INSERT OR IGNORE INTO guild_applicants (guild_id, user_id, applied_at) VALUES (?1, ?2, ?3)")
          .bind(Number(guildId), userId, Date.now()).run();
        return json({ success: true });
      }
      case "/guild/get_applicants": {
        const db = await this.migrate();
        const myGuild = await db.prepare("SELECT guild_id, job FROM guild_members WHERE user_id = ?1").bind(userId).first<{ guild_id: number; job: number }>();
        if (!myGuild || myGuild.job < 9 - 8) /* [RE] officer+ */ return json({ error: "noPermission" });
        const rows = await db.prepare("SELECT user_id, applied_at FROM guild_applicants WHERE guild_id = ?1").bind(myGuild.guild_id).all();
        return json({ applicants: rows.results ?? [] });
      }
      case "/guild/process_applicant": {
        const [applicantId, accept] = args as [string, number];
        const db = await this.migrate();
        const myGuild = await db.prepare("SELECT guild_id, job FROM guild_members WHERE user_id = ?1").bind(userId).first<{ guild_id: number; job: number }>();
        if (!myGuild || myGuild.job < 1) return json({ error: "noPermission" });
        if (accept) {
          await db.batch([
            db.prepare("INSERT OR IGNORE INTO guild_members (guild_id, user_id, job, joined_at) VALUES (?1, ?2, 1, ?3)")
              .bind(myGuild.guild_id, String(applicantId), Date.now()),
            db.prepare("DELETE FROM guild_applicants WHERE guild_id = ?1 AND user_id = ?2")
              .bind(myGuild.guild_id, String(applicantId)),
          ]);
        } else {
          await db.prepare("DELETE FROM guild_applicants WHERE guild_id = ?1 AND user_id = ?2")
            .bind(myGuild.guild_id, String(applicantId)).run();
        }
        return json({ success: true });
      }
      case "/guild/leave":
      case "/guild/kick": {
        const db = await this.migrate();
        const target = path === "/guild/kick" ? String(args[0]) : userId;
        const myGuild = await db.prepare("SELECT guild_id, job FROM guild_members WHERE user_id = ?1").bind(userId).first<{ guild_id: number; job: number }>();
        if (!myGuild) return json({ error: "noGuild" });
        if (path === "/guild/kick" && myGuild.job < 1) return json({ error: "noPermission" });
        const masterCount = await db.prepare("SELECT COUNT(*) AS c FROM guild_members WHERE guild_id = ?1 AND job = 9").bind(myGuild.guild_id).first<{ c: number }>();
        if (path === "/guild/leave" && target === userId && masterCount && masterCount.c === 1)
          return json({ error: "needGuildMasterSuccessor" });   // [RE] succeed_guild_master first
        await db.prepare("DELETE FROM guild_members WHERE guild_id = ?1 AND user_id = ?2")
          .bind(myGuild.guild_id, target).run();
        return json({ success: true });
      }
      case "/guild/set_motd": {
        const [motd] = args as [string];
        const db = await this.migrate();
        const myGuild = await db.prepare("SELECT guild_id, job FROM guild_members WHERE user_id = ?1").bind(userId).first<{ guild_id: number; job: number }>();
        if (!myGuild || myGuild.job < 1) return json({ error: "noPermission" });
        await db.prepare("UPDATE guilds SET motd = ?2 WHERE guild_id = ?1")
          .bind(myGuild.guild_id, String(motd ?? "").slice(0, 200)).run();
        return json({ success: true });
      }

      // ---- guild-war world sync (FlatBuffers snapshot, dsa-02 §1b) --------
      case "/guildWar/getWorldMetaData": {
        const db = await this.migrate();
        const worlds = await db.prepare("SELECT world_id, seq FROM world_seq ORDER BY world_id").all();
        return json({ worlds: worlds.results ?? [] });
      }
      case "/guildWar/getWorldData": {
        // [RE] binary FlatBuffers struct snapshot: corps + buildings rows.
        // original: RequestGetWorldData(clan_db_shard, world_id, from_seq,
        // to_seq, except_sequences) — sequence-window sync.
        const [clanDbShard, worldId, fromSeq] = args as [number, number, number];
        return await this.worldSnapshot(Number(worldId) || 1, Number(fromSeq) || 0);
      }
      case "/multiplay/getBattlePlayDataForContinue":
      case "/multiplay/getReplayData": {
        // [RE] the event-sourced battle log IS the replay data (dsa-07 §6):
        // controls recorded per battleId replay the full battle.
        const [battleId] = args as [string];
        const db = await this.migrate();
        const battle = await db.prepare(
          "SELECT battle_id, kind, ref_id, state, is_win, grade, occupation_pct, play_time_ms FROM battles WHERE battle_id = ?1")
          .bind(String(battleId ?? "")).first();
        if (!battle) return json({ error: "notFoundBattleId" });
        return json({ battle: battle, controls: [] });
      }
      // ---- multiplay matchmaking (MultiplayManager decompile) -------------
      // [RE] ConnectToMultiplayServer: REST /multiplay/getServerInfo first —
      // the matchmaker hands out the game-server endpoint; then socket room
      // events (want_room_id / room_id / notFoundRoomId per event catalog).
      case "/multiplay/getServerInfo": {
        await this.migrate();
        // [RE] rebuild: single global DO — return our own WS route info
        return json({
          socketServerUuid: "local",
          socketServerIP: "ws://localhost:8787/socket.io/?EIO=4&transport=websocket",
          logicServerUuid: "local",
          state: 0,
          maintenance: false,
          userCnt: this.sessions.size,
        });
      }
      case "/multiplay/registerSubscribingRooms": {
        // [RE] socket-side registerSubscribingRooms event also exists; REST
        // variant acks with the current room roster.
        return json({ success: true, rooms: [] });
      }
      // ---- battle flow (event-sourced per dsa-07) ------------------------
      // [RE] payload shapes decompiled from FinishScenario/FinishSiege
      // iterators: scenarioBattleEnd {scenarioId,isWin,victoryGrade,
      // modeLevel,battlePlayTime}; multi {townId,opponentId,unitId,isWin,
      // occupationPct}. Every call appends to the battles log table.
      case "/battle/scenarioBattleStart": {
        await this.migrate();
        const [scenarioId] = args as [number];
        const battleId = sid(12);
        await (this.env as Env).DB
          .prepare("INSERT INTO battles (battle_id, user_id, kind, ref_id, state, opened_at) VALUES (?1, ?2, 'scenario', ?3, 'open', ?4)")
          .bind(battleId, userId, Number(scenarioId) || 0, Date.now()).run();
        return json({ success: true, battleId });
      }
      case "/battle/scenarioBattleEnd": {
        const [scenarioId, isWin, victoryGrade, modeLevel, battlePlayTime] = args as [number, number, number, number, number];
        const now = Date.now();
        const stmts: D1PreparedStatement[] = [
          (this.env as Env).DB.prepare(
            "UPDATE battles SET state = 'done', is_win = ?2, grade = ?3, play_time_ms = ?4, closed_at = ?5 WHERE user_id = ?1 AND kind = 'scenario' AND state = 'open'")
            .bind(userId, isWin ? 1 : 0, victoryGrade ?? 0, battlePlayTime ?? 0, now),
          // [RE] progress: cleared scenario unlocks next; append-only progress log
          (this.env as Env).DB.prepare(
            "INSERT OR REPLACE INTO progress (user_id, key, value, updated_at) VALUES (?1, ?2, ?3, ?4)")
            .bind(userId, "scenario:" + (scenarioId ?? 0) + ":clear", isWin ? "1" : "0", now),
          isWin ? (this.env as Env).DB.prepare(
            "UPDATE wallets SET gold = gold + ?2 WHERE user_id = ?1").bind(userId, 250 + (victoryGrade ?? 0) * 100)
            : (this.env as Env).DB.prepare("SELECT 1").bind(),
        ];
        await (this.env as Env).DB.batch(stmts.filter(s => s));
        return json({ success: true, rewardGold: isWin ? 250 + (victoryGrade ?? 0) * 100 : 0 });
      }
      case "/battle/multiBattleStart": {
        await this.migrate();
        const battleId = sid(12);
        await (this.env as Env).DB
          .prepare("INSERT INTO battles (battle_id, user_id, kind, ref_id, state, opened_at) VALUES (?1, ?2, 'multi', ?3, 'open', ?4)")
          .bind(battleId, userId, String(args[0] ?? ""), Date.now()).run();
        return json({ success: true, battleId });
      }
      case "/battle/multiBattleEnd": {
        const [townId, opponentId, unitId, isWin, occupationPct] = args as [string, number, number, number, number];
        await (this.env as Env).DB
          .prepare("UPDATE battles SET state = 'done', is_win = ?2, occupation_pct = ?3, closed_at = ?4 WHERE user_id = ?1 AND kind = 'multi' AND state = 'open'")
          .bind(userId, isWin ? 1 : 0, occupationPct ?? 0, Date.now()).run();
        return json({ success: true });
      }
      case "/item/lock": {
        const [itemId, locked] = args as [number, number];
        await (this.env as Env).DB
          .prepare("UPDATE inventory SET locked = ?2 WHERE user_id = ?1 AND item_id = ?3")
          .bind(userId, locked ? 1 : 0, Number(itemId)).run();
        return json({ success: true });
      }
      default:
        // [RE] every recovered endpoint lands here in later milestones
        // (guild/, arena/, battle/...); explicit 404 keeps the contract honest.
        return new Response("not implemented: " + path, { status: 404 });
    }
  }

  // [RE] self-migration: schema + catalog seed on first use — dev CLI and
  // deployed Workers each get their own D1 storage, so the server carries
  // its schema (the catalog is embedded from seed/items.json at build time).
  private async migrate(): Promise<D1Database> {
    const db = (this.env as Env).DB;
    if (this.migrated) return db;
    // [RE] repair: dev D1 may predate a column; recreate stale tables.
    // (Deployed DBs are always fresh; ALTER-based migration lands later.)
    try {
      const invCols = await db.prepare("PRAGMA table_info(inventory)").all<{ name: string }>();
      if (invCols.results && !invCols.results.some(c => c.name === "locked")) {
        await db.batch([
          db.prepare("DROP TABLE IF EXISTS inventory"),
          db.prepare("DROP TABLE IF EXISTS wallets"),
          db.prepare("DROP TABLE IF EXISTS items"),
        ]);
      }
    } catch { /* fresh db */ }
    await db.batch([
      db.prepare(`CREATE TABLE IF NOT EXISTS players (user_id TEXT PRIMARY KEY, auth_key TEXT NOT NULL, created_at INTEGER NOT NULL, last_seen INTEGER)`),
      db.prepare(`CREATE TABLE IF NOT EXISTS inventory (user_id TEXT NOT NULL, item_id INTEGER NOT NULL, count INTEGER NOT NULL DEFAULT 1, tier INTEGER NOT NULL DEFAULT 0, locked INTEGER NOT NULL DEFAULT 0, acquired_at INTEGER NOT NULL, PRIMARY KEY (user_id, item_id))`),
      db.prepare(`CREATE TABLE IF NOT EXISTS wallets (user_id TEXT PRIMARY KEY, gold INTEGER NOT NULL DEFAULT 0, cash INTEGER NOT NULL DEFAULT 0)`),
      db.prepare(`CREATE TABLE IF NOT EXISTS battles (battle_id TEXT PRIMARY KEY, user_id TEXT NOT NULL, kind TEXT NOT NULL, ref_id TEXT, state TEXT NOT NULL, is_win INTEGER, grade INTEGER, occupation_pct INTEGER, play_time_ms INTEGER, opened_at INTEGER NOT NULL, closed_at INTEGER)`),
      db.prepare(`CREATE TABLE IF NOT EXISTS guilds (guild_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL UNIQUE, motd TEXT NOT NULL DEFAULT '', emblem INTEGER NOT NULL DEFAULT 0, join_config INTEGER NOT NULL DEFAULT 0, created_at INTEGER NOT NULL)`),
      db.prepare(`CREATE TABLE IF NOT EXISTS guild_members (guild_id INTEGER NOT NULL, user_id TEXT NOT NULL, job INTEGER NOT NULL DEFAULT 0, joined_at INTEGER NOT NULL, PRIMARY KEY (guild_id, user_id))`),
      db.prepare(`CREATE TABLE IF NOT EXISTS guild_applicants (guild_id INTEGER NOT NULL, user_id TEXT NOT NULL, applied_at INTEGER NOT NULL, PRIMARY KEY (guild_id, user_id))`),
      db.prepare(`CREATE TABLE IF NOT EXISTS world_corps (world_id INTEGER NOT NULL, corps_id INTEGER NOT NULL, guild_id INTEGER NOT NULL, pos_x INTEGER NOT NULL, pos_y INTEGER NOT NULL, face_unit_id INTEGER NOT NULL DEFAULT 0, job_type INTEGER NOT NULL DEFAULT 0, military_power INTEGER NOT NULL DEFAULT 0, updated_seq INTEGER NOT NULL, PRIMARY KEY (world_id, corps_id))`),
      db.prepare(`CREATE TABLE IF NOT EXISTS world_buildings (world_id INTEGER NOT NULL, place_id INTEGER NOT NULL, kind INTEGER NOT NULL DEFAULT 0, guild_id INTEGER NOT NULL DEFAULT 0, updated_seq INTEGER NOT NULL, PRIMARY KEY (world_id, place_id))`),
      db.prepare(`CREATE TABLE IF NOT EXISTS world_seq (world_id INTEGER PRIMARY KEY, seq INTEGER NOT NULL DEFAULT 0)`),
      db.prepare(`CREATE TABLE IF NOT EXISTS items (item_id INTEGER PRIMARY KEY, name TEXT NOT NULL, tier INTEGER NOT NULL DEFAULT 0, slot INTEGER NOT NULL DEFAULT 0, item_type INTEGER NOT NULL DEFAULT 0, box_type INTEGER NOT NULL DEFAULT 0, price INTEGER NOT NULL DEFAULT 0, gold INTEGER NOT NULL DEFAULT 0, item_effect INTEGER NOT NULL DEFAULT 0, effect_value INTEGER NOT NULL DEFAULT 0, max_value INTEGER NOT NULL DEFAULT 0, type_value INTEGER NOT NULL DEFAULT 0, pay_type INTEGER NOT NULL DEFAULT 0, atk INTEGER DEFAULT 0, atk_up INTEGER DEFAULT 0, def INTEGER DEFAULT 0, def_up INTEGER DEFAULT 0, inte INTEGER DEFAULT 0, inte_up INTEGER DEFAULT 0, agi INTEGER DEFAULT 0, agi_up INTEGER DEFAULT 0, mor INTEGER DEFAULT 0, mor_up INTEGER DEFAULT 0, hp INTEGER DEFAULT 0, hp_up INTEGER DEFAULT 0, mp INTEGER DEFAULT 0, mp_up INTEGER DEFAULT 0, max_lv INTEGER NOT NULL DEFAULT 1, target_type INTEGER NOT NULL DEFAULT 0, skill_id INTEGER NOT NULL DEFAULT 0, unit_id INTEGER NOT NULL DEFAULT 0, atlas TEXT DEFAULT '', icon TEXT DEFAULT '', country INTEGER NOT NULL DEFAULT 0, job_type INTEGER NOT NULL DEFAULT 0)`),
    ]);
    const count = await db.prepare("SELECT COUNT(*) AS c FROM items").first<{ c: number }>();
    if (!count || count.c === 0) {
      const stmts = CATALOG.map(it =>
        db.prepare(`INSERT OR REPLACE INTO items (item_id,name,tier,slot,item_type,box_type,price,gold,item_effect,effect_value,max_value,type_value,pay_type,atk,atk_up,def,def_up,inte,inte_up,agi,agi_up,mor,mor_up,hp,hp_up,mp,mp_up,max_lv,target_type,skill_id,unit_id,atlas,icon,country,job_type)
          VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28,?29,?30,?31,?32,?33,?34,?35)`)
          .bind(it.item_id, it.name, it.tier ?? 0, it.slot ?? 0, it.item_type ?? 0, it.box_type ?? 0,
                it.price ?? 0, it.gold ?? 0,
                it.item_effect ?? 0, it.effect_value ?? 0, it.max_value ?? 0, it.type_value ?? 0, it.pay_type ?? 0,
                it.atk ?? 0, it.atk_up ?? 0, it.def ?? 0, it.def_up ?? 0, it.inte ?? 0, it.inte_up ?? 0,
                it.agi ?? 0, it.agi_up ?? 0, it.mor ?? 0, it.mor_up ?? 0, it.hp ?? 0, it.hp_up ?? 0,
                it.mp ?? 0, it.mp_up ?? 0, it.max_lv ?? 1, it.target_type ?? 0, it.skill_id ?? 0,
                it.unit_id ?? 0, it.atlas ?? "", it.icon ?? "", it.country ?? 0, it.job_type ?? 0));
      await db.batch(stmts);
    }
    this.migrated = true;
    return db;
  }

  // [RE] world snapshot — REBUILD contract (the original's key_* structs are
  // id-only lookup keys; data rows rode the ClientDataType payloads). Row
  // layout: [corps_id][guild_id][pos_x][pos_y][face][job][power]
  // little-endian, 28 bytes; buildings 16 bytes. Binary content-type.
  private async worldSnapshot(worldId: number, fromSeq: number): Promise<Response> {
    const db = await this.migrate();
    const corps = await db.prepare(
      "SELECT corps_id, guild_id, pos_x, pos_y, face_unit_id, job_type, military_power FROM world_corps WHERE world_id = ?1 AND updated_seq > ?2 ORDER BY corps_id")
      .bind(worldId, fromSeq).all<{ corps_id: number; guild_id: number; pos_x: number; pos_y: number; face_unit_id: number; job_type: number; military_power: number }>();
    const buildings = await db.prepare(
      "SELECT place_id, kind, guild_id FROM world_buildings WHERE world_id = ?1 AND updated_seq > ?2 ORDER BY place_id")
      .bind(worldId, fromSeq).all<{ place_id: number; kind: number; guild_id: number }>();
    const seqRow = await db.prepare("SELECT seq FROM world_seq WHERE world_id = ?1").bind(worldId).first<{ seq: number }>();

    const corpsRow = (r: { corps_id: number; guild_id: number; pos_x: number; pos_y: number; face_unit_id: number; job_type: number; military_power: number }) => {
      // [RE] key_corps struct field order (from the .fbs + decompiled struct layout)
      const b = new ArrayBuffer(28);
      const v = new DataView(b);
      v.setInt32(0, r.corps_id, true);
      v.setInt32(4, r.guild_id, true);
      v.setInt32(8, r.pos_x, true);
      v.setInt32(12, r.pos_y, true);
      v.setInt32(16, r.face_unit_id, true);
      v.setInt32(20, r.job_type, true);
      v.setInt32(24, r.military_power, true);
      return new Uint8Array(b);
    };
    const buildingRow = (r: { place_id: number; kind: number; guild_id: number }) => {
      const b = new ArrayBuffer(12);
      const v = new DataView(b);
      v.setInt32(0, r.place_id, true);
      v.setInt32(4, r.kind, true);
      v.setInt32(8, r.guild_id, true);
      return new Uint8Array(b);
    };

    // frame: [magic "CWLD"][worldId i32][seq i32][corpsCount i32][rows...][bldCount i32][rows...]
    const cc = corps.results?.length ?? 0, bc = buildings.results?.length ?? 0;
    const header = new ArrayBuffer(20);
    const hv = new DataView(header);
    hv.setInt32(0, 0x444C5743, true);          // "CWLD"
    hv.setInt32(4, worldId, true);
    hv.setInt32(8, seqRow?.seq ?? 0, true);
    hv.setInt32(12, cc, true);
    hv.setInt32(16, bc, true);
    const parts: Uint8Array[] = [new Uint8Array(header)];
    for (const r of corps.results ?? []) parts.push(corpsRow(r));
    for (const r of buildings.results ?? []) parts.push(buildingRow(r));
    const total = parts.reduce((s, p) => s + p.length, 0);
    const out = new Uint8Array(total);
    let off = 0;
    for (const p of parts) { out.set(p, off); off += p.length; }
    return new Response(out, {
      headers: { "content-type": "application/octet-stream" },
    });
  }

  private async ensureUser(userId: string) {
    const db = await this.migrate();
    if (userId) {
      const found = await db
        .prepare("SELECT user_id, auth_key FROM players WHERE user_id = ?1")
        .bind(userId).first<{ user_id: string; auth_key: string }>();
      if (found) {
        await this.ensureWallet(userId);
        return found;
      }
    }
    const newId = userId || sid(8);
    const authKey = sid(24);
    await db.batch([
      db.prepare(
        "INSERT INTO players (user_id, auth_key, created_at) VALUES (?1, ?2, ?3)")
        .bind(newId, authKey, Date.now()),
      db.prepare("INSERT OR IGNORE INTO wallets (user_id, gold, cash) VALUES (?1, 1000, 0)")
        .bind(newId),
      // [RE] starter kit: iron sword + leather armor + 3 potions
      db.prepare("INSERT OR IGNORE INTO inventory (user_id, item_id, count, acquired_at) VALUES (?1, 10001, 1, ?2)").bind(newId, Date.now()),
      db.prepare("INSERT OR IGNORE INTO inventory (user_id, item_id, count, acquired_at) VALUES (?1, 10041, 1, ?2)").bind(newId, Date.now()),
      db.prepare("INSERT OR IGNORE INTO inventory (user_id, item_id, count, acquired_at) VALUES (?1, 10061, 3, ?2)").bind(newId, Date.now()),
    ]);
    return { user_id: newId, auth_key: authKey };
  }

  private async ensureWallet(userId: string) {
    const db = await this.migrate();
    await db
      .prepare("INSERT OR IGNORE INTO wallets (user_id, gold, cash) VALUES (?1, 1000, 0)")
      .bind(userId).run();
  }

  private async getUserData(userId: string) {
    const db = await this.migrate();
    const row = await db.prepare(
      "SELECT user_id, created_at FROM players WHERE user_id = ?1")
      .bind(userId).first<{ user_id: string; created_at: number }>();
    if (!row) return { error: "notFoundUserId" };   // [RE] literal exists in metadata
    await this.ensureWallet(userId);
    const wallet = await db.prepare("SELECT gold, cash FROM wallets WHERE user_id = ?1")
      .bind(userId).first<{ gold: number; cash: number }>();
    const inventory = await db.prepare(
      "SELECT item_id, count, tier, locked FROM inventory WHERE user_id = ?1")
      .bind(userId).all<{ item_id: number; count: number; tier: number; locked: number }>();
    const catalog = await db.prepare(
      "SELECT * FROM items ORDER BY item_id").all();
    return {
      userId: row.user_id,
      createdAt: row.created_at,
      gold: wallet?.gold ?? 0,
      cash: wallet?.cash ?? 0,
      inventory: inventory.results ?? [],
      catalog: catalog.results ?? [],
      serverTime: Date.now(),
    };
  }

  private async sellItems(userId: string, itemIds: number[]) {
    const db = await this.migrate();
    await this.ensureWallet(userId);
    let goldEarned = 0;
    const stmts: D1PreparedStatement[] = [];
    for (const id of itemIds) {
      const item = await db.prepare("SELECT gold FROM items WHERE item_id = ?1")
        .bind(id).first<{ gold: number }>();
      const owned = await db.prepare(
        "SELECT count, locked FROM inventory WHERE user_id = ?1 AND item_id = ?2")
        .bind(userId, id).first<{ count: number; locked: number }>();
      if (!owned || owned.locked) continue;
      const quarter = Math.floor((item?.gold ?? 0) / 4) || 1;   // [RE] 25% resale
      goldEarned += quarter;
      const remaining = owned.count - 1;
      if (remaining <= 0)
        stmts.push(db.prepare("DELETE FROM inventory WHERE user_id = ?1 AND item_id = ?2").bind(userId, id));
      else
        stmts.push(db.prepare("UPDATE inventory SET count = ?3 WHERE user_id = ?1 AND item_id = ?2").bind(userId, id, remaining));
    }
    if (goldEarned > 0)
      stmts.push(db.prepare("UPDATE wallets SET gold = gold + ?2 WHERE user_id = ?1").bind(userId, goldEarned));
    if (stmts.length > 0) await db.batch(stmts);
    return { success: true, goldEarned };
  }

  private async buyItem(userId: string, itemId: number) {
    const db = await this.migrate();
    await this.ensureWallet(userId);
    const item = await db.prepare("SELECT gold, price FROM items WHERE item_id = ?1")
      .bind(itemId).first<{ gold: number; price: number }>();
    if (!item) return { error: "notFoundItemId" };
    const cost = item.gold > 0 ? item.gold : item.price;   // [RE] gold vs cash
    const wallet = await db.prepare("SELECT gold, cash FROM wallets WHERE user_id = ?1")
      .bind(userId).first<{ gold: number; cash: number }>();
    if (!wallet || wallet.gold < cost) return { error: "notEnoughGold" };
    const owned = await db.prepare(
      "SELECT count FROM inventory WHERE user_id = ?1 AND item_id = ?2")
      .bind(userId, itemId).first<{ count: number }>();
    const stmts = [
      db.prepare("UPDATE wallets SET gold = gold - ?2 WHERE user_id = ?1").bind(userId, cost),
      owned
        ? db.prepare("UPDATE inventory SET count = count + 1 WHERE user_id = ?1 AND item_id = ?2").bind(userId, itemId)
        : db.prepare("INSERT INTO inventory (user_id, item_id, count, acquired_at) VALUES (?1, ?2, 1, ?3)").bind(userId, itemId, Date.now()),
    ];
    await db.batch(stmts);
    return { success: true, itemId, cost };
  }

  // ---- WebSocket / socket.io ----------------------------------------------

  override async webSocketMessage(ws: WebSocket, message: string | ArrayBuffer): Promise<void> {
    console.log("[do] ws msg:", typeof message, String(message).slice(0, 60));
    if (typeof message !== "string") return;
    const frame = decodeFrame(message);
    if (!frame) return;

    switch (frame.type) {
      case "ping": ws.send("3"); break;             // edge auto-response may cover this
      case "message": {
        const pkt = decodeIo(frame.data);
        if (pkt) await this.handleIo(ws, pkt);
        break;
      }
      case "close": this.drop(ws); break;
    }
  }

  override async webSocketClose(ws: WebSocket): Promise<void> {
    console.log("[do] ws close");
    const s = this.sessions.get(ws);
    if (!s) return;
    for (const room of s.rooms) this.broadcastTo(room, event("playerLeft", { sid: s.sid }), ws);
    this.drop(ws);
  }

  private drop(ws: WebSocket): void {
    this.sessions.delete(ws);
  }

  private async handleIo(ws: WebSocket, pkt: IoPacket): Promise<void> {
    console.log("[do] io packet:", pkt.kind, pkt.eventName ?? "");
    const s = this.sessions.get(ws);
    if (!s) { console.log("[do] NO SESSION for this ws!"); return; }

    switch (pkt.kind) {
      case "connect":
        ws.send(connectAck({ sid: s.sid }));
        break;

      case "event":
        try {
          await this.handleEvent(ws, s, pkt.eventName ?? "", pkt.args, pkt.ackId);
        } catch (e) {
          // [RE] SessionImpl emits "error" on protocol failures
          ws.send(event("error", { message: String(e) }));
        }
        break;

      case "ack":
      case "disconnect":
      case "connectError":
        break;
    }
  }

  private async handleEvent(
    ws: WebSocket, s: Session, name: string, args: unknown[], ackId?: number,
  ): Promise<void> {
    const payload = args[0] ?? {};
    const reply = (ackData: unknown[]) =>
      ackId !== undefined ? ws.send(ioAck(ackId, ackData)) : ws.send(event(name, ...ackData));

    switch (name) {
      // [RE] SessionImpl$$UpdateSocketLogin: ["login", {userId, authKey}]
      case "login": {
        const p = LoginPayload.safeParse(payload);
        if (!p.success) {
          console.log("[do] login bad_payload:", JSON.stringify(payload).slice(0, 120));
          return reply([{ error: "bad_payload" }]);
        }
        s.userId = String(p.data.userId);
        s.authKey = p.data.authKey;
        const db = await this.migrate();
        const row = await db.prepare("SELECT auth_key FROM players WHERE user_id = ?1")
          .bind(s.userId).first<{ auth_key: string }>();
        if (!row) console.log("[do] login authFail: no user", s.userId);
        else if (row.auth_key !== p.data.authKey) console.log("[do] login authFail: key mismatch");
        if (!row || row.auth_key !== p.data.authKey) {
          return reply([{ error: "authFail" }]);
        }
        s.logined = true;
        return reply([{ userId: s.userId }]);          // → OnSocketLogined path
      }

      // [RE] SessionImpl$$EnterSocketServerWorldRoom
      case "enterToWorld": {
        if (!s.logined) return reply([{ error: "notLogined" }]);
        const p = EnterWorldPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = roomName(p.data);
        s.rooms.add(room);
        this.broadcastTo(room, event("userJoined", { userId: s.userId }), ws);
        return reply([{ room_id: room, world_id: p.data.world_id ?? 0 }]);
      }

      // [RE] matchmaking: want_room_id / room lifecycle (MultiplayManager)
      case "want_room_id": {
        const p = WantRoomPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = `${p.data.room_type}:${sid(6)}`;
        s.rooms.add(room);
        return reply([{ room_id: room, room_type: p.data.room_type }]);
      }

      case "leaveRoom": {
        const roomId = String((payload as { room_id?: string })?.room_id ?? "");
        s.rooms.delete(roomId);
        this.broadcastTo(roomId, event("playerLeft", { userId: s.userId }), ws);
        return reply([{ room_id: roomId }]);
      }

      // [RE] Chat.Chatting$$EmitChattingMessage → broadcastTo channel
      case "broadcastTo": {
        const p = BroadcastPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = p.data.room_id ?? "";
        this.broadcastTo(room, event("chattingMessage", {
          type: p.data.type ?? 0, subType: p.data.sub_type ?? 0,
          msg: p.data.msg, userId: s.userId,
        }));
        return reply([{ success: true }]);
      }

      // [RE] world history sync (RequestGetWorldData params)
      case "updateHistory":
        return reply([{ success: true, seq: 0 }]);

      default:
        // Unknown events: [RE] original SessionImpl → "error" envelope
        return reply([{ error: "notFoundEventId", event: name }]);
    }
  }

  private broadcastTo(room: string, text: string, except?: WebSocket): void {
    for (const [ws, sess] of this.sessions) {
      if (ws !== except && sess.rooms.has(room)) ws.send(text);
    }
  }
}

function roomName(p: { world_id?: number; guild_id?: number; room_type?: string }): string {
  if (p.guild_id !== undefined) return `guild:${p.guild_id}`;
  if (p.world_id !== undefined) return `world:${p.world_id}`;
  return `${p.room_type ?? "default"}:global`;
}

function json(obj: unknown, status = 200): Response {
  return new Response(JSON.stringify(obj), {
    status,
    headers: { "content-type": "application/json", ...CORS },
  });
}
