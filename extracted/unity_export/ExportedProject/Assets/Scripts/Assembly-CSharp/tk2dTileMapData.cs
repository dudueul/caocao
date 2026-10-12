using System.Collections.Generic;
using UnityEngine;
using tk2dRuntime.TileMap;

public class tk2dTileMapData : ScriptableObject
{
	public enum SortMethod
	{
		BottomLeft = 0,
		TopLeft = 1,
		BottomRight = 2,
		TopRight = 3
	}

	public enum TileType
	{
		Rectangular = 0,
		Isometric = 1
	}

	public Vector3 tileSize;

	public Vector3 tileOrigin;

	public TileType tileType;

	public SortMethod sortMethod;

	public bool layersFixedZ;

	public bool useSortingLayers;

	public GameObject[] tilePrefabs;

	[SerializeField]
	private TileInfo[] tileInfo;

	[SerializeField]
	public List<LayerInfo> tileMapLayers;

	public int NumLayers
	{
		get
		{
			return 0;
		}
	}

	public LayerInfo[] Layers
	{
		get
		{
			return null;
		}
	}

	public TileInfo GetTileInfoForSprite(int tileId)
	{
		return null;
	}

	public TileInfo[] GetOrCreateTileInfo(int numTiles)
	{
		return null;
	}

	public void GetTileOffset(out float x, out float y)
	{
		x = default(float);
		y = default(float);
	}

	private void InitLayers()
	{
	}
}
