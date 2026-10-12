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
				return null;
			}
		}

		public int CorpsId
		{
			get
			{
				return 0;
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

		public corps_history __assign(int _i, ByteBuffer _bb)
		{
			return default(corps_history);
		}

		public static Offset<corps_history> Createcorps_history(FlatBufferBuilder builder, int CorpsId, int HistoryId, int HistoryKey, int HistoryValue, int LatestDate)
		{
			return default(Offset<corps_history>);
		}
	}
}
