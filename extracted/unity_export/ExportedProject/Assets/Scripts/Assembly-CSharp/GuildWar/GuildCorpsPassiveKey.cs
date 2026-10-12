using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GuildCorpsPassiveKey : IKeyTypeDeserializable<GuildCorpsPassiveKey, guild_corps_passive, key_guild_corps_passive>, IKeyType<GuildCorpsPassiveKey>, IKeyType, IEquatable<GuildCorpsPassiveKey>
	{
		private ulong m_guild_id;

		private int m_passive_index;

		public ulong guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public int passive_index
		{
			get
			{
				return 0;
			}
		}

		public GuildCorpsPassiveKey(ulong guild_id, int passive_index)
		{
			m_guild_id = 0uL;
			m_passive_index = 0;
		}

		public void DeserializeKey(key_guild_corps_passive data)
		{
		}

		public void DeserializeKeyFromData(guild_corps_passive data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildCorpsPassiveKey left, GuildCorpsPassiveKey right)
		{
			return false;
		}

		public static bool operator !=(GuildCorpsPassiveKey left, GuildCorpsPassiveKey right)
		{
			return false;
		}

		public bool Equals(GuildCorpsPassiveKey other)
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
