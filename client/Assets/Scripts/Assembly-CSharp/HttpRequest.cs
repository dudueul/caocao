// [RE] Rebuilt HttpRequest (TypeDefIndex 10899) — the REST engine.
// Original used HttpWebRequest + IAsyncResult callbacks (2016 style); the
// rebuild keeps the exact public architecture (queue → context → coroutine →
// LitJson callback + per-path listeners) on UnityWebRequest (Unity 6).
// Wire payload: PacketUtil.JsonString(object[]) = "[userId, ...args]".
// URL base: ServerSetting.instance.GAME_SERVER_HOST_URL (LOCAL → wrangler dev).
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net;
using System.Text;
using LitJson;
using UnityEngine;
using UnityEngine.Networking;

public class HttpRequest : Singleton<HttpRequest>
{
	public enum RequestStatus
	{
		init = 0,
		willBeSend = 1,
		didSent = 2,
		isProcessing = 3,
		isDone = 4,
		failed = 5,
		willRetry = 6,
		retryPopupFocused = 7,
		disConnect = 8,
		ignored = 9,
		versionError = 10,
		versionErrorPopup = 11
	}

	public delegate void ResponseCallback(JsonData jsonData);

	public delegate void RetryPopupCallback(HttpRequestContext ctx);

	public class HttpRequestQueue : Queue<HttpRequestContext>
	{
		public bool hasReqNeed2Indicator { get; private set; }

		public new void Enqueue(HttpRequestContext item)
		{
			base.Enqueue(item);
			if (item.showNetworkIndicator)
				hasReqNeed2Indicator = true;
		}

		public new HttpRequestContext Dequeue()
		{
			var ctx = base.Dequeue();
			hasReqNeed2Indicator = FindRequestNeed2ShowIndicator();
			return ctx;
		}

		private bool FindRequestNeed2ShowIndicator()
		{
			foreach (var ctx in this)
				if (ctx.showNetworkIndicator)
					return true;
			return false;
		}
	}

	public class HttpRequestContext
	{
		[Flags]
		public enum Option
		{
			Default = 0,
			DisableAutoRetry = 1,
			DisableRetryPopup = 2
		}

		private static int m_timeout = 15;            // [RE] rebuild defaults
		private static int m_timeoutOnAutoRetry = 5;
		private static int m_maxAutoRetry = 2;

		// [RE] original carried HttpWebRequest; rebuild stores the request
		// spec and executes on UnityWebRequest inside the singleton's Update.
		public UnityEngine.Networking.UnityWebRequest request;

		private string m_uri;

		private string m_method;

		private string m_packet;

		private Option m_opt;

		public ResponseCallback responseCallback;

		public RetryPopupCallback retryPopupCallback;

		public JsonData responseData;

		private RequestStatus m_status;

		public string failReason;

		public string serverMsg;

		public int failedCount;

		public float requestTime;

		public bool showNetworkIndicator;

		public bool autoRetry
		{
			get
			{
				return (m_opt & Option.DisableAutoRetry) == 0;
			}
		}

		public bool disabledRetryPopup
		{
			get
			{
				return (m_opt & Option.DisableRetryPopup) != 0;
			}
		}

		public int timeout
		{
			get
			{
				return m_timeout;
			}
			set
			{
				m_timeout = value;
			}
		}

		public int maxTimeoutRetry
		{
			get
			{
				return m_maxAutoRetry;
			}
		}

		public RequestStatus status
		{
			get
			{
				return m_status;
			}
			set
			{
				m_status = value;
			}
		}

		public string absolutePath
		{
			get
			{
				return ServerSetting.instance.GAME_SERVER_HOST_URL + m_uri;
			}
		}

		public string uri
		{
			get
			{
				return m_uri;
			}
		}

		public string method
		{
			get
			{
				return m_method;
			}
		}

		public string packet
		{
			get
			{
				return m_packet;
			}
		}

		public HttpRequestContext(string uri, string packet, string method, bool showNetworkIndicator, ResponseCallback responseCallback, RetryPopupCallback retryPopupCallback, Option opt)
		{
			m_uri = uri;
			m_packet = packet;
			m_method = string.IsNullOrEmpty(method) ? "POST" : method;
			this.showNetworkIndicator = showNetworkIndicator;
			this.responseCallback = responseCallback;
			this.retryPopupCallback = retryPopupCallback;
			m_opt = opt;
			m_status = RequestStatus.init;
			failedCount = 0;
			requestTime = 0f;
		}

		public override string ToString()
		{
			return string.Format("[{0} {1} status={2} fail={3}]", m_method, m_uri, m_status, failReason);
		}

		public override int GetHashCode()
		{
			return (m_uri ?? "").GetHashCode() ^ m_packet.GetHashCode();
		}

