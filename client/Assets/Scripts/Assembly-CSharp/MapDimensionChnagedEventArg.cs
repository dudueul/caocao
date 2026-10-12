using System;

public class MapDimensionChnagedEventArg : EventArgs
{
	public IntVector2 dimension { get; set; }

	public MapDimensionChnagedEventArg(IntVector2 mapDimension)
	{
	}
}
