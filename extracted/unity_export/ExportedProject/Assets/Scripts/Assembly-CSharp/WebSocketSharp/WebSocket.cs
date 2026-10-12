using System;
using System.Collections.Generic;
using System.IO;
using System.Net.Security;
using System.Net.Sockets;
using System.Threading;
using WebSocketSharp.Net;
using WebSocketSharp.Net.WebSockets;

namespace WebSocketSharp
{
	public class WebSocket : IDisposable
	{
		private const string _guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

		private const string _version = "13";

		private AuthenticationChallenge _authChallenge;

		private string _base64Key;

		private RemoteCertificateValidationCallback _certValidationCallback;

		private bool _client;

		private Action _closeContext;

		private CompressionMethod _compression;

		private WebSocketContext _context;

		private CookieCollection _cookies;

		private NetworkCredential _credentials;

		private string _extensions;

		private AutoResetEvent _exitReceiving;

		private object _forConn;

		private object _forEvent;

		private object _forMessageEventQueue;

		private object _forSend;

		private Func<WebSocketContext, string> _handshakeRequestChecker;

		private Logger _logger;

		private Queue<MessageEventArgs> _messageEventQueue;

		private uint _nonceCount;

		private string _origin;

		private bool _preAuth;

		private string _protocol;

		private string[] _protocols;

		private WebSocketState _readyState;

		private AutoResetEvent _receivePong;

		private bool _secure;

		private WebSocketStream _stream;

		private TcpClient _tcpClient;

		private Uri _uri;

		internal const int FragmentLength = 1016;

		private EventHandler<CloseEventArgs> OnClose__BackingField;

		private EventHandler<ErrorEventArgs> OnError__BackingField;

		private EventHandler<MessageEventArgs> OnMessage__BackingField;

		private EventHandler OnOpen__BackingField;

		internal CookieCollection CookieCollection
		{
			get
			{
				return null;
			}
		}

		internal Func<WebSocketContext, string> CustomHandshakeRequestChecker
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		internal bool IsConnected
		{
			get
			{
				return false;
			}
		}

		public CompressionMethod Compression
		{
			get
			{
				return CompressionMethod.None;
			}
			set
			{
			}
		}

		public IEnumerable<Cookie> Cookies
		{
			get
			{
				return null;
			}
		}

		public NetworkCredential Credentials
		{
			get
			{
				return null;
			}
		}

		public string Extensions
		{
			get
			{
				return null;
			}
		}

		public bool IsAlive
		{
			get
			{
				return false;
			}
		}

		public bool IsSecure
		{
			get
			{
				return false;
			}
		}

		public Logger Log
		{
			get
			{
				return null;
			}
			internal set
			{
			}
		}

		public string Origin
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string Protocol
		{
			get
			{
				return null;
			}
			internal set
			{
			}
		}

		public WebSocketState ReadyState
		{
			get
			{
				return WebSocketState.Connecting;
			}
		}

		public RemoteCertificateValidationCallback ServerCertificateValidationCallback
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public Uri Url
		{
			get
			{
				return null;
			}
		}

		public event EventHandler<CloseEventArgs> OnClose
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<ErrorEventArgs> OnError
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<MessageEventArgs> OnMessage
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnOpen
		{
			add
			{
			}
			remove
			{
			}
		}

		internal WebSocket(HttpListenerWebSocketContext context, string protocol, Logger logger)
		{
		}

		internal WebSocket(TcpListenerWebSocketContext context, string protocol, Logger logger)
		{
		}

		public WebSocket(string url, params string[] protocols)
		{
		}

		private bool acceptCloseFrame(WebSocketFrame frame)
		{
			return false;
		}

		private bool acceptDataFrame(WebSocketFrame frame)
		{
			return false;
		}

		private void acceptException(Exception exception, string message)
		{
		}

		private bool acceptFragmentedFrame(WebSocketFrame frame)
		{
			return false;
		}

		private bool acceptFragments(WebSocketFrame first)
		{
			return false;
		}

		private bool acceptFrame(WebSocketFrame frame)
		{
			return false;
		}

		private bool acceptHandshake()
		{
			return false;
		}

		private bool acceptPingFrame(WebSocketFrame frame)
		{
			return false;
		}

		private bool acceptPongFrame(WebSocketFrame frame)
		{
			return false;
		}

		private void acceptSecWebSocketExtensionsHeader(string value)
		{
		}

		private bool acceptUnsupportedFrame(WebSocketFrame frame, CloseStatusCode code, string reason)
		{
			return false;
		}

		private string checkIfAvailable(string operation, bool availableAsServer, bool availableAsConnected)
		{
			return null;
		}

		private string checkIfCanConnect()
		{
			return null;
		}

		private string checkIfValidHandshakeRequest(WebSocketContext context)
		{
			return null;
		}

		private string checkIfValidHandshakeResponse(HandshakeResponse response)
		{
			return null;
		}

		private void close(CloseStatusCode code, string reason, bool wait)
		{
		}

