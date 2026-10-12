public class SpAction523 : SpActionBase
{
	private const int FLAME_TILE_ID = 4200058;

	private const int ADD_DMG_PERCENTAGE = 50;

	private const int FLAME_OBJECT_ID = 4300516;

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
