using System;
using System.IO;

namespace WebSocketSharp.Net
{
	internal class ChunkedRequestStream : RequestStream
	{
		private const int _bufferSize = 8192;

		private HttpListenerContext _context;

		private ChunkStream _decoder;

		private bool _disposed;

		private bool _noMoreData;

		public ChunkStream Decoder
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public ChunkedRequestStream(HttpListenerContext context, Stream stream, byte[] buffer, int offset, int length)
			: base(null, null, 0, 0)
		{
		}

		private void onRead(IAsyncResult asyncResult)
		{
		}

		public override IAsyncResult BeginRead(byte[] buffer, int offset, int count, AsyncCallback callback, object state)
		{
			return null;
		}

		public override void Close()
		{
		}

		public override int EndRead(IAsyncResult asyncResult)
		{
			return 0;
		}

		public override int Read(byte[] buffer, int offset, int count)
		{
			return 0;
		}
	}
}
