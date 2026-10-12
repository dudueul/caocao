using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_building_custom_function : IFlatbufferObject
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

		public short CustomActionType
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 4);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_building_custom_function __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_building_custom_function> Createkey_building_custom_function(FlatBufferBuilder builder, int PPlaceId, short CustomActionType)
		{
			builder.Prep(4, 8);
			builder.PutShort(CustomActionType);
			builder.PutInt(PPlaceId);
			return new Offset<key_building_custom_function>(builder.Offset);
		}
	}
}
