public class SkillEffectBerserk : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectBerserk(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
