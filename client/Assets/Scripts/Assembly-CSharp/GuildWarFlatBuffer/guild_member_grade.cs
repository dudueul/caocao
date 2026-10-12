using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild_member_grade : IFlatbufferObject
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

		public short Grade
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public guild_member_grade __assign(int _i, ByteBuffer _bb)
		{
			return default(guild_member_grade);
		}

		public static Offset<guild_member_grade> Createguild_member_grade(FlatBufferBuilder builder, ulong GuildId, ulong UserId, short Grade)
		{
			return default(Offset<guild_member_grade>);
		}
	}
}
