using System;
using Thingsoft;
using UnityEngine;

public class WorldMapCameraController : MonoBehaviour
{
	public static WorldMapCameraController instance;

	private const int ZOOM_MIN_STEP = 1;

	private const int ZOOM_MAX_STEP = 4;

	private const float WORLDMAP_ORTHO_ZOOM_IN = 3.6f;

	private const float LOGICAL_ASPECT = 0.5625f;

	private const float PANNING_THRESHOLD = 10f;

	private float stepDiff;

	public Action<int> zoomStepChanged;

	private int m_zoomStep;

	private Camera m_camera;

	private const float WORLD_MAP_WIDTH = 61.7f;

	private const float WORLD_MAP_HEIGHT = 41.2f;

	private const float HALF_WORLD_MAP_WIDTH = 30.85f;

	private const float HALF_WORLD_MAP_HEIGHT = 20.6f;

	private float m_aspectMagic;

	private MeshCollider m_waterCollider;

	private Texture2D m_flowMap;

	private int m_screenWidth;

	private int m_screenHeight;

	private float m_minZoomFactor;

	private float m_maxZoomFactor;

	private float m_minZoomCushionFactor;

	private float m_maxZoomCushionFactor;

	private GameInput m_gameInput;

	private Vector3 m_startPos;

	private Vector2 m_startTouchPos;

	private float m_startOrthoSize;

	private float m_startDistance;

	public int zoomStep
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isPinching { get; private set; }

	public bool isPanning { get; private set; }

	public bool isZoomAndMove { get; private set; }

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void Start()
	{
	}

	private void UpdateScreen()
	{
	}

	private void Update()
	{
	}

	public Vector3 ClampPosition(Vector3 pos, float orthographicSize)
	{
		return default(Vector3);
	}

	public Vector3 ClampPosition(Vector3 pos)
	{
		return default(Vector3);
	}

	private void OnBegan(Vector2 pos)
	{
	}

	private void OnMove(Vector2 pos)
	{
	}

	private void OnEnded(Vector2 pos)
	{
	}

	private void OnPinchBegan(float distance)
	{
	}

	private void OnPinchMove(float distance)
	{
	}

	private void OnPinchEnded(float distance)
	{
	}

	public void MoveCamera(Vector3 toPos, float time)
	{
	}

	public void ZoomAndMoveCamera(Vector3 toPos, int targetZoomStep, float time)
	{
	}

	private void ZoomAndMoveUpdate(object value)
	{
	}

	private void ZoomAndMoveComplete(object prevZoomStep)
	{
	}

	public bool IsPositionOnWater(Vector2 pos)
	{
		return false;
	}

	private void InvalidateZoomFactor()
	{
	}

	private int GetZoomStepByOrthgraphicSize()
	{
		return 0;
	}

	private float GetOrhographicSize(int requestedZoomStep)
	{
		return 0f;
	}
}
