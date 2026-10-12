public class SpAction690 : SpActionBase
{
	private static int effectAreaType;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public static bool CheckAffectedValue(UnitData triggerUnit, out int incAtkPercent, out int decIntePercent)
	{
		incAtkPercent = default(int);
		decIntePercent = default(int);
		return false;
	}
}
