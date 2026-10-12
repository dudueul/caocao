public class SkillEffectHPDown : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectHPDown(AttackBase attack, EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, int delta)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
