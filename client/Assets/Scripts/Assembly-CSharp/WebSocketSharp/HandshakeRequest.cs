using System;
using System.Collections.Specialized;
using WebSocketSharp.Net;

namespace WebSocketSharp
{
	internal class HandshakeRequest : HandshakeBase
	{
		private string _method;

		private string _uri;

		private bool _websocketRequest;

		private bool _websocketRequestWasSet;

		public AuthenticationResponse AuthResponse
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

		public string HttpMethod
		{
			get
			{
				return null;
			}
		}

		public bool IsWebSocketRequest
		{
			get
			{
				return false;
			}
		}

		public string RequestUri
		{
			get
			{
				return null;
			}
		}

		private HandshakeRequest(Version version, NameValueCollection headers)
			: base(null, null)
		{
		}

		internal HandshakeRequest(string pathAndQuery)
			: base(null, null)
		{
		}

		internal static HandshakeRequest Parse(string[] headerParts)
		{
			return null;
		}

		public void SetCookies(CookieCollection cookies)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
