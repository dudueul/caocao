// [RE] Rebuild-only platform abstraction: the wire engine (SocketIOComponent)
// must run on Android/iOS/Windows (WebSocketSharp: real TCP sockets) AND on
// WebGL (browser forbids raw sockets — only the JS WebSocket API exists).
// This interface is the seam; dsa-04's queue design sits above it untouched.
//
// Lab 09 teaches why: WebGL is a browser sandbox — no System.Net.Sockets,
// no threads; the transport becomes the browser's own WebSocket via a .jslib
// bridge, delivering the same events through Unity's SendMessage callback.
using System;

public interface IWebSocketTransport : IDisposable
{
	/// <summary>ReadyState-compatible flag for the socket layer.</summary>
	bool IsOpen { get; }

	string Url { get; }

	event Action OnOpen;
	event Action OnClose;
	event Action<string> OnError;
	event Action<string> OnTextMessage;   // engine.io frames are text

	void Connect();
	void Close();
	void Send(string text);
}

public static class WebSocketTransportFactory
{
	public static IWebSocketTransport Create(string url)
	{
#if UNITY_WEBGL && !UNITY_EDITOR
		return new WebGLWebSocketTransport(url);
#else
		return new WebSocketSharpTransport(url);
#endif
	}
}

// Native platforms: the vendored WebSocketSharp (real vendored lib restored
// in milestone 6a's sibling chunk).
public class WebSocketSharpTransport : IWebSocketTransport
{
	private WebSocketSharp.WebSocket ws;

	public bool IsOpen { get; private set; }

	public string Url { get; private set; }

	public event Action OnOpen;
	public event Action OnClose;
	public event Action<string> OnError;
	public event Action<string> OnTextMessage;

	public WebSocketSharpTransport(string url)
	{
		Url = url;
	}

	public void Connect()
	{
		Close();
		ws = new WebSocketSharp.WebSocket(Url);
		ws.OnOpen += delegate { IsOpen = true; if (OnOpen != null) OnOpen(); };
		ws.OnClose += delegate { IsOpen = false; if (OnClose != null) OnClose(); };
		ws.OnError += delegate(object s, WebSocketSharp.ErrorEventArgs e)
		{
			if (OnError != null) OnError(e.Message);
		};
		ws.OnMessage += delegate(object s, WebSocketSharp.MessageEventArgs e)
		{
			if (e.Type == WebSocketSharp.Opcode.Text && OnTextMessage != null)
				OnTextMessage(e.Data);
		};
		ws.ConnectAsync();
	}

	public void Close()
	{
		if (ws != null)
		{
			try { ws.CloseAsync(); } catch { }
			ws = null;
		}
		IsOpen = false;
	}

	public void Send(string text)
	{
		if (ws != null && ws.ReadyState == WebSocketSharp.ReadyState.Open)
			ws.Send(text);
	}

	public void Dispose()
	{
		Close();
	}
}
