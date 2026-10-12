public class SpAction709 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnApplyPostEffect(ResultPerUnit result, Attack attack)
	{
	}

	public override int OnGetCalculatedResult(UnitData targetUnitData)
	{
		return 0;
	}

	private int CalculateDamage(UnitData targetUnitData)
	{
		return 0;
	}
}
