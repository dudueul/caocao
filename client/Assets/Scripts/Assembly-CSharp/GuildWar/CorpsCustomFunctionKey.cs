using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 12)]
	public struct CorpsCustomFunctionKey : IKeyTypeDeserializable<CorpsCustomFunctionKey, corps_custom_function, key_corps_custom_function>, IKeyType<CorpsCustomFunctionKey>, IKeyType, IEquatable<CorpsCustomFunctionKey>
	{
		private int m_corps_id;

		private short m_custom_action_type;

		private int m_custom_action_key;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public short custom_action_type
		{
			get
			{
				return 0;
			}
		}

		public int custom_action_key
		{
			get
			{
				return 0;
			}
		}

		public CorpsCustomFunctionKey(int corps_id, short custom_action_type, int custom_action_key)
		{
			m_corps_id = 0;
			m_custom_action_type = 0;
			m_custom_action_key = 0;
		}

		public void DeserializeKey(key_corps_custom_function data)
		{
		}

		public void DeserializeKeyFromData(corps_custom_function data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsCustomFunctionKey left, CorpsCustomFunctionKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsCustomFunctionKey left, CorpsCustomFunctionKey right)
		{
			return false;
		}

		public bool Equals(CorpsCustomFunctionKey other)
		{
			return false;
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
		}
	}
}
