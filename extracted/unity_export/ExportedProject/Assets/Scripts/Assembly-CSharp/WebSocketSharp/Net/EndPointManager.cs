using System.Collections.Generic;
using System.Net;

namespace WebSocketSharp.Net
{
	internal sealed class EndPointManager
	{
		private static Dictionary<IPAddress, Dictionary<int, EndPointListener>> _ipToEndpoints;

		private EndPointManager()
		{
		}

		private static void addPrefix(string uriPrefix, HttpListener httpListener)
		{
		}

		private static EndPointListener getEndPointListener(IPAddress address, int port, HttpListener httpListener, bool secure)
		{
			return null;
		}

		private static void removePrefix(string uriPrefix, HttpListener httpListener)
		{
		}

		public static void AddListener(HttpListener httpListener)
		{
		}

		public static void AddPrefix(string uriPrefix, HttpListener httpListener)
		{
		}

		public static void RemoveEndPoint(EndPointListener epListener, IPEndPoint endpoint)
		{
		}

		public static void RemoveListener(HttpListener httpListener)
		{
		}

		public static void RemovePrefix(string uriPrefix, HttpListener httpListener)
		{
		}
	}
}
