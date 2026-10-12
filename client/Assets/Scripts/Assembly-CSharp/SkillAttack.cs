public class SkillAttack : AttackBase
{
	public EnumData.Skills skillID { get; set; }

	public int attackIdx { get; set; }

	public UnitData baseTargetUnitData
	{
		get
		{
			return null;
		}
	}

	public int hitCount
	{
		get
		{
			return 0;
		}
	}

	public int criticalCount
	{
		get
		{
			return 0;
		}
	}

	public int castingCount
	{
		get
		{
			return 0;
		}
	}

	public SkillAttack(EnumData.Skills skillid, int atkIndex)
	{
	}

	public bool IsHitted(UnitData targetUnitData)
	{
		return false;
	}
}
