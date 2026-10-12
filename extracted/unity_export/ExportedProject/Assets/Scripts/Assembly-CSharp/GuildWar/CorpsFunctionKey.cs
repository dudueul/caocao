using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct CorpsFunctionKey : IKeyTypeDeserializable<CorpsFunctionKey, corps_function, key_corps_function>, IKeyType<CorpsFunctionKey>, IKeyType, IEquatable<CorpsFunctionKey>
	{
		private int m_corps_id;

		private int m_p_corps_function_id;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public int p_corps_function_id
		{
			get
			{
				return 0;
			}
		}

		public CorpsFunctionKey(int corps_id, int p_corps_function_id)
		{
			m_corps_id = 0;
			m_p_corps_function_id = 0;
		}

		public void DeserializeKey(key_corps_function data)
		{
		}

		public void DeserializeKeyFromData(corps_function data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsFunctionKey left, CorpsFunctionKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsFunctionKey left, CorpsFunctionKey right)
		{
			return false;
		}

		public bool Equals(CorpsFunctionKey other)
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
