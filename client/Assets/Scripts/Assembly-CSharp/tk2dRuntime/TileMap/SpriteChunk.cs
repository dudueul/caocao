using System;
using System.Collections.Generic;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	[Serializable]
	public class SpriteChunk
	{
		private bool dirty;

		public int[] spriteIds;

		public GameObject gameObject;

		public Mesh mesh;

		public MeshCollider meshCollider;

		public Mesh colliderMesh;

		public List<EdgeCollider2D> edgeColliders;

		public bool Dirty
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool IsEmpty
		{
			get
			{
				return false;
			}
		}

		public bool HasGameData
		{
			get
			{
				return false;
			}
		}

		public void DestroyGameData(tk2dTileMap tileMap)
		{
		}

		public void DestroyColliderData(tk2dTileMap tileMap)
		{
		}
	}
}
