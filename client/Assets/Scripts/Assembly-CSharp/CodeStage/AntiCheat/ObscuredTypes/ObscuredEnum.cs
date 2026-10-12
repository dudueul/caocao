using System;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	public struct ObscuredEnum<T> where T : struct, IConvertible, IFormattable, IComparable
	{
		private static class Helper
		{
			public static int ToInt<TEnum>(TEnum value)
			{
				return 0;
			}

			public static TEnum FromInt<TEnum>(int value)
			{
				return default(TEnum);
			}

			internal static TEnum Parse<TEnum>(string value, TEnum defaultValue)
			{
				return default(TEnum);
			}
		}

		private ObscuredInt m_internalValue;

		private ObscuredEnum(T value)
		{
			m_internalValue = default(ObscuredInt);
		}

		public static implicit operator T(ObscuredEnum<T> value)
		{
			return default(T);
		}

		public static implicit operator ObscuredEnum<T>(T value)
		{
			return default(ObscuredEnum<T>);
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public bool Equals(ObscuredEnum<T> obj)
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
