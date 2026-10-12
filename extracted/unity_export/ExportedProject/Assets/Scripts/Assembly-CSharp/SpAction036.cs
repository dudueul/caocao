public class SpAction036 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public bool CanUseSpAction(UnitData unitData)
	{
		return false;
	}

	public float OnGetCalculatedHitRatio(UnitData unitData, bool isSimulate, float value)
	{
		return 0f;
	}

	public float OnGetCalculatedAttackDamage(UnitData unitData, bool isSimulate, float value)
	{
		return 0f;
	}
}
