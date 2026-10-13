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

		public int HistoryId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int HistoryValue
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int LatestDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public guild_history __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<guild_history> Createguild_history(FlatBufferBuilder builder, ulong GuildId, int HistoryId, int HistoryValue, int LatestDate)
		{
			builder.Prep(8, 24);
			builder.PutInt(LatestDate);
			builder.PutInt(HistoryValue);
			builder.PutInt(HistoryId);
			builder.PutUlong(GuildId);
			return new Offset<guild_history>(builder.Offset);
		}
	}
}
