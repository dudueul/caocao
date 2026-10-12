using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattlePlay;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class CompetitionManager
{
	[Serializable]
	private class ReplayDataWrapper
	{
		public byte[] data;
	}

	private int m_competitionId;

	private int m_competitionModeId;

	private DateTime m_seasonEndTime;

	private BrawlRotation m_brawlRotation;

	private bool m_pvpUnitStatisticsRequestIng;

	private Dictionary<int, PvpUnitStatistics> m_pvpUnitStatisticses;

	private List<int> m_pvpUnitStatiWarIds;

	public string continuableBattleKey;

	private ulong[] m_prevOpponents;

	public EnumData.MultiplayBattleResult continuableBattleResult;

	public int earlyRetreatPenalty;

	public int earlyRetreatPenaltyTurn;

	public int earlyRetreatPenaltyDeadUnitCount;

	public Action OnFinishQueueRest;

	public UnitData taggingUnitData;

	private PopupBrawlTagDirection popupTagDirection;

	private Queue<TagDirectionInfo> tagDirectionQueue;

	private AchievementValueStorage achievementValueStorage;

	public UserUnitGroup friendlyMatchUnitGroup;

	public FriendlyMatchingRoom friendlyMatchingRoom;

	public bool shamMode;

	public static CompetitionManager instance
	{
		get
		{
			return null;
		}
	}

	public ObscuredInt competitionId
	{
		get
		{
			return default(ObscuredInt);
		}
		set
		{
		}
	}

	public int competitionModeId
	{
		get
		{
			return 0;
		}
	}

	public bool isSeasonOff
	{
		get
		{
			return false;
		}
	}

	public int pickCount
	{
		get
		{
			return 0;
		}
	}

	public int banCount
	{
		get
		{
			return 0;
		}
	}

	public int holdCount
	{
		get
		{
			return 0;
		}
	}

	public int queueCount
	{
		get
		{
			return 0;
		}
	}

	public int battleUnitCount
	{
		get
		{
			return 0;
		}
	}

	public int ranking
	{
		get
		{
			return 0;
		}
	}

	public DateTime seasonEndTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public bool weekFirst { get; set; }

	public int brawlRotationIdx { get; set; }

	public BrawlRotation brawlRotation
	{
		get
		{
			return null;
		}
	}

	public string lastLimitDay { get; set; }

	public string limitDay { get; set; }

	public int mmr { get; set; }

	public int point { get; set; }

	public int streak { get; set; }

	public int tierGrade { get; set; }

	public bool applyFairRule { get; set; }

	public Season currentSeason { get; private set; }

	public Dictionary<int, PvpUnitStatistics> pvpUnitStatisticses
	{
		get
		{
			return null;
		}
	}

	public List<int> pvpUnitStatiWarIds
	{
		get
		{
			return null;
		}
	}

	public bool canContinueBattle
	{
		get
		{
			return false;
		}
	}

	public ulong[] prevOpponents
	{
		get
		{
			return null;
		}
	}

	public UserUnitGroup currentUnitGroup
	{
		get
		{
			return null;
		}
	}

	public bool doingChargeEffect { get; private set; }

	public string friendlyMatchingRoomId
	{
		get
		{
			return null;
		}
	}

	public string friendlyMatchingRoomVoiceChatRoomId
	{
		get
		{
			return null;
		}
	}

	public EnumData.BattleMode friendlyBattleMode
	{
		get
		{
			return EnumData.BattleMode.Scenario;
		}
	}

	public int friendlyBattleMapId
	{
		get
		{
			return 0;
		}
	}

	public int friendlyMatchModeId
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public int friendlyMatchId
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public void MakeSpawnInfos()
	{
	}

	public void SetDeck(ulong uid)
	{
	}

	public void SetTechs(ulong uid)
	{
	}

	public void SetRelations(ulong uid)
	{
	}

	public int GetUserUnitId(ulong uid, int formationIndex)
	{
		return 0;
	}

	public bool CanMatchBattle()
	{
		return false;
	}

	public bool CanEnterBattle()
	{
		return false;
	}

	public TimeSpan SeasonRemainingTime()
	{
		return default(TimeSpan);
	}

	private int GetUserUnitIndex(UserData userData, int unitId)
	{
		return 0;
	}

	public bool NeedToSetCompetitionDeck()
	{
		return false;
	}

	public EnumData.BattleMode GetCompetitionBattleMode()
	{
		return EnumData.BattleMode.Scenario;
	}

	public EnumData.BattleMode GetCompetitionBattleMode(int id)
	{
		return EnumData.BattleMode.Scenario;
	}

	public bool ChangeCompetitionBattleMode()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator GetMatchInfo(bool registerNewSeason, bool willEndPreviousBattle, Action<string> errorCallback)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator GetStat(bool registerNewSeason, Action<string> errorCallback)
	{
		return null;
	}

	public void ShowSeasonOffPopup()
	{
	}

	public void RequestMatchmaking()
	{
	}

	public void CreateFriendlyMatchingRoom(int mapId)
	{
	}

	public void JoinFriendlyMatchingRoom(string roomId, bool byInvitation)
	{
	}

	public void SetCancelFriendlyMatch()
	{
	}

	public void ShowErrorPopupOnFriendlyMatching(EnumData.FriendlyMatchingError error, JsonData jsonData, IDepthManagerblePopup parent = null)
	{
	}

	public int GetPrevBattleCount()
	{
		return 0;
	}

	public bool SetBattleMapInfoForContinue()
	{
		return false;
	}

	public bool SetBattleMapInfoForReplay(string battleKey)
	{
		return false;
	}

	public bool SetBattleMapInfoForWatch(string battleKey)
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator GotoBattleMap()
	{
		return null;
	}

	public bool ShowResponseError(JsonData packetData, Action confirmCallBack = null)
	{
		return false;
	}

	public void ShowReplay(ulong uid, string battleKey, bool willRestoreList, Action completeCallback = null)
	{
	}

	private void ProcessReplayData(ulong uid, JsonData replayData, bool willRestoreList)
	{
	}

	[DebuggerHidden]
	public IEnumerator RequestBattlePlayDataForWatch(ulong hostUid, string battleKey, Action OnReponsedCallback = null, IDepthManagerblePopup parentPopup = null)
	{
		return null;
	}

	public bool IsTagActive()
	{
		return false;
	}

	public void FinishQueueRest()
	{
	}

	public void ShowTagEffect(Vector2 pos)
	{
	}

	public void ShowTagDirection(UserUnit userUnit, PopupBrawlTagDirection.Type type)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowTagDirection()
	{
		return null;
	}

	public void SetAutoTagUnitDatas(List<UnitData> tagUnitDatas)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowChargeEffect(List<UnitData> tagUnitDatas)
	{
		return null;
	}

	public void UnitTagAction(BattleUnit fightBattleUnit, BattleUnit tagBattleUnit)
	{
	}

	public void UnitTagAction(BattleControl battleControl)
	{
	}

	public UnitData UnitAutoTagAction(UnitData deadUnitData)
	{
		return null;
	}

	private void SwapUnitMove(UnitData unitData, IntVector2 toGridPos, bool isSettablePos = true)
	{
	}

	public void SetBrawlRotation()
	{
	}

	public bool IsInBrawlRotationJobType(int unitId)
	{
		return false;
	}

	public void ApplyBattleFormationPassives()
	{
	}

	public void ApplyBattleFormationPassives(BattleUser battleUser)
	{
	}

	private List<UserUnit> GetUserUnitsForBattleFormationTarget(BattleUser battleUser, EnumData.BattleFormationEffectType effectType, EnumData.BattleFormationTarget target)
	{
		return null;
	}

	public EnumData.PvpPositionType GetPositionTypeFromUnitId(BattleUser battleUser, int unitId)
	{
		return EnumData.PvpPositionType.NONE;
	}

	[DebuggerHidden]
	public IEnumerator RequestGetPvpUnitStatistics(int want_id)
	{
		return null;
	}

	private void OnRequestPvpUnitStatistics(JsonData response)
	{
	}

	public void CloneUserDeck(out UserBattleFormation userBattleFormation, out UserUnitGroup userUnitGroup)
	{
		userBattleFormation = null;
		userUnitGroup = null;
	}

	public int GetCompetitionCalibratingPoint()
	{
		return 0;
	}

	public void SetAchievementValue(EnumData.AchievementCheckType checkType, int setValue)
	{
	}

	public void AddAchievementValue(EnumData.AchievementCheckType checkType, int addValue = 1)
	{
	}

	public int GetAchievementValue(EnumData.AchievementCheckType checkType, bool isResetValue = true)
	{
		return 0;
	}

	public void ClearAchievementValue()
	{
	}

	public void InitFriendlyMatchPickUnits()
	{
	}

	public void CloneUserDeckFriendlyMatch(out UserBattleFormation userBattleFormation, out UserUnitGroup userUnitGroup)
	{
		userBattleFormation = null;
		userUnitGroup = null;
	}

	public bool SetBattleMapInfoForFriendlyMatch()
	{
		return false;
	}

	public void DeserializeWarInfo(JsonData jsonData)
	{
	}

	public void DeserializeContinuableBattleInfo(JsonData jsonData)
	{
	}

	public EnumData.MultiplayEarlyRetreatReason GetEarlyRetreatReason()
	{
		return EnumData.MultiplayEarlyRetreatReason.None;
	}

	public void ShowDesyncReplay(ulong uid, string battleKey, bool desync, string env)
	{
	}

	public bool IsHoldUnit(int unitId)
	{
		return false;
	}
}
