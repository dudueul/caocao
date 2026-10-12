using System.Collections.Generic;

public class SpAction620 : SpActionBase
{
	private static Dictionary<int, int> tileHelp;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	protected override void OnActiveOnce()
	{
	}

	public override int OnGetCalculatedInt(int tileId)
	{
		return 0;
	}

	public bool IsHelpTile(int tileId)
	{
		return false;
	}

	public float OnGetCalculatedHitRatio(int tileId, float value)
	{
		return 0f;
	}
}