		public static bool operator ==(HttpRequestContext A, HttpRequestContext B)
		{
			if (ReferenceEquals(A, B)) return true;
			if ((object)A == null || (object)B == null) return false;
			return A.m_uri == B.m_uri && A.m_packet == B.m_packet;
		}

		public static bool operator !=(HttpRequestContext A, HttpRequestContext B)
		{
			return !(A == B);
		}

		public override bool Equals(object obj)
		{
			var other = obj as HttpRequestContext;
			return other != null && this == other;
		}

		public void Send()
		{
			m_status = RequestStatus.willBeSend;
			HttpRequest.instance.EnqueueContext(this);
		}

		public bool NeedSession()
		{
			return true;   // [RE] all rebuilt endpoints ride the auth session
		}

		public bool NeedCypher()
		{
			// [RE] USE_PACKET_ENCRYPT (original: Rijndael over the wire body)
			return ServerSetting.instance.USE_PACKET_ENCRYPT;
		}

		public void Fail(object errorType)
		{
			failReason = errorType != null ? errorType.ToString() : "unknown";
			m_status = RequestStatus.failed;
			SendCallback();
		}

		public void SendCallback()
		{
			if (responseCallback != null)
				responseCallback(responseData);
		}

		public void Retry()
		{
			failedCount++;
			m_status = RequestStatus.willRetry;
			HttpRequest.instance.EnqueueContext(this);
		}

		public bool IsAvaliableRetryQuietly()
		{
			return autoRetry && failedCount < m_maxAutoRetry;
		}
	}

	private class OrderedResponseCallback : IComparable
	{
		public ResponseCallback responseHandler;

		public string path;

		public int priority;

		public int CompareTo(object b)   // [RE] public so lambdas can call it
		{
			var o = (OrderedResponseCallback)b;
			return priority.CompareTo(o.priority);
		}
	}

	private static StringBuilder ms_sb;

	public Action<string> OnResponse;

	private Dictionary<string, List<OrderedResponseCallback>> path2Listeners;

	public CookieContainer cookieContainer;

	private HttpRequestQueue m_requestQueue;

	private const int readBufferGrowupSize = 524288;

	private int readBufferSize;

	private byte[] m_readBuffer;

	private List<HttpRequestContext> m_inflight = new List<HttpRequestContext>();

	protected override void Awake()
	{
		base.Awake();
		m_requestQueue = new HttpRequestQueue();
		path2Listeners = new Dictionary<string, List<OrderedResponseCallback>>();
		m_readBuffer = new byte[readBufferGrowupSize];
	}

	public void AddResponseHandler(string path, ResponseCallback listener, int priority)
	{
		if (!path2Listeners.TryGetValue(path, out var list))
			path2Listeners[path] = list = new List<OrderedResponseCallback>();
		list.Add(new OrderedResponseCallback { path = path, responseHandler = listener, priority = priority });
		list.Sort((a, b) => a.CompareTo(b));
	}

	public void RemoveResponseHandler(string path, ResponseCallback listener)
	{
		if (path2Listeners.TryGetValue(path, out var list))
			list.RemoveAll(o => o.responseHandler == listener);
	}

	private void EnqueueContext(HttpRequestContext ctx)
	{
		m_requestQueue.Enqueue(ctx);
	}

	private void Update()
	{
		// start next queued request (serialize to one-in-flight, 2016 style)
		while (m_requestQueue.Count > 0)
		{
			var ctx = m_requestQueue.Dequeue();
			if (ctx.status == RequestStatus.failed)
				continue;
			StartCoroutine(RequestCoroutine(ctx));
		}
		// retire finished inflight
		for (int i = m_inflight.Count - 1; i >= 0; i--)
			if (m_inflight[i].status == RequestStatus.isDone || m_inflight[i].status == RequestStatus.failed)
				m_inflight.RemoveAt(i);
	}

