using System.Collections.Generic;

public interface IUserDataBase
{
	UserItems items { get; }

	UserUnits units { get; }

	UserSoldiers soldiers { get; }

	UserSoldierDecks soldierDecks { get; }

	UserScenarios scenarios { get; }

	UserTowns towns { get; }

	UserSaveFlags saveflags { get; }

	UserCommonMissions commonMissions { get; }

	UserAchievements achievements { get; }

	UserTechs techs { get; }

	UserInfo info { get; }

	UserDefenseDecks defenseDecks { get; }

	UserHistories histories { get; }

	UserUnitTreeNodes unitTreeNodes { get; }

	UserUnitTrees unitTrees { get; }

	UserStates states { get; }

	UserInstances instances { get; }

	UserStorybooks storybooks { get; }

	UserStorybookRewardHistories storybookRewardHistories { get; }

	UserTreasureBoxes treasureBoxes { get; }

	UserBattleFormations battleFormations { get; }

	UserUnitGroups unitGroups { get; }

	UserPvpDecks pvpDecks { get; }

	UserPvpStats pvpStats { get; }

	UserCompetitionStats competitionStats { get; }

	UserUnitPassiveInvens unitPassiveInvens { get; }

	UserTreasureQuickSlots treasureQuickSlots { get; }

	UserBanContents banContents { get; }

	UserSubScriptions subScriptions { get; }

	UserBattleFields battleFields { get; }

	UserBattleFieldAttackGroups battleFieldAttackGroups { get; }

	UserBattleFieldDefenseGroups battleFieldDefenseGroups { get; }

	UserRaidInfos raidInfos { get; }

	UserRaidAwakenInfos raidAwakenInfos { get; }

	UserRaidAwakenAttackInfos raidAwakenAttackInfos { get; }

	UserRuneItems userRuneItems { get; }

	UserRuneSlots userRuneSlots { get; }

	UserRuneSetHistories userRuneSetHistories { get; }

	UserUnitRelations unitRelations { get; }

	UserSeasonPasses userSeasonPasses { get; }

	UserTreasureSockets treasureSockets { get; }

	UserArenas userArenas { get; }

	UserArenaUnitGroups userArenaUnitGroups { get; }

	UserArenaLevels userArenaLevels { get; }

	UserArenaUnits userArenaUnits { get; }

	UserArenaTreasures userArenaTreasures { get; }

	UserArenaPassives userArenaPassives { get; }

	UserArenaRecords userArenaRecords { get; }

	UserArenaScenarios userArenaScenarios { get; }

	UserArenaShopItems userArenaShopItems { get; }

	UserInstanceExpedition instanceExpedition { get; }

	UserDailyGiftHistorys dailyGiftHistorys { get; }

	UserLinkedGoodsNodes userLinkedGoodsNodes { get; }

	UserLinkedGoodses userLinkedGoodses { get; }

	UserUnit kingUnit { get; }

	bool willRegister { get; }

	UserPvpDeck currentUserPvpDeck { get; }

	UserUnitGroup currentUserUnitGroup { get; }

	UserBattleFormation currentUserBattleFormation { get; }

	UserPvpDeck currentUserCompetitionDeck { get; }

	UserUnitGroup currentUserCompetitionUnitGroup { get; }

	UserBattleFormation currentUserCompetitionBattleFormation { get; }

	UserItem GetEquippedItem(ulong unitSrl, EnumData.ItemSlotType itemSlotType);

	Item GetItemInfo(ulong itemSrl);

	ulong AddUnit(int unitID, string nickName, int unitJobLevel, int unitLevel);

	UserUnit GetMayorUnit(int townId);

	UserUnit GetUnitDataUnitID(int unitId);

	UserUnit GetUnitEquipped(ulong itemSrl);

	ulong GetUnitEquippedSrl(ulong itemSrl);

	List<ulong> GetInventoryItems(EnumData.ItemSlotType slotType, UserUnit userUnit, bool exceptEquipped = true);

	int GetFoodHarvestBonus(int townId);

	int GetSilverHarvestBonus(int townId);

	int GetFoodStorageBonus(int townId);

	int GetSilverStorageBonus(int townId);

	int GetSafeBonus(int townId);

	int GetFoodPlunderBonus(int townId);

	int GetSilverPlunderBonus(int townId);

	int GetRefreshTimeBonus(params int[] effectRanges);

	int GetOccupationPctBonus(params int[] effectRanges);

	int GetGoldCostBonus(params int[] effectRanges);

	int GetSilverCostBonus(params int[] effectRanges);

	int GetFoodCostBonus(params int[] effectRanges);

	int GetSoldierDeckLevelAvg(int townId);

	int GetDefenseDeckLevelAvg(int townId);
}
