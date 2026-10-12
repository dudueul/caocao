using System;
using System.Runtime.InteropServices;
using UnityEngine;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct ObscuredDouble : IEquatable<ObscuredDouble>, IFormattable
	{
		[StructLayout((LayoutKind)2, Pack = 1, Size = 8)]
		private struct DoubleLongBytesUnion
		{
			[FieldOffset(0)]
			public double d;

			[FieldOffset(0)]
			public long l;

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
		}

		private static long cryptoKey;

		[SerializeField]
		private long currentCryptoKey;

		[SerializeField]
		private byte[] hiddenValue;

		[SerializeField]
		private bool inited;

		private ObscuredDouble(byte[] value)
		{
			currentCryptoKey = 0L;
			hiddenValue = null;
			inited = false;
		}

		public static long Encrypt(double value)
		{
			return 0L;
		}

		public static long Encrypt(double value, long key)
		{
			return 0L;
		}

		private static byte[] InternalEncrypt(double value)
		{
			return null;
		}

		private static byte[] InternalEncrypt(double value, long key)
		{
			return null;
		}

		public static double Decrypt(long value)
		{
			return 0.0;
		}

		public static double Decrypt(long value, long key)
		{
			return 0.0;
		}

		public void ApplyNewCryptoKey()
		{
		}

		public void RandomizeCryptoKey()
		{
		}

		public long GetEncrypted()
		{
			return 0L;
		}

		public void SetEncrypted(long encrypted)
		{
		}

		private double InternalDecrypt()
		{
			return 0.0;
		}

		public static implicit operator ObscuredDouble(double value)
		{
			return default(ObscuredDouble);
		}

		public static implicit operator double(ObscuredDouble value)
		{
			return 0.0;
		}

		public static ObscuredDouble operator ++(ObscuredDouble input)
		{
			return default(ObscuredDouble);
		}

		public static ObscuredDouble operator --(ObscuredDouble input)
		{
			return default(ObscuredDouble);
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

		public bool Equals(ObscuredDouble obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
		}
	}
}
