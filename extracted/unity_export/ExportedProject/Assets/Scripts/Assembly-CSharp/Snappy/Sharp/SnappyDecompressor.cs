namespace Snappy.Sharp
{
	public class SnappyDecompressor
	{
		private const int MAX_INCREMENT_COPY_OVERFLOW = 10;

		private const int bitMask = 128;

		private static readonly uint[] wordmask;

		private static readonly ushort[] opLookupTable;

		public int[] ReadUncompressedLength(byte[] data, int offset)
		{
			return null;
		}

		private static int UpdateSum(byte[] data, int offset, int currentShift, int sum)
		{
			return 0;
		}

		public byte[] Decompress(byte[] compressed, int compressedOffset, int compressedSize)
		{
			return null;
		}

		public int Decompress(byte[] input, int inputOffset, int inputSize, byte[] output, int outputOffset, int outputLimit)
		{
			return 0;
		}

		private int[] DecompressTagSlow(byte[] input, int ipIndex, byte[] output, int outputLimit, int outputOffset, int opIndex)
		{
			return null;
		}

		private void CopyLiteral(byte[] input, int ipIndex, byte[] output, int opIndex, int length)
		{
		}

		private void CopyCopy(byte[] output, int length, int opIndex, int outputLimit, int copyOffset)
		{
		}

		private void IncrementalCopy(byte[] source, int srcIndex, byte[] output, int opIndex, int length)
		{
		}

		private void IncrementalCopyFastPath(byte[] output, int srcIndex, int opIndex, int length)
		{
		}
	}
}
