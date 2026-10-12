using System.Collections.Specialized;

namespace WebSocketSharp.Net
{
	internal class AuthenticationChallenge : AuthenticationBase
	{
		public string Domain
		{
			get
			{
				return null;
			}
		}

		public string Stale
		{
			get
			{
				return null;
			}
		}

		private AuthenticationChallenge(AuthenticationSchemes scheme, NameValueCollection parameters)
			: base(AuthenticationSchemes.None, null)
		{
		}

		internal AuthenticationChallenge(AuthenticationSchemes scheme, string realm)
			: base(AuthenticationSchemes.None, null)
		{
		}

		internal static AuthenticationChallenge CreateBasicChallenge(string realm)
		{
			return null;
		}

		internal static AuthenticationChallenge CreateDigestChallenge(string realm)
		{
			return null;
		}

		internal static AuthenticationChallenge Parse(string value)
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
	}
}
