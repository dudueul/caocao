using System;
using UnityEngine;

[Serializable]
public class MGUIPopupList
{
	public string buttonStyle;

	public string popupStyle;

	public string labelStyle;

	[SerializeField]
	private bool _isShowPopup;

	[SerializeField]
	private Vector2 _scrollViewVector;

	[SerializeField]
	private Rect _dropDownRect;

	[SerializeField]
	private Rect _labelRect;

	[SerializeField]
	private Rect _arrowRect;

	[SerializeField]
	private Texture2D _downArrow;

	[SerializeField]
	private Rect _rect;

	[SerializeField]
	private float _popupHeight;

	[SerializeField]
	private string[] _list;

	[SerializeField]
	private int _selectedNum;

	public Rect rect
	{
		get
		{
			return default(Rect);
		}
		set
		{
		}
	}

	public float popupHeight
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public string[] list
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int selectedNum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void Init()
	{
	}

	public void OnGUI()
	{
	}
}
