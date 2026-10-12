using System;
using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class GeoMan : SceneSingleton<GeoMan>
{
	public delegate void MapDimensionChanged(object sender, MapDimensionChnagedEventArg args);

	private GameObject m_battleMapObjectsParent;

	private int m_mapID;

	private GameObject m_currentMapObj;

	private MapDimensionChanged OnMapDimensionChanged__BackingField;

	private IntVector2 m_dimension;

	private List<StackTileData> m_tiles;

	public QuickPathFinder m_quickPathFinder;

	public List<bool> m_eventMapTilesMovableness;

	private IntVector2 m_focusedTilePos;

	private int m_focusedTileID;

	private List<IntVector2> m_markedTiles;

	public GameObject battleMapObjectParent
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int mapID
	{
		get
		{
			return 0;
		}
	}

	public GameObject currentMapObj
	{
		get
		{
			return null;
		}
	}

	public IntVector2 dimension
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public int diameter { get; private set; }

	public int dimensionX
	{
		get
		{
			return 0;
		}
	}

	public int dimensionY
	{
		get
		{
			return 0;
		}
	}

	public int tileLength
	{
		get
		{
			return 0;
		}
	}

	public IntVector2 focusedTilePos
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public int focusedTileID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public event MapDimensionChanged OnMapDimensionChanged
	{
		add
		{
		}
		remove
		{
		}
	}

	protected GeoMan()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void SetCurrentMapObj(GameObject currentMapObj)
	{
	}

	public BattleMap GetCurrentMapInfo()
	{
		return null;
	}

	public void LoadTileDatas()
	{
	}

	public void InsertNewMapObjectData(BattleMapObject newObj)
	{
	}

	public void UpdateWholeTileIDs(object sender, EventArgs args)
	{
	}

	public void SetEventNonMovableTiles(List<IntVector2> nonmovableTiles)
	{
	}

	public void LoadEventMapTileData()
	{
	}

	public int GetTileByGridPos(IntVector2 gridPos)
	{
		return 0;
	}

	public List<BattleMapObject> GetMapObjectsByGridPos(IntVector2 gridPos)
	{
		return null;
	}

	public BattleMapObject GetMapObjectByGridPos(int mapObjectId, IntVector2 gridPos)
	{
		return null;
	}

	public void MarkTileAsMovable(GameObject unitObj, IntVector2 gridPos)
	{
	}

	public void MarkTileAsAttackable(GameObject unitObj, IntVector2 gridPos)
	{
	}

	public void MarkTileAsArtificial(IntVector2 gridPos, bool isEnabled)
	{
	}

	public void MarkTileAsAvailableToUseItem(IntVector2 gridPos)
	{
	}

	public void MarkTileAsAttackRange(IntVector2 gridPos, bool attackable)
	{
	}

	public void MarkTileAsMultiTarget(IntVector2 gridPos, bool isBaseTarget)
	{
	}

	public void MarkTileAsPincerable(IntVector2 gridPos)
	{
	}

	public void UnmarkTile(IntVector2 gridPos)
	{
	}

	public void ClearMarkedTiles()
	{
	}

	public bool IsTileMarked(IntVector2 tilePos)
	{
		return false;
	}

	public EnumData.TileStatus GetTileStatus(IntVector2 gridPos)
	{
		return EnumData.TileStatus.None;
	}

	public TileData GetTileDataByGrid(IntVector2 gridPos)
	{
		return null;
	}

	public TileData GetTileDataByIdx(int idx)
	{
		return null;
	}

	public BattleMapObject CreateNewMapObject(int mapObjectId, IntVector2 pivotPos, int initState, int appearance, bool isUnit, int levelCorrect, EnumData.OccupyType occuType, bool isCastle, string sortingLayerName, bool createAfterMapLoaded, GameObject parent, ObjectSide side, string name, bool forceAbsoluteLevel)
	{
		return null;
	}

	private void CreateBackgroundBigSelection()
	{
	}

	public int GetTileIdxByGridPos(IntVector2 gridPos)
	{
		return 0;
	}

	public int GetTileIdxByGridPos(int x, int y)
	{
		return 0;
	}

	public void ShowTileInfo()
	{
	}

	private void SetATileInfo(int tileIdx)
	{
	}

	public void HideTileInfo()
	{
	}

	public void FocusTile(int tileID)
	{
	}

	public int GetTileIDByTileIdx(int tileIdx)
	{
		return 0;
	}

	private List<int> FindTileIdxsByTileID(int tileID)
	{
		return null;
	}

	public List<int> GetTileTypeList()
	{
		return null;
	}

	public IntVector2? GetTileGridPos(int tileID)
	{
		return null;
	}

	public void SetObjectDye(IntVector2 pos, UnitSide side = UnitSide.OWN)
	{
	}
}
