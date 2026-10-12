using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using GuildWar;
using LitJson;
using UnityEngine;

public class SceneDataPipe : Singleton<SceneDataPipe>
{
	private EnumData.BattleMode m_battleMode;

	public Action OnBattleModeChanged;

	public EnumData.BattleMode lastBattleMode;

	private Dictionary<EnumData.BattleMode, EnumData.BattleOption> battleOptions;

	private readonly EnumData.BattleMode[] m_competitionModes;

	private readonly EnumData.BattleMode[] m_multiplayModes;

	private int m_defaultUnitCustomLevel;

	public HashSet<EnumData.TreasureType> treasureFilterTypeRestriction;

	public HashSet<UnitSide> treasureFilterUnitSideRestriction;

	public HashSet<UnitSide> runeFilterUnitSideRestriction;

	public HashSet<int> techIDsToRestrict;

	public EnumData.ScenarioModeLevel scenarioModeLevel;

	public EnumData.ScenarioModeLevel lastScenarioModeLevel;

	public int storybookId;

	public int storybookFirstEndingId;

	public EnumData.BattleResult lastBattleResult;

	public DateTime battleStartDateTime;

	public List<int> lockScenarioIds;

	public bool isRestartBattle;

	public bool isLoadedBattle;

	public bool isCmdEditor;

	public int townPrefabResourceTownId;

	public int eventMapID;

	public int scenarioId;

	public int battlemapID;

	public List<bool> scenarioBlock;

	public int autoPickedUnitCount;

	public int tutorialId;

	public int relationId;

	public int arenaStageId;

	public ObscuredString savedAuthId;

	public ulong battleSrl;

	public List<BattleReward> battleRewardItems;

	public int battleDefeatSeq;

	public bool firstBattleFieldDefenseDeck;

	private Dictionary<int, UnitPickInfo> m_pickedUnitSrls;

	private SortedDictionary<int, SlotInfo> m_spawnInfos;

	private List<int> m_pickedByScenarioEventUnits;

	private List<int> m_bannedByScenarioEventUnits;

	private List<int> deadUnits;

	public int deckLevelAvg;

	public int nextScenarioId;

	private LastInstanceInfo m_lastInstanceInfo;

	public EnumData.AutoInstanceType autoInstanceProcessType;

	public bool willRecoverEnergy;

	public bool willUseFastForward;

	public bool willExpBoost;

	public bool isFastForwardSubScriptions;

	public bool isEnergySubScriptions;

	private Coroutine m_autoAction;

	private bool m_isAutoInstanceProcessing;

	public List<ulong> pickedUnitSrlsForAutoInstance;

	public List<ulong> maxLevelSrlsForAutoInstance;

	private bool isAutoProcessBackup;

	private bool isFastForwardProcessBackup;

	public bool isWaitingForRecoveringEnergy;

	public int autoInstancePlayCount;

	public RaidSelectInfo RaidSelectInfo;

	public WorldBossInfo WorldBossInfo;

	private StringBuilder sb;

	public Exception exceptionWhileLoading;

	public JsonData myData;

	public JsonData opponentData;

	public UserTown opponentTown;

	public bool GuildWar_IsCommander;

	public int GuildSelectedRequestGoods;

	public GuildWarBarracksType GuildWarBarracksType;

	public Dictionary<GuildWarBarracksType, List<UserQuickBattleDeckUnit>> GuildWarPickedUnits;

	public List<UserQuickBattleDeckUnit> SelectedBarrackUnits;

	public int SelectedBarrackArea;

	public EnumData.BattleMode GuildWar_prevBattleMode;

	public int GuildWar_prevSortType;

	private int m_highestDamage;

	public EnumData.BattleMode battleMode
	{
		get
		{
			return EnumData.BattleMode.Scenario;
		}
		set
		{
		}
	}

	public bool isScenarioMode
	{
		get
		{
			return false;
		}
	}

	public bool isSiegeMode
	{
		get
		{
			return false;
		}
	}

	public bool isInstanceMode
	{
		get
		{
			return false;
		}
	}

