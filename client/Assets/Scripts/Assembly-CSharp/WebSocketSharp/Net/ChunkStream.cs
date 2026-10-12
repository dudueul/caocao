using System.Collections.Generic;
using System.Text;

namespace WebSocketSharp.Net
{
	internal class ChunkStream
	{
		private int _chunkRead;

		private int _chunkSize;

		private List<Chunk> _chunks;

		private bool _gotit;

		private WebHeaderCollection _headers;

		private StringBuilder _saved;

		private bool _sawCR;

		private InputChunkState _state;

		private int _trailerState;

		internal WebHeaderCollection Headers
		{
			get
			{
				return null;
			}
		}

		public int ChunkLeft
		{
			get
			{
				return 0;
			}
		}

		public bool WantMore
		{
			get
			{
				return false;
			}
		}

		public ChunkStream(byte[] buffer, int offset, int size, WebHeaderCollection headers)
		{
		}

		public ChunkStream(WebHeaderCollection headers)
		{
		}

		private InputChunkState readCRLF(byte[] buffer, ref int offset, int size)
		{
			return InputChunkState.None;
		}

		private int readFromChunks(byte[] buffer, int offset, int size)
		{
			return 0;
		}

		private InputChunkState readTrailer(byte[] buffer, ref int offset, int size)
		{
			return InputChunkState.None;
		}

		private static string removeChunkExtension(string input)
		{
			return null;
		}

		private InputChunkState setChunkSize(byte[] buffer, ref int offset, int size)
		{
			return InputChunkState.None;
		}

		private static void throwProtocolViolation(string message)
		{
		}

		private void write(byte[] buffer, ref int offset, int size)
		{
		}

		private InputChunkState writeBody(byte[] buffer, ref int offset, int size)
		{
			return InputChunkState.None;
		}

		public int Read(byte[] buffer, int offset, int size)
		{
			return 0;
		}

		public void ResetBuffer()
		{
		}

		public void Write(byte[] buffer, int offset, int size)
		{
		}

		public void WriteAndReadBack(byte[] buffer, int offset, int size, ref int read)
		{
		}
	}
}
