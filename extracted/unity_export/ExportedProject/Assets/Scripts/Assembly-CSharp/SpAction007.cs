public class SpAction007 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public bool CheckCondition()
	{
		return false;
	}

	private int GetAroundOwnUnitCount()
	{
		return 0;
	}
}
