// [RE] WebGL transport: the browser sandbox has no System.Net.Sockets —
// the ONLY socket is the page's WebSocket. Unity WebGL calls JS through
// .jslib plugins; the JS side opens the socket and forwards events back via
// SendMessage(gameObject, "JSCallback", payload).
//
// Wire format notes (Lab 09): engine.io v4 frames are TEXT (single char +
// payload) — a perfect fit for the browser WebSocket. Binary frames unused
// by this protocol.
using System;
using System.Runtime.InteropServices;

public class WebGLWebSocketTransport : IWebSocketTransport
{
	[DllImport("__Internal")]
	private static extern int WsCreate(string url, string gameObjectName);

	[DllImport("__Internal")]
	private static extern int WsSend(int id, string text);

	[DllImport("__Internal")]
	private static extern void WsClose(int id);

	public bool IsOpen { get; private set; }

	public string Url { get; private set; }

	public event Action OnOpen;
	public event Action OnClose;
	public event Action<string> OnError;
	public event Action<string> OnTextMessage;

	private readonly string gameObjectName;
	private int socketId = -1;

	public WebGLWebSocketTransport(string url)
	{
		Url = url;
		gameObjectName = "WsTransport_" + url.GetHashCode().ToString("x8");
	}

	public void Connect()
	{
		// [RE] create a hidden GameObject the JS bridge can SendMessage into
		var go = new GameObject(gameObjectName);
		var relay = go.AddComponent<WebGLTransportRelay>();
		relay.owner = this;
		socketId = WsCreate(Url, gameObjectName);
	}

	public void Close()
	{
		if (socketId >= 0)
		{
			WsClose(socketId);
			socketId = -1;
		}
		IsOpen = false;
	}

	public void Send(string text)
	{
		if (IsOpen && socketId >= 0)
			WsSend(socketId, text);
	}

	public void Dispose()
	{
		Close();
	}

	// ---- callbacks from JS (invoked via SendMessage) -----------------------

	public void JSCallback(string payload)
	{
		// payload = "open" | "close" | "error:<msg>" | "msg:<text>"
		if (payload == "open")
		{
			IsOpen = true;
			if (OnOpen != null) OnOpen();
		}
		else if (payload == "close")
		{
			IsOpen = false;
			if (OnClose != null) OnClose();
		}
		else if (payload.StartsWith("error:", StringComparison.Ordinal))
		{
			if (OnError != null) OnError(payload.Substring(6));
		}
		else if (payload.StartsWith("msg:", StringComparison.Ordinal))
		{
			if (OnTextMessage != null) OnTextMessage(payload.Substring(4));
		}
	}
}

public class WebGLTransportRelay : MonoBehaviour
{
	public WebGLWebSocketTransport owner;
}
