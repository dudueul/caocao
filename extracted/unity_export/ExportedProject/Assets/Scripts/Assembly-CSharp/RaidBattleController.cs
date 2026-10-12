using CodeStage.AntiCheat.ObscuredTypes;

public class RaidBattleController : SceneSingleton<RaidBattleController>
{
	private RaidBossState m_bossState;

	private EnumData.RaidType m_raidType;

	private UnitData m_targetUnitData;

	private ObscuredInt[] m_maxRes;

	private ObscuredInt[] m_curRes;

	private ObscuredInt[] m_delta;

	private bool m_battleStart;

	private bool m_hitted;

	private bool m_initialized;

	public UnitData TargetUnitData
	{
		get
		{
			return null;
		}
	}

	public bool Hitted
	{
		get
		{
			return false;
		}
	}

	public void Initialize(UnitData targetUnitData, IRaidInfo raidInfo)
	{
	}

	public void StartBattle()
	{
	}

	private void SetRes(EnumData.UnitResType resType, int value)
	{
	}

	public int GetBattleStartRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	public int GetDeltaRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	public int GetCurRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	public int GetMaxRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	private bool CheckValid(UnitData unit)
	{
		return false;
	}

	public void ApplyEffect(UnitData unitData, EnumData.UnitResType resType, int to)
	{
	}

	public void RevertEffect(UnitData unitData, EnumData.UnitResType resType, int to)
	{
	}

	public void Animate(UnitData unitData, EnumData.UnitResType resType, int from, int to)
	{
	}
}
