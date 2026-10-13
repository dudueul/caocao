// [RE] The full Socket.IO client state machine, rebuilt into the original
// stub (all original public/private member names preserved — decompiled
// anchor: SocketIOComponent TypeDefIndex 4688; field offsets match dump.cs).
// Threading: WebSocketSharp callbacks arrive off-thread; events/acks are
// queued and drained in Update() — the single-consumer discipline (dsa-04).
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

namespace SocketIO
{
	public class SocketIOComponent : MonoBehaviour
	{
		// [RE] original serialized fields — keep names/defaults for prefab compat
		public string url;

		public bool autoConnect;

		public float ackExpirationTime = 180f;

		public int pingInterval = 25000;      // ms — [RE] matches server handshake default

		public double pingTimeout = 20000d;   // ms

		private bool isConnecting;

		private bool connected;

		private bool thPinging;

		private bool thPong;

		private bool wsConnected;

		private IWebSocketTransport ws;   // [RE] platform seam: WebSocketSharp on
		                                  // native, browser WS via .jslib on WebGL (Lab 09)

		private Encoder encoder;

		private Decoder decoder;

		private Parser parser;

		private Dictionary<string, List<Action<SocketIOEvent>>> handlers;

		private List<Ack> ackList;

		private int packetId;

		private object eventQueueLock;

		private Queue<SocketIOEvent> eventQueue;

		private object ackQueueLock;

		private Queue<Packet> ackQueue;

		private Coroutine pingCoroutine;

		private float reconnectTimer;         // [RE] reconnectDelay backing state
		private DateTime lastPongUtc;

		public int reconnectDelay { get; set; }

		public IWebSocketTransport socket
		{
			get
			{
				return ws;
			}
		}

		public string sid { get; set; }

		public bool IsConnected
		{
			get
			{
				return connected;
			}
		}

		public bool IsConnecting
		{
			get
			{
				return isConnecting;
			}
		}

		public string URL
		{
			get
			{
				return url;
			}
			set
			{
				url = value;
			}
		}

		public void Awake()
		{
			encoder = new Encoder();
			decoder = new Decoder();
			parser = new Parser();
			handlers = new Dictionary<string, List<Action<SocketIOEvent>>>();
			ackList = new List<Ack>();
			eventQueueLock = new object();
			ackQueueLock = new object();
			eventQueue = new Queue<SocketIOEvent>();
			ackQueue = new Queue<Packet>();
			reconnectDelay = 3;
			packetId = 0;
		}

		public void Start()
		{
			if (autoConnect)
				Connect();
		}

		public void Update()
		{
			// [RE] single-consumer drain — see dsa-04.
			if (eventQueue != null)
			{
				while (true)
				{
					SocketIOEvent ev;
					lock (eventQueueLock)
					{
						if (eventQueue.Count == 0) break;
						ev = eventQueue.Dequeue();
					}
					DispatchEvent(ev);
				}
			}
			if (ackList.Count > 0)
			{
				var now = DateTime.UtcNow;
				for (int i = ackList.Count - 1; i >= 0; i--)
				{
					if ((now - ackList[i].time).TotalSeconds > ackExpirationTime)
						ackList.RemoveAt(i);
				}
			}
		}

		public void OnApplicationQuit()
		{
			Close();
		}

		public void Connect()
		{
			if (ws != null || isConnecting)
				return;
			isConnecting = true;
			ws = WebSocketTransportFactory.Create(url);
			ws.OnOpen += OnOpen;
			ws.OnTextMessage += OnTransportMessage;
			ws.OnClose += OnClose;
			ws.OnError += OnError;
			ws.Connect();
		}

		public void Close()
		{
			if (ws != null)
			{
				ws.Dispose();
				ws = null;
			}
			connected = false;
			isConnecting = false;
			StopPing();
		}

		public void On(string ev, Action<SocketIOEvent> callback)
		{
			if (!handlers.TryGetValue(ev, out var list))
				handlers[ev] = list = new List<Action<SocketIOEvent>>();
			list.Add(callback);
		}

		public void Off(string ev, Action<SocketIOEvent> callback)
		{
			if (handlers.TryGetValue(ev, out var list))
				list.Remove(callback);
		}

		public void Emit(string ev)
		{
			EmitPacket(new Packet(EnginePacketType.MESSAGE, SocketPacketType.EVENT, 0, "/", -1, WrapEvent(ev, null)));
		}

		public void Emit(string ev, Action<JsonData> action)
		{
			EmitWithAck(ev, null, action);
		}

		public void Emit(string ev, JsonData data)
		{
			EmitPacket(new Packet(EnginePacketType.MESSAGE, SocketPacketType.EVENT, 0, "/", -1, WrapEvent(ev, data)));
		}

