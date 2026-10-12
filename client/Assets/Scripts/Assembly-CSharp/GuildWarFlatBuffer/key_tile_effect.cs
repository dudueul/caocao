using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_tile_effect : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 0);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 2);
			}
		}

		public int PTileEffect
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_tile_effect __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_tile_effect> Createkey_tile_effect(FlatBufferBuilder builder, short PosX, short PosY, int PTileEffect)
		{
			builder.Prep(4, 8);
			builder.PutInt(PTileEffect);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			return new Offset<key_tile_effect>(builder.Offset);
		}
	}
}
