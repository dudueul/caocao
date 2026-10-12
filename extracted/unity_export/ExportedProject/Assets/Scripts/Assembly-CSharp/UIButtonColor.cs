using System;
using UnityEngine;

[ExecuteInEditMode]
public class UIButtonColor : UIWidgetContainer
{
	public enum State
	{
		Normal = 0,
		Hover = 1,
		Pressed = 2,
		Disabled = 3
	}

	public GameObject tweenTarget;

	public Color hover;

	public Color pressed;

	public Color disabledColor;

	public float duration;

	[NonSerialized]
	protected Color mStartingColor;

	[NonSerialized]
	protected Color mDefaultColor;

	[NonSerialized]
	protected bool mInitDone;

	[NonSerialized]
	protected UIWidget mWidget;

	[NonSerialized]
	protected State mState;

	public State state
	{
		get
		{
			return State.Normal;
		}
		set
		{
		}
	}

	public Color defaultColor
	{
		get
		{
			return default(Color);
		}
		set
		{
		}
	}

	public virtual bool isEnabled
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void ResetDefaultColor()
	{
	}

	private void Start()
	{
	}

	protected virtual void OnInit()
	{
	}

	protected virtual void OnEnable()
	{
	}

	protected virtual void OnDestroy()
	{
	}

	protected virtual void OnDisable()
	{
	}

	protected virtual void OnHover(bool isOver)
	{
	}

	protected virtual void OnPress(bool isPressed)
	{
	}

	protected virtual void OnDragOver()
	{
	}

	protected virtual void OnDragOut()
	{
	}

	protected virtual void OnSelect(bool isSelected)
	{
	}

	public virtual void SetState(State state, bool instant)
	{
	}

	public void UpdateColor(bool instant)
	{
	}

	protected void SetChildColor(Color color)
	{
	}
}
