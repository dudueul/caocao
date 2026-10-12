using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_siege_weapon : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_corps_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_corps_siege_weapon> Createkey_corps_siege_weapon(FlatBufferBuilder builder, int CorpsId)
		{
			builder.Prep(4, 4);
			builder.PutInt(CorpsId);
			return new Offset<key_corps_siege_weapon>(builder.Offset);
		}
	}
}
