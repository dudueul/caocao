using System;
using System.Collections.Generic;
using UnityEngine;

public class UIReuseGrid : UIWidgetContainer
{
	public enum Align
	{
		Top = 0,
		Center = 1
	}

	public delegate GameObject Func();

	public enum Direction
	{
		Down = 0,
		Up = 1
	}

	public UIReuseScrollView m_ReuseScrollView;

	public GameObject m_ScrollViewPrefab;

	public int m_cellWidth;

	public int m_cellHeight;

	public bool m_InitCreateData;

	public int m_Column;

	public Align m_itemVerticalAlign;

	private int m_maxLine;

	private UIReuseScrollViewCell[] m_cellList;

	private float lastPos;

	private List<IReuseCellData> m_listData;

	private Vector3 defaultPos;

	private Func create_fn;

	private bool m_inited;

	private float m_startOffsetX;

	private float m_startOffsetY;

	private bool m_isNextLoading;

	public Action callBackNextLoading;

	public Direction m_direction;

	public int MaxCellData
	{
		get
		{
			return 0;
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public IReuseCellData GetCellData(int index)
	{
		return null;
	}

	public void SendMessageFromCellList(string methodName)
	{
	}

	public void SendMessageFromCellList(string methodName, object obj)
	{
	}

	public void SendMessageFromCellList(string methodName, int idx)
	{
	}

	public void InitData(Func function)
	{
	}

	public bool IsInit()
	{
		return false;
	}

	public void Reposition()
	{
	}

	private void UpdateBounds(int count)
	{
	}

	public void ChangeItem(List<IReuseCellData> list)
	{
	}

	public void AddItem(IReuseCellData ListData, bool Update)
	{
	}

	public void InsertItem(int idx, IReuseCellData ListData, bool Update)
	{
	}

	public void RemoveItem(IReuseCellData ListData, bool Update)
	{
	}

	public void ClearItem(bool Update)
	{
	}

	public void RemoveCellAll()
	{
	}

	public void UpdateAllCellData()
	{
	}

	public void UpdateCellData(int idx)
	{
	}

	public void UpdateCellData(int idx, IReuseCellData cellData)
	{
	}

	public void SetPostion(int idx)
	{
	}

	private void Validate(bool Scroll, bool RefreshAll)
	{
	}

	private void UpdateListVer(bool scroll, bool RefreshAll)
	{
	}

	private void UpdateListVerDirectionUp(bool scroll, bool RefreshAll)
	{
	}

	private void UpdateListVerColumn(bool scroll, bool RefreshAll)
	{
	}

	private void UpdateListHor(bool scroll, bool RefreshAll)
	{
	}

	private void CreateListItem()
	{
	}
}
