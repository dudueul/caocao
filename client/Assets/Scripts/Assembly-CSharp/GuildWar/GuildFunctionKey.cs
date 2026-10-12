using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GuildFunctionKey : IKeyTypeDeserializable<GuildFunctionKey, guild_function, key_guild_function>, IKeyType<GuildFunctionKey>, IKeyType, IEquatable<GuildFunctionKey>
	{
		private ulong m_guild_id;

		private int m_p_guild_function_id;

		public ulong guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public int p_guild_function_id
		{
			get
			{
				return 0;
			}
		}

		public GuildFunctionKey(ulong guild_id, int p_guild_function_id)
		{
			m_guild_id = 0uL;
			m_p_guild_function_id = 0;
		}

		public void DeserializeKey(key_guild_function data)
		{
		}

		public void DeserializeKeyFromData(guild_function data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildFunctionKey left, GuildFunctionKey right)
		{
			return false;
		}

		public static bool operator !=(GuildFunctionKey left, GuildFunctionKey right)
		{
			return false;
		}

		public bool Equals(GuildFunctionKey other)
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
