using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct CorpsPassiveKey : IKeyTypeDeserializable<CorpsPassiveKey, corps_passive, key_corps_passive>, IKeyType<CorpsPassiveKey>, IKeyType, IEquatable<CorpsPassiveKey>
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

		public CorpsPassiveKey(int corps_id, int passive_index)
		{
			m_corps_id = 0;
			m_passive_index = 0;
		}

		public void DeserializeKey(key_corps_passive data)
		{
		}

		public void DeserializeKeyFromData(corps_passive data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsPassiveKey left, CorpsPassiveKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsPassiveKey left, CorpsPassiveKey right)
		{
			return false;
		}

		public bool Equals(CorpsPassiveKey other)
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
