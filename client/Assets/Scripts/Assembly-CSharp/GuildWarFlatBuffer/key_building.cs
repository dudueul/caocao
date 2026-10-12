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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_building __assign(int _i, ByteBuffer _bb)
		{
			return default(key_building);
		}

		public static Offset<key_building> Createkey_building(FlatBufferBuilder builder, int PPlaceId)
		{
			return default(Offset<key_building>);
		}
	}
}
