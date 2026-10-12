using UnityEngine;

public class GuildWarMapInfo : MonoBehaviour
{
	private int m_iTileXCount;

	private int m_iTileYCount;

	private float m_fFaceWidth;

	private float m_fFaceHeight;

	private float m_fHalfFaceWidth;

	private float m_fHalfFaceHeight;

	private bool m_showGrid;

	private Vector2 m_tileSize;

	public Color m_gizmoColor;

	public Color m_gizmoDivideColor;

	public float FaceWidth
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float FaceHeight
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float HalfFaceWidth
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float HalfFaceHeight
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public int TileXCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int TileYCount
	{
		get
		{
			return 0;
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

	private void Reset()
	{
	}

	public void Init()
	{
	}

	private void OnEnable()
	{
	}

	private void DrawHexGizmos(Vector2 Pos)
	{
	}

	public Vector2 GetHexTileWorldPosition(Vector2 hexPos)
	{
		return default(Vector2);
	}

	public Vector2 GetHexTileWorldPosition(int x, int y)
	{
		return default(Vector2);
	}

	public void GetHexTileWorldPositionRange(out Vector2 min, out Vector2 max)
	{
		min = default(Vector2);
		max = default(Vector2);
	}

	public Vector2 GetHexTilePosByWorldPosition(Vector2 worldpos)
	{
		return default(Vector2);
	}

	public Vector2 GetHexTilePosByWorldPosition(float x, float y)
	{
		return default(Vector2);
	}

	public Vector2Int GetHexTilePosByWorldIntPosition(float x, float y)
	{
		return default(Vector2Int);
	}

	private void OnDrawGizmos()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}
}
