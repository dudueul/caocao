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

		public int PassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_building_passive __assign(int _i, ByteBuffer _bb)
		{
			return default(key_building_passive);
		}

		public static Offset<key_building_passive> Createkey_building_passive(FlatBufferBuilder builder, int PPlaceId, int PassiveIndex)
		{
			return default(Offset<key_building_passive>);
		}
	}
}
