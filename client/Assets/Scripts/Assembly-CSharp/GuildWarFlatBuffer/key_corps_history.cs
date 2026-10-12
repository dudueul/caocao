using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_history : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_corps_history __assign(int _i, ByteBuffer _bb)
		{
			return default(key_corps_history);
		}

		public static Offset<key_corps_history> Createkey_corps_history(FlatBufferBuilder builder, int CorpsId, int HistoryId, int HistoryKey)
		{
			return default(Offset<key_corps_history>);
		}
	}
}
