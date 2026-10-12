using BattleRecorder;

public sealed class EffectWeaponExp : EffectWeaponExpBase
{
	private ResultPerUnit m_result;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectWeaponExp(UnitObject triggerUnit, UnitObject targetUnit, ResultPerUnit result)
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

	public static EffectWeaponExp Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
