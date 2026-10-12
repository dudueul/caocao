using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user_history : IFlatbufferObject
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

		public int HistoryValue
		{
			get
			{
				return 0;
			}
		}

		public int LatestDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public user_history __assign(int _i, ByteBuffer _bb)
		{
			return default(user_history);
		}

		public static Offset<user_history> Createuser_history(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int HistoryId, int HistoryKey, int HistoryValue, int LatestDate)
		{
			return default(Offset<user_history>);
		}
	}
}
