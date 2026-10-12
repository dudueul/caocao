public class SkillDragonAnger : SkillBase
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

	public SkillDragonAnger(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public override void _Apply()
	{
	}
}
