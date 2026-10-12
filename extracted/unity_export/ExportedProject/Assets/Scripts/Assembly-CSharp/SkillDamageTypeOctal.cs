public class SkillDamageTypeOctal : SkillDamageTypeBase
{
	protected override EnumData.damageType damageType
	{
		get
		{
			return EnumData.damageType.NO_DAMAGE;
		}
	}

	public SkillDamageTypeOctal(UnitData castUnit, UnitData targetUnit, int skillDamage)
		: base(null, null)
	{
	}
}
