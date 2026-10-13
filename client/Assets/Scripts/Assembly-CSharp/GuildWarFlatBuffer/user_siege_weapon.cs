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
				return __p.bb;
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 0);
			}
		}

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public int PSiegeWeapon
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int Amount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public user_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<user_siege_weapon> Createuser_siege_weapon(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PSiegeWeapon, int Amount)
		{
			builder.Prep(8, 24);
			builder.PutInt(Amount);
			builder.PutInt(PSiegeWeapon);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user_siege_weapon>(builder.Offset);
		}
	}
}
