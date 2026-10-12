using BattleRecorder;

public sealed class EffectRemoveSpAction : EffectBase
{
	private EnumData.spAction m_spActionId;

	private int m_spActionValue;

	private bool m_removed;

	private SpActionBase.Origin m_prevOrigin;

	private int m_prevInitTurnDuration;

	private int m_prevInitCountLimitation;

	private int m_prevRemainingTurn;

	private int m_prevRemainingCount;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectRemoveSpAction(UnitObject triggerUnit, UnitObject targetUnit, EnumData.spAction spActionId, int spActionValue)
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

	public static EffectRemoveSpAction Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
