using System.Collections.Generic;
using UnityEngine;

public class TileGroup : MonoBehaviour
{
	private MapInfo m_mapInfo;

	private bool m_bshowFileImporter;

	private bool m_bshowEdit;

	private bool m_bshowTileInit;

	private bool m_bshowTileType;

	private bool m_bshowNoPass;

	private GameObject m_tiles;

	private Vector2 m_tileSize;

	private int m_itileTypeIndex;

	private int m_tileNoPassFlag;

	private bool m_bshowGrid;

	private bool m_showUnfilled;

	private List<Index> m_unfilledTiles;

	private List<int> m_tileIds;

	private Dictionary<int, List<GameObject>> m_tileId2GOList;

	public MapInfo mapData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Vector2 TileSize
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public GameObject tiles
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool ShowFileImporter
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowEdit
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowTileInit
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowTileType
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowTileNoPass
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowGrid
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public List<Index> unfilledTiles
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool showUnfilled
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public Dictionary<int, List<GameObject>> tileId2GoList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<int> tileIds
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void ClearListReferences()
	{
	}

	private void OnDrawGizmosSelected()
	{
	}

	private void Reset()
	{
	}

	public void Init()
	{
	}

	private void _DrawGrid()
	{
	}

	public void GridSwitch()
	{
	}

	public int GetTileTypeIndex()
	{
		return 0;
	}

	public void SetTileTypeIndex(int iTileTypeIndex)
	{
	}

	public int GetTileNoPassFlag()
	{
		return 0;
	}

	public void SetTileNoPassFlag(int noPassFlag)
	{
	}

	public void AddTile(int tileId, GameObject tileObj)
	{
	}

	public void DeleteTile(GameObject tileObj)
	{
	}

	public void DeleteTile(int tileId, GameObject tileObj)
	{
	}

	public void UpdateTileList()
	{
	}

	public int GetTileUsedCountByTileId(int tileId)
	{
		return 0;
	}

	public void OnMapSizeChanged()
	{
	}

	public bool FindOverlappedTiles()
	{
		return false;
	}

	public bool FindUnfilledTiles()
	{
		return false;
	}
}
