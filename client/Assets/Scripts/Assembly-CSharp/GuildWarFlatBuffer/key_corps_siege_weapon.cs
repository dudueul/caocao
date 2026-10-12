using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_siege_weapon : IFlatbufferObject
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

		public key_corps_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			return default(key_corps_siege_weapon);
		}

		public static Offset<key_corps_siege_weapon> Createkey_corps_siege_weapon(FlatBufferBuilder builder, int CorpsId)
		{
			return default(Offset<key_corps_siege_weapon>);
		}
	}
}
