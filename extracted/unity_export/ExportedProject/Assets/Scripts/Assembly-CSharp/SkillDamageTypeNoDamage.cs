public class SkillDamageTypeNoDamage : SkillDamageTypeBase
{
	protected override EnumData.damageType damageType
	{
		get
		{
			return EnumData.damageType.NO_DAMAGE;
		}
	}

	public SkillDamageTypeNoDamage(UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}
