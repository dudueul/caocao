using BattleRecorder;

public sealed class EffectGiveSpActionExp : EffectBase
{
	private EnumData.spAction m_spActionId;

	private int m_spActionValue;

	private int m_triggerRateValue;

	private int m_conditionVal1;

	private int m_conditionVal2;

	private SpActionBase.Origin m_origin;

	private int m_turnDuration;

	private int m_applyCountLimit;

	private SpActionBase m_given;

	private bool m_setStat;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectGiveSpActionExp(UnitObject triggerUnit, UnitObject targetUnit, EnumData.spAction spActionId, int spActionValue, int triggerRateValue, int conditionVal1, int conditionVal2, SpActionBase.Origin origin, int turnDuration, int applyCountLimit = -1, bool setStat = false)
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

	public static EffectGiveSpActionExp Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
