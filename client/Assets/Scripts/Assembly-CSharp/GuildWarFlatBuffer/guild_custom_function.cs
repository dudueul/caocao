using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild_custom_function : IFlatbufferObject
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

		public short CustomGuildActionType
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 8);
			}
		}

		public int CooldownDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public guild_custom_function __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<guild_custom_function> Createguild_custom_function(FlatBufferBuilder builder, ulong GuildId, short CustomGuildActionType, int CooldownDate)
		{
			builder.Prep(8, 16);
			builder.PutInt(CooldownDate);
			builder.PutShort(CustomGuildActionType);
			builder.PutUlong(GuildId);
			return new Offset<guild_custom_function>(builder.Offset);
		}
	}
}
