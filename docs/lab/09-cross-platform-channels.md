# Lab 09 — Cross-Platform Channels: Android, iOS, WebGL

**Goal:** one codebase, three store channels. Each channel has exactly ONE
hard constraint and one adaptation. Learn the constraint first — the
adaptations then become obvious.

---

## 9.1 The constraint table (memorize this)

| Channel | Transport | Build host | IL2CPP? | Gotcha |
|---|---|---|---|---|
| Android | WebSocketSharp (TCP+TLS) | Windows/Linux/macOS | yes | 64-bit required since 2019 (we have arm64 ✓) |
| **iOS** | WebSocketSharp | **macOS + Xcode — non-negotiable** | **mandatory** (Apple bans JIT; IL2CPP=AOT ✓) | deviceUniqueIdentifier semantics differ |
| **WebGL** | **browser WebSocket ONLY** (raw sockets banned by the sandbox) | any | **yes, forced** (AOT→WASM) | no threads, no `System.Net.Sockets`; CORS everywhere |

**The classroom question that sets up everything:** *why can't WebSocketSharp
just run in the browser?* → it builds on `System.Net.Sockets` — raw TCP is
invisible inside a browser sandbox; the ONLY socket a web page gets is the
page's own `WebSocket`. So the channel work is a *seam*, not a port.

## 9.2 The seam (what we built)

```
SocketIOComponent (unchanged state machine — dsa-04 discipline)
      │ IWebSocketTransport (OnOpen/OnClose/OnError/OnTextMessage/Send)
      ├── WebSocketSharpTransport          → Android/iOS/Windows/Linux
      └── WebGLWebSocketTransport          → browser, via WebGLWebSocketTransport.jslib
            JS: new WebSocket(url) → SendMessage(go, "JSCallback", "msg:<frame>")
```

Verified: the golden login flow passes through the seam byte-identically
(protocol_test.py green after the refactor — the seam adds zero bytes).

**Exercise 9-A:** the first version of the seam wrapped frames back in a
`MessageEventArgs` — which has an **internal** constructor in the real
WebSocketSharp. Why do library authors make wrapper types internal, and what
does that force on consumers? (Answer: protects invariants — forces you to
*seam at the API boundary*, not the type; the fix was `Decoder.Decode(string)`.)

## 9.3 The WebGL channel checklist

1. **CORS**: browsers preflight every `POST` with `content-type` → the Worker
   answers `OPTIONS` with 204 + allow-headers (implemented, verified with curl).
2. **WS origin**: browser WebSockets are *not* CORS-bound, but our Worker
   route checks Upgrade + serves same-origin anyway.
3. **Asset pipeline**: 505MB of content → WebGL needs Brotli-compressed
   AssetBundles from the CDN (`ServerSetting.CDN_URL` → R2) — the original
   design was already CDN-driven (akamaized), we keep the shape.
4. **Compression**: Unity WebGL builds need Brotli + proper
   `Content-Encoding` headers on hosting (R2 route does it).
5. **No threads**: the queue discipline (dsa-04) already assumed main-thread
   drain — WebGL changes nothing. If a later port adds threads, WebGL breaks.

**Exercise 9-B:** decode why `Ping` on WebGL is free: the browser WebSocket
has its own ping/pong — yet engine.io still needs "2"/"3" frames. What breaks
if you skip them? (Hint: the *server's* timeout, not the socket's.)

## 9.4 The iOS channel checklist

1. **IL2CPP mandatory** — Apple bans JIT; Mono runtime is out. Our project is
   IL2CPP-native already (recovered from an IL2CPP build — convenient).
2. **Build host**: Xcode → macOS. Options: (a) own Mac, (b) CI Mac (GitHub
   Actions macOS runners + Unity activate/license), (c) Unity Cloud Build.
   The Unity *editor* can stay on Windows; the final build step needs macOS.
3. **Identifiers**: `SystemInfo.deviceUniqueIdentifier` on iOS = vendor-id
   hash; our guest-auth salt (SessionBoot) — same call, different stability
   semantics (survives reinstall? per-vendor). For real accounts this
   disappears behind the auth layer anyway.
4. **Cert/profile**: Apple Developer account ($99/yr) + signing team;
   TestFlight = the soft-launch channel.
5. **TLS**: ATS (App Transport Security) requires TLS1.2+ — our WS must be
   `wss://` in prod (Workers serve TLS natively; the original's plain
   `http://…:9080` would be REJECTED on iOS).

**Exercise 9-C:** the original shipped `http://` game traffic in 2016. Which
of today's four channels would each of: plain http, self-signed TLS,
proper TLS break on, and why? (Answer: iOS breaks on both of the first two;
browsers break on self-signed; Android 9+ blocks plaintext too.)

## 9.5 Build configuration matrix (rebuild targets)

| Channel | Unity target | Backend | Server URL |
|---|---|---|---|
| Android | Android, arm64-v8a | IL2CPP | `https://caocao.<owner>.workers.dev` |
| iOS | iOS | IL2CPP | same (wss://) |
| WebGL | WebGL 2022+ settings, Brotli | IL2CPP→WASM | same (CORS-ready) |
| Dev (editor) | Standalone | Mono (fast iteration) | `ws://localhost:8787` |

The channel switch is *data, not code*: one `ServerSetting` row per channel
(the original's `Live/Stage/Dev × JPN/GLB` matrix taught exactly this — we
rebuilt it as `Environment.LOCAL` + deploy channels).

## 9.6 Exercises

- **9-D** Add a fourth row: Switch/Xbox (Unity platform). Which of the three
  constraints (socket, build host, AOT) bite, per platform?
- **9-E** The original game shipped NO WebGL channel (2016 era). List three
  things in this codebase that make a 2026 WebGL port *easier* than a 2016
  one would have been. (Seam-first transport; CDN-driven assets; REST+WS split.)
