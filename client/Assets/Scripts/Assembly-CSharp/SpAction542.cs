public class SpAction542 : SpActionBase
{
	private float m_accumulated;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnitData, float value, bool isSimulate)
	{
		return 0f;
	}

	public override void OnApplyPostEffect(ResultPerUnit result)
	{
	}
}
