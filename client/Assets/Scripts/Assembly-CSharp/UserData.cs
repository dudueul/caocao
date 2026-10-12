using System;
using System.Collections.Generic;
using GuildWar;
using LitJson;

public class UserData : ITransmittable, IUserDataBase, iTransmitContainer
{
	private UserItems m_items;

	private UserUnits m_units;

	private UserSoldiers m_soldiers;

	private UserSoldierDecks m_soldierDecks;

	private UserScenarios m_scenarios;

	private UserTowns m_towns;

	private UserSaveFlags m_saveflags;

	private UserCommonMissions m_commonMissions;

	private UserAchievements m_achievements;

	private UserTechs m_techs;

	private UserDefenseDecks m_defenseDecks;

	private UserAttends m_attends;

	private UserDailyCounts m_dailyCounts;

	private UserHistories m_histories;

	private UserUnitTreeNodes m_unitTreeNodes;

	private UserUnitTrees m_unitTrees;

	private UserStates m_states;

	private UserInstances m_instances;

	private UserStorybooks m_storybooks;

	private UserStorybookRewardHistories m_storybookRewardHistories;

	private UserTreasureBoxes m_treasureBoxes;

	private UserBattleFormations m_battleFormations;

	private UserUnitGroups m_unitGroups;

	private UserPvpDecks m_pvpDecks;

	private UserPvpStats m_pvpStats;

	private UserCompetitionStats m_competitionStats;

	private UserUnitPassiveInvens m_unitPassiveInvens;

	private UserTreasureQuickSlots m_treasureQuickSlots;

	private UserBanContents m_banContents;

	private UserSubScriptions m_subScriptions;

	private UserBattleFields m_battleFields;

	private UserBattleFieldAttackGroups m_battleFieldAttackGroups;

	private UserBattleFieldDefenseGroups m_battleFieldDefenseGroups;

	private UserRaidInfos m_raidInfos;

	private UserRaidAwakenInfos m_raidAwakenInfos;

	private UserRaidAwakenAttackInfos m_raidAwakenAttackInfos;

	private UserRaidAwakenReserves m_raidAwakenReserves;

	private UserRuneItems m_runeItems;

	private UserRuneSlots m_runeSlots;

	private UserRuneSetHistories m_runeSetHistories;

	private UserRetrieveDatas m_retrieveDatas;

	private UserBattleDeckFormations m_battleDeckFormations;

	private UserQuickBattleDecks m_quickBattleDecks;

	private UserTimeBoxes m_userTimeBoxes;

	private UserSelectBoxes m_userSelectBoxes;

	private UserRotationTimeBoxes m_userRotationTimeBoxes;

	private UserFollowUsers m_followUsers;

	private UserUnitPassiveInfo m_userUnitPassiveInfo;

	private UserCumulativePurchaseAmounts m_userCumulativePurchaseAmounts;

	private UserUnitRelations m_unitRelations;

	private UserSeasonPasses m_userSeasonPasses;

	private UserTreasureSockets m_userTreasureSockets;

	private UserInteractives m_userInteractives;

	private UserArenas m_userArenas;

	private UserArenaUnitGroups m_userArenaUnitGroups;

	private UserArenaLevels m_userArenaLevels;

	private UserArenaUnits m_userArenaUnits;

	private UserArenaTreasures m_userArenaTreasures;

	private UserArenaPassives m_userArenaPassives;

	private UserArenaRecords m_userArenaRecords;

	private UserArenaScenarios m_userArenaScenarios;

	private UserArenaShopItems m_userArenaShopItems;

	private UserInstanceExpedition m_userInstanceExpedition;

	private UserDailyGiftHistorys m_dailyGiftHistorys;

	private UserLinkedGoodsNodes m_userLinkedGoodsNodes;

	private UserLinkedGoodses m_userLinkedGoodses;

	private UserInfo m_info;

	private UserTags m_userTags;

	private int m_stealSilver;

	public Action<int> OnFoodsUpdated;

	public Action<int> OnSilverUpdated;

	public Action<int> OnGoldUpdated;

