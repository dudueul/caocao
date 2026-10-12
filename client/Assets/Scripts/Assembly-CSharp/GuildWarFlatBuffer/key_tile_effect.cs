using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_tile_effect : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
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

		public int PTileEffect
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_tile_effect __assign(int _i, ByteBuffer _bb)
		{
			return default(key_tile_effect);
		}

		public static Offset<key_tile_effect> Createkey_tile_effect(FlatBufferBuilder builder, short PosX, short PosY, int PTileEffect)
		{
			return default(Offset<key_tile_effect>);
		}
	}
}
