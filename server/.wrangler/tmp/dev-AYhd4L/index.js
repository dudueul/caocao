var __defProp = Object.defineProperty;
var __name = (target, value) => __defProp(target, "name", { value, configurable: true });

// src/index.ts
import { DurableObject } from "cloudflare:workers";

// src/engineio.ts
var EIO = {
  open: "0",
  ping: "2",
  pong: "3",
  message: "4",
  close: "6"
};
function encodeOpen(h) {
  return EIO.open + JSON.stringify(h);
}
__name(encodeOpen, "encodeOpen");
function decodeFrame(frame) {
  if (frame.length === 0) return null;
  const t = frame[0];
  const data = frame.slice(1);
  switch (t) {
    case EIO.open:
      return { type: "open", data };
    case EIO.ping:
      return { type: "ping", data };
    case EIO.pong:
      return { type: "pong", data };
    case EIO.message:
      return { type: "message", data };
    case EIO.close:
      return { type: "close", data };
    default:
      return null;
  }
}
__name(decodeFrame, "decodeFrame");
function sid(len = 16) {
  const bytes = new Uint8Array(len);
  crypto.getRandomValues(bytes);
  return Array.from(bytes, (b) => b.toString(16).padStart(2, "0")).join("");
}
__name(sid, "sid");

// src/socketio.ts
var SIO = {
  connect: "0",
  disconnect: "1",
  event: "2",
  ack: "3",
  connectError: "4"
};
function wrapIo(inner) {
  return "4" + inner;
}
__name(wrapIo, "wrapIo");
function connectAck(data) {
  return wrapIo(SIO.connect + JSON.stringify(data));
}
__name(connectAck, "connectAck");
function event(name, ...args) {
  return wrapIo(SIO.event + JSON.stringify([name, ...args]));
}
__name(event, "event");
function decodeIo(data) {
  if (!data) return null;
  let i = 0;
  if (data[0] === "/") {
    const comma = data.indexOf(",");
    if (comma < 0) return null;
    if (data.slice(0, comma) !== "/") return null;
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
      let ackId;
      if (p === SIO.ack) {
        const m = /^(\d+)(.*)$/s.exec(body);
        if (!m) return null;
        ackId = parseInt(m[1], 10);
        body = m[2];
      } else if (/^\d+#/.test(body)) {
        const m = /^(\d+)#(.*)$/s.exec(body);
        ackId = parseInt(m[1], 10);
        body = m[2];
      }
      const arr = body ? JSON.parse(body) : [];
      return {
        kind: p === SIO.ack ? "ack" : "event",
        ackId,
        eventName: typeof arr[0] === "string" ? arr[0] : void 0,
        args: arr.slice(1)
      };
    }
    case SIO.connectError:
      return { kind: "connectError", args: [body] };
    default:
      return null;
  }
}
__name(decodeIo, "decodeIo");

