using System;

public class SpAction415 : SpActionBase
{
	public bool guarded;

	private static Random random;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloat(float damage)
	{
		return 0f;
	}

	public static UnitData GetGuardUnitData(UnitData baseUnit)
	{
		return null;
	}
}
