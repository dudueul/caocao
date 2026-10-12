namespace WebSocketSharp.Net
{
	internal class Chunk
	{
		private byte[] _data;

		private int _offset;

		public int ReadLeft
		{
			get
			{
				return 0;
			}
		}

		public Chunk(byte[] data)
		{
		}

		public int Read(byte[] buffer, int offset, int size)
		{
			return 0;
		}
	}
}
