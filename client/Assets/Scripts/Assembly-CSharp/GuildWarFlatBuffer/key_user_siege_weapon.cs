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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_user_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_user_siege_weapon> Createkey_user_siege_weapon(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PSiegeWeapon)
		{
			builder.Prep(8, 24);
			builder.PutInt(PSiegeWeapon);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<key_user_siege_weapon>(builder.Offset);
		}
	}
}
