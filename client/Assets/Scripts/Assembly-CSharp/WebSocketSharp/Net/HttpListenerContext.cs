using System;
using System.Security.Principal;
using WebSocketSharp.Net.WebSockets;

namespace WebSocketSharp.Net
{
	public sealed class HttpListenerContext
	{
		private HttpConnection _connection;

		private string _error;

		private int _errorStatus;

		private HttpListenerRequest _request;

		private HttpListenerResponse _response;

		private IPrincipal _user;

		internal HttpListener Listener;

		internal HttpConnection Connection
		{
			get
			{
				return null;
			}
		}

		internal string ErrorMessage
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		internal int ErrorStatus
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		internal bool HasError
		{
			get
			{
				return false;
			}
		}

		public HttpListenerRequest Request
		{
			get
			{
				return null;
			}
		}

		public HttpListenerResponse Response
		{
			get
			{
				return null;
			}
		}

		public IPrincipal User
		{
			get
			{
				return null;
			}
		}

		internal HttpListenerContext(HttpConnection connection)
		{
		}

		internal void SetUser(AuthenticationSchemes scheme, string realm, Func<IIdentity, NetworkCredential> credentialsFinder)
		{
		}

		public HttpListenerWebSocketContext AcceptWebSocket(string protocol, Logger logger)
		{
			return null;
		}
	}
}
