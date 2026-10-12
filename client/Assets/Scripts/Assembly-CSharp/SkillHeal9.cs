public class SkillHeal9 : SkillBase
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

	public SkillHeal9(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
