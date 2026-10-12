public class SkillDamageTypeNormalDamage : SkillDamageTypeBase
{
	protected override EnumData.damageType damageType
	{
		get
		{
			return EnumData.damageType.NO_DAMAGE;
		}
	}

	public SkillDamageTypeNormalDamage(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, bool isHitted, bool isCritical)
		: base(null, null)
	{
	}
}
