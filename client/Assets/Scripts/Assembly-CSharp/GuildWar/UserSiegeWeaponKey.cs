using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct UserSiegeWeaponKey : IKeyTypeDeserializable<UserSiegeWeaponKey, user_siege_weapon, key_user_siege_weapon>, IKeyType<UserSiegeWeaponKey>, IKeyType, IEquatable<UserSiegeWeaponKey>
	{
		private ulong m_guild_id;

		private ulong m_user_id;

		private int m_p_siege_weapon;

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

		public int p_siege_weapon
		{
			get
			{
				return 0;
			}
		}

		public UserSiegeWeaponKey(ulong guild_id, ulong user_id, int p_siege_weapon)
		{
			m_guild_id = 0uL;
			m_user_id = 0uL;
			m_p_siege_weapon = 0;
		}

		public void DeserializeKey(key_user_siege_weapon data)
		{
		}

		public void DeserializeKeyFromData(user_siege_weapon data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(UserSiegeWeaponKey left, UserSiegeWeaponKey right)
		{
			return false;
		}

		public static bool operator !=(UserSiegeWeaponKey left, UserSiegeWeaponKey right)
		{
			return false;
		}

		public bool Equals(UserSiegeWeaponKey other)
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