	public Action<int> OnStampUpdated;

	public Action<int> OnFameUpdated;

	public UserInfo info
	{
		get
		{
			return null;
		}
	}

	public UserTags userTags
	{
		get
		{
			return null;
		}
	}

	public UserItems items
	{
		get
		{
			if (m_items == null)
				m_items = new UserItems();
			return m_items;
		}
	}

	public UserUnits units
	{
		get
		{
			if (m_units == null)
				m_units = new UserUnits();
			return m_units;
		}
	}

	public UserSoldiers soldiers
	{
		get
		{
			return null;
		}
	}

	public UserSoldierDecks soldierDecks
	{
		get
		{
			return null;
		}
	}

	public UserScenarios scenarios
	{
		get
		{
			return null;
		}
	}

	public UserTowns towns
	{
		get
		{
			return null;
		}
	}

	public UserSaveFlags saveflags
	{
		get
		{
			return null;
		}
	}

	public UserCommonMissions commonMissions
	{
		get
		{
			return null;
		}
	}

	public UserAchievements achievements
	{
		get
		{
			return null;
		}
	}

	public UserTechs techs
	{
		get
		{
			return null;
		}
	}

	public UserDefenseDecks defenseDecks
	{
		get
		{
			return null;
		}
	}

	public UserDailyCounts dailyCounts
	{
		get
		{
			return null;
		}
	}

	public UserHistories histories
	{
		get
		{
			return null;
		}
	}

	public UserUnitTreeNodes unitTreeNodes
	{
		get
		{
			return null;
		}
	}

	public UserUnitTrees unitTrees
	{
		get
		{
			return null;
		}
	}

	public UserStates states
	{
		get
		{
			return null;
		}
	}

	public UserInstances instances
	{
		get
		{
			return null;
		}
	}

	public UserStorybooks storybooks
	{
		get
		{
			return null;
		}
	}

	public UserStorybookRewardHistories storybookRewardHistories
	{
		get
		{
			return null;
		}
	}

	public UserTreasureBoxes treasureBoxes
	{
		get
		{
			return null;
		}
	}

	public UserBattleFormations battleFormations
	{
		get
		{
			return null;
		}
	}

	public UserUnitGroups unitGroups
	{
		get
		{
			return null;
		}
	}

	public UserPvpDecks pvpDecks
	{
		get
		{
			return null;
		}
	}

	public UserPvpStats pvpStats
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionStats competitionStats
	{
		get
		{
			return null;
		}
	}

	public UserUnitPassiveInvens unitPassiveInvens
	{
		get
		{
			return null;
		}
	}

	public UserTreasureQuickSlots treasureQuickSlots
	{
		get
		{
			return null;
		}
	}

	public UserBanContents banContents
	{
		get
		{
			return null;
		}
	}

	public UserSubScriptions subScriptions
	{
		get
		{
			return null;
		}
	}

	public UserBattleFields battleFields
	{
		get
		{
			return null;
		}
	}

	public UserBattleFieldAttackGroups battleFieldAttackGroups
	{
		get
		{
			return null;
		}
	}

	public UserBattleFieldDefenseGroups battleFieldDefenseGroups
	{
		get
		{
			return null;
		}
	}

	public UserRaidInfos raidInfos
	{
		get
		{
			return null;
		}
	}

	public UserRaidAwakenInfos raidAwakenInfos
	{
		get
		{
			return null;
		}
	}

	public UserRaidAwakenAttackInfos raidAwakenAttackInfos
	{
		get
		{
			return null;
		}
	}

	public UserRaidAwakenReserves raidAwakenReserves
	{
		get
		{
			return null;
		}
	}

	public UserRuneItems userRuneItems
	{
		get
		{
			return null;
		}
	}

	public UserRuneSlots userRuneSlots
	{
		get
		{
			return null;
		}
	}

	public UserRuneSetHistories userRuneSetHistories
	{
		get
		{
			return null;
		}
	}

	public UserRetrieveDatas userRetrieveDatas
	{
		get
		{
			return null;
		}
	}

