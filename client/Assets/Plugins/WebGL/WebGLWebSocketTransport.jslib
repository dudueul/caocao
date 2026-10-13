// WebGLWebSocketTransport.jslib — the browser side of the socket bridge.
// Unity WebGL loads this via the .jslib plugin; functions are resolved by
// name from the C# DllImport("__Internal") declarations.
//
// MergeIntoUnityJS lesson (Lab 09): the browser's WebSocket delivers string
// frames; we wrap each event as "open"/"close"/"error:x"/"msg:x" and forward
// through SendMessage to the relay GameObject.
//
// createUnityInstance's module object receives this via `dataUrl`/plugin
// folder automatically (Assets/Plugins/WebGL/*.jslib).
mergeInto(LibraryManager.library, {
  WsCreate: function (urlPtr, gameObjectNamePtr) {
    var url = UTF8ToString(urlPtr);
    var goName = UTF8ToString(gameObjectNamePtr);
    var id = WebSocketBridge.nextId++;
    var ws = new WebSocket(url);
    WebSocketBridge.sockets[id] = ws;
    ws.onopen = function () {
      SendMessage(goName, "JSCallback", "open");
    };
    ws.onclose = function () {
      SendMessage(goName, "JSCallback", "close");
      delete WebSocketBridge.sockets[id];
    };
    ws.onerror = function (ev) {
      SendMessage(goName, "JSCallback", "error:" + (ev && ev.message ? ev.message : "ws_error"));
    };
    ws.onmessage = function (ev) {
      if (typeof ev.data === "string")
        SendMessage(goName, "JSCallback", "msg:" + ev.data);
      // binary frames unused by the engine.io v4 text protocol
    };
    return id;
  },
  WsSend: function (id, textPtr) {
    var ws = WebSocketBridge.sockets[id];
    if (ws && ws.readyState === 1)
      ws.send(UTF8ToString(textPtr));
    return 0;
  },
  WsClose: function (id) {
    var ws = WebSocketBridge.sockets[id];
    if (ws) ws.close();
    delete WebSocketBridge.sockets[id];
    return 0;
  },
});

var WebSocketBridge = { nextId: 1, sockets: {} };
