using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_user : IFlatbufferObject
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

		public key_user __assign(int _i, ByteBuffer _bb)
		{
			return default(key_user);
		}

		public static Offset<key_user> Createkey_user(FlatBufferBuilder builder, ulong GuildId, ulong UserId)
		{
			return default(Offset<key_user>);
		}
	}
}
