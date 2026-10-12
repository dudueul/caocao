using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_user_siege_weapon : IFlatbufferObject
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

		public int PSiegeWeapon
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_user_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			return default(key_user_siege_weapon);
		}

		public static Offset<key_user_siege_weapon> Createkey_user_siege_weapon(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PSiegeWeapon)
		{
			return default(Offset<key_user_siege_weapon>);
		}
	}
}
