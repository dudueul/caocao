public class SpAction065 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override bool OnGetCalculatedBoolResult(bool isSimulate, UnitData[] applyTargets)
	{
		return false;
	}

	public override int OnGetCalculatedIntBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, Attack attackResult, int value, int rawAttackDamage, bool isSimulate)
	{
		return 0;
	}
}
