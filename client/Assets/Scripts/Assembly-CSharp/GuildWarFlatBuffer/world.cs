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
				return __p.bb;
			}
		}

		public short ClanDbShard
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 0);
			}
		}

		public int WorldId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int InstanceIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int LatestCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int Status
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int SeasonKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int SeasonDetailKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int NextSeasonDetailKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int PBattleWorldId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int PGuildwarTownId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int TownShardIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int TownGrade
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public int RewardGrade
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public int Map
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 52);
			}
		}

		public long PlayStart
		{
			get
			{
				return __p.bb.GetLong(__p.bb_pos + 56);
			}
		}

		public long PlayEnd
		{
			get
			{
				return __p.bb.GetLong(__p.bb_pos + 64);
			}
		}

		public long ExpireDate
		{
			get
			{
				return __p.bb.GetLong(__p.bb_pos + 72);
			}
		}

		public int PVictoryPlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 80);
			}
		}

		public ulong VictoryGuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 88);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public world __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<world> Createworld(FlatBufferBuilder builder, short ClanDbShard, int WorldId, int InstanceIndex, int LatestCorpsId, int Status, int SeasonKey, int SeasonDetailKey, int NextSeasonDetailKey, int PBattleWorldId, int PGuildwarTownId, int TownShardIndex, int TownGrade, int RewardGrade, int Map, long PlayStart, long PlayEnd, long ExpireDate, int PVictoryPlaceId, ulong VictoryGuildId)
		{
			builder.Prep(8, 96);
			builder.PutUlong(VictoryGuildId);
			builder.PutInt(PVictoryPlaceId);
			builder.PutLong(ExpireDate);
			builder.PutLong(PlayEnd);
			builder.PutLong(PlayStart);
			builder.PutInt(Map);
			builder.PutInt(RewardGrade);
			builder.PutInt(TownGrade);
			builder.PutInt(TownShardIndex);
			builder.PutInt(PGuildwarTownId);
			builder.PutInt(PBattleWorldId);
			builder.PutInt(NextSeasonDetailKey);
			builder.PutInt(SeasonDetailKey);
			builder.PutInt(SeasonKey);
			builder.PutInt(Status);
			builder.PutInt(LatestCorpsId);
			builder.PutInt(InstanceIndex);
			builder.PutInt(WorldId);
			builder.PutShort(ClanDbShard);
			return new Offset<world>(builder.Offset);
		}
	}
}
