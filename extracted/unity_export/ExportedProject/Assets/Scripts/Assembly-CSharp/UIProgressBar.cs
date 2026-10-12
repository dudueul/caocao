using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UIProgressBar : UIWidgetContainer
{
	public enum FillDirection
	{
		LeftToRight = 0,
		RightToLeft = 1,
		BottomToTop = 2,
		TopToBottom = 3
	}

	public delegate void OnDragFinished();

	public static UIProgressBar current;

	public OnDragFinished onDragFinished;

	public Transform thumb;

	[HideInInspector]
	[SerializeField]
	protected UIWidget mBG;

	[HideInInspector]
	[SerializeField]
	protected UIWidget mFG;

	[HideInInspector]
	[SerializeField]
	protected float mValue;

	[HideInInspector]
	[SerializeField]
	protected FillDirection mFill;

	protected Transform mTrans;

	protected bool mIsDirty;

	protected Camera mCam;

	protected float mOffset;

	public int numberOfSteps;

	public List<EventDelegate> onChange;

	public Transform cachedTransform
	{
		get
		{
			return null;
		}
	}

	public Camera cachedCamera
	{
		get
		{
			return null;
		}
	}

	public UIWidget foregroundWidget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UIWidget backgroundWidget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public FillDirection fillDirection
	{
		get
		{
			return FillDirection.LeftToRight;
		}
		set
		{
		}
	}

	public float value
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float alpha
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	protected bool isHorizontal
	{
		get
		{
			return false;
		}
	}

	protected bool isInverted
	{
		get
		{
			return false;
		}
	}

	protected void Start()
	{
	}

	protected virtual void Upgrade()
	{
	}

	protected virtual void OnStart()
	{
	}

	protected void Update()
	{
	}

	protected void OnValidate()
	{
	}

	protected float ScreenToValue(Vector2 screenPos)
	{
		return 0f;
	}

	protected virtual float LocalToValue(Vector2 localPos)
	{
		return 0f;
	}

	public virtual void ForceUpdate()
	{
	}

	protected void SetThumbPosition(Vector3 worldPos)
	{
	}
}
