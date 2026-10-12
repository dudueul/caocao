public class SkillHighPhysicalStrike : SkillBase
{
	private int costHP;

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

	public SkillHighPhysicalStrike(AttackBase attack, UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}
}
