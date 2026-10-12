using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user_trading_post : IFlatbufferObject
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

		public int UseCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int GetGold
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int UseGold
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public user_trading_post __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<user_trading_post> Createuser_trading_post(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int UseCount, int GetGold, int UseGold)
		{
			builder.Prep(8, 32);
			builder.PutInt(UseGold);
			builder.PutInt(GetGold);
			builder.PutInt(UseCount);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user_trading_post>(builder.Offset);
		}
	}
}
