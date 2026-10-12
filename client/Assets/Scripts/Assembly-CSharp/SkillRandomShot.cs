public class SkillRandomShot : SkillBase
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

	public SkillRandomShot(UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}
