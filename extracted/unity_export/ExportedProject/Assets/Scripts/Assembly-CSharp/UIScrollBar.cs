using System;
using UnityEngine;

[ExecuteInEditMode]
public class UIScrollBar : UISlider
{
	private enum Direction
	{
		Horizontal = 0,
		Vertical = 1,
		Upgraded = 2
	}

	[HideInInspector]
	[SerializeField]
	protected float mSize;

	[HideInInspector]
	[SerializeField]
	private float mScroll;

	[HideInInspector]
	[SerializeField]
	private Direction mDir;

	[Obsolete]
	public float scrollValue
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float barSize
	{
		get
		{
			return 0f;
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

	protected override float LocalToValue(Vector2 localPos)
	{
		return 0f;
	}

	public override void ForceUpdate()
	{
	}
}
