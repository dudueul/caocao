using UnityEngine;

public class TileMarker : MonoBehaviour
{
	public GameObject m_tileOutline;

	private static SpriteRenderer m_tileSpriteRenderer;

	public static TileMarker CreateMarker(TileGroup tileGroup, Texture texture, Sprite spriteTile)
	{
		return null;
	}

	public static TileMarker CreateMarker(UnitGroup unitGroup, Texture texture)
	{
		return null;
	}

	public static TileMarker CreateMarker(MapObjectGroup objGroup, Texture texture, Sprite sprite)
	{
		return null;
	}

	public static TileMarker CreateMarker(MapEditControl go, Texture texture, Sprite sprite)
	{
		return null;
	}

	private static TileMarker CreateMarkerImpl(GameObject parent, Texture texture, Sprite spriteTile)
	{
		return null;
	}

	public void UpdateOutline(Texture texture)
	{
	}

	public void UpdateOutline(Texture texture, Sprite spriteTile)
	{
	}

	public void Destroy()
	{
	}

	private static Mesh NewMesh()
	{
		return null;
	}

	public void MoveMarker(Vector2 tileSize, Vector3 mouseHitPos, MapInfo mapInfo)
	{
	}

	public IntVector2 GetMarkerGridPos(int tileXCount, int tileYCount)
	{
		return default(IntVector2);
	}
}
