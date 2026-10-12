public class SkillEmperor4 : SkillBase
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

	public SkillEmperor4(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
