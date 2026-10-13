using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_function : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_corps_function __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_corps_function> Createkey_corps_function(FlatBufferBuilder builder, int CorpsId, int PCorpsFunctionId)
		{
			builder.Prep(4, 8);
			builder.PutInt(PCorpsFunctionId);
			builder.PutInt(CorpsId);
			return new Offset<key_corps_function>(builder.Offset);
		}
	}
}
