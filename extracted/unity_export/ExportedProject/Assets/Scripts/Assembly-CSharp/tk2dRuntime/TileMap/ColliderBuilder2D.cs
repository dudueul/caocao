using System.Collections.Generic;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	public static class ColliderBuilder2D
	{
		public static void Build(tk2dTileMap tileMap, bool forceBuild)
		{
		}

		public static void BuildForChunk(tk2dTileMap tileMap, SpriteChunk chunk, int baseX, int baseY)
		{
		}

		private static void BuildLocalMeshForChunk(tk2dTileMap tileMap, SpriteChunk chunk, int baseX, int baseY, ref Vector2[] vertices, ref int[] indices)
		{
		}

		private static int CompareWeldVertices(Vector2 a, Vector2 b)
		{
			return 0;
		}

		private static Vector2[] WeldVertices(Vector2[] vertices, ref int[] indices)
		{
			return null;
		}

		private static int CompareDuplicateFaces(int[] indices, int face0index, int face1index)
		{
			return 0;
		}

		private static int[] RemoveDuplicateEdges(int[] indices)
		{
			return null;
		}

		private static List<Vector2[]> MergeEdges(Vector2[] verts, int[] indices)
		{
			return null;
		}
	}
}
