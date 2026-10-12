public class SkillDragonEye : SkillBase
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

	public SkillDragonEye(AttackBase attack, UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	private bool IsTreasureOriginSp(SpActionBase spAction)
	{
		return false;
	}

	private int GetLessRes(EnumData.UnitResType resType, int addValue)
	{
		return 0;
	}
}
