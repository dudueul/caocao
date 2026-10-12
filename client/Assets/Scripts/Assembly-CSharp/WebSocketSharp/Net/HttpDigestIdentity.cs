using System.Collections.Specialized;
using System.Security.Principal;

namespace WebSocketSharp.Net
{
	public class HttpDigestIdentity : GenericIdentity
	{
		private NameValueCollection _parameters;

		public string Algorithm
		{
			get
			{
				return null;
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

		public string Nonce
		{
			get
			{
				return null;
			}
		}

		public string Opaque
		{
			get
			{
				return null;
			}
		}

		public string Qop
		{
			get
			{
				return null;
			}
		}

		public string Realm
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

		internal HttpDigestIdentity(NameValueCollection parameters)
			: base(null, null)
		{
		}

		internal bool IsValid(string password, string realm, string method, string entity)
		{
			return false;
		}
	}
}
