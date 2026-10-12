using BattleRecorder;

public abstract class EffectExp : EffectInteger
{
	protected int m_prevLevel;

	protected int m_level;

	public bool isLevelUp
	{
		get
		{
			return false;
		}
	}

	public int prevLev
	{
		get
		{
			return 0;
		}
	}

	public int lev
	{
		get
		{
			return 0;
		}
	}

	protected EffectExp(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null, 0)
	{
	}
}
