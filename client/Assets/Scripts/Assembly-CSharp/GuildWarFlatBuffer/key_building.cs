using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_building : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int PPlaceId
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

		public key_building __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_building> Createkey_building(FlatBufferBuilder builder, int PPlaceId)
		{
			builder.Prep(4, 4);
			builder.PutInt(PPlaceId);
			return new Offset<key_building>(builder.Offset);
		}
	}
}
