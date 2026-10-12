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

		public int UseCount
		{
			get
			{
				return 0;
			}
		}

		public int GetGold
		{
			get
			{
				return 0;
			}
		}

		public int UseGold
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public user_trading_post __assign(int _i, ByteBuffer _bb)
		{
			return default(user_trading_post);
		}

		public static Offset<user_trading_post> Createuser_trading_post(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int UseCount, int GetGold, int UseGold)
		{
			return default(Offset<user_trading_post>);
		}
	}
}
