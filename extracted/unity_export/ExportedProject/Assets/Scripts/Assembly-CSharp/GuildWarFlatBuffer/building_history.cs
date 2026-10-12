using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct building_history : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int PPlaceId
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

		public building_history __assign(int _i, ByteBuffer _bb)
		{
			return default(building_history);
		}

		public static Offset<building_history> Createbuilding_history(FlatBufferBuilder builder, int PPlaceId, int HistoryId, int HistoryKey, int HistoryValue, int LatestDate)
		{
			return default(Offset<building_history>);
		}
	}
}
