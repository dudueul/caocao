using System;
using UnityEngine;

public class PanelPopupEventListFilterItem : MonoBehaviour
{
	private EnumData.EventFilterType m_filterType;

	private bool m_selected;

	private int m_data;

	private Action<EnumData.EventFilterType, int, bool> onClickBtn__BackingField;

	public int data
	{
		get
		{
			return 0;
		}
	}

	private event Action<EnumData.EventFilterType, int, bool> onClickBtn
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetInfo(EnumData.EventFilterType filterType, int itemData, bool isSelected, Action<EnumData.EventFilterType, int, bool> callback)
	{
	}

	public void OnClickBtn()
	{
	}

	public void UpdateButton(bool isSelected)
	{
	}

	private void Selected(bool selected)
	{
	}
}
