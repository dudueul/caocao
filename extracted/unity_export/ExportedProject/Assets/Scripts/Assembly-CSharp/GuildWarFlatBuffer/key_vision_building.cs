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

		public short PosX
		{
			get
			{
				return 0;
			}
		}

		public short PosY
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_vision_building __assign(int _i, ByteBuffer _bb)
		{
			return default(key_vision_building);
		}

		public static Offset<key_vision_building> Createkey_vision_building(FlatBufferBuilder builder, int PPlaceId, short PosX, short PosY)
		{
			return default(Offset<key_vision_building>);
		}
	}
}
