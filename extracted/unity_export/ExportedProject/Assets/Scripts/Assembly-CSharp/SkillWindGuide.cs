using System.Collections.Generic;

public class SkillWindGuide : SkillBase
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

	public SkillWindGuide(AttackBase attack, UnitData castUnit, UnitData targetUnit, List<UnitData> targetUnits, int successRate)
		: base(null, null)
	{
	}

	private void CalculateDestinationGrid(AttackBase attack, List<UnitData> targetUnits)
	{
	}

	private bool CheckGridIsValidToMoved(IntVector2 grid, List<UnitData> otherUnits)
	{
		return false;
	}
}
