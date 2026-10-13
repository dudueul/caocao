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

		public int BaseAtk
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int BaseDef
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int BaseInte
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int BaseAgi
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int BaseMor
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int TreasurePoint
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public user_stats __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<user_stats> Createuser_stats(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int BaseAtk, int BaseDef, int BaseInte, int BaseAgi, int BaseMor, int TreasurePoint)
		{
			builder.Prep(8, 40);
			builder.PutInt(TreasurePoint);
			builder.PutInt(BaseMor);
			builder.PutInt(BaseAgi);
			builder.PutInt(BaseInte);
			builder.PutInt(BaseDef);
			builder.PutInt(BaseAtk);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user_stats>(builder.Offset);
		}
	}
}
