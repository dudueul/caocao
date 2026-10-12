public class SkillBuff8 : SkillBase
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

	public SkillBuff8(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
