using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_history : IFlatbufferObject
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

		public int HistoryId
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_guild_history __assign(int _i, ByteBuffer _bb)
		{
			return default(key_guild_history);
		}

		public static Offset<key_guild_history> Createkey_guild_history(FlatBufferBuilder builder, ulong GuildId, int HistoryId)
		{
			return default(Offset<key_guild_history>);
		}
	}
}
