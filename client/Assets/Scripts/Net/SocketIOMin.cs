// SocketIOMin.cs — minimal Socket.IO v4 (Engine.IO v4) client for the CAOCAAO rebuild.
//
// [RE] Provenance: re-implementation of the wire behavior observed in the
// original build's `SocketIOComponent` (Namespace: SocketIO, TypeDefIndex 4688,
// fields: url, autoConnect, ackExpirationTime, pingInterval:int,
// pingTimeout:double, sid, handlers Dictionary<string, List<Action<SocketIOEvent>>>,
// ackList, packetId, eventQueue, ackQueue) and `PacketUtil` (TypeDefIndex 10912,
// JsonString(object[])). URL template literal recovered from
// global-metadata string literals: ws://{0}/socket.io/?EIO=4&transport=websocket
// Reference docs: docs/lab/07-protocol-reconstruction.md, analysis/PROTOCOL.md.
//
// Design notes (see dsa-04 in docs/lab/dsa/ for the queueing discussion):
//  - One WS connection; engine.io framing is 1-char type + payload per text frame.
//  - v4: CLIENT sends "2" ping, SERVER answers "3" pong (opposite of v2/v3!).
//  - socket.io packets: 0=CONNECT 1=DISCONNECT 2=EVENT 3=ACK 4=CONNECT_ERROR,
//    optionally prefixed by namespace "/admin," and ack id + "#".
//  - Threading: WebSocket receive loop runs on a worker; events are marshalled
//    to the Unity main thread via a ConcurrentQueue drained in Update().

using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Net.WebSockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Newtonsoft.Json;
using UnityEngine;

namespace Net
{
    public class SocketIOEvent
    {
        public string Name;
        public object[] Args;
        public int? AckId;
    }

    /// Mirror of the original SocketIOComponent public surface (subset we use).
    public class SocketIOMin : IDisposable
    {
        // [RE] original public fields — keep names for port-compatibility
        public string url;
        public bool autoConnect = true;
        public float ackExpirationTime = 180f;
        public int pingInterval = 25000;      // ms, server may override via handshake
        public double pingTimeout = 20000;    // ms
        public string sid { get; private set; }
        public bool IsConnected => connected;

        private ClientWebSocket ws;
        private CancellationTokenSource cts;
        private readonly Dictionary<string, List<Action<SocketIOEvent>>> handlers = new();
        private readonly Dictionary<int, (Action<object[]> cb, DateTime sent)> acks = new();
        private readonly ConcurrentQueue<SocketIOEvent> eventQueue = new();
        private int packetId;
        private bool connected;
        private DateTime lastPongUtc;
        private DateTime lastPingUtc;
        private Task rxLoop;

        public event Action OnConnected;
        public event Action OnDisconnected;

        public SocketIOMin(string url, bool autoConnect = true)
        {
            this.url = url;
            this.autoConnect = autoConnect;
        }

        // ---- public API (mirrors original) --------------------------------

        public void On(string ev, Action<SocketIOEvent> cb)
        {
            if (!handlers.TryGetValue(ev, out var list))
                handlers[ev] = list = new List<Action<SocketIOEvent>>();
            list.Add(cb);
        }

        public void Emit(string ev) => Emit(ev, Array.Empty<object>());

        public void Emit(string ev, params object[] args)
        {
            // [RE] PacketUtil.JsonString(object[]) equivalent: positional JSON array.
            var payload = new object[] { ev }.Concat(args); // ["event", ...args]
            SendIOPacket("2", JsonConvert.SerializeObject(payload));
        }

        public void EmitWithAck(string ev, Action<object[]> ack, params object[] args)
        {
            int id = ++packetId;
            acks[id] = (ack, DateTime.UtcNow);
            var payload = new object[] { ev }.Concat(args);
            SendIOPacket("2" + id, JsonConvert.SerializeObject(payload));
        }

        public void Connect()
        {
            if (ws != null) return;
            cts = new CancellationTokenSource();
            ws = new ClientWebSocket();
            ws.Options.KeepAliveInterval = TimeSpan.FromSeconds(30);
            rxLoop = Task.Run(() => RunAsync(cts.Token), cts.Token);
        }

        public void Dispose()
        {
            try { cts?.Cancel(); ws?.Dispose(); } catch { }
            connected = false;
        }

        // ---- main-thread drain (call from a MonoBehaviour.Update) -----------

        public void DispatchQueued()
        {
            while (eventQueue.TryDequeue(out var ev))
            {
                if (handlers.TryGetValue(ev.Name, out var list))
                    foreach (var cb in list) cb(ev);
            }
        }

        // ---- engine.io / socket.io state machine ----------------------------

        private async Task RunAsync(CancellationToken ct)
        {
            while (!ct.IsCancellationRequested)
            {
                try
                {
                    await ws.ConnectAsync(new Uri(url), ct);
                    await ReceiveLoop(ct);
                }
                catch (OperationCanceledException) { break; }
                catch (Exception)
                {
                    // [RE] original: reconnectDelay:int backing field
                    await Task.Delay(3000, ct);
                }
                connected = false;
                MainThread.Post(OnDisconnected);
            }
        }

