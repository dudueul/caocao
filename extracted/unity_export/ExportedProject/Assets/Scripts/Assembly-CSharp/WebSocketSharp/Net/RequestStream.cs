using System;
using System.IO;

namespace WebSocketSharp.Net
{
	internal class RequestStream : Stream
	{
		private byte[] _buffer;

		private bool _disposed;

		private int _length;

		private int _offset;

		private long _remainingBody;

		private Stream _stream;

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

		internal RequestStream(Stream stream, byte[] buffer, int offset, int length)
		{
		}

		internal RequestStream(Stream stream, byte[] buffer, int offset, int length, long contentlength)
		{
		}

		private int fillFromBuffer(byte[] buffer, int offset, int count)
		{
			return 0;
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
