using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild_history : IFlatbufferObject
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

		public guild_history __assign(int _i, ByteBuffer _bb)
		{
			return default(guild_history);
		}

		public static Offset<guild_history> Createguild_history(FlatBufferBuilder builder, ulong GuildId, int HistoryId, int HistoryValue, int LatestDate)
		{
			return default(Offset<guild_history>);
		}
	}
}
