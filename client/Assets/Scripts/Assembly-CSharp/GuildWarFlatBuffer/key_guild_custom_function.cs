using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_custom_function : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_guild_custom_function __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_guild_custom_function> Createkey_guild_custom_function(FlatBufferBuilder builder, ulong GuildId, short CustomGuildActionType)
		{
			builder.Prep(8, 16);
			builder.PutShort(CustomGuildActionType);
			builder.PutUlong(GuildId);
			return new Offset<key_guild_custom_function>(builder.Offset);
		}
	}
}
