public class SkillDragonBurp : SkillBase
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

	public SkillDragonBurp(SkillAttack skillAttack, UnitData castUnit, UnitData targetUnit, int skillDamage, int successRate, bool isCritical)
		: base(null, null)
	{
	}
}
