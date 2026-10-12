using System;
using UnityEngine;

public class TreasureFilterListItem : MonoBehaviour
{
	private EnumData.TreasureFilterType m_filterType;

	public int data;

	private bool m_selected;

	private UIButton m_btn;

	private Action<EnumData.TreasureFilterType, int, bool> onClickBtn__BackingField;

	private UIButton btn
	{
		get
		{
			return null;
		}
	}

	private event Action<EnumData.TreasureFilterType, int, bool> onClickBtn
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetInfo(EnumData.TreasureFilterType filterType, int itemData, bool isSelected, Action<EnumData.TreasureFilterType, int, bool> callback)
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
