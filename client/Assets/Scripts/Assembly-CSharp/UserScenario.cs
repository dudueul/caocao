using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserScenario : ITransmittable
{
	private static PermanentData ms_pd;

	private ObscuredInt m_id;

	private ObscuredBool m_isUnlockCash;

	private ObscuredInt m_victoryGrade;

	private ObscuredInt m_playCount;

	private ObscuredInt m_victoryCount;

	private ObscuredBool m_isEnterHard;

	private ObscuredInt m_victoryGradeHard;

	private ObscuredInt m_playCountHard;

	private ObscuredInt m_victoryCountHard;

	private ObscuredInt m_victoryGradeClassic;

	private ObscuredInt m_playCountClassic;

	private ObscuredInt m_victoryCountClassic;

	private ObscuredInt m_resetCountClassic;

	private ObscuredInt m_isLock;

	private ObscuredInt m_isLockHard;

	private ObscuredInt m_isLockClassic;

	private ObscuredInt m_clearClassic;

	private ObscuredBool m_isSuccessMission1;

	private ObscuredBool m_isSuccessMission2;

	private ObscuredBool m_isSuccessMission3;

	private ObscuredBool m_isEnterClassic;

	public Action deserializeCallback { get; set; }

	public int id
	{
		get
		{
			return 0;
		}
	}

	public bool isUnlockCash
	{
		get
		{
			return false;
		}
	}

	public int victoryGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int playCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int victoryCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isEnterHard
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int victoryGradeHard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int playCountHard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int victoryCountHard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int victoryGradeClassic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int playCountClassic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int victoryCountClassic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resetCountClassic
	{
		get
		{
			return 0;
		}
	}

	public int isLock
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isLockHard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isLockClassic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int clearClassic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isSuccessMission1
	{
		get
		{
			return false;
		}
	}

	public bool isSuccessMission2
	{
		get
		{
			return false;
		}
	}

	public bool isSuccessMission3
	{
		get
		{
			return false;
		}
	}

	public bool isEnterClassic
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void SetEnterState(EnumData.ScenarioModeLevel modeLevel, bool state)
	{
	}

	public bool GetEnterState(EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public int GetVictoryGrade(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}

	public int GetVictoryCount(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}

	public bool GetLockedState(EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}
}