	public bool isInstanceExpeditionMode
	{
		get
		{
			return false;
		}
	}

	public bool isShamMode
	{
		get
		{
			return false;
		}
	}

	public bool isDefenseDeckMode
	{
		get
		{
			return false;
		}
	}

	public bool isTutorialMode
	{
		get
		{
			return false;
		}
	}

	public bool isAnnihilationWarMode
	{
		get
		{
			return false;
		}
	}

	public bool isThousandBattleMode
	{
		get
		{
			return false;
		}
	}

	public bool isBattleFieldMode
	{
		get
		{
			return false;
		}
	}

	public bool isBattleFieldDefenseMode
	{
		get
		{
			return false;
		}
	}

	public bool isRaidMode
	{
		get
		{
			return false;
		}
	}

	public bool isGuildSiegeWarMode
	{
		get
		{
			return false;
		}
	}

	public bool isGuildSiegeWarDefenceMode
	{
		get
		{
			return false;
		}
	}

	public bool isGuildSiegeWarShamMode
	{
		get
		{
			return false;
		}
	}

	public bool isCompetitionAnnihilationMode
	{
		get
		{
			return false;
		}
	}

	public bool isCompetitionBrawlMode
	{
		get
		{
			return false;
		}
	}

	public bool isWorldBossMode
	{
		get
		{
			return false;
		}
	}

	public bool isRelationMode
	{
		get
		{
			return false;
		}
	}

	public bool isGuildWarMode
	{
		get
		{
			return false;
		}
	}

	public bool isArenaMode
	{
		get
		{
			return false;
		}
	}

	public bool isCompetitionMode
	{
		get
		{
			return false;
		}
	}

	public bool isMultiplayMode
	{
		get
		{
			return false;
		}
	}

	public bool isUsingUnitCustomLevel
	{
		get
		{
			return false;
		}
	}

	public int defaultUnitCustomLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isUsingTreasureFilter
	{
		get
		{
			return false;
		}
	}

	public bool isUsingRuneFilter
	{
		get
		{
			return false;
		}
	}

	public bool isUsingTechFilter
	{
		get
		{
			return false;
		}
	}

	public int oldInstanceStamp { get; set; }

	public bool isScenarioNormalMode
	{
		get
		{
			return false;
		}
	}

	public bool isScenarioHardMode
	{
		get
		{
			return false;
		}
	}

	public bool isScenarioClassicMode
	{
		get
		{
			return false;
		}
	}

	public bool isWin
	{
		get
		{
			return false;
		}
	}

	public bool isBattleFieldDefenseModeFirst
	{
		get
		{
			return false;
		}
	}

	public bool isBattleSave
	{
		get
		{
			return false;
		}
	}

	public Dictionary<int, UnitPickInfo> pickedUnitSrls
	{
		get
		{
			return null;
		}
	}

	public SortedDictionary<int, SlotInfo> spawnInfos
	{
		get
		{
			return null;
		}
	}

	public List<int> pickedByScenarioEventUnits
	{
		get
		{
			return null;
		}
	}

	public LastInstanceInfo lastInstanceInfo
	{
		get
		{
			return null;
		}
	}

	public bool isPackManShortCutClicked { get; set; }

	public bool isSequentialInstanceMode
	{
		get
		{
			return false;
		}
	}

	public bool isConsecutiveInstanceMode
	{
		get
		{
			return false;
		}
	}

	public bool isAutoInstanceProcessing
	{
		get
		{
			return false;
		}
	}

	public int paidNeedAttackFood { get; private set; }

	public int highestDamage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.BattleOption GetBattleOption(EnumData.BattleMode mode)
	{
		return EnumData.BattleOption.None;
	}

	public bool IsOnBattleOption(EnumData.BattleOption option)
	{
		return false;
	}

	public void SetBattleOption(EnumData.BattleMode mode, EnumData.BattleOption option)
	{
	}

	public bool IsCompetitionMode(EnumData.BattleMode mode)
	{
		return false;
	}

	public void SetScenarioClassicMode()
	{
	}

