using UnityEngine;

public class UIReuseScrollView : MonoBehaviour
{
	public enum Movement
	{
		Horizontal = 0,
		Vertical = 1,
		Unrestricted = 2,
		Custom = 3
	}

	public enum DragEffect
	{
		None = 0,
		Momentum = 1,
		MomentumAndSpring = 2
	}

	public enum ShowCondition
	{
		Always = 0,
		OnlyIfNeeded = 1,
		WhenDragging = 2
	}

	public delegate void OnDragFinished();

	public Movement movement;

	public DragEffect dragEffect;

	public bool restrictWithinPanel;

	public bool disableDragIfFits;

	public bool smoothDragStart;

	public bool iOSDragEmulation;

	public float scrollWheelFactor;

	public float momentumAmount;

	public UIScrollBar horizontalScrollBar;

	public UIScrollBar verticalScrollBar;

	public ShowCondition showScrollBars;

	public Vector2 customMovement;

	public Vector2 relativePositionOnReset;

	public OnDragFinished onDragFinished;

	[HideInInspector]
	[SerializeField]
	private Vector3 scale;

	private Transform mTrans;

	private UIPanel mPanel;

	private Plane mPlane;

	private Vector3 mLastPos;

	private bool mPressed;

	private Vector3 mMomentum;

	private float mScroll;

	private Bounds mBounds;

	private bool mShouldMove;

	private bool mIgnoreCallbacks;

	private int mDragID;

	private Vector2 mDragStartOffset;

	private bool mDragStarted;

	public Vector3 initPosition { get; private set; }

	public Vector2 initClipOffset { get; private set; }

	public bool isShouldMove
	{
		get
		{
			return false;
		}
	}

	public UIPanel panel
	{
		get
		{
			return null;
		}
	}

	public Bounds bounds
	{
		get
		{
			return default(Bounds);
		}
		set
		{
		}
	}

	public bool canMoveHorizontally
	{
		get
		{
			return false;
		}
	}

	public bool canMoveVertically
	{
		get
		{
			return false;
		}
	}

	public virtual bool shouldMoveHorizontally
	{
		get
		{
			return false;
		}
	}

	public virtual bool shouldMoveVertically
	{
		get
		{
			return false;
		}
	}

	protected virtual bool shouldMove
	{
		get
		{
			return false;
		}
	}

	public Vector3 currentMomentum
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	public void SaveCurrentPosition()
	{
	}

	private void OnPanelChange()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public bool RestrictWithinBounds(bool instant)
	{
		return false;
	}

	public bool RestrictWithinBounds(bool instant, bool horizontal, bool vertical)
	{
		return false;
	}

	public void DisableSpring()
	{
	}

	public virtual void UpdateScrollbars(bool recalculateBounds)
	{
	}

	public virtual void SetDragAmount(float x, float y, bool updateScrollbars)
	{
	}

	public void ResetPosition()
	{
	}

	private void OnHorizontalBar()
	{
	}

	private void OnVerticalBar()
	{
	}

	public virtual void MoveRelative(Vector3 relative)
	{
	}

	public void MoveAbsolute(Vector3 absolute)
	{
	}

	public void Press(bool pressed)
	{
	}

	public void Drag()
	{
	}

	public void Scroll(float delta)
	{
	}

	private void LateUpdate()
	{
	}

	public void StopScroll()
	{
	}
}
