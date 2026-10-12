using System;
using System.IO;

namespace FlatBuffers
{
	public class ByteBuffer
	{
		protected byte[] _buffer;

		private int _pos;

		private float[] floathelper;

		private int[] inthelper;

		private double[] doublehelper;

		private ulong[] ulonghelper;

		public int Length
		{
			get
			{
				return 0;
			}
		}

		public int Position
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public ByteBuffer(int size)
		{
		}

		public ByteBuffer(byte[] buffer)
		{
		}

		public ByteBuffer(byte[] buffer, int pos)
		{
		}

		public void Reset()
		{
		}

		public ByteBuffer Duplicate()
		{
			return null;
		}

		public void GrowFront(int newSize)
		{
		}

		public byte[] ToArray(int pos, int len)
		{
			return null;
		}

		public byte[] ToSizedArray()
		{
			return null;
		}

		public byte[] ToFullArray()
		{
			return null;
		}

		public ArraySegment<byte> ToArraySegment(int pos, int len)
		{
			return default(ArraySegment<byte>);
		}

		public MemoryStream ToMemoryStream(int pos, int len)
		{
			return null;
		}

		public static ushort ReverseBytes(ushort input)
		{
			return 0;
		}

		public static uint ReverseBytes(uint input)
		{
			return 0u;
		}

		public static ulong ReverseBytes(ulong input)
		{
			return 0uL;
		}

		protected void WriteLittleEndian(int offset, int count, ulong data)
		{
		}

		protected ulong ReadLittleEndian(int offset, int count)
		{
			return 0uL;
		}

		private void AssertOffsetAndLength(int offset, int length)
		{
		}

		public void PutSbyte(int offset, sbyte value)
		{
		}

		public void PutByte(int offset, byte value)
		{
		}

		public void PutByte(int offset, byte value, int count)
		{
		}

		public void Put(int offset, byte value)
		{
		}

		public void PutStringUTF8(int offset, string value)
		{
		}

		public void PutShort(int offset, short value)
		{
		}

		public void PutUshort(int offset, ushort value)
		{
		}

		public void PutInt(int offset, int value)
		{
		}

		public void PutUint(int offset, uint value)
		{
		}

		public void PutLong(int offset, long value)
		{
		}

		public void PutUlong(int offset, ulong value)
		{
		}

		public void PutFloat(int offset, float value)
		{
		}

		public void PutDouble(int offset, double value)
		{
		}

		public sbyte GetSbyte(int index)
		{
			return 0;
		}

		public byte Get(int index)
		{
			return 0;
		}

		public string GetStringUTF8(int startPos, int len)
		{
			return null;
		}

		public short GetShort(int index)
		{
			return 0;
		}

		public ushort GetUshort(int index)
		{
			return 0;
		}

		public int GetInt(int index)
		{
			return 0;
		}

		public uint GetUint(int index)
		{
			return 0u;
		}

		public long GetLong(int index)
		{
			return 0L;
		}

		public ulong GetUlong(int index)
		{
			return 0uL;
		}

		public float GetFloat(int index)
		{
			return 0f;
		}

		public double GetDouble(int index)
		{
			return 0.0;
		}
	}
}
