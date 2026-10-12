using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_guild __assign(int _i, ByteBuffer _bb)
		{
			return default(key_guild);
		}

		public static Offset<key_guild> Createkey_guild(FlatBufferBuilder builder, ulong GuildId)
		{
			return default(Offset<key_guild>);
		}
	}
}
