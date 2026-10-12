public class SkillEffectIntelligence : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectIntelligence(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, int delta)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
