using System.Net;

namespace WebSocketSharp.Net
{
	internal sealed class ListenerPrefix
	{
		private IPAddress[] _addresses;

		private string _host;

		private string _original;

		private string _path;

		private ushort _port;

		private bool _secure;

		public HttpListener Listener;

		public IPAddress[] Addresses
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string Host
		{
			get
			{
				return null;
			}
		}

		public string Path
		{
			get
			{
				return null;
			}
		}

		public int Port
		{
			get
			{
				return 0;
			}
		}

		public bool Secure
		{
			get
			{
				return false;
			}
		}

		public ListenerPrefix(string uriPrefix)
		{
		}

		private void parse(string uriPrefix)
		{
		}

		public static void CheckUriPrefix(string uriPrefix)
		{
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public override string ToString()
		{
			return null;
		}
	}
}
