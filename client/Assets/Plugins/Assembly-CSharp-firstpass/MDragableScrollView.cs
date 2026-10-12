using System;
using UnityEngine;

[Serializable]
public abstract class MDragableScrollView
{
	public float scrollPosition;

	public float itemHeight;

	[SerializeField]
	protected Vector2 _logScrollViewPosition;

	[SerializeField]
	protected float _scrollViewHeight;

	[SerializeField]
	protected GUIStyle _style;

	[SerializeField]
	protected Color _color;

	[SerializeField]
	protected Vector2 _deltaDrag;

	[SerializeField]
	protected bool _isDrag;

	protected Rect _scrollViewRect;

	public bool isDrag
	{
		get
		{
			return false;
		}
	}

	public MDragableScrollView(float itemHeight, GUIStyle style, Color color)
	{
	}

	public void OnGUI()
	{
	}

	public void ResetScrollViewHeight()
	{
	}

	public abstract void GotoEndOfScroll();

	protected abstract void SetScrollViewPosition();

	protected abstract void OnGUIScrollViewInside();

	private void checkDrag()
	{
	}
}
