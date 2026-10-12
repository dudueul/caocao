public class SpAction443 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float GetAttackSuccessRate(UnitData atkUnitData, UnitData defUnitData, float value)
	{
		return 0f;
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData defUnit, float value)
	{
		return 0f;
	}

	private float Calculate(UnitData atkUnitData, UnitData defUnitData, float value)
	{
		return 0f;
	}
}
