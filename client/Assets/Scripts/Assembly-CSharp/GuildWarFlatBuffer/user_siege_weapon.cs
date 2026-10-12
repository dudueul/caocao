using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user_siege_weapon : IFlatbufferObject
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

		public int Amount
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public user_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			return default(user_siege_weapon);
		}

		public static Offset<user_siege_weapon> Createuser_siege_weapon(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PSiegeWeapon, int Amount)
		{
			return default(Offset<user_siege_weapon>);
		}
	}
}
