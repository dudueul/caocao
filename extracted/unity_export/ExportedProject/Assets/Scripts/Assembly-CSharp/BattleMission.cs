public class BattleMission
{
	private Mission m_mission;

	public BattleMissionState curState;

	public BattleMissionState prevState;

	public int missionId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.BattleMissionType missionType
	{
		get
		{
			return EnumData.BattleMissionType.Win;
		}
	}

	public EnumData.ForceType forceType
	{
		get
		{
			return EnumData.ForceType.OwnForce;
		}
	}

	public int targetUnitId1
	{
		get
		{
			return 0;
		}
	}

	public int targetUnitId2
	{
		get
		{
			return 0;
		}
	}

	public int targetUnitId3
	{
		get
		{
			return 0;
		}
	}

	public int limitTurn
	{
		get
		{
			return 0;
		}
	}

	public int objectiveCount
	{
		get
		{
			return 0;
		}
	}

	public int treasureType
	{
		get
		{
			return 0;
		}
	}

	public int jobType
	{
		get
		{
			return 0;
		}
	}

	public bool unit1Status
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public bool unit2Status
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public bool unit3Status
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isSuccess
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isFailed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public BattleMission(int missionId)
	{
	}

	private void BackupCurState()
	{
	}

	public void SetUnitStatus(int curUnitId, bool isTrue = true)
	{
	}

	public bool IsSucceed()
	{
		return false;
	}

	public EnumData.MISSION_STATUS GetMissionState()
	{
		return EnumData.MISSION_STATUS.None;
	}
}
