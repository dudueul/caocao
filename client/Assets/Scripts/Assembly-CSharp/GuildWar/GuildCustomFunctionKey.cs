using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GuildCustomFunctionKey : IKeyTypeDeserializable<GuildCustomFunctionKey, guild_custom_function, key_guild_custom_function>, IKeyType<GuildCustomFunctionKey>, IKeyType, IEquatable<GuildCustomFunctionKey>
	{
		private ulong m_guild_id;

		private short m_custom_guild_action_type;

		public ulong guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public short custom_guild_action_type
		{
			get
			{
				return 0;
			}
		}

		public GuildCustomFunctionKey(ulong guild_id, short custom_guild_action_type)
		{
			m_guild_id = 0uL;
			m_custom_guild_action_type = 0;
		}

		public void DeserializeKey(key_guild_custom_function data)
		{
		}

		public void DeserializeKeyFromData(guild_custom_function data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildCustomFunctionKey left, GuildCustomFunctionKey right)
		{
			return false;
		}

		public static bool operator !=(GuildCustomFunctionKey left, GuildCustomFunctionKey right)
		{
			return false;
		}

		public bool Equals(GuildCustomFunctionKey other)
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
