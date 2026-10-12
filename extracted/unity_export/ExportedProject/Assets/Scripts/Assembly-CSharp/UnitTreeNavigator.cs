using Thingsoft;
using UnityEngine;

public class UnitTreeNavigator : MonoBehaviour
{
	public Transform[] targetsForScale;

	public Vector2 bound;

	public UIScrollBar horizontalScrollBar;

	public UIScrollBar verticalScrollBar;

	private GameInput m_gameInput;

	private Vector3[] m_startLocalScales;

	private Vector2 m_localStartPos;

	private float m_startDist;

	private Vector2 m_dragStartPos;

	private Vector3 m_scaleStartPos;

	private float m_localScale;

	private float m_localStartScale;

	private Vector2 m_halfScreenSize;

	private Vector2 m_halfBound;

	private float m_minScale;

	private const float m_maxScale = 1.5f;

	private const float m_maxScaleCushion = 1.8f;

	private GameObject m_bgGo;

	private bool m_beganPan;

	private bool m_beganPinch;

	private void Awake()
	{
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

	private void Update()
	{
	}

	private Vector2 ConvertMousePosToWorldPos(Vector2 mousePosition)
	{
		return default(Vector2);
	}

	private bool isOverUI()
	{
		return false;
	}

	private void OnBegan(Vector2 position)
	{
	}

	private void OnMove(Vector2 position)
	{
	}

	private void OnEnded(Vector2 position)
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

	private void AdjustPosition()
	{
	}

	public Vector3 ClampPosition(Vector3 pos)
	{
		return default(Vector3);
	}

	private void UpdateTransforms()
	{
	}

	private void UpdateScrollBar()
	{
	}
}