		public void Emit(string ev, string data)
		{
			// [RE] string payload variant — SessionImpl uses JsonData variants;
			// string form wraps raw text into the array.
			var arr = new JsonData();
			arr.Add(ev);
			arr.Add(data);
			EmitPacket(new Packet(EnginePacketType.MESSAGE, SocketPacketType.EVENT, 0, "/", -1, arr));
		}

		public void Emit(string ev, JsonData data, Action<JsonData> action)
		{
			EmitWithAck(ev, data, action);
		}

		public void ResetPingTime(int interval, double timeout)
		{
			pingInterval = interval;
			pingTimeout = timeout;
			RestartPing();
		}

		[DebuggerHidden]
		private IEnumerator StayConnected(IWebSocketTransport webSocket)
		{
			// [RE] original reconnect watchdog: retries every reconnectDelay while
			// the app is alive and auto-reconnect is desired.
			while (true)
			{
				if (!wsConnected && !isConnecting)
				{
					Close();
					yield return new WaitForSeconds(reconnectDelay);
					Connect();
				}
				yield return new WaitForSeconds(1f);
			}
		}

		[DebuggerHidden]
		private IEnumerator SendPing(IWebSocketTransport webSocket)
		{
			// [RE] engine.io v4: CLIENT pings ("2"), server answers "3".
			while (wsConnected)
			{
				yield return new WaitForSeconds(pingInterval / 1000f);
				if (!wsConnected) break;
				thPinging = true;
				thPong = false;
				EmitPing();
				float waited = 0f;
				while (!thPong && waited < (float)(pingTimeout / 1000d))
				{
					waited += Time.deltaTime;
					yield return null;
				}
				if (!thPong)
				{
					// missed pong → drop and reconnect
					wsConnected = false;
					connected = false;
					break;
				}
			}
			yield return null;
		}

		private void ResetConnecting(SocketIOEvent ev)
		{
			isConnecting = false;
		}

		private void EmitMessage(int id, string raw)
		{
			// [RE] raw outgoing event used by ack replies
			var p = new Packet(EnginePacketType.MESSAGE, SocketPacketType.EVENT, 0, "/", id, JsonMapper.ToObject(raw));
			EmitPacket(p);
		}

		private void EmitClose()
		{
			if (ws != null && ws.IsOpen)
				ws.Send("6");
		}

		private void EmitPacket(Packet packet)
		{
			if (ws == null || !ws.IsOpen)
				return;
			ws.Send(encoder.Encode(packet));
		}

		private void EmitPing()
		{
			if (ws != null && ws.IsOpen)
				ws.Send("2");
		}

		private void EmitWithAck(string ev, JsonData data, Action<JsonData> action)
		{
			int id = ++packetId;
			var arr = new JsonData();
			arr.Add(ev);
			if (data != null) arr.Add(data);
			ackList.Add(new Ack(id, action) { time = DateTime.UtcNow });
			var p = new Packet(EnginePacketType.MESSAGE, SocketPacketType.EVENT, 0, "/", id, arr);
			EmitPacket(p);
		}

		private void OnOpen()
		{
			wsConnected = true;
			isConnecting = false;
			EmitPacket(new Packet(EnginePacketType.MESSAGE, SocketPacketType.CONNECT, 0, "/", -1, null));
		}

		private void OnTransportMessage(string text)
		{
			// [RE] transport delivers engine.io text frames on every platform
			Packet packet = decoder.Decode(text);
			if (packet == null)
				return;
			switch (packet.enginePacketType)
			{
				case EnginePacketType.OPEN:
					HandleOpen(packet);
					break;
				case EnginePacketType.PING:
					// v4: server does not ping; respond defensively anyway
					EmitPong();
					break;
				case EnginePacketType.PONG:
					HandlePong();
					break;
				case EnginePacketType.CLOSE:
					EmitClose();
					wsConnected = false;
					connected = false;
					break;
				case EnginePacketType.MESSAGE:
					lock (ackQueueLock)
						ackQueue.Enqueue(packet);
					break;
			}
		}

		private void OnError(string message)
		{
			wsConnected = false;
		}

		private void OnClose()
		{
			wsConnected = false;
			connected = false;
			StopPing();
			lock (eventQueueLock)
				eventQueue.Enqueue(new SocketIOEvent("disconnect"));
		}

		private void EmitPong()
		{
			if (ws != null && ws.IsOpen)
				ws.Send("3");
		}