	public UserBattleDeckFormations userBattleDeckFormations
	{
		get
		{
			return null;
		}
	}

	public UserQuickBattleDecks userQuickBattleDecks
	{
		get
		{
			return null;
		}
	}

	public UserPvpDeck currentUserPvpDeck
	{
		get
		{
			return null;
		}
	}

	public UserUnitGroup currentUserUnitGroup
	{
		get
		{
			return null;
		}
	}

	public UserBattleFormation currentUserBattleFormation
	{
		get
		{
			return null;
		}
	}

	public UserTimeBoxes userTimeBoxes
	{
		get
		{
			return null;
		}
	}

	public UserSelectBoxes userSelectBoxes
	{
		get
		{
			return null;
		}
	}

	public UserRotationTimeBoxes userRotationTimeBoxes
	{
		get
		{
			return null;
		}
	}

	public UserFollowUsers followUsers
	{
		get
		{
			return null;
		}
	}

	public UserUnitPassiveInfo userUnitPassiveInfo
	{
		get
		{
			return null;
		}
	}

	public UserCumulativePurchaseAmounts userCumulativePurchaseAmounts
	{
		get
		{
			return null;
		}
	}

	public UserUnitRelations unitRelations
	{
		get
		{
			return null;
		}
	}

	public UserSeasonPasses userSeasonPasses
	{
		get
		{
			return null;
		}
	}

	public UserTreasureSockets treasureSockets
	{
		get
		{
			return null;
		}
	}

	public UserInteractives interactives
	{
		get
		{
			return null;
		}
	}

	public UserArenas userArenas
	{
		get
		{
			return null;
		}
	}

	public UserArenaUnitGroups userArenaUnitGroups
	{
		get
		{
			return null;
		}
	}

	public UserArenaLevels userArenaLevels
	{
		get
		{
			return null;
		}
	}

	public UserArenaUnits userArenaUnits
	{
		get
		{
			return null;
		}
	}

	public UserArenaTreasures userArenaTreasures
	{
		get
		{
			return null;
		}
	}

	public UserArenaPassives userArenaPassives
	{
		get
		{
			return null;
		}
	}

	public UserArenaRecords userArenaRecords
	{
		get
		{
			return null;
		}
	}

	public UserArenaScenarios userArenaScenarios
	{
		get
		{
			return null;
		}
	}

	public UserArenaShopItems userArenaShopItems
	{
		get
		{
			return null;
		}
	}

	public UserInstanceExpedition instanceExpedition
	{
		get
		{
			return null;
		}
	}

	public UserDailyGiftHistorys dailyGiftHistorys
	{
		get
		{
			return null;
		}
	}

	public UserLinkedGoodses userLinkedGoodses
	{
		get
		{
			return null;
		}
	}

	public UserLinkedGoodsNodes userLinkedGoodsNodes
	{
		get
		{
			return null;
		}
	}

	public UserPvpDeck currentUserCompetitionDeck
	{
		get
		{
			return null;
		}
	}

	public UserUnitGroup currentUserCompetitionUnitGroup
	{
		get
		{
			return null;
		}
	}

	public UserBattleFormation currentUserCompetitionBattleFormation
	{
		get
		{
			return null;
		}
	}

	public virtual bool willRegister
	{
		get
		{
			return false;
		}
	}

	public int maxUnitCost
	{
		get
		{
			return 0;
		}
	}

	[DoNotJsonfy]
	public UserUnit kingUnit
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public int MaxBattleModeUnitCost(int battleMode)
	{
		return 0;
	}

	public UserUnit GetMayorUnit(int townId)
	{
		return null;
	}

	public int GetFoodHarvestBonus(int townId)
	{
		return 0;
	}

	public int GetSilverHarvestBonus(int townId)
	{
		return 0;
	}

	public int GetFoodStorageBonus(int townId)
	{
		return 0;
	}

	public int GetSilverStorageBonus(int townId)
	{
		return 0;
	}

	public int GetSafeBonus(int townId)
	{
		return 0;
	}

	public int GetFoodPlunderBonus(int townId)
	{
		return 0;
	}

