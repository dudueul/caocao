using System.Collections.Specialized;
using System.Security.Principal;

namespace WebSocketSharp.Net
{
	internal class AuthenticationResponse : AuthenticationBase
	{
		private uint _nonceCount;

		internal uint NonceCount
		{
			get
			{
				return 0u;
			}
		}

		public string Cnonce
		{
			get
			{
				return null;
			}
		}

		public string Nc
		{
			get
			{
				return null;
			}
		}

		public string Password
		{
			get
			{
				return null;
			}
		}

		public string Response
		{
			get
			{
				return null;
			}
		}

		public string Uri
		{
			get
			{
				return null;
			}
		}

		public string UserName
		{
			get
			{
				return null;
			}
		}

		private AuthenticationResponse(AuthenticationSchemes scheme, NameValueCollection parameters)
			: base(AuthenticationSchemes.None, null)
		{
		}

		internal AuthenticationResponse(NetworkCredential credentials)
			: base(AuthenticationSchemes.None, null)
		{
		}

		internal AuthenticationResponse(AuthenticationChallenge challenge, NetworkCredential credentials, uint nonceCount)
			: base(AuthenticationSchemes.None, null)
		{
		}

		internal AuthenticationResponse(AuthenticationSchemes scheme, NameValueCollection parameters, NetworkCredential credentials, uint nonceCount)
			: base(AuthenticationSchemes.None, null)
		{
		}

		private static string createA1(string username, string password, string realm)
		{
			return null;
		}

		private static string createA1(string username, string password, string realm, string nonce, string cnonce)
		{
			return null;
		}

		private static string createA2(string method, string uri)
		{
			return null;
		}

		private static string createA2(string method, string uri, string entity)
		{
			return null;
		}

		private static string hash(string value)
		{
			return null;
		}

		private void initAsDigest()
		{
		}

		internal static string CreateRequestDigest(NameValueCollection parameters)
		{
			return null;
		}

		internal static AuthenticationResponse Parse(string value)
		{
			return null;
		}

		internal static NameValueCollection ParseBasicCredentials(string value)
		{
			return null;
		}

		internal override string ToBasicString()
		{
			return null;
		}

		internal override string ToDigestString()
		{
			return null;
		}

		public IIdentity ToIdentity()
		{
			return null;
		}
	}
}
