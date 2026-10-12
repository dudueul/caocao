using UnityEngine;

public class UIReuseDragScrollView : MonoBehaviour
{
	public UIReuseScrollView scrollView;

	[HideInInspector]
	[SerializeField]
	private UIReuseScrollView draggablePanel;

	private Transform mTrans;

	private UIReuseScrollView mScroll;

	private bool mAutoFind;

	public void SetScrollView(UIReuseScrollView scroll)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	private void FindScrollView()
	{
	}

	private void Start()
	{
	}

	private void OnPress(bool pressed)
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void OnScroll(float delta)
	{
	}
}
