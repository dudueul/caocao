using System;
using System.Collections.Generic;
using Thingsoft;
using UnityEngine;

public class BattleCameraController : MonoBehaviour
{
	public enum FlowUnitTarget
	{
		Attacker = 0,
		Target = 1
	}

	public Action OnTouchEventOccured;

	public Action OnTouchUp;

	private const float BATLEMAP_ORTHO_ZOOM_IN = 3.6f;

	private const float LOGICAL_ASPECT = 0.5625f;

	private const float HALF_UNIT_SIZE = 0.48f;

	private const float PANNING_THRESHOLD = 10f;

	private const int ZOOM_MIN_STEP = 1;

	private const int ZOOM_MAX_STEP = 4;

	private float stepDiff;

	private Camera m_camera;

	private float m_battleMapWidth;

	private float m_battleMapHeight;

	private float m_halfBattleMapWidth;

	private float m_halfBattleMapHeight;

	private float m_minZoomFactor;

	private float m_maxZoomFactor;

	private float m_minZoomCushionFactor;

	private float m_maxZoomCushionFactor;

	private int m_screenWidth;

	private int m_screenHeight;

	private float m_aspectMagic;

	private bool touchedBeforeIdleStatus;

	private ClickMiniMap m_miniMapBtnCtl;

	private GameInput m_gameInput;

	public Action<int> zoomStepChanged;

	private int m_zoomStep;

	private bool isRecon;

	private float cachedLastOrthgraphicSize;

	private Vector3 m_startPos;

	private Vector2 m_startTouchPos;

	private float m_startOrthoSize;

	private float m_startDistance;

	public bool isPinching { get; private set; }

	public bool isPanning { get; private set; }

	public bool isEditorMode { get; set; }

	public bool ignoreThresholdZoom { get; set; }

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

	public bool ignoreFocusFlowUnits { get; set; }

	public bool isZoomAndMove { get; private set; }

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private float AvoidOrthgraphicSizeNan(float desired)
	{
		return 0f;
	}

	public void SetZoomSize()
	{
	}

	private void OnDestroy()
	{
	}

	private void UpdateScreen()
	{
	}

	public void SetBattleMapSize(float mapWidth, float mapHeight)
	{
	}

	private void Update()
	{
	}

	public Vector3 ClampPosition(Vector3 pos)
	{
		return default(Vector3);
	}

	public Vector3 ClampPosition(Vector3 pos, float orthographicSize)
	{
		return default(Vector3);
	}

	private float GetWidthDeco()
	{
		return 0f;
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

	public void MoveCamera(Vector2 toPos, bool usingInFastforward = false)
	{
	}

	public void MoveEventCamera(Vector2 toPos, float TimeInterval, iTween.EaseType waveType)
	{
	}

	public Vector2 CaculateMoveEventCamera(Vector2 toPos)
	{
		return default(Vector2);
	}

	public void SetCameraPositionToObjectPosition(Transform unitTransform)
	{
	}

	public Vector2 GetCurrentCameraPosition()
	{
		return default(Vector2);
	}

	private void FocusFlowUnits(FlowUnitTarget flowTarget, Vector3 attackerPos, Vector3 targetPos)
	{
	}

	public void FocusFlowUnits(FlowUnitTarget flowTarget, Component attacker, Component target)
	{
	}

	public void FocusFlowUnits(FlowUnitTarget flowTarget, Component attacker, List<Component> targets)
	{
	}

	public void ZoomAndMoveCamera(Vector3 toPos, int targetZoomStep, float time)
	{
	}

	public void ZoomAndMoveCamera(Vector3 toPos, float orthoSize, float time)
	{
	}

	private void ZoomAndMoveUpdate(object value)
	{
	}

	private void ZoomAndMoveComplete(object prevZoomStep)
	{
	}

	private float GetOrhographicSize(int requestedZoomStep)
	{
		return 0f;
	}

	private int GetZoomStepByOrthgraphicSize()
	{
		return 0;
	}
}