	[DebuggerHidden]
	private IEnumerator RequestCoroutine(HttpRequestContext ctx)
	{
		ctx.status = RequestStatus.didSent;
		var url = ctx.absolutePath;
		UnityEngine.Debug.Log("[HttpRequest] " + ctx.method + " " + url);
		using (var req = new UnityWebRequest(url, ctx.method.ToUpper() == "GET" ? "GET" : "POST"))
		{
			if (req.method != "GET")
			{
				var body = Encoding.UTF8.GetBytes(ctx.packet ?? "[]");
				req.uploadHandler = new UploadHandlerRaw(body);
			}
			req.downloadHandler = new DownloadHandlerBuffer();
			req.SetRequestHeader("Content-Type", "application/json");
			req.timeout = ctx.timeout;
			ctx.requestTime = Time.realtimeSinceStartup;
			var op = req.SendWebRequest();
			while (!op.isDone)
				yield return null;
			ctx.status = RequestStatus.isDone;
			JsonData parsed = null;
			var text = req.downloadHandler != null ? req.downloadHandler.text : "";
			if (!string.IsNullOrEmpty(text))
			{
				try { parsed = JsonMapper.ToObject(text); }
				catch (Exception) { parsed = null; }
			}
			ctx.responseData = parsed;
			if (req.result != UnityWebRequest.Result.Success || (req.responseCode >= 400 && parsed == null))
			{
				ctx.failReason = req.error ?? ("http_" + req.responseCode);
				ctx.status = RequestStatus.failed;
				if (ctx.IsAvaliableRetryQuietly())
				{
					ctx.Retry();
					yield break;
				}
				ctx.SendCallback();     // deliver failure to the callback (null data)
				OnFinishWebRequest(null);
				yield break;
			}
			// [RE] original CheckRequest validated error envelopes; rebuild
			// keeps the surface: error field → Fail path.
			if (parsed != null && parsed.Keys.Contains("error"))
			{
				ctx.serverMsg = parsed["error"].ToString();
			}
			ctx.SendCallback();
			Done();
		}
	}

	private void Done()
	{
		if (OnResponse != null)
			OnResponse(null);
	}

	public static HttpRequestContext Construct(string uri, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		return Construct(uri, "POST", showNetworkIndicator, callback, args);
	}

	public static HttpRequestContext Construct(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		return instance.Construct(uri, method, showNetworkIndicator, callback, null, HttpRequestContext.Option.Default, args);
	}

	public HttpRequestContext Construct(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
		// [RE] PacketUtil.JsonString(object[]) — positional JSON array body
		var packet = PacketArrayToPacketString(args);
		return new HttpRequestContext(uri, packet, method, showNetworkIndicator, callback, retryPopupCallback, opt);
	}

	public void Send(HttpRequestContext context)
	{
		context.Send();
	}

	public void Send(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		Send(Construct(uri, method, showNetworkIndicator, callback, args));
	}

	public void Send(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
		Send(Construct(uri, method, showNetworkIndicator, callback, retryPopupCallback, opt, args));
	}

	[DebuggerHidden]
	public IEnumerator SendAndWait(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		bool done = false;
		JsonData result = null;
		Send(uri, method, showNetworkIndicator, delegate(JsonData jd)
		{
			result = jd;
			done = true;
		}, args);
		while (!done)
			yield return null;
		yield return result;
	}

	[DebuggerHidden]
	public IEnumerator SendAndWait(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
		bool done = false;
		JsonData result = null;
		Send(uri, method, showNetworkIndicator, delegate(JsonData jd)
		{
			result = jd;
			done = true;
		}, retryPopupCallback, opt, args);
		while (!done)
			yield return null;
		yield return result;
	}

	public static string PacketArrayToPacketString(object[] packet)
	{
		// [RE] PacketUtil.JsonString(object[]) equivalent (LitJson in this build)
		var sb = new StringBuilder("[");
		if (packet != null)
		{
			for (int i = 0; i < packet.Length; i++)
			{
				if (i > 0) sb.Append(',');
				var v = packet[i];
				if (v == null) sb.Append("null");
				else if (v is string s) sb.Append(JsonMapper.ToJson(s));
				else if (v is bool b) sb.Append(b ? "true" : "false");
				else if (v is ulong || v is uint) sb.Append(JsonMapper.ToJson(v.ToString()));   // [RE] exact: JSON numbers lose ulong precision
				else if (v is long l && (l > 4503599627370496L || l < -4503599627370496L)) sb.Append(JsonMapper.ToJson(l.ToString()));
				else if (v is int || v is long || v is float || v is double) sb.Append(v.ToString());
				else if (v is object[] arr) sb.Append(PacketArrayToPacketString(arr));
				else sb.Append(JsonMapper.ToJson(v.ToString()));
			}
		}
		sb.Append("]");
		return sb.ToString();
	}

	private void OnFinishWebRequest(IAsyncResult result)
	{
	}

	private bool CheckRequest(JsonData responseData, ref string errorType)
	{
		if (responseData == null)
		{
			errorType = "empty";
			return false;
		}
		if (responseData.Keys.Contains("error"))
		{
			errorType = responseData["error"].ToString();
			return false;
		}
		return true;
	}

	public void RetryOnPopup()
	{
	}

	protected override void OnDestroy()
	{
		base.OnDestroy();
		m_requestQueue = new HttpRequestQueue();
		m_inflight.Clear();
	}

	public void Clear()
	{
		m_requestQueue = new HttpRequestQueue();
		m_inflight.Clear();
	}

	public static bool CommonCheck(JsonData response)
	{
		return response != null && !response.Keys.Contains("error");
	}
}
