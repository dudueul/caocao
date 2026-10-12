public class SkillThrowBean : SkillBase
{
	public static int healingBeanId;

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

	public SkillThrowBean(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public static bool IsAvailable(UnitData castUnit, UnitData targetUnit)
	{
		return false;
	}
}
