using BattleRecorder;

public sealed class EffectSpActionConsumeCount : EffectBase
{
	private EnumData.spAction m_spActionId;

	private int m_spActionValue;

	private SpActionBase.Origin m_origin;

	private int m_initTurn;

	private int m_remainedTurn;

	private int m_initCount;

	private int m_remainedCount;

	private SpActionBase m_target;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectSpActionConsumeCount(UnitObject triggerUnit, UnitObject targetUnit, EnumData.spAction spActionId, int spActionValue, SpActionBase.Origin origin, int remainedTurn, int remainedCount, int initTurn, int initCount)
		: base(null, null)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectSpActionConsumeCount Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
