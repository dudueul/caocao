using System;
using System.Collections.Specialized;
using WebSocketSharp.Net;

namespace WebSocketSharp
{
	internal class HandshakeResponse : HandshakeBase
	{
		private string _code;

		private string _reason;

		public AuthenticationChallenge AuthChallenge
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

		public bool IsUnauthorized
		{
			get
			{
				return false;
			}
		}

		public bool IsWebSocketResponse
		{
			get
			{
				return false;
			}
		}

		public string Reason
		{
			get
			{
				return null;
			}
		}

		public string StatusCode
		{
			get
			{
				return null;
			}
		}

		private HandshakeResponse(Version version, NameValueCollection headers)
			: base(null, null)
		{
		}

		internal HandshakeResponse(HttpStatusCode code)
			: base(null, null)
		{
		}

		internal static HandshakeResponse CreateCloseResponse(HttpStatusCode code)
		{
			return null;
		}

		internal static HandshakeResponse Parse(string[] headerParts)
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
