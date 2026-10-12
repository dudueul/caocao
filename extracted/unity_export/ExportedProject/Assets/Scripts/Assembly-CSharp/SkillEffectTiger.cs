public class SkillEffectTiger : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectTiger(AttackBase attack, EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
