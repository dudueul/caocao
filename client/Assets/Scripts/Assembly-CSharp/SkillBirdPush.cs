public class SkillBirdPush : SkillBase
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

	public SkillBirdPush(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, bool isCritical, int successRate)
		: base(null, null)
	{
	}

	private void CalculatePush(AttackBase attack, int skillDamage, bool isCritical)
	{
	}
}
