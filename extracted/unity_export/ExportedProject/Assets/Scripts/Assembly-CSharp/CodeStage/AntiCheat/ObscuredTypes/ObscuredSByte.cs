using System;
using System.Runtime.InteropServices;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct ObscuredSByte : IEquatable<ObscuredSByte>, IFormattable
	{
		private static sbyte cryptoKey;

		private sbyte currentCryptoKey;

		private sbyte hiddenValue;

		private sbyte fakeValue;

		private bool inited;

		private ObscuredSByte(sbyte value)
		{
			currentCryptoKey = 0;
			hiddenValue = 0;
			fakeValue = 0;
			inited = false;
		}

		public static void SetNewCryptoKey(sbyte newKey)
		{
		}

		public static sbyte EncryptDecrypt(sbyte value)
		{
			return 0;
		}

		public static sbyte EncryptDecrypt(sbyte value, sbyte key)
		{
			return 0;
		}

		public void ApplyNewCryptoKey()
		{
		}

		public void RandomizeCryptoKey()
		{
		}

		public sbyte GetEncrypted()
		{
			return 0;
		}

		public void SetEncrypted(sbyte encrypted)
		{
		}

		private sbyte InternalDecrypt()
		{
			return 0;
		}

		public static implicit operator ObscuredSByte(sbyte value)
		{
			return default(ObscuredSByte);
		}

		public static implicit operator sbyte(ObscuredSByte value)
		{
			return 0;
		}

		public static ObscuredSByte operator ++(ObscuredSByte input)
		{
			return default(ObscuredSByte);
		}

		public static ObscuredSByte operator --(ObscuredSByte input)
		{
			return default(ObscuredSByte);
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public bool Equals(ObscuredSByte obj)
		{
			return false;
		}

		public override string ToString()
		{
			return null;
		}

		public string ToString(string format)
		{
			return null;
		}

		public override int GetHashCode()
		{
			return 0;
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
