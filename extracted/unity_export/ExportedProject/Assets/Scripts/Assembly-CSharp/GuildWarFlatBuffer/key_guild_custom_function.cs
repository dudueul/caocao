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

		public short CustomGuildActionType
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_guild_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(key_guild_custom_function);
		}

		public static Offset<key_guild_custom_function> Createkey_guild_custom_function(FlatBufferBuilder builder, ulong GuildId, short CustomGuildActionType)
		{
			return default(Offset<key_guild_custom_function>);
		}
	}
}
