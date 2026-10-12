using UnityEngine;

public class MapInfo : MonoBehaviour
{
	private int m_mapID;

	private string m_PrefabName;

	private Vector2 m_v2LayerSize;

	private float m_fFaceWidth;

	private float m_fFaceHeight;

	private float m_fHalfFaceWidth;

	private float m_fHalfFaceHeight;

	[HideInInspector]
	public int m_iTileXCount;

	[HideInInspector]
	public int m_iTileYCount;

	private Vector2 m_tileSize;

	[HideInInspector]
	public Color m_gizmoColor;

	[HideInInspector]
	public bool m_showGrid;

	[HideInInspector]
	public IntVector2 m_FirstBattleCameraPos;

	public string PrefabName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Vector2 v2LayerSize
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

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

	public IntVector2 tileSize
	{
		get
		{
			return default(IntVector2);
		}
		private set
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

	private void OnDrawGizmos()
	{
	}

	public void toggleShowGrid()
	{
	}
}
