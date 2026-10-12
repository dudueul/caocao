using System;
using System.Collections.Generic;

public sealed class BattleMissions : SceneSingleton<BattleMissions>
{
	private List<BattleMission> m_missions;

	private List<BattleMission> m_missionsWith4th;

	private Action OnMissionStateChecked__BackingField;

	public List<BattleMission> missions
	{
		get
		{
			return null;
		}
	}

	public BattleMission mission4th
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleMission get_Item(int id)
	{
		return null;
	}

	public event Action OnMissionStateChecked
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Awake()
	{
	}

	public void InitMissions()
	{
	}

	private void Init4thStampMission()
	{
	}

	public List<BattleMission> GetSortedMissions()
	{
		return null;
	}

	public void UpdateKillMissions(List<UnitData> targets, UnitData attacker)
	{
	}

	public void UpdateKillAllMission()
	{
	}

	public void UpdateSurviveMission()
	{
	}

	public void UpdateNoConsumablesMission(Item item)
	{
	}

	public void RecordMissionStatus(EnumData.BattleMissionType missionType, int arg1, int arg2 = 0)
	{
	}

	private bool IsBelongToForce(EnumData.ForceType forceType, int unitId)
	{
		return false;
	}

	private List<UnitData> GetUnitsByForceType(EnumData.ForceType forceType)
	{
		return null;
	}

	private int GetAlivedUnitCountByForceType(EnumData.ForceType forceType)
	{
		return 0;
	}

	private int GetRetreatUnitCountByForceType(EnumData.ForceType forceType)
	{
		return 0;
	}

	public int GetVictoryGrade()
	{
		return 0;
	}

	protected override void OnDestroy()
	{
	}
}
