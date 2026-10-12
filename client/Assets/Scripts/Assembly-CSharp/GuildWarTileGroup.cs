using UnityEngine;

public class GuildWarTileGroup : MonoBehaviour
{
	private GuildWarMapInfo m_mapInfo;

	private GameObject m_tiles;

	private bool m_bshowGrid;

	private Vector2 m_tileSize;

	private bool m_bshowTileType;

	private int m_itileTypeIndex;

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

	public GuildWarMapInfo mapData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void Init()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	public void OnMapSizeChanged()
	{
	}

	public int GetTileTypeIndex()
	{
		return 0;
	}

	public int GetTileTexture()
	{
		return 0;
	}

	public void SetTileTypeIndex(int iTileTypeIndex)
	{
	}
}
