using System;
using UnityEngine;

public class PanelPopupHeroListFilterItem : MonoBehaviour
{
	private EnumData.DeckFilterType m_filterType;

	public int data;

	private bool m_selected;

	private Action<EnumData.DeckFilterType, int, bool> onClickBtn__BackingField;

	private event Action<EnumData.DeckFilterType, int, bool> onClickBtn
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetInfo(EnumData.DeckFilterType filterType, int itemData, bool isSelected, Action<EnumData.DeckFilterType, int, bool> callback)
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
