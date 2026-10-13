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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_corps_history __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_corps_history> Createkey_corps_history(FlatBufferBuilder builder, int CorpsId, int HistoryId, int HistoryKey)
		{
			builder.Prep(4, 12);
			builder.PutInt(HistoryKey);
			builder.PutInt(HistoryId);
			builder.PutInt(CorpsId);
			return new Offset<key_corps_history>(builder.Offset);
		}
	}
}
