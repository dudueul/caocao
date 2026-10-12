using System;
using System.Collections.Generic;
using UnityEngine;

public class UITable : UIWidgetContainer
{
	public delegate void OnReposition();

	public enum Direction
	{
		Down = 0,
		Up = 1
	}

	public enum Sorting
	{
		None = 0,
		Alphabetic = 1,
		Horizontal = 2,
		Vertical = 3,
		Custom = 4
	}

	public int columns;

	public Direction direction;

	public Sorting sorting;

	public UIWidget.Pivot pivot;

	public UIWidget.Pivot cellAlignment;

	public bool hideInactive;

	public bool keepWithinPanel;

	public Vector2 padding;

	public OnReposition onReposition;

	public Comparison<Transform> onCustomSort;

	protected UIPanel mPanel;

	protected bool mInitDone;

	protected bool mReposition;

	public bool repositionNow
	{
		set
		{
		}
	}

	public List<Transform> GetChildList()
	{
		return null;
	}

	protected virtual void Sort(List<Transform> list)
	{
	}

	protected virtual void Start()
	{
	}

	protected virtual void Init()
	{
	}

	protected virtual void LateUpdate()
	{
	}

	private void OnValidate()
	{
	}

	protected void RepositionVariableSize(List<Transform> children)
	{
	}

	public virtual void Reposition()
	{
	}
}
