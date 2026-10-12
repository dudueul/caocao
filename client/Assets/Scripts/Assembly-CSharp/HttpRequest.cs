using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net;
using System.Text;
using LitJson;

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
		}

		public new HttpRequestContext Dequeue()
		{
			return null;
		}

		private bool FindRequestNeed2ShowIndicator()
		{
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

		private static int m_timeout;

		private static int m_timeoutOnAutoRetry;

		private static int m_maxAutoRetry;

		public HttpWebRequest request;

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
				return false;
			}
		}

		public bool disabledRetryPopup
		{
			get
			{
				return false;
			}
		}

		public int timeout
		{
			get
			{
				return 0;
			}
		}

		public int maxTimeoutRetry
		{
			get
			{
				return 0;
			}
		}

		public RequestStatus status
		{
			get
			{
				return RequestStatus.init;
			}
			set
			{
			}
		}

		public string absolutePath
		{
			get
			{
				return null;
			}
		}

		public HttpRequestContext(string uri, string packet, string method, bool showNetworkIndicator, ResponseCallback responseCallback, RetryPopupCallback retryPopupCallback, Option opt)
		{
		}

		public override string ToString()
		{
			return null;
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public static bool operator ==(HttpRequestContext A, HttpRequestContext B)
		{
			return false;
		}

		public static bool operator !=(HttpRequestContext A, HttpRequestContext B)
		{
			return false;
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public void Send()
		{
		}

		public bool NeedSession()
		{
			return false;
		}

		public bool NeedCypher()
		{
			return false;
		}

		public void Fail(object errorType)
		{
		}

		public void SendCallback()
		{
		}

		public void Retry()
		{
		}

		public bool IsAvaliableRetryQuietly()
		{
			return false;
		}
	}

	private class OrderedResponseCallback : IComparable
	{
		public ResponseCallback responseHandler;

		public string path;

		public int priority;

		int IComparable.CompareTo(object b)
		{
			return 0;
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

	protected override void Awake()
	{
	}

	public void AddResponseHandler(string path, ResponseCallback listener, int priority)
	{
	}

	public void RemoveResponseHandler(string path, ResponseCallback listener)
	{
	}

	private void Update()
	{
	}

	private void Done()
	{
	}

	public static HttpRequestContext Construct(string uri, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		return null;
	}

	public static HttpRequestContext Construct(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		return null;
	}

	public HttpRequestContext Construct(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
		return null;
	}

	public void Send(HttpRequestContext context)
	{
	}

	public void Send(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
	}

	public void Send(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
	}

	[DebuggerHidden]
	public IEnumerator SendAndWait(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, params object[] args)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator SendAndWait(string uri, string method, bool showNetworkIndicator, ResponseCallback callback, RetryPopupCallback retryPopupCallback, HttpRequestContext.Option opt, params object[] args)
	{
		return null;
	}

	public static string PacketArrayToPacketString(object[] packet)
	{
		return null;
	}

	private void OnFinishWebRequest(IAsyncResult result)
	{
	}

	private bool CheckRequest(JsonData responseData, ref string errorType)
	{
		return false;
	}

	public void RetryOnPopup()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void Clear()
	{
	}

	public static bool CommonCheck(JsonData response)
	{
		return false;
	}
}
