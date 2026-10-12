using System;
using System.IO;

namespace WebSocketSharp.Net
{
	internal class ResponseStream : Stream
	{
		private static byte[] _crlf;

		private bool _disposed;

		private bool _ignoreErrors;

		private HttpListenerResponse _response;

		private Stream _stream;

		private bool _trailerSent;

		public override bool CanRead
		{
			get
			{
				return false;
			}
		}

		public override bool CanSeek
		{
			get
			{
				return false;
			}
		}

		public override bool CanWrite
		{
			get
			{
				return false;
			}
		}

		public override long Length
		{
			get
			{
				return 0L;
			}
		}

		public override long Position
		{
			get
			{
				return 0L;
			}
			set
			{
			}
		}

		internal ResponseStream(Stream stream, HttpListenerResponse response, bool ignoreErrors)
		{
		}

		private static byte[] getChunkSizeBytes(int size, bool final)
		{
			return null;
		}

		private MemoryStream getHeaders(bool closing)
		{
			return null;
		}

		internal void WriteInternally(byte[] buffer, int offset, int count)
		{
		}

		public override IAsyncResult BeginRead(byte[] buffer, int offset, int count, AsyncCallback callback, object state)
		{
			return null;
		}

		public override IAsyncResult BeginWrite(byte[] buffer, int offset, int count, AsyncCallback callback, object state)
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

		public override void EndWrite(IAsyncResult asyncResult)
		{
		}

		public override void Flush()
		{
		}

		public override int Read(byte[] buffer, int offset, int count)
		{
			return 0;
		}

		public override long Seek(long offset, SeekOrigin origin)
		{
			return 0L;
		}

		public override void SetLength(long value)
		{
		}

		public override void Write(byte[] buffer, int offset, int count)
		{
		}
	}
}
