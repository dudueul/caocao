using System;
using UnityEngine;

[ExecuteInEditMode]
public class UISlider : UIProgressBar
{
	private enum Direction
	{
		Horizontal = 0,
		Vertical = 1,
		Upgraded = 2
	}

	[HideInInspector]
	[SerializeField]
	private Transform foreground;

	[HideInInspector]
	[SerializeField]
	private float rawValue;

	[HideInInspector]
	[SerializeField]
	private Direction direction;

	[HideInInspector]
	[SerializeField]
	protected bool mInverted;

	[Obsolete]
	public float sliderValue
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	[Obsolete]
	public bool inverted
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	protected override void Upgrade()
	{
	}

	protected override void OnStart()
	{
	}

	protected void OnPressBackground(GameObject go, bool isPressed)
	{
	}

	protected void OnDragBackground(GameObject go, Vector2 delta)
	{
	}

	protected void OnPressForeground(GameObject go, bool isPressed)
	{
	}

	protected void OnDragForeground(GameObject go, Vector2 delta)
	{
	}

	protected void OnKey(KeyCode key)
	{
	}
}
