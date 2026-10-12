using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_building_passive : IFlatbufferObject
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

		public int PassiveIndex
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

		public key_building_passive __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_building_passive> Createkey_building_passive(FlatBufferBuilder builder, int PPlaceId, int PassiveIndex)
		{
			builder.Prep(4, 8);
			builder.PutInt(PassiveIndex);
			builder.PutInt(PPlaceId);
			return new Offset<key_building_passive>(builder.Offset);
		}
	}
}
