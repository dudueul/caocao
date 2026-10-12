using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class AnnihilationWarManager
{
	private AnnihilationWarMatchInfo m_matchInfo;

	private bool m_pvpUnitStatisticsRequestIng;

	private List<int> m_pvpUnitStatiWarIds;

	private Dictionary<int, PvpUnitStatistics> m_pvpUnitStatisticses;

	private List<int> m_annihilationWarIds;

	private DateTime m_warEndTime;

	public static AnnihilationWarManager instance
	{
		get
		{
			return null;
		}
	}

	public AnnihilationWarMatchInfo matchInfo
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

	public Dictionary<int, PvpUnitStatistics> pvpUnitStatisticses
	{
		get
		{
			return null;
		}
	}

	public List<int> annihilationWarIds
	{
		get
		{
			return null;
		}
	}

	public ObscuredInt selectedAnnihilationWarId { get; set; }

	public DateTime warEndTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public string lastLimitDay { get; set; }

	public string limitDay { get; set; }

	public float remainingTimeOfSeason
	{
		get
		{
			return 0f;
		}
	}

	public int mmr { get; set; }

	public void MakeSpawnInfos(UserBattleFormation userBattleFormation)
	{
	}

	public void ApplyUnitsToPickedUnit()
	{
	}

	public int GetUserUnitId(int formationIndex)
	{
		return 0;
	}

	public int GetOpponentUnitId(int formationIndex)
	{
		return 0;
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

	public EnumData.PvpPositionType GetPositionTypeFromUnitId(UserData userData, int unitId)
	{
		return EnumData.PvpPositionType.NONE;
	}

	public EnumData.PvpPositionType GetPositionTypeFromSlotIndex(UserData userData, int slotIndex)
	{
		return EnumData.PvpPositionType.NONE;
	}

	public PvpUnitStatistics pvpUnitStatistics(int warId)
	{
		return null;
	}

	public void CloneUserDeck(out UserBattleFormation userBattleFormation, out UserUnitGroup userUnitGroup)
	{
		userBattleFormation = null;
		userUnitGroup = null;
	}

	public void ApplyBattleFormationPassives()
	{
	}

	public void ApplyBattleFormationPassives(UserData userData)
	{
	}

	private List<UserUnit> GetUserUnitsForBattleFormationTarget(UserData userData, EnumData.BattleFormationEffectType effectType, EnumData.BattleFormationTarget target, List<UserUnitGroup.UnitInfo> unitList)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator RequestGetPvpUnitStatistics(int want_id)
	{
		return null;
	}

	private void OnRequestPvpUnitStatistics(JsonData response)
	{
	}
}
