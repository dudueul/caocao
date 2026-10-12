public class SkillEffectMorDown : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectMorDown(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.MOR_DOWN)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
