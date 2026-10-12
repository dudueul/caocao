public class SkillHeal4 : SkillBase
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

	public SkillHeal4(AttackBase attack, UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
