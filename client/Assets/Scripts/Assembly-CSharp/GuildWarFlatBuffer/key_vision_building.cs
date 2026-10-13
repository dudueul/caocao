using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_vision_building : IFlatbufferObject
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

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 4);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 6);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_vision_building __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_vision_building> Createkey_vision_building(FlatBufferBuilder builder, int PPlaceId, short PosX, short PosY)
		{
			builder.Prep(4, 8);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutInt(PPlaceId);
			return new Offset<key_vision_building>(builder.Offset);
		}
	}
}
