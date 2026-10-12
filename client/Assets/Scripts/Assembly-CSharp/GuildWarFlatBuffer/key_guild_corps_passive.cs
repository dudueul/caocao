using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_corps_passive : IFlatbufferObject
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

		public int PassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_guild_corps_passive __assign(int _i, ByteBuffer _bb)
		{
			return default(key_guild_corps_passive);
		}

		public static Offset<key_guild_corps_passive> Createkey_guild_corps_passive(FlatBufferBuilder builder, ulong GuildId, int PassiveIndex)
		{
			return default(Offset<key_guild_corps_passive>);
		}
	}
}
