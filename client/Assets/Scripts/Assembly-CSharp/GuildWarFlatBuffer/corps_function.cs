using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_function : IFlatbufferObject
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

		public int PCorpsFunctionId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int AvailableDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int AvailableDuration
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int CooldownDate
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

		public corps_function __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<corps_function> Createcorps_function(FlatBufferBuilder builder, int CorpsId, int PCorpsFunctionId, int AvailableDate, int AvailableDuration, int CooldownDate)
		{
			builder.Prep(4, 20);
			builder.PutInt(CooldownDate);
			builder.PutInt(AvailableDuration);
			builder.PutInt(AvailableDate);
			builder.PutInt(PCorpsFunctionId);
			builder.PutInt(CorpsId);
			return new Offset<corps_function>(builder.Offset);
		}
	}
}
