using BattleRecorder;

public sealed class EffectWeaponExpBySpAction : EffectWeaponExpBase
{
	private int m_exp;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectWeaponExpBySpAction(UnitObject triggerUnit, UnitObject targetUnit, int exp)
		: base(null, null)
	{
	}

	protected override int CaculateWeaponExp()
	{
		return 0;
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectWeaponExpBySpAction Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
