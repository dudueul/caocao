using System.IO;

namespace Snappy.Sharp
{
	public static class Snappy
	{
		internal const int LITERAL = 0;

		internal const int COPY_1_BYTE_OFFSET = 1;

		internal const int COPY_2_BYTE_OFFSET = 2;

		internal const int COPY_4_BYTE_OFFSET = 3;

		private static SnappyCompressor compressor;

		private static SnappyDecompressor decompressor;

		public static int MaxCompressedLength(int sourceLength)
		{
			return 0;
		}

		public static byte[] Compress(byte[] uncompressed)
		{
			return null;
		}

		public static void Compress(Stream uncompressed, SnappyStream compressed)
		{
		}

		public static int GetUncompressedLength(byte[] compressed, int offset = 0)
		{
			return 0;
		}

		public static byte[] Uncompress(byte[] compressed)
		{
			return null;
		}

		public static void Uncompress(SnappyStream compressed, StreamWriter uncompressed)
		{
		}
	}
}
