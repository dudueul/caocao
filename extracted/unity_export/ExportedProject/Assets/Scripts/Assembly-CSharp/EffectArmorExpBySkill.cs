using BattleRecorder;

public sealed class EffectArmorExpBySkill : EffectArmorExpBase
{
	private ResultPerUnit m_resultPerUnit;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectArmorExpBySkill(UnitObject triggerUnit, UnitObject targetUnit, ResultPerUnit resultPerUnit)
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

	public static EffectArmorExpBySkill Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
