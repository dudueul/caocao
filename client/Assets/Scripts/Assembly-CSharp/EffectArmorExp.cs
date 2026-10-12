using BattleRecorder;

public sealed class EffectArmorExp : EffectArmorExpBase
{
	private ResultPerUnit m_resultPerUnit;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectArmorExp(UnitObject triggerUnit, UnitObject targetUnit, ResultPerUnit resultPerUnit)
		: base(null, null)
	{
	}

	protected override int CaculateArmorExp()
	{
		return 0;
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectArmorExp Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
