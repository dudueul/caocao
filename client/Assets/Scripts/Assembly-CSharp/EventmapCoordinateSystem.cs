using UnityEngine;

public class EventmapCoordinateSystem : ICoordinateSystem
{
	private int m_mapMaxAreaX;

	private int m_mapMaxAreaY;

	private float m_tileWidth;

	private float m_tileHeight;

	private IntVector2 m_preOffset;

	private IntVector2 m_postOffset;

	private IntVector2 m_diffOffset;

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
