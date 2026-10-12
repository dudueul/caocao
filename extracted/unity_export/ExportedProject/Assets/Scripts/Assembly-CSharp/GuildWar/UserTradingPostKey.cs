using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct UserTradingPostKey : IKeyTypeDeserializable<UserTradingPostKey, user_trading_post, key_user_trading_post>, IKeyType<UserTradingPostKey>, IKeyType, IEquatable<UserTradingPostKey>
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

		public UserTradingPostKey(ulong guild_id, ulong user_id)
		{
			m_guild_id = 0uL;
			m_user_id = 0uL;
		}

		public void DeserializeKey(key_user_trading_post data)
		{
		}

		public void DeserializeKeyFromData(user_trading_post data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(UserTradingPostKey left, UserTradingPostKey right)
		{
			return false;
		}

		public static bool operator !=(UserTradingPostKey left, UserTradingPostKey right)
		{
			return false;
		}

		public bool Equals(UserTradingPostKey other)
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
