public class SpAction435 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData attacker, float value, bool isSimulate = false)
	{
		return 0f;
	}

	public static int _GetDamageDecreasePct(int passiveValue, int atkMor, int defMor)
	{
		return 0;
	}
}
