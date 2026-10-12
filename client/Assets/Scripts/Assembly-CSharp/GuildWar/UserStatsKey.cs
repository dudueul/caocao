using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct UserStatsKey : IKeyTypeDeserializable<UserStatsKey, user_stats, key_user_stats>, IKeyType<UserStatsKey>, IKeyType, IEquatable<UserStatsKey>
	{
		private ulong m_guild_id;

		private ulong m_user_id;

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

		public UserStatsKey(ulong guild_id, ulong user_id)
		{
			m_guild_id = 0uL;
			m_user_id = 0uL;
		}

		public void DeserializeKey(key_user_stats data)
		{
		}

		public void DeserializeKeyFromData(user_stats data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(UserStatsKey left, UserStatsKey right)
		{
			return false;
		}

		public static bool operator !=(UserStatsKey left, UserStatsKey right)
		{
			return false;
		}

		public bool Equals(UserStatsKey other)
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
