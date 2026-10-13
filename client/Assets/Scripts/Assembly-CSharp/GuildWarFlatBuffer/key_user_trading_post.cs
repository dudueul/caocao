using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_user_trading_post : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_user_trading_post __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_user_trading_post> Createkey_user_trading_post(FlatBufferBuilder builder, ulong GuildId, ulong UserId)
		{
			builder.Prep(8, 16);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<key_user_trading_post>(builder.Offset);
		}
	}
}
