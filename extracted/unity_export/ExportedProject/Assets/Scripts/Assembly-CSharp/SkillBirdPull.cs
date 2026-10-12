public class SkillBirdPull : SkillBase
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

	public SkillBirdPull(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, bool isCritical, int successRate)
		: base(null, null)
	{
	}

	private void CalculatePull(AttackBase attack, int skillDamage, bool isCritical)
	{
	}
}
