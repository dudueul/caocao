public class SpAction522 : SpActionBase
{
	private int m_objectId;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnit, float value, bool isSimulate)
	{
		return 0f;
	}

	public override void OnApplyPostEffect(ResultPerUnit result, Attack attack)
	{
	}
}
