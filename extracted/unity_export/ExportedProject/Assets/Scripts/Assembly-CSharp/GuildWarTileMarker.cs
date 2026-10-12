using UnityEngine;

public class GuildWarTileMarker : MonoBehaviour
{
	public GameObject m_tileOutline;

	private static MeshRenderer m_tileMeshRenderer;

	public void Destroy()
	{
	}

	public static GuildWarTileMarker CreateMarker(GuildWarTileGroup tileGroup, Texture texture, Sprite spriteTile)
	{
		return null;
	}

	public static GuildWarTileMarker CreateMarker(GuildWarBuildGroup tileGroup, Texture texture, Sprite spriteTile)
	{
		return null;
	}

	private static GuildWarTileMarker CreateMarkerImpl(GameObject parent, Texture texture, Sprite spriteTile)
	{
		return null;
	}

	public static Mesh NewMesh()
	{
		return null;
	}

	public void UpdateOutline(Texture texture)
	{
	}

	public void UpdateOutline(Texture texture, Sprite spriteTile)
	{
	}
}
