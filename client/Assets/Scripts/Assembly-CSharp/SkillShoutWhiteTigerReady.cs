public class SkillShoutWhiteTigerReady : SkillBase
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

	public SkillShoutWhiteTigerReady(EnumData.Skills skillid, AttackBase attack, UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}
