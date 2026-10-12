using UnityEngine;

public class MiniMap : MonoBehaviour
{
	public delegate void InitCompleted(RenderTexture renderTexture);

	private IntVector2 m_worldSize;

	private RenderTexture m_renderTexture;

	private Camera m_camera;

	private const int MaxTextureSize = 584;

	private int m_textureSizeX;

	private int m_textureSizeY;

	private InitCompleted OnInitCompleted__BackingField;

	public float ratioWidth { get; set; }

	public float ratioHeight { get; set; }

	public bool inited { get; set; }

	public event InitCompleted OnInitCompleted
	{
		add
		{
		}
		remove
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void Init(object sender, MapDimensionChnagedEventArg args)
	{
	}

	public void RefreshTexture()
	{
	}

	public Rect GetLocalViewportRect(Vector3 pos, float orthographicSize)
	{
		return default(Rect);
	}

	public Vector3 GetOffsetPosFromMiniMapCamera(Vector3 worldPos)
	{
		return default(Vector3);
	}

	public Vector3 GetWorldPosFromOffsetPos(Vector3 offsetPos)
	{
		return default(Vector3);
	}
}
