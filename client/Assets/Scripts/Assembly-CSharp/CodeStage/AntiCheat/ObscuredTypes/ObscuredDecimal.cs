using System;
using System.Runtime.InteropServices;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct ObscuredDecimal : IEquatable<ObscuredDecimal>, IFormattable
	{
		[StructLayout((LayoutKind)2, Pack = 1, Size = 16)]
		private struct DecimalLongBytesUnion
		{
			[FieldOffset(0)]
			public decimal d;

			[FieldOffset(0)]
			public long l1;

			[FieldOffset(8)]
			public long l2;

			[FieldOffset(0)]
			public byte b1;

			[FieldOffset(1)]
			public byte b2;

			[FieldOffset(2)]
			public byte b3;

			[FieldOffset(3)]
			public byte b4;

			[FieldOffset(4)]
			public byte b5;

			[FieldOffset(5)]
			public byte b6;

			[FieldOffset(6)]
			public byte b7;

			[FieldOffset(7)]
			public byte b8;

			[FieldOffset(8)]
			public byte b9;

			[FieldOffset(9)]
			public byte b10;

			[FieldOffset(10)]
			public byte b11;

			[FieldOffset(11)]
			public byte b12;

			[FieldOffset(12)]
			public byte b13;

			[FieldOffset(13)]
			public byte b14;

			[FieldOffset(14)]
			public byte b15;

			[FieldOffset(15)]
			public byte b16;
		}

		private static long cryptoKey;

		private long currentCryptoKey;

		private byte[] hiddenValue;

		private bool inited;

		private ObscuredDecimal(byte[] value)
		{
			currentCryptoKey = 0L;
			hiddenValue = null;
			inited = false;
		}

		public static decimal Encrypt(decimal value)
		{
			return 0m;
		}

		public static decimal Encrypt(decimal value, long key)
		{
			return 0m;
		}

		private static byte[] InternalEncrypt(decimal value)
		{
			return null;
		}

		private static byte[] InternalEncrypt(decimal value, long key)
		{
			return null;
		}

		public static decimal Decrypt(decimal value)
		{
			return 0m;
		}

		public static decimal Decrypt(decimal value, long key)
		{
			return 0m;
		}

		public void ApplyNewCryptoKey()
		{
		}

		public void RandomizeCryptoKey()
		{
		}

		public decimal GetEncrypted()
		{
			return 0m;
		}

		public void SetEncrypted(decimal encrypted)
		{
		}

		private decimal InternalDecrypt()
		{
			return 0m;
		}

		public static implicit operator ObscuredDecimal(decimal value)
		{
			return default(ObscuredDecimal);
		}

		public static implicit operator decimal(ObscuredDecimal value)
		{
			return 0m;
		}

		public static ObscuredDecimal operator ++(ObscuredDecimal input)
		{
			return default(ObscuredDecimal);
		}

		public static ObscuredDecimal operator --(ObscuredDecimal input)
		{
			return default(ObscuredDecimal);
		}

		public override string ToString()
		{
			return null;
		}

		public string ToString(string format)
		{
			return null;
		}

		public string ToString(IFormatProvider provider)
		{
			return null;
		}

		public string ToString(string format, IFormatProvider provider)
		{
			return null;
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public bool Equals(ObscuredDecimal obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
		}
	}
}
