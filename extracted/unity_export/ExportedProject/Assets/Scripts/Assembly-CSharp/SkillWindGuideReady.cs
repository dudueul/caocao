public class SkillWindGuideReady : SkillBase
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

	public SkillWindGuideReady(AttackBase attack, UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}
