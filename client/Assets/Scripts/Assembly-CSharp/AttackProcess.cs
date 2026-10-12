using System.Collections.Generic;

public class AttackProcess
{
	private UnitData m_trigterUnitData;

	private List<UnitData> m_targetUnitDatas;

	private float m_2ndAtkSuccessRate;

	private bool m_SecondAtkSucceeded;

	public EnumData.AttackType attackType { get; set; }

	public UnitData trigterUnitData
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

	public float secondAtkSuccessRate
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool isSecondAtkSucceeded
	{
		get
		{
			return false;
		}
	}

	public List<Attack> attackResults { get; set; }

	public int attackSuccessCount
	{
		get
		{
			return 0;
		}
	}

	public int criticalOccuredCount
	{
		get
		{
			return 0;
		}
	}

	public AttackProcess(UnitData attackerData, List<UnitData> targetGameObjs)
	{
	}

	public override string ToString()
	{
		return null;
	}
}
