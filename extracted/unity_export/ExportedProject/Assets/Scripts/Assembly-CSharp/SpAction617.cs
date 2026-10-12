public class SpAction617 : SpActionBase
{
	private UnitData m_failTargetUnit;

	private int m_failCount;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData others, float value)
	{
		return 0f;
	}

	public void ResetFailCount()
	{
	}

	public void AddFailCount(UnitData targetUnit)
	{
	}
}
