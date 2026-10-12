using System;

namespace GuildWar
{
	public abstract class DataTablesOrigin
	{
		public abstract DataTable<BuildingKey, BuildingData> buildingDataTables { get; }

		public abstract DataTable<BuildingCustomFunctionKey, BuildingCustomFunctionData> buildingCustomFunctionDataTables { get; }

		public abstract DataTable<BuildingHistoryKey, BuildingHistoryData> buildingHistoryDataTables { get; }

		public abstract DataTable<BuildingPassiveKey, BuildingPassiveData> buildingPassiveDataTables { get; }

		public abstract DataTable<BuildingPassiveDamageKey, BuildingPassiveDamageData> buildingPassiveDamageDataTables { get; }

		public abstract DataTable<CorpsKey, CorpsData> corpsDataTables { get; }

		public abstract DataTable<CorpsCustomFunctionKey, CorpsCustomFunctionData> corpsCustomFunctionDataTables { get; }

		public abstract DataTable<CorpsFunctionKey, CorpsFunctionData> corpsFunctionDataTables { get; }

		public abstract DataTable<CorpsHistoryKey, CorpsHistoryData> corpsHistoryDataTables { get; }

		public abstract DataTable<CorpsInformationKey, CorpsInformationData> corpsInformationDataTables { get; }

		public abstract DataTable<CorpsPassiveKey, CorpsPassiveData> corpsPassiveDataTables { get; }

		public abstract DataTable<CorpsPassiveDamageKey, CorpsPassiveDamageData> corpsPassiveDamageDataTables { get; }

		public abstract DataTable<CorpsSiegeWeaponKey, CorpsSiegeWeaponData> corpsSiegeWeaponDataTables { get; }

		public abstract DataTable<GuildKey, GuildData> guildDataTables { get; }

		public abstract DataTable<GuildCorpsPassiveKey, GuildCorpsPassiveData> guildCorpsPassiveDataTables { get; }

		public abstract DataTable<GuildCustomFunctionKey, GuildCustomFunctionData> guildCustomFunctionDataTables { get; }

		public abstract DataTable<GuildFunctionKey, GuildFunctionData> guildFunctionDataTables { get; }

		public abstract DataTable<GuildHistoryKey, GuildHistoryData> guildHistoryDataTables { get; }

		public abstract DataTable<GuildMemberGradeKey, GuildMemberGradeData> guildMemberGradeDataTables { get; }

		public abstract DataTable<TileEffectKey, TileEffectData> tileEffectDataTables { get; }

		public abstract DataTable<UserKey, UserData> userDataTables { get; }

		public abstract DataTable<UserCorpsKey, UserCorpsData> userCorpsDataTables { get; }

		public abstract DataTable<UserHistoryKey, UserHistoryData> userHistoryDataTables { get; }

		public abstract DataTable<UserSiegeWeaponKey, UserSiegeWeaponData> userSiegeWeaponDataTables { get; }

		public abstract DataTable<UserStatsKey, UserStatsData> userStatsDataTables { get; }

		public abstract DataTable<UserTradingPostKey, UserTradingPostData> userTradingPostDataTables { get; }

		public abstract DataTable<VisionKey, VisionData> visionDataTables { get; }

		public abstract DataTable<VisionBuildingKey, VisionBuildingData> visionBuildingDataTables { get; }

		public abstract DataTable<VisionCorpsKey, VisionCorpsData> visionCorpsDataTables { get; }

		public abstract Action<DirectionCombatSourceData> onDirectionCombatSource { get; set; }

		public abstract Action<DirectionCombatTargetData> onDirectionCombatTarget { get; set; }

		public abstract Action<DirectionChangeHpData> onDirectionChangeHp { get; set; }

		public abstract Action<DirectionCorpsMovedData> onDirectionCorpsMoved { get; set; }

		public abstract Action<DirectionCombatMessageData> onDirectionCombatMessage { get; set; }

		public abstract Action<DirectionPingData> onDirectionPing { get; set; }
	}
}
