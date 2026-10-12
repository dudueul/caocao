using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_member_grade : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_guild_member_grade __assign(int _i, ByteBuffer _bb)
		{
			return default(key_guild_member_grade);
		}

		public static Offset<key_guild_member_grade> Createkey_guild_member_grade(FlatBufferBuilder builder, ulong GuildId, ulong UserId)
		{
			return default(Offset<key_guild_member_grade>);
		}
	}
}