	public int GetSilverPlunderBonus(int townId)
	{
		return 0;
	}

	public int GetRefreshTimeBonus(params int[] effectRanges)
	{
		return 0;
	}

	public int GetOccupationPctBonus(params int[] effectRanges)
	{
		return 0;
	}

	public int GetGoldCostBonus(params int[] effectRanges)
	{
		return 0;
	}

	public int GetSilverCostBonus(params int[] effectRanges)
	{
		return 0;
	}

	public int GetFoodCostBonus(params int[] effectRanges)
	{
		return 0;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeUserInfo(JsonData jsonData)
	{
	}

	public UserItem GetEquippedItem(ulong unitSrl, EnumData.ItemSlotType itemSlotType)
	{
		return null;
	}

	public bool DidEqiupAll(ulong unitSrl)
	{
		return false;
	}

	public Item GetItemInfo(ulong itemSrl)
	{
		return null;
	}

	public ulong AddUnit(int unitID, string nickName, int unitJobLevel, int unitLevel)
	{
		return 0uL;
	}

	public void ReduceItem(ulong itemSrl, int amount)
	{
	}

	public void AddTown(UserTown userTown)
	{
	}

	public void AddSoldier(int soldierId, int level)
	{
	}

	public void UpdateRecentMap(int scenarioID)
	{
	}

	public UserUnit GetUnitDataUnitID(int unitId)
	{
		return null;
	}

	public UserUnit GetUnitEquipped(ulong itemSrl)
	{
		return null;
	}

	public ulong GetUnitEquippedSrl(ulong itemSrl)
	{
		return 0uL;
	}

	public void GetUnitData(ulong unitSerial, out UserUnit userUnit)
	{
		userUnit = null;
	}

	public void StealSilver(int delta)
	{
	}

	public int GetUserMaxSilver()
	{
		return 0;
	}

	public int GetUserMaxFood()
	{
		return 0;
	}

	public void UpgradeGoodsMax(EnumData.TownBuildingType buildingType)
	{
	}

	public bool IsKing(int unitID)
	{
		return false;
	}

	public List<ulong> GetInventoryItems(EnumData.ItemSlotType slotType, UserUnit userUnit, bool exceptEquipped = true)
	{
		return null;
	}

	public List<ulong> GetInventoryTrasureItems(GuildWarMilitaryStrategyType guildWarMilitaryStrategyType)
	{
		return null;
	}

	public List<ulong> GetInventoryTrasureItems(EnumData.ItemType itemType, bool exceptEquipped = true)
	{
		return null;
	}

	public List<UserItem> GetGuildwarTreasureItems()
	{
		return null;
	}

	public int GetGuildwarTreasurePoint()
	{
		return 0;
	}

	public bool CanCraft(int itemId)
	{
		return false;
	}

	public bool CanCraftRecursively(int itemId, int amount, Dictionary<int, int> usage = null)
	{
		return false;
	}

	private bool CheckMaterialRecursively(int itemId, int needCount, Dictionary<int, int> usage)
	{
		return false;
	}

	public int GetCraftCostRecursively(int itemId, int amount, ref Dictionary<int, int> usage, ref Dictionary<int, int> craft, ref int craftCount)
	{
		return 0;
	}

	private int _GetCraftCostRecursively(int itemId, int needCount, ref Dictionary<int, int> usage, ref Dictionary<int, int> craft, ref int craftCount)
	{
		return 0;
	}

	public bool CanCraftTreasure(int treasureMixId, int count)
	{
		return false;
	}

	public List<int> GetMissionIds()
	{
		return null;
	}

	public int GetMissionGrade()
	{
		return 0;
	}

	public int GetSoldierDeckLevelAvg(int townId)
	{
		return 0;
	}

	public int GetDefenseDeckLevelAvg(int townId)
	{
		return 0;
	}

	public void TurnOffUpsertedMark()
	{
	}

	public bool CheckMultiBattleCount()
	{
		return false;
	}

	public List<int> GetCustomEventDefaultUserData()
	{
		return null;
	}
}
