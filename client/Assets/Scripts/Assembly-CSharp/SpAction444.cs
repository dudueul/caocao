public class SpAction444 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetStaticFloat()
	{
		return 0f;
	}

	public override void ResetIntState()
	{
	}

	public override float GetAttackSuccessRate(UnitData atkUnitData, UnitData defUnitData, int attackIdx, AttackProcess attackProcess, float value)
	{
		return 0f;
	}
}
