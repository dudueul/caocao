using System.Collections.Generic;
using UnityEngine;

public class MClipingVerticalScrollView
{
	public float scrollPosition;

	public float itemHeight;

	private Vector2 _logScrollViewPosition;

	private float _scrollViewHeight;

	private bool _isEmptyLayout;

	private Color _color;

	private Rect _viewRect;

	private List<IMClipingScrollViewItem> _itemList;

	private Vector2 _deltaDrag;

	private bool _isDrag;

	public List<IMClipingScrollViewItem> itemList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isDrag
	{
		get
		{
			return false;
		}
	}

	public MClipingVerticalScrollView(float itemHeight, Color color)
	{
	}

	public void OnGUI()
	{
	}

	private void checkDrag()
	{
	}
}
