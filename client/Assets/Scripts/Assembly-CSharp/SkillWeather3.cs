public class SkillWeather3 : SkillBase
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

	public SkillWeather3(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
