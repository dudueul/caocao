using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_building_history : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_building_history __assign(int _i, ByteBuffer _bb)
		{
			return default(key_building_history);
		}

		public static Offset<key_building_history> Createkey_building_history(FlatBufferBuilder builder, int PPlaceId, int HistoryId, int HistoryKey)
		{
			return default(Offset<key_building_history>);
		}
	}
}
