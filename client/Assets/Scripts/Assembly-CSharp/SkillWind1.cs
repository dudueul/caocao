public class SkillWind1 : SkillBase
{
	public override EnumData.Skills skillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public override EnumData.SkillTypes skillType
	{
		get
		{
			return EnumData.SkillTypes.ATK_FIRE;
		}
	}

	public SkillWind1(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, int successRate, bool isCritical)
		: base(null, null)
	{
	}
}
