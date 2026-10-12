using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct data : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int ClanDbShard
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int WorldId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int Seq
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int StringsLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int BuildingLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int BuildingCustomFunctionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int BuildingHistoryLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int BuildingPassiveLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int BuildingPassiveDamageLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int CorpsLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int CorpsCustomFunctionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int CorpsFunctionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public int CorpsHistoryLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public int CorpsInformationLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 52);
			}
		}

		public int CorpsPassiveLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 56);
			}
		}

		public int CorpsPassiveDamageLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 60);
			}
		}

		public int CorpsSiegeWeaponLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 64);
			}
		}

		public int GuildLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 68);
			}
		}

		public int GuildCorpsPassiveLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 72);
			}
		}

		public int GuildCustomFunctionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 76);
			}
		}

		public int GuildFunctionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 80);
			}
		}

		public int GuildHistoryLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 84);
			}
		}

		public int GuildMemberGradeLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 88);
			}
		}

		public int TileEffectLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 92);
			}
		}

		public int UserLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 96);
			}
		}

		public int UserCorpsLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 100);
			}
		}

		public int UserHistoryLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 104);
			}
		}

		public int UserSiegeWeaponLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 108);
			}
		}

		public int UserStatsLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 112);
			}
		}

		public int UserTradingPostLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 116);
			}
		}

		public int VisionLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 120);
			}
		}

		public int VisionBuildingLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 124);
			}
		}

		public int VisionCorpsLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 128);
			}
		}

		public int BuildingDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 132);
			}
		}

		public int BuildingCustomFunctionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 136);
			}
		}

		public int BuildingHistoryDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 140);
			}
		}

		public int BuildingPassiveDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 144);
			}
		}

		public int BuildingPassiveDamageDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 148);
			}
		}

		public int CorpsDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 152);
			}
		}

		public int CorpsCustomFunctionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 156);
			}
		}

		public int CorpsFunctionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 160);
			}
		}

		public int CorpsHistoryDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 164);
			}
		}

		public int CorpsInformationDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 168);
			}
		}

		public int CorpsPassiveDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 172);
			}
		}

		public int CorpsPassiveDamageDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 176);
			}
		}

		public int CorpsSiegeWeaponDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 180);
			}
		}

		public int GuildDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 184);
			}
		}

		public int GuildCorpsPassiveDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 188);
			}
		}

		public int GuildCustomFunctionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 192);
			}
		}

		public int GuildFunctionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 196);
			}
		}

		public int GuildHistoryDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 200);
			}
		}

		public int GuildMemberGradeDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 204);
			}
		}

		public int TileEffectDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 208);
			}
		}

		public int UserDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 212);
			}
		}

		public int UserCorpsDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 216);
			}
		}

		public int UserHistoryDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 220);
			}
		}

		public int UserSiegeWeaponDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 224);
			}
		}

		public int UserStatsDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 228);
			}
		}

		public int UserTradingPostDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 232);
			}
		}

		public int VisionDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 236);
			}
		}

		public int VisionBuildingDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 240);
			}
		}

		public int VisionCorpsDeletedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 244);
			}
		}

		public int DirectionCombatSourceLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 248);
			}
		}

		public int DirectionCombatTargetLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 252);
			}
		}

		public int DirectionChangeHpLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 256);
			}
		}

		public int DirectionCorpsMovedLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 260);
			}
		}

		public int DirectionCombatMessageLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 264);
			}
		}

		public int DirectionPingLength
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 268);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public data __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<data> Createdata(FlatBufferBuilder builder, int ClanDbShard, int WorldId, int Seq, int StringsLength, int BuildingLength, int BuildingCustomFunctionLength, int BuildingHistoryLength, int BuildingPassiveLength, int BuildingPassiveDamageLength, int CorpsLength, int CorpsCustomFunctionLength, int CorpsFunctionLength, int CorpsHistoryLength, int CorpsInformationLength, int CorpsPassiveLength, int CorpsPassiveDamageLength, int CorpsSiegeWeaponLength, int GuildLength, int GuildCorpsPassiveLength, int GuildCustomFunctionLength, int GuildFunctionLength, int GuildHistoryLength, int GuildMemberGradeLength, int TileEffectLength, int UserLength, int UserCorpsLength, int UserHistoryLength, int UserSiegeWeaponLength, int UserStatsLength, int UserTradingPostLength, int VisionLength, int VisionBuildingLength, int VisionCorpsLength, int BuildingDeletedLength, int BuildingCustomFunctionDeletedLength, int BuildingHistoryDeletedLength, int BuildingPassiveDeletedLength, int BuildingPassiveDamageDeletedLength, int CorpsDeletedLength, int CorpsCustomFunctionDeletedLength, int CorpsFunctionDeletedLength, int CorpsHistoryDeletedLength, int CorpsInformationDeletedLength, int CorpsPassiveDeletedLength, int CorpsPassiveDamageDeletedLength, int CorpsSiegeWeaponDeletedLength, int GuildDeletedLength, int GuildCorpsPassiveDeletedLength, int GuildCustomFunctionDeletedLength, int GuildFunctionDeletedLength, int GuildHistoryDeletedLength, int GuildMemberGradeDeletedLength, int TileEffectDeletedLength, int UserDeletedLength, int UserCorpsDeletedLength, int UserHistoryDeletedLength, int UserSiegeWeaponDeletedLength, int UserStatsDeletedLength, int UserTradingPostDeletedLength, int VisionDeletedLength, int VisionBuildingDeletedLength, int VisionCorpsDeletedLength, int DirectionCombatSourceLength, int DirectionCombatTargetLength, int DirectionChangeHpLength, int DirectionCorpsMovedLength, int DirectionCombatMessageLength, int DirectionPingLength)
		{
			builder.Prep(4, 272);
			builder.PutInt(DirectionPingLength);
			builder.PutInt(DirectionCombatMessageLength);
			builder.PutInt(DirectionCorpsMovedLength);
			builder.PutInt(DirectionChangeHpLength);
			builder.PutInt(DirectionCombatTargetLength);
			builder.PutInt(DirectionCombatSourceLength);
			builder.PutInt(VisionCorpsDeletedLength);
			builder.PutInt(VisionBuildingDeletedLength);
			builder.PutInt(VisionDeletedLength);
			builder.PutInt(UserTradingPostDeletedLength);
			builder.PutInt(UserStatsDeletedLength);
			builder.PutInt(UserSiegeWeaponDeletedLength);
			builder.PutInt(UserHistoryDeletedLength);
			builder.PutInt(UserCorpsDeletedLength);
			builder.PutInt(UserDeletedLength);
			builder.PutInt(TileEffectDeletedLength);
			builder.PutInt(GuildMemberGradeDeletedLength);
			builder.PutInt(GuildHistoryDeletedLength);
			builder.PutInt(GuildFunctionDeletedLength);
			builder.PutInt(GuildCustomFunctionDeletedLength);
			builder.PutInt(GuildCorpsPassiveDeletedLength);
			builder.PutInt(GuildDeletedLength);
			builder.PutInt(CorpsSiegeWeaponDeletedLength);
			builder.PutInt(CorpsPassiveDamageDeletedLength);
			builder.PutInt(CorpsPassiveDeletedLength);
			builder.PutInt(CorpsInformationDeletedLength);
			builder.PutInt(CorpsHistoryDeletedLength);
			builder.PutInt(CorpsFunctionDeletedLength);
			builder.PutInt(CorpsCustomFunctionDeletedLength);
			builder.PutInt(CorpsDeletedLength);
			builder.PutInt(BuildingPassiveDamageDeletedLength);
			builder.PutInt(BuildingPassiveDeletedLength);
			builder.PutInt(BuildingHistoryDeletedLength);
			builder.PutInt(BuildingCustomFunctionDeletedLength);
			builder.PutInt(BuildingDeletedLength);
			builder.PutInt(VisionCorpsLength);
			builder.PutInt(VisionBuildingLength);
			builder.PutInt(VisionLength);
			builder.PutInt(UserTradingPostLength);
			builder.PutInt(UserStatsLength);
			builder.PutInt(UserSiegeWeaponLength);
			builder.PutInt(UserHistoryLength);
			builder.PutInt(UserCorpsLength);
			builder.PutInt(UserLength);
			builder.PutInt(TileEffectLength);
			builder.PutInt(GuildMemberGradeLength);
			builder.PutInt(GuildHistoryLength);
			builder.PutInt(GuildFunctionLength);
			builder.PutInt(GuildCustomFunctionLength);
			builder.PutInt(GuildCorpsPassiveLength);
			builder.PutInt(GuildLength);
			builder.PutInt(CorpsSiegeWeaponLength);
			builder.PutInt(CorpsPassiveDamageLength);
			builder.PutInt(CorpsPassiveLength);
			builder.PutInt(CorpsInformationLength);
			builder.PutInt(CorpsHistoryLength);
			builder.PutInt(CorpsFunctionLength);
			builder.PutInt(CorpsCustomFunctionLength);
			builder.PutInt(CorpsLength);
			builder.PutInt(BuildingPassiveDamageLength);
			builder.PutInt(BuildingPassiveLength);
			builder.PutInt(BuildingHistoryLength);
			builder.PutInt(BuildingCustomFunctionLength);
			builder.PutInt(BuildingLength);
			builder.PutInt(StringsLength);
			builder.PutInt(Seq);
			builder.PutInt(WorldId);
			builder.PutInt(ClanDbShard);
			return new Offset<data>(builder.Offset);
		}
	}
}
