public class SkillShoutWhiteTiger : SkillBase
{
	private EnumData.Skills m_skillId;

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

	public SkillShoutWhiteTiger(EnumData.Skills skillId, AttackBase attack, UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}
