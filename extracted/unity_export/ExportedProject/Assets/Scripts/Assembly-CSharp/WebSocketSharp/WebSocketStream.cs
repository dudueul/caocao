using System;
using System.IO;
using System.Net.Security;
using System.Net.Sockets;
using System.Security.Cryptography.X509Certificates;
using WebSocketSharp.Net.Security;

namespace WebSocketSharp
{
	internal class WebSocketStream : IDisposable
	{
		private const int _handshakeHeadersLimitLen = 8192;

		private object _forWrite;

		private Stream _innerStream;

		private bool _secure;

		public bool DataAvailable
		{
			get
			{
				return false;
			}
		}

		public bool IsSecure
		{
			get
			{
				return false;
			}
		}

		internal WebSocketStream(Stream innerStream, bool secure)
		{
		}

		public WebSocketStream(NetworkStream innerStream)
		{
		}

		public WebSocketStream(WebSocketSharp.Net.Security.SslStream innerStream)
		{
		}

		private static byte[] readHandshakeEntityBody(Stream stream, string length)
		{
			return null;
		}

		private static string[] readHandshakeHeaders(Stream stream)
		{
			return null;
		}

		internal T ReadHandshake<T>(Func<string[], T> parser, int millisecondsTimeout) where T : HandshakeBase
		{
			return null;
		}

		internal bool Write(byte[] data)
		{
			return false;
		}

		public void Close()
		{
		}

		public static WebSocketStream CreateClientStream(TcpClient client, bool secure, string host, RemoteCertificateValidationCallback validationCallback)
		{
			return null;
		}

		public static WebSocketStream CreateServerStream(TcpClient client, bool secure, X509Certificate cert)
		{
			return null;
		}

		public void Dispose()
		{
		}

		public WebSocketFrame ReadFrame()
		{
			return null;
		}

		public void ReadFrameAsync(Action<WebSocketFrame> completed, Action<Exception> error)
		{
		}

		public HandshakeRequest ReadHandshakeRequest()
		{
			return null;
		}

		public HandshakeResponse ReadHandshakeResponse()
		{
			return null;
		}

		public bool WriteFrame(WebSocketFrame frame)
		{
			return false;
		}

		public bool WriteHandshake(HandshakeBase handshake)
		{
			return false;
		}
	}
}
