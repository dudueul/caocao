using System.Collections.Generic;
using System.Text;
using UnityEngine;

public static class MapEditorUtil
{
	private static Dictionary<int, Texture2D> m_tileTextures;

	public static StringBuilder ms_stringBuilder;

	public static void ClearReferences()
	{
	}

	public static Color ConvertHexToRGB(string hexColor)
	{
		return default(Color);
	}

	public static IntVector2 ConvertWorldPosToGridPos(Vector2 pos, int xTileCount, int yTileCount)
	{
		return default(IntVector2);
	}

	public static Vector2 ConvertGridPosToWorldPos(IntVector2 pos, int xTileCount, int yTileCount)
	{
		return default(Vector2);
	}

	public static Texture2D GetTileTexture(int tileId)
	{
		return null;
	}

	public static Sprite GetTileSprite(int tileId)
	{
		return null;
	}
}
