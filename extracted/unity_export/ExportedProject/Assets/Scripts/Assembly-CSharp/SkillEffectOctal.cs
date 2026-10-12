public class SkillEffectOctal : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectOctal(AttackBase attack, EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, int skillDamage, bool isCritical)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}
