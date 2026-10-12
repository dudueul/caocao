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

		public int CooldownDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public guild_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(guild_custom_function);
		}

		public static Offset<guild_custom_function> Createguild_custom_function(FlatBufferBuilder builder, ulong GuildId, short CustomGuildActionType, int CooldownDate)
		{
			return default(Offset<guild_custom_function>);
		}
	}
}
