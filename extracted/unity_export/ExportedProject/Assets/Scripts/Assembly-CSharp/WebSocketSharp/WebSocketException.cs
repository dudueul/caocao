using System;

namespace WebSocketSharp
{
	public class WebSocketException : Exception
	{
		public CloseStatusCode Code { get; private set; }

		internal WebSocketException()
		{
		}

		internal WebSocketException(string message)
		{
		}

		internal WebSocketException(CloseStatusCode code)
		{
		}

		internal WebSocketException(string message, Exception innerException)
		{
		}

		internal WebSocketException(CloseStatusCode code, string message)
		{
		}

		internal WebSocketException(CloseStatusCode code, string message, Exception innerException)
		{
		}
	}
}
