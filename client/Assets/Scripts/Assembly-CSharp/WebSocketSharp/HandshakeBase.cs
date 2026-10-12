using System;
using System.Collections.Specialized;
using System.Text;

namespace WebSocketSharp
{
	internal abstract class HandshakeBase
	{
		private NameValueCollection _headers;

		private Version _version;

		internal byte[] EntityBodyData;

		protected const string CrLf = "\r\n";

		public string EntityBody
		{
			get
			{
				return null;
			}
		}

		public NameValueCollection Headers
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

		protected HandshakeBase(Version version, NameValueCollection headers)
		{
		}

		private static Encoding getEncoding(string contentType)
		{
			return null;
		}

		public byte[] ToByteArray()
		{
			return null;
		}
	}
}
