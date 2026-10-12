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
				return null;
			}
		}

		public int PPlaceId
		{
			get
			{
				return 0;
			}
		}

		public short CustomActionType
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_building_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(key_building_custom_function);
		}

		public static Offset<key_building_custom_function> Createkey_building_custom_function(FlatBufferBuilder builder, int PPlaceId, short CustomActionType)
		{
			return default(Offset<key_building_custom_function>);
		}
	}
}
