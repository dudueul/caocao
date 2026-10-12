using System;
using System.Collections.Generic;
using UnityEngine;
using tk2dRuntime;
using tk2dRuntime.TileMap;

[ExecuteInEditMode]
public class tk2dTileMap : MonoBehaviour, ISpriteCollectionForceBuild
{
	[Serializable]
	public class TilemapPrefabInstance
	{
		public int x;

		public int y;

		public int layer;

		public GameObject instance;
	}

	[Flags]
	public enum BuildFlags
	{
		Default = 0,
		EditMode = 1,
		ForceBuild = 2
	}

	public string editorDataGUID;

	public tk2dTileMapData data;

	public GameObject renderData;

	[SerializeField]
	private tk2dSpriteCollectionData spriteCollection;

	[SerializeField]
	private int spriteCollectionKey;

	public int width;

	public int height;

	public int partitionSizeX;

	public int partitionSizeY;

	[SerializeField]
	private Layer[] layers;

	[SerializeField]
	private ColorChannel colorChannel;

	[SerializeField]
	private GameObject prefabsRoot;

	[SerializeField]
	private List<TilemapPrefabInstance> tilePrefabsList;

	[SerializeField]
	private bool _inEditMode;

	public string serializedMeshPath;

	public tk2dSpriteCollectionData Editor__SpriteCollection
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public tk2dSpriteCollectionData SpriteCollectionInst
	{
		get
		{
			return null;
		}
	}

	public bool AllowEdit
	{
		get
		{
			return false;
		}
	}

	public List<TilemapPrefabInstance> TilePrefabsList
	{
		get
		{
			return null;
		}
	}

	public Layer[] Layers
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public ColorChannel ColorChannel
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject PrefabsRoot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void Build()
	{
	}

	public void ForceBuild()
	{
	}

	private void ClearSpawnedInstances()
	{
	}

	private void SetPrefabsRootActive(bool active)
	{
	}

	public void Build(BuildFlags buildFlags)
	{
	}

	public bool GetTileAtPosition(Vector3 position, out int x, out int y)
	{
		x = default(int);
		y = default(int);
		return false;
	}

	public bool GetTileFracAtPosition(Vector3 position, out float x, out float y)
	{
		x = default(float);
		y = default(float);
		return false;
	}

	public Vector3 GetTilePosition(int x, int y)
	{
		return default(Vector3);
	}

	public int GetTileIdAtPosition(Vector3 position, int layer)
	{
		return 0;
	}

	public TileInfo GetTileInfoForTileId(int tileId)
	{
		return null;
	}

	public Color GetInterpolatedColorAtPosition(Vector3 position)
	{
		return default(Color);
	}

	public bool UsesSpriteCollection(tk2dSpriteCollectionData spriteCollection)
	{
		return false;
	}

	public void EndEditMode()
	{
	}

	public void TouchMesh(Mesh mesh)
	{
	}

	public void DestroyMesh(Mesh mesh)
	{
	}

	public int GetTilePrefabsListCount()
	{
		return 0;
	}

	public void GetTilePrefabsListItem(int index, out int x, out int y, out int layer, out GameObject instance)
	{
		x = default(int);
		y = default(int);
		layer = default(int);
		instance = null;
	}

	public void SetTilePrefabsList(List<int> xs, List<int> ys, List<int> layers, List<GameObject> instances)
	{
	}

	public int GetTile(int x, int y, int layer)
	{
		return 0;
	}

	public tk2dTileFlags GetTileFlags(int x, int y, int layer)
	{
		return tk2dTileFlags.None;
	}

	public void SetTile(int x, int y, int layer, int tile)
	{
	}

	public void SetTileFlags(int x, int y, int layer, tk2dTileFlags flags)
	{
	}

	public void ClearTile(int x, int y, int layer)
	{
	}
}
