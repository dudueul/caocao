using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_history : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int CorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int HistoryId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int HistoryKey
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
			__p = new Struct(_i, _bb);
		}

		public corps_history __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<corps_history> Createcorps_history(FlatBufferBuilder builder, int CorpsId, int HistoryId, int HistoryKey, int HistoryValue, int LatestDate)
		{
			builder.Prep(4, 20);
			builder.PutInt(LatestDate);
			builder.PutInt(HistoryValue);
			builder.PutInt(HistoryKey);
			builder.PutInt(HistoryId);
			builder.PutInt(CorpsId);
			return new Offset<corps_history>(builder.Offset);
		}
	}
}
