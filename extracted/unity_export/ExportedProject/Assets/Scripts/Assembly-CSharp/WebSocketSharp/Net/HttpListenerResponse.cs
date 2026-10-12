using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace WebSocketSharp.Net
{
	public sealed class HttpListenerResponse : IDisposable
	{
		private bool _chunked;

		private Encoding _contentEncoding;

		private long _contentLength;

		private bool _contentLengthSet;

		private string _contentType;

		private HttpListenerContext _context;

		private CookieCollection _cookies;

		private bool _disposed;

		private bool _forceCloseChunked;

		private WebHeaderCollection _headers;

		private bool _headersSent;

		private bool _keepAlive;

		private string _location;

		private ResponseStream _outputStream;

		private int _statusCode;

		private string _statusDescription;

		private Version _version;

		internal bool CloseConnection
		{
			get
			{
				return false;
			}
		}

		internal bool ForceCloseChunked
		{
			get
			{
				return false;
			}
		}

		internal bool HeadersSent
		{
			get
			{
				return false;
			}
		}

		public Encoding ContentEncoding
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public long ContentLength64
		{
			get
			{
				return 0L;
			}
			set
			{
			}
		}

		public string ContentType
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public CookieCollection Cookies
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public WebHeaderCollection Headers
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool KeepAlive
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public Stream OutputStream
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
			set
			{
			}
		}

		public string RedirectLocation
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool SendChunked
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public int StatusCode
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public string StatusDescription
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		internal HttpListenerResponse(HttpListenerContext context)
		{
		}

		private bool canAddOrUpdate(Cookie cookie)
		{
			return false;
		}

		private void checkDisposed()
		{
		}

		private void checkDisposedOrHeadersSent()
		{
		}

		private void close(bool force)
		{
		}

		[DebuggerHidden]
		private IEnumerable<Cookie> findCookie(Cookie cookie)
		{
			return null;
		}

		internal void SendHeaders(bool closing, MemoryStream stream)
		{
		}

		public void Abort()
		{
		}

		public void AddHeader(string name, string value)
		{
		}

		public void AppendCookie(Cookie cookie)
		{
		}

		public void AppendHeader(string name, string value)
		{
		}

		public void Close()
		{
		}

		public void Close(byte[] responseEntity, bool willBlock)
		{
		}

		public void CopyFrom(HttpListenerResponse templateResponse)
		{
		}

		public void Redirect(string url)
		{
		}

		public void SetCookie(Cookie cookie)
		{
		}

		void IDisposable.Dispose()
		{
		}
	}
}
