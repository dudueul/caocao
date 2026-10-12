public class SpAction683 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, Attack attackResult, int value, bool isSimulate)
	{
	}

	public override void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, SkillAttack attackResult, int value, bool isSimulate)
	{
	}

	private void ApplyOverchargedCondition(UnitData triggerUnitData, UnitData targetUnitdata, AttackBase attack, EnumData.Skills skillId, int value, bool isSimulate)
	{
	}
}
