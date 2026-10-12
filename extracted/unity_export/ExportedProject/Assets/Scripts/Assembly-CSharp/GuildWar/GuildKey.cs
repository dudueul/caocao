using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct GuildKey : IKeyTypeDeserializable<GuildKey, guild, key_guild>, IKeyType<GuildKey>, IKeyType, IEquatable<GuildKey>
	{
		private ulong m_guild_id;

		public ulong guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public GuildKey(ulong guild_id)
		{
			m_guild_id = 0uL;
		}

		public void DeserializeKey(key_guild data)
		{
		}

		public void DeserializeKeyFromData(guild data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildKey left, GuildKey right)
		{
			return false;
		}

		public static bool operator !=(GuildKey left, GuildKey right)
		{
			return false;
		}

		public bool Equals(GuildKey other)
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
