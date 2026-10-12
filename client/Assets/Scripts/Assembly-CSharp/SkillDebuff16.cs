public class SkillDebuff16 : SkillBase, IRateDamageSkill
{
	private bool m_isDamageHit;

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

	public SkillDebuff16(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, int successRate)
		: base(null, null)
	{
	}

	public SkillDebuff16(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public bool IsDamageHit()
	{
		return false;
	}
}