        private async Task ReceiveLoop(CancellationToken ct)
        {
            var buf = new byte[64 * 1024];
            while (!ct.IsCancellationRequested && ws.State == WebSocketState.Open)
            {
                var sb = new StringBuilder();
                WebSocketReceiveResult r;
                do
                {
                    r = await ws.ReceiveAsync(new ArraySegment<byte>(buf), ct);
                    if (r.MessageType == WebSocketMessageType.Close)
                    {
                        await ws.CloseAsync(WebSocketCloseStatus.NormalClosure, null, ct);
                        return;
                    }
                    sb.Append(Encoding.UTF8.GetString(buf, 0, r.Count));
                } while (!r.EndOfMessage);

                HandleFrame(sb.ToString());
            }
        }

        private void HandleFrame(string frame)
        {
            if (string.IsNullOrEmpty(frame)) return;
            char t = frame[0];
            string rest = frame.Length > 1 ? frame.Substring(1) : "";
            switch (t)
            {
                case '0': // engine.io OPEN — handshake carries server cadence
                    var hs = JsonConvert.DeserializeObject<EngineIoHandshake>(rest);
                    if (hs != null)
                    {
                        sid = hs.sid;
                        if (hs.pingInterval > 0) pingInterval = hs.pingInterval;
                        if (hs.pingTimeout > 0) pingTimeout = hs.pingTimeout;
                    }
                    lastPongUtc = DateTime.UtcNow;
                    SendIOPacketRaw("0"); // socket.io CONNECT (default namespace)
                    break;

                case '3': // engine.io PONG (v4: client pings)
                    lastPongUtc = DateTime.UtcNow;
                    break;

                case '4': // engine.io MESSAGE → socket.io layer
                    HandleIoPacket(rest);
                    break;

                case '6': // engine.io CLOSE
                    connected = false;
                    MainThread.Post(OnDisconnected);
                    break;
            }
        }

        private void HandleIoPacket(string data)
        {
            if (string.IsNullOrEmpty(data)) return;
            int i = 0;
            if (data[0] == '/') // namespace prefix (we only use default "/")
            {
                i = data.IndexOf(',', StringComparison.Ordinal);
                if (i < 0) return;
                i++;
            }
            if (i >= data.Length) return;
            char p = data[i];
            string body = data.Substring(i + 1);

            switch (p)
            {
                case '0': // socket.io CONNECT ack
                    connected = true;
                    lastPongUtc = DateTime.UtcNow;
                    MainThread.Post(OnConnected);
                    break;
                case '1': // socket.io DISCONNECT
                    connected = false;
                    MainThread.Post(OnDisconnected);
                    break;
                case '2': // EVENT: [id#]<json array>
                case '3': // ACK
                {
                    int hash = body.IndexOf('#');
                    int? ackId = null;
                    if (p == '3' || (hash > 0))
                    {
                        string num = hash > 0 ? body.Substring(0, hash) : body;
                        body = hash > 0 ? body.Substring(hash + 1) : "";
                        if (int.TryParse(num, out int id2)) ackId = id2;
                    }
                    if (body.Length == 0) return;
                    var arr = JsonConvert.DeserializeObject<object[]>(body);
                    if (arr == null || arr.Length == 0) return;

                    var ev = new SocketIOEvent
                    {
                        Name = Convert.ToString(arr[0]),
                        Args = arr.Length > 1 ? arr[1..] : Array.Empty<object>(),
                        AckId = ackId,
                    };
                    eventQueue.Enqueue(ev);
                    break;
                }
                case '4': // CONNECT_ERROR
                    Debug.LogWarning("[SocketIOMin] connect_error: " + body);
                    break;
            }
        }

        private void SendIOPacket(string payload) => SendIOPacketRaw("4" + payload);

        private void SendIOPacketRaw(string frame)
        {
            var w = ws;
            if (w?.State != WebSocketState.Open) return;
            var bytes = Encoding.UTF8.GetBytes(frame);
            _ = w.SendAsync(new ArraySegment<byte>(bytes), WebSocketMessageType.Text,
                            true, cts.Token);
        }

        /// <summary>Keepalive driver: call every frame from the net MonoBehaviour.
        /// [RE] mirrors StayConnected/SendPing coroutines of the original.</summary>
        public void Tick()
        {
            if (!connected) return;
            var now = DateTime.UtcNow;
            if ((now - lastPingUtc).TotalMilliseconds >= pingInterval)
            {
                lastPingUtc = now;
                SendIOPacketRaw("2");
            }
            if ((now - lastPongUtc).TotalMilliseconds > pingInterval + pingTimeout)
            {
                // missed pong — drop and let the reconnect loop rebuild
                try { ws.Abort(); } catch { }
                connected = false;
            }
        }

        private class EngineIoHandshake
        {
            public string sid;
            public int pingInterval;
            public int pingTimeout;
            public int maxPayload;
        }
    }

    /// Marshals callbacks onto Unity's main thread (original used coroutines
    /// on the Unity player loop; we use SynchronizationContext).
    internal static class MainThread
    {
        private static SynchronizationContext ctx;

        [RuntimeInitializeOnLoadMethod(RuntimeInitializeLoadType.SubsystemRegistration)]
        static void Init() => ctx = SynchronizationContext.Current;

        public static void Post(Action a)
        {
            if (ctx != null) ctx.Post(_ => a(), null);
            else a();
        }
    }

    internal static class LinqExt
    {
        public static IEnumerable<object> Concat(this object[] head, object[] tail)
        {
            foreach (var x in head) yield return x;
            foreach (var x in tail) yield return x;
        }
    }
}
