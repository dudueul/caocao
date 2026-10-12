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

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public int HistoryId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int HistoryKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int HistoryValue
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int LatestDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public user_history __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<user_history> Createuser_history(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int HistoryId, int HistoryKey, int HistoryValue, int LatestDate)
		{
			builder.Prep(8, 32);
			builder.PutInt(LatestDate);
			builder.PutInt(HistoryValue);
			builder.PutInt(HistoryKey);
			builder.PutInt(HistoryId);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user_history>(builder.Offset);
		}
	}
}