	public void ResetScenarioClassicMode(bool resetUnits = true)
	{
	}

	private bool CanUseTreasure(EnumData.TreasureType treasureType, UnitSide unitSide)
	{
		return false;
	}

	public bool CanUseTreasure(UserItem userItem, UserUnit userUnit)
	{
		return false;
	}

	private bool CanUseRune(UnitSide unitSide)
	{
		return false;
	}

	public bool CanUseRune(RuneItemSlot runeItemSlot, UserUnit userUnit)
	{
		return false;
	}

	public bool CanUseRune(UserRuneSlot userRuneSlot)
	{
		return false;
	}

	public bool CanUseTech(int techID)
	{
		return false;
	}

	public bool ReadBattleSrl(BinaryReader r)
	{
		return false;
	}

	public bool ReadInvariants(BinaryReader r)
	{
		return false;
	}

	private bool ReadInvariantsCustomMode(BinaryReader r)
	{
		return false;
	}

	public void WriteInvariants(BinaryWriter w)
	{
	}

	private void WriteInvariantsCustomMode(BinaryWriter w)
	{
	}

	public void ReadVariants(BinaryReader r)
	{
	}

	public void WriteVariants(BinaryWriter w)
	{
	}

	public void DeserializeBattleRewards(JsonData rewards)
	{
	}

	public void InitBattleInfo()
	{
	}

	private void InitStoryBookFirstEnding()
	{
	}

	public int? GetSquadIndex(int bfIndex)
	{
		return null;
	}

	public int? GetEmptySlot()
	{
		return null;
	}

	public int? GetSquadOfUnit(ulong srl)
	{
		return null;
	}

	public int GetNumSquads()
	{
		return 0;
	}

	public int GetNumSlotInSquad(int group)
	{
		return 0;
	}

	public int GetMaxUnit()
	{
		return 0;
	}

	public int GetRemainedSlotCount()
	{
		return 0;
	}

	public int GetBattleUnitCount()
	{
		return 0;
	}

	public void AddPickUnitByScenarioEvent(int unitId)
	{
	}

	public void AddDeadUnitList(int unitId)
	{
	}

	public bool CheckDeadUnit(int unitid)
	{
		return false;
	}

	public bool CheckAbsenceOfForcePickUnit()
	{
		return false;
	}

	public int CheckUnitEnergyCost()
	{
		return 0;
	}

	public void GetNeedEnergyUnitSrlsForCostOnPickedUnits(out List<ulong> unists, out int cost)
	{
		unists = null;
		cost = default(int);
	}

	public string GetAbsentUnitNames()
	{
		return null;
	}

	public bool IsForcePickByScenario(int unitId)
	{
		return false;
	}

	public bool IsDefenseTownMayor(int unitId)
	{
		return false;
	}

	public void AddBanUnitByScenarioEvent(int unitId)
	{
	}

	public bool IsBannedByScenario(int unitId)
	{
		return false;
	}

	public bool IsBannedByMayor(UserUnit userUnit)
	{
		return false;
	}

	public bool IsDefenseUnit(UserUnit userUnit)
	{
		return false;
	}

	public void PickInitialUnits(bool includeDefendingUnits, bool includeForcePickUnits)
	{
	}

	public bool IsPicked(ulong srl)
	{
		return false;
	}

	public void PickUnit(ulong srl, int unitID, bool forceOrManual, int bfIndex = -1, int order = -1)
	{
	}

	public void UnpickUnit(ulong srl)
	{
	}

	public int? GetPickedUnitID(int bfIndex)
	{
		return null;
	}

	public ulong? GetPickedUnitSrl(int bfIndex)
	{
		return null;
	}

	public ulong? GetUnitSrlInBfIndex(int OrderId)
	{
		return null;
	}

	public int? GetBFIndex(ulong srl)
	{
		return null;
	}

	public ulong? GetSurvivalUnitSrl(int bfIndex)
	{
		return null;
	}

	private UserUnit GetUserUnit(ulong srl)
	{
		return null;
	}

	public int GetPickedUnitCosts()
	{
		return 0;
	}

