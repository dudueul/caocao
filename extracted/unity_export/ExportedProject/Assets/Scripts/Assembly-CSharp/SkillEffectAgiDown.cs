public class SkillEffectAgiDown : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectAgiDown(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.AGI_DOWN)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
