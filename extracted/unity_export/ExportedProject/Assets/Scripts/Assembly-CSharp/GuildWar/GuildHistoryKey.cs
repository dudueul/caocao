using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GuildHistoryKey : IKeyTypeDeserializable<GuildHistoryKey, guild_history, key_guild_history>, IKeyType<GuildHistoryKey>, IKeyType, IEquatable<GuildHistoryKey>
	{
		private ulong m_guild_id;

		private int m_history_id;

		public ulong guild_id
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

		public GuildHistoryKey(ulong guild_id, int history_id)
		{
			m_guild_id = 0uL;
			m_history_id = 0;
		}

		public void DeserializeKey(key_guild_history data)
		{
		}

		public void DeserializeKeyFromData(guild_history data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildHistoryKey left, GuildHistoryKey right)
		{
			return false;
		}

		public static bool operator !=(GuildHistoryKey left, GuildHistoryKey right)
		{
			return false;
		}

		public bool Equals(GuildHistoryKey other)
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
