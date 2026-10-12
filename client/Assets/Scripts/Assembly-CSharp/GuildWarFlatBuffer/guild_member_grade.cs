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
				return __p.bb;
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 0);
			}
		}

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public short Grade
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 16);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public guild_member_grade __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<guild_member_grade> Createguild_member_grade(FlatBufferBuilder builder, ulong GuildId, ulong UserId, short Grade)
		{
			builder.Prep(8, 24);
			builder.PutShort(Grade);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<guild_member_grade>(builder.Offset);
		}
	}
}
