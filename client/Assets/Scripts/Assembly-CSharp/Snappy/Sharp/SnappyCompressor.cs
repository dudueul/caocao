using System;

namespace Snappy.Sharp
{
	public class SnappyCompressor
	{
		private const int BLOCK_LOG = 15;

		private const int BLOCK_SIZE = 32768;

		private const int INPUT_MARGIN_BYTES = 15;

		private const int MAX_HASH_TABLE_BITS = 14;

		private const int MAX_HASH_TABLE_SIZE = 16384;

		private readonly Func<byte[], int, int, int, int> FindMatchLength;

		public SnappyCompressor()
		{
		}

		internal SnappyCompressor(int intPtrBytes)
		{
		}

		public int MaxCompressedLength(int sourceLength)
		{
			return 0;
		}

		public int Compress(byte[] uncompressed, int uncompressedOffset, int uncompressedLength, byte[] compressed)
		{
			return 0;
		}

		public int Compress(byte[] uncompressed, int uncompressedOffset, int uncompressedLength, byte[] compressed, int compressedOffset)
		{
			return 0;
		}

		internal int CompressInternal(byte[] uncompressed, int uncompressedOffset, int uncompressedLength, byte[] compressed, int compressedOffset)
		{
			return 0;
		}

		internal int CompressFragment(byte[] input, int inputOffset, int inputSize, byte[] output, int outputIndex, short[] hashTable)
		{
			return 0;
		}

		private int EmitCopyLessThan64(byte[] output, int outputIndex, int offset, int length)
		{
			return 0;
		}

		private int EmitCopy(byte[] compressed, int compressedIndex, int offset, int length)
		{
			return 0;
		}

		private int FindMatchLengthBasic(byte[] s1, int s1Index, int s2Index, int s2Limit)
		{
			return 0;
		}

		private int FindMatchLength32(byte[] s1, int s1Index, int s2Index, int s2Limit)
		{
			return 0;
		}

		private int FindMatchLength64(byte[] s1, int s1Index, int s2Index, int s2Limit)
		{
			return 0;
		}

		internal int EmitLiteral(byte[] output, int outputIndex, byte[] literal, int literalIndex, int length, bool allowFastPath)
		{
			return 0;
		}

		internal int EmitLiteralTag(byte[] output, int outputIndex, int size)
		{
			return 0;
		}

		internal int GetHashTableSize(int inputSize)
		{
			return 0;
		}

		private uint Hash(uint bytes, int shift)
		{
			return 0u;
		}

		internal int WriteUncomressedLength(byte[] compressed, int compressedOffset, int uncompressedLength)
		{
			return 0;
		}

		internal short[] GetHashTable(int inputLength)
		{
			return null;
		}
	}
}
