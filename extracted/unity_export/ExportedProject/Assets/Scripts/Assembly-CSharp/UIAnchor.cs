using UnityEngine;

[ExecuteInEditMode]
public class UIAnchor : MonoBehaviour
{
	public enum Side
	{
		BottomLeft = 0,
		Left = 1,
		TopLeft = 2,
		Top = 3,
		TopRight = 4,
		Right = 5,
		BottomRight = 6,
		Bottom = 7,
		Center = 8
	}

	public Camera uiCamera;

	public GameObject container;

	public Side side;

	public bool runOnlyOnce;

	public Vector2 relativeOffset;

	public Vector2 pixelOffset;

	[HideInInspector]
	[SerializeField]
	private UIWidget widgetContainer;

	private Transform mTrans;

	private Animation mAnim;

	private Rect mRect;

	private UIRoot mRoot;

	private bool mStarted;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void ScreenSizeChanged()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}
}
