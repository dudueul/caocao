using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user_stats : IFlatbufferObject
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

		public int BaseAtk
		{
			get
			{
				return 0;
			}
		}

		public int BaseDef
		{
			get
			{
				return 0;
			}
		}

		public int BaseInte
		{
			get
			{
				return 0;
			}
		}

		public int BaseAgi
		{
			get
			{
				return 0;
			}
		}

		public int BaseMor
		{
			get
			{
				return 0;
			}
		}

		public int TreasurePoint
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public user_stats __assign(int _i, ByteBuffer _bb)
		{
			return default(user_stats);
		}

		public static Offset<user_stats> Createuser_stats(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int BaseAtk, int BaseDef, int BaseInte, int BaseAgi, int BaseMor, int TreasurePoint)
		{
			return default(Offset<user_stats>);
		}
	}
}