// src/index.ts
var HS = {
  pingInterval: 25e3,
  // [RE] original client field default (SocketIOComponent)
  pingTimeout: 2e4,
  maxPayload: 1e6
};
var src_default = {
  async fetch(request, env) {
    const url = new URL(request.url);
    if (url.pathname === "/socket.io/" && request.headers.get("Upgrade") === "websocket") {
      const id = env.GAME_SERVER.idFromName("global");
      const stub = env.GAME_SERVER.get(id);
      return stub.fetch(request);
    }
    if (url.pathname === "/healthz") return new Response("ok");
    return new Response("not found", { status: 404 });
  }
};
var GameServer = class extends DurableObject {
  static {
    __name(this, "GameServer");
  }
  async fetch(request) {
    const pair = new WebSocketPair();
    const [client, server] = Object.values(pair);
    this.ctx.acceptWebSocket(server);
    const session = sid();
    server.send(encodeOpen({ ...HS, sid: session }));
    this.ctx.setWebSocketAutoResponse(
      new WebSocketRequestResponsePair("2", "3")
    );
    this.sessions.set(server, session);
    return new Response(null, { status: 101, webSocket: client });
  }
  sessions = /* @__PURE__ */ new Map();
  async webSocketMessage(ws, message) {
    if (typeof message !== "string") return;
    const frame = decodeFrame(message);
    if (!frame) return;
    switch (frame.type) {
      case "ping":
        ws.send("3");
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
  async webSocketClose(ws) {
    const s = this.sessions.get(ws);
    if (s) {
      this.sessions.delete(ws);
      this.broadcast(event("playerLeft", { sid: s }), ws);
    }
  }
  async handleIo(ws, pkt) {
    switch (pkt.kind) {
      case "connect":
        ws.send(connectAck({ sid: this.sessions.get(ws) }));
        break;
      case "event":
        ws.send(event("serverEcho", pkt.eventName, pkt.args));
        break;
      case "ack":
        break;
    }
  }
  broadcast(text, except) {
    for (const ws of this.ctx.getWebSockets()) {
      if (ws !== except) ws.send(text);
    }
  }
};

// ../../../.local/share/fnm/node-versions/v26.2.0/installation/lib/node_modules/wrangler/templates/middleware/middleware-ensure-req-body-drained.ts
var drainBody = /* @__PURE__ */ __name(async (request, env, _ctx, middlewareCtx) => {
  try {
    return await middlewareCtx.next(request, env);
  } finally {
    try {
      if (request.body !== null && !request.bodyUsed) {
        const reader = request.body.getReader();
        while (!(await reader.read()).done) {
        }
      }
    } catch (e) {
      console.error("Failed to drain the unused request body.", e);
    }
  }
}, "drainBody");
var middleware_ensure_req_body_drained_default = drainBody;

// ../../../.local/share/fnm/node-versions/v26.2.0/installation/lib/node_modules/wrangler/templates/middleware/middleware-miniflare3-json-error.ts
function reduceError(e) {
  return {
    name: e?.name,
    message: e?.message ?? String(e),
    stack: e?.stack,
    cause: e?.cause === void 0 ? void 0 : reduceError(e.cause)
  };
}
__name(reduceError, "reduceError");
var jsonError = /* @__PURE__ */ __name(async (request, env, _ctx, middlewareCtx) => {
  try {
    return await middlewareCtx.next(request, env);
  } catch (e) {
    const error = reduceError(e);
    const body = JSON.stringify(error);
    const headers = {
      "Content-Type": "application/json",
      "MF-Experimental-Error-Stack": "true"
    };
    const encoded = encodeURIComponent(body);
    if (encoded.length <= 8192) {
      headers["MF-Experimental-Error-Stack-Payload"] = encoded;
    }
    return new Response(body, { status: 500, headers });
  }
}, "jsonError");
var middleware_miniflare3_json_error_default = jsonError;

// .wrangler/tmp/bundle-UwFTxn/middleware-insertion-facade.js
var __INTERNAL_WRANGLER_MIDDLEWARE__ = [
  middleware_ensure_req_body_drained_default,
  middleware_miniflare3_json_error_default
];
var middleware_insertion_facade_default = src_default;

// ../../../.local/share/fnm/node-versions/v26.2.0/installation/lib/node_modules/wrangler/templates/middleware/common.ts
var __facade_middleware__ = [];
function __facade_register__(...args) {
  __facade_middleware__.push(...args.flat());
}
__name(__facade_register__, "__facade_register__");
function __facade_invokeChain__(request, env, ctx, dispatch, middlewareChain) {
  const [head, ...tail] = middlewareChain;
  const middlewareCtx = {
    dispatch,
    next(newRequest, newEnv) {
      return __facade_invokeChain__(newRequest, newEnv, ctx, dispatch, tail);
    }
  };
  return head(request, env, ctx, middlewareCtx);
}
__name(__facade_invokeChain__, "__facade_invokeChain__");
function __facade_invoke__(request, env, ctx, dispatch, finalMiddleware) {
  return __facade_invokeChain__(request, env, ctx, dispatch, [
    ...__facade_middleware__,
    finalMiddleware
  ]);
}
__name(__facade_invoke__, "__facade_invoke__");

// .wrangler/tmp/bundle-UwFTxn/middleware-loader.entry.ts
var __Facade_ScheduledController__ = class ___Facade_ScheduledController__ {
  constructor(scheduledTime, cron, noRetry) {
    this.scheduledTime = scheduledTime;
    this.cron = cron;
    this.#noRetry = noRetry;
  }
  scheduledTime;
  cron;
  static {
    __name(this, "__Facade_ScheduledController__");
  }
  #noRetry;
  noRetry() {
    if (!(this instanceof ___Facade_ScheduledController__)) {
      throw new TypeError("Illegal invocation");
    }
    this.#noRetry();
  }
};
function wrapExportedHandler(worker) {
  if (__INTERNAL_WRANGLER_MIDDLEWARE__ === void 0 || __INTERNAL_WRANGLER_MIDDLEWARE__.length === 0) {
    return worker;
  }
  for (const middleware of __INTERNAL_WRANGLER_MIDDLEWARE__) {
    __facade_register__(middleware);
  }
  const fetchDispatcher = /* @__PURE__ */ __name(function(request, env, ctx) {
    if (worker.fetch === void 0) {
      throw new Error("Handler does not export a fetch() function.");
    }
    return worker.fetch(request, env, ctx);
  }, "fetchDispatcher");
  return {
    ...worker,
    fetch(request, env, ctx) {
      const dispatcher = /* @__PURE__ */ __name(function(type, init) {
        if (type === "scheduled" && worker.scheduled !== void 0) {
          const controller = new __Facade_ScheduledController__(
            Date.now(),
            init.cron ?? "",
            () => {
            }
          );
          return worker.scheduled(controller, env, ctx);
        }
      }, "dispatcher");
      return __facade_invoke__(request, env, ctx, dispatcher, fetchDispatcher);
    }
  };
}
__name(wrapExportedHandler, "wrapExportedHandler");
function wrapWorkerEntrypoint(klass) {
  if (__INTERNAL_WRANGLER_MIDDLEWARE__ === void 0 || __INTERNAL_WRANGLER_MIDDLEWARE__.length === 0) {
    return klass;
  }
  for (const middleware of __INTERNAL_WRANGLER_MIDDLEWARE__) {
    __facade_register__(middleware);
  }
  return class extends klass {
    #fetchDispatcher = /* @__PURE__ */ __name((request, env, ctx) => {
      this.env = env;
      this.ctx = ctx;
      if (super.fetch === void 0) {
        throw new Error("Entrypoint class does not define a fetch() function.");
      }
      return super.fetch(request);
    }, "#fetchDispatcher");
    #dispatcher = /* @__PURE__ */ __name((type, init) => {
      if (type === "scheduled" && super.scheduled !== void 0) {
        const controller = new __Facade_ScheduledController__(
          Date.now(),
          init.cron ?? "",
          () => {
          }
        );
        return super.scheduled(controller);
      }
    }, "#dispatcher");
    fetch(request) {
      return __facade_invoke__(
        request,
        this.env,
        this.ctx,
        this.#dispatcher,
        this.#fetchDispatcher
      );
    }
  };
}
__name(wrapWorkerEntrypoint, "wrapWorkerEntrypoint");
var WRAPPED_ENTRY;
if (typeof middleware_insertion_facade_default === "object") {
  WRAPPED_ENTRY = wrapExportedHandler(middleware_insertion_facade_default);
} else if (typeof middleware_insertion_facade_default === "function") {
  WRAPPED_ENTRY = wrapWorkerEntrypoint(middleware_insertion_facade_default);
}
var middleware_loader_entry_default = WRAPPED_ENTRY;
export {
  GameServer,
  __INTERNAL_WRANGLER_MIDDLEWARE__,
  middleware_loader_entry_default as default
};
//# sourceMappingURL=index.js.map
