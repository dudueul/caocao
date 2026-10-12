using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography.X509Certificates;
using System.Security.Principal;

namespace WebSocketSharp.Net.WebSockets
{
	public class TcpListenerWebSocketContext : WebSocketContext
	{
		private TcpClient _client;

		private CookieCollection _cookies;

		private NameValueCollection _queryString;

		private HandshakeRequest _request;

		private bool _secure;

		private WebSocketStream _stream;

		private Uri _uri;

		private IPrincipal _user;

		private WebSocket _websocket;

		internal WebSocketStream Stream
		{
			get
			{
				return null;
			}
		}

		public override CookieCollection CookieCollection
		{
			get
			{
				return null;
			}
		}

		public override NameValueCollection Headers
		{
			get
			{
				return null;
			}
		}

		public override string Host
		{
			get
			{
				return null;
			}
		}

		public override bool IsAuthenticated
		{
			get
			{
				return false;
			}
		}

		public override bool IsLocal
		{
			get
			{
				return false;
			}
		}

		public override bool IsSecureConnection
		{
			get
			{
				return false;
			}
		}

		public override bool IsWebSocketRequest
		{
			get
			{
				return false;
			}
		}

		public override string Origin
		{
			get
			{
				return null;
			}
		}

		public override NameValueCollection QueryString
		{
			get
			{
				return null;
			}
		}

		public override Uri RequestUri
		{
			get
			{
				return null;
			}
		}

		public override string SecWebSocketKey
		{
			get
			{
				return null;
			}
		}

		public override IEnumerable<string> SecWebSocketProtocols
		{
			get
			{
				return null;
			}
		}

		public override string SecWebSocketVersion
		{
			get
			{
				return null;
			}
		}

		public override IPEndPoint ServerEndPoint
		{
			get
			{
				return null;
			}
		}

		public override IPrincipal User
		{
			get
			{
				return null;
			}
		}

		public override IPEndPoint UserEndPoint
		{
			get
			{
				return null;
			}
		}

		public override WebSocket WebSocket
		{
			get
			{
				return null;
			}
		}

		internal TcpListenerWebSocketContext(TcpClient client, string protocol, bool secure, X509Certificate cert, Logger logger)
		{
		}

		internal void Close()
		{
		}

		internal void Close(HttpStatusCode code)
		{
		}

		internal void SendAuthChallenge(string challenge)
		{
		}

		internal void SetUser(AuthenticationSchemes scheme, string realm, Func<IIdentity, NetworkCredential> credentialsFinder)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
