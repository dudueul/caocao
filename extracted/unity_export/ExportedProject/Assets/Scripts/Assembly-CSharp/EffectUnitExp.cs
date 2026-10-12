using BattleRecorder;

public sealed class EffectUnitExp : EffectUnitExpBase
{
	private ResultPerUnit m_result;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitExp(UnitObject triggerUnit, UnitObject targetUnit, ResultPerUnit result)
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

	public static EffectUnitExp Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
