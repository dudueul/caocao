using BattleRecorder;

public sealed class EffectUnitExpBySkill : EffectUnitExpBase
{
	private SkillProcess m_skillProcess;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitExpBySkill(UnitObject triggerUnit, UnitObject targetUnit, SkillProcess skillProcess)
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

	public static EffectUnitExpBySkill Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