		private void HandleOpen(Packet packet)
		{
			if (packet.json != null)
			{
				// [RE] LitJson 0.16 stores JSON ints as long — direct
				// (int)/(double) casts throw "doesn't hold a double"; parse
				// from string instead.
				if (packet.json.Keys.Contains("sid"))
					sid = packet.json["sid"].ToString();
				if (packet.json.Keys.Contains("pingInterval"))
					int.TryParse(packet.json["pingInterval"].ToString(), out pingInterval);
				if (packet.json.Keys.Contains("pingTimeout"))
					double.TryParse(packet.json["pingTimeout"].ToString(), out pingTimeout);
			}
			lastPongUtc = DateTime.UtcNow;
		}

		private void HandlePing()
		{
		}

		private void HandlePong()
		{
			thPong = true;
			thPinging = false;
			lastPongUtc = DateTime.UtcNow;
		}

		private void HandleMessage(Packet packet)
		{
			switch (packet.socketPacketType)
			{
				case SocketPacketType.CONNECT:
					connected = true;
					StartPing();
					lock (eventQueueLock)
						eventQueue.Enqueue(new SocketIOEvent("connect"));
					break;
				case SocketPacketType.DISCONNECT:
					connected = false;
					StopPing();
					lock (eventQueueLock)
						eventQueue.Enqueue(new SocketIOEvent("disconnect"));
					break;
				case SocketPacketType.EVENT:
				{
					SocketIOEvent ev = parser.Parse(packet.json);
					if (ev != null)
						lock (eventQueueLock)
							eventQueue.Enqueue(ev);
					break;
				}
				case SocketPacketType.ACK:
				{
					SocketIOEvent ev = parser.Parse(packet.json);
					for (int i = ackList.Count - 1; i >= 0; i--)
					{
						if (ackList[i].packetId == packet.id)
						{
							var a = ackList[i];
							ackList.RemoveAt(i);
							a.Invoke(ev != null ? ev.data : null);
						}
					}
					break;
				}
				case SocketPacketType.ERROR:
				{
					var err = new SocketIOEvent("error", packet.json);
					lock (eventQueueLock)
						eventQueue.Enqueue(err);
					break;
				}
			}
		}

		private void StartPing()
		{
			StopPing();
			pingCoroutine = StartCoroutine(SendPing(ws));
		}

		private void StopPing()
		{
			if (pingCoroutine != null)
			{
				StopCoroutine(pingCoroutine);
				pingCoroutine = null;
			}
		}

		private void RestartPing()
		{
			if (wsConnected)
				StartPing();
		}

		private void DispatchEvent(SocketIOEvent ev)
		{
			// ack queue drain happens here too — HandleMessage defers ACKs to
			// the main thread via ackQueue.
			while (true)
			{
				Packet p;
				lock (ackQueueLock)
				{
					if (ackQueue == null || ackQueue.Count == 0) break;
					p = ackQueue.Dequeue();
				}
				if (p != null)
					HandleMessageDeferred(p);
			}
			if (ev == null) return;
			if (handlers.TryGetValue(ev.name, out var list))
				foreach (var cb in list.ToArray())
				{
					try { cb(ev); }
					catch (Exception ex) { Debug.LogError("[SocketIO] handler threw: " + ex); }
				}
		}

		private void HandleMessageDeferred(Packet packet)
		{
			switch (packet.socketPacketType)
			{
				case SocketPacketType.CONNECT:
					HandlePacketConnect(packet);
					break;
				case SocketPacketType.DISCONNECT:
					HandlePacketDisconnect(packet);
					break;
				case SocketPacketType.EVENT:
				case SocketPacketType.ACK:
				case SocketPacketType.ERROR:
					HandlePacketPayload(packet);
					break;
			}
		}

		private void HandlePacketConnect(Packet packet)
		{
			connected = true;
			StartPing();
			DispatchEvent(new SocketIOEvent("connect"));
		}

		private void HandlePacketDisconnect(Packet packet)
		{
			connected = false;
			StopPing();
			DispatchEvent(new SocketIOEvent("disconnect"));
		}

		private void HandlePacketPayload(Packet packet)
		{
			SocketIOEvent ev = parser.Parse(packet.json);
			if (packet.socketPacketType == SocketPacketType.ACK)
			{
				for (int i = ackList.Count - 1; i >= 0; i--)
				{
					if (ackList[i].packetId == packet.id)
					{
						var a = ackList[i];
						ackList.RemoveAt(i);
						a.Invoke(ev != null ? ev.data : null);
						return;
					}
				}
			}
			if (ev != null)
				DispatchEvent(ev);
			else if (packet.socketPacketType == SocketPacketType.EVENT)
				DispatchEvent(new SocketIOEvent("error", packet.json));
		}

		private static JsonData WrapEvent(string ev, JsonData data)
		{
			// [RE] `["event", payload]` — the decompiled LitJson array pattern
			var arr = new JsonData();
			arr.Add(ev);
			if (data != null)
				arr.Add(data);
			return arr;
		}
	}
}
