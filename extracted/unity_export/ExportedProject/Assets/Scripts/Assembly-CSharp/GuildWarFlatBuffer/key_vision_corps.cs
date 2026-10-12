using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_vision_corps : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int CorpsId
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

		public key_vision_corps __assign(int _i, ByteBuffer _bb)
		{
			return default(key_vision_corps);
		}

		public static Offset<key_vision_corps> Createkey_vision_corps(FlatBufferBuilder builder, int CorpsId, short PosX, short PosY)
		{
			return default(Offset<key_vision_corps>);
		}
	}
}
