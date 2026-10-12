public class SkillDebuff3 : SkillBase
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

	public SkillDebuff3(UnitData castUnit, UnitData targetUnit, int skillDamage, int successRate)
		: base(null, null)
	{
	}
}
