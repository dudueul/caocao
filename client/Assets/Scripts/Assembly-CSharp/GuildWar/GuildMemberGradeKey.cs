using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GuildMemberGradeKey : IKeyTypeDeserializable<GuildMemberGradeKey, guild_member_grade, key_guild_member_grade>, IKeyType<GuildMemberGradeKey>, IKeyType, IEquatable<GuildMemberGradeKey>
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

		public GuildMemberGradeKey(ulong guild_id, ulong user_id)
		{
			m_guild_id = 0uL;
			m_user_id = 0uL;
		}

		public void DeserializeKey(key_guild_member_grade data)
		{
		}

		public void DeserializeKeyFromData(guild_member_grade data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(GuildMemberGradeKey left, GuildMemberGradeKey right)
		{
			return false;
		}

		public static bool operator !=(GuildMemberGradeKey left, GuildMemberGradeKey right)
		{
			return false;
		}

		public bool Equals(GuildMemberGradeKey other)
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
