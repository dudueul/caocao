using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct world : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public short ClanDbShard
		{
			get
			{
				return 0;
			}
		}

		public int WorldId
		{
			get
			{
				return 0;
			}
		}

		public int InstanceIndex
		{
			get
			{
				return 0;
			}
		}

		public int LatestCorpsId
		{
			get
			{
				return 0;
			}
		}

		public int Status
		{
			get
			{
				return 0;
			}
		}

		public int SeasonKey
		{
			get
			{
				return 0;
			}
		}

		public int SeasonDetailKey
		{
			get
			{
				return 0;
			}
		}

		public int NextSeasonDetailKey
		{
			get
			{
				return 0;
			}
		}

		public int PBattleWorldId
		{
			get
			{
				return 0;
			}
		}

		public int PGuildwarTownId
		{
			get
			{
				return 0;
			}
		}

		public int TownShardIndex
		{
			get
			{
				return 0;
			}
		}

		public int TownGrade
		{
			get
			{
				return 0;
			}
		}

		public int RewardGrade
		{
			get
			{
				return 0;
			}
		}

		public int Map
		{
			get
			{
				return 0;
			}
		}

		public long PlayStart
		{
			get
			{
				return 0L;
			}
		}

		public long PlayEnd
		{
			get
			{
				return 0L;
			}
		}

		public long ExpireDate
		{
			get
			{
				return 0L;
			}
		}

		public int PVictoryPlaceId
		{
			get
			{
				return 0;
			}
		}

		public ulong VictoryGuildId
		{
			get
			{
				return 0uL;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public world __assign(int _i, ByteBuffer _bb)
		{
			return default(world);
		}

		public static Offset<world> Createworld(FlatBufferBuilder builder, short ClanDbShard, int WorldId, int InstanceIndex, int LatestCorpsId, int Status, int SeasonKey, int SeasonDetailKey, int NextSeasonDetailKey, int PBattleWorldId, int PGuildwarTownId, int TownShardIndex, int TownGrade, int RewardGrade, int Map, long PlayStart, long PlayEnd, long ExpireDate, int PVictoryPlaceId, ulong VictoryGuildId)
		{
			return default(Offset<world>);
		}
	}
}
