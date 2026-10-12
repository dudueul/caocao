using System.Collections.Generic;

public class SkillProcess
{
	private UnitData m_triggerUnitData;

	private List<UnitData> m_targetUnitDatas;

	private float m_streakCastRate;

	private bool m_streakCastSucceeded;

	private List<SkillAttack> m_attacks;

	public ResultPerUnit triggerResultByCast;

	public EnumData.Skills skillID { get; set; }

	public UnitData triggerUnitData
	{
		get
		{
			return null;
		}
	}

	public List<UnitData> targetUnitDatas
	{
		get
		{
			return null;
		}
	}

	public UnitData baseTargetUnitData
	{
		get
		{
			return null;
		}
	}

	public float streakSuccessRate
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool isStreakCastSucceeded
	{
		get
		{
			return false;
		}
	}

	public List<SkillAttack> attacks
	{
		get
		{
			return null;
		}
	}

	public int hitCount
	{
		get
		{
			return 0;
		}
	}

	public int criticalCount
	{
		get
		{
			return 0;
		}
	}

	public int castingCount
	{
		get
		{
			return 0;
		}
	}

	public SkillProcess(EnumData.Skills usedSkillID, UnitData castUnitData, List<UnitData> targetDatas)
	{
	}
}