		private void close(PayloadData payload, bool send, bool wait)
		{
		}

		private void closeAsync(PayloadData payload, bool send, bool wait)
		{
		}

		private void closeClientResources()
		{
		}

		private bool closeHandshake(byte[] frame, int timeout, Action release)
		{
			return false;
		}

		private void closeServerResources()
		{
		}

		private bool concatenateFragmentsInto(Stream dest)
		{
			return false;
		}

		private bool connect()
		{
			return false;
		}

		private string createExtensionsRequest()
		{
			return null;
		}

		private HandshakeRequest createHandshakeRequest()
		{
			return null;
		}

		private HandshakeResponse createHandshakeResponse()
		{
			return null;
		}

		private HandshakeResponse createHandshakeResponse(HttpStatusCode code)
		{
			return null;
		}

		private MessageEventArgs dequeueFromMessageEventQueue()
		{
			return null;
		}

		private bool doHandshake()
		{
			return false;
		}

		private void enqueueToMessageEventQueue(MessageEventArgs e)
		{
		}

		private void error(string message)
		{
		}

		private void init()
		{
		}

		private void open()
		{
		}

		private HandshakeResponse receiveHandshakeResponse()
		{
			return null;
		}

		private bool send(byte[] frame)
		{
			return false;
		}

		private void send(HandshakeRequest request)
		{
		}

		private bool send(HandshakeResponse response)
		{
			return false;
		}

		private bool send(WebSocketFrame frame)
		{
			return false;
		}

		private bool send(Opcode opcode, byte[] data)
		{
			return false;
		}

		private bool send(Opcode opcode, Stream stream)
		{
			return false;
		}

		private void sendAsync(Opcode opcode, byte[] data, Action<bool> completed)
		{
		}

		private void sendAsync(Opcode opcode, Stream stream, Action<bool> completed)
		{
		}

		private bool sendFragmented(Opcode opcode, Stream stream, Mask mask, bool compressed)
		{
			return false;
		}

		private HandshakeResponse sendHandshakeRequest()
		{
			return null;
		}

		private HandshakeResponse sendHandshakeRequest(HandshakeRequest request)
		{
			return null;
		}

		private void setClientStream()
		{
		}

		private void startReceiving()
		{
		}

		private bool validateSecWebSocketAcceptHeader(string value)
		{
			return false;
		}

		private bool validateSecWebSocketExtensionsHeader(string value)
		{
			return false;
		}

		private bool validateSecWebSocketKeyHeader(string value)
		{
			return false;
		}

		private bool validateSecWebSocketProtocolHeader(string value)
		{
			return false;
		}

		private bool validateSecWebSocketVersionClientHeader(string value)
		{
			return false;
		}

		private bool validateSecWebSocketVersionServerHeader(string value)
		{
			return false;
		}

		internal void Close(HandshakeResponse response)
		{
		}

		internal void Close(HttpStatusCode code)
		{
		}

		internal void Close(CloseEventArgs e, byte[] frame, int timeout)
		{
		}

		internal void ConnectAsServer()
		{
		}

		internal static string CreateBase64Key()
		{
			return null;
		}

		internal static string CreateResponseKey(string base64Key)
		{
			return null;
		}

		internal bool Ping(byte[] frame, int timeout)
		{
			return false;
		}

		internal void Send(Opcode opcode, byte[] data, Dictionary<CompressionMethod, byte[]> cache)
		{
		}

		internal void Send(Opcode opcode, Stream stream, Dictionary<CompressionMethod, Stream> cache)
		{
		}

		public void Close()
		{
		}

		public void Close(ushort code)
		{
		}

		public void Close(CloseStatusCode code)
		{
		}

		public void Close(ushort code, string reason)
		{
		}

		public void Close(CloseStatusCode code, string reason)
		{
		}

		public void CloseAsync()
		{
		}

		public void CloseAsync(ushort code)
		{
		}

		public void CloseAsync(CloseStatusCode code)
		{
		}

		public void CloseAsync(ushort code, string reason)
		{
		}

		public void CloseAsync(CloseStatusCode code, string reason)
		{
		}

		public void Connect()
		{
		}

		public void ConnectAsync()
		{
		}

		public bool Ping()
		{
			return false;
		}

		public bool Ping(string message)
		{
			return false;
		}

		public void Send(byte[] data)
		{
		}

		public void Send(FileInfo file)
		{
		}

		public void Send(string data)
		{
		}

		public void SendAsync(byte[] data, Action<bool> completed)
		{
		}

		public void SendAsync(FileInfo file, Action<bool> completed)
		{
		}

		public void SendAsync(string data, Action<bool> completed)
		{
		}

		public void SendAsync(Stream stream, int length, Action<bool> completed)
		{
		}

		public void SetCookie(Cookie cookie)
		{
		}

		public void SetCredentials(string username, string password, bool preAuth)
		{
		}

		void IDisposable.Dispose()
		{
		}
	}
}
