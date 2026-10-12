using System;
using System.Collections.Specialized;
using System.IO;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace WebSocketSharp.Net
{
	public sealed class HttpListenerRequest
	{
		private static byte[] _100continue;

		private string[] _acceptTypes;

		private bool _chunked;

		private Encoding _contentEncoding;

		private long _contentLength;

		private bool _contentLengthWasSet;

		private HttpListenerContext _context;

		private CookieCollection _cookies;

		private WebHeaderCollection _headers;

		private Guid _identifier;

		private Stream _inputStream;

		private bool _keepAlive;

		private bool _keepAliveWasSet;

		private string _method;

		private NameValueCollection _queryString;

		private Uri _referer;

		private string _uri;

		private Uri _url;

		private string[] _userLanguages;

		private Version _version;

		private bool _websocketRequest;

		private bool _websocketRequestWasSet;

		public string[] AcceptTypes
		{
			get
			{
				return null;
			}
		}

		public int ClientCertificateError
		{
			get
			{
				return 0;
			}
		}

		public Encoding ContentEncoding
		{
			get
			{
				return null;
			}
		}

		public long ContentLength64
		{
			get
			{
				return 0L;
			}
		}

		public string ContentType
		{
			get
			{
				return null;
			}
		}

		public CookieCollection Cookies
		{
			get
			{
				return null;
			}
		}

		public bool HasEntityBody
		{
			get
			{
				return false;
			}
		}

		public NameValueCollection Headers
		{
			get
			{
				return null;
			}
		}

		public string HttpMethod
		{
			get
			{
				return null;
			}
		}

		public Stream InputStream
		{
			get
			{
				return null;
			}
		}

		public bool IsAuthenticated
		{
			get
			{
				return false;
			}
		}

		public bool IsLocal
		{
			get
			{
				return false;
			}
		}

		public bool IsSecureConnection
		{
			get
			{
				return false;
			}
		}

		public bool IsWebSocketRequest
		{
			get
			{
				return false;
			}
		}

		public bool KeepAlive
		{
			get
			{
				return false;
			}
		}

		public IPEndPoint LocalEndPoint
		{
			get
			{
				return null;
			}
		}

		public Version ProtocolVersion
		{
			get
			{
				return null;
			}
		}

		public NameValueCollection QueryString
		{
			get
			{
				return null;
			}
		}

		public string RawUrl
		{
			get
			{
				return null;
			}
		}

		public IPEndPoint RemoteEndPoint
		{
			get
			{
				return null;
			}
		}

		public Guid RequestTraceIdentifier
		{
			get
			{
				return default(Guid);
			}
		}

		public Uri Url
		{
			get
			{
				return null;
			}
		}

		public Uri UrlReferrer
		{
			get
			{
				return null;
			}
		}

		public string UserAgent
		{
			get
			{
				return null;
			}
		}

		public string UserHostAddress
		{
			get
			{
				return null;
			}
		}

		public string UserHostName
		{
			get
			{
				return null;
			}
		}

		public string[] UserLanguages
		{
			get
			{
				return null;
			}
		}

		internal HttpListenerRequest(HttpListenerContext context)
		{
		}

		private static bool tryCreateVersion(string version, out Version result)
		{
			result = null;
			return false;
		}

		internal void AddHeader(string header)
		{
		}

		internal void FinishInitialization()
		{
		}

		internal bool FlushInput()
		{
			return false;
		}

		internal void SetRequestLine(string requestLine)
		{
		}

		public IAsyncResult BeginGetClientCertificate(AsyncCallback requestCallback, object state)
		{
			return null;
		}

		public X509Certificate2 EndGetClientCertificate(IAsyncResult asyncResult)
		{
			return null;
		}

		public X509Certificate2 GetClientCertificate()
		{
			return null;
		}

		public override string ToString()
		{
			return null;
		}
	}
}
