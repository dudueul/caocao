using System;
using UnityEngine;

[ExecuteInEditMode]
public class tk2dUIScrollableArea : MonoBehaviour
{
	public enum Axes
	{
		XAxis = 0,
		YAxis = 1
	}

	[SerializeField]
	private float contentLength;

	[SerializeField]
	private float visibleAreaLength;

	public GameObject contentContainer;

	public tk2dUIScrollbar scrollBar;

	public tk2dUIItem backgroundUIItem;

	public Axes scrollAxes;

	public bool allowSwipeScrolling;

	public bool allowScrollWheel;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayout backgroundLayoutItem;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayoutContainer contentLayoutContainer;

	private bool isBackgroundButtonDown;

	private bool isBackgroundButtonOver;

	private Vector3 swipeScrollingPressDownStartLocalPos;

	private Vector3 swipeScrollingContentStartLocalPos;

	private Vector3 swipeScrollingContentDestLocalPos;

	private bool isSwipeScrollingInProgress;

	private const float SWIPE_SCROLLING_FIRST_SCROLL_THRESHOLD = 0.02f;

	private const float WITHOUT_SCROLLBAR_FIXED_SCROLL_WHEEL_PERCENT = 0.1f;

	private Vector3 swipePrevScrollingContentPressLocalPos;

	private float swipeCurrVelocity;

	private float snapBackVelocity;

	private Action<tk2dUIScrollableArea> OnScroll__BackingField;

	public string SendMessageOnScrollMethodName;

	private float percent;

	private static readonly Vector3[] boxExtents;

	public float ContentLength
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float VisibleAreaLength
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public tk2dUILayout BackgroundLayoutItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public tk2dUILayoutContainer ContentLayoutContainer
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject SendMessageTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public float Value
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	private Vector3 ContentContainerOffset
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public event Action<tk2dUIScrollableArea> OnScroll
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetScrollPercentWithoutEvent(float newScrollPercent)
	{
	}

	public float MeasureContentLength()
	{
		return 0f;
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Start()
	{
	}

	private void BackgroundHoverOverScrollWheelChange(float mouseWheelChange)
	{
	}

	private void ScrollBarMove(tk2dUIScrollbar scrollBar)
	{
	}

	private void SetContentPosition()
	{
	}

	private void BackgroundButtonDown()
	{
	}

	private void BackgroundOverUpdate()
	{
	}

	private void UpdateSwipeScrollDestintationPosition()
	{
	}

	private void BackgroundButtonRelease()
	{
	}

	private void BackgroundButtonHoverOver()
	{
	}

	private void BackgroundButtonHoverOut()
	{
	}

	private Vector3 CalculateClickWorldPos(tk2dUIItem btn)
	{
		return default(Vector3);
	}

	private void UpdateScrollbarActiveState()
	{
	}

	private void ContentLengthVisibleAreaLengthChange(float prevContentLength, float newContentLength, float prevVisibleAreaLength, float newVisibleAreaLength)
	{
	}

	private void UnpressAllUIItemChildren()
	{
	}

	private void TargetOnScrollCallback()
	{
	}

	private static void GetRendererBoundsInChildren(Matrix4x4 rootWorldToLocal, Vector3[] minMax, Transform t)
	{
	}

	private void LayoutReshaped(Vector3 dMin, Vector3 dMax)
	{
	}

	private void ContentLayoutChangeCallback()
	{
	}
}
