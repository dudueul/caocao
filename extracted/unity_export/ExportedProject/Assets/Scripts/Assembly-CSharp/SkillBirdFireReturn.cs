using System;

public class SkillBirdFireReturn : SkillBase
{
	public static readonly int FIRE_RETURN_AI_BAN_SCORE;

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

	public SkillBirdFireReturn(UnitData castUnit, UnitData targetUnit, int successRate)
		: base(null, null)
	{
	}

	public static IntVector2 GetTeleportGridPos(UnitData triggerUnitData, Func<UnitData, bool> targetFilter)
	{
		return default(IntVector2);
	}

	public static bool IsAreaFireTile(int effectAreaType, IntVector2 triggerGridPos, UnitData targetUnitdata, UnitMetaData.RangeShape atkRangeShape, out IntVector2 goalPos)
	{
		goalPos = default(IntVector2);
		return false;
	}
}
