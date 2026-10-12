public class SkillDamageTypeRateDamage : SkillDamageTypeBase
{
	protected override EnumData.damageType damageType
	{
		get
		{
			return EnumData.damageType.NO_DAMAGE;
		}
	}

	public SkillDamageTypeRateDamage(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage)
		: base(null, null)
	{
	}
}
