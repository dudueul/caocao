public class SkillBuff13 : SkillBase
{
	private static int m_hpCostPercent;

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

	public static int hpCostPercent
	{
		get
		{
			return 0;
		}
	}

	public SkillBuff13(AttackBase attack, UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public static int GetHPCostAmount(UnitData unitData)
	{
		return 0;
	}
}
