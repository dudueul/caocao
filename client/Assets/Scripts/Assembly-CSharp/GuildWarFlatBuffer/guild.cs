using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild : IFlatbufferObject
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

		public int GuildName
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int EmblemSpriteIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int GuildwarRank
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public short PositionType
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 20);
			}
		}

		public int MaxUserJoinCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int ColorIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int LatestCorpsPassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int RankingPoint
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public guild __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<guild> Createguild(FlatBufferBuilder builder, ulong GuildId, int GuildName, int EmblemSpriteIndex, int GuildwarRank, short PositionType, int MaxUserJoinCount, int ColorIndex, int LatestCorpsPassiveIndex, int RankingPoint)
		{
			builder.Prep(8, 40);
			builder.PutInt(RankingPoint);
			builder.PutInt(LatestCorpsPassiveIndex);
			builder.PutInt(ColorIndex);
			builder.PutInt(MaxUserJoinCount);
			builder.PutShort(PositionType);
			builder.PutInt(GuildwarRank);
			builder.PutInt(EmblemSpriteIndex);
			builder.PutInt(GuildName);
			builder.PutUlong(GuildId);
			return new Offset<guild>(builder.Offset);
		}
	}
}
