public class SkillEffectShout : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectShout(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
