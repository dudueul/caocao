using BattleRecorder;

public sealed class EffectArmorExpBySpAction : EffectArmorExpBase
{
	private int m_exp;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectArmorExpBySpAction(UnitObject triggerUnit, UnitObject targetUnit, int exp)
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

	public static EffectArmorExpBySpAction Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
