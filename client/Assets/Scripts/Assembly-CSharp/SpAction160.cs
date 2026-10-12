using Caocao;

public class SpAction160 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public bool OwnTurn(UnitSide unitSide)
	{
		return false;
	}
}
