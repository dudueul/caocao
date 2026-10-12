using UnityEngine;

namespace tk2dRuntime.TileMap
{
	public static class ColliderBuilder3D
	{
		public static void Build(tk2dTileMap tileMap, bool forceBuild)
		{
		}

		public static void BuildForChunk(tk2dTileMap tileMap, SpriteChunk chunk, int baseX, int baseY)
		{
		}

		private static void BuildLocalMeshForChunk(tk2dTileMap tileMap, SpriteChunk chunk, int baseX, int baseY, ref Vector3[] vertices, ref int[] indices)
		{
		}

		private static int CompareWeldVertices(Vector3 a, Vector3 b)
		{
			return 0;
		}

		private static Vector3[] WeldVertices(Vector3[] vertices, ref int[] indices)
		{
			return null;
		}

		private static int CompareDuplicateFaces(int[] indices, int face0index, int face1index)
		{
			return 0;
		}

		private static int[] RemoveDuplicateFaces(int[] indices)
		{
			return null;
		}
	}
}
