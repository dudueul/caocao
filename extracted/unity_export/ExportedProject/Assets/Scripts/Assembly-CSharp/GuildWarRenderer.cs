using System.Collections.Generic;
using UnityEngine;

public class GuildWarRenderer : MonoBehaviour
{
	private GuildWarMapInfo _mapInfo;

	private bool valid;

	public GuildWarMapInfo mapInfo
	{
		get
		{
			return null;
		}
	}

	protected static bool Color32Equal(Color32 left, Color32 right)
	{
		return false;
	}

	private static string GetGameObjectPoolName(string prefabName)
	{
		return null;
	}

	private static GameObject GetGameObject(string prefabName)
	{
		return null;
	}

	public static void DisposeGuildWarEffect(string prefabName, GameObject gameObject)
	{
	}

	public static GameObject ShowGuildWarEffect(string prefabName, Transform parent, Vector3 pos, bool isEffectSoundPlay = false)
	{
		return null;
	}

	public static bool IsMyEffect(ulong sourceUserId, ulong targetUserId)
	{
		return false;
	}

	public void InitRenderer(GuildWarMapInfo mapInfo)
	{
	}

	public Vector2 GetHexTileWorldPosition(int x, int y)
	{
		return default(Vector2);
	}

	public static Vector3Int oddr_to_cube(int posX, int posY)
	{
		return default(Vector3Int);
	}

	public static Vector2Int cube_to_oddr(int posX, int posY, int posZ)
	{
		return default(Vector2Int);
	}

	public static List<Vector2Int> GetGridArea(int posX, int posY, int radius)
	{
		return null;
	}

	public void SetValid(bool flag)
	{
	}

	public bool GetValid()
	{
		return false;
	}
}
