using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace WebSocketSharp.Net
{
	internal sealed class HttpConnection
	{
		private const int _bufferSize = 8192;

		private byte[] _buffer;

		private bool _chunked;

		private HttpListenerContext _context;

		private bool _contextWasBound;

		private StringBuilder _currentLine;

		private InputState _inputState;

		private RequestStream _inputStream;

		private HttpListener _lastListener;

		private LineState _lineState;

		private EndPointListener _listener;

		private ResponseStream _outputStream;

		private int _position;

		private ListenerPrefix _prefix;

		private MemoryStream _requestBuffer;

		private int _reuses;

		private bool _secure;

		private Socket _socket;

		private Stream _stream;

		private object _sync;

		private int _timeout;

		private Timer _timer;

		private WebSocketStream _websocketStream;

		public bool IsClosed
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

		public IPEndPoint LocalEndPoint
		{
			get
			{
				return null;
			}
		}

		public ListenerPrefix Prefix
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public IPEndPoint RemoteEndPoint
		{
			get
			{
				return null;
			}
		}

		public int Reuses
		{
			get
			{
				return 0;
			}
		}

		public Stream Stream
		{
			get
			{
				return null;
			}
		}

		public HttpConnection(Socket socket, EndPointListener listener)
		{
		}

		private void close()
		{
		}

		private void closeSocket()
		{
		}

		private void disposeRequestBuffer()
		{
		}

		private void disposeStream()
		{
		}

		private void disposeTimer()
		{
		}

		private void init()
		{
		}

		private static void onRead(IAsyncResult asyncResult)
		{
		}

		private static void onTimeout(object state)
		{
		}

		private bool processInput(byte[] data)
		{
			return false;
		}

		private string readLine(byte[] buffer, int offset, int length, ref int used)
		{
			return null;
		}

		private void removeConnection()
		{
		}

		private void unbind()
		{
		}

		internal void Close(bool force)
		{
		}

		public void BeginReadRequest()
		{
		}

		public void Close()
		{
		}

		public RequestStream GetRequestStream(bool chunked, long contentlength)
		{
			return null;
		}

		public ResponseStream GetResponseStream()
		{
			return null;
		}

		public WebSocketStream GetWebSocketStream()
		{
			return null;
		}

		public void SendError()
		{
		}

		public void SendError(string message, int status)
		{
		}
	}
}
