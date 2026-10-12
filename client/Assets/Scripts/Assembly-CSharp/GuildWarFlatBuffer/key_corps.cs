using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_corps __assign(int _i, ByteBuffer _bb)
		{
			return default(key_corps);
		}

		public static Offset<key_corps> Createkey_corps(FlatBufferBuilder builder, int CorpsId)
		{
			return default(Offset<key_corps>);
		}
	}
}
