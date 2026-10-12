using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;
using WebSocketSharp;

namespace SocketIO
{
	public class SocketIOComponent : MonoBehaviour
	{
		public string url;

		public bool autoConnect;

		public float ackExpirationTime;

		public int pingInterval;

		public double pingTimeout;

		private bool isConnecting;

		private bool connected;

		private bool thPinging;

		private bool thPong;

		private bool wsConnected;

		private WebSocket ws;

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

		public int reconnectDelay { get; set; }

		public WebSocket socket
		{
			get
			{
				return null;
			}
		}

		public string sid { get; set; }

		public bool IsConnected
		{
			get
			{
				return false;
			}
		}

		public bool IsConnecting
		{
			get
			{
				return false;
			}
		}

		public string URL
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public void Awake()
		{
		}

		public void Start()
		{
		}

		public void Update()
		{
		}

		public void OnApplicationQuit()
		{
		}

		public void Connect()
		{
		}

		public void Close()
		{
		}

		public void On(string ev, Action<SocketIOEvent> callback)
		{
		}

		public void Off(string ev, Action<SocketIOEvent> callback)
		{
		}

		public void Emit(string ev)
		{
		}

		public void Emit(string ev, Action<JsonData> action)
		{
		}

		public void Emit(string ev, JsonData data)
		{
		}

		public void Emit(string ev, string data)
		{
		}

		public void Emit(string ev, JsonData data, Action<JsonData> action)
		{
		}

		public void ResetPingTime(int interval, double timeout)
		{
		}

		[DebuggerHidden]
		private IEnumerator StayConnected(WebSocket webSocket)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator SendPing(WebSocket webSocket)
		{
			return null;
		}

		private void ResetConnecting(SocketIOEvent ev)
		{
		}

		private void EmitMessage(int id, string raw)
		{
		}

		private void EmitClose()
		{
		}

		private void EmitPacket(Packet packet)
		{
		}

		private void OnOpen(object sender, EventArgs e)
		{
		}

		private void OnMessage(object sender, MessageEventArgs e)
		{
		}

		private void HandleOpen(Packet packet)
		{
		}

		private void HandlePing()
		{
		}

		private void HandlePong()
		{
		}

		private void HandleMessage(Packet packet)
		{
		}

		private void OnError(object sender, ErrorEventArgs e)
		{
		}

		private void OnClose(object sender, CloseEventArgs e)
		{
		}

		private void EmitEvent(string type)
		{
		}

		private void EmitEvent(SocketIOEvent ev)
		{
		}

		private void InvokeAck(Packet packet)
		{
		}
	}
}
