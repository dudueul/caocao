using System;
using UnityEngine;

[ExecuteInEditMode]
public class tk2dUIScrollbar : MonoBehaviour
{
	public enum Axes
	{
		XAxis = 0,
		YAxis = 1
	}

	public tk2dUIItem barUIItem;

	public float scrollBarLength;

	public tk2dUIItem thumbBtn;

	public Transform thumbTransform;

	public float thumbLength;

	public tk2dUIItem upButton;

	private tk2dUIHoverItem hoverUpButton;

	public tk2dUIItem downButton;

	private tk2dUIHoverItem hoverDownButton;

	public float buttonUpDownScrollDistance;

	public bool allowScrollWheel;

	public Axes scrollAxes;

	public tk2dUIProgressBar highlightProgressBar;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayout barLayoutItem;

	private bool isScrollThumbButtonDown;

	private bool isTrackHoverOver;

	private float percent;

	private Vector3 moveThumbBtnOffset;

	private int scrollUpDownButtonState;

	private float timeOfUpDownButtonPressStart;

	private float repeatUpDownButtonHoldCounter;

	private const float WITHOUT_SCROLLBAR_FIXED_SCROLL_WHEEL_PERCENT = 0.1f;

	private const float INITIAL_TIME_TO_REPEAT_UP_DOWN_SCROLL_BUTTON_SCROLLING_ON_HOLD = 0.55f;

	private const float TIME_TO_REPEAT_UP_DOWN_SCROLL_BUTTON_SCROLLING_ON_HOLD = 0.45f;

	private Action<tk2dUIScrollbar> OnScroll__BackingField;

	public string SendMessageOnScrollMethodName;

	public tk2dUILayout BarLayoutItem
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

	public event Action<tk2dUIScrollbar> OnScroll
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

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void TrackHoverScrollWheelChange(float mouseWheelChange)
	{
	}

	private void SetScrollThumbPosition()
	{
	}

	private void MoveScrollThumbButton()
	{
	}

	private Vector3 CalculateClickWorldPos(tk2dUIItem btn)
	{
		return default(Vector3);
	}

	private void ScrollToPosition(Vector3 worldPos)
	{
	}

	private void ScrollTrackButtonDown()
	{
	}

	private void ScrollTrackButtonHoverOver()
	{
	}

	private void ScrollTrackButtonHoverOut()
	{
	}

	private void ScrollThumbButtonDown()
	{
	}

	private void ScrollThumbButtonRelease()
	{
	}

	private void ScrollUpButtonDown()
	{
	}

	private void ScrollUpButtonUp()
	{
	}

	private void ScrollDownButtonDown()
	{
	}

	private void ScrollDownButtonUp()
	{
	}

	public void ScrollUpFixed()
	{
	}

	public void ScrollDownFixed()
	{
	}

	private void CheckRepeatScrollUpDownButton()
	{
	}

	public void ScrollDirection(int dir)
	{
	}

	private float CalcScrollPercentOffsetButtonScrollDistance()
	{
		return 0f;
	}

	private void LayoutReshaped(Vector3 dMin, Vector3 dMax)
	{
	}
}
