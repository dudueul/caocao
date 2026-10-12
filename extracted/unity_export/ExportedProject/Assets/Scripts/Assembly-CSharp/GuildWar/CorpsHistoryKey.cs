using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 12)]
	public struct CorpsHistoryKey : IKeyTypeDeserializable<CorpsHistoryKey, corps_history, key_corps_history>, IKeyType<CorpsHistoryKey>, IKeyType, IEquatable<CorpsHistoryKey>
	{
		private int m_corps_id;

		private int m_history_id;

		private int m_history_key;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public int history_id
		{
			get
			{
				return 0;
			}
		}

		public int history_key
		{
			get
			{
				return 0;
			}
		}

		public CorpsHistoryKey(int corps_id, int history_id, int history_key)
		{
			m_corps_id = 0;
			m_history_id = 0;
			m_history_key = 0;
		}

		public void DeserializeKey(key_corps_history data)
		{
		}

		public void DeserializeKeyFromData(corps_history data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsHistoryKey left, CorpsHistoryKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsHistoryKey left, CorpsHistoryKey right)
		{
			return false;
		}

		public bool Equals(CorpsHistoryKey other)
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
