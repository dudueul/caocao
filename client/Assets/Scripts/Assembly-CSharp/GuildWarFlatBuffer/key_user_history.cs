using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_user_history : IFlatbufferObject
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

		public ulong UserId
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

		public int HistoryKey
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_user_history __assign(int _i, ByteBuffer _bb)
		{
			return default(key_user_history);
		}

		public static Offset<key_user_history> Createkey_user_history(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int HistoryId, int HistoryKey)
		{
			return default(Offset<key_user_history>);
		}
	}
}
