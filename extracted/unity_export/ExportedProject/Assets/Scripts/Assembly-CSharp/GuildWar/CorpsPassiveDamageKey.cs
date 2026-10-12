using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct CorpsPassiveDamageKey : IKeyTypeDeserializable<CorpsPassiveDamageKey, corps_passive_damage, key_corps_passive_damage>, IKeyType<CorpsPassiveDamageKey>, IKeyType, IEquatable<CorpsPassiveDamageKey>
	{
		private int m_corps_id;

		private int m_passive_index;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public int passive_index
		{
			get
			{
				return 0;
			}
		}

		public CorpsPassiveDamageKey(int corps_id, int passive_index)
		{
			m_corps_id = 0;
			m_passive_index = 0;
		}

		public void DeserializeKey(key_corps_passive_damage data)
		{
		}

		public void DeserializeKeyFromData(corps_passive_damage data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsPassiveDamageKey left, CorpsPassiveDamageKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsPassiveDamageKey left, CorpsPassiveDamageKey right)
		{
			return false;
		}

		public bool Equals(CorpsPassiveDamageKey other)
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
