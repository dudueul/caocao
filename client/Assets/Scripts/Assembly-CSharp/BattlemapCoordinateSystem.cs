using UnityEngine;

public class BattlemapCoordinateSystem : ICoordinateSystem
{
	private int m_mapMaxAreaX;

	private int m_mapMaxAreaY;

	public void OnMapDimensionChanged(object sender, MapDimensionChnagedEventArg arg)
	{
	}

	public IntVector2 convertWorldPosToGridPos(Vector2 pos)
	{
		return default(IntVector2);
	}

	public Vector2 convertGridPosToWorldPos(IntVector2 gridPos)
	{
		return default(Vector2);
	}
}
