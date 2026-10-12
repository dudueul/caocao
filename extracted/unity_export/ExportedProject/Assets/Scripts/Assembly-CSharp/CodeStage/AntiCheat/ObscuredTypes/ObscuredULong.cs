using System;
using System.Runtime.InteropServices;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct ObscuredULong : IEquatable<ObscuredULong>, IFormattable
	{
		private static ulong cryptoKey;

		private ulong currentCryptoKey;

		private ulong hiddenValue;

		private bool inited;

		private ObscuredULong(ulong value)
		{
			currentCryptoKey = 0uL;
			hiddenValue = 0uL;
			inited = false;
		}

		public static ulong Encrypt(ulong value)
		{
			return 0uL;
		}

		public static ulong Decrypt(ulong value)
		{
			return 0uL;
		}

		public static ulong Encrypt(ulong value, ulong key)
		{
			return 0uL;
		}

		public static ulong Decrypt(ulong value, ulong key)
		{
			return 0uL;
		}

		public void ApplyNewCryptoKey()
		{
		}

		public void RandomizeCryptoKey()
		{
		}

		public ulong GetEncrypted()
		{
			return 0uL;
		}

		public void SetEncrypted(ulong encrypted)
		{
		}

		private ulong InternalDecrypt()
		{
			return 0uL;
		}

		public static implicit operator ObscuredULong(ulong value)
		{
			return default(ObscuredULong);
		}

		public static implicit operator ulong(ObscuredULong value)
		{
			return 0uL;
		}

		public static ObscuredULong operator ++(ObscuredULong input)
		{
			return default(ObscuredULong);
		}

		public static ObscuredULong operator --(ObscuredULong input)
		{
			return default(ObscuredULong);
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public bool Equals(ObscuredULong obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
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
	}
}
