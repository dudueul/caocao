using BattleRecorder;

public abstract class EffectInteger : EffectBase
{
	protected int m_delta;

	protected int m_fromValue;

	protected int m_toValue;

	public int delta
	{
		get
		{
			return 0;
		}
	}

	public int fromValue
	{
		get
		{
			return 0;
		}
	}

	public int toValue
	{
		get
		{
			return 0;
		}
	}

	protected EffectInteger(UnitObject triggerUnit, UnitObject targetUnit, int delta)
		: base(null, null)
	{
	}
}
