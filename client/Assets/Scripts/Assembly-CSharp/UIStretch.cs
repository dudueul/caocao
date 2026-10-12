using UnityEngine;

[ExecuteInEditMode]
public class UIStretch : MonoBehaviour
{
	public enum Style
	{
		None = 0,
		Horizontal = 1,
		Vertical = 2,
		Both = 3,
		BasedOnHeight = 4,
		FillKeepingRatio = 5,
		FitInternalKeepingRatio = 6
	}

	public Camera uiCamera;

	public GameObject container;

	public Style style;

	public bool runOnlyOnce;

	public Vector2 relativeSize;

	public Vector2 initialSize;

	public Vector2 borderPadding;

	[HideInInspector]
	[SerializeField]
	private UIWidget widgetContainer;

	private Transform mTrans;

	private UIWidget mWidget;

	private UISprite mSprite;

	private UIPanel mPanel;

	private UIRoot mRoot;

	private Animation mAnim;

	private Rect mRect;

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
