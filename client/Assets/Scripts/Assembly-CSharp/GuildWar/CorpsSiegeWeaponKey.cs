using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct CorpsSiegeWeaponKey : IKeyTypeDeserializable<CorpsSiegeWeaponKey, corps_siege_weapon, key_corps_siege_weapon>, IKeyType<CorpsSiegeWeaponKey>, IKeyType, IEquatable<CorpsSiegeWeaponKey>
	{
		private int m_corps_id;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public CorpsSiegeWeaponKey(int corps_id)
		{
			m_corps_id = 0;
		}

		public void DeserializeKey(key_corps_siege_weapon data)
		{
		}

		public void DeserializeKeyFromData(corps_siege_weapon data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsSiegeWeaponKey left, CorpsSiegeWeaponKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsSiegeWeaponKey left, CorpsSiegeWeaponKey right)
		{
			return false;
		}

		public bool Equals(CorpsSiegeWeaponKey other)
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
