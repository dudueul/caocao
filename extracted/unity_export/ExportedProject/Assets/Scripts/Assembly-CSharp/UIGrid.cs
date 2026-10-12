using System;
using System.Collections.Generic;
using UnityEngine;

public class UIGrid : UIWidgetContainer
{
	public delegate void OnReposition();

	public enum Arrangement
	{
		Horizontal = 0,
		Vertical = 1,
		CellSnap = 2
	}

	public enum Sorting
	{
		None = 0,
		Alphabetic = 1,
		Horizontal = 2,
		Vertical = 3,
		Custom = 4
	}

	public Arrangement arrangement;

	public Sorting sorting;

	public UIWidget.Pivot pivot;

	public int maxPerLine;

	public float cellWidth;

	public float cellHeight;

	public bool animateSmoothly;

	public bool hideInactive;

	public bool keepWithinPanel;

	public OnReposition onReposition;

	public Comparison<Transform> onCustomSort;

	[HideInInspector]
	[SerializeField]
	private bool sorted;

	protected bool mReposition;

	protected UIPanel mPanel;

	protected bool mInitDone;

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

	public Transform GetChild(int index)
	{
		return null;
	}

	public int GetIndex(Transform trans)
	{
		return 0;
	}

	public void AddChild(Transform trans)
	{
	}

	public void AddChild(Transform trans, bool sort)
	{
	}

	public bool RemoveChild(Transform t)
	{
		return false;
	}

	protected virtual void Init()
	{
	}

	protected virtual void Start()
	{
	}

	protected virtual void Update()
	{
	}

	private void OnValidate()
	{
	}

	public static int SortByName(Transform a, Transform b)
	{
		return 0;
	}

	public static int SortHorizontal(Transform a, Transform b)
	{
		return 0;
	}

	public static int SortVertical(Transform a, Transform b)
	{
		return 0;
	}

	protected virtual void Sort(List<Transform> list)
	{
	}

	public virtual void Reposition()
	{
	}

	public void ConstrainWithinPanel()
	{
	}

	protected virtual void ResetPosition(List<Transform> list)
	{
	}
}
