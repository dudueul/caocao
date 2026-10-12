using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_user_corps : IFlatbufferObject
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

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_user_corps __assign(int _i, ByteBuffer _bb)
		{
			return default(key_user_corps);
		}

		public static Offset<key_user_corps> Createkey_user_corps(FlatBufferBuilder builder, ulong GuildId, ulong UserId)
		{
			return default(Offset<key_user_corps>);
		}
	}
}
