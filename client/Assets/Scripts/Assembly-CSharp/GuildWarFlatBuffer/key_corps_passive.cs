using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_passive : IFlatbufferObject
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

		public int PassiveIndex
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

		public key_corps_passive __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_corps_passive> Createkey_corps_passive(FlatBufferBuilder builder, int CorpsId, int PassiveIndex)
		{
			builder.Prep(4, 8);
			builder.PutInt(PassiveIndex);
			builder.PutInt(CorpsId);
			return new Offset<key_corps_passive>(builder.Offset);
		}
	}
}
