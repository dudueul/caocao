using System;
using System.ComponentModel;
using System.Runtime.Serialization;

namespace WebSocketSharp.Net
{
	[Serializable]
	public class HttpListenerException : Win32Exception
	{
		public override int ErrorCode
		{
			get
			{
				return 0;
			}
		}

		protected HttpListenerException(SerializationInfo serializationInfo, StreamingContext streamingContext)
		{
		}

		public HttpListenerException()
		{
		}

		public HttpListenerException(int errorCode)
		{
		}

		public HttpListenerException(int errorCode, string message)
		{
		}
	}
}
