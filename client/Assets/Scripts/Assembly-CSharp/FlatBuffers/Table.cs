using System;

namespace FlatBuffers
{
	public struct Table
	{
		public int bb_pos;

		public ByteBuffer bb;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int __offset(int vtableOffset)
		{
			return 0;
		}

		public static int __offset(int vtableOffset, int offset, ByteBuffer bb)
		{
			return 0;
		}

		public int __indirect(int offset)
		{
			return 0;
		}

		public static int __indirect(int offset, ByteBuffer bb)
		{
			return 0;
		}

		public string __string(int offset)
		{
			return null;
		}

		public int __vector_len(int offset)
		{
			return 0;
		}

		public int __vector(int offset)
		{
			return 0;
		}

		public ArraySegment<byte>? __vector_as_arraysegment(int offset)
		{
			return null;
		}

		public T __union<T>(int offset) where T : struct, IFlatbufferObject
		{
			return default(T);
		}

		public static bool __has_identifier(ByteBuffer bb, string ident)
		{
			return false;
		}

		public static int CompareStrings(int offset_1, int offset_2, ByteBuffer bb)
		{
			return 0;
		}

		public static int CompareStrings(int offset_1, byte[] key, ByteBuffer bb)
		{
			return 0;
		}
	}
}
