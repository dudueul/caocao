using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild_function : IFlatbufferObject
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

		public int PGuildFunctionId
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public guild_function __assign(int _i, ByteBuffer _bb)
		{
			return default(guild_function);
		}

		public static Offset<guild_function> Createguild_function(FlatBufferBuilder builder, ulong GuildId, int PGuildFunctionId)
		{
			return default(Offset<guild_function>);
		}
	}
}
