using System.Collections.Generic;

public class ChangeBuffSpell : UserSpellBase
{
	private static List<EffectCondition> applyEffectConditions;

	public override void Apply()
	{
	}

	public override void Apply(List<UnitData> targetUnitDatas)
	{
	}

	private bool IsSpellTargetConditionType(EnumData.Conditions pdConditionId)
	{
		return false;
	}

	private EffectCondition CreateEffectCondition(EnumData.Conditions pdConditionId, UnitData targetUniData)
	{
		return null;
	}
}
