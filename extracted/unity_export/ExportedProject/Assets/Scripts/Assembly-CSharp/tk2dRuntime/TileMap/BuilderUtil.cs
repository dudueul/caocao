using System.Collections.Generic;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	public static class BuilderUtil
	{
		private static List<int> TilePrefabsX;

		private static List<int> TilePrefabsY;

		private static List<int> TilePrefabsLayer;

		private static List<GameObject> TilePrefabsInstance;

		private const int tileMask = 16777215;

		public static bool InitDataStore(tk2dTileMap tileMap)
		{
			return false;
		}

		private static GameObject GetExistingTilePrefabInstance(tk2dTileMap tileMap, int tileX, int tileY, int tileLayer)
		{
			return null;
		}

		public static void SpawnPrefabsForChunk(tk2dTileMap tileMap, SpriteChunk chunk, int baseX, int baseY, int layer, int[] prefabCounts)
		{
		}

		public static void SpawnPrefabs(tk2dTileMap tileMap, bool forceBuild)
		{
		}

		public static void HideTileMapPrefabs(tk2dTileMap tileMap)
		{
		}

		private static Vector3 GetTilePosition(tk2dTileMap tileMap, int x, int y)
		{
			return default(Vector3);
		}

		public static void CreateRenderData(tk2dTileMap tileMap, bool editMode, Dictionary<Layer, bool> layersActive)
		{
		}

		public static void GetLoopOrder(tk2dTileMapData.SortMethod sortMethod, int w, int h, out int x0, out int x1, out int dx, out int y0, out int y1, out int dy)
		{
			x0 = default(int);
			x1 = default(int);
			dx = default(int);
			y0 = default(int);
			y1 = default(int);
			dy = default(int);
		}

		public static int GetTileFromRawTile(int rawTile)
		{
			return 0;
		}

		public static bool IsRawTileFlagSet(int rawTile, tk2dTileFlags flag)
		{
			return false;
		}

		public static void SetRawTileFlag(ref int rawTile, tk2dTileFlags flag, bool setValue)
		{
		}

		public static void InvertRawTileFlag(ref int rawTile, tk2dTileFlags flag)
		{
		}

		public static Vector3 ApplySpriteVertexTileFlags(tk2dTileMap tileMap, tk2dSpriteDefinition spriteDef, Vector3 pos, bool flipH, bool flipV, bool rot90)
		{
			return default(Vector3);
		}

		public static Vector2 ApplySpriteVertexTileFlags(tk2dTileMap tileMap, tk2dSpriteDefinition spriteDef, Vector2 pos, bool flipH, bool flipV, bool rot90)
		{
			return default(Vector2);
		}
	}
}