	public int GetAlivePickedUnitCount()
	{
		return 0;
	}

	public void GetScriptData(ref int battleMapId, ref string eventScript, ref string placeName)
	{
	}

	public int GetPickedUnitAverageLvel()
	{
		return 0;
	}

	public string GetPickedUnitCombatPowerData(bool includeStatisticsData)
	{
		return null;
	}

	public int GetPickedUnitCombatPower()
	{
		return 0;
	}

	public bool CheckAllPickedUnitEquipmentAvailable()
	{
		return false;
	}

	private void SetForcePickUnitFromBattleScript()
	{
	}

	private void SetForcePickUnitOnEntryDeck()
	{
	}

	public void ClearInfo(bool restart)
	{
	}

	public List<UserUnit> GetUnits()
	{
		return null;
	}

	public bool IsThousandBattleDeckAutoAssignCandidate(UserUnit userUnit)
	{
		return false;
	}

	public List<UserUnit> GetAutoAssignCandidates()
	{
		return null;
	}

	public List<UserUnit> GetAutoAssignCandidatesForArena()
	{
		return null;
	}

	public List<UserUnit> GetShowedUnitsOnDeck()
	{
		return null;
	}

	public void AutoAssignDeckForAnnihilationWar()
	{
	}

	public void AutoAssignDeckForGuildSiegeWar()
	{
	}

	public void AutoAssignDeckForGuildwar(EnumData.SortType sortType)
	{
	}

	public void AutoAssignDeckForBrawl()
	{
	}

	public void AutoAssignDeckForArena()
	{
	}

	public void AutoAssignDeck(bool checkCost = true)
	{
	}

	private bool IsDefenseDeckAutoAssignCandidate(UserUnit userUnit)
	{
		return false;
	}

	private bool IsBattleFieldDeckAutoAssignCandidate(UserUnit userUnit)
	{
		return false;
	}

	private bool IsRaidAutoAssignCandidate(UserUnit userUnit)
	{
		return false;
	}

	public void SetOpponentTown()
	{
	}

	public int GetNeedAttackFood(bool restart = false)
	{
		return 0;
	}

	private int GetNeedAttackCostAsUnits()
	{
		return 0;
	}

	private int GetNeedInstanceExpeditionCostAsUnits()
	{
		return 0;
	}

	public string GetPickedUnitSrlsString()
	{
		return null;
	}

	public string GetPickedUnitIdsString()
	{
		return null;
	}

	public List<int> GetPickedUnitIds()
	{
		return null;
	}

	public RewardInfo GetBattleItem(int rewardId)
	{
		return null;
	}

	public void GiveBackBattleItem(int rewardId, RewardInfo rewardInfo)
	{
	}

	public int GetBattleLimitTurn()
	{
		return 0;
	}

	public int GetStoryBookIdOnScenarioMode()
	{
		return 0;
	}

	public void StartAutoInstanceProcessing()
	{
	}

	public bool CompareSubScriptions()
	{
		return false;
	}

	public bool NeedToWaitForRecoveringEnergy()
	{
		return false;
	}

	public void CancelAutoInstanceMode(EnumData.AutoInstanceCancelReason reason, Action callback = null, params object[] param)
	{
	}

	public void SetAutoAction(Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator AutoAction(Action callback)
	{
		return null;
	}

	public void PayFastForward()
	{
	}

	public TimeSpan GetRemainedRecoveringTime()
	{
		return default(TimeSpan);
	}

	public void ApplyMultiplayUnitsToPickedUnit()
	{
	}

	private int ApplyMultiplayHoldUnitsToPickedUnit()
	{
		return 0;
	}

	public void ApplyCompetitionFriendlyUnitsPicked()
	{
	}

	public bool CheckGuildWarUnitUsage(int unitId)
	{
		return false;
	}

	public bool CheckGuildWarUnitUsage(int unitId, out string UsageCondition)
	{
		UsageCondition = null;
		return false;
	}

	public string GetNxLogDataForMatch()
	{
		return null;
	}
}
