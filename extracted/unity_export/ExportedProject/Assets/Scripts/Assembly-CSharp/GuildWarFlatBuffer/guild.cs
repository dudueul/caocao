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

		public int GuildName
		{
			get
			{
				return 0;
			}
		}

		public int EmblemSpriteIndex
		{
			get
			{
				return 0;
			}
		}

		public int GuildwarRank
		{
			get
			{
				return 0;
			}
		}

		public short PositionType
		{
			get
			{
				return 0;
			}
		}

		public int MaxUserJoinCount
		{
			get
			{
				return 0;
			}
		}

		public int ColorIndex
		{
			get
			{
				return 0;
			}
		}

		public int LatestCorpsPassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int RankingPoint
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public guild __assign(int _i, ByteBuffer _bb)
		{
			return default(guild);
		}

		public static Offset<guild> Createguild(FlatBufferBuilder builder, ulong GuildId, int GuildName, int EmblemSpriteIndex, int GuildwarRank, short PositionType, int MaxUserJoinCount, int ColorIndex, int LatestCorpsPassiveIndex, int RankingPoint)
		{
			return default(Offset<guild>);
		}
	}
}
