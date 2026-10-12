using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct UserHistoryKey : IKeyTypeDeserializable<UserHistoryKey, user_history, key_user_history>, IKeyType<UserHistoryKey>, IKeyType, IEquatable<UserHistoryKey>
	{
		private ulong m_guild_id;

		private ulong m_user_id;

		private int m_history_id;

		private int m_history_key;

		public ulong guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public ulong user_id
		{
			get
			{
				return 0uL;
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

		public UserHistoryKey(ulong guild_id, ulong user_id, int history_id, int history_key)
		{
			m_guild_id = 0uL;
			m_user_id = 0uL;
			m_history_id = 0;
			m_history_key = 0;
		}

		public void DeserializeKey(key_user_history data)
		{
		}

		public void DeserializeKeyFromData(user_history data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(UserHistoryKey left, UserHistoryKey right)
		{
			return false;
		}

		public static bool operator !=(UserHistoryKey left, UserHistoryKey right)
		{
			return false;
		}

		public bool Equals(UserHistoryKey other)
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
