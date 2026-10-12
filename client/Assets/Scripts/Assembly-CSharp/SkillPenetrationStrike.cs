public class SkillPenetrationStrike : SkillBase
{
	private static int moveTileCount;

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

	public SkillPenetrationStrike(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public static IntVector2 GetTriggerDestGridPos(UnitData castUnit, UnitData targetUnit)
	{
		return default(IntVector2);
	}

	public static IntVector2 GetTargetDestGridPos(UnitData castUnit, UnitData targetUnit)
	{
		return default(IntVector2);
	}

	public static bool IsAvailable(UnitData castUnit, UnitData targetUnit)
	{
		return false;
	}
}
