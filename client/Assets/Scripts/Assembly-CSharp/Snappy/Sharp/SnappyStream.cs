using System;
using System.IO;
using System.IO.Compression;

namespace Snappy.Sharp
{
	public class SnappyStream : Stream
	{
		private const int BLOCK_LOG = 15;

		private const int BLOCK_SIZE = 32768;

		private Stream stream;

		private readonly CompressionMode compressionMode;

		private readonly bool leaveStreamOpen;

		private static readonly byte[] StreamHeader;

		private const byte StreamIdentifier = byte.MaxValue;

		private const byte CompressedType = 0;

		private const byte UncompressedType = 1;

		private readonly byte[] internalBuffer;

		private int internalBufferIndex;

		private int internalBufferLength;

		private readonly SnappyCompressor compressor;

		private readonly SnappyDecompressor decompressor;

		public Stream BaseStream
		{
			get
			{
				return null;
			}
		}

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

		public SnappyStream(Stream s, CompressionMode mode)
		{
		}

		public SnappyStream(Stream s, CompressionMode mode, bool leaveOpen, bool checksum)
		{
		}

		public override void Flush()
		{
		}

		public override IAsyncResult BeginRead(byte[] buffer, int offset, int count, AsyncCallback callback, object state)
		{
			return null;
		}

		public override int EndRead(IAsyncResult asyncResult)
		{
			return 0;
		}

		public override int Read(byte[] buffer, int offset, int count)
		{
			return 0;
		}

		private int ProcessRemainingInternalBuffer(byte[] buffer, int offset, int count)
		{
			return 0;
		}

		private int GetChunkUncompressedLength()
		{
			return 0;
		}

		public override IAsyncResult BeginWrite(byte[] buffer, int offset, int count, AsyncCallback callback, object state)
		{
			return null;
		}

		public override void EndWrite(IAsyncResult asyncResult)
		{
		}

		public override void Write(byte[] buffer, int offset, int count)
		{
		}

		protected override void Dispose(bool disposing)
		{
		}

		public override long Seek(long offset, SeekOrigin origin)
		{
			return 0L;
		}

		public override void SetLength(long value)
		{
		}

		private void CheckStreamHeader()
		{
		}

		private void CheckStreamIdentifier()
		{
		}
	}
}
