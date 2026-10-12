using BattleRecorder;

public sealed class EffectUnitExpBySpAction : EffectUnitExpBase
{
	private int m_exp;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitExpBySpAction(UnitObject triggerUnit, UnitObject targetUnit, int exp)
		: base(null, null)
	{
	}

	protected override int CaculateUnitExp()
	{
		return 0;
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectUnitExpBySpAction Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
