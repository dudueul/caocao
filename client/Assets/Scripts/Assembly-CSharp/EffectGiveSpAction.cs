using BattleRecorder;

public sealed class EffectGiveSpAction : EffectBase
{
	private EnumData.spAction m_spActionId;

	private int m_spActionValue;

	private SpActionBase.Origin m_origin;

	private int m_turnDuration;

	private int m_applyCountLimit;

	private SpActionBase m_given;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectGiveSpAction(UnitObject triggerUnit, UnitObject targetUnit, EnumData.spAction spActionId, int spActionValue, SpActionBase.Origin origin, int turnDuration, int applyCountLimit = -1)
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

	public static EffectGiveSpAction Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
