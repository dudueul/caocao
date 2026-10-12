public class SkillEffectMorUp : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectMorUp(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.MOR_UP)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
