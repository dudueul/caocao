using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_vision : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public ulong GuildId
		{
			get
			{
				return 0uL;
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

		public key_vision __assign(int _i, ByteBuffer _bb)
		{
			return default(key_vision);
		}

		public static Offset<key_vision> Createkey_vision(FlatBufferBuilder builder, ulong GuildId, short PosX, short PosY)
		{
			return default(Offset<key_vision>);
		}
	}
}
