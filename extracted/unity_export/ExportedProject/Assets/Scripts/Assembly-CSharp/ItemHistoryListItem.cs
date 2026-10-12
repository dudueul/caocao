using System;
using UnityEngine;

public class ItemHistoryListItem : MonoBehaviour
{
	private bool m_isLast;

	private ItemUI m_itemUI;

	public GameObject m_arrow;

	public bool isLast
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public ItemUI itemUI
	{
		get
		{
			return null;
		}
	}

	public void SetItem(Item itemData, bool isLast, Action<ItemUI> OnClickDelegate)
	{
	}
}
