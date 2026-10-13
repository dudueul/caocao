using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_function : IFlatbufferObject
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

		public int PGuildFunctionId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_guild_function __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_guild_function> Createkey_guild_function(FlatBufferBuilder builder, ulong GuildId, int PGuildFunctionId)
		{
			builder.Prep(8, 16);
			builder.PutInt(PGuildFunctionId);
			builder.PutUlong(GuildId);
			return new Offset<key_guild_function>(builder.Offset);
		}
	}
}
